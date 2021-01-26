/**
 * 
 */
package jsm.mdata.seguimiento.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			LOGGER.info("INICIO");
			holaMundoDerbyEmbedded();
			LOGGER.info("CORRECTO");
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
		}
		finally
		{
			LOGGER.info("FIN");
		}
	}

	/**
	 * @throws Throwable
	 */
	private static void holaMundoDerbyEmbedded() throws Throwable
	{
		String dbUrl = "jdbc:derby:C:\\_JSM\\SeguimientoCartera\\03_Fuentes\\markets_data\\SeguimientoCartera\\derby\\prueba";
		Connection conn = DriverManager.getConnection(dbUrl);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
		while (rs.next())
		{
			LOGGER.info("row {id: " + rs.getInt("id") + ", name: " + rs.getString("name") + "}");
		}
		try
		{
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
		}
		catch (SQLException se)
		{
			if (se.getErrorCode() != 50000 || !"XJ015".equals(se.getSQLState()))
			{
				throw se;
			}
		}
	}

}
