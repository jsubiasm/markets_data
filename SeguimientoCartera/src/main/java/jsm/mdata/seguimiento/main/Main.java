/**
 * 
 */
package jsm.mdata.seguimiento.main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsm.mdata.seguimiento.constantes.Cons;
import jsm.mdata.seguimiento.dao.DatosDAO;
import jsm.mdata.seguimiento.dto.GanPerProdPesoDTO;
import jsm.mdata.seguimiento.dto.MovimientoDTO;
import jsm.mdata.seguimiento.dto.PrecioDTO;
import jsm.mdata.seguimiento.dto.ProductoDTO;

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
			BigDecimal totalCalculado = movimiento.getNumeroTitulos().multiply(movimiento.getPrecioTitulo()).add(movimiento.getComision()).setScale(2, RoundingMode.HALF_UP);
			if (!totalCalculado.equals(movimiento.getTotal().setScale(2, RoundingMode.HALF_UP)))
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
	 * @throws Throwable
	 */
	private static void validate_VW03_GAN_PER_PROD_PESO(Connection connection) throws Throwable
	{
		Map<String, GanPerProdPesoDTO> mapGanPerProdPeso = new HashMap<String, GanPerProdPesoDTO>();
		List<MovimientoDTO> listaMovimientos = DatosDAO.select_TB02_MOVIMIENTOS(connection);
		for (MovimientoDTO mov : listaMovimientos)
		{
			ProductoDTO producto = DatosDAO.select_TB02_PRODUCTOS(connection, mov.getProductoId());
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
			PrecioDTO precio = DatosDAO.select_TB02_PRECIOS(connection, gpp.getProductoId());
			gpp.setValorTitulo(precio.getValorTitulo());
			gpp.setValorTitulosActuales(precio.getValorTitulo().multiply(gpp.getTitulosActuales()));
			gpp.setGananciaPerdida(gpp.getPrecioTitulosVendidos().add(gpp.getValorTitulosActuales()).subtract(gpp.getPrecioTitulosComprados()));
			gpp.setGananciaPerdidaPrcnt(gpp.getGananciaPerdida().multiply(new BigDecimal(100d)).divide(gpp.getPrecioTitulosComprados(), 2, RoundingMode.HALF_UP));
			sumValorTitulosActuales = sumValorTitulosActuales.add(gpp.getValorTitulosActuales());
			mapGanPerProdPeso.put(mapKey, gpp);
		}
		for (String mapKey : mapGanPerProdPeso.keySet())
		{
			GanPerProdPesoDTO gpp = mapGanPerProdPeso.get(mapKey);
			gpp.setPesoEnCartera(gpp.getValorTitulosActuales().multiply(new BigDecimal(100d)).divide(sumValorTitulosActuales, 2, RoundingMode.HALF_UP));
			mapGanPerProdPeso.put(mapKey, gpp);
		}
		List<GanPerProdPesoDTO> listGanPerProdPeso = DatosDAO.select_VW03_GAN_PER_PROD_PESO(connection);
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
			if (!ganPerProdPesoSQL.getTitulosComprados().setScale(2, RoundingMode.HALF_UP).equals(ganPerProdPesoJAVA.getTitulosComprados().setScale(2, RoundingMode.HALF_UP)))
			{
				throw new Exception("Los titulos comprados no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getTitulosComprados() + "] [" + ganPerProdPesoJAVA.getTitulosComprados() + "]");
			}
			if (!ganPerProdPesoSQL.getTitulosVendidos().setScale(2, RoundingMode.HALF_UP).equals(ganPerProdPesoJAVA.getTitulosVendidos().setScale(2, RoundingMode.HALF_UP)))
			{
				throw new Exception("Los titulos vendidos no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getTitulosVendidos() + "] [" + ganPerProdPesoJAVA.getTitulosVendidos() + "]");
			}
			if (!ganPerProdPesoSQL.getTitulosActuales().setScale(2, RoundingMode.HALF_UP).equals(ganPerProdPesoJAVA.getTitulosActuales().setScale(2, RoundingMode.HALF_UP)))
			{
				throw new Exception("Los titulos actuales no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getTitulosActuales() + "] [" + ganPerProdPesoJAVA.getTitulosActuales() + "]");
			}
			if (!ganPerProdPesoSQL.getPrecioTitulosComprados().setScale(2, RoundingMode.HALF_UP).equals(ganPerProdPesoJAVA.getPrecioTitulosComprados().setScale(2, RoundingMode.HALF_UP)))
			{
				throw new Exception("Los precios de titulos comprados no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getPrecioTitulosComprados() + "] [" + ganPerProdPesoJAVA.getPrecioTitulosComprados() + "]");
			}
			if (!ganPerProdPesoSQL.getPrecioTitulosVendidos().setScale(2, RoundingMode.HALF_UP).equals(ganPerProdPesoJAVA.getPrecioTitulosVendidos().setScale(2, RoundingMode.HALF_UP)))
			{
				throw new Exception("Los precios de titulos vendidos no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getPrecioTitulosVendidos() + "] [" + ganPerProdPesoJAVA.getPrecioTitulosVendidos() + "]");
			}
			if (!ganPerProdPesoSQL.getValorTitulo().setScale(2, RoundingMode.HALF_UP).equals(ganPerProdPesoJAVA.getValorTitulo().setScale(2, RoundingMode.HALF_UP)))
			{
				throw new Exception("Los valores del titulo no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getValorTitulo() + "] [" + ganPerProdPesoJAVA.getValorTitulo() + "]");
			}
			if (!ganPerProdPesoSQL.getValorTitulosActuales().setScale(2, RoundingMode.HALF_UP).equals(ganPerProdPesoJAVA.getValorTitulosActuales().setScale(2, RoundingMode.HALF_UP)))
			{
				throw new Exception("Los valores de los titulos actuales no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getValorTitulosActuales() + "] [" + ganPerProdPesoJAVA.getValorTitulosActuales() + "]");
			}
			if (!ganPerProdPesoSQL.getGananciaPerdida().setScale(2, RoundingMode.HALF_UP).equals(ganPerProdPesoJAVA.getGananciaPerdida().setScale(2, RoundingMode.HALF_UP)))
			{
				throw new Exception("Los valores de ganancia perdida no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getGananciaPerdida() + "] [" + ganPerProdPesoJAVA.getGananciaPerdida() + "]");
			}
			if (!ganPerProdPesoSQL.getGananciaPerdidaPrcnt().setScale(2, RoundingMode.HALF_UP).equals(ganPerProdPesoJAVA.getGananciaPerdidaPrcnt().setScale(2, RoundingMode.HALF_UP)))
			{
				throw new Exception("Los valores de ganancia perdida porcentual no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getGananciaPerdidaPrcnt() + "] [" + ganPerProdPesoJAVA.getGananciaPerdidaPrcnt() + "]");
			}
			if (!ganPerProdPesoSQL.getPesoEnCartera().setScale(2, RoundingMode.HALF_UP).equals(ganPerProdPesoJAVA.getPesoEnCartera().setScale(2, RoundingMode.HALF_UP)))
			{
				throw new Exception("Los valores peso en cartera no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoSQL.getPesoEnCartera() + "] [" + ganPerProdPesoJAVA.getPesoEnCartera() + "]");
			}
			else
			{
				LOGGER.info("VW03_GAN_PER_PROD_PESO - OK -> [" + ganPerProdPesoSQL.getProductoId() + "]");
			}
		}
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
			validate_TB02_MOVIMIENTOS(connection);
			validate_VW03_GAN_PER_PROD_PESO(connection);
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
