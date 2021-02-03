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
			LOGGER.info("Abriendo Conexion");
			connection = getConnection();
			validateMovimientos(connection);
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
				StringBuilder movimientoLog = new StringBuilder();
				movimientoLog.append(" movimientoId [" + movimientoId + "]");
				movimientoLog.append(" produtoId [" + produtoId + "]");
				movimientoLog.append(" compraVenta [" + compraVenta + "]");
				movimientoLog.append(" fecha [" + fecha + "]");
				movimientoLog.append(" numeroTitulos [" + numeroTitulos + "]");
				movimientoLog.append(" precioTitulo [" + precioTitulo + "]");
				movimientoLog.append(" comision [" + comision + "]");
				movimientoLog.append(" comercializador [" + comercializador + "]");
				movimientoLog.append(" mercado [" + mercado + "]");
				BigDecimal totalCalculado = numeroTitulos.multiply(precioTitulo).add(comision).setScale(2, RoundingMode.HALF_UP);
				if (totalCalculado.equals(total.setScale(2, RoundingMode.HALF_UP)))
				{
					LOGGER.info("Movimiento validado: totalCalculado [" + totalCalculado + "] total [" + total + "]" + movimientoLog.toString());
				}
				else
				{
					throw new Exception("ERROR MOVIMIENTO [" + totalCalculado + "] [" + total + "]" + movimientoLog.toString());
				}
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

}
