/**
 * 
 */
package jsm.mdata.selenium.investing.screenshot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Empleado
 *
 */
public class DriverControllerSCR extends DriverControllerBase
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DriverControllerSCR.class);

	/**
	 * Web Driver
	 */
	public static final String WEB_DRIVER_PROPERTY = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_EXE = "C:\\_PELAYO\\Software\\Selenium\\drivers\\chromedriver.exe";

	/**
	 * Rutas
	 */
	private static final String DOWNLOAD_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\Screenshots\\investing\\download";

	/**
	 * Formatos
	 */
	private static final String CHARSET = "UTF-8";

	/**
	 * URL Groups
	 */
	private final static List<URLGroup> LISTA_URL_GROUP = new ArrayList<URLGroup>();
	static
	{
		// --
		// -- Alemania
		// --
		List<String> listaURL_01 = new ArrayList<String>();
		listaURL_01.add("https://es.investing.com/stock-screener/?sp=country::17|sector::a|industry::a|equityType::ORD|exchange::4|eq_market_cap::100000000,583950000000|eq_one_year_return::0,63900|yield_us::6.13,286.36%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Alemania", listaURL_01));
		// --
		// -- Austria
		// --
		List<String> listaURL_02 = new ArrayList<String>();
		listaURL_02.add("https://es.investing.com/stock-screener/?sp=country::54|sector::a|industry::a|equityType::ORD|exchange::17|eq_market_cap::100000000,17970000000|eq_one_year_return::0,201.16|yield_us::3,30.9%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Austria", listaURL_02));
		// --
		// -- Bélgica
		// --
		List<String> listaURL_03 = new ArrayList<String>();
		listaURL_03.add("https://es.investing.com/stock-screener/?sp=country::34|sector::a|industry::a|equityType::ORD|exchange::14|eq_market_cap::100000000,159550000000|eq_one_year_return::0,287.68|yield_us::3,21.35%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Bélgica", listaURL_03));
		// --
		// -- España
		// --
		List<String> listaURL_04 = new ArrayList<String>();
		listaURL_04.add("https://es.investing.com/stock-screener/?sp=country::26|sector::a|industry::a|equityType::ORD|exchange::11|eq_market_cap::100000000,88260000000|eq_one_year_return::0,561.5|yield_us::3,24.88%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("España", listaURL_04));
		// --
		// -- USA-Nasdaq
		// --
		List<String> listaURL_05 = new ArrayList<String>();
		listaURL_05.add("https://es.investing.com/stock-screener/?sp=country::5|sector::a|industry::a|equityType::ORD|exchange::2|eq_market_cap::100000000,888100000000|eq_one_year_return::0,16110000|yield_us::3,125.14%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("USA-Nasdaq", listaURL_05));
		// --
		// -- USA-Nueva York
		// --
		List<String> listaURL_06 = new ArrayList<String>();
		listaURL_06.add("https://es.investing.com/stock-screener/?sp=country::5|sector::a|industry::a|equityType::ORD|exchange::1|eq_market_cap::100000000,888100000000|eq_one_year_return::0,16110000|yield_us::3,125.14|ytd::0,254900%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("USA-Nueva York", listaURL_06));
		// --
		// -- Finlandia
		// --
		List<String> listaURL_07 = new ArrayList<String>();
		listaURL_07.add("https://es.investing.com/stock-screener/?sp=country::71|sector::a|industry::a|equityType::ORD|exchange::16|eq_market_cap::100000000,26970000000|eq_one_year_return::0,201.4|yield_us::3,7.17%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Finlandia", listaURL_07));
		// --
		// -- Francia
		// --
		List<String> listaURL_08 = new ArrayList<String>();
		listaURL_08.add("https://es.investing.com/stock-screener/?sp=country::22|sector::a|industry::a|equityType::ORD|exchange::9|eq_market_cap::100000000,124510000000|eq_one_year_return::0,867.74|yield_us::3,69.2|ytd::0,524.52%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Francia", listaURL_08));
		// --
		// -- Holanda
		// --
		List<String> listaURL_09 = new ArrayList<String>();
		listaURL_09.add("https://es.investing.com/stock-screener/?sp=country::21|sector::a|industry::a|equityType::ORD|exchange::7|eq_market_cap::100000000,215550000000|eq_one_year_return::0,319.1|yield_us::3,31.65%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Holanda", listaURL_09));
		// --
		// -- Irlanda
		// --
		List<String> listaURL_10 = new ArrayList<String>();
		listaURL_10.add("https://es.investing.com/stock-screener/?sp=country::33|sector::a|industry::a|equityType::ORD|exchange::58|eq_market_cap::100000000,23060000000|eq_one_year_return::0,120%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Irlanda", listaURL_10));
		// --
		// -- Italia
		// --
		List<String> listaURL_11 = new ArrayList<String>();
		listaURL_11.add("https://es.investing.com/stock-screener/?sp=country::10|sector::a|industry::a|equityType::ORD|exchange::6|eq_market_cap::100000000,51210000000|eq_one_year_return::0,1050|yield_us::3,9.54%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Italia", listaURL_11));
		// --
		// -- Portugal
		// --
		List<String> listaURL_12 = new ArrayList<String>();
		listaURL_12.add("https://es.investing.com/stock-screener/?sp=country::38|sector::a|industry::a|equityType::ORD|exchange::10|eq_market_cap::100000000,11350000000|eq_one_year_return::0,209.09|yield_us::3,18.38%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Portugal", listaURL_12));
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
		System.setProperty(WEB_DRIVER_PROPERTY, WEB_DRIVER_EXE);
		WebDriver driver = new ChromeDriver();

		LOGGER.info("Cargando URL inicial para introducir datos proxy");
		driver.get("https://es.investing.com/");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.className("disclaimer")));

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
					String hrefScreener = listaURL.get(urlsIdx);

					LOGGER.info("Cargando URL [" + hrefScreener + "]");
					driver.get(hrefScreener);
					String tableId = "resultsTable";

					LOGGER.info("Recuperando tabla de elementos [" + tableId + "]");
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id(tableId)));
					WebElement tablaElementos = driver.findElement(By.id(tableId));
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(tablaElementos, By.tagName("tbody")));
					WebElement tableBody = tablaElementos.findElement(By.tagName("tbody"));
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(tableBody, By.tagName("a")));
					List<WebElement> listaLinks = tableBody.findElements(By.tagName("a"));

					LOGGER.info("Guardando enlaces de elementos");
					List<String> listaHrefs = new ArrayList<String>();
					for (WebElement link : listaLinks)
					{
						listaHrefs.add(link.getAttribute("href"));
					}

					LOGGER.info("Procesando elementos");
					int hrefsIdx = 0;
					while (hrefsIdx < listaHrefs.size())
					{
						try
						{
							String hrefElemento = listaHrefs.get(hrefsIdx);
							procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder, CHARSET);
							hrefsIdx++;
						}
						catch (Exception e)
						{
							LOGGER.error("Se ha producido un error", e);
							gestionError(driver);
						}
					}
					urlsIdx++;
				}
				catch (Exception e)
				{
					LOGGER.error("Se ha producido un error", e);
					gestionError(driver);
				}
			}
		}

	}

}
