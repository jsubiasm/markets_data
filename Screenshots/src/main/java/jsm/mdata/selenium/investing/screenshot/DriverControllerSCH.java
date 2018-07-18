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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Empleado
 *
 */
public class DriverControllerSCH extends DriverControllerBase
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DriverControllerSCH.class);

	/**
	 * URL Groups
	 */
	private final static List<URLGroup> LISTA_URL_GROUP = new ArrayList<URLGroup>();
	static
	{
		// --
		// -- ETFs NYSE
		// --
		List<String> listaURL_01 = new ArrayList<String>();
		listaURL_01.add("SPY");
		LISTA_URL_GROUP.add(new URLGroup("NYSE", listaURL_01));
		// --
		// -- ETFs NASDAQ
		// --
		List<String> listaURL_02 = new ArrayList<String>();
		listaURL_02.add("QQQ");
		LISTA_URL_GROUP.add(new URLGroup("NASDAQ", listaURL_02));
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
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);

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
					LOGGER.info("Cargando URL inicial");
					driver.get("https://es.investing.com/");
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.className("disclaimer")));

					String ticker = listaURL.get(urlsIdx);
					LOGGER.info("Buscando ticker [" + ticker + "]");
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("searchTextTop")));
					WebElement textBoxBusqueda = driver.findElement(By.id("searchTextTop"));
					textBoxBusqueda.clear();
					textBoxBusqueda.sendKeys(ticker);

					LOGGER.info("Filtrando resultado solo para ETFs");
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("search_box_topETFs")));
					WebElement filterETFs = driver.findElement(By.id("search_box_topETFs"));
					filterETFs.click();

					LOGGER.info("Seleccionando primer resultado");
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("searchRowIdtop_0")));
					WebElement primerETF = driver.findElement(By.id("searchRowIdtop_0"));
					primerETF.click();

					LOGGER.info("Recuperando URL");
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.className("disclaimer")));
					String hrefElemento = driver.getCurrentUrl();

					LOGGER.info("Procesando URL [" + hrefElemento + "]");
					procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_MENSUAL, TF_MENSUAL);
					procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_SEMANAL, TF_SEMANAL);
					// procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_DIARIO, TF_DIARIO);
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
