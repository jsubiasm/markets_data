/**
 * 
 */
package jsm.mdata.selenium.investing.snapshot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Empleado
 *
 */
public class DriverController
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DriverController.class);

	/**
	 * Web Driver
	 */
	public static final String WEB_DRIVER_PROPERTY = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_EXE = "C:\\_PELAYO\\Software\\Selenium\\drivers\\chromedriver.exe";

	/**
	 * Rutas
	 */
	private static final String DOWNLOAD_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\Snapshots\\investing\\download";

	/**
	 * URLs
	 */
	private final static List<String> LISTA_URLS = new ArrayList<String>();
	static
	{
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-euro-stoxx-midcap?cid=46473");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-euro-corporate-bond?cid=46127");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-%E2%82%AC-government-bond-1-3?cid=38600");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-ind.-goods--ser.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-divdax");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-chemicals?cid=46117");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			run();
		}
		catch (Exception e)
		{
			LOGGER.error("ERROR", e);
		}
	}

	/**
	 * @throws Exception
	 * 
	 */
	private static void run() throws Exception
	{
		LOGGER.info("Iniciando driver");
		System.setProperty(WEB_DRIVER_PROPERTY, WEB_DRIVER_EXE);
		WebDriver driver = new ChromeDriver();

		LOGGER.info("Cargando URL inicial para introducir datos proxy");
		driver.get("https://es.investing.com/");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.className("disclaimer")));

		LOGGER.info("Suprimiendo ficheros temporales antiguos");
		FileUtils.cleanDirectory(new File(DOWNLOAD_PATH));

	}

}
