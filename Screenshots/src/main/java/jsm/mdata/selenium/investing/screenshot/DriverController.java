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
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-euro-corp-fina.-iboxx-de");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-euro-corp-x-fin.-iboxx?cid=45958");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-euro-corporates-de");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-gov-bond-high-rate-mts-inv.?cid=45954");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-gov-bond-low-rate-mts-inv.?cid=46274");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-gov-bond-mts-broad-invest?cid=45968");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-gov-bond-mts-invest-1-3?cid=46107");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-gov-bond-mts-invest-10-15?cid=45960");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-gov-bond-mts-invest-3-5?cid=45961");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-gov-bond-mts-invest-7-10?cid=46110");
		LISTA_URLS.add("https://es.investing.com/etfs/amundi-gov-bond-mts-nvest-5-7?cid=45964");
		LISTA_URLS.add("https://es.investing.com/etfs/australia-ssa-bonds-tr-index");
		LISTA_URLS.add("https://es.investing.com/etfs/coms.-%E2%82%AC-liq.-sov.-diver.-10-15-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-1-3-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-15-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-25-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-3-5-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-3m-1-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-5-7-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-7-10-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-sov.-diver.-overall-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-sov.-ger.-cap-3m-2-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-sov.-ger.-cap-5-10-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-sov.-germany-cap-1-5-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-sov.-germany-cap-10-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-iboxx-%E2%82%AC-sov.-euro-inf.-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-(ie)-iboxx-usd-govt-1-3-uk?cid=38652");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-iboxx-eur-govt-1-3?cid=45788");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-iboxx-eur-govt-3-7");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-iboxx-eur-govt-7-10");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-iboxx-usd-govt-3-7");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-iboxx-usd-govt-7-10");
		LISTA_URLS.add("https://es.investing.com/etfs/db-%E2%82%AC-liq.-corp-100-fin.-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-%E2%82%AC-liq.-corp-100-non-fin.-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-%E2%82%AC-sov.-euro-yield-plus-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-em-liquid-eurobond---eur");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-%E2%82%AC-sov.-euro-tr-4-d");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-ii-crossover-5-y-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-ii-global-sov.-1d");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trckers-ii-iboxx-sovereigns-eu?cid=949281");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-liquid-corp-100-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-1-3-tr-1c");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-1-3-tr-1d");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-10-15-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-3-5-tr-1c");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-3-5-tr-1d");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-3-5-tr-1d?cid=949560");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-7-10-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-ii-%E2%82%AC-sov.-euro-15-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-ii-%E2%82%AC-sov.-euro-25-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-ii-%E2%82%AC-sov.-euro-5-7-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-ii-%E2%82%AC-sov.-euro-aaa-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-ii-%E2%82%AC-sov.-euro-tr-1c");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-ii-global-sov.-1c");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liquid-sov.-diver.-1-10");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liquid-sov.-diver.-1-3");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liquid-sov.-diver.-10");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liquid-sov.-diver.-3-5");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liquid-sov.-diver.-5-7");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liquid-sov.-diver.-7-10");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-deutscheb.-eurogov-france");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-iboxx-%E2%82%AC-liquid-corps-diver.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-$-em-corp-bond?cid=46603");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-$-treasury-bond-1-3?cid=38620");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-$-treasury-bond-7-10?cid=38618");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-%E2%82%AC-government-bond-1-3?cid=38600");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-euro-corp.-bd-eur?cid=46455");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-euro-tr.-bond?cid=46457");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-germany-tr.-bond?cid=46693");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-italy-tr.bond?cid=46728");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-spain-tr.-bond?cid=46780");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-us-agg-bond?cid=46819");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-citigroup-global-gov.-bd-$?cid=46459");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eb.rexx-gov-ger.-1.5-2.5");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eb.rexx-gov-ger.-2.5-5.5");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eb.rexx-gov-ger.-5.5-10.5");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eb.rexx-gov-germany");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eb.rexx-gov-germany-10,5");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-em-asia-local-gov-cap-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eur-corporate-bond?cid=46481");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eur-covered-bond?cid=46482");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-corp-bd-x-fin-1-5?cid=46467");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-corp-bd-x-finance?cid=46466");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-corp.-bond-1-5?cid=46465");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-gov-bond-10-15-as?cid=44514");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-gov-bond-5-7-eur-uk?cid=46456");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-gov-bond-7-10-eur-uk?cid=37559");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-gov.-bond-15-30-it?cid=38602");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-gov.-bond-3-5-it?cid=37558");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-france-tr.-bond-gbp?cid=46630");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-global-infl.-link-bond-$?cid=46479");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-iboxx-$-hyld-cap.-bond?cid=46820");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-jp-usd-emerging-mkts-bond?cid=46602");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-markit-%E2%82%AC-sov.-cap-1.5-10.5");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-usd-corporate-bond?cid=38601");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-euro-corp-bond-x-financials?cid=46120");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-euro-corporate-bond?cid=46127");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-euromts-aaa-gov-1-3-y?cid=46177");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-euromts-aaa-gov-3-5-y?cid=46178");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-euromts-aaa-gov-5-7-y?cid=46179");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-euromts-covered-bond-agg.?cid=46145");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-iboxx-$-liquid-em-sov.-de");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-1-3-year-euro-gov-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-euro-aggregate-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-euro-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-euro-gov-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-euro-high-yield-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-sterling-corp-bnd-uk?cid=38661");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-us-aggregate-bond?cid=45922");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-us-treasury-bond-de");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-citi-asia-local-gov-bond-de");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-em-corporate-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etf-us-1-3-year-tr-bond-a-de");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etf-us-7-10-year-tr-bond-a-de");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-markit-eur-liquid-corps-a-de");
		LISTA_URLS.add("https://es.investing.com/etfs/advisorshares-madrona-global-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/barclays-bank-ipath-us-treasury-10");
		LISTA_URLS.add("https://es.investing.com/etfs/build-america-bond-portfolio");
		LISTA_URLS.add("https://es.investing.com/etfs/chinese-yuan-dim-sum-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ipath-us-treasury-flattener-etn");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-0-5-y-high-yld-corp-bd");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-0-5-year-invmt-grd-corp-bd");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-10-year-credit-bond-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-aaa---a-rated-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-0-5-y-tips-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-agency-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-cmbs-bond-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-gnma-bond-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-us-treasury-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-em-high-yield-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-em-local-currency-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-emerging-markets-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-gl.-x-us-high-y-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-global-high-y-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-h-y-corporate-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ibds-mar-2018-corp-exfincl");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ibds-mar-2018-corp-trm");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ibds-mar-2020-corp-exfincl");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ibds-mar-2020-corp-trm");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ibds-mar-2023-corp-exfincl");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ibds-mar-2023-corp-trm");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-inter.-gov.-credit-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-inv-g-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-lehman-1-3-y-credit-bond?cid=45158");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-lehman-10-20-y-tr.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-lehman-20-year-treas");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-lehman-credit-bond-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-lehman-gov.-credit-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-cali-muni-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-citigroup-intl-treas.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-national-mun-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-sh.term-nat.-muni-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/market-vectors-intl-hyield-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/marketv.-fallen-angel-high-y-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/marketvectors-em-high-yield-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/marketvectors-em-local-curr.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/marketvectors-latam-aggregate-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/pimco-0-5-year-high-y-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/pimco-1-3-year-us-treasury-index");
		LISTA_URLS.add("https://es.investing.com/etfs/pimco-inter.-mun.-bond-strategy");
		LISTA_URLS.add("https://es.investing.com/etfs/pimco-investment-grade-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-em-sovereign-debt");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-fund.-invest-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-high-yield-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-ins.-nat.-mun.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-insured-ca-mun.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-insured-ny-mun.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-intl-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/schwab-us-aggregate-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-aggregate-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-convertible-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-em-local-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-high-yield-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-interm.-term-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-intl-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-bofa-crossover-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-capital-srt-term-high-y-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-db-intl-gov.-tips-prot.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-issuer-scored-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-lehman-interm.-term-treasury");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-lehman-intl-treasury-bd");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-lehman-municipal-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-mortgage-backed-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-nuveen-s-p-high-y-mun.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/us-treasury-steepener-etn");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-bond-etf");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-emr-mrkts-govt-bd-idx");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-inter.-term-gov-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-interm.-term-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-total-bond-market");
		LISTA_URLS.add("https://es.investing.com/etfs/wisdomtree-em-corp-bond");
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

		LOGGER.info("Esperando gráfico en tercer IFrame");
		new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("pane-controls")));
		Thread.sleep(500);

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
