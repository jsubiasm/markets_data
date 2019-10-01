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
	 * 
	 */
	private final static Integer MAX_ERROR_RETRY = 10;

	/**
	 * 
	 */
	private final static String URL_INICIAL = "https://es.investing.com";

	/**
	 * URL Groups
	 */
	private final static List<URLGroup> LISTA_URL_GROUP = new ArrayList<URLGroup>();
	static
	{
		// --
		// -- ETFs USA
		// --
		List<String> listaURL_01 = new ArrayList<String>();
		listaURL_01.add("AIRR");
		listaURL_01.add("BBP");
		listaURL_01.add("BJK");
		listaURL_01.add("CGW");
		listaURL_01.add("CHIM");
		listaURL_01.add("CHIQ");
		listaURL_01.add("CUT");
		listaURL_01.add("EVX");
		listaURL_01.add("EXI");
		listaURL_01.add("FBT");
		listaURL_01.add("FDIS");
		listaURL_01.add("FHLC");
		listaURL_01.add("FIDU");
		listaURL_01.add("FILL");
		listaURL_01.add("FIW");
		listaURL_01.add("FMAT");
		listaURL_01.add("FNCL");
		listaURL_01.add("FXD");
		listaURL_01.add("FXO");
		listaURL_01.add("FXR");
		listaURL_01.add("FXZ");
		listaURL_01.add("GNR");
		listaURL_01.add("GRID");
		listaURL_01.add("GUNR");
		listaURL_01.add("HAP");
		listaURL_01.add("IAI");
		listaURL_01.add("IAT");
		listaURL_01.add("IEO");
		listaURL_01.add("IGE");
		listaURL_01.add("IHF");
		listaURL_01.add("IHI");
		listaURL_01.add("INCO");
		listaURL_01.add("ITA");
		listaURL_01.add("ITB");
		listaURL_01.add("IXC");
		listaURL_01.add("IYC");
		listaURL_01.add("IYF");
		listaURL_01.add("IYG");
		listaURL_01.add("IYH");
		listaURL_01.add("IYJ");
		listaURL_01.add("IYM");
		listaURL_01.add("IYT");
		listaURL_01.add("KBE");
		listaURL_01.add("KBWB");
		listaURL_01.add("KBWR");
		listaURL_01.add("KCE");
		listaURL_01.add("KOL");
		listaURL_01.add("KRE");
		listaURL_01.add("LIT");
		listaURL_01.add("MOO");
		listaURL_01.add("MXI");
		listaURL_01.add("NLR");
		listaURL_01.add("PBS");
		listaURL_01.add("PEJ");
		listaURL_01.add("PEZ");
		listaURL_01.add("PFI");
		listaURL_01.add("PHO");
		listaURL_01.add("PICK");
		listaURL_01.add("PPA");
		listaURL_01.add("PRN");
		listaURL_01.add("PSCC");
		listaURL_01.add("PSCD");
		listaURL_01.add("PSCF");
		listaURL_01.add("PSCH");
		listaURL_01.add("PSCI");
		listaURL_01.add("PSCM");
		listaURL_01.add("PSL");
		listaURL_01.add("PSP");
		listaURL_01.add("PTH");
		listaURL_01.add("PYZ");
		listaURL_01.add("PZD");
		listaURL_01.add("QABA");
		listaURL_01.add("RCD");
		listaURL_01.add("REMX");
		listaURL_01.add("RGI");
		listaURL_01.add("RTH");
		listaURL_01.add("RTM");
		listaURL_01.add("RWW");
		listaURL_01.add("RXI");
		listaURL_01.add("RYF");
		listaURL_01.add("RYH");
		listaURL_01.add("SLX");
		listaURL_01.add("VAW");
		listaURL_01.add("VCR");
		listaURL_01.add("VDE");
		listaURL_01.add("VFH");
		listaURL_01.add("VHT");
		listaURL_01.add("VIS");
		listaURL_01.add("WOOD");
		listaURL_01.add("XAR");
		listaURL_01.add("XHE");
		listaURL_01.add("XLB");
		listaURL_01.add("XLE");
		listaURL_01.add("XLF");
		listaURL_01.add("XLI");
		listaURL_01.add("XLV");
		listaURL_01.add("XLY");
		listaURL_01.add("XME");
		listaURL_01.add("XTN");
		LISTA_URL_GROUP.add(new URLGroup("ETFs_USA", listaURL_01));
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
			int errorRetry = 0;
			while (urlsIdx < listaURL.size())
			{
				try
				{
					LOGGER.info("Cargando URL inicial");
					driver.get(URL_INICIAL);
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("disclaimer")));

					String ticker = listaURL.get(urlsIdx);
					LOGGER.info("Buscando ticker [" + ticker + "]");
					if (errorRetry > 0)
					{
						LOGGER.info("Reintento [" + errorRetry + "]");
					}
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
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Gráfico")));
					String hrefElemento = driver.getCurrentUrl();
					if (hrefElemento.equalsIgnoreCase(URL_INICIAL))
					{
						throw new Exception("URL inadecuada");
					}

					LOGGER.info("Procesando URL [" + hrefElemento + "]");
					procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_MENSUAL, TF_MENSUAL, null);
					procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_SEMANAL, TF_SEMANAL, null);
					// procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_DIARIO, TF_DIARIO);
					urlsIdx++;
					errorRetry = 0;
				}
				catch (Exception e)
				{
					LOGGER.error("Se ha producido un error", e);
					errorRetry++;
					if (errorRetry >= MAX_ERROR_RETRY)
					{
						urlsIdx++;
						errorRetry = 0;
					}
				}
			}
		}

	}

}
