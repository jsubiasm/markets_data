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
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-europe-energy?cid=45955");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-europe-healthcare?cid=45977");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-europe-industrials?cid=45982");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-europe-insurance?cid=45980");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-europe-utilities?cid=45993");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-world-energy---eur?cid=45995");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-msci-europe-banks-de");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-msci-europe-materials?cid=46111");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-msci-europe-telecom-ser.?cid=46135");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-msci-world-financials---eur?cid=46140");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-real-estate-reit-ieif?cid=46112");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-commerzbank-eonia-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-dj-industrial-average");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-nyse-arca-gold-bugs");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-retail-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-telecom-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-banks-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-media-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-tech-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-auto.--parts-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-health-care-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-travel--lei.-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstagecommerzbank-bund-future-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-ftse-nareit-dev.-euro-re-1c");
		LISTA_URLS.add("https://es.investing.com/etfs/db-mts-x-bank-of-italy-agg.-de");
		LISTA_URLS.add("https://es.investing.com/etfs/db-mts-x-bank-of-italy-bot-de");
		LISTA_URLS.add("https://es.investing.com/etfs/db-mts-x-bank-of-italy-btp-de");
		LISTA_URLS.add("https://es.investing.com/etfs/db-physical-gold-etc-(eur)-de");
		LISTA_URLS.add("https://es.investing.com/etfs/db-physical-gold-euro-hdg-etc-de");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-banks");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-tech");
		LISTA_URLS.add("https://es.investing.com/etfs/dj-stoxx600-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/dj-stoxx600-telecommunications");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-energy-dj-ubsci");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-ex-energy-dj-ubsci-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-forward-ind.-metals-dj-ubsci");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-forward-softs-dj-ubsci-f3-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-foward-energy-dj-ubsci-f3");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-foward-ex-energy-dj-ubsci-f3");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-gold-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-industrial-metals-dj-ubsci-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-physical-gold-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-physical-swiss-gold-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-pre.-metals-dj-ubs-eur-hedged");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-precious-metals-dj-ubsci-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-softs-dj-ubsci-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etfx-daxglobal-gold-mining-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/euro-stoxx-optimised-banks-source");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-industrial-average");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-auto-parts");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-banks");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-basicresources");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-financial-svs");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-insurance");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-media");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-us-energy-sector-fund?cid=21520");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-us-energy-sector-fund?cid=38200");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-corp-bd-x-finance?cid=46466");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-stoxx-banks---de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-stoxx-telecom---de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx-600-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx-600-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx-600-telecom--de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx-600-utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-nareit-asia-fd?cid=38604");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-nareit-dev.-markets?cid=38605");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-nareit-us-prop-fd?cid=38606");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-global-clean-energy?cid=45836");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nareit-european-prop.-fund?cid=38612");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-commodity-prod.-gold");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-glbl-water?cid=46491");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxx-euro-600-retail");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxx-europe-600-chemicals");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-commodity-crb-x-energy-tr?cid=45989");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-dj-industrial-average?cid=46142");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-euro-corp-bond-x-financials?cid=46120");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-europe-real-estate?cid=46182");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-usa-real-estate?cid=46188");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-consumer-disc.-tr?cid=46122");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-energy-tr?cid=46033");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-financials-tr?cid=46006");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-health-care-tr?cid=46008");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-industrials-tr?cid=46010");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-info-tech-tr?cid=46225");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-materials-tr?cid=46025");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-real-estate?cid=46031");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-telecom-ser.-tr?cid=46041");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-utilities-tr?cid=46272");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-new-energy?cid=46168");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-banks?cid=46171");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-chemicals?cid=46117");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-health-care?cid=46172");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-insurance?cid=46176");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-media?cid=46181");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-retail?cid=46035");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-technology?cid=46023");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-telecom?cid=46040");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-utilities?cid=46024");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-auto.---parts?cid=46104");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-travel--lei.?cid=46175");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-world-water?cid=46017");
		LISTA_URLS.add("https://es.investing.com/etfs/marketaccess-arca-gold-bugs-uk?cid=942310");
		LISTA_URLS.add("https://es.investing.com/etfs/rbs-market-access-rici-metals");
		LISTA_URLS.add("https://es.investing.com/etfs/source-physical-gold-p-etc-de");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-dow-jones-global-real-estate?cid=45857");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-consumer-discr.?cid=46218");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-consumer-staples?cid=46219");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-energy?cid=46184");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-financials?cid=46223");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-health-care?cid=46222");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-industrials?cid=46217");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-info-tech?cid=46215");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-materials?cid=46216");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-telecom-services?cid=46220");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-utilities?cid=46221");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-opt.-banks-source-de");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-chemicals");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-financial-ser.");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-insurance");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-media-source");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-retail-source");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-tech-source");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-telecom-source");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx600-opt.-auto.--parts-source");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx600-opt.-travel--lei.-sou.");
		LISTA_URLS.add("https://es.investing.com/etfs/xetra-gold");
		LISTA_URLS.add("https://es.investing.com/etfs/alps-alerian-energy-infra");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-fi-enhanced-global");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-ipath-asian-gulf-cur");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-ipath-bloomberg-lead");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-ipath-eurusd-exchange");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-ipath-gbpusd-exchange");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-ipath-jpyusd-exchange");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-ipath-pure-beta-lead");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-ipath-pure-beta-sp");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-ipath-sp-mlp");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-ipath-us-treasury-10");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-ipath-pure-beta-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-ipath-pure-beta-ind-metals");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-ipath-pure-beta-softs");
		LISTA_URLS.add("https://es.investing.com/etfs/build-america-bond-portfolio");
		LISTA_URLS.add("https://es.investing.com/etfs/claymore-china-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/claymore-mac-global-solar-energy-in");
		LISTA_URLS.add("https://es.investing.com/etfs/credit-suisse-x-links-gold-shares");
		LISTA_URLS.add("https://es.investing.com/etfs/deutsche-bank-fi-enhanced-global");
		LISTA_URLS.add("https://es.investing.com/etfs/egshares-emerging-markets-consumer");
		LISTA_URLS.add("https://es.investing.com/etfs/egshares-india-consumer");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-physical-swiss-gold-shares");
		LISTA_URLS.add("https://es.investing.com/etfs/fidelity-msci-energy-index");
		LISTA_URLS.add("https://es.investing.com/etfs/fidelity-msci-financials-index");
		LISTA_URLS.add("https://es.investing.com/etfs/fidelity-msci-healthcare-index");
		LISTA_URLS.add("https://es.investing.com/etfs/fidelity-msci-indust-index");
		LISTA_URLS.add("https://es.investing.com/etfs/fidelity-msci-info-tech");
		LISTA_URLS.add("https://es.investing.com/etfs/fidelity-msci-materials-index");
		LISTA_URLS.add("https://es.investing.com/etfs/fidelity-msci-telecom-svcs");
		LISTA_URLS.add("https://es.investing.com/etfs/fidelity-msci-utilities-index");
		LISTA_URLS.add("https://es.investing.com/etfs/financial-select-sector-spdr-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttr.-nq-clean-edge-green-en.");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-amex-biotech");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-energy-alphadex-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-epra-nareit-global-re");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-financials-alphadex");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-health-care-alphadex");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-industrials-alphadex");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-ise-global-wind-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-ise-water");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-materials-alphadex-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-nasdaq-global-auto");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-north-am.-energy-infr.");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-nq-100-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-nq-100-x-tech-sector");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-nq-aba-community-bank");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-nq-technology-dividend");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-s-p-reit-index-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-technology-alphadex");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-utilities-alphadex-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/global-x-china-consumer");
		LISTA_URLS.add("https://es.investing.com/etfs/global-x-china-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/global-x-china-financials");
		LISTA_URLS.add("https://es.investing.com/etfs/global-x-china-industrials");
		LISTA_URLS.add("https://es.investing.com/etfs/global-x-china-materials");
		LISTA_URLS.add("https://es.investing.com/etfs/global-x-gold-explorers");
		LISTA_URLS.add("https://es.investing.com/etfs/global-x-mlp---energy-infrs");
		LISTA_URLS.add("https://es.investing.com/etfs/global-x-nasdaq-china-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/global-x-social-media-index");
		LISTA_URLS.add("https://es.investing.com/etfs/golden-dragon-halter-usx-china");
		LISTA_URLS.add("https://es.investing.com/etfs/guggenheim-canada-energy-income");
		LISTA_URLS.add("https://es.investing.com/etfs/guggenheim-china-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/guggenheim-s-p-global-water");
		LISTA_URLS.add("https://es.investing.com/etfs/holdrs-merrill-lynch-pharma.");
		LISTA_URLS.add("https://es.investing.com/etfs/holdrs-merrill-lynch-semiconductor");
		LISTA_URLS.add("https://es.investing.com/etfs/industrial-sector-spdr-trust");
		LISTA_URLS.add("https://es.investing.com/etfs/ipath-dj-ind.-metals-tr-subindex");
		LISTA_URLS.add("https://es.investing.com/etfs/ipath-dj-ubs-energy-tr-sub-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ipath-dj-ubs-pr.-metals-tr-subin.");
		LISTA_URLS.add("https://es.investing.com/etfs/ipath-dj-ubs-softs-tr-sub-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ipath-goldman-sachs-crude-oil-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/iq-arb-global-resources-etf");
		LISTA_URLS.add("https://es.investing.com/etfs/iq-us-real-estate-small-cap");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-comex-gold-trust");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-transport-average-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-us-energy-sector-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-aerospace---defense");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-basic-materials-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-consumer");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-consumer-goods-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-financial-sector");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-financial-services");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-health-care-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-health-care-providers");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-home-construction");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-industrial-sector");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-insurance");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-medical-devices-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-pharmaceutical-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-regional-banks-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-telecommunications");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftepra-nareit-dev.-re-x-us");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-epra-nareit-europe");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-nareit-residential");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-goldman-sachs-network");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-goldman-sachs-software");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-goldman-sachs-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-global-energy-prod.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-global-gold-miners-be?cid=44703");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nareit-mortgage-reits");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nareit-real-estate-50");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nasdaq-biotech");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-phlx-sox-semiconductor");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-clean-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-financial");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-healthcare");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-industrials");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-materials");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-telecom.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/market-vectors-biotech");
		LISTA_URLS.add("https://es.investing.com/etfs/market-vectors-gold-miners");
		LISTA_URLS.add("https://es.investing.com/etfs/market-vectors-junior-gold-miners");
		LISTA_URLS.add("https://es.investing.com/etfs/market-vectors-retail");
		LISTA_URLS.add("https://es.investing.com/etfs/marketv.-rare-earth-strat.-metals");
		LISTA_URLS.add("https://es.investing.com/etfs/marketvectors-global-alt.-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/marketvectors-intermediate-mun.");
		LISTA_URLS.add("https://es.investing.com/etfs/marketvectors-mortgage-reit-income");
		LISTA_URLS.add("https://es.investing.com/etfs/marketvectors-nuclear-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/msci-europe-financials-secto");
		LISTA_URLS.add("https://es.investing.com/etfs/msci-global-metals-mining-prod.");
		LISTA_URLS.add("https://es.investing.com/etfs/nq-clean-edge-smart-grid-infras.");
		LISTA_URLS.add("https://es.investing.com/etfs/physical-pr.-metals-basket-sh.");
		LISTA_URLS.add("https://es.investing.com/etfs/powers.-wilderhill-prog.-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh-s-p-small-cap-utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh-s-p-smallcap-financials");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh-s-p-smallcap-healthcare");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh-wilderhill-clean-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh.-dynamic-biotech---genome");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh.-dynamic-pharmaceuticals");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh.-global-gold---pr.-metals");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares---global-clean-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-active-us-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-aerospace---defense");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-cleantech-portfolio");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-db-base-metals-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-db-energy-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-db-gold-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-db-precious-metals");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-build---const.");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-energy-e-p");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-media");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-retail");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-semiconductors");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-software");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-financial-preferred");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-global-water");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-insured-ca-mun.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-insured-ny-mun.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-kbw-premium-y-eq-reit");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-s-p-smallcap-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-s-p-smallcap-info-tech");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-s-p-smallcap-materials");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-water-resource-port");
		LISTA_URLS.add("https://es.investing.com/etfs/revenueshares-financials-sector");
		LISTA_URLS.add("https://es.investing.com/etfs/rogers-intl-commodity-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/rogers-intl-commodity-metal");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-financial");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-industrials");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-materials");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/salix-pharmaceuticals-ltd.");
		LISTA_URLS.add("https://es.investing.com/etfs/schwab-intermediate-term-us-tr.");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr---consumer-staples");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr---health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-consumer-discr.-select-sector");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-dj-wilshire-intl-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-dj-wilshire-reit");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-energy-select-sector-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-gold-trust");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-kbw-bank");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-kbw-insurance");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-kbw-regional-banking");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-materials-select-sector-etf");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-ms-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-aerospace---defense");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-biotech");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-global-natural-resources");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-health-care-equipment");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-health-care-services");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-homebuilders");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-metals---mining");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-pharmaceuticals");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-retail");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-semiconductor");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-software---services");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-telecom");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-transportation");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-select-sector---technology");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-select-sector---utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-wilshire-global-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/sprott-physical-gold-trust");
		LISTA_URLS.add("https://es.investing.com/etfs/u.s.-reit");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etracs-bloomberg-cmci-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etracs-bloomberg-cmci-industr");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etracs-cmci-gold-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-consumer-discretion");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-consumer-staples");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-financials");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-global-ex-su-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-industrials");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-information-tech");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-materials");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-reit");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-telecom");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-utilities");
		LISTA_URLS.add("https://es.investing.com/etfs/wilshire-us-reit");
		LISTA_URLS.add("https://es.investing.com/etfs/wisdomtree-dividend-x-financials");
		LISTA_URLS.add("https://es.investing.com/etfs/wisdomtree-em-consumer-growth");
		LISTA_URLS.add("https://es.investing.com/etfs/wisdomtree-intl-real-estate-fund");
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
