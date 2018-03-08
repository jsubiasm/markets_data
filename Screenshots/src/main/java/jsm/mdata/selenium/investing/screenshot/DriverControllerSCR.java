/**
 * 
 */
package jsm.mdata.selenium.investing.screenshot;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
public class DriverControllerSCR
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
		listaURL_01.add("https://es.investing.com/stock-screener/?sp=country::17|sector::a|industry::a|equityType::ORD|exchange::4|eq_market_cap::100000000,583950000000|yield_us::3,286.36|eq_one_year_return::0,70300|ytd::0,1900%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Alemania", listaURL_01));
		// --
		// -- Francia
		// --
		List<String> listaURL_02 = new ArrayList<String>();
		listaURL_02.add("https://es.investing.com/stock-screener/?sp=country::22|sector::a|industry::a|equityType::ORD|exchange::9|eq_market_cap::100000000,122360000000|yield_us::3,68.73|eq_one_year_return::0,948.39|ytd::0,524.52%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Francia", listaURL_02));
		// --
		// -- Italia
		// --
		List<String> listaURL_03 = new ArrayList<String>();
		listaURL_03.add("https://es.investing.com/stock-screener/?sp=country::10|sector::a|industry::a|equityType::ORD|exchange::6|eq_market_cap::100000000,50380000000|yield_us::3,9.54|eq_one_year_return::0,977.78|ytd::0,309.38%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Italia", listaURL_03));
		// --
		// -- Espa�a
		// --
		List<String> listaURL_04 = new ArrayList<String>();
		listaURL_04.add("https://es.investing.com/stock-screener/?sp=country::26|sector::a|industry::a|equityType::ORD|exchange::11|eq_market_cap::100000000,88830000000|yield_us::3,24.88|eq_one_year_return::0,563.72|ytd::0,563.72%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Espa�a", listaURL_04));
		// --
		// -- Holanda
		// --
		List<String> listaURL_05 = new ArrayList<String>();
		listaURL_05.add("https://es.investing.com/stock-screener/?sp=country::21|sector::a|industry::a|equityType::ORD|exchange::7|eq_market_cap::100000000,214540000000|yield_us::3,25.64|eq_one_year_return::0,381.48|ytd::0,267.39%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Holanda", listaURL_05));
		// --
		// -- B�lgica
		// --
		List<String> listaURL_06 = new ArrayList<String>();
		listaURL_06.add("https://es.investing.com/stock-screener/?sp=country::34|sector::a|industry::a|equityType::ORD|exchange::14|eq_market_cap::100000000,152600000000|yield_us::3,21.35|eq_one_year_return::0,280.24|ytd::0,208.64%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("B�lgica", listaURL_06));
		// --
		// -- Nasdaq
		// --
		List<String> listaURL_07 = new ArrayList<String>();
		listaURL_07.add("https://es.investing.com/stock-screener/?sp=country::5|sector::a|industry::a|equityType::ORD|exchange::2|eq_market_cap::100000000,897190000000|yield_us::3,119.85|eq_one_year_return::0,15989900|ytd::0,254900%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Nasdaq", listaURL_07));
		// --
		// -- Nueva York
		// --
		List<String> listaURL_08 = new ArrayList<String>();
		listaURL_08.add("https://es.investing.com/stock-screener/?sp=country::5|sector::a|industry::a|equityType::ORD|exchange::1|eq_market_cap::100000000,897190000000|yield_us::3,119.85|eq_one_year_return::0,15989900|ytd::0,254900%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Nueva York", listaURL_08));
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
		System.setProperty(DriverControllerSCR.WEB_DRIVER_PROPERTY, DriverControllerSCR.WEB_DRIVER_EXE);
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
							DriverControllerSCR.procesarElemento(driver, hrefElemento, downloadFolder);
							hrefsIdx++;
						}
						catch (Exception e)
						{
							LOGGER.error("Se ha producido un error", e);
							DriverControllerSCR.gestionError(driver);
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

	/**
	 * @param driver
	 * @param hrefElemento
	 * @throws Exception
	 */
	public static void procesarElemento(WebDriver driver, String hrefElemento, String downloadFolder) throws Exception
	{
		LOGGER.info("Cargando URL [" + hrefElemento + "]");
		driver.get(hrefElemento);

		LOGGER.info("Accediendo gr�fico t�cnico");
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Gr�fico t�cnico")));
		WebElement graficoLink = driver.findElement(By.partialLinkText("Gr�fico t�cnico"));
		graficoLink.click();

		LOGGER.info("Recuperando URL primer IFrame");
		List<WebElement> listaIFrames = driver.findElements(By.tagName("iframe"));
		String iframe01Url = null;
		for (WebElement iframe : listaIFrames)
		{
			if (iframe.getAttribute("src") != null && iframe.getAttribute("src").indexOf("tvc4.forexpros.com") != -1)
			{
				iframe01Url = iframe.getAttribute("src");
				break;
			}
		}

		LOGGER.info("Cargando URL [" + iframe01Url + "]");
		driver.get(iframe01Url);

		LOGGER.info("Recuperando URL segundo IFrame");
		WebElement iframe02 = driver.findElement(By.tagName("iframe"));
		String iframe02Url = iframe02.getAttribute("src").replaceAll("interval=D", "interval=M");

		LOGGER.info("Cargando URL [" + iframe02Url + "]");
		driver.get(iframe02Url);

		LOGGER.info("Esperando gr�fico en tercer IFrame");
		new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("pane-controls")));

		LOGGER.info("Buscando botonera tipo gr�fico");
		List<WebElement> listaDivsQuick = driver.findElements(By.className("quick"));
		int element = 0;
		for (WebElement divQuick : listaDivsQuick)
		{
			if (element == 0)
			{
				element++;
				continue;
			}

			LOGGER.info("Seleccionando gr�fico de velas");
			WebElement spanVelas = divQuick.findElement(By.tagName("span"));
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(spanVelas));
			spanVelas.click();

		}

		LOGGER.info("Esperamos 500 milisegundos");
		Thread.sleep(500);

		LOGGER.info("Generando screenshot");
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + URLEncoder.encode(hrefElemento, CHARSET) + ".png"));
	}

	/**
	 * @param driver
	 */
	public static void gestionError(WebDriver driver)
	{
		LOGGER.info("Intentando cerrar popup bloqueante");
		try
		{
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.className("popupCloseIcon")));
			WebElement closePopUp = driver.findElement(By.className("popupCloseIcon"));
			closePopUp.click();
		}
		catch (Exception e)
		{
			LOGGER.error("Error intentando cerrar popup bloqueante", e);
		}
	}

}
