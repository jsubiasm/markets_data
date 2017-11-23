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
		LISTA_URLS.add("https://es.investing.com/etfs/financial-select-sector-spdr-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/industrial-sector-spdr-trust");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr---consumer-staples");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr---health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-consumer-discr.-select-sector");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-energy-select-sector-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-materials-select-sector-etf");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-select-sector---technology");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-select-sector---utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-telecommunications");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-financial-sector");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-us-energy-sector-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-consumer-goods-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-health-care-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-basic-materials-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-financial-services");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-consumer");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-industrial-sector");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-ms-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nasdaq-biotech");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-stoxx-telecom---de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx-600-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx-600-telecom--de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-banks");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-stoxx-banks---de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-phlx-sox-semiconductor");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-industrial-average");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-telecom.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-financial");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-healthcare");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-insurance");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-oil-gas");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxx-600-pers-housld-gds");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-food-beverage");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-auto-parts");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-basicresources");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxx-euro-600-retail");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxxeuro-600-cons.---mat.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-media");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-financial-svs");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxx-europe-600-chemicals");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxx600-ind.-goods--ser.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx-600-utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx600-trvl---leis.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-transport-average-index");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-consumer-discretion");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-consumer-staples");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-financials");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-information-tech");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-materials");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-industrials");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-telecom");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-gold-trust");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-comex-gold-trust");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh-wilderhill-clean-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-ftse-emerging-markets");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh.-dynamic-biotech---genome");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh.-dynamic-food---beverage");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh.-dynamic-pharmaceuticals");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-media");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-semiconductors");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-software");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-aerospace---defense");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-energy-e-p");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-retail");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-kbw-bank");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-kbw-insurance");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-kbw-capital-markets");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-water-resource-port");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-biotech");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-homebuilders");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-semiconductor");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-emerging-market?cid=46607");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-nq-100-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-us-oil-gas-exp.---prod.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-aerospace---defense");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-broker-dealers-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-health-care-providers");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-insurance");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-medical-devices-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-pharmaceutical-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-regional-banks-index");
		LISTA_URLS.add("https://es.investing.com/etfs/market-vectors-gold-miners");
		LISTA_URLS.add("https://es.investing.com/etfs/wisdomtree-dividend-x-financials");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-kbw-regional-banking");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-metals---mining");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-oil---gas-eq---services");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-oil--gas-explor---product");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-pharmaceuticals");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-retail");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-amex-biotech");
		LISTA_URLS.add("https://es.investing.com/etfs/first-trust-capital-strength");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-dj-industrial-average?cid=46142");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-materials");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-industrials");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx-600-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/salix-pharmaceuticals-ltd.");
		LISTA_URLS.add("https://es.investing.com/etfs/powers.-wilderhill-prog.-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-cleantech-portfolio");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-gold-de");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-financial");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-industrials");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-materials");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-energy-dj-ubsci");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-industrial-metals-dj-ubsci-de");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-commodity-crb-x-energy-tr?cid=45989");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-precious-metals-dj-ubsci-de");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-financial-preferred");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-dj-wilshire-intl-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-insurance?cid=46176");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-db-base-metals-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-db-energy-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-db-gold-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-db-precious-metals");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-technology?cid=46023");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-utilities?cid=46024");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-health-care?cid=46172");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-telecom?cid=46040");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-ex-energy-dj-ubsci-de");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-nq-100-x-tech-sector");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-oil---gas?cid=46021");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-euro-600-cons.---mat.?cid=46165");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-retail?cid=46035");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx600-per.--house.-goods?cid=46174");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-banks?cid=46171");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-600-ind.-goods---ser.?cid=46153");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-emerging-asia-pacific");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-emerging-markets");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-media?cid=46181");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-chemicals?cid=46117");
		LISTA_URLS.add("https://es.investing.com/etfs/rbs-market-access-rici-metals");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nareit-real-estate-50");
		LISTA_URLS.add("https://es.investing.com/etfs/marketvectors-global-alt.-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-energy-alphadex-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-health-care-alphadex");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-materials-alphadex-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-utilities-alphadex-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-ipath-eurusd-exchange");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-financials-alphadex");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-ise-water");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-physical-gold-de");
		LISTA_URLS.add("https://es.investing.com/etfs/guggenheim-s-p-global-water");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-industrials-alphadex");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-technology-alphadex");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-capital-tips");
		LISTA_URLS.add("https://es.investing.com/etfs/wisdomtree-intl-real-estate-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares---global-clean-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-global-water");
		LISTA_URLS.add("https://es.investing.com/etfs/guggenheim-canada-energy-income");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-stoxxeuro-600-oil-gas");
		LISTA_URLS.add("https://es.investing.com/etfs/dj-stoxx600-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-banks");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-tech");
		LISTA_URLS.add("https://es.investing.com/etfs/marketvectors-nuclear-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-emerging-markets?cid=46020");
		LISTA_URLS.add("https://es.investing.com/etfs/dj-stoxx600-telecommunications");
		LISTA_URLS.add("https://es.investing.com/etfs/db-stoxxeuro600-food--beverage");
		LISTA_URLS.add("https://es.investing.com/etfs/db-stoxxeuro600-ind.-goods");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-glbl-water?cid=46491");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-ft-rafi-emerging-mrkt");
		LISTA_URLS.add("https://es.investing.com/etfs/rogers-intl-commodity-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ipath-dj-ubs-energy-tr-sub-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ipath-dj-ind.-metals-tr-subindex");
		LISTA_URLS.add("https://es.investing.com/etfs/wisdomtree-emerging-mrkt-smallcap");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-global-clean-energy?cid=45836");
		LISTA_URLS.add("https://es.investing.com/etfs/marketvectors-intermediate-mun.");
		LISTA_URLS.add("https://es.investing.com/etfs/claymore-china-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/xetra-gold");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-new-energy?cid=46168");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-world-water?cid=46017");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-forward-ind.-metals-dj-ubsci");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-foward-energy-dj-ubsci-f3");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-foward-ex-energy-dj-ubsci-f3");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etracs-bloomberg-cmci-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etracs-cmci-gold-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/claymore-mac-global-solar-energy-in");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-emerging-mrkt-islamic?cid=46605");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-wilshire-global-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-emerging-markets-smallcap");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-ise-global-wind-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-ipath-bloomberg-lead");
		LISTA_URLS.add("https://es.investing.com/etfs/ipath-dj-ubs-pr.-metals-tr-subin.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-clean-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-commerzbank-eonia-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-dj-industrial-average");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-health-care-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-banks-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxxeuro-600-oil-gas-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh.-global-gold---pr.-metals");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-telecom-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-ind.-goods--ser.");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-retail-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/revenueshares-financials-sector");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-tech-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-cons.---mat.-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-active-us-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-media-nr");
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
