/**
 * 
 */
package jsm.mdata.seguimiento.main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsm.mdata.seguimiento.dao.DatosDAO;
import jsm.mdata.seguimiento.dto.MovimientoDTO;

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
		List<MovimientoDTO> listaMovimientos = DatosDAO.selectMovimientos(connection);
		for (MovimientoDTO movimiento : listaMovimientos)
		{
			BigDecimal totalCalculado = movimiento.getNumeroTitulos().multiply(movimiento.getPrecioTitulo()).add(movimiento.getComision()).setScale(2, RoundingMode.HALF_UP);
			if (totalCalculado.equals(movimiento.getTotal().setScale(2, RoundingMode.HALF_UP)))
			{
				LOGGER.info("OK -> [" + movimiento.getMovimientoId() + "] [" + movimiento.getProducto() + "] total [" + movimiento.getTotal() + "] totalCalculado [" + totalCalculado + "]");
			}
			else
			{
				throw new Exception("ERROR MOVIMIENTO [" + movimiento.getMovimientoId() + "] [" + movimiento.getProducto() + "] [" + movimiento.getTotal() + "] [" + totalCalculado + "]");
			}
		}
	}

	/**
	 * @param connection
	 * @throws Throwable
	 */
	private static void validate_VW03_GAN_PER_PROD_PESO(Connection connection) throws Throwable
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try
		{
			LOGGER.info("Abriendo Sentencia");
			statement = connection.prepareStatement("SELECT MOVIMIENTO_ID, PRODUCTO_ID, COMPRA_VENTA, FECHA, NUMERO_TITULOS, PRECIO_TITULO, COMISION, TOTAL, COMERCIALIZADOR, MERCADO from TB02_MOVIMIENTOS");
			LOGGER.info("Ejecutando Sentencia");
			resultSet = statement.executeQuery();
			LOGGER.info("Abriendo Cursor");
			while (resultSet.next())
			{

			}
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
		}
		finally
		{
			LOGGER.info("Cerrando Cursor");
			resultSet.close();
			LOGGER.info("Cerrando Sentencia");
			statement.close();
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
