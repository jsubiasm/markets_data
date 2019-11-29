/**
 * 
 */
package jsm.mdata.selenium.yahoo.screenshot;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Empleado
 *
 */
public abstract class DriverControllerBase
{

	/**
	 * Rutas
	 */
	protected static final String DOWNLOAD_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\Screenshots\\yahoo\\download";

	/**
	 * 
	 */
	protected static final String TF_MENSUAL = "TF_MENSUAL";

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DriverControllerBase.class);

	/**
	 * @param driver
	 * @param hrefElemento
	 * @param downloadPath
	 * @param charset
	 * @throws Exception
	 */
	protected static void procesarElemento(WebDriver driver, String hrefElemento, String downloadPath, String timeFrame, Double minDividendo, Double minCapitalizacion) throws Exception
	{
		LOGGER.info("Cargando URL [" + hrefElemento + "] ");
		driver.get(hrefElemento);
	}

}
