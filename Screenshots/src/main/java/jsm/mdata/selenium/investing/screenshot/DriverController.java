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
		// -- GER
		// --
		List<String> listaURL_01 = new ArrayList<String>();
		listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-europe-energy?cid=45955");
		listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-europe-healthcare?cid=45977");
		listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-europe-industrials?cid=45982");
		listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-europe-insurance?cid=45980");
		listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-europe-utilities?cid=45993");
		listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-world-energy---eur?cid=45995");
		listaURL_01.add("https://es.investing.com/etfs/amundi-msci-europe-banks-de");
		listaURL_01.add("https://es.investing.com/etfs/amundi-msci-europe-materials?cid=46111");
		listaURL_01.add("https://es.investing.com/etfs/amundi-msci-europe-telecom-ser.?cid=46135");
		listaURL_01.add("https://es.investing.com/etfs/amundi-msci-world-financials---eur?cid=46140");
		listaURL_01.add("https://es.investing.com/etfs/amundi-real-estate-reit-ieif?cid=46112");
		listaURL_01.add("https://es.investing.com/etfs/comstage-dj-industrial-average");
		listaURL_01.add("https://es.investing.com/etfs/comstage-nyse-arca-gold-bugs");
		listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-retail-nr");
		listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-telecom-nr");
		listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-banks-nr");
		listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-media-nr");
		listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-tech-nr");
		listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-auto.--parts-nr");
		listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-basic-res.-nr");
		listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-food--bev.-nr");
		listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-health-care-nr");
		listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-ind.-goods--ser.");
		listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-travel--lei.-nr");
		listaURL_01.add("https://es.investing.com/etfs/db-ftse-nareit-dev.-euro-re-1c");
		listaURL_01.add("https://es.investing.com/etfs/db-ftse-nareit-dev.-euro-re-1c?cid=1024718");
		listaURL_01.add("https://es.investing.com/etfs/db-stoxxeuro600-basic-res.");
		listaURL_01.add("https://es.investing.com/etfs/db-stoxxeuro600-food--beverage");
		listaURL_01.add("https://es.investing.com/etfs/db-stoxxeuro600-ind.-goods");
		listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-banks");
		listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-tech");
		listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-s-p-global-infr.-uk?cid=38694");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-world-energy-dr-1c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-world-indust-dr-1c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrck-msci-wrld-infotech-dr-1c-mi?cid=1009153");
		listaURL_01.add("https://es.investing.com/etfs/deka-dax-ex-financials-30-tg?cid=998476");
		listaURL_01.add("https://es.investing.com/etfs/dj-stoxx600-health-care");
		listaURL_01.add("https://es.investing.com/etfs/dj-stoxx600-telecommunications");
		listaURL_01.add("https://es.investing.com/etfs/easyetf-stoxx-europe-600-insurance?cid=1008830");
		listaURL_01.add("https://es.investing.com/etfs/etfx-daxglobal-gold-mining-fund");
		listaURL_01.add("https://es.investing.com/etfs/euro-stoxx-optimised-banks-source");
		listaURL_01.add("https://es.investing.com/etfs/ishares-automation-robotics-acc?cid=1009418");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-industrial-average");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-auto-parts");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-banks");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-basicresources");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-financial-svs");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-food-beverage");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-health-care");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-insurance");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-media");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-us-energy-sector-fund?cid=21520");
		listaURL_01.add("https://es.investing.com/etfs/ishares-euro-stoxx-50-x-financials");
		listaURL_01.add("https://es.investing.com/etfs/ishares-euro-stoxx-banks---de");
		listaURL_01.add("https://es.investing.com/etfs/ishares-euro-stoxx-telecom---de");
		listaURL_01.add("https://es.investing.com/etfs/ishares-eurostoxx-600-real-estate");
		listaURL_01.add("https://es.investing.com/etfs/ishares-eurostoxx-600-technology");
		listaURL_01.add("https://es.investing.com/etfs/ishares-eurostoxx-600-telecom--de");
		listaURL_01.add("https://es.investing.com/etfs/ishares-eurostoxx-600-utilities");
		listaURL_01.add("https://es.investing.com/etfs/ishares-eurostoxx600-trvl---leis.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-exponential-tech-mu");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-nareit-asia-fd?cid=38604");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-nareit-dev.-markets?cid=38605");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-nareit-us-prop-fd?cid=38606");
		listaURL_01.add("https://es.investing.com/etfs/ishares-global-clean-energy?cid=45836");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-em-consumer-growth");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-global-gold-miners-be");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-target-uk-real-estate?cid=994113");
		listaURL_01.add("https://es.investing.com/etfs/ishares-nareit-european-prop.-fund?cid=38612");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-500-usd-energy?cid=1024484");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-500-usd-financials?cid=1024483");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-500-usd-health-care");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-500-usd-info-tech?cid=1024485");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-emerging-mkts-infr.?cid=46608");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-glbl-water?cid=46491");
		listaURL_01.add("https://es.investing.com/etfs/ishares-stoxx-euro-600-retail");
		listaURL_01.add("https://es.investing.com/etfs/ishares-stoxx-europe-600-chemicals");
		listaURL_01.add("https://es.investing.com/etfs/ishares-stoxx600-ind.-goods--ser.");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-dj-industrial-average?cid=46142");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-eurostoxx-banks?cid=1008868");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-europe-real-estate?cid=46182");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-usa-real-estate?cid=46188");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-consumer-disc.-tr?cid=46122");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-energy-tr?cid=46033");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-financials-tr?cid=46006");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-health-care-tr?cid=46008");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-industrials-tr?cid=46010");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-info-tech-tr?cid=46225");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-materials-tr?cid=46025");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-real-estate?cid=46031");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-telecom-ser.-tr?cid=46041");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-utilities-tr?cid=46272");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-new-energy?cid=46168");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-600-ind.-goods---ser.?cid=46153");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-banks?cid=46171");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-basic-res.?cid=46068");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-chemicals?cid=46117");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-health-care?cid=46172");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-insurance?cid=46176");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-media?cid=46181");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-retail?cid=46035");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-technology?cid=46023");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-telecom?cid=46040");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-utilities?cid=46024");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx600-per.--house.-goods?cid=46174");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-auto.---parts?cid=46104");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-food---bev.?cid=46167");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-travel--lei.?cid=46175");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-world-water?cid=46017");
		listaURL_01.add("https://es.investing.com/etfs/marketaccess-arca-gold-bugs-uk?cid=942310");
		listaURL_01.add("https://es.investing.com/etfs/robo-stox-global-robotics");
		listaURL_01.add("https://es.investing.com/etfs/robo-stox-global-robotics?cid=1052316");
		listaURL_01.add("https://es.investing.com/etfs/source-goldman-sachs-equity");
		listaURL_01.add("https://es.investing.com/etfs/source-gpr-real-estate-europe");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-consumer-discr.?cid=46218");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-consumer-staples?cid=46219");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-energy?cid=46184");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-financials?cid=46223");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-health-care?cid=46222");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-industrials?cid=46217");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-info-tech?cid=46215");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-materials?cid=46216");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-telecom-services?cid=46220");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-utilities?cid=46221");
		listaURL_01.add("https://es.investing.com/etfs/spdr-sp-us-consum-discr-sel-sect");
		listaURL_01.add("https://es.investing.com/etfs/spdr-sp-us-consum-staples-sel-sect");
		listaURL_01.add("https://es.investing.com/etfs/spdr-sp-us-energy-select-sector");
		listaURL_01.add("https://es.investing.com/etfs/spdr-sp-us-indust-select-sector");
		listaURL_01.add("https://es.investing.com/etfs/spdr-sp-us-tech-select-sector");
		listaURL_01.add("https://es.investing.com/etfs/stoxx-600-opt.-banks-source-de");
		listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-basic-res.");
		listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-chemicals");
		listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-financial-ser.");
		listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-health-care");
		listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-insurance");
		listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-media-source");
		listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-retail-source");
		listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-tech-source");
		listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-telecom-source");
		listaURL_01.add("https://es.investing.com/etfs/stoxx600-opt.-auto.--parts-source");
		listaURL_01.add("https://es.investing.com/etfs/stoxx600-opt.-food--bev.-source");
		listaURL_01.add("https://es.investing.com/etfs/stoxx600-opt.-per.--house.-goods");
		listaURL_01.add("https://es.investing.com/etfs/stoxx600-opt.-travel--lei.-sou.");
		listaURL_01.add("https://es.investing.com/etfs/ubs-(irl)-msci-emu-defensive");
		listaURL_01.add("https://es.investing.com/etfs/ubs-irl-plc-sol-glbl-gold-min-adis?cid=1024390");
		LISTA_URL_GROUP.add(new URLGroup("GER", listaURL_01));
		// --
		// -- USA
		// --
		List<String> listaURL_02 = new ArrayList<String>();
		listaURL_02.add("https://es.investing.com/etfs/advisorshrs-new-tech-media");
		listaURL_02.add("https://es.investing.com/etfs/alps-alerian-energy-infra");
		listaURL_02.add("https://es.investing.com/etfs/alps-medical-breakthroughs");
		listaURL_02.add("https://es.investing.com/etfs/alps-sprott-gold-miners");
		listaURL_02.add("https://es.investing.com/etfs/alps-sprott-junior-gold-miners");
		listaURL_02.add("https://es.investing.com/etfs/amplify-online-retail");
		listaURL_02.add("https://es.investing.com/etfs/anfield-capital-divers-alter");
		listaURL_02.add("https://es.investing.com/etfs/ark-industrial-innovation");
		listaURL_02.add("https://es.investing.com/etfs/arrow-reserve-capital-management");
		listaURL_02.add("https://es.investing.com/etfs/bioshares-biotechnology-clinical");
		listaURL_02.add("https://es.investing.com/etfs/bioshares-biotechnology-products");
		listaURL_02.add("https://es.investing.com/etfs/bluestar-ta-bigitech-israel-tech");
		listaURL_02.add("https://es.investing.com/etfs/claymore-mac-global-solar-energy-in");
		listaURL_02.add("https://es.investing.com/etfs/davis-select-financial");
		listaURL_02.add("https://es.investing.com/etfs/egshares-emerging-markets-consumer");
		listaURL_02.add("https://es.investing.com/etfs/egshares-india-consumer");
		listaURL_02.add("https://es.investing.com/etfs/egshares-india-infrastructure");
		listaURL_02.add("https://es.investing.com/etfs/etracs-1xmon.-sh.-al.-mlp-infr.-tr");
		listaURL_02.add("https://es.investing.com/etfs/etracs-alerian-mlp-infrastructure");
		listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-energy-index");
		listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-financials-index");
		listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-healthcare-index");
		listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-indust-index");
		listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-info-tech");
		listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-materials-index");
		listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-real-estate");
		listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-telecom-svcs");
		listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-utilities-index");
		listaURL_02.add("https://es.investing.com/etfs/financial-select-sector-spdr-fund");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-capital-strength");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-nasdaq-food-beverage");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-nasdaq-retail");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-nasdaq-semiconductor");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-nasdaq-transport");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-rba-american-industrial");
		listaURL_02.add("https://es.investing.com/etfs/firsttr.-nq-clean-edge-green-en.");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-amex-biotech");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-energy-alphadex-fund");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-financials-alphadex");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-health-care-alphadex");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-industrials-alphadex");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-ise-global-wind-energy");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-ise-water");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-materials-alphadex-fund");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-nasdaq-global-auto");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-north-am.-energy-infr.");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-nq-100-technology");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-nq-100-x-tech-sector");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-nq-technology-dividend");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-technology-alphadex");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-utilities-alphadex-fund");
		listaURL_02.add("https://es.investing.com/etfs/flexshares-stoxx-gbl-brd-infrs");
		listaURL_02.add("https://es.investing.com/etfs/gabelli-media-mogul-nextshares");
		listaURL_02.add("https://es.investing.com/etfs/global-x-china-consumer");
		listaURL_02.add("https://es.investing.com/etfs/global-x-china-energy");
		listaURL_02.add("https://es.investing.com/etfs/global-x-china-financials");
		listaURL_02.add("https://es.investing.com/etfs/global-x-china-industrials");
		listaURL_02.add("https://es.investing.com/etfs/global-x-china-materials");
		listaURL_02.add("https://es.investing.com/etfs/global-x-copper-miners");
		listaURL_02.add("https://es.investing.com/etfs/global-x-fintech-thematic");
		listaURL_02.add("https://es.investing.com/etfs/global-x-gold-explorers");
		listaURL_02.add("https://es.investing.com/etfs/global-x-mlp---energy-infrs");
		listaURL_02.add("https://es.investing.com/etfs/global-x-nasdaq-china-technology");
		listaURL_02.add("https://es.investing.com/etfs/global-x-robotics---ai-usd");
		listaURL_02.add("https://es.investing.com/etfs/global-x-social-media-index");
		listaURL_02.add("https://es.investing.com/etfs/global-x-us-infrastructure-dev");
		listaURL_02.add("https://es.investing.com/etfs/golden-dragon-halter-usx-china");
		listaURL_02.add("https://es.investing.com/etfs/goldman-sachs-activebeta-em");
		listaURL_02.add("https://es.investing.com/etfs/goldman-sachs-activebeta-inteqt");
		listaURL_02.add("https://es.investing.com/etfs/goldman-sachs-activebeta-japan-eqty");
		listaURL_02.add("https://es.investing.com/etfs/goldman-sachs-activebeta-us-large");
		listaURL_02.add("https://es.investing.com/etfs/goldman-sachs-equal-weight-us");
		listaURL_02.add("https://es.investing.com/etfs/goldman-sachs-hedge-industry-vip");
		listaURL_02.add("https://es.investing.com/etfs/goldman-sachs-treasuryaccess-0-1");
		listaURL_02.add("https://es.investing.com/etfs/goldmansachs-activebeta-europe-eqty");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-canada-energy-income");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-china-technology");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-defensive-equity");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-s-p-global-water");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-sp-high-income-infrastr");
		listaURL_02.add("https://es.investing.com/etfs/holdrs-merrill-lynch-pharma.");
		listaURL_02.add("https://es.investing.com/etfs/holdrs-merrill-lynch-semiconductor");
		listaURL_02.add("https://es.investing.com/etfs/industrial-sector-spdr-trust");
		listaURL_02.add("https://es.investing.com/etfs/infracap-mlp");
		listaURL_02.add("https://es.investing.com/etfs/iq-arb-global-resources-etf");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-transport-average-index");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-us-energy-sector-fund");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-aerospace---defense");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-basic-materials-index");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-broker-dealers-index");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-consumer");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-consumer-goods-index");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-financial-sector");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-financial-services");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-health-care-index");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-health-care-providers");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-home-construction");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-industrial-sector");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-insurance");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-medical-devices-index");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-pharmaceutical-index");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-regional-banks-index");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-technology");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-telecommunications");
		listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-utilities");
		listaURL_02.add("https://es.investing.com/etfs/ishares-edge-msci-mf-energy");
		listaURL_02.add("https://es.investing.com/etfs/ishares-edge-msci-mf-financials");
		listaURL_02.add("https://es.investing.com/etfs/ishares-edge-msci-mf-healthcare");
		listaURL_02.add("https://es.investing.com/etfs/ishares-edge-msci-mf-industrials");
		listaURL_02.add("https://es.investing.com/etfs/ishares-edge-msci-mf-materials");
		listaURL_02.add("https://es.investing.com/etfs/ishares-edge-msci-mf-techn");
		listaURL_02.add("https://es.investing.com/etfs/ishares-exponential-tech-mu?cid=1043100");
		listaURL_02.add("https://es.investing.com/etfs/ishares-goldman-sachs-network");
		listaURL_02.add("https://es.investing.com/etfs/ishares-goldman-sachs-software");
		listaURL_02.add("https://es.investing.com/etfs/ishares-goldman-sachs-technology");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-global-energy-prod.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-global-gold-miners-be?cid=44703");
		listaURL_02.add("https://es.investing.com/etfs/ishares-phlx-sox-semiconductor");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-clean-energy");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-energy");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-financial");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-healthcare");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-industrials");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-infrastructure");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-materials");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-technology");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-telecom.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-utilities");
		listaURL_02.add("https://es.investing.com/etfs/ivy-energy-nextshares");
		listaURL_02.add("https://es.investing.com/etfs/john-hancock-multiconsumer-staples");
		listaURL_02.add("https://es.investing.com/etfs/john-hancock-multif-indust");
		listaURL_02.add("https://es.investing.com/etfs/john-hancock-multifactor-consumer");
		listaURL_02.add("https://es.investing.com/etfs/john-hancock-multifactor-energy");
		listaURL_02.add("https://es.investing.com/etfs/john-hancock-multifactor-financials");
		listaURL_02.add("https://es.investing.com/etfs/john-hancock-multifactor-health");
		listaURL_02.add("https://es.investing.com/etfs/john-hancock-multifactor-materials");
		listaURL_02.add("https://es.investing.com/etfs/john-hancock-multifactor-technology");
		listaURL_02.add("https://es.investing.com/etfs/john-hancock-multifactor-utilities");
		listaURL_02.add("https://es.investing.com/etfs/market-vectors-biotech");
		listaURL_02.add("https://es.investing.com/etfs/market-vectors-gold-miners");
		listaURL_02.add("https://es.investing.com/etfs/market-vectors-junior-gold-miners");
		listaURL_02.add("https://es.investing.com/etfs/market-vectors-retail");
		listaURL_02.add("https://es.investing.com/etfs/marketv.-rare-earth-strat.-metals");
		listaURL_02.add("https://es.investing.com/etfs/marketvectors-global-alt.-energy");
		listaURL_02.add("https://es.investing.com/etfs/marketvectors-nuclear-energy");
		listaURL_02.add("https://es.investing.com/etfs/msci-europe-financials-secto");
		listaURL_02.add("https://es.investing.com/etfs/msci-global-metals-mining-prod.");
		listaURL_02.add("https://es.investing.com/etfs/msci-global-silver-miners-fund");
		listaURL_02.add("https://es.investing.com/etfs/nq-clean-edge-smart-grid-infras.");
		listaURL_02.add("https://es.investing.com/etfs/powers.-wilderhill-prog.-energy");
		listaURL_02.add("https://es.investing.com/etfs/powersh-s-p-small-cap-utilities");
		listaURL_02.add("https://es.investing.com/etfs/powersh-s-p-smallcap-financials");
		listaURL_02.add("https://es.investing.com/etfs/powersh-s-p-smallcap-healthcare");
		listaURL_02.add("https://es.investing.com/etfs/powersh-wilderhill-clean-energy");
		listaURL_02.add("https://es.investing.com/etfs/powersh.-dynamic-biotech---genome");
		listaURL_02.add("https://es.investing.com/etfs/powersh.-dynamic-food---beverage");
		listaURL_02.add("https://es.investing.com/etfs/powersh.-dynamic-pharmaceuticals");
		listaURL_02.add("https://es.investing.com/etfs/powersh.-global-gold---pr.-metals");
		listaURL_02.add("https://es.investing.com/etfs/powershares---global-clean-energy");
		listaURL_02.add("https://es.investing.com/etfs/powershares-aerospace---defense");
		listaURL_02.add("https://es.investing.com/etfs/powershares-cleantech-portfolio");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dwa-basic-materials-o");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dwa-consum-cyclicals-o");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dwa-consumer-staples-o");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dwa-energy-o");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dwa-financial-o");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dwa-healthcare-o");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dwa-industrials-o");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dwa-technology-o");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dwa-utilities-o");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-energy-e-p");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-leisure---ent.");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-media");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-retail");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-semiconductors");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-software");
		listaURL_02.add("https://es.investing.com/etfs/powershares-financial-preferred");
		listaURL_02.add("https://es.investing.com/etfs/powershares-global-em-infr.");
		listaURL_02.add("https://es.investing.com/etfs/powershares-global-water");
		listaURL_02.add("https://es.investing.com/etfs/powershares-s-p-smallcap-energy");
		listaURL_02.add("https://es.investing.com/etfs/powershares-s-p-smallcap-info-tech");
		listaURL_02.add("https://es.investing.com/etfs/powershares-s-p-smallcap-materials");
		listaURL_02.add("https://es.investing.com/etfs/powershares-water-resource-port");
		listaURL_02.add("https://es.investing.com/etfs/proshares-dj-brookfield-global-infr");
		listaURL_02.add("https://es.investing.com/etfs/proshares-sp-500-ex-energy");
		listaURL_02.add("https://es.investing.com/etfs/proshares-sp-500-ex-financials");
		listaURL_02.add("https://es.investing.com/etfs/proshares-sp-500-ex-health-care");
		listaURL_02.add("https://es.investing.com/etfs/proshares-sp-500-ex-technology");
		listaURL_02.add("https://es.investing.com/etfs/purefunds-video-game-tech");
		listaURL_02.add("https://es.investing.com/etfs/reaves-utilities");
		listaURL_02.add("https://es.investing.com/etfs/recon-capital-dax-germany");
		listaURL_02.add("https://es.investing.com/etfs/recon-capital-nasdaq-100-covered");
		listaURL_02.add("https://es.investing.com/etfs/revenueshares-financials-sector");
		listaURL_02.add("https://es.investing.com/etfs/rex-gold-hedged-sp-500");
		listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-energy");
		listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-financial");
		listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-health-care");
		listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-industrials");
		listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-materials");
		listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-technology");
		listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-utilities");
		listaURL_02.add("https://es.investing.com/etfs/s-p-emerging-mrkt-infrastructure");
		listaURL_02.add("https://es.investing.com/etfs/salix-pharmaceuticals-ltd.");
		listaURL_02.add("https://es.investing.com/etfs/silver-miners");
		listaURL_02.add("https://es.investing.com/etfs/spdr---consumer-staples");
		listaURL_02.add("https://es.investing.com/etfs/spdr---health-care");
		listaURL_02.add("https://es.investing.com/etfs/spdr-consumer-discr.-select-sector");
		listaURL_02.add("https://es.investing.com/etfs/spdr-dj-wilshire-intl-real-estate");
		listaURL_02.add("https://es.investing.com/etfs/spdr-energy-select-sector-fund");
		listaURL_02.add("https://es.investing.com/etfs/spdr-factset-innovative-technology");
		listaURL_02.add("https://es.investing.com/etfs/spdr-kbw-capital-markets");
		listaURL_02.add("https://es.investing.com/etfs/spdr-kbw-insurance");
		listaURL_02.add("https://es.investing.com/etfs/spdr-kbw-regional-banking");
		listaURL_02.add("https://es.investing.com/etfs/spdr-materials-select-sector-etf");
		listaURL_02.add("https://es.investing.com/etfs/spdr-ms-technology");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-aerospace---defense");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-biotech");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-global-natural-resources");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-health-care-equipment");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-health-care-services");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-homebuilders");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-metals---mining");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-pharmaceuticals");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-retail");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-semiconductor");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-software---services");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-telecom");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-transportation");
		listaURL_02.add("https://es.investing.com/etfs/spdr-select-sector---technology");
		listaURL_02.add("https://es.investing.com/etfs/spdr-select-sector---utilities");
		listaURL_02.add("https://es.investing.com/etfs/spdr-sp-technology-hardware");
		listaURL_02.add("https://es.investing.com/etfs/spdr-wilshire-global-real-estate");
		listaURL_02.add("https://es.investing.com/etfs/summit-water-infra-multif");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-consumer-discretion");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-consumer-staples");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-energy");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-financials");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-global-ex-su-real-estate");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-health-care");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-industrials");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-information-tech");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-materials");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-telecom");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-utilities");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-dividend-x-financials");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-em-consumer-growth");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-japan-hdg-capital-goods");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-japan-hdg-health-care");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-japan-hedged-financials");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-japan-hedged-real-estate");
		LISTA_URL_GROUP.add(new URLGroup("USA", listaURL_02));
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

		LOGGER.info("Buscando botonera tipo gráfico");
		List<WebElement> listaDivsQuick = driver.findElements(By.className("quick"));
		int element = 0;
		for (WebElement divQuick : listaDivsQuick)
		{
			if (element == 0)
			{
				element++;
				continue;
			}

			LOGGER.info("Seleccionando gráfico de velas");
			WebElement spanVelas = divQuick.findElement(By.tagName("span"));
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(spanVelas));
			spanVelas.click();

		}

		LOGGER.info("Esperamos 100 milisegundos");
		Thread.sleep(100);

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
