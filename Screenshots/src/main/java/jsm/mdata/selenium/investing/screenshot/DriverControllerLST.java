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
public class DriverControllerLST extends DriverControllerBase
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DriverControllerLST.class);

	/**
	 * URL Groups
	 */
	private final static List<URLGroup> LISTA_URL_GROUP = new ArrayList<URLGroup>();
	static
	{
		// --
		// -- GER
		// --
		// List<String> listaURL_01 = new ArrayList<String>();
		// listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-europe-energy?cid=45955");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-europe-healthcare?cid=45977");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-europe-industrials?cid=45982");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-europe-insurance?cid=45980");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-europe-utilities?cid=45993");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-japan---eur?cid=45983");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-world-energy---eur?cid=45995");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-msci-china-de");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-msci-east-euro-x-russia-de");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-msci-europe-banks-de");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-msci-europe-materials?cid=46111");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-msci-europe-telecom-ser.?cid=46135");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-msci-india---eur-de");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-msci-pacific-x-japan---eur?cid=46125");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-msci-world-financials---eur?cid=46140");
		// listaURL_01.add("https://es.investing.com/etfs/amundi-real-estate-reit-ieif?cid=46112");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-dj-industrial-average");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-msci-japan-trn");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-msci-pacific-x-japan-trn");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-msci-russia-30-cappedtr");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-nyse-arca-gold-bugs");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-retail-nr");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-telecom-nr");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-banks-nr");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-media-nr");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-tech-nr");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-auto.--parts-nr");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-basic-res.-nr");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-food--bev.-nr");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-health-care-nr");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-ind.-goods--ser.");
		// listaURL_01.add("https://es.investing.com/etfs/comstage-stoxx600-travel--lei.-nr");
		// listaURL_01.add("https://es.investing.com/etfs/cs-(ie)-on-msci-canada-de");
		// listaURL_01.add("https://es.investing.com/etfs/cs-(ie)-on-msci-pacific-x-japan-de");
		// listaURL_01.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-japan?cid=45843");
		// listaURL_01.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-russia?cid=45845");
		// listaURL_01.add("https://es.investing.com/etfs/db-ftse-nareit-dev.-euro-re-1c");
		// listaURL_01.add("https://es.investing.com/etfs/db-msci-russia-25-cap-uk?cid=46771");
		// listaURL_01.add("https://es.investing.com/etfs/db-stoxxeuro600-basic-res.");
		// listaURL_01.add("https://es.investing.com/etfs/db-stoxxeuro600-food--beverage");
		// listaURL_01.add("https://es.investing.com/etfs/db-stoxxeuro600-ind.-goods");
		// listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-msci-japantr---eur");
		// listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-banks");
		// listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-tech");
		// listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-ftse-xinhua-china-25?cid=45808");
		// listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-msci-canada-trn-uk?cid=46540");
		// listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-msci-china-trn-uk?cid=46543");
		// listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-msci-india-trn-uk?cid=46725");
		// listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-msci-japan-trn-etf?cid=45809");
		// listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-s-p-global-infr.-uk?cid=38694");
		// listaURL_01.add("https://es.investing.com/etfs/dj-stoxx600-health-care");
		// listaURL_01.add("https://es.investing.com/etfs/dj-stoxx600-telecommunications");
		// listaURL_01.add("https://es.investing.com/etfs/etflab-msci-china");
		// listaURL_01.add("https://es.investing.com/etfs/etflab-msci-japan");
		// listaURL_01.add("https://es.investing.com/etfs/etflab-msci-japan-lc");
		// listaURL_01.add("https://es.investing.com/etfs/etflab-msci-japan-mc");
		// listaURL_01.add("https://es.investing.com/etfs/etfx-daxglobal-gold-mining-fund");
		// listaURL_01.add("https://es.investing.com/etfs/euro-stoxx-optimised-banks-source");
		// listaURL_01.add("https://es.investing.com/etfs/hsbc-msci-japan-de");
		// listaURL_01.add("https://es.investing.com/etfs/hsbc-msci-pacific-x-japan-de");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-dj-china-offshore-50---de");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-dj-industrial-average");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-auto-parts");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-banks");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-basicresources");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-financial-svs");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-food-beverage");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-health-care");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-insurance");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-dj-stoxx600-media");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-dj-us-energy-sector-fund?cid=21520");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-dj-us-energy-sector-fund?cid=38200");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-euro-stoxx-banks---de");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-euro-stoxx-telecom---de");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-eurostoxx-600-real-estate");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-eurostoxx-600-technology");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-eurostoxx-600-telecom--de");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-eurostoxx-600-utilities");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-eurostoxx600-trvl---leis.");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-nareit-asia-fd?cid=38604");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-nareit-dev.-markets?cid=38605");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-nareit-us-prop-fd?cid=38606");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-xinhua-china25?cid=45780");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-global-clean-energy?cid=45836");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-msci-ac-far-east-x-japan?cid=38609");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-msci-japan---acc?cid=46059");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-msci-japan---ie?cid=45811");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-msci-japan-mon.-euro-hed.?cid=46741");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-msci-japan-small-cap");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-msci-pacific-ex-japan-uk?cid=45777");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-nareit-european-prop.-fund?cid=38612");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-emerging-mkts-infr.?cid=46608");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-glbl-water?cid=46491");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-stoxx-euro-600-retail");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-stoxx-europe-600-chemicals");
		// listaURL_01.add("https://es.investing.com/etfs/ishares-stoxx600-ind.-goods--ser.");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-china-enterprise-(hscei)?cid=45807");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-dj-industrial-average?cid=46142");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-japan---topix?cid=46011");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-japan-(topix)-daily-hedged-mi?cid=962222");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-ac-asia-pacific-x-japan?cid=45950");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-europe-real-estate?cid=46182");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-india?cid=46155");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-usa-real-estate?cid=46188");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-consumer-disc.-tr?cid=46122");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-energy-tr?cid=46033");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-financials-tr?cid=46006");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-health-care-tr?cid=46008");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-industrials-tr?cid=46010");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-info-tech-tr?cid=46225");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-materials-tr?cid=46025");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-real-estate?cid=46031");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-telecom-ser.-tr?cid=46041");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-utilities-tr?cid=46272");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-new-energy?cid=46168");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-russia---dj-russia-gdr?cid=46163");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-600-ind.-goods---ser.?cid=46153");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-banks?cid=46171");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-basic-res.?cid=46068");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-chemicals?cid=46117");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-health-care?cid=46172");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-insurance?cid=46176");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-media?cid=46181");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-retail?cid=46035");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-technology?cid=46023");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-telecom?cid=46040");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-utilities?cid=46024");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxx600-per.--house.-goods?cid=46174");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-auto.---parts?cid=46104");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-food---bev.?cid=46167");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-travel--lei.?cid=46175");
		// listaURL_01.add("https://es.investing.com/etfs/lyxor-world-water?cid=46017");
		// listaURL_01.add("https://es.investing.com/etfs/marketaccess-arca-gold-bugs-uk?cid=942310");
		// listaURL_01.add("https://es.investing.com/etfs/msci-japan-source?cid=45891");
		// listaURL_01.add("https://es.investing.com/etfs/msci-pacific-x-japan-trn-%C2%A3-uk?cid=46403");
		// listaURL_01.add("https://es.investing.com/etfs/rbs-market-access-daxglobal-russia");
		// listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-consumer-discr.?cid=46218");
		// listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-consumer-staples?cid=46219");
		// listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-energy?cid=46184");
		// listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-financials?cid=46223");
		// listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-health-care?cid=46222");
		// listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-industrials?cid=46217");
		// listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-info-tech?cid=46215");
		// listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-materials?cid=46216");
		// listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-telecom-services?cid=46220");
		// listaURL_01.add("https://es.investing.com/etfs/spdr-msci-europe-utilities?cid=46221");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx-600-opt.-banks-source-de");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-basic-res.");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-chemicals");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-financial-ser.");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-health-care");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-insurance");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-media-source");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-retail-source");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-tech-source");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx-600-optimised-telecom-source");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx600-opt.-auto.--parts-source");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx600-opt.-food--bev.-source");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx600-opt.-per.--house.-goods");
		// listaURL_01.add("https://es.investing.com/etfs/stoxx600-opt.-travel--lei.-sou.");
		// listaURL_01.add("https://es.investing.com/etfs/ubs-etf-msci-canada-de");
		// listaURL_01.add("https://es.investing.com/etfs/ubs-etf-msci-japan-a");
		// listaURL_01.add("https://es.investing.com/etfs/ubs-etf-msci-pacific-x-japan-de");
		// listaURL_01.add("https://es.investing.com/etfs/ubss-plc--msci-canadatr-sf-(cad)-a?cid=45919");
		// LISTA_URL_GROUP.add(new URLGroup("GER", listaURL_01));
		// --
		// -- USA
		// --
		// List<String> listaURL_02 = new ArrayList<String>();
		// listaURL_02.add("https://es.investing.com/etfs/alps-alerian-energy-infra");
		// listaURL_02.add("https://es.investing.com/etfs/claymore-mac-global-solar-energy-in");
		// listaURL_02.add("https://es.investing.com/etfs/db-x-msci-japan-curr.-hedged-eq");
		// listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-harvest-china");
		// listaURL_02.add("https://es.investing.com/etfs/egshares-emerging-markets-consumer");
		// listaURL_02.add("https://es.investing.com/etfs/egshares-india-consumer");
		// listaURL_02.add("https://es.investing.com/etfs/egshares-india-infrastructure");
		// listaURL_02.add("https://es.investing.com/etfs/egshares-india-small-cap");
		// listaURL_02.add("https://es.investing.com/etfs/etracs-1xmon.-sh.-al.-mlp-infr.-tr");
		// listaURL_02.add("https://es.investing.com/etfs/etracs-alerian-mlp-infrastructure");
		// listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-energy-index");
		// listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-financials-index");
		// listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-healthcare-index");
		// listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-indust-index");
		// listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-info-tech");
		// listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-materials-index");
		// listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-telecom-svcs");
		// listaURL_02.add("https://es.investing.com/etfs/fidelity-msci-utilities-index");
		// listaURL_02.add("https://es.investing.com/etfs/financial-select-sector-spdr-fund");
		// listaURL_02.add("https://es.investing.com/etfs/first-trust-capital-strength");
		// listaURL_02.add("https://es.investing.com/etfs/firsttr.-nq-clean-edge-green-en.");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-amex-biotech");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-canada-alphadex-fund");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-china-alphadex-fund");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-energy-alphadex-fund");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-financials-alphadex");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-health-care-alphadex");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-industrials-alphadex");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-ise-chindia");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-ise-global-wind-energy");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-ise-water");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-japan-alphadex-fund");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-materials-alphadex-fund");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-nasdaq-global-auto");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-north-am.-energy-infr.");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-nq-100-technology");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-nq-100-x-tech-sector");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-nq-technology-dividend");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-technology-alphadex");
		// listaURL_02.add("https://es.investing.com/etfs/firsttrust-utilities-alphadex-fund");
		// listaURL_02.add("https://es.investing.com/etfs/flexshares-stoxx-gbl-brd-infrs");
		// listaURL_02.add("https://es.investing.com/etfs/global-x-china-consumer");
		// listaURL_02.add("https://es.investing.com/etfs/global-x-china-energy");
		// listaURL_02.add("https://es.investing.com/etfs/global-x-china-financials");
		// listaURL_02.add("https://es.investing.com/etfs/global-x-china-industrials");
		// listaURL_02.add("https://es.investing.com/etfs/global-x-china-materials");
		// listaURL_02.add("https://es.investing.com/etfs/global-x-copper-miners");
		// listaURL_02.add("https://es.investing.com/etfs/global-x-ftse-norway-30");
		// listaURL_02.add("https://es.investing.com/etfs/global-x-gold-explorers");
		// listaURL_02.add("https://es.investing.com/etfs/global-x-mlp---energy-infrs");
		// listaURL_02.add("https://es.investing.com/etfs/global-x-nasdaq-china-technology");
		// listaURL_02.add("https://es.investing.com/etfs/global-x-social-media-index");
		// listaURL_02.add("https://es.investing.com/etfs/golden-dragon-halter-usx-china");
		// listaURL_02.add("https://es.investing.com/etfs/guggenheim-canada-energy-income");
		// listaURL_02.add("https://es.investing.com/etfs/guggenheim-china-all-cap");
		// listaURL_02.add("https://es.investing.com/etfs/guggenheim-china-smlcap");
		// listaURL_02.add("https://es.investing.com/etfs/guggenheim-china-technology");
		// listaURL_02.add("https://es.investing.com/etfs/guggenheim-defensive-equity");
		// listaURL_02.add("https://es.investing.com/etfs/guggenheim-s-p-global-water");
		// listaURL_02.add("https://es.investing.com/etfs/holdrs-merrill-lynch-pharma.");
		// listaURL_02.add("https://es.investing.com/etfs/holdrs-merrill-lynch-semiconductor");
		// listaURL_02.add("https://es.investing.com/etfs/industrial-sector-spdr-trust");
		// listaURL_02.add("https://es.investing.com/etfs/ipath-msci-india-index-etn");
		// listaURL_02.add("https://es.investing.com/etfs/iq-arb-global-resources-etf");
		// listaURL_02.add("https://es.investing.com/etfs/iq-canada-small-cap");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-dj-transport-average-index");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-dj-us-energy-sector-fund");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-aerospace---defense");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-basic-materials-index");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-broker-dealers-index");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-consumer");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-consumer-goods-index");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-financial-sector");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-financial-services");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-health-care-index");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-health-care-providers");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-home-construction");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-industrial-sector");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-insurance");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-medical-devices-index");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-pharmaceutical-index");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-regional-banks-index");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-technology");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-telecommunications");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-djsu-utilities");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-ftse-xinhua-china-25");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-goldman-sachs-network");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-goldman-sachs-software");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-goldman-sachs-technology");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-canada");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-china");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-china-small-cap");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-denmark-cap-inv.-mrkt");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-finland-cap-inv.-mrkt");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-global-energy-prod.");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-global-gold-miners-be?cid=44703");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-india");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-india-small-cap");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-japan");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-norway-cap-inv.-mrkt");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-russia-capped-index");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-msci-sweden");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-phlx-sox-semiconductor");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-clean-energy");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-energy");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-financial");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-healthcare");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-industrials");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-infrastructure");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-materials");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-technology");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-telecom.");
		// listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-utilities");
		// listaURL_02.add("https://es.investing.com/etfs/kraneshares-csi-china-5-y-plan");
		// listaURL_02.add("https://es.investing.com/etfs/kraneshares-csi-china-internet");
		// listaURL_02.add("https://es.investing.com/etfs/market-vectors-biotech");
		// listaURL_02.add("https://es.investing.com/etfs/market-vectors-gold-miners");
		// listaURL_02.add("https://es.investing.com/etfs/market-vectors-junior-gold-miners");
		// listaURL_02.add("https://es.investing.com/etfs/market-vectors-retail");
		// listaURL_02.add("https://es.investing.com/etfs/marketv.-rare-earth-strat.-metals");
		// listaURL_02.add("https://es.investing.com/etfs/marketvectors-china");
		// listaURL_02.add("https://es.investing.com/etfs/marketvectors-global-alt.-energy");
		// listaURL_02.add("https://es.investing.com/etfs/marketvectors-india-small-cap");
		// listaURL_02.add("https://es.investing.com/etfs/marketvectors-nuclear-energy");
		// listaURL_02.add("https://es.investing.com/etfs/marketvectors-russia-small-cap");
		// listaURL_02.add("https://es.investing.com/etfs/marketvectors-tr-russia");
		// listaURL_02.add("https://es.investing.com/etfs/msci-europe-financials-secto");
		// listaURL_02.add("https://es.investing.com/etfs/msci-global-metals-mining-prod.");
		// listaURL_02.add("https://es.investing.com/etfs/msci-global-silver-miners-fund");
		// listaURL_02.add("https://es.investing.com/etfs/msci-japan-small-cap");
		// listaURL_02.add("https://es.investing.com/etfs/nq-clean-edge-smart-grid-infras.");
		// listaURL_02.add("https://es.investing.com/etfs/powers.-wilderhill-prog.-energy");
		// listaURL_02.add("https://es.investing.com/etfs/powersh-s-p-small-cap-utilities");
		// listaURL_02.add("https://es.investing.com/etfs/powersh-s-p-smallcap-financials");
		// listaURL_02.add("https://es.investing.com/etfs/powersh-s-p-smallcap-healthcare");
		// listaURL_02.add("https://es.investing.com/etfs/powersh-wilderhill-clean-energy");
		// listaURL_02.add("https://es.investing.com/etfs/powersh.-dynamic-biotech---genome");
		// listaURL_02.add("https://es.investing.com/etfs/powersh.-dynamic-food---beverage");
		// listaURL_02.add("https://es.investing.com/etfs/powersh.-dynamic-pharmaceuticals");
		// listaURL_02.add("https://es.investing.com/etfs/powersh.-global-gold---pr.-metals");
		// listaURL_02.add("https://es.investing.com/etfs/powersh.-rafi-asia-pacific-x-japan");
		// listaURL_02.add("https://es.investing.com/etfs/powershares---global-clean-energy");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-aerospace---defense");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-cleantech-portfolio");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-energy-e-p");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-leisure---ent.");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-media");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-retail");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-semiconductors");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-software");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-financial-preferred");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-global-em-infr.");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-global-water");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-india-portfolio");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-s-p-smallcap-energy");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-s-p-smallcap-info-tech");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-s-p-smallcap-materials");
		// listaURL_02.add("https://es.investing.com/etfs/powershares-water-resource-port");
		// listaURL_02.add("https://es.investing.com/etfs/recon-capital-nasdaq-100-covered");
		// listaURL_02.add("https://es.investing.com/etfs/revenueshares-financials-sector");
		// listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-energy");
		// listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-financial");
		// listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-health-care");
		// listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-industrials");
		// listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-materials");
		// listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-technology");
		// listaURL_02.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-utilities");
		// listaURL_02.add("https://es.investing.com/etfs/s-p-emerging-mrkt-infrastructure");
		// listaURL_02.add("https://es.investing.com/etfs/s-p-india-nifty-fifty");
		// listaURL_02.add("https://es.investing.com/etfs/salix-pharmaceuticals-ltd.");
		// listaURL_02.add("https://es.investing.com/etfs/silver-miners");
		// listaURL_02.add("https://es.investing.com/etfs/spdr---consumer-staples");
		// listaURL_02.add("https://es.investing.com/etfs/spdr---health-care");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-consumer-discr.-select-sector");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-dj-wilshire-intl-real-estate");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-energy-select-sector-fund");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-kbw-capital-markets");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-kbw-insurance");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-kbw-regional-banking");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-materials-select-sector-etf");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-ms-technology");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-aerospace---defense");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-biotech");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-china");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-global-natural-resources");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-health-care-equipment");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-health-care-services");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-homebuilders");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-metals---mining");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-pharmaceuticals");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-retail");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-semiconductor");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-software---services");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-telecom");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-transportation");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-select-sector---technology");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-select-sector---utilities");
		// listaURL_02.add("https://es.investing.com/etfs/spdr-wilshire-global-real-estate");
		// listaURL_02.add("https://es.investing.com/etfs/vanguard-consumer-discretion");
		// listaURL_02.add("https://es.investing.com/etfs/vanguard-consumer-staples");
		// listaURL_02.add("https://es.investing.com/etfs/vanguard-energy");
		// listaURL_02.add("https://es.investing.com/etfs/vanguard-financials");
		// listaURL_02.add("https://es.investing.com/etfs/vanguard-global-ex-su-real-estate");
		// listaURL_02.add("https://es.investing.com/etfs/vanguard-health-care");
		// listaURL_02.add("https://es.investing.com/etfs/vanguard-industrials");
		// listaURL_02.add("https://es.investing.com/etfs/vanguard-information-tech");
		// listaURL_02.add("https://es.investing.com/etfs/vanguard-materials");
		// listaURL_02.add("https://es.investing.com/etfs/vanguard-telecom");
		// listaURL_02.add("https://es.investing.com/etfs/vanguard-utilities");
		// listaURL_02.add("https://es.investing.com/etfs/wisdomtree-asia-pacific-x-japan");
		// listaURL_02.add("https://es.investing.com/etfs/wisdomtree-china-div-ex-fincls");
		// listaURL_02.add("https://es.investing.com/etfs/wisdomtree-dividend-x-financials");
		// listaURL_02.add("https://es.investing.com/etfs/wisdomtree-em-consumer-growth");
		// listaURL_02.add("https://es.investing.com/etfs/wisdomtree-india-earnings-fund");
		// listaURL_02.add("https://es.investing.com/etfs/wisdomtree-japan-smallcap-fund");
		// listaURL_02.add("https://es.investing.com/etfs/wisdomtree-japan-total-dividend");
		// LISTA_URL_GROUP.add(new URLGroup("USA", listaURL_02));
		// --
		// -- GER-WORLD
		// --
		List<String> listaURL_03 = new ArrayList<String>();
		listaURL_03.add("https://es.investing.com/etfs/amundi-etf-msci-emerging-markets?cid=45949");
		listaURL_03.add("https://es.investing.com/etfs/amundi-etf-msci-world-energy---eur?cid=45995");
		listaURL_03.add("https://es.investing.com/etfs/amundi-etf-msci-world-x-emu?cid=45985");
		listaURL_03.add("https://es.investing.com/etfs/amundi-msci-world-x-europe---eur?cid=46114");
		listaURL_03.add("https://es.investing.com/etfs/amundi-msci-world-financials---eur");
		listaURL_03.add("https://es.investing.com/etfs/amundi-msci-world-de");
		listaURL_03.add("https://es.investing.com/etfs/comstage-alpha-dividende-plus");
		listaURL_03.add("https://es.investing.com/etfs/comstage-msci-emerging-markets-trn");
		listaURL_03.add("https://es.investing.com/etfs/comstage-msci-world-trn");
		listaURL_03.add("https://es.investing.com/etfs/comstage-s-p-smit-40-trn?cid=1024623");
		listaURL_03.add("https://es.investing.com/etfs/db-x-trackers-msci-ac-world-index");
		listaURL_03.add("https://es.investing.com/etfs/db-x-trackers-msci-em-emea-trn");
		listaURL_03.add("https://es.investing.com/etfs/db-x-trackers-msci-em-trn-idx-etf?cid=45821");
		listaURL_03.add("https://es.investing.com/etfs/db-x-trackers-msci-wrd-hlth-care-de");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrck-msci-world-4c");
		listaURL_03.add("https://es.investing.com/etfs/db-xtrack-msci-wrld-cons-disc-1c?cid=1024387");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrack-msci-wrld-consstpl-dr-1c");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrack-msci-world-dr-1d");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrack-msci-world-energy-dr-1c");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrack-msci-world-finls-dr-1c");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrack-msci-world-indust-dr-1c");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrck-msci-wrld-infotech-dr-1c-mi?cid=1009153");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrack-msci-world-mats-dr-1c");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrack-msci-world-minvol-dr-1c");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrck-msci-world-mom-fact-dr1c");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrack-msci-world-qlt-fct-dr-1c");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrack-msci-wrld-tlcom-srvc-dr-1c");
		listaURL_03.add("https://es.investing.com/etfs/db-xtrackers-msci-world-trn-uk?cid=46409");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrack-msci-world-util-dr-1c");
		listaURL_03.add("https://es.investing.com/etfs/dbxtrack-msci-world-val-fct-dr-1c");
		listaURL_03.add("https://es.investing.com/etfs/db-x-trackers-portfolio-tr-index?cid=1056533");
		listaURL_03.add("https://es.investing.com/etfs/db-xtrackers-s-p-global-infr.-uk?cid=38694");
		listaURL_03.add("https://es.investing.com/etfs/db-xtrackers-s-p-select-frontier");
		listaURL_03.add("https://es.investing.com/etfs/db-xtrackers-stoxx-glbl-div-100?cid=46096");
		listaURL_03.add("https://es.investing.com/etfs/etflab-msci-emerging-markets");
		listaURL_03.add("https://es.investing.com/etfs/etfx-daxglobal-gold-mining-fund");
		listaURL_03.add("https://es.investing.com/etfs/etfs-ise-cyber-sec-go-accum");
		listaURL_03.add("https://es.investing.com/etfs/fundlogic-alter-plc-ms-scibeta-glbl");
		listaURL_03.add("https://es.investing.com/etfs/hsbc-eco-scale-worldwide-eq-ucits");
		listaURL_03.add("https://es.investing.com/etfs/hsbc-msci-world");
		listaURL_03.add("https://es.investing.com/etfs/hsbc-multifactor-worldwide-eq-ucits");
		listaURL_03.add("https://es.investing.com/etfs/ishares-ageing-population-acc?cid=1037050");
		listaURL_03.add("https://es.investing.com/etfs/ishares-automation-robotics-acc?cid=1009408");
		listaURL_03.add("https://es.investing.com/etfs/ishares-ftse-birc-50---ie?cid=38607");
		listaURL_03.add("https://es.investing.com/etfs/ishares-core-msci-em-imi?cid=994133");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-world---acc?cid=45873");
		listaURL_03.add("https://es.investing.com/etfs/ishares-ftse-nareit-dev.-markets?cid=38605");
		listaURL_03.add("https://es.investing.com/etfs/ishares-digitalisation-acc?cid=1080073");
		listaURL_03.add("https://es.investing.com/etfs/ishares-dj-global-sust-screened-uk?cid=38617");
		listaURL_03.add("https://es.investing.com/etfs/ishares-dj-global-titans-50");
		listaURL_03.add("https://es.investing.com/etfs/ishares-edge-msci-world-mf-gbp?cid=994123");
		listaURL_03.add("https://es.investing.com/etfs/ishares-edge-msci-world-min-vol");
		listaURL_03.add("https://es.investing.com/etfs/ishrs-edge-msci-wrld-minvol-hdg-acc");
		listaURL_03.add("https://es.investing.com/etfs/ishares-edge-msci-wrld-multihdg-acc");
		listaURL_03.add("https://es.investing.com/etfs/ishares-dj-em-select-dividend-%C2%A3?cid=46600");
		listaURL_03.add("https://es.investing.com/etfs/ishares-s-p-emerging-mkts-infr.?cid=46608");
		listaURL_03.add("https://es.investing.com/etfs/ishares-exponential-tech-mu");
		listaURL_03.add("https://es.investing.com/etfs/ishares-global-clean-energy?cid=45836");
		listaURL_03.add("https://es.investing.com/etfs/ishares-macquarie-global-inf.-100?cid=38611");
		listaURL_03.add("https://es.investing.com/etfs/ishares-s-p-glbl-water?cid=46491");
		listaURL_03.add("https://es.investing.com/etfs/ishares-hc-innov-acc-mi?cid=1009026");
		listaURL_03.add("https://es.investing.com/etfs/ishares-s-p-listed-private-eqty?cid=46492");
		listaURL_03.add("https://es.investing.com/etfs/ishares-v-msci-acwi?cid=46483");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-em-consumer-growth");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-em-minimum-volatility?cid=994135");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-em-smlcap-$?cid=46606");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-emerging-mrkt---acc?cid=46604");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-emerging-market?cid=46607");
		listaURL_03.add("https://es.investing.com/etfs/cs-etf(ie)on-cs-gbl-altrn.engy");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-global-gold-miners-be");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-world-mon.-hedged?cid=46487");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-world-momentum-factor");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-wrld-quality-factor");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-wrld-size-factor");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-world---gbp?cid=38614");
		listaURL_03.add("https://es.investing.com/etfs/ishares-msci-wrld-value-factor");
		listaURL_03.add("https://es.investing.com/etfs/ishares-s-p-comm.-prod.-oil-gas");
		listaURL_03.add("https://es.investing.com/etfs/ishares-s-p-commodity-prod.-gold");
		listaURL_03.add("https://es.investing.com/etfs/ishares-stoxx-global-sel-div.-100");
		listaURL_03.add("https://es.investing.com/etfs/ishares-sust-msci-em-sri");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world-consumer-disc.-tr?cid=46122");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world-cons.-st.-tr?cid=45990");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world-info-tech-tr?cid=46225");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world-real-estate?cid=46031");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-all-country-world-de");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-emerging-markets?cid=46020");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world?cid=46273");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world-energy-tr?cid=996756");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world-financials-tr?cid=46006");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world-health-care-tr?cid=46008");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world-industrials-tr?cid=46010");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world-materials-tr?cid=46025");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world-telecom-ser.-tr?cid=46041");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world-utilities-tr?cid=46272");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-new-energy?cid=46168");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-privex?cid=46192");
		listaURL_03.add("https://es.investing.com/etfs/lyxor-world-water?cid=46017");
		listaURL_03.add("https://es.investing.com/etfs/rbs-market-access-dax-global-birc");
		listaURL_03.add("https://es.investing.com/etfs/ossiam-em-min-variance-nr-(eur)-de");
		listaURL_03.add("https://es.investing.com/etfs/powershares-dynamic-europe-fund?cid=45898");
		listaURL_03.add("https://es.investing.com/etfs/robo-stox-global-robotics");
		listaURL_03.add("https://es.investing.com/etfs/msci-emerging-mrktssource-de");
		listaURL_03.add("https://es.investing.com/etfs/msci-world-source?cid=46070");
		listaURL_03.add("https://es.investing.com/etfs/spdr-msci-acwi-imi-de");
		listaURL_03.add("https://es.investing.com/etfs/spdr-msci-world-small-cap?cid=1024392");
		listaURL_03.add("https://es.investing.com/etfs/spdr-s-p-global-dividend-aristocrat?cid=1024489");
		listaURL_03.add("https://es.investing.com/etfs/spdr-msci-acwi?cid=46046");
		listaURL_03.add("https://es.investing.com/etfs/spdr-msci-em-smlcap?cid=45851");
		listaURL_03.add("https://es.investing.com/etfs/spdr-msci-emerging-markets?cid=45850");
		listaURL_03.add("https://es.investing.com/etfs/spdr-s-p-emerging-mrktsdividend-uk?cid=45833");
		listaURL_03.add("https://es.investing.com/etfs/ubs-(irl)-dj-global-select-dividend?cid=949270");
		listaURL_03.add("https://es.investing.com/etfs/ubs-plc-msci-world-(usd)-a-de");
		listaURL_03.add("https://es.investing.com/etfs/ubs-msci-world-socially-resp.-a-de");
		listaURL_03.add("https://es.investing.com/etfs/ubs-etf-msci-world-a?cid=1024717");
		listaURL_03.add("https://es.investing.com/etfs/ubs-irl-plc-solactive-glbl-oil-adis?cid=1024391");
		listaURL_03.add("https://es.investing.com/etfs/ubs-irl-plc-sol-glbl-gold-min-adis?cid=1024390");
		listaURL_03.add("https://es.investing.com/etfs/ubs-msci-em-socially-resp-adis");
		listaURL_03.add("https://es.investing.com/etfs/ubs-etf-msci-emerging-markets-a");
		listaURL_03.add("https://es.investing.com/etfs/wisdomtree-em-equity-income-de");
		listaURL_03.add("https://es.investing.com/etfs/wisdomtree-em-smallcap-de");
		listaURL_03.add("https://es.investing.com/etfs/wisdomtree-em-equity-incacc");
		LISTA_URL_GROUP.add(new URLGroup("GER-WORLD", listaURL_03));
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
					procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_MENSUAL, TF_MENSUAL);
					// procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_SEMANAL, TF_SEMANAL);
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
