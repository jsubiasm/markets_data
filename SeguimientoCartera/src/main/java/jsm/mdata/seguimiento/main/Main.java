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
		for (MovimientoDTO mov : listaMovimientos)
		{
			BigDecimal totalCalculado = null;
			if (mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA))
			{
				totalCalculado = mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).add(mov.getComision());
			}
			else
			{
				totalCalculado = mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).subtract(mov.getComision());
			}
			if (!similar(mov.getTotal(), totalCalculado))
			{
				throw new Exception("Los totales del movimiento no coinciden [" + mov.getMovimientoId() + "] [" + mov.getProductoId() + "] [" + mov.getTotal() + "] [" + totalCalculado + "]");
			}
			else
			{
				LOGGER.info("TB02_MOVIMIENTOS - OK -> [" + mov.getMovimientoId() + "]");
			}
		}
	}

	/**
	 * @param mov
	 * @param prod
	 * @return
	 */
	private static String getMapKey(MovimientoDTO mov, ProductoDTO prod)
	{
		return mov.getProductoId() + prod.getNombre() + mov.getComercializador() + prod.getInstrumento() + mov.getMercado() + prod.getMoneda() + prod.getProveedor() + prod.getSubtipoActivo() + prod.getTipoActivo() + prod.getUsoIngresos();
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
	 * @param mapGpp
	 * @throws Throwable
	 */
	private static void validate_VW03_GAN_PER_PROD_PESO_sufijo(Connection connection, Map<String, GanPerProdPesoDTO> mapGpp, String sufijo) throws Throwable
	{
		List<MovimientoDTO> listaMovimientos = DatosDAO.select_TB02_MOVIMIENTOS(connection);
		for (MovimientoDTO mov : listaMovimientos)
		{
			ProductoDTO prod = DatosDAO.select_TB02_PRODUCTOS(connection, mov.getProductoId()).get(0);
			boolean incluirGlobal = sufijo.equalsIgnoreCase("GLOBAL");
			boolean incluirOro = sufijo.equalsIgnoreCase("ORO") && prod.getTipoActivo().equalsIgnoreCase("Oro");
			boolean incluirRF = sufijo.equalsIgnoreCase("RF") && prod.getTipoActivo().equalsIgnoreCase("Renta Fija");
			boolean incluirRV = sufijo.equalsIgnoreCase("RV") && prod.getTipoActivo().equalsIgnoreCase("Renta Variable");
			if (incluirGlobal || incluirOro || incluirRF || incluirRV)
			{
				if (mapGpp.containsKey(getMapKey(mov, prod)))
				{
					GanPerProdPesoDTO gpp = mapGpp.get(getMapKey(mov, prod));
					gpp.setPrecioTitulosComprados(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? gpp.getPrecioTitulosComprados().add(mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).add(mov.getComision())) : gpp.getPrecioTitulosComprados());
					gpp.setPrecioTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase(Cons.VENTA) ? gpp.getPrecioTitulosVendidos().add(mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).subtract(mov.getComision())) : gpp.getPrecioTitulosVendidos());
					gpp.setTitulosActuales(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? gpp.getTitulosActuales().add(mov.getNumeroTitulos()) : gpp.getTitulosActuales().subtract(mov.getNumeroTitulos()));
					gpp.setTitulosComprados(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? gpp.getTitulosComprados().add(mov.getNumeroTitulos()) : gpp.getTitulosComprados());
					gpp.setTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase(Cons.VENTA) ? gpp.getTitulosVendidos().add(mov.getNumeroTitulos()) : gpp.getTitulosVendidos());
					mapGpp.put(getMapKey(mov, prod), gpp);
				}
				else
				{
					GanPerProdPesoDTO gpp = new GanPerProdPesoDTO();
					gpp.setGananciaPerdida(BigDecimal.ZERO);
					gpp.setGananciaPerdidaPrcnt(BigDecimal.ZERO);
					gpp.setPesoEnCartera(BigDecimal.ZERO);
					gpp.setValorTitulo(BigDecimal.ZERO);
					gpp.setValorTitulosActuales(BigDecimal.ZERO);
					gpp.setFlujoCaja(BigDecimal.ZERO);
					gpp.setProductoId(mov.getProductoId());
					gpp.setNombre(prod.getNombre());
					gpp.setComercializador(mov.getComercializador());
					gpp.setInstrumento(prod.getInstrumento());
					gpp.setMercado(mov.getMercado());
					gpp.setMoneda(prod.getMoneda());
					gpp.setProveedor(prod.getProveedor());
					gpp.setSubtipoActivo(prod.getSubtipoActivo());
					gpp.setTipoActivo(prod.getTipoActivo());
					gpp.setUsoIngresos(prod.getUsoIngresos());
					gpp.setPrecioTitulosComprados(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).add(mov.getComision()) : BigDecimal.ZERO);
					gpp.setPrecioTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase(Cons.VENTA) ? mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).subtract(mov.getComision()) : BigDecimal.ZERO);
					gpp.setTitulosActuales(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? mov.getNumeroTitulos() : mov.getNumeroTitulos().multiply(new BigDecimal(-1d)));
					gpp.setTitulosComprados(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? mov.getNumeroTitulos() : BigDecimal.ZERO);
					gpp.setTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase(Cons.VENTA) ? mov.getNumeroTitulos() : BigDecimal.ZERO);
					mapGpp.put(getMapKey(mov, prod), gpp);
				}
			}
		}
		BigDecimal sumValorTitulosActuales = BigDecimal.ZERO;
		for (String mapKey : mapGpp.keySet())
		{
			GanPerProdPesoDTO gpp = mapGpp.get(mapKey);
			ProductoVarDTO prodVar = DatosDAO.select_TB02_PRODUCTOS_VAR(connection, gpp.getProductoId()).get(0);
			gpp.setTer(prodVar.getTer());
			gpp.setValorTitulo(prodVar.getValorTitulo());
			gpp.setValorTitulosActuales(prodVar.getValorTitulo().multiply(gpp.getTitulosActuales()));
			gpp.setFlujoCaja(gpp.getPrecioTitulosVendidos().subtract(gpp.getPrecioTitulosComprados()));
			gpp.setGananciaPerdida(gpp.getPrecioTitulosVendidos().add(gpp.getValorTitulosActuales()).subtract(gpp.getPrecioTitulosComprados()));
			gpp.setGananciaPerdidaPrcnt(gpp.getGananciaPerdida().multiply(new BigDecimal(100d)).divide(gpp.getPrecioTitulosComprados(), 6, RoundingMode.HALF_EVEN));
			sumValorTitulosActuales = sumValorTitulosActuales.add(gpp.getValorTitulosActuales());
			mapGpp.put(mapKey, gpp);
		}
		for (String mapKey : mapGpp.keySet())
		{
			GanPerProdPesoDTO gpp = mapGpp.get(mapKey);
			if (sumValorTitulosActuales.compareTo(BigDecimal.ZERO) == 1)
			{
				gpp.setPesoEnCartera(gpp.getValorTitulosActuales().multiply(new BigDecimal(100d)).divide(sumValorTitulosActuales, 6, RoundingMode.HALF_EVEN));
			}
			else
			{
				gpp.setPesoEnCartera(BigDecimal.ZERO);
			}
			mapGpp.put(mapKey, gpp);
		}
		List<GanPerProdPesoDTO> listGpp = DatosDAO.select_VW03_GAN_PER_PROD_PESO_sufijo(connection, sufijo);
		if (listGpp.size() != mapGpp.size())
		{
			throw new Exception("La dimensión de los elementos es distinta [" + listGpp.size() + "] [" + mapGpp.size() + "]");
		}
		for (GanPerProdPesoDTO gppSQL : listGpp)
		{
			GanPerProdPesoDTO gppJava = mapGpp.get(getMapKey(gppSQL));
			if (!gppSQL.getProductoId().equalsIgnoreCase(gppJava.getProductoId()))
			{
				throw new Exception("Los IDs de producto no coinciden [" + gppSQL.getProductoId() + "] [" + gppJava.getProductoId() + "]");
			}
			if (!gppSQL.getNombre().equalsIgnoreCase(gppJava.getNombre()))
			{
				throw new Exception("Los nombres de producto no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getNombre() + "] [" + gppJava.getNombre() + "]");
			}
			if (!gppSQL.getComercializador().equalsIgnoreCase(gppJava.getComercializador()))
			{
				throw new Exception("Los valores de comercializador no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getComercializador() + "] [" + gppJava.getComercializador() + "]");
			}
			if (!gppSQL.getMercado().equalsIgnoreCase(gppJava.getMercado()))
			{
				throw new Exception("Los valores de mercado no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getMercado() + "] [" + gppJava.getMercado() + "]");
			}
			if (!gppSQL.getProveedor().equalsIgnoreCase(gppJava.getProveedor()))
			{
				throw new Exception("Los valores de proveedor no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getProveedor() + "] [" + gppJava.getProveedor() + "]");
			}
			if (!gppSQL.getInstrumento().equalsIgnoreCase(gppJava.getInstrumento()))
			{
				throw new Exception("Los valores de instrumento no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getInstrumento() + "] [" + gppJava.getInstrumento() + "]");
			}
			if (!gppSQL.getTipoActivo().equalsIgnoreCase(gppJava.getTipoActivo()))
			{
				throw new Exception("Los valores de tipo activo no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getTipoActivo() + "] [" + gppJava.getTipoActivo() + "]");
			}
			if (!gppSQL.getSubtipoActivo().equalsIgnoreCase(gppJava.getSubtipoActivo()))
			{
				throw new Exception("Los valores de subtipo activo no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getSubtipoActivo() + "] [" + gppJava.getSubtipoActivo() + "]");
			}
			if (!gppSQL.getMoneda().equalsIgnoreCase(gppJava.getMoneda()))
			{
				throw new Exception("Los valores de moneda no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getMoneda() + "] [" + gppJava.getMoneda() + "]");
			}
			if (!gppSQL.getUsoIngresos().equalsIgnoreCase(gppJava.getUsoIngresos()))
			{
				throw new Exception("Los valores de uso ingresos no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getUsoIngresos() + "] [" + gppJava.getUsoIngresos() + "]");
			}
			if (!similar(gppJava.getTer(), gppSQL.getTer()))
			{
				throw new Exception("Los valores del TER no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getTer() + "] [" + gppJava.getTer() + "]");
			}
			if (!similar(gppJava.getTitulosComprados(), gppSQL.getTitulosComprados()))
			{
				throw new Exception("Los titulos comprados no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getTitulosComprados() + "] [" + gppJava.getTitulosComprados() + "]");
			}
			if (!similar(gppJava.getTitulosVendidos(), gppSQL.getTitulosVendidos()))
			{
				throw new Exception("Los titulos vendidos no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getTitulosVendidos() + "] [" + gppJava.getTitulosVendidos() + "]");
			}
			if (!similar(gppJava.getTitulosActuales(), gppSQL.getTitulosActuales()))
			{
				throw new Exception("Los titulos actuales no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getTitulosActuales() + "] [" + gppJava.getTitulosActuales() + "]");
			}
			if (!similar(gppJava.getPrecioTitulosComprados(), gppSQL.getPrecioTitulosComprados()))
			{
				throw new Exception("Los precios de titulos comprados no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getPrecioTitulosComprados() + "] [" + gppJava.getPrecioTitulosComprados() + "]");
			}
			if (!similar(gppJava.getPrecioTitulosVendidos(), gppSQL.getPrecioTitulosVendidos()))
			{
				throw new Exception("Los precios de titulos vendidos no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getPrecioTitulosVendidos() + "] [" + gppJava.getPrecioTitulosVendidos() + "]");
			}
			if (!similar(gppJava.getFlujoCaja(), gppSQL.getFlujoCaja()))
			{
				throw new Exception("Los valores de flujo de caja no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getFlujoCaja() + "] [" + gppJava.getFlujoCaja() + "]");
			}
			if (!similar(gppJava.getValorTitulo(), gppSQL.getValorTitulo()))
			{
				throw new Exception("Los valores del titulo no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getValorTitulo() + "] [" + gppJava.getValorTitulo() + "]");
			}
			if (!similar(gppJava.getValorTitulosActuales(), gppSQL.getValorTitulosActuales()))
			{
				throw new Exception("Los valores de los titulos actuales no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getValorTitulosActuales() + "] [" + gppJava.getValorTitulosActuales() + "]");
			}
			if (!similar(gppJava.getGananciaPerdida(), gppSQL.getGananciaPerdida()))
			{
				throw new Exception("Los valores de ganancia perdida no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getGananciaPerdida() + "] [" + gppJava.getGananciaPerdida() + "]");
			}
			if (!similar(gppJava.getGananciaPerdidaPrcnt(), gppSQL.getGananciaPerdidaPrcnt()))
			{
				throw new Exception("Los valores de ganancia perdida porcentual no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getGananciaPerdidaPrcnt() + "] [" + gppJava.getGananciaPerdidaPrcnt() + "]");
			}
			if (!similar(gppJava.getPesoEnCartera(), gppSQL.getPesoEnCartera()))
			{
				throw new Exception("Los valores peso en cartera no coinciden [" + gppSQL.getProductoId() + "] [" + gppSQL.getPesoEnCartera() + "] [" + gppJava.getPesoEnCartera() + "]");
			}
			else
			{
				LOGGER.info("VW03_GAN_PER_PROD_PESO_" + sufijo + " - OK -> [" + gppSQL.getProductoId() + "]");
			}
		}
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
			else if (nombreVista.startsWith("SUBTIPO_ACTIVO"))
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
	private static void validate_VWF_nombreVista(Connection connection, Map<String, GanPerProdPesoDTO> mapGppIn, String nombreVista) throws Throwable
	{
		Map<String, GanPerProdPesoDTO> mapGppVWF = new HashMap<String, GanPerProdPesoDTO>();
		for (String mapGppInKey : mapGppIn.keySet())
		{
			GanPerProdPesoDTO gppIn = mapGppIn.get(mapGppInKey);
			if (mapGppVWF.containsKey(getMapKey(gppIn, nombreVista)))
			{
				GanPerProdPesoDTO gppVWF = mapGppVWF.get(getMapKey(gppIn, nombreVista));
				gppVWF.setPrecioTitulosComprados(gppVWF.getPrecioTitulosComprados().add(gppIn.getPrecioTitulosComprados()));
				gppVWF.setPrecioTitulosVendidos(gppVWF.getPrecioTitulosVendidos().add(gppIn.getPrecioTitulosVendidos()));
				gppVWF.setFlujoCaja(gppVWF.getFlujoCaja().add(gppIn.getFlujoCaja()));
				gppVWF.setValorTitulosActuales(gppVWF.getValorTitulosActuales().add(gppIn.getValorTitulosActuales()));
				gppVWF.setGananciaPerdida(gppVWF.getGananciaPerdida().add(gppIn.getGananciaPerdida()));
				gppVWF.setGananciaPerdidaPrcnt(gppVWF.getGananciaPerdida().multiply(new BigDecimal(100d)).divide(gppVWF.getPrecioTitulosComprados(), 6, RoundingMode.HALF_EVEN));
				gppVWF.setPesoEnCartera(gppVWF.getPesoEnCartera().add(gppIn.getPesoEnCartera()));
				gppVWF.setTer(gppVWF.getTer().add(gppIn.getValorTitulosActuales().multiply(gppIn.getTer())));
				mapGppVWF.put(getMapKey(gppIn, nombreVista), gppVWF);
			}
			else
			{
				GanPerProdPesoDTO gppVWF = new GanPerProdPesoDTO();
				gppVWF.setPrecioTitulosComprados(gppIn.getPrecioTitulosComprados());
				gppVWF.setPrecioTitulosVendidos(gppIn.getPrecioTitulosVendidos());
				gppVWF.setFlujoCaja(gppIn.getFlujoCaja());
				gppVWF.setValorTitulosActuales(gppIn.getValorTitulosActuales());
				gppVWF.setGananciaPerdida(gppIn.getGananciaPerdida());
				gppVWF.setGananciaPerdidaPrcnt(gppVWF.getGananciaPerdida().multiply(new BigDecimal(100d)).divide(gppVWF.getPrecioTitulosComprados(), 6, RoundingMode.HALF_EVEN));
				gppVWF.setPesoEnCartera(gppIn.getPesoEnCartera());
				gppVWF.setTer(gppIn.getValorTitulosActuales().multiply(gppIn.getTer()));
				mapGppVWF.put(getMapKey(gppIn, nombreVista), gppVWF);
			}
		}
		if (nombreVista == null)
		{
			GanPerProdPesoDTO gppVWF = mapGppVWF.get("N/A");
			if (gppVWF.getValorTitulosActuales().compareTo(BigDecimal.ZERO) == 1)
			{
				gppVWF.setTer(gppVWF.getTer().divide(gppVWF.getValorTitulosActuales(), 6, RoundingMode.HALF_EVEN));
			}
			else
			{
				gppVWF.setTer(BigDecimal.ZERO);
			}
		}
		List<GanPerProdPesoDTO> listGpp = DatosDAO.select_VWF_nombreVista(connection, nombreVista);
		if (listGpp.size() != mapGppVWF.size())
		{
			throw new Exception("La dimensión de los elementos es distinta [" + listGpp.size() + "] [" + mapGppVWF.size() + "]");
		}
		for (GanPerProdPesoDTO gppSQL : listGpp)
		{
			GanPerProdPesoDTO gppJAVA = mapGppVWF.get(getMapKey(gppSQL, nombreVista));
			if (nombreVista == null)
			{
				if (!similar(gppJAVA.getTer(), gppSQL.getTer()))
				{
					throw new Exception("Los valores de TER no coinciden [" + getMapKey(gppSQL, nombreVista) + "] [" + gppSQL.getTer() + "] [" + gppJAVA.getTer() + "]");
				}
			}
			if (!similar(gppJAVA.getPrecioTitulosComprados(), gppSQL.getPrecioTitulosComprados()))
			{
				throw new Exception("Los precios de titulos comprados no coinciden [" + getMapKey(gppSQL, nombreVista) + "] [" + gppSQL.getPrecioTitulosComprados() + "] [" + gppJAVA.getPrecioTitulosComprados() + "]");
			}
			if (!similar(gppJAVA.getPrecioTitulosVendidos(), gppSQL.getPrecioTitulosVendidos()))
			{
				throw new Exception("Los precios de titulos vendidos no coinciden [" + getMapKey(gppSQL, nombreVista) + "] [" + gppSQL.getPrecioTitulosVendidos() + "] [" + gppJAVA.getPrecioTitulosVendidos() + "]");
			}
			if (!similar(gppJAVA.getFlujoCaja(), gppSQL.getFlujoCaja()))
			{
				throw new Exception("Los valores de flujo de caja no coinciden [" + getMapKey(gppSQL, nombreVista) + "] [" + gppSQL.getFlujoCaja() + "] [" + gppJAVA.getFlujoCaja() + "]");
			}
			if (!similar(gppJAVA.getValorTitulosActuales(), gppSQL.getValorTitulosActuales()))
			{
				throw new Exception("Los valores de los titulos actuales no coinciden [" + getMapKey(gppSQL, nombreVista) + "] [" + gppSQL.getValorTitulosActuales() + "] [" + gppJAVA.getValorTitulosActuales() + "]");
			}
			if (!similar(gppJAVA.getGananciaPerdida(), gppSQL.getGananciaPerdida()))
			{
				throw new Exception("Los valores de ganancia perdida no coinciden [" + getMapKey(gppSQL, nombreVista) + "] [" + gppSQL.getGananciaPerdida() + "] [" + gppJAVA.getGananciaPerdida() + "]");
			}
			if (!similar(gppJAVA.getGananciaPerdidaPrcnt(), gppSQL.getGananciaPerdidaPrcnt()))
			{
				throw new Exception("Los valores de ganancia perdida porcentual no coinciden [" + getMapKey(gppSQL, nombreVista) + "] [" + gppSQL.getGananciaPerdidaPrcnt() + "] [" + gppJAVA.getGananciaPerdidaPrcnt() + "]");
			}
			if (!similar(gppJAVA.getPesoEnCartera(), gppSQL.getPesoEnCartera()))
			{
				throw new Exception("Los valores peso en cartera no coinciden [" + getMapKey(gppSQL, nombreVista) + "] [" + gppSQL.getPesoEnCartera() + "] [" + gppJAVA.getPesoEnCartera() + "]");
			}
			else
			{
				LOGGER.info("VWF_" + (nombreVista == null ? "VWF_GAN_PER_PROD_PESO_GLOBAL_TOTALES" : nombreVista) + " - OK -> [" + getMapKey(gppSQL, nombreVista) + "]");
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
			BigDecimal diferencia = primero.subtract(segundo);
			similar = diferencia.doubleValue() >= -margenError && diferencia.doubleValue() <= margenError;
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
			Map<String, GanPerProdPesoDTO> mapGpp = new HashMap<String, GanPerProdPesoDTO>();
			validate_VW03_GAN_PER_PROD_PESO_sufijo(connection, mapGpp, "GLOBAL");
			validate_VWF_nombreVista(connection, mapGpp, "INSTRUMENTO");
			validate_VWF_nombreVista(connection, mapGpp, "COMERCIALIZADOR");
			validate_VWF_nombreVista(connection, mapGpp, "MERCADO");
			validate_VWF_nombreVista(connection, mapGpp, "MONEDA");
			validate_VWF_nombreVista(connection, mapGpp, "PROVEEDOR");
			validate_VWF_nombreVista(connection, mapGpp, "TIPO_ACTIVO");
			validate_VWF_nombreVista(connection, mapGpp, "SUBTIPO_ACTIVO_GLOBAL");
			validate_VWF_nombreVista(connection, mapGpp, "USO_INGRESOS");
			validate_VWF_nombreVista(connection, mapGpp, null);
			Map<String, GanPerProdPesoDTO> mapGppOro = new HashMap<String, GanPerProdPesoDTO>();
			validate_VW03_GAN_PER_PROD_PESO_sufijo(connection, mapGppOro, "ORO");
			validate_VWF_nombreVista(connection, mapGppOro, "SUBTIPO_ACTIVO_ORO");
			Map<String, GanPerProdPesoDTO> mapGppRF = new HashMap<String, GanPerProdPesoDTO>();
			validate_VW03_GAN_PER_PROD_PESO_sufijo(connection, mapGppRF, "RF");
			validate_VWF_nombreVista(connection, mapGppRF, "SUBTIPO_ACTIVO_RF");
			Map<String, GanPerProdPesoDTO> mapGppRV = new HashMap<String, GanPerProdPesoDTO>();
			validate_VW03_GAN_PER_PROD_PESO_sufijo(connection, mapGppRV, "RV");
			validate_VWF_nombreVista(connection, mapGppRV, "SUBTIPO_ACTIVO_RV");
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
