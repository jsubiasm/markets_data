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
	 * @return
	 * @throws Throwable
	 */
	private static Connection getConnection() throws Throwable
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
				LOGGER.info("TB02_MOVIMIENTOS - OK -> [" + movimiento.getMovimientoId() + "] [" + movimiento.getProductoId() + "] total [" + movimiento.getTotal() + "] totalCalculado [" + totalCalculado + "]");
			}
		}
	}

	/**
	 * @param connection
	 * @throws Throwable
	 */
	private static void validate_VW03_GAN_PER_PROD_PESO(Connection connection) throws Throwable
	{
		Map<String, GanPerProdPesoDTO> mapGanPerProdPeso = new HashMap<String, GanPerProdPesoDTO>();
		List<MovimientoDTO> listaMovimientos = DatosDAO.select_TB02_MOVIMIENTOS(connection);
		BigDecimal sumValorTitulosActuales = BigDecimal.ZERO;
		for (MovimientoDTO mov : listaMovimientos)
		{
			if (mapGanPerProdPeso.containsKey(mov.getProductoId()))
			{
				GanPerProdPesoDTO gpp = mapGanPerProdPeso.get(mov.getProductoId());
				if (!gpp.getProductoId().equalsIgnoreCase(mov.getProductoId()))
				{
					throw new Exception("Los IDs de producto no coinciden [" + gpp.getProductoId() + "] [" + mov.getProductoId() + "]");
				}
				gpp.setPrecioTitulosComprados(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? gpp.getPrecioTitulosComprados().add(mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).add(mov.getComision())) : gpp.getPrecioTitulosComprados());
				gpp.setPrecioTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase(Cons.VENTA) ? gpp.getPrecioTitulosVendidos().add(mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).subtract(mov.getComision())) : gpp.getPrecioTitulosVendidos());
				gpp.setTitulosActuales(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? gpp.getTitulosActuales().add(mov.getNumeroTitulos()) : gpp.getTitulosActuales().subtract(mov.getNumeroTitulos()));
				gpp.setTitulosComprados(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? gpp.getTitulosComprados().add(mov.getNumeroTitulos()) : gpp.getTitulosComprados());
				gpp.setTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase(Cons.VENTA) ? gpp.getTitulosVendidos().add(mov.getNumeroTitulos()) : gpp.getTitulosVendidos());
				mapGanPerProdPeso.put(mov.getProductoId(), gpp);
				sumValorTitulosActuales.add(gpp.getValorTitulosActuales());
			}
			else
			{
				ProductoDTO producto = DatosDAO.select_TB02_PRODUCTOS(connection, mov.getProductoId());
				GanPerProdPesoDTO gpp = new GanPerProdPesoDTO();
				gpp.setGananciaPerdida(BigDecimal.ZERO);
				gpp.setGananciaPerdidaPrcnt(BigDecimal.ZERO);
				gpp.setNombre(producto.getNombre());
				gpp.setPesoEnCartera(BigDecimal.ZERO);
				gpp.setPrecioTitulosComprados(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).add(mov.getComision()) : BigDecimal.ZERO);
				gpp.setPrecioTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase(Cons.VENTA) ? mov.getNumeroTitulos().multiply(mov.getPrecioTitulo()).subtract(mov.getComision()) : BigDecimal.ZERO);
				gpp.setProductoId(mov.getProductoId());
				gpp.setTitulosActuales(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? mov.getNumeroTitulos() : mov.getNumeroTitulos().multiply(new BigDecimal(-1d)));
				gpp.setTitulosComprados(mov.getCompraVenta().equalsIgnoreCase(Cons.COMPRA) ? mov.getNumeroTitulos() : BigDecimal.ZERO);
				gpp.setTitulosVendidos(mov.getCompraVenta().equalsIgnoreCase(Cons.VENTA) ? mov.getNumeroTitulos() : BigDecimal.ZERO);
				gpp.setValorTitulo(BigDecimal.ZERO);
				gpp.setValorTitulosActuales(BigDecimal.ZERO);
				mapGanPerProdPeso.put(mov.getProductoId(), gpp);
				sumValorTitulosActuales.add(gpp.getValorTitulosActuales());
			}
		}
		for (String productoId : mapGanPerProdPeso.keySet())
		{
			PrecioDTO precio = DatosDAO.select_TB02_PRECIOS(connection, productoId);
			GanPerProdPesoDTO gpp = mapGanPerProdPeso.get(productoId);
			if (!gpp.getProductoId().equalsIgnoreCase(productoId))
			{
				throw new Exception("Los IDs de producto no coinciden [" + gpp.getProductoId() + "] [" + productoId + "]");
			}
			gpp.setGananciaPerdida(gpp.getPrecioTitulosVendidos().add(gpp.getValorTitulosActuales().subtract(gpp.getPrecioTitulosComprados())));
			gpp.setGananciaPerdidaPrcnt(gpp.getGananciaPerdida().multiply(new BigDecimal(100d).divide(gpp.getPrecioTitulosComprados(), 2, RoundingMode.HALF_UP)));
			gpp.setPesoEnCartera(gpp.getValorTitulosActuales().multiply(new BigDecimal(100d).divide(sumValorTitulosActuales, 2, RoundingMode.HALF_UP)));
			gpp.setValorTitulo(precio.getValorTitulo());
			gpp.setValorTitulosActuales(precio.getValorTitulo().multiply(gpp.getTitulosActuales()));
			mapGanPerProdPeso.put(productoId, gpp);
		}
		List<GanPerProdPesoDTO> listGanPerProdPeso = DatosDAO.select_VW03_GAN_PER_PROD_PESO(connection);
		for (GanPerProdPesoDTO ganPerProdPesoSQL : listGanPerProdPeso)
		{
			GanPerProdPesoDTO ganPerProdPesoJAVA = mapGanPerProdPeso.get(ganPerProdPesoSQL.getProductoId());
//			getGananciaPerdida()
//			getGananciaPerdidaPrcnt()
//			getNombre()
//			getPesoEnCartera()
//			getPrecioTitulosComprados()
//			getPrecioTitulosVendidos()
//			getProductoId()
//			getTitulosActuales()
//			getTitulosComprados()
//			getTitulosVendidos()
//			getValorTitulo()
//			getValorTitulosActuales()
			if (!ganPerProdPesoSQL.getProductoId().equalsIgnoreCase(ganPerProdPesoJAVA.getProductoId()))
			{
				throw new Exception("Los IDs de producto no coinciden [" + ganPerProdPesoSQL.getProductoId() + "] [" + ganPerProdPesoJAVA.getProductoId() + "]");
			}
			else
			{
				LOGGER.info("VW03_GAN_PER_PROD_PESO - OK -> [" + ganPerProdPesoSQL.getProductoId() + "]");
			}
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
			connection = getConnection();
			validate_TB02_MOVIMIENTOS(connection);
			validate_VW03_GAN_PER_PROD_PESO(connection);
			LOGGER.info("Confirmando Transaccion");
			connection.commit();
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
			LOGGER.info("Deshaciendo Transaccion");
			try
			{
				connection.rollback();
			}
			catch (Throwable t2)
			{
				LOGGER.error("ERROR", t2);
			}
		}
		finally
		{
			LOGGER.info("Cerrando Conexion");
			try
			{
				connection.close();
			}
			catch (Throwable t)
			{
				LOGGER.error("ERROR", t);
			}
			LOGGER.info("Cerrando Base Datos");
			try
			{
				DriverManager.getConnection("jdbc:derby:;shutdown=true");
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
			LOGGER.info("FIN PROCESO");
		}
	}

}
