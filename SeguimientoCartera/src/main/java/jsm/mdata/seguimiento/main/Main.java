/**
 * 
 */
package jsm.mdata.seguimiento.main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * @param args
	 */
	public static void main(String[] args)
	{
		LOGGER.info("INICIO PROCESO");
		Connection connection = null;
		try
		{
			LOGGER.info("Abriendo connection");
			connection = getConnection();
			validateMovimientos(connection);
			validateMovimientos(connection);
			LOGGER.info("Confirmando transaccion");
			connection.commit();
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
			LOGGER.info("Deshaciendo transaccion");
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
			LOGGER.info("Cerrando connection");
			try
			{
				connection.close();
			}
			catch (Throwable t)
			{
				LOGGER.error("ERROR", t);
			}
			LOGGER.info("Cerrando DB");
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
	private static void validateMovimientos(Connection connection) throws Throwable
	{
		Statement statement = null;
		ResultSet resultSet = null;
		try
		{
			LOGGER.info("Abriendo statement");
			statement = connection.createStatement();
			LOGGER.info("Abriendo resultSet");
			resultSet = statement.executeQuery("SELECT MOVIMIENTO_ID, PRODUCTO_ID, COMPRA_VENTA, FECHA, NUMERO_TITULOS, PRECIO_TITULO, COMISION, TOTAL, COMERCIALIZADOR, MERCADO from MASTER_MOVIMIENTOS");
			while (resultSet.next())
			{
				Integer movimientoId = resultSet.getInt("MOVIMIENTO_ID");
				String produtoId = resultSet.getString("PRODUCTO_ID");
				String compraVenta = resultSet.getString("COMPRA_VENTA");
				Date fecha = resultSet.getDate("FECHA");
				BigDecimal numeroTitulos = resultSet.getBigDecimal("NUMERO_TITULOS");
				BigDecimal precioTitulo = resultSet.getBigDecimal("PRECIO_TITULO");
				BigDecimal comision = resultSet.getBigDecimal("COMISION");
				BigDecimal total = resultSet.getBigDecimal("TOTAL");
				String comercializador = resultSet.getString("COMERCIALIZADOR");
				String mercado = resultSet.getString("MERCADO");
				LOGGER.info("movimientoId [" + movimientoId + "]");
				LOGGER.info("produtoId [" + produtoId + "]");
				LOGGER.info("compraVenta [" + compraVenta + "]");
				LOGGER.info("fecha [" + fecha + "]");
				LOGGER.info("numeroTitulos [" + numeroTitulos + "]");
				LOGGER.info("precioTitulo [" + precioTitulo + "]");
				LOGGER.info("comision [" + comision + "]");
				LOGGER.info("total [" + total + "]");
				LOGGER.info("comercializador [" + comercializador + "]");
				LOGGER.info("mercado [" + mercado + "]");
				BigDecimal totalCalculado = numeroTitulos.multiply(precioTitulo).add(comision).setScale(2, RoundingMode.HALF_UP);
				if (totalCalculado.equals(total.setScale(2, RoundingMode.HALF_UP)))
				{
					LOGGER.info("----------MOVIMIENTO VALIDADO----------");
				}
				else
				{
					throw new Exception("MOVIMIENTO NO VALIDADO [" + totalCalculado + "] [" + total + "]");
				}
			}
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
		}
		finally
		{
			LOGGER.info("Cerrando resultSet");
			resultSet.close();
			LOGGER.info("Cerrando statement");
			statement.close();
		}
	}

}
