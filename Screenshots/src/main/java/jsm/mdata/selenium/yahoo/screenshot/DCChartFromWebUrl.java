/**
 * 
 */
package jsm.mdata.selenium.yahoo.screenshot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsm.mdata.selenium.common.URLGroup;
import jsm.mdata.selenium.common.WebDriverBase;

/**
 * @author Empleado
 *
 */
public class DCChartFromWebUrl extends DriverControllerBase
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DCChartFromWebUrl.class);

	/**
	 * URL Groups
	 */
	private final static List<URLGroup> LISTA_URL_GROUP = new ArrayList<URLGroup>();
	static
	{
		// --
		// -- ACCIONES CON DIVIDENDO
		// --
		List<String> listaURL_09 = new ArrayList<String>();
		listaURL_09.add("https://es.finance.yahoo.com/quote/MMM");
		listaURL_09.add("https://es.finance.yahoo.com/quote/FOUR.L");
		listaURL_09.add("https://es.finance.yahoo.com/quote/AALB.AS");
		listaURL_09.add("https://es.finance.yahoo.com/quote/ABBV");
		listaURL_09.add("https://es.finance.yahoo.com/quote/AGN.AS");
		listaURL_09.add("https://es.finance.yahoo.com/quote/ACN");
		LISTA_URL_GROUP.add(new URLGroup("DIVIDENDO", listaURL_09));
	}

	/**
	 * 
	 */
	private static final Double RPD_MINIMA = 4.0;

	/**
	 * 
	 */
	private static final Double CAP_MINIMA_EN_B = 2.0;

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
		finally
		{
			LOGGER.info("FINALIZADO");
		}
	}

	/**
	 * @throws Exception
	 */
	private static void run() throws Exception
	{
		LOGGER.info("Suprimiendo ficheros temporales antiguos");
		FileUtils.cleanDirectory(new File(DOWNLOAD_PATH));

		LOGGER.info("Iniciando driver");
		System.setProperty(WebDriverBase.WEB_DRIVER_PROPERTY, WebDriverBase.WEB_DRIVER_EXE);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);

		LOGGER.info("Cargando URL inicial para introducir datos proxy");
		driver.get("https://es.finance.yahoo.com/");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("Nav-0-DesktopNav")));

		LOGGER.info("Iniciando proceso");
		for (URLGroup urlGroup : LISTA_URL_GROUP)
		{
			String downloadFolder = urlGroup.getDownloadFolder();
			LOGGER.info("Procesando grupo [" + downloadFolder + "]");
			List<String> listaURL = urlGroup.getListaURL();
			int urlsIdx = 0;
			while (urlsIdx < listaURL.size())
			{
				try
				{
					String hrefElemento = listaURL.get(urlsIdx);
					procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_MENSUAL, TF_MENSUAL, RPD_MINIMA, CAP_MINIMA_EN_B);
					urlsIdx++;
				}
				catch (Exception e)
				{
					LOGGER.error("Se ha producido un error", e);
				}
			}
		}
	}

}
