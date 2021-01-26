/**
 * 
 */
package jsm.mdata.seguimiento.main;

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
			run();
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
	private static void run() throws Throwable
	{
		LOGGER.info("run()");
	}

}
