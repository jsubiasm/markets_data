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
	private static final String DOWNLOAD_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\Screenshots\\investing\\download";

	/**
	 * Formatos
	 */
	private static final String CHARSET = "UTF-8";

	/**
	 * URLs
	 */
	private final static List<String> LISTA_URLS = new ArrayList<String>();
	static
	{
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-emerging-market?cid=46607");
		LISTA_URLS.add("https://es.investing.com/etfs/gold-bullion-securities");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-physical-gold-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-gold-de");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-emerging-markets?cid=46020");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-global-clean-energy?cid=45836");
		LISTA_URLS.add("https://es.investing.com/etfs/xetra-gold");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-new-energy?cid=46168");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-glbl-water?cid=46491");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-world-water?cid=46017");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-jp-usd-emerging-mkts-bond?cid=46602");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-emerging-mkts-infr.?cid=46608");
		LISTA_URLS.add("https://es.investing.com/etfs/etfx-daxglobal-gold-mining-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-emerging-mrkt-islamic?cid=46605");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-emerging-mrkt---acc?cid=46604");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-physical-swiss-gold-de");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-nyse-arca-gold-bugs");
		LISTA_URLS.add("https://es.investing.com/etfs/db-physical-gold-euro-hdg-etc-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-msci-emerging-markets");
		LISTA_URLS.add("https://es.investing.com/etfs/db-physical-gold-etc-(eur)-de");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etf-msci-emerging-markets-a");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-emerging-markets?cid=45949");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-emerging-markets?cid=45850");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-emerging-markets-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-emerging-mrktsdividend-uk?cid=45833");
		LISTA_URLS.add("https://es.investing.com/etfs/msci-emerging-mrktssource-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-gold-eur-daily-hedged-de");
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
		System.setProperty(DriverController.WEB_DRIVER_PROPERTY, DriverController.WEB_DRIVER_EXE);
		WebDriver driver = new ChromeDriver();

		LOGGER.info("Cargando URL inicial para introducir datos proxy");
		driver.get("https://es.investing.com/");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.className("disclaimer")));

		LOGGER.info("Iniciando proceso");
		int urlsIdx = 0;
		while (urlsIdx < LISTA_URLS.size())
		{
			try
			{
				String hrefElemento = LISTA_URLS.get(urlsIdx);
				DriverController.procesarElemento(driver, hrefElemento);
				urlsIdx++;
			}
			catch (Exception e)
			{
				LOGGER.error("Se ha producido un error", e);
				DriverController.gestionError(driver);
			}
		}
	}

	/**
	 * @param driver
	 * @param hrefElemento
	 * @throws Exception
	 */
	public static void procesarElemento(WebDriver driver, String hrefElemento) throws Exception
	{
		LOGGER.info("Cargando URL [" + hrefElemento + "]");
		driver.get(hrefElemento);

		LOGGER.info("Accediendo gráfico técnico");
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Gráfico técnico")));
		WebElement graficoLink = driver.findElement(By.partialLinkText("Gráfico técnico"));
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
		Thread.sleep(2000);

		LOGGER.info("Generando screenshot");
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(DOWNLOAD_PATH + "\\" + URLEncoder.encode(hrefElemento, CHARSET) + ".png"));
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
