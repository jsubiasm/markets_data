/**
 * 
 */
package jsm.mdata.seguimiento.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsm.mdata.seguimiento.dao.DatosDAO;
import jsm.mdata.seguimiento.dto.EfectivoDTO;
import jsm.mdata.seguimiento.dto.GanPerProdPesoDTO;
import jsm.mdata.seguimiento.dto.MovimientoDTO;
import jsm.mdata.seguimiento.dto.ProductoDTO;
import jsm.mdata.seguimiento.dto.ProductoUrlDTO;
import jsm.mdata.seguimiento.dto.ProductoVarDTO;
import jsm.mdata.seguimiento.dto.TemplateDTO;
import jsm.mdata.seguimiento.scraping.Scraper;
import jsm.mdata.seguimiento.template.HtmlTemplate;

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
	 * 
	 */
	private static final String SCRIPT_SQL = "C:\\_JSM\\SeguimientoCartera\\03_Fuentes\\markets_data\\SeguimientoCartera\\resources\\01.input.seguimiento_cartera.sql";
	private static final String HTML_TEMPLATE = "C:\\_JSM\\SeguimientoCartera\\03_Fuentes\\markets_data\\SeguimientoCartera\\resources\\02.input.seguimiento_cartera.template";
	private static final String SCRIPT_LOG = "C:\\_JSM\\SeguimientoCartera\\03_Fuentes\\markets_data\\SeguimientoCartera\\resources\\03.output.seguimiento_cartera.log";
	private static final String HTML_OUTPUT = "C:\\_JSM\\SeguimientoCartera\\03_Fuentes\\markets_data\\SeguimientoCartera\\resources\\04.output.seguimiento_cartera.html";

	/**
	 * 
	 */
	private static final double MARGEN_ERROR_PORCENTAJE = 3.0;

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
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
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
		catch (SQLException e)
		{
			if (e.getErrorCode() != 50000 || !"XJ015".equals(e.getSQLState()))
			{
				LOGGER.error("ERROR", e);
			}
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
		}
	}

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
			if (mov.getCompraVenta().equalsIgnoreCase("Compra"))
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
			boolean incluirCripto = sufijo.equalsIgnoreCase("CRIPTO") && prod.getTipoActivo().equalsIgnoreCase("Criptomonedas");
			boolean incluirLiquidez = sufijo.equalsIgnoreCase("LIQUIDEZ") && prod.getTipoActivo().equalsIgnoreCase("Liquidez");
			boolean incluirOtros = sufijo.equalsIgnoreCase("OTROS") && prod.getTipoActivo().equalsIgnoreCase("Otros");
			if (incluirGlobal || incluirOro || incluirRF || incluirRV || incluirCripto || incluirLiquidez || incluirOtros)
			{
				if (mapGpp.containsKey(getMapKey(mov, prod)))
				{
					GanPerProdPesoDTO gpp = mapGpp.get(getMapKey(mov, prod));
					gpp.setPrecioTitulosComprados(mov.getCompraVenta().equalsIgnoreCase("Compra") ? gpp.getPrecioTitulosComprados().add(mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).add(mov.getComision())) : gpp.getPrecioTitulosComprados());
					gpp.setPrecioTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase("Venta") ? gpp.getPrecioTitulosVendidos().add(mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).subtract(mov.getComision())) : gpp.getPrecioTitulosVendidos());
					gpp.setTitulosActuales(mov.getCompraVenta().equalsIgnoreCase("Compra") ? gpp.getTitulosActuales().add(mov.getNumeroTitulos()) : gpp.getTitulosActuales().subtract(mov.getNumeroTitulos()));
					gpp.setTitulosComprados(mov.getCompraVenta().equalsIgnoreCase("Compra") ? gpp.getTitulosComprados().add(mov.getNumeroTitulos()) : gpp.getTitulosComprados());
					gpp.setTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase("Venta") ? gpp.getTitulosVendidos().add(mov.getNumeroTitulos()) : gpp.getTitulosVendidos());
					mapGpp.put(getMapKey(mov, prod), gpp);
				}
				else
				{
					GanPerProdPesoDTO gpp = new GanPerProdPesoDTO();
					gpp.setGananciaPerdida(BigDecimal.ZERO);
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
					gpp.setPrecioTitulosComprados(mov.getCompraVenta().equalsIgnoreCase("Compra") ? mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).add(mov.getComision()) : BigDecimal.ZERO);
					gpp.setPrecioTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase("Venta") ? mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).subtract(mov.getComision()) : BigDecimal.ZERO);
					gpp.setTitulosActuales(mov.getCompraVenta().equalsIgnoreCase("Compra") ? mov.getNumeroTitulos() : mov.getNumeroTitulos().multiply(new BigDecimal(-1d)));
					gpp.setTitulosComprados(mov.getCompraVenta().equalsIgnoreCase("Compra") ? mov.getNumeroTitulos() : BigDecimal.ZERO);
					gpp.setTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase("Venta") ? mov.getNumeroTitulos() : BigDecimal.ZERO);
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
			if (gpp.getValorTitulosActuales().compareTo(BigDecimal.ZERO) == 1)
			{
				if (gpp.getFlujoCaja().compareTo(BigDecimal.ZERO) == -1)
				{
					gpp.setGananciaPerdidaPrcnt(gpp.getGananciaPerdida().multiply(new BigDecimal(-100d)).divide(gpp.getFlujoCaja(), 10, RoundingMode.HALF_EVEN));
				}
				else
				{
					gpp.setGananciaPerdidaPrcnt(null);
				}
			}
			else
			{
				gpp.setGananciaPerdidaPrcnt(gpp.getGananciaPerdida().multiply(new BigDecimal(100d)).divide(gpp.getPrecioTitulosComprados(), 10, RoundingMode.HALF_EVEN));
			}
			sumValorTitulosActuales = sumValorTitulosActuales.add(gpp.getValorTitulosActuales());
			mapGpp.put(mapKey, gpp);
		}
		for (String mapKey : mapGpp.keySet())
		{
			GanPerProdPesoDTO gpp = mapGpp.get(mapKey);
			if (sumValorTitulosActuales.compareTo(BigDecimal.ZERO) == 1)
			{
				gpp.setPesoEnCartera(gpp.getValorTitulosActuales().multiply(new BigDecimal(100d)).divide(sumValorTitulosActuales, 10, RoundingMode.HALF_EVEN));
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
			throw new Exception("La dimension de los elementos es distinta [" + listGpp.size() + "] [" + mapGpp.size() + "]");
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
				if (gppVWF.getValorTitulosActuales().compareTo(BigDecimal.ZERO) == 1)
				{
					if (gppVWF.getFlujoCaja().compareTo(BigDecimal.ZERO) == -1)
					{
						gppVWF.setGananciaPerdidaPrcnt(gppVWF.getGananciaPerdida().multiply(new BigDecimal(-100d)).divide(gppVWF.getFlujoCaja(), 10, RoundingMode.HALF_EVEN));
					}
					else
					{
						gppVWF.setGananciaPerdidaPrcnt(null);
					}
				}
				else
				{
					gppVWF.setGananciaPerdidaPrcnt(gppVWF.getGananciaPerdida().multiply(new BigDecimal(100d)).divide(gppVWF.getPrecioTitulosComprados(), 10, RoundingMode.HALF_EVEN));
				}
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
				if (gppVWF.getValorTitulosActuales().compareTo(BigDecimal.ZERO) == 1)
				{
					if (gppVWF.getFlujoCaja().compareTo(BigDecimal.ZERO) == -1)
					{
						gppVWF.setGananciaPerdidaPrcnt(gppVWF.getGananciaPerdida().multiply(new BigDecimal(-100d)).divide(gppVWF.getFlujoCaja(), 10, RoundingMode.HALF_EVEN));
					}
					else
					{
						gppVWF.setGananciaPerdidaPrcnt(null);
					}
				}
				else
				{
					gppVWF.setGananciaPerdidaPrcnt(gppVWF.getGananciaPerdida().multiply(new BigDecimal(100d)).divide(gppVWF.getPrecioTitulosComprados(), 10, RoundingMode.HALF_EVEN));
				}
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
				gppVWF.setTer(gppVWF.getTer().divide(gppVWF.getValorTitulosActuales(), 10, RoundingMode.HALF_EVEN));
			}
			else
			{
				gppVWF.setTer(BigDecimal.ZERO);
			}
		}
		List<GanPerProdPesoDTO> listGpp = DatosDAO.select_VWF_nombreVista(connection, nombreVista);
		if (listGpp.size() != mapGppVWF.size())
		{
			throw new Exception("La dimension de los elementos es distinta [" + listGpp.size() + "] [" + mapGppVWF.size() + "]");
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

	private static void urlScraping(Connection connection) throws Throwable
	{
		List<ProductoVarDTO> listaProductosVar = DatosDAO.select_TB02_PRODUCTOS_VAR(connection, null);
		for (ProductoVarDTO productoVar : listaProductosVar)
		{
			LOGGER.info("--- Actualizando producto [" + productoVar.getProductoId() + "]");
			List<ProductoUrlDTO> listaProductosURL = DatosDAO.select_TB02_PRODUCTOS_URL(connection, productoVar.getProductoId());
			if (listaProductosURL != null && !listaProductosURL.isEmpty())
			{
				if (listaProductosURL.size() < 2)
				{
					throw new Exception("Existen menos de 2 URLs para el producto [" + productoVar.getProductoId() + "]");
				}
				List<ProductoVarDTO> listaProductosVarScraping = new ArrayList<ProductoVarDTO>();
				for (ProductoUrlDTO productoURL : listaProductosURL)
				{
					String urlScraping = productoURL.getUrlScraping();
					LOGGER.info("Scraping [" + urlScraping + "]");
					int numErroresConexion = 0;
					boolean procesadoOK = false;
					while (!procesadoOK)
					{
						try
						{
							if (urlScraping.contains("morningstar.es"))
							{
								listaProductosVarScraping.add(Scraper.getProductoFromMorningstar(productoVar.getProductoId(), urlScraping));
							}
							else if (urlScraping.contains("markets.ft.com"))
							{
								listaProductosVarScraping.add(Scraper.getProductoFromFinancialTimes(productoVar.getProductoId(), urlScraping));
							}
							else if (urlScraping.contains("investing.com"))
							{
								listaProductosVarScraping.add(Scraper.getProductoFromInvesting(productoVar.getProductoId(), urlScraping));
							}
							else if (urlScraping.contains("finance.yahoo.com"))
							{
								listaProductosVarScraping.add(Scraper.getProductoFromYahoo(productoVar.getProductoId(), urlScraping));
							}
							else if (urlScraping.contains("quefondos.com"))
							{
								listaProductosVarScraping.add(Scraper.getProductoFromQuefondos(productoVar.getProductoId(), urlScraping));
							}
							else if (urlScraping.contains("finect.com"))
							{
								listaProductosVarScraping.add(Scraper.getProductoFromFinect(productoVar.getProductoId(), urlScraping));
							}
							else if (urlScraping.contains("dracmametales.com"))
							{
								listaProductosVarScraping.add(Scraper.getProductoFromDracma(productoVar.getProductoId(), urlScraping));
							}
							else if (urlScraping.contains("andorrano-joyeria.com"))
							{
								listaProductosVarScraping.add(Scraper.getProductoFromAndorrano(productoVar.getProductoId(), urlScraping));
							}
							else
							{
								throw new Exception("URL no soportada [" + urlScraping + "]");
							}
							procesadoOK = true;
						}
						catch (Throwable e)
						{
							LOGGER.error("Error procesando URL [" + urlScraping + "]");
							numErroresConexion++;
							if (numErroresConexion >= 5)
							{
								throw e;
							}
							LOGGER.error("Esperamos 3 segundos para reintento...");
							Thread.sleep(3000);
						}
					}
				}
				productoVar = getProductoVarValidado(listaProductosVarScraping, productoVar.getProductoId());
				int rowsUpdated = DatosDAO.update_TB02_PRODUCTOS_VAR(connection, productoVar);
				if (rowsUpdated != 1)
				{
					throw new Exception("Se han actualizado [" + rowsUpdated + "] columnas y se esperaba solo una");
				}
				LOGGER.info("Producto actualizado - OK -> [" + productoVar.getProductoId() + "] [" + productoVar.getValorTitulo() + "] [" + productoVar.getFechaValor() + "] [" + productoVar.getTer() + "] [" + productoVar.getFechaTer() + "]");
			}
		}
	}

	/**
	 * @param listaProductosVarScraping
	 * @return
	 */
	private static ProductoVarDTO getProductoVarValidado(List<ProductoVarDTO> listaProductosVarScraping, String productoId) throws Throwable
	{
		Date fechaValor = null;
		BigDecimal ter = null;
		Date fechaTer = null;
		for (int i = 0; i < listaProductosVarScraping.size(); i++)
		{
			if (fechaValor == null && listaProductosVarScraping.get(i).getFechaValor() != null)
			{
				fechaValor = listaProductosVarScraping.get(i).getFechaValor();
			}
			if (fechaTer == null && listaProductosVarScraping.get(i).getFechaTer() != null)
			{
				fechaTer = listaProductosVarScraping.get(i).getFechaTer();
			}
			if (ter == null && listaProductosVarScraping.get(i).getTer() != null)
			{
				ter = listaProductosVarScraping.get(i).getTer();
			}
			for (int j = i + 1; j < listaProductosVarScraping.size(); j++)
			{
				BigDecimal diferencia = listaProductosVarScraping.get(i).getValorTitulo().subtract(listaProductosVarScraping.get(j).getValorTitulo()).abs();
				BigDecimal umbral = listaProductosVarScraping.get(i).getValorTitulo().multiply(new BigDecimal(MARGEN_ERROR_PORCENTAJE / 100.0));
				if (diferencia.compareTo(umbral) > 0)
				{
					throw new Exception("Los valores " + getStringValores(listaProductosVarScraping) + "para el producto [" + productoId + "] presentan una dispersion superior al [" + MARGEN_ERROR_PORCENTAJE + "] %");
				}
			}
		}
		ProductoVarDTO productoVarValidado = new ProductoVarDTO();
		productoVarValidado.setFechaTer(fechaTer != null ? fechaTer : new Date());
		productoVarValidado.setFechaValor(fechaValor != null ? fechaValor : new Date());
		productoVarValidado.setProductoId(productoId);
		productoVarValidado.setTer(ter != null ? ter : BigDecimal.ZERO);
		productoVarValidado.setValorTitulo(getMediaValores(listaProductosVarScraping));
		LOGGER.info("Producto validado [" + productoVarValidado.getProductoId() + "] [" + getStringValores(listaProductosVarScraping) + "] [" + productoVarValidado.getValorTitulo() + "]");
		return productoVarValidado;
	}

	/**
	 * @param listaProductosVarScraping
	 * @return
	 */
	private static BigDecimal getMediaValores(List<ProductoVarDTO> listaProductosVarScraping)
	{
		BigDecimal sumaValores = BigDecimal.ZERO;
		for (ProductoVarDTO productoVarScraping : listaProductosVarScraping)
		{
			sumaValores = sumaValores.add(productoVarScraping.getValorTitulo());
		}
		BigDecimal mediaValores = sumaValores.divide(new BigDecimal(listaProductosVarScraping.size()), 5, RoundingMode.HALF_EVEN);
		return mediaValores;
	}

	/**
	 * @param listaProductosVarScraping
	 * @return
	 */
	private static String getStringValores(List<ProductoVarDTO> listaProductosVarScraping)
	{
		String stringValores = "";
		for (ProductoVarDTO productoVarScraping : listaProductosVarScraping)
		{
			stringValores = stringValores + "[" + productoVarScraping.getValorTitulo().doubleValue() + "] ";
		}
		return stringValores;
	}

	/**
	 * @param primero
	 * @param segundo
	 * @return
	 */
	private static boolean similar(BigDecimal primero, BigDecimal segundo)
	{
		double margenError = 0.0001d;
		boolean similar = false;
		if ((primero == null && segundo == null) || primero.equals(segundo))
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
	 * @param connection
	 * @throws Throwable
	 */
	private static void generacionInformeHtml(Connection connection) throws Throwable
	{
		TemplateDTO templateDto = new TemplateDTO();
		List<EfectivoDTO> vistaEfectivo = DatosDAO.select_VWF_EFECTIVO(connection);
		templateDto.setTableEfectivo(HtmlTemplate.getTable_VWF_EFECTIVO(vistaEfectivo));
		templateDto.setChartEfectivoData(HtmlTemplate.getChartEfectivoData(vistaEfectivo));
		templateDto.setChartEfectivoBGColor(HtmlTemplate.getChartEfectivoBGColor(vistaEfectivo));
		templateDto.setChartEfectivoLabel(HtmlTemplate.getChartEfectivoLabel(vistaEfectivo));
		List<GanPerProdPesoDTO> vistaGlobalTotales = DatosDAO.select_VWF_nombreVista(connection, null);
		templateDto.setTableGlobalTotales(HtmlTemplate.getTable_VWF_nombreVista(vistaGlobalTotales, null));
		List<GanPerProdPesoDTO> vistaTipoActivo = DatosDAO.select_VWF_nombreVista(connection, "TIPO_ACTIVO");
		templateDto.setTableTipoActivo(HtmlTemplate.getTable_VWF_nombreVista(vistaTipoActivo, "TIPO_ACTIVO"));
		templateDto.setChartTipoActivoData(HtmlTemplate.getChartData(vistaTipoActivo));
		templateDto.setChartTipoActivoBGColor(HtmlTemplate.getChartBGColor(vistaTipoActivo, "TIPO_ACTIVO"));
		templateDto.setChartTipoActivoLabel(HtmlTemplate.getChartLabel(vistaTipoActivo, "TIPO_ACTIVO"));
		templateDto.setChartEfectivoInvertidoData(HtmlTemplate.getChartData(vistaEfectivo, vistaTipoActivo));
		templateDto.setChartEfectivoInvertidoBGColor(HtmlTemplate.getChartBGColor(vistaTipoActivo));
		templateDto.setChartEfectivoInvertidoLabel(HtmlTemplate.getChartLabel(vistaTipoActivo));
		List<GanPerProdPesoDTO> vistaSubtipoActivoOro = DatosDAO.select_VWF_nombreVista(connection, "SUBTIPO_ACTIVO_ORO");
		templateDto.setTableOro(HtmlTemplate.getTable_VWF_nombreVista(vistaSubtipoActivoOro, "SUBTIPO_ACTIVO_ORO"));
		templateDto.setChartOroData(HtmlTemplate.getChartData(vistaSubtipoActivoOro));
		templateDto.setChartOroBGColor(HtmlTemplate.getChartBGColor(vistaSubtipoActivoOro, "SUBTIPO_ACTIVO_ORO"));
		templateDto.setChartOroLabel(HtmlTemplate.getChartLabel(vistaSubtipoActivoOro, "SUBTIPO_ACTIVO_ORO"));
		List<GanPerProdPesoDTO> vistaSubtipoActivoRF = DatosDAO.select_VWF_nombreVista(connection, "SUBTIPO_ACTIVO_RF");
		templateDto.setTableRentaFija(HtmlTemplate.getTable_VWF_nombreVista(vistaSubtipoActivoRF, "SUBTIPO_ACTIVO_RF"));
		templateDto.setChartRentaFijaData(HtmlTemplate.getChartData(vistaSubtipoActivoRF));
		templateDto.setChartRentaFijaBGColor(HtmlTemplate.getChartBGColor(vistaSubtipoActivoRF, "SUBTIPO_ACTIVO_RF"));
		templateDto.setChartRentaFijaLabel(HtmlTemplate.getChartLabel(vistaSubtipoActivoRF, "SUBTIPO_ACTIVO_RF"));
		List<GanPerProdPesoDTO> vistaSubtipoActivoRV = DatosDAO.select_VWF_nombreVista(connection, "SUBTIPO_ACTIVO_RV");
		templateDto.setTableRentaVariable(HtmlTemplate.getTable_VWF_nombreVista(vistaSubtipoActivoRV, "SUBTIPO_ACTIVO_RV"));
		templateDto.setChartRentaVariableData(HtmlTemplate.getChartData(vistaSubtipoActivoRV));
		templateDto.setChartRentaVariableBGColor(HtmlTemplate.getChartBGColor(vistaSubtipoActivoRV, "SUBTIPO_ACTIVO_RV"));
		templateDto.setChartRentaVariableLabel(HtmlTemplate.getChartLabel(vistaSubtipoActivoRV, "SUBTIPO_ACTIVO_RV"));
		List<GanPerProdPesoDTO> vistaSubtipoActivoCripto = DatosDAO.select_VWF_nombreVista(connection, "SUBTIPO_ACTIVO_CRIPTO");
		templateDto.setTableCriptomonedas(HtmlTemplate.getTable_VWF_nombreVista(vistaSubtipoActivoCripto, "SUBTIPO_ACTIVO_CRIPTO"));
		templateDto.setChartCriptomonedasData(HtmlTemplate.getChartData(vistaSubtipoActivoCripto));
		templateDto.setChartCriptomonedasBGColor(HtmlTemplate.getChartBGColor(vistaSubtipoActivoCripto, "SUBTIPO_ACTIVO_CRIPTO"));
		templateDto.setChartCriptomonedasLabel(HtmlTemplate.getChartLabel(vistaSubtipoActivoCripto, "SUBTIPO_ACTIVO_CRIPTO"));
		List<GanPerProdPesoDTO> vistaSubtipoActivoLiquidez = DatosDAO.select_VWF_nombreVista(connection, "SUBTIPO_ACTIVO_LIQUIDEZ");
		templateDto.setTableLiquidez(HtmlTemplate.getTable_VWF_nombreVista(vistaSubtipoActivoLiquidez, "SUBTIPO_ACTIVO_LIQUIDEZ"));
		templateDto.setChartLiquidezData(HtmlTemplate.getChartData(vistaSubtipoActivoLiquidez));
		templateDto.setChartLiquidezBGColor(HtmlTemplate.getChartBGColor(vistaSubtipoActivoLiquidez, "SUBTIPO_ACTIVO_LIQUIDEZ"));
		templateDto.setChartLiquidezLabel(HtmlTemplate.getChartLabel(vistaSubtipoActivoLiquidez, "SUBTIPO_ACTIVO_LIQUIDEZ"));
		List<GanPerProdPesoDTO> vistaSubtipoActivoOtros = DatosDAO.select_VWF_nombreVista(connection, "SUBTIPO_ACTIVO_OTROS");
		templateDto.setTableOtros(HtmlTemplate.getTable_VWF_nombreVista(vistaSubtipoActivoOtros, "SUBTIPO_ACTIVO_OTROS"));
		templateDto.setChartOtrosData(HtmlTemplate.getChartData(vistaSubtipoActivoOtros));
		templateDto.setChartOtrosBGColor(HtmlTemplate.getChartBGColor(vistaSubtipoActivoOtros, "SUBTIPO_ACTIVO_OTROS"));
		templateDto.setChartOtrosLabel(HtmlTemplate.getChartLabel(vistaSubtipoActivoOtros, "SUBTIPO_ACTIVO_OTROS"));
		List<GanPerProdPesoDTO> vistaMoneda = DatosDAO.select_VWF_nombreVista(connection, "MONEDA");
		templateDto.setTableMoneda(HtmlTemplate.getTable_VWF_nombreVista(vistaMoneda, "MONEDA"));
		templateDto.setChartMonedaData(HtmlTemplate.getChartData(vistaMoneda));
		templateDto.setChartMonedaBGColor(HtmlTemplate.getChartBGColor(vistaMoneda, "MONEDA"));
		templateDto.setChartMonedaLabel(HtmlTemplate.getChartLabel(vistaMoneda, "MONEDA"));
		List<GanPerProdPesoDTO> vistaInstrumento = DatosDAO.select_VWF_nombreVista(connection, "INSTRUMENTO");
		templateDto.setTableInstrumento(HtmlTemplate.getTable_VWF_nombreVista(vistaInstrumento, "INSTRUMENTO"));
		templateDto.setChartInstrumentoData(HtmlTemplate.getChartData(vistaInstrumento));
		templateDto.setChartInstrumentoBGColor(HtmlTemplate.getChartBGColor(vistaInstrumento, "INSTRUMENTO"));
		templateDto.setChartInstrumentoLabel(HtmlTemplate.getChartLabel(vistaInstrumento, "INSTRUMENTO"));
		List<GanPerProdPesoDTO> vistaUsoIngresos = DatosDAO.select_VWF_nombreVista(connection, "USO_INGRESOS");
		templateDto.setTableUsoIngresos(HtmlTemplate.getTable_VWF_nombreVista(vistaUsoIngresos, "USO_INGRESOS"));
		templateDto.setChartUsoIngresosData(HtmlTemplate.getChartData(vistaUsoIngresos));
		templateDto.setChartUsoIngresosBGColor(HtmlTemplate.getChartBGColor(vistaUsoIngresos, "USO_INGRESOS"));
		templateDto.setChartUsoIngresosLabel(HtmlTemplate.getChartLabel(vistaUsoIngresos, "USO_INGRESOS"));
		List<GanPerProdPesoDTO> vistaMercado = DatosDAO.select_VWF_nombreVista(connection, "MERCADO");
		templateDto.setTableMercado(HtmlTemplate.getTable_VWF_nombreVista(vistaMercado, "MERCADO"));
		templateDto.setChartMercadoData(HtmlTemplate.getChartData(vistaMercado));
		templateDto.setChartMercadoBGColor(HtmlTemplate.getChartBGColor(vistaMercado, "MERCADO"));
		templateDto.setChartMercadoLabel(HtmlTemplate.getChartLabel(vistaMercado, "MERCADO"));
		List<GanPerProdPesoDTO> vistaComercializador = DatosDAO.select_VWF_nombreVista(connection, "COMERCIALIZADOR");
		templateDto.setTableComercializador(HtmlTemplate.getTable_VWF_nombreVista(vistaComercializador, "COMERCIALIZADOR"));
		templateDto.setChartComercializadorData(HtmlTemplate.getChartData(vistaComercializador));
		templateDto.setChartComercializadorBGColor(HtmlTemplate.getChartBGColor(vistaComercializador, "COMERCIALIZADOR"));
		templateDto.setChartComercializadorLabel(HtmlTemplate.getChartLabel(vistaComercializador, "COMERCIALIZADOR"));
		List<GanPerProdPesoDTO> vistaProveedor = DatosDAO.select_VWF_nombreVista(connection, "PROVEEDOR");
		templateDto.setTableProveedor(HtmlTemplate.getTable_VWF_nombreVista(vistaProveedor, "PROVEEDOR"));
		templateDto.setChartProveedorData(HtmlTemplate.getChartData(vistaProveedor));
		templateDto.setChartProveedorBGColor(HtmlTemplate.getChartBGColor(vistaProveedor, "PROVEEDOR"));
		templateDto.setChartProveedorLabel(HtmlTemplate.getChartLabel(vistaProveedor, "PROVEEDOR"));
		List<GanPerProdPesoDTO> vistaGlobal = DatosDAO.select_VWF_GAN_PER_PROD_PESO_GLOBAL(connection);
		templateDto.setTableGlobal(HtmlTemplate.getTable_VWF_GAN_PER_PROD_PESO_GLOBAL(vistaGlobal));
		templateDto.setMensajeFechaFichero(HtmlTemplate.getMensajeFechaFichero());
		List<String> listLineasInput = FileUtils.readLines(new File(HTML_TEMPLATE), "UTF-8");
		List<String> listLineasOutput = new ArrayList<String>();
		if (listLineasInput != null && !listLineasInput.isEmpty())
		{
			for (String lineaInput : listLineasInput)
			{
				if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.EFECTIVO -->"))
				{
					listLineasOutput.add(templateDto.getTableEfectivo());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.GLOBAL_TOTALES -->"))
				{
					listLineasOutput.add(templateDto.getTableGlobalTotales());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.TIPO_ACTIVO -->"))
				{
					listLineasOutput.add(templateDto.getTableTipoActivo());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.ORO -->"))
				{
					listLineasOutput.add(templateDto.getTableOro());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.RENTA_FIJA -->"))
				{
					listLineasOutput.add(templateDto.getTableRentaFija());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.RENTA_VARIABLE -->"))
				{
					listLineasOutput.add(templateDto.getTableRentaVariable());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.CRIPTOMONEDAS -->"))
				{
					listLineasOutput.add(templateDto.getTableCriptomonedas());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.LIQUIDEZ -->"))
				{
					listLineasOutput.add(templateDto.getTableLiquidez());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.OTROS -->"))
				{
					listLineasOutput.add(templateDto.getTableOtros());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.MONEDA -->"))
				{
					listLineasOutput.add(templateDto.getTableMoneda());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.INSTRUMENTO -->"))
				{
					listLineasOutput.add(templateDto.getTableInstrumento());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.USO_INGRESOS -->"))
				{
					listLineasOutput.add(templateDto.getTableUsoIngresos());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.MERCADO -->"))
				{
					listLineasOutput.add(templateDto.getTableMercado());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.COMERCIALIZADOR -->"))
				{
					listLineasOutput.add(templateDto.getTableComercializador());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.PROVEEDOR -->"))
				{
					listLineasOutput.add(templateDto.getTableProveedor());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.TABLE.GLOBAL -->"))
				{
					listLineasOutput.add(templateDto.getTableGlobal());
				}
				else if (lineaInput != null && lineaInput.contains("<!-- TEMPLATE.MENSAJE.FECHA.FICHERO -->"))
				{
					listLineasOutput.add(templateDto.getMensajeFechaFichero());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.EFECTIVO.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartEfectivoData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.EFECTIVO.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartEfectivoBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.EFECTIVO.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartEfectivoLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.EFECTIVO_INVERTIDO.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartEfectivoInvertidoData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.EFECTIVO_INVERTIDO.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartEfectivoInvertidoBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.EFECTIVO_INVERTIDO.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartEfectivoInvertidoLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.TIPO_ACTIVO.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartTipoActivoData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.TIPO_ACTIVO.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartTipoActivoBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.TIPO_ACTIVO.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartTipoActivoLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.ORO.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartOroData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.ORO.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartOroBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.ORO.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartOroLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.RENTA_FIJA.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartRentaFijaData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.RENTA_FIJA.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartRentaFijaBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.RENTA_FIJA.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartRentaFijaLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.RENTA_VARIABLE.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartRentaVariableData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.RENTA_VARIABLE.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartRentaVariableBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.RENTA_VARIABLE.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartRentaVariableLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.CRIPTOMONEDAS.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartCriptomonedasData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.CRIPTOMONEDAS.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartCriptomonedasBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.CRIPTOMONEDAS.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartCriptomonedasLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.LIQUIDEZ.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartLiquidezData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.LIQUIDEZ.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartLiquidezBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.LIQUIDEZ.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartLiquidezLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.OTROS.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartOtrosData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.OTROS.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartOtrosBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.OTROS.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartOtrosLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.MONEDA.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartMonedaData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.MONEDA.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartMonedaBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.MONEDA.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartMonedaLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.INSTRUMENTO.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartInstrumentoData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.INSTRUMENTO.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartInstrumentoBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.INSTRUMENTO.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartInstrumentoLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.USO_INGRESOS.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartUsoIngresosData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.USO_INGRESOS.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartUsoIngresosBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.USO_INGRESOS.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartUsoIngresosLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.MERCADO.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartMercadoData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.MERCADO.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartMercadoBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.MERCADO.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartMercadoLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.COMERCIALIZADOR.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartComercializadorData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.COMERCIALIZADOR.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartComercializadorBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.COMERCIALIZADOR.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartComercializadorLabel());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.PROVEEDOR.DATA //"))
				{
					listLineasOutput.add(templateDto.getChartProveedorData());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.PROVEEDOR.BGCOLOR //"))
				{
					listLineasOutput.add(templateDto.getChartProveedorBGColor());
				}
				else if (lineaInput != null && lineaInput.contains("// TEMPLATE.CHART.PROVEEDOR.LABEL //"))
				{
					listLineasOutput.add(templateDto.getChartProveedorLabel());
				}
				else
				{
					listLineasOutput.add(lineaInput);
				}
			}
		}
		FileUtils.writeLines(new File(HTML_OUTPUT), "UTF-8", listLineasOutput, false);
	}

	/**
	 * @param connection
	 * @throws Throwable
	 */
	private static void ejecutarScript(Connection connection) throws Throwable
	{
		FileInputStream scriptInputStream = null;
		FileOutputStream logOutputStream = null;
		try
		{
			scriptInputStream = new FileInputStream(new File(SCRIPT_SQL));
			logOutputStream = new FileOutputStream(new File(SCRIPT_LOG));
			int exitCode = org.apache.derby.tools.ij.runScript(connection, scriptInputStream, "UTF-8", logOutputStream, "UTF-8");
			if (exitCode != 0)
			{
				throw new Exception("La ejecucion del script SQL ha terminado con codigo de error [" + exitCode + "]");
			}
		}
		finally
		{
			if (scriptInputStream != null)
			{
				scriptInputStream.close();
			}
			if (logOutputStream != null)
			{
				logOutputStream.close();
			}
		}
	}

	/**
	 * @throws Throwable
	 */
	private static void validarEjecucionScript() throws Throwable
	{
		List<String> listLineasInput = FileUtils.readLines(new File(SCRIPT_LOG), "UTF-8");
		if (listLineasInput != null && !listLineasInput.isEmpty())
		{
			for (String lineaInput : listLineasInput)
			{
				if (lineaInput != null && lineaInput.toUpperCase().contains("ERROR"))
				{
					throw new Exception("Se han producido errores en la ejecucion del script SQL [" + lineaInput + "]");
				}
			}
		}
	}

	/**
	 * @throws Throwable
	 */
	private static void borrarFicherosSalida() throws Throwable
	{
		File outLog = new File(SCRIPT_LOG);
		if (outLog.exists())
		{
			outLog.delete();
		}
		File outHtml = new File(HTML_OUTPUT);
		if (outHtml.exists())
		{
			outHtml.delete();
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
			LOGGER.info("Borrando Ficheros Salida");
			borrarFicherosSalida();
			LOGGER.info("Abriendo Conexion");
			connection = abrirConexion();
			LOGGER.info("Ejecutando Script");
			ejecutarScript(connection);
			LOGGER.info("Validando Ejecucion Script");
			validarEjecucionScript();
			LOGGER.info("Recuperando Datos Productos");
			urlScraping(connection);
			LOGGER.info("Validando Datos");
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
			Map<String, GanPerProdPesoDTO> mapGppCripto = new HashMap<String, GanPerProdPesoDTO>();
			validate_VW03_GAN_PER_PROD_PESO_sufijo(connection, mapGppCripto, "CRIPTO");
			validate_VWF_nombreVista(connection, mapGppCripto, "SUBTIPO_ACTIVO_CRIPTO");
			Map<String, GanPerProdPesoDTO> mapGppLiquidez = new HashMap<String, GanPerProdPesoDTO>();
			validate_VW03_GAN_PER_PROD_PESO_sufijo(connection, mapGppLiquidez, "LIQUIDEZ");
			validate_VWF_nombreVista(connection, mapGppLiquidez, "SUBTIPO_ACTIVO_LIQUIDEZ");
			Map<String, GanPerProdPesoDTO> mapGppOtros = new HashMap<String, GanPerProdPesoDTO>();
			validate_VW03_GAN_PER_PROD_PESO_sufijo(connection, mapGppOtros, "OTROS");
			validate_VWF_nombreVista(connection, mapGppOtros, "SUBTIPO_ACTIVO_OTROS");
			LOGGER.info("Generando Informe");
			generacionInformeHtml(connection);
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
