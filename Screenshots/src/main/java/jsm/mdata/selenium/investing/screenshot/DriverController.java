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
	 * URL Groups
	 */
	private final static List<URLGroup> LISTA_URL_GROUP = new ArrayList<URLGroup>();
	static
	{
		// --
		// -- HEALTH CARE
		// --
		List<String> listaURL_01 = new ArrayList<String>();
		listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-europe-healthcare?cid=45977");
		listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-health-care-nr");
		listaURL_01.add("https://es.investing.com/etfs/dj-stoxx600-health-care");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-health-care");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-health-care-tr?cid=46008");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-health-care?cid=46172");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-health-care?cid=46222");
		listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-health-care");
		listaURL_01.add("https://es.investing.com/etfs/fidelity-msci-healthcare-index");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-health-care-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-health-care-index");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-health-care-providers");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-healthcare");
		listaURL_01.add("https://es.investing.com/etfs/powersh-s-p-smallcap-healthcare");
		listaURL_01.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-health-care");
		listaURL_01.add("https://es.investing.com/etfs/spdr---health-care");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-health-care-equipment");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-health-care-services");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-health-care");
		LISTA_URL_GROUP.add(new URLGroup("health-care", listaURL_01));
		// --
		// -- CLEAN ENERGY
		// --
		List<String> listaURL_02 = new ArrayList<String>();
		listaURL_02.add("https://es.investing.com/etfs/ishares-global-clean-energy?cid=45836");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-new-energy?cid=46168");
		listaURL_02.add("https://es.investing.com/etfs/claymore-mac-global-solar-energy-in");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-ise-global-wind-energy");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-clean-energy");
		listaURL_02.add("https://es.investing.com/etfs/marketvectors-global-alt.-energy");
		listaURL_02.add("https://es.investing.com/etfs/powersh-wilderhill-clean-energy");
		listaURL_02.add("https://es.investing.com/etfs/powershares---global-clean-energy");
		LISTA_URL_GROUP.add(new URLGroup("clean-energy", listaURL_02));
		// --
		// -- GOLD
		// --
		List<String> listaURL_03 = new ArrayList<String>();
		listaURL_03.add("https://es.investing.com/etfs/comstage-nyse-arca-gold-bugs");
		listaURL_03.add("https://es.investing.com/etfs/db-physical-gold-etc-(eur)-de");
		listaURL_03.add("https://es.investing.com/etfs/db-physical-gold-euro-hdg-etc-de");
		listaURL_03.add("https://es.investing.com/etfs/etfs-gold-de");
		listaURL_03.add("https://es.investing.com/etfs/etfs-physical-gold-de");
		listaURL_03.add("https://es.investing.com/etfs/etfs-physical-swiss-gold-de");
		listaURL_03.add("https://es.investing.com/etfs/etfx-daxglobal-gold-mining-fund");
		listaURL_03.add("https://es.investing.com/etfs/ishares-s-p-commodity-prod.-gold");
		listaURL_03.add("https://es.investing.com/etfs/marketaccess-arca-gold-bugs-uk?cid=942310");
		listaURL_03.add("https://es.investing.com/etfs/source-physical-gold-p-etc-de");
		listaURL_03.add("https://es.investing.com/etfs/xetra-gold");
		listaURL_03.add("https://es.investing.com/etfs/credit-suisse-x-links-gold-shares");
		listaURL_03.add("https://es.investing.com/etfs/etfs-physical-swiss-gold-shares");
		listaURL_03.add("https://es.investing.com/etfs/global-x-gold-explorers");
		listaURL_03.add("https://es.investing.com/etfs/ishares-comex-gold-trust");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-global-gold-miners-be?cid=44703");
		listaURL_03.add("https://es.investing.com/etfs/market-vectors-gold-miners");
		listaURL_03.add("https://es.investing.com/etfs/market-vectors-junior-gold-miners");
		listaURL_03.add("https://es.investing.com/etfs/powersh.-global-gold---pr.-metals");
		listaURL_03.add("https://es.investing.com/etfs/powershares-db-gold-fund");
		listaURL_03.add("https://es.investing.com/etfs/spdr-gold-trust");
		listaURL_03.add("https://es.investing.com/etfs/sprott-physical-gold-trust");
		listaURL_03.add("https://es.investing.com/etfs/ubs-etracs-cmci-gold-tr");
		LISTA_URL_GROUP.add(new URLGroup("gold", listaURL_03));
		// --
		// -- REAL ESTATE REIT
		// --
		List<String> listaURL_04 = new ArrayList<String>();
		listaURL_04.add("https://es.investing.com/etfs/amundi-real-estate-reit-ieif?cid=46112");
		listaURL_04.add("https://es.investing.com/etfs/db-ftse-nareit-dev.-euro-re-1c");
		listaURL_04.add("https://es.investing.com/etfs/ishares-eurostoxx-600-real-estate");
		listaURL_04.add("https://es.investing.com/etfs/ishares-ftse-nareit-asia-fd?cid=38604");
		listaURL_04.add("https://es.investing.com/etfs/ishares-ftse-nareit-dev.-markets?cid=38605");
		listaURL_04.add("https://es.investing.com/etfs/ishares-ftse-nareit-us-prop-fd?cid=38606");
		listaURL_04.add("https://es.investing.com/etfs/ishares-nareit-european-prop.-fund?cid=38612");
		listaURL_04.add("https://es.investing.com/etfs/lyxor-msci-europe-real-estate?cid=46182");
		listaURL_04.add("https://es.investing.com/etfs/lyxor-msci-usa-real-estate?cid=46188");
		listaURL_04.add("https://es.investing.com/etfs/lyxor-msci-world-real-estate?cid=46031");
		listaURL_04.add("https://es.investing.com/etfs/spdr-dow-jones-global-real-estate?cid=45857");
		listaURL_04.add("https://es.investing.com/etfs/claymore-china-real-estate");
		listaURL_04.add("https://es.investing.com/etfs/firsttrust-epra-nareit-global-re");
		listaURL_04.add("https://es.investing.com/etfs/firsttrust-s-p-reit-index-fund");
		listaURL_04.add("https://es.investing.com/etfs/iq-us-real-estate-small-cap");
		listaURL_04.add("https://es.investing.com/etfs/ishares-ftepra-nareit-dev.-re-x-us");
		listaURL_04.add("https://es.investing.com/etfs/ishares-ftse-epra-nareit-europe");
		listaURL_04.add("https://es.investing.com/etfs/ishares-ftse-nareit-residential");
		listaURL_04.add("https://es.investing.com/etfs/ishares-nareit-mortgage-reits");
		listaURL_04.add("https://es.investing.com/etfs/ishares-nareit-real-estate-50");
		listaURL_04.add("https://es.investing.com/etfs/marketvectors-mortgage-reit-income");
		listaURL_04.add("https://es.investing.com/etfs/powershares-active-us-real-estate");
		listaURL_04.add("https://es.investing.com/etfs/powershares-kbw-premium-y-eq-reit");
		listaURL_04.add("https://es.investing.com/etfs/spdr-dj-wilshire-intl-real-estate");
		listaURL_04.add("https://es.investing.com/etfs/spdr-dj-wilshire-reit");
		listaURL_04.add("https://es.investing.com/etfs/spdr-wilshire-global-real-estate");
		listaURL_04.add("https://es.investing.com/etfs/u.s.-reit");
		listaURL_04.add("https://es.investing.com/etfs/vanguard-global-ex-su-real-estate");
		listaURL_04.add("https://es.investing.com/etfs/vanguard-reit");
		listaURL_04.add("https://es.investing.com/etfs/wilshire-us-reit");
		listaURL_04.add("https://es.investing.com/etfs/wisdomtree-intl-real-estate-fund");
		LISTA_URL_GROUP.add(new URLGroup("real-estate-reit", listaURL_04));
		// --
		// -- INFRAESTRUCTURES
		// --
		List<String> listaURL_05 = new ArrayList<String>();
		listaURL_05.add("https://es.investing.com/etfs/db-xtrackers-s-p-global-infr.-uk?cid=38694");
		listaURL_05.add("https://es.investing.com/etfs/easyetf-nmx30-infr.-global");
		listaURL_05.add("https://es.investing.com/etfs/ishares-s-p-emerging-mkts-infr.?cid=46608");
		listaURL_05.add("https://es.investing.com/etfs/alps-alerian-energy-infra");
		listaURL_05.add("https://es.investing.com/etfs/db-x-trackers-muni-infras-revn");
		listaURL_05.add("https://es.investing.com/etfs/egshares-india-infrastructure");
		listaURL_05.add("https://es.investing.com/etfs/etracs-1xmon.-sh.-al.-mlp-infr.-tr");
		listaURL_05.add("https://es.investing.com/etfs/etracs-alerian-mlp-infrastructure");
		listaURL_05.add("https://es.investing.com/etfs/firsttrust-north-am.-energy-infr.");
		listaURL_05.add("https://es.investing.com/etfs/flexshares-stoxx-gbl-brd-infrs");
		listaURL_05.add("https://es.investing.com/etfs/global-x-mlp---energy-infrs");
		listaURL_05.add("https://es.investing.com/etfs/ishares-s-p-global-infrastructure");
		listaURL_05.add("https://es.investing.com/etfs/nq-clean-edge-smart-grid-infras.");
		listaURL_05.add("https://es.investing.com/etfs/powershares-global-em-infr.");
		listaURL_05.add("https://es.investing.com/etfs/s-p-emerging-mrkt-infrastructure");
		LISTA_URL_GROUP.add(new URLGroup("infraestructure", listaURL_05));
		// --
		// -- BASIC RESOURCES, MATERIALS AND METALS
		// --
		List<String> listaURL_06 = new ArrayList<String>();
		listaURL_06.add("https://es.investing.com/etfs/amundi-msci-europe-materials?cid=46111");
		listaURL_06.add("https://es.investing.com/etfs/comstage-stoxx600-basic-res.-nr");
		listaURL_06.add("https://es.investing.com/etfs/db-stoxxeuro600-basic-res.");
		listaURL_06.add("https://es.investing.com/etfs/etfs-forward-ind.-metals-dj-ubsci");
		listaURL_06.add("https://es.investing.com/etfs/etfs-industrial-metals-dj-ubsci-de");
		listaURL_06.add("https://es.investing.com/etfs/etfs-pre.-metals-dj-ubs-eur-hedged");
		listaURL_06.add("https://es.investing.com/etfs/etfs-precious-metals-dj-ubsci-de");
		listaURL_06.add("https://es.investing.com/etfs/ishares-dj-stoxx600-basicresources");
		listaURL_06.add("https://es.investing.com/etfs/lyxor-msci-world-materials-tr?cid=46025");
		listaURL_06.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-basic-res.?cid=46068");
		listaURL_06.add("https://es.investing.com/etfs/rbs-market-access-rici-metals");
		listaURL_06.add("https://es.investing.com/etfs/spdr-msci-europe-materials?cid=46216");
		listaURL_06.add("https://es.investing.com/etfs/stoxx-600-optimised-basic-res.");
		listaURL_06.add("https://es.investing.com/etfs/barclays-ipath-pure-beta-ind-metals");
		listaURL_06.add("https://es.investing.com/etfs/fidelity-msci-materials-index");
		listaURL_06.add("https://es.investing.com/etfs/firsttrust-materials-alphadex-fund");
		listaURL_06.add("https://es.investing.com/etfs/global-x-china-materials");
		listaURL_06.add("https://es.investing.com/etfs/ipath-dj-ind.-metals-tr-subindex");
		listaURL_06.add("https://es.investing.com/etfs/ipath-dj-ubs-pr.-metals-tr-subin.");
		listaURL_06.add("https://es.investing.com/etfs/ishares-djsu-basic-materials-index");
		listaURL_06.add("https://es.investing.com/etfs/ishares-s-p-global-materials");
		listaURL_06.add("https://es.investing.com/etfs/marketv.-rare-earth-strat.-metals");
		listaURL_06.add("https://es.investing.com/etfs/msci-global-metals-mining-prod.");
		listaURL_06.add("https://es.investing.com/etfs/physical-pr.-metals-basket-sh.");
		listaURL_06.add("https://es.investing.com/etfs/powersh.-global-gold---pr.-metals");
		listaURL_06.add("https://es.investing.com/etfs/powershares-db-base-metals-fund");
		listaURL_06.add("https://es.investing.com/etfs/powershares-db-precious-metals");
		listaURL_06.add("https://es.investing.com/etfs/powershares-s-p-smallcap-materials");
		listaURL_06.add("https://es.investing.com/etfs/rogers-intl-commodity-metal");
		listaURL_06.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-materials");
		listaURL_06.add("https://es.investing.com/etfs/spdr-materials-select-sector-etf");
		listaURL_06.add("https://es.investing.com/etfs/spdr-s-p-metals---mining");
		listaURL_06.add("https://es.investing.com/etfs/vanguard-materials");
		LISTA_URL_GROUP.add(new URLGroup("basic-res-materials-metals", listaURL_06));
		// --
		// -- TELECOM
		// --
		List<String> listaURL_07 = new ArrayList<String>();
		listaURL_07.add("https://es.investing.com/etfs/amundi-msci-europe-telecom-ser.?cid=46135");
		listaURL_07.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-telecom-nr");
		listaURL_07.add("https://es.investing.com/etfs/dj-stoxx600-telecommunications");
		listaURL_07.add("https://es.investing.com/etfs/ishares-euro-stoxx-telecom---de");
		listaURL_07.add("https://es.investing.com/etfs/ishares-eurostoxx-600-telecom--de");
		listaURL_07.add("https://es.investing.com/etfs/lyxor-msci-world-telecom-ser.-tr?cid=46041");
		listaURL_07.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-telecom?cid=46040");
		listaURL_07.add("https://es.investing.com/etfs/spdr-msci-europe-telecom-services?cid=46220");
		listaURL_07.add("https://es.investing.com/etfs/stoxx-600-optimised-telecom-source");
		listaURL_07.add("https://es.investing.com/etfs/fidelity-msci-telecom-svcs");
		listaURL_07.add("https://es.investing.com/etfs/ishares-djsu-telecommunications");
		listaURL_07.add("https://es.investing.com/etfs/ishares-s-p-global-telecom.");
		listaURL_07.add("https://es.investing.com/etfs/spdr-s-p-telecom");
		listaURL_07.add("https://es.investing.com/etfs/vanguard-telecom");
		LISTA_URL_GROUP.add(new URLGroup("telecom", listaURL_07));
		// --
		// -- UTILITIES
		// --
		List<String> listaURL_08 = new ArrayList<String>();
		listaURL_08.add("https://es.investing.com/etfs/amundi-etf-msci-europe-utilities?cid=45993");
		listaURL_08.add("https://es.investing.com/etfs/ishares-eurostoxx-600-utilities");
		listaURL_08.add("https://es.investing.com/etfs/lyxor-msci-world-utilities-tr?cid=46272");
		listaURL_08.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-utilities?cid=46024");
		listaURL_08.add("https://es.investing.com/etfs/spdr-msci-europe-utilities?cid=46221");
		listaURL_08.add("https://es.investing.com/etfs/fidelity-msci-utilities-index");
		listaURL_08.add("https://es.investing.com/etfs/firsttrust-utilities-alphadex-fund");
		listaURL_08.add("https://es.investing.com/etfs/ishares-djsu-utilities");
		listaURL_08.add("https://es.investing.com/etfs/ishares-s-p-global-utilities");
		listaURL_08.add("https://es.investing.com/etfs/powersh-s-p-small-cap-utilities");
		listaURL_08.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-utilities");
		listaURL_08.add("https://es.investing.com/etfs/spdr-select-sector---utilities");
		listaURL_08.add("https://es.investing.com/etfs/vanguard-utilities");
		LISTA_URL_GROUP.add(new URLGroup("utilities", listaURL_08));
		// --
		// -- BIOTECH
		// --
		List<String> listaURL_09 = new ArrayList<String>();
		listaURL_09.add("https://es.investing.com/etfs/firsttrust-amex-biotech");
		listaURL_09.add("https://es.investing.com/etfs/ishares-nasdaq-biotech");
		listaURL_09.add("https://es.investing.com/etfs/market-vectors-biotech");
		listaURL_09.add("https://es.investing.com/etfs/powersh.-dynamic-biotech---genome");
		listaURL_09.add("https://es.investing.com/etfs/spdr-s-p-biotech");
		LISTA_URL_GROUP.add(new URLGroup("biotech", listaURL_09));
		// --
		// -- PHARMA
		// --
		List<String> listaURL_10 = new ArrayList<String>();
		listaURL_10.add("https://es.investing.com/etfs/holdrs-merrill-lynch-pharma.");
		listaURL_10.add("https://es.investing.com/etfs/ishares-djsu-pharmaceutical-index");
		listaURL_10.add("https://es.investing.com/etfs/powersh.-dynamic-pharmaceuticals");
		listaURL_10.add("https://es.investing.com/etfs/salix-pharmaceuticals-ltd.");
		listaURL_10.add("https://es.investing.com/etfs/spdr-s-p-pharmaceuticals");
		LISTA_URL_GROUP.add(new URLGroup("pharma", listaURL_10));
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
					DriverController.procesarElemento(driver, hrefElemento, downloadFolder);
					urlsIdx++;
				}
				catch (Exception e)
				{
					LOGGER.error("Se ha producido un error", e);
					DriverController.gestionError(driver);
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

		LOGGER.info("Esperando gráfico en tercer IFrame");
		new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("pane-controls")));
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
