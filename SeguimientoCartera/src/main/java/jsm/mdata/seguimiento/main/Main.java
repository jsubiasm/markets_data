/**
 * 
 */
package jsm.mdata.seguimiento.main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsm.mdata.seguimiento.constantes.Cons;
import jsm.mdata.seguimiento.dao.DatosDAO;
import jsm.mdata.seguimiento.dto.GanPerProdPesoDTO;
import jsm.mdata.seguimiento.dto.MovimientoDTO;
import jsm.mdata.seguimiento.dto.ProductoDTO;
import jsm.mdata.seguimiento.dto.ProductoVarDTO;

/**
 * 
 * @author JSM
 *
 */
public class Main
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	/**
	 * 
	 */
	private static final String DB_PROTOCOL = "jdbc:derby:";

	/**
	 * 
	 */
	private static final String DB_PATH = "C:\\_JSM\\SeguimientoCartera\\03_Fuentes\\markets_data\\SeguimientoCartera\\derby\\seguimiento_cartera";

	/**
	 * @param connection
	 * @throws Throwable
	 */
	private static void validate_TB02_MOVIMIENTOS(Connection connection) throws Throwable
	{
		List<MovimientoDTO> listaMovimientos = DatosDAO.select_TB02_MOVIMIENTOS(connection);
		for (MovimientoDTO movimiento : listaMovimientos)
		{
			BigDecimal totalCalculado = movimiento.getNumeroTitulos().multiply(movimiento.getPrecioTitulo()).add(movimiento.getComision());
			if (!similar(movimiento.getTotal(), totalCalculado))
			{
				throw new Exception("Los totales del movimiento no coinciden [" + movimiento.getMovimientoId() + "] [" + movimiento.getProductoId() + "] [" + movimiento.getTotal() + "] [" + totalCalculado + "]");
			}
			else
			{
				LOGGER.info("TB02_MOVIMIENTOS - OK -> [" + movimiento.getMovimientoId() + "]");
			}
		}
	}

	/**
	 * @param mov
	 * @param producto
	 * @return
	 */
	private static String getMapKey(MovimientoDTO mov, ProductoDTO producto)
	{
		return mov.getProductoId() + producto.getNombre() + mov.getComercializador() + producto.getInstrumento() + mov.getMercado() + producto.getMoneda() + producto.getProveedor() + producto.getSubtipoActivo() + producto.getTipoActivo() + producto.getUsoIngresos();
	}

	/**
	 * @param gpp
	 * @return
	 */
	private static String getMapKey(GanPerProdPesoDTO gpp)
	{
		return gpp.getProductoId() + gpp.getNombre() + gpp.getComercializador() + gpp.getInstrumento() + gpp.getMercado() + gpp.getMoneda() + gpp.getProveedor() + gpp.getSubtipoActivo() + gpp.getTipoActivo() + gpp.getUsoIngresos();
	}

	/**
	 * @param connection
	 * @return
	 * @throws Throwable
	 */
	private static Map<String, GanPerProdPesoDTO> validate_VW03_GAN_PER_PROD_PESO(Connection connection) throws Throwable
	{
		Map<String, GanPerProdPesoDTO> mapGanPerProdPeso = new HashMap<String, GanPerProdPesoDTO>();
		List<MovimientoDTO> listaMovimientos = DatosDAO.select_TB02_MOVIMIENTOS(connection);
		for (MovimientoDTO mov : listaMovimientos)
		{
			ProductoDTO producto = DatosDAO.select_TB02_PRODUCTOS(connection, mov.getProductoId()).get(0);
			if (mapGanPerProdPeso.containsKey(getMapKey(mov, producto)))
			{
				GanPerProdPesoDTO gpp = mapGanPerProdPeso.get(getMapKey(mov, producto));
				if (!gpp.getProductoId().equalsIgnoreCase(mov.getProductoId()))
				{
					throw new Exception("Los IDs de producto no coinciden [" + gpp.getProductoId() + "] [" + mov.getProductoId() + "]");
				}
				gpp.setPrecioTitulosComprados(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? gpp.getPrecioTitulosComprados().add(mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).add(mov.getComision())) : gpp.getPrecioTitulosComprados());
				gpp.setPrecioTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase(Cons.VENTA) ? gpp.getPrecioTitulosVendidos().add(mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).subtract(mov.getComision())) : gpp.getPrecioTitulosVendidos());
				gpp.setTitulosActuales(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? gpp.getTitulosActuales().add(mov.getNumeroTitulos()) : gpp.getTitulosActuales().subtract(mov.getNumeroTitulos()));
				gpp.setTitulosComprados(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? gpp.getTitulosComprados().add(mov.getNumeroTitulos()) : gpp.getTitulosComprados());
				gpp.setTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase(Cons.VENTA) ? gpp.getTitulosVendidos().add(mov.getNumeroTitulos()) : gpp.getTitulosVendidos());
				mapGanPerProdPeso.put(getMapKey(mov, producto), gpp);
			}
			else
			{
				GanPerProdPesoDTO gpp = new GanPerProdPesoDTO();
				gpp.setProductoId(mov.getProductoId());
				gpp.setNombre(producto.getNombre());
				gpp.setComercializador(mov.getComercializador());
				gpp.setInstrumento(producto.getInstrumento());
				gpp.setMercado(mov.getMercado());
				gpp.setMoneda(producto.getMoneda());
				gpp.setProveedor(producto.getProveedor());
				gpp.setSubtipoActivo(producto.getSubtipoActivo());
				gpp.setTipoActivo(producto.getTipoActivo());
				gpp.setUsoIngresos(producto.getUsoIngresos());
				gpp.setGananciaPerdida(BigDecimal.ZERO);
				gpp.setGananciaPerdidaPrcnt(BigDecimal.ZERO);
				gpp.setPesoEnCartera(BigDecimal.ZERO);
				gpp.setPrecioTitulosComprados(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).add(mov.getComision()) : BigDecimal.ZERO);
				gpp.setPrecioTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase(Cons.VENTA) ? mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).subtract(mov.getComision()) : BigDecimal.ZERO);
				gpp.setFlujoCaja(BigDecimal.ZERO);
				gpp.setTitulosActuales(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? mov.getNumeroTitulos() : mov.getNumeroTitulos().multiply(new BigDecimal(-1d)));
				gpp.setTitulosComprados(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? mov.getNumeroTitulos() : BigDecimal.ZERO);
				gpp.setTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase(Cons.VENTA) ? mov.getNumeroTitulos() : BigDecimal.ZERO);
				gpp.setValorTitulo(BigDecimal.ZERO);
				gpp.setValorTitulosActuales(BigDecimal.ZERO);
				mapGanPerProdPeso.put(getMapKey(mov, producto), gpp);
			}
		}
		BigDecimal sumValorTitulosActuales = BigDecimal.ZERO;
		for (String mapKey : mapGanPerProdPeso.keySet())
		{
			GanPerProdPesoDTO gpp = mapGanPerProdPeso.get(mapKey);
			ProductoVarDTO productoVar = DatosDAO.select_TB02_PRODUCTOS_VAR(connection, gpp.getProductoId()).get(0);
			gpp.setTer(productoVar.getTer());
			gpp.setValorTitulo(productoVar.getValorTitulo());
			gpp.setValorTitulosActuales(productoVar.getValorTitulo().multiply(gpp.getTitulosActuales()));
			gpp.setFlujoCaja(gpp.getPrecioTitulosVendidos().subtract(gpp.getPrecioTitulosComprados()));
			gpp.setGananciaPerdida(gpp.getPrecioTitulosVendidos().add(gpp.getValorTitulosActuales()).subtract(gpp.getPrecioTitulosComprados()));
			gpp.setGananciaPerdidaPrcnt(gpp.getGananciaPerdida().multiply(new BigDecimal(100d)).divide(gpp.getPrecioTitulosComprados(), 6, RoundingMode.HALF_EVEN));
			sumValorTitulosActuales = sumValorTitulosActuales.add(gpp.getValorTitulosActuales());
			mapGanPerProdPeso.put(mapKey, gpp);
		}
		for (String mapKey : mapGanPerProdPeso.keySet())
		{
			GanPerProdPesoDTO gpp = mapGanPerProdPeso.get(mapKey);
			gpp.setPesoEnCartera(gpp.getValorTitulosActuales().multiply(new BigDecimal(100d)).divide(sumValorTitulosActuales, 6, RoundingMode.HALF_EVEN));
			mapGanPerProdPeso.put(mapKey, gpp);
		}
		List<GanPerProdPesoDTO> listGanPerProdPeso = DatosDAO.select_VW03_GAN_PER_PROD_PESO(connection);
		if (listGanPerProdPeso.size() != mapGanPerProdPeso.size())
		{
			throw new Exception("La dimensión de los elementos es distinta [" + listGanPerProdPeso.size() + "] [" + mapGanPerProdPeso.size() + "]");
		}
		for (GanPerProdPesoDTO ganPerProdPesoSQL : listGanPerProdPeso)
		{
			GanPerProdPesoDTO ganPerProdPesoJAVA = mapGanPerProdPeso.get(getMapKey(ganPerProdPesoSQL));
			if (!ganPerProdPesoSQL.getProductoId().equalsIgnoreCase(ganPerProdPesoJAVA.getProductoId()))
			{
				throw new Exception("Los IDs de producto no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoJAVA.getProductoId() + "]");
			}
			if (!ganPerProdPesoSQL.getNombre().equalsIgnoreCase(ganPerProdPesoJAVA.getNombre()))
			{
				throw new Exception("Los nombres de producto no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getNombre() + "] [" + ganPerProdPesoJAVA.getNombre() + "]");
			}
			if (!ganPerProdPesoSQL.getComercializador().equalsIgnoreCase(ganPerProdPesoJAVA.getComercializador()))
			{
				throw new Exception("Los valores de comercializador no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getComercializador() + "] [" + ganPerProdPesoJAVA.getComercializador() + "]");
			}
			if (!ganPerProdPesoSQL.getMercado().equalsIgnoreCase(ganPerProdPesoJAVA.getMercado()))
			{
				throw new Exception("Los valores de mercado no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getMercado() + "] [" + ganPerProdPesoJAVA.getMercado() + "]");
			}
			if (!ganPerProdPesoSQL.getProveedor().equalsIgnoreCase(ganPerProdPesoJAVA.getProveedor()))
			{
				throw new Exception("Los valores de proveedor no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getProveedor() + "] [" + ganPerProdPesoJAVA.getProveedor() + "]");
			}
			if (!ganPerProdPesoSQL.getInstrumento().equalsIgnoreCase(ganPerProdPesoJAVA.getInstrumento()))
			{
				throw new Exception("Los valores de instrumento no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getInstrumento() + "] [" + ganPerProdPesoJAVA.getInstrumento() + "]");
			}
			if (!ganPerProdPesoSQL.getTipoActivo().equalsIgnoreCase(ganPerProdPesoJAVA.getTipoActivo()))
			{
				throw new Exception("Los valores de tipo activo no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getTipoActivo() + "] [" + ganPerProdPesoJAVA.getTipoActivo() + "]");
			}
			if (!ganPerProdPesoSQL.getSubtipoActivo().equalsIgnoreCase(ganPerProdPesoJAVA.getSubtipoActivo()))
			{
				throw new Exception("Los valores de subtipo activo no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getSubtipoActivo() + "] [" + ganPerProdPesoJAVA.getSubtipoActivo() + "]");
			}
			if (!ganPerProdPesoSQL.getMoneda().equalsIgnoreCase(ganPerProdPesoJAVA.getMoneda()))
			{
				throw new Exception("Los valores de moneda no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getMoneda() + "] [" + ganPerProdPesoJAVA.getMoneda() + "]");
			}
			if (!ganPerProdPesoSQL.getUsoIngresos().equalsIgnoreCase(ganPerProdPesoJAVA.getUsoIngresos()))
			{
				throw new Exception("Los valores de uso ingresos no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getUsoIngresos() + "] [" + ganPerProdPesoJAVA.getUsoIngresos() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getTer(), ganPerProdPesoSQL.getTer()))
			{
				throw new Exception("Los valores del TER no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getTer() + "] [" + ganPerProdPesoJAVA.getTer() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getTitulosComprados(), ganPerProdPesoSQL.getTitulosComprados()))
			{
				throw new Exception("Los titulos comprados no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getTitulosComprados() + "] [" + ganPerProdPesoJAVA.getTitulosComprados() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getTitulosVendidos(), ganPerProdPesoSQL.getTitulosVendidos()))
			{
				throw new Exception("Los titulos vendidos no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getTitulosVendidos() + "] [" + ganPerProdPesoJAVA.getTitulosVendidos() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getTitulosActuales(), ganPerProdPesoSQL.getTitulosActuales()))
			{
				throw new Exception("Los titulos actuales no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getTitulosActuales() + "] [" + ganPerProdPesoJAVA.getTitulosActuales() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getPrecioTitulosComprados(), ganPerProdPesoSQL.getPrecioTitulosComprados()))
			{
				throw new Exception("Los precios de titulos comprados no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getPrecioTitulosComprados() + "] [" + ganPerProdPesoJAVA.getPrecioTitulosComprados() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getPrecioTitulosVendidos(), ganPerProdPesoSQL.getPrecioTitulosVendidos()))
			{
				throw new Exception("Los precios de titulos vendidos no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getPrecioTitulosVendidos() + "] [" + ganPerProdPesoJAVA.getPrecioTitulosVendidos() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getFlujoCaja(), ganPerProdPesoSQL.getFlujoCaja()))
			{
				throw new Exception("Los valores de flujo de caja no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getFlujoCaja() + "] [" + ganPerProdPesoJAVA.getFlujoCaja() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getValorTitulo(), ganPerProdPesoSQL.getValorTitulo()))
			{
				throw new Exception("Los valores del titulo no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getValorTitulo() + "] [" + ganPerProdPesoJAVA.getValorTitulo() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getValorTitulosActuales(), ganPerProdPesoSQL.getValorTitulosActuales()))
			{
				throw new Exception("Los valores de los titulos actuales no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getValorTitulosActuales() + "] [" + ganPerProdPesoJAVA.getValorTitulosActuales() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getGananciaPerdida(), ganPerProdPesoSQL.getGananciaPerdida()))
			{
				throw new Exception("Los valores de ganancia perdida no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getGananciaPerdida() + "] [" + ganPerProdPesoJAVA.getGananciaPerdida() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getGananciaPerdidaPrcnt(), ganPerProdPesoSQL.getGananciaPerdidaPrcnt()))
			{
				throw new Exception("Los valores de ganancia perdida porcentual no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getGananciaPerdidaPrcnt() + "] [" + ganPerProdPesoJAVA.getGananciaPerdidaPrcnt() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getPesoEnCartera(), ganPerProdPesoSQL.getPesoEnCartera()))
			{
				throw new Exception("Los valores peso en cartera no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getPesoEnCartera() + "] [" + ganPerProdPesoJAVA.getPesoEnCartera() + "]");
			}
			else
			{
				LOGGER.info("VW03_GAN_PER_PROD_PESO - OK -> [" + ganPerProdPesoSQL.getProductoId() + "]");
			}
		}
		return mapGanPerProdPeso;
	}

	/**
	 * @param gpp
	 * @param nombreVista
	 * @return
	 * @throws Throwable
	 */
	private static String getMapKey(GanPerProdPesoDTO gpp, String nombreVista) throws Throwable
	{
		String key = "N/A";
		if (nombreVista != null)
		{
			if ("COMERCIALIZADOR".equalsIgnoreCase(nombreVista))
			{
				key = gpp.getComercializador();
			}
			else if ("INSTRUMENTO".equalsIgnoreCase(nombreVista))
			{
				key = gpp.getInstrumento();
			}
			else if ("MERCADO".equalsIgnoreCase(nombreVista))
			{
				key = gpp.getMercado();
			}
			else if ("MONEDA".equalsIgnoreCase(nombreVista))
			{
				key = gpp.getMoneda();
			}
			else if ("PROVEEDOR".equalsIgnoreCase(nombreVista))
			{
				key = gpp.getProveedor();
			}
			else if ("SUBTIPO_ACTIVO".equalsIgnoreCase(nombreVista))
			{
				key = gpp.getSubtipoActivo();
			}
			else if ("TIPO_ACTIVO".equalsIgnoreCase(nombreVista))
			{
				key = gpp.getTipoActivo();
			}
			else if ("USO_INGRESOS".equalsIgnoreCase(nombreVista))
			{
				key = gpp.getUsoIngresos();
			}
			else
			{
				throw new Exception("Nombre de vista inesperado [" + nombreVista + "]");
			}
		}
		return key;
	}

	/**
	 * @param connection
	 * @param nombreVista
	 * @throws Throwable
	 */
	private static void validate_VWF_nombreVista(Connection connection, String nombreVista, Map<String, GanPerProdPesoDTO> mapInput) throws Throwable
	{
		Map<String, GanPerProdPesoDTO> mapVWF = new HashMap<String, GanPerProdPesoDTO>();
		for (String mapGppKey : mapInput.keySet())
		{
			GanPerProdPesoDTO gppInput = mapInput.get(mapGppKey);
			if (mapVWF.containsKey(getMapKey(gppInput, nombreVista)))
			{
				GanPerProdPesoDTO gppVWF = mapVWF.get(getMapKey(gppInput, nombreVista));
				gppVWF.setPrecioTitulosComprados(gppVWF.getPrecioTitulosComprados().add(gppInput.getPrecioTitulosComprados()));
				gppVWF.setPrecioTitulosVendidos(gppVWF.getPrecioTitulosVendidos().add(gppInput.getPrecioTitulosVendidos()));
				gppVWF.setFlujoCaja(gppVWF.getFlujoCaja().add(gppInput.getFlujoCaja()));
				gppVWF.setValorTitulosActuales(gppVWF.getValorTitulosActuales().add(gppInput.getValorTitulosActuales()));
				gppVWF.setGananciaPerdida(gppVWF.getGananciaPerdida().add(gppInput.getGananciaPerdida()));
				gppVWF.setGananciaPerdidaPrcnt(gppVWF.getGananciaPerdida().multiply(new BigDecimal(100d)).divide(gppVWF.getPrecioTitulosComprados(), 6, RoundingMode.HALF_EVEN));
				gppVWF.setPesoEnCartera(gppVWF.getPesoEnCartera().add(gppInput.getPesoEnCartera()));
				gppVWF.setTer(gppVWF.getTer().add(gppInput.getValorTitulosActuales().multiply(gppInput.getTer())));
				mapVWF.put(getMapKey(gppInput, nombreVista), gppVWF);
			}
			else
			{
				GanPerProdPesoDTO gppVWF = new GanPerProdPesoDTO();
				gppVWF.setPrecioTitulosComprados(gppInput.getPrecioTitulosComprados());
				gppVWF.setPrecioTitulosVendidos(gppInput.getPrecioTitulosVendidos());
				gppVWF.setFlujoCaja(gppInput.getFlujoCaja());
				gppVWF.setValorTitulosActuales(gppInput.getValorTitulosActuales());
				gppVWF.setGananciaPerdida(gppInput.getGananciaPerdida());
				gppVWF.setGananciaPerdidaPrcnt(gppVWF.getGananciaPerdida().multiply(new BigDecimal(100d)).divide(gppVWF.getPrecioTitulosComprados(), 6, RoundingMode.HALF_EVEN));
				gppVWF.setPesoEnCartera(gppInput.getPesoEnCartera());
				gppVWF.setTer(gppInput.getValorTitulosActuales().multiply(gppInput.getTer()));
				mapVWF.put(getMapKey(gppInput, nombreVista), gppVWF);
			}
		}
		if (nombreVista == null)
		{
			GanPerProdPesoDTO gppVWF = mapVWF.get("N/A");
			gppVWF.setTer(gppVWF.getTer().divide(gppVWF.getValorTitulosActuales(), 6, RoundingMode.HALF_EVEN));
		}
		List<GanPerProdPesoDTO> listGanPerProdPeso = DatosDAO.select_VWF_nombreVista(connection, nombreVista);
		if (listGanPerProdPeso.size() != mapVWF.size())
		{
			throw new Exception("La dimensión de los elementos es distinta [" + listGanPerProdPeso.size() + "] [" + mapVWF.size() + "]");
		}
		for (GanPerProdPesoDTO ganPerProdPesoSQL : listGanPerProdPeso)
		{
			GanPerProdPesoDTO ganPerProdPesoJAVA = mapVWF.get(getMapKey(ganPerProdPesoSQL, nombreVista));
			if (nombreVista == null)
			{
				if (!similar(ganPerProdPesoJAVA.getTer(), ganPerProdPesoSQL.getTer()))
				{
					throw new Exception("Los valores de TER no coinciden [" + getMapKey(ganPerProdPesoSQL, nombreVista) + "] [" + ganPerProdPesoSQL.getTer() + "] [" + ganPerProdPesoJAVA.getTer() + "]");
				}
			}
			if (!similar(ganPerProdPesoJAVA.getPrecioTitulosComprados(), ganPerProdPesoSQL.getPrecioTitulosComprados()))
			{
				throw new Exception("Los precios de titulos comprados no coinciden [" + getMapKey(ganPerProdPesoSQL, nombreVista) + "] [" + ganPerProdPesoSQL.getPrecioTitulosComprados() + "] [" + ganPerProdPesoJAVA.getPrecioTitulosComprados() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getPrecioTitulosVendidos(), ganPerProdPesoSQL.getPrecioTitulosVendidos()))
			{
				throw new Exception("Los precios de titulos vendidos no coinciden [" + getMapKey(ganPerProdPesoSQL, nombreVista) + "] [" + ganPerProdPesoSQL.getPrecioTitulosVendidos() + "] [" + ganPerProdPesoJAVA.getPrecioTitulosVendidos() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getFlujoCaja(), ganPerProdPesoSQL.getFlujoCaja()))
			{
				throw new Exception("Los valores de flujo de caja no coinciden [" + getMapKey(ganPerProdPesoSQL, nombreVista) + "] [" + ganPerProdPesoSQL.getFlujoCaja() + "] [" + ganPerProdPesoJAVA.getFlujoCaja() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getValorTitulosActuales(), ganPerProdPesoSQL.getValorTitulosActuales()))
			{
				throw new Exception("Los valores de los titulos actuales no coinciden [" + getMapKey(ganPerProdPesoSQL, nombreVista) + "] [" + ganPerProdPesoSQL.getValorTitulosActuales() + "] [" + ganPerProdPesoJAVA.getValorTitulosActuales() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getGananciaPerdida(), ganPerProdPesoSQL.getGananciaPerdida()))
			{
				throw new Exception("Los valores de ganancia perdida no coinciden [" + getMapKey(ganPerProdPesoSQL, nombreVista) + "] [" + ganPerProdPesoSQL.getGananciaPerdida() + "] [" + ganPerProdPesoJAVA.getGananciaPerdida() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getGananciaPerdidaPrcnt(), ganPerProdPesoSQL.getGananciaPerdidaPrcnt()))
			{
				throw new Exception("Los valores de ganancia perdida porcentual no coinciden [" + getMapKey(ganPerProdPesoSQL, nombreVista) + "] [" + ganPerProdPesoSQL.getGananciaPerdidaPrcnt() + "] [" + ganPerProdPesoJAVA.getGananciaPerdidaPrcnt() + "]");
			}
			if (!similar(ganPerProdPesoJAVA.getPesoEnCartera(), ganPerProdPesoSQL.getPesoEnCartera()))
			{
				throw new Exception("Los valores peso en cartera no coinciden [" + getMapKey(ganPerProdPesoSQL, nombreVista) + "] [" + ganPerProdPesoSQL.getPesoEnCartera() + "] [" + ganPerProdPesoJAVA.getPesoEnCartera() + "]");
			}
			else
			{
				LOGGER.info("VWF_" + (nombreVista == null ? "VWF_GAN_PER_PROD_PESO_TOTALES" : nombreVista) + " - OK -> [" + getMapKey(ganPerProdPesoSQL, nombreVista) + "]");
			}
		}
	}

	/**
	 * @param connection
	 * @throws Throwable
	 */
	private static void urlScraping(Connection connection) throws Throwable
	{
		List<ProductoVarDTO> listaProductosVar = DatosDAO.select_TB02_PRODUCTOS_VAR(connection, null);
		for (ProductoVarDTO productoVar : listaProductosVar)
		{
			String urlScraping = productoVar.getUrlScraping();
			Document page = Jsoup.connect(urlScraping).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:80.0) Gecko/27132701 Firefox/78.7").get();
			Elements tablasDatos = page.getElementsByClass("snapshotTextColor snapshotTextFontStyle snapshotTable overviewKeyStatsTable");
			Element tablaDatos = tablasDatos.get(0);
			Elements filas = tablaDatos.getElementsByTag("tr");
			String fechaValor = null;
			String valorTitulo = null;
			String fechaTer = null;
			String ter = null;
			for (Element fila : filas)
			{
				if (fila.text().startsWith("VL "))
				{
					fechaValor = fila.text().substring(3, 13);
					valorTitulo = fila.text().substring(18, fila.text().length());
				}
				else if (fila.text().startsWith("Precio de Cierre "))
				{
					fechaValor = fila.text().substring(17, 27);
					valorTitulo = fila.text().substring(32, fila.text().length());
				}
				else if (fila.text().startsWith("Gastos Corrientes ") && !fila.text().contains("-%"))
				{
					fechaTer = fila.text().substring(18, 28);
					ter = fila.text().substring(29, fila.text().length() - 1);
				}
			}
			productoVar.setFechaValor(new SimpleDateFormat("dd/MM/yyyy").parse(fechaValor));
			productoVar.setValorTitulo(BigDecimal.valueOf(Double.valueOf(NumberFormat.getNumberInstance(Locale.GERMAN).parse(valorTitulo).doubleValue())));
			if (ter != null && fechaTer != null)
			{
				productoVar.setFechaTer(new SimpleDateFormat("dd/MM/yyyy").parse(fechaTer));
				productoVar.setTer(BigDecimal.valueOf(Double.valueOf(NumberFormat.getNumberInstance(Locale.GERMAN).parse(ter).doubleValue())));
			}
			int rowsUpdated = DatosDAO.update_TB02_PRODUCTOS_VAR(connection, productoVar);
			if (rowsUpdated != 1)
			{
				throw new Exception("Se han actualizado [" + rowsUpdated + "] columnas y se esperaba solo una");
			}
			LOGGER.info("Precio actualizado - OK -> [" + productoVar.getProductoId() + "] [" + productoVar.getValorTitulo() + "] [" + productoVar.getFechaValor() + "] [" + productoVar.getTer() + "] [" + productoVar.getFechaTer() + "]");
		}
	}

	/**
	 * @param primero
	 * @param segundo
	 * @return
	 */
	private static boolean similar(BigDecimal primero, BigDecimal segundo)
	{
		double margenError = 0.02d;
		boolean similar = false;
		if (primero.equals(segundo))
		{
			similar = true;
		}
		else
		{
			BigDecimal tercero = primero.subtract(segundo);
			if (tercero.doubleValue() < -margenError || tercero.doubleValue() > margenError)
			{
				similar = false;
			}
			else
			{
				similar = true;
			}
		}
		return similar;
	}

	/**
	 * @return
	 * @throws Throwable
	 */
	private static Connection abrirConexion() throws Throwable
	{
		String dbUrl = DB_PROTOCOL + DB_PATH;
		Connection connection = DriverManager.getConnection(dbUrl);
		connection.setAutoCommit(false);
		return connection;
	}

	/**
	 * @param connection
	 * @throws Throwable
	 */
	private static void confirmarTransaccion(Connection connection) throws Throwable
	{
		connection.commit();
	}

	/**
	 * @param connection
	 */
	private static void deshacerTransaccion(Connection connection)
	{
		try
		{
			connection.rollback();
		}
		catch (Throwable t2)
		{
			LOGGER.error("ERROR", t2);
		}
	}

	/**
	 * @param connection
	 */
	private static void cerrarConexion(Connection connection)
	{
		try
		{
			connection.close();
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
		}
	}

	/**
	 * 
	 */
	private static void cerrarBaseDatos()
	{
		try
		{
			DriverManager.getConnection(DB_PROTOCOL + ";shutdown=true");
		}
		catch (SQLException se)
		{
			if (se.getErrorCode() != 50000 || !"XJ015".equals(se.getSQLState()))
			{
				LOGGER.error("ERROR", se);
			}
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LOGGER.info("INICIO PROCESO");
		Connection connection = null;
		try
		{
			LOGGER.info("Abriendo Conexion");
			connection = abrirConexion();
			urlScraping(connection);
			LOGGER.info("Confirmando Transaccion");
			confirmarTransaccion(connection);
			validate_TB02_MOVIMIENTOS(connection);
			Map<String, GanPerProdPesoDTO> mapGanPerProdPeso = validate_VW03_GAN_PER_PROD_PESO(connection);
			validate_VWF_nombreVista(connection, "INSTRUMENTO", mapGanPerProdPeso);
			validate_VWF_nombreVista(connection, "COMERCIALIZADOR", mapGanPerProdPeso);
			validate_VWF_nombreVista(connection, "MERCADO", mapGanPerProdPeso);
			validate_VWF_nombreVista(connection, "MONEDA", mapGanPerProdPeso);
			validate_VWF_nombreVista(connection, "PROVEEDOR", mapGanPerProdPeso);
			validate_VWF_nombreVista(connection, "SUBTIPO_ACTIVO", mapGanPerProdPeso);
			validate_VWF_nombreVista(connection, "TIPO_ACTIVO", mapGanPerProdPeso);
			validate_VWF_nombreVista(connection, "USO_INGRESOS", mapGanPerProdPeso);
			validate_VWF_nombreVista(connection, null, mapGanPerProdPeso);
			LOGGER.info("Confirmando Transaccion");
			confirmarTransaccion(connection);
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
			LOGGER.info("Deshaciendo Transaccion");
			deshacerTransaccion(connection);
		}
		finally
		{
			LOGGER.info("Cerrando Conexion");
			cerrarConexion(connection);
			LOGGER.info("Cerrando Base Datos");
			cerrarBaseDatos();
			LOGGER.info("FIN PROCESO");
		}
	}

}
