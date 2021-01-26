/**
 * 
 */
package jsm.mdata.seguimiento.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
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
	 * @throws Exception
	 */
	private static void holaMundoDerbyEmbedded() throws Throwable
	{
		LOGGER.info("run()");

		String dbUrl = "jdbc:derby:C:\\_JSM\\SeguimientoCartera\\03_Fuentes\\markets_data\\SeguimientoCartera\\derby\\prueba;create=true";
		Connection conn = DriverManager.getConnection(dbUrl);
		Statement stmt = conn.createStatement();

		// drop table
		// stmt.executeUpdate("Drop Table users");

		// create table
		// stmt.executeUpdate("Create table users (id int primary key, name varchar(30))");
		// LOGGER.info("Tabla users creada");

		// query
		ResultSet rs = stmt.executeQuery("SELECT * FROM users");

		// print out query result
		int secuencial = 1;
		while (rs.next())
		{
			secuencial++;
		}

		// insert 2 rows
		stmt.executeUpdate("insert into users values (" + (secuencial++) + ",'tom')");
		stmt.executeUpdate("insert into users values (" + (secuencial++) + ",'peter')");
		LOGGER.info("Dos filas insertadas");

		// query
		rs = stmt.executeQuery("SELECT * FROM users");

		// print out query result
		while (rs.next())
		{
			LOGGER.info("row {id: " + rs.getInt("id") + ", name: " + rs.getString("name") + "}");
		}

	}

}
