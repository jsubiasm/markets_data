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
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-tecdax-(de)-de");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-lpx-mm-private-eq");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-nasdaq-100");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-physical-palladium-de");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-eqqq?cid=45852");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-nasdaq-100?cid=46271");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nasdaq-100");
		LISTA_URLS.add("https://es.investing.com/etfs/msci-europe-smlcap-trn-index");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxxeuro-600-chem.-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-dj-industrial-average");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-tech-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-stoxxeuro600-food--beverage");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-tech");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxx600-ind.-goods--ser.");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-food--bev.-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-chemicals?cid=46117");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-nasdaq-100---eur?cid=45825");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-(ie)-on-nasdaq-100?cid=46049");
		LISTA_URLS.add("https://es.investing.com/etfs/rbs-market-access-dax-global-asia");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-msci-taiwan-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-food---bev.?cid=46167");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-dj-industrial-average?cid=46142");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-timber-forestry-gbp?cid=46090");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-industrial-average");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-world-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-msci-world-trn-uk?cid=46409");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-technology?cid=46023");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-msci-em-asia-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/msci-europe-mid-cap-trn-index");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-info-tech-tr?cid=46225");
		LISTA_URLS.add("https://es.investing.com/etfs/db-msci-asia-x-jap.-tr-uk?cid=46398");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-s-p-cnx-nifty-uk?cid=37482");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-glbl-water?cid=46491");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-taiwan?cid=46170");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-ac-asia-pacific-x-japan?cid=45950");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-asia-apex-50?cid=45956");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-nikkei-225");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxx-europe-mid-200");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-ac-far-east-x-japan?cid=38609");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-mid-200-source");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etf-msci-world-a");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-europe-mid-cap-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-world---gbp?cid=38614");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world?cid=46273");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-chemicals");
		LISTA_URLS.add("https://es.investing.com/etfs/msci-world-source?cid=46070");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-msci-em-trn-idx-etf?cid=45821");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-tech-source");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx600-opt.-food--bev.-source");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-(ie)-dow-jones-ind.-average?cid=46048");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-(ie)-on-msci-emu-smlcap-uk?cid=38651");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nikkei-225");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-emerging-markets?cid=46020");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-info-tech?cid=46215");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-europe-insurance?cid=45980");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-small-cap?cid=46208");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-europe-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-world---acc?cid=45873");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-europe-large-cap-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-pacific-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-industrials-tr?cid=46010");
		LISTA_URLS.add("https://es.investing.com/etfs/msci-europe-source");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-world-x-emu?cid=45985");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-msci-china-trn-uk?cid=46543");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-topix");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-acwi-imi-de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-world-islamic---gbp?cid=46486");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-msci-europe-mc");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-acwi?cid=46046");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-(ie)-on-nikkei-225?cid=46050");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p500-monthly-euro-hedged?cid=46822");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-msci-n.-amrca-soc.-resp.-a-de");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-msci-pan-euro-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-global-sust-screened-uk?cid=38617");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-world-mon.-hedged?cid=46487");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-msci-thailand-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-europe?cid=45975");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe?cid=46001");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-em-asia-de");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-em-asia?cid=45840");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-msci-world-socially-resp.-a-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-msci-japan");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-(ie)-on-ftse-100-de");
		LISTA_URLS.add("https://es.investing.com/etfs/hsbc-msci-em-far-east-de");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-msci-em-asia-de");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-materials?cid=46216");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-msci-europe-materials?cid=46111");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-emerging-mrkt---acc?cid=46604");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-emerging-markets?cid=45949");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-emerging-markets?cid=45850");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-materials-tr?cid=46025");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-europe-utilities?cid=45993");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-emerging-markets-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-europe-energy?cid=45955");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-msci-emerging-markets");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-energy?cid=46184");
		LISTA_URLS.add("https://es.investing.com/etfs/msci-emerging-mrktssource-de");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-3-5-tr-1c");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-euro-corporate-bond?cid=46127");
		LISTA_URLS.add("https://es.investing.com/etfs/db-%E2%82%AC-liq.-corp-100-fin.-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-euro-corp-fina.-iboxx-de");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-liquid-corp-100-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-gov.-bond-3-5-it?cid=37558");
		LISTA_URLS.add("https://es.investing.com/etfs/db-%E2%82%AC-liq.-corp-100-non-fin.-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-ii-europe-5-year-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-%E2%82%AC-government-bond-1-3?cid=38600");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-corp.-bond-1-5?cid=46465");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-iboxx-eur-govt-1-3?cid=45788");
		LISTA_URLS.add("https://es.investing.com/etfs/db-stoxx-europe-600-uk?cid=46414");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-euro-stoxx-midcap?cid=46473");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-portfolio-tr-index");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-europe-(acc)-uk?cid=46060");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-euro-corporates-de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-euro-stoxx-growth?cid=38610");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-ii-crossover-5-y-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etf-msci-emu-small-cap-a-de");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-msci-europe-trn?cid=45815");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-europe-600-source");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-stoxx-europe-600?cid=45967");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-(ie)-on-msci-uk-gbp-de");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-euro-corp-bond-x-financials?cid=46120");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-us-mrkt-it?cid=45899");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-japan---ie?cid=45811");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-3-5-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-europe-small-cap-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-msci-japan-lc");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-japan?cid=45843");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-gov-bond-mts-invest-3-5?cid=45961");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-msci-japan-trn-etf?cid=45809");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-mdax-(de)-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-dax-(preisindex)");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-japan-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-japan---topix?cid=46011");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-small-200-source");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-dax");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx600-opt.-ind.-good--ser.-sou.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dax-de");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-dax-pa?cid=46694");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-600-ind.-goods---ser.?cid=46153");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-japan-small-cap");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-dax");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-dax-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/dbx-s-p-500-class-1-uk?cid=38570");
		LISTA_URLS.add("https://es.investing.com/etfs/db-stoxxeuro600-ind.-goods");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-stoxx-europe-strong-gr.-20");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-taiwan-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-msci-world-x-europe---eur?cid=46114");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-msci-germany-de");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-europe-industrials?cid=45982");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-ind.-goods--ser.");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-stoxx-strong-style-comp.-40");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-msci-usa-trn-index?cid=45827");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxx-europe-small-200");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-ftse-rafi-eu-mid-small?cid=45893");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-industrials?cid=46217");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etf-msci-emu-de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-euro-stoxx-smallcap?cid=46475");
		LISTA_URLS.add("https://es.investing.com/etfs/rbs-topix-eur-hedged-index-de");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-s-p-500---eur-de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-divdax");
		LISTA_URLS.add("https://es.investing.com/etfs/s-p-500-source");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-msci-usa-de");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-s-p-500?cid=45844");
		LISTA_URLS.add("https://es.investing.com/etfs/db-euro-stoxx-50-1c-mc?cid=46383");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-divdax-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-usa-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxxeuro-600-insu.-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-korea?cid=46156");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-financials-tr?cid=46006");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-msci-korea-trn-uk?cid=37485");
		LISTA_URLS.add("https://es.investing.com/etfs/msci-usa-source");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-north-america-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-s-p-500");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-usa?cid=45846");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-euro-stoxx-50-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-fin.-ser.-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-korea-gbp?cid=45779");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-msci-europe-consumer-discr.?cid=46218");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-euro-600-fin.-ser.?cid=46148");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-msci-world-financials---eur?cid=46140");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-food-beverage");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-sdax-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-financial-ser.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-emerging-market?cid=46607");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-india?cid=46155");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-consumer-disc.-tr?cid=46122");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-insurance?cid=46176");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-insurance");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-taiwan-gbp?cid=45775");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-stoxx-euro-strong-value-20");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-europe-ex-uk?cid=44523");
		LISTA_URLS.add("https://es.investing.com/etfs/hsbc-s-p-500-de");
		LISTA_URLS.add("https://es.investing.com/etfs/hsbc-msci-usa-de");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etf-msci-usa");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-usa?cid=46270");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-uk-small-cap");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-msci-usa-lc");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-s-p-500?cid=46039");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx600-opt.-auto.--parts-source");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-global-titans-50");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-etf-msci-europe-cons.-disc?cid=45971");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-eurostoxx-div.-30-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxx-europe-600-chemicals");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-s-p-global-infr.-uk?cid=38694");
		LISTA_URLS.add("https://es.investing.com/etfs/msci-pacific-x-japan-trn-%C2%A3-uk?cid=46403");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-msci-usa");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-atx");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx600-opt.-travel--lei.-sou.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-north-america-gbp?cid=37538");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-euro-stoxx-50-leverage");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-500---gbp?cid=45782");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-world-water?cid=46017");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-auto.---parts?cid=46104");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-pacific-x-japan-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-euro-600-cons.---mat.?cid=46165");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-listed-private-eqty?cid=46492");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-cons.---mat.-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx600-opt.-cons.---mat.-source");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-auto.--parts-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-(ie)-on-msci-usa-smlcap-uk?cid=46810");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nareit-european-prop.-fund?cid=38612");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-travel--lei.?cid=46175");
		LISTA_URLS.add("https://es.investing.com/etfs/source-russell-2000?cid=46079");
		LISTA_URLS.add("https://es.investing.com/etfs/etfx-russell-2000-us-small-cap");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx600-per.--house.-goods?cid=46174");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-usa-mid-cap-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx600-opt.-per.--house.-goods");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-usa-small-cap-trn");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-msci-usa-mc");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-china-offshore-50---de");
		LISTA_URLS.add("https://es.investing.com/etfs/coba-5x-bundf-daily-long");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxxeuro-600-cons.---mat.");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-travel--lei.-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/ossiamus-minimum-variance-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-smi");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-smallcap-600?cid=45781");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-ftse-rafi-us-1000-fund?cid=46078");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-usa-islamic-gbp?cid=46821");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-msci-world-health-care-tr?cid=46008");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx600-trvl---leis.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-smi");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-financial-svs");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-insurance");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-re-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/rbs-market-access-dax-global-birc");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-em-eastern-europetr");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-birc-50---ie?cid=38607");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-s-p-asx-200");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxx-600-pers-housld-gds");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-us-select-dividend");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-stoxx-glbl-div-100?cid=46096");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-hong-kong---hsi?cid=46152");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-south-africa-(ftse-jse)?cid=45824");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-smi?cid=45945");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-nareit-dev.-markets?cid=38605");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-ftse-250?cid=46097");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-asia-pacific-div.-30?cid=38615");
		LISTA_URLS.add("https://es.investing.com/etfs/dj-stoxx600-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-health-care-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-health-care?cid=46172");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-auto-parts");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-msci-far-est-x-jap.-smlcp?cid=45872");
		LISTA_URLS.add("https://es.investing.com/etfs/easyetf-nmx30-infr.-global");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-nareit-asia-fd?cid=38604");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-ftse-xinhua-china-25?cid=45808");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-media?cid=46181");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-nareit-us-prop-fd?cid=38606");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-basic-res.-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-media-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-msci-russia-30-cappedtr");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-basic-res.");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-privex?cid=46192");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-asia-pac.-div.-30");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-banks-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etf-ftse-100?cid=46088");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-xinhua-china25?cid=45780");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-china-enterprise-(hscei)?cid=45807");
		LISTA_URLS.add("https://es.investing.com/etfs/easyetf-epra-eurozone?cid=45999");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-eastern-europe-(cece-eur)-de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx-600-technology");
		LISTA_URLS.add("https://es.investing.com/etfs/db-stoxxeuro600-basic-res.");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-turkey---dj-turkey-titans-20?cid=46015");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-basic-res.?cid=46068");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-russia---dj-russia-gdr?cid=46163");
		LISTA_URLS.add("https://es.investing.com/etfs/rbs-market-access-daxglobal-russia");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx-600-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-basicresources");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-banks");
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
