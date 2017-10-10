/**
 * 
 */
package jsm.mdata.ops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Empleado
 *
 */
public class OPTest
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(OPTest.class);

	/**
	 * Conexión a base de datos
	 */
	private static final String DATABASE_DRIVER = "org.postgresql.Driver";
	private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String DATABASE_USERNAME = "Empleado";
	private static final String DATABASE_PASSWORD = "Empleado";

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Connection dbConnection = null;
		try
		{
			LOGGER.info("Abriendo conexión a base de datos");
			dbConnection = getConnection();
			dbConnection.setAutoCommit(false);

			LOGGER.info("num_reg [" + getNumReg(dbConnection) + "]");

			LOGGER.info("Confirmando transacción");
			dbConnection.commit();
		}
		catch (Exception e1)
		{
			LOGGER.error("ERROR", e1);
			if (dbConnection != null)
			{
				try
				{
					LOGGER.info("Deshaciendo transacción");
					dbConnection.rollback();
				}
				catch (Exception e2)
				{
					LOGGER.error("ERROR", e2);
				}
			}
		}
		finally
		{
			if (dbConnection != null)
			{
				try
				{
					LOGGER.info("Cerrando conexión a base de datos");
					dbConnection.close();
				}
				catch (Exception e3)
				{
					LOGGER.error("ERROR", e3);
				}
			}
		}

	}

	/**
	 * @return
	 * @throws Exception
	 */
	private static Connection getConnection() throws Exception
	{
		Class.forName(DATABASE_DRIVER);
		return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
	}

	/**
	 * @param dbConnection
	 * @return
	 * @throws Exception
	 */
	private static Long getNumReg(Connection dbConnection) throws Exception
	{
		PreparedStatement pStatement = null;
		try
		{
			String consultaSQL = "select count(1) as num_reg from public.mercados";
			pStatement = dbConnection.prepareStatement(consultaSQL);
			ResultSet rSet = pStatement.executeQuery();
			Long numReg = null;
			if (rSet.next())
			{
				numReg = rSet.getLong("num_reg");
			}
			return numReg;
		}
		catch (Exception e)
		{
			LOGGER.error("ERROR", e);
			throw e;
		}
		finally
		{
			if (pStatement != null)
			{
				pStatement.close();
			}
		}
	}

}
