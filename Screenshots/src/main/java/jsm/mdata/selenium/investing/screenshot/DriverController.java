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
		// -- max-usa
		// --
		List<String> listaURL_01 = new ArrayList<String>();
		listaURL_01.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-cons.-dis.");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-cons.-discret.-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-industrials-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-raymond-james-sb-1-eq");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-materials-alphadex-fund");
		listaURL_01.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-industrials");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-industrials");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-transport-average-index");
		listaURL_01.add("https://es.investing.com/etfs/industrial-sector-spdr-trust");
		listaURL_01.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-materials");
		listaURL_01.add("https://es.investing.com/etfs/first-trust-capital-strength");
		listaURL_01.add("https://es.investing.com/etfs/wilshire-micro-cap");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-health-care");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-low-p-e-fund");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-total-stkmkt");
		listaURL_01.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-cons.-stap.");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-high-yielding-equity");
		listaURL_01.add("https://es.investing.com/etfs/spdr---health-care");
		listaURL_01.add("https://es.investing.com/etfs/diamonds-trust");
		listaURL_01.add("https://es.investing.com/etfs/spdr-materials-select-sector-etf");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-transportation");
		listaURL_01.add("https://es.investing.com/etfs/first-trust-total-us-market-alphade");
		listaURL_01.add("https://es.investing.com/etfs/pshares-ftse-rafi-dm-x-us-sml-mid");
		listaURL_01.add("https://es.investing.com/etfs/egshares-india-consumer");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-total-world-stock-index");
		listaURL_01.add("https://es.investing.com/etfs/powershares-s-p-500-high-beta");
		listaURL_01.add("https://es.investing.com/etfs/advisorshares-trimtabs-flt-shrink");
		listaURL_01.add("https://es.investing.com/etfs/schwab-us-broad-market");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-russell-3000");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-russell-1000");
		listaURL_01.add("https://es.investing.com/etfs/market-vectors-retail");
		listaURL_01.add("https://es.investing.com/etfs/s-p-india-nifty-fifty");
		listaURL_01.add("https://es.investing.com/etfs/advisorshares-madrona-intl");
		listaURL_01.add("https://es.investing.com/etfs/barclays-etn-s-p-veqtor-etn");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-500");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-industrials");
		listaURL_01.add("https://es.investing.com/etfs/advisorshares-madrona-domestic");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-s-p-500");
		listaURL_01.add("https://es.investing.com/etfs/global-x-ftse-argentina-20");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-multi-cg-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/schwab-international-equity");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-consumer-discretion");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-health-care-providers");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-global-timber");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-basic-materials-index");
		listaURL_01.add("https://es.investing.com/etfs/cboe-sp-500-buy-write");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-europe-alphadex-fund");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-dm-x-us-alphadex-fund");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-industrial-sector");
		listaURL_01.add("https://es.investing.com/etfs/ishares-floating-rate-note-fund");
		listaURL_01.add("https://es.investing.com/etfs/proshares-credit-suisse-130-30");
		listaURL_01.add("https://es.investing.com/etfs/spdr-consumer-discr.-select-sector");
		listaURL_01.add("https://es.investing.com/etfs/iq-arb-merger-arbitrage");
		listaURL_01.add("https://es.investing.com/etfs/powershares-ftse-rafi-us-1000");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-cons.discr.");
		listaURL_01.add("https://es.investing.com/etfs/morningstar-us-market-factor-tilt");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-aerospace---defense");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-materials");
		listaURL_01.add("https://es.investing.com/etfs/morningstar-wide-moat-focus-etn");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-usa");
		listaURL_01.add("https://es.investing.com/etfs/marketv.-invest.-grade-float-rate");
		listaURL_01.add("https://es.investing.com/etfs/rydex-russell-2000-equal-weight");
		listaURL_01.add("https://es.investing.com/etfs/powershares-aerospace---defense");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-nq-100-x-tech-sector");
		listaURL_01.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-health-care");
		listaURL_01.add("https://es.investing.com/etfs/rydex-s-p-equal-weight");
		listaURL_01.add("https://es.investing.com/etfs/proshares-hedge-replication");
		listaURL_01.add("https://es.investing.com/etfs/msci-ireland-capped-invest.-mrkt");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-nq-100-equal-weighted");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-insider");
		listaURL_01.add("https://es.investing.com/etfs/s-p-500-high-quality-portfolio");
		listaURL_01.add("https://es.investing.com/etfs/ishares-russell-3000");
		listaURL_01.add("https://es.investing.com/etfs/ishares-russell-1000");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-consumer");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-morningstar");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-aggressive-allocation");
		listaURL_01.add("https://es.investing.com/etfs/iq-hedge-multi-strategy");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p---global-100");
		listaURL_01.add("https://es.investing.com/etfs/meidell-tactical-advantage");
		listaURL_01.add("https://es.investing.com/etfs/ishares-russell-top-200");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-total-intl-stock");
		listaURL_01.add("https://es.investing.com/etfs/fidelity-nq-composite-tracking");
		listaURL_01.add("https://es.investing.com/etfs/rydex-russell-top-50");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-moderate-allocation");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-defensive-equity");
		listaURL_01.add("https://es.investing.com/etfs/alps-equal-sector-weight");
		listaURL_01.add("https://es.investing.com/etfs/nq-clean-edge-smart-grid-infras.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-russell-2000-index-etf");
		listaURL_01.add("https://es.investing.com/etfs/ishares-kld-400-social");
		listaURL_01.add("https://es.investing.com/etfs/ishares-kld-select-social-index");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-consumer-goods-index");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-con.-stap.");
		listaURL_01.add("https://es.investing.com/etfs/etfs-physical-palladium-shares");
		listaURL_01.add("https://es.investing.com/etfs/ishares-goldman-sachs-network");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-spin-off");
		listaURL_01.add("https://es.investing.com/etfs/powershares-qqqq");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-ipox-100");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-health-care-index");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-leisure---ent.");
		listaURL_01.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-technology");
		listaURL_01.add("https://es.investing.com/etfs/revenueshares-financials-sector");
		listaURL_01.add("https://es.investing.com/etfs/spdr---consumer-staples");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-japan-alphadex-fund");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-financials-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-technology");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-aerospace---defense");
		listaURL_01.add("https://es.investing.com/etfs/ishares-goldman-sachs-technology");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-total-market-index");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-timber---for.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-usa-min-volatility");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-extended-market");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-ise-chindia");
		listaURL_01.add("https://es.investing.com/etfs/spdr-select-sector---technology");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-500");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-consumer-staples");
		listaURL_01.add("https://es.investing.com/etfs/ishares-core-s-p-tot-us-stock-mrkt");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-health-care-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/ishares-russell-microcap-index");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-100");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-conser.-allocation");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-s-p-global-water");
		listaURL_01.add("https://es.investing.com/etfs/ise-cloud-computing");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-financials");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-dj-internet");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-cons.-staples-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-dj-select-microcap");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-healthcare");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-russell-2000");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-ise-glb-engnrg---const");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-global-ex-su-real-estate");
		listaURL_01.add("https://es.investing.com/etfs/powershares-xtf:-dynamic-market");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-acwi-index-fund");
		listaURL_01.add("https://es.investing.com/etfs/powershares-s-p-500-low-volatility");
		listaURL_01.add("https://es.investing.com/etfs/powershares-active-us-real-estate");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-build---const.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-eafe-min-volatility");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-medical-devices-index");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-technology");
		listaURL_01.add("https://es.investing.com/etfs/spdr-dj-global-titans");
		listaURL_01.add("https://es.investing.com/etfs/powershares-zacks-micro-cap");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-networking");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-broker-dealers-index");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-ftse-all-wld-x-us-smcp");
		listaURL_01.add("https://es.investing.com/etfs/db-x-msci-japan-curr.-hedged-eq");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-japan");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-software---services");
		listaURL_01.add("https://es.investing.com/etfs/powershares-water-resource-port");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-all-country-min.-vol.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-insurance");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-information-tech");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-nasdaq-global-auto");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci---kokusai");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-ise-water");
		listaURL_01.add("https://es.investing.com/etfs/global-x-social-media-index");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-nasdaq-cea-smartphone");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-health-care-equipment");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-nq-100-technology");
		listaURL_01.add("https://es.investing.com/etfs/msci-emerging-markets-smlcp-fund");
		listaURL_01.add("https://es.investing.com/etfs/powershares-nasdaq-internet");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-utilities");
		listaURL_01.add("https://es.investing.com/etfs/spdr-ms-technology");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-financial-sector");
		listaURL_01.add("https://es.investing.com/etfs/ishares-goldman-sachs-software");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-mortgage-backed-sec.");
		listaURL_01.add("https://es.investing.com/etfs/spdr-select-sector---utilities");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-technology-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/powersh.-dynamic-food---beverage");
		listaURL_01.add("https://es.investing.com/etfs/spdr-kbw-regional-banking");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-emerging-asia-pacific");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-nq-aba-community-bank");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-pacific");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-intermediate-mun.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-switzerland-index");
		listaURL_01.add("https://es.investing.com/etfs/powershares-cleantech-portfolio");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-netherlands-index");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-dreyfus-chinese-yuan");
		listaURL_01.add("https://es.investing.com/etfs/ishares-barclays-agg");
		listaURL_01.add("https://es.investing.com/etfs/powershares-vrdo-tax-free-weekly");
		listaURL_01.add("https://es.investing.com/etfs/ishares-phlx-sox-semiconductor");
		listaURL_01.add("https://es.investing.com/etfs/powershares-india-portfolio");
		listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-msci-ger-hgd-eq");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-software");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-asia-50");
		listaURL_01.add("https://es.investing.com/etfs/market-vectors-indian-rupee-usd");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-n.z.-investable-mar.");
		listaURL_01.add("https://es.investing.com/etfs/ipath-inv.-s-p500-vix-shterm-fut.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-allcountry-asia-x-jap");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-pre-refunded-mun.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-thailand");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-china-all-cap");
		listaURL_01.add("https://es.investing.com/etfs/market-vectors-renminbi-usd");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-hong-kong");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-media");
		listaURL_01.add("https://es.investing.com/etfs/powershares-global-water");
		listaURL_01.add("https://es.investing.com/etfs/spdr-wells-fargo-preferred-stock");
		listaURL_01.add("https://es.investing.com/etfs/egshares-emerging-markets-consumer");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-china");
		listaURL_01.add("https://es.investing.com/etfs/golden-dragon-halter-usx-china");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-financial-services");
		listaURL_01.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-utilities");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-telecom");
		listaURL_01.add("https://es.investing.com/etfs/iboxx-3-y-target-dur.-tips");
		listaURL_01.add("https://es.investing.com/etfs/ishares-south-korea-index");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-semiconductors");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-intl-hedged-equity-fund");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-utilities");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-russia-capped-index");
		listaURL_01.add("https://es.investing.com/etfs/global-x-nasdaq-china-technology");
		listaURL_01.add("https://es.investing.com/etfs/spdr-wilshire-global-real-estate");
		listaURL_01.add("https://es.investing.com/etfs/market-vectors-env-svcs");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-amex-biotech");
		listaURL_01.add("https://es.investing.com/etfs/schwab-us-tips");
		listaURL_01.add("https://es.investing.com/etfs/blackrock-muniassets-closed-fund");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-india-earnings-fund");
		listaURL_01.add("https://es.investing.com/etfs/iq-hedge-macro-tracker");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-singapore");
		listaURL_01.add("https://es.investing.com/etfs/iboxx-5-y-target-dur.-tips");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-homebuilders");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-em-min-volatility");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-utilities-alphadex-fund");
		listaURL_01.add("https://es.investing.com/etfs/market-vectors-high-yield-munic-etf");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-china");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-agribusiness");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-semiconductor");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-regional-banks-index");
		listaURL_01.add("https://es.investing.com/etfs/claymore-china-real-estate");
		listaURL_01.add("https://es.investing.com/etfs/u.s.-reit");
		listaURL_01.add("https://es.investing.com/etfs/pimco-broad-us-tips-index-fund");
		listaURL_01.add("https://es.investing.com/etfs/ishares-taiwan-index");
		listaURL_01.add("https://es.investing.com/etfs/holdrs-merrill-lynch-semiconductor");
		listaURL_01.add("https://es.investing.com/etfs/schwab-emerging-markets-equity");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-biotech");
		listaURL_01.add("https://es.investing.com/etfs/spdr-barclays-capital-tips");
		listaURL_01.add("https://es.investing.com/etfs/ishares-barclays-tip");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-reit");
		listaURL_01.add("https://es.investing.com/etfs/spdr-dj-wilshire-reit");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-health-care-services");
		listaURL_01.add("https://es.investing.com/etfs/ishares-nareit-real-estate-50");
		listaURL_01.add("https://es.investing.com/etfs/powershares-senior-loan-portfolio");
		listaURL_01.add("https://es.investing.com/etfs/powersh.-rafi-asia-pacific-x-japan");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-south-korea-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/alps-coh.-ste.-global-realty-maj.");
		listaURL_01.add("https://es.investing.com/etfs/financial-select-sector-spdr-fund");
		listaURL_01.add("https://es.investing.com/etfs/wilshire-us-reit");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-s-p-reit-index-fund");
		listaURL_01.add("https://es.investing.com/etfs/global-x-china-financials");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-china-technology");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-nareit-residential");
		listaURL_01.add("https://es.investing.com/etfs/powershares-kbw-premium-y-eq-reit");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-cef-municipal-income");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-china-alphadex-fund");
		listaURL_01.add("https://es.investing.com/etfs/cef-income-composite-portfolio");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-emerging-mrkt-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-telecom");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-infrastructure");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-world-ex-us");
		listaURL_01.add("https://es.investing.com/etfs/pshares-kbw-high-div.-yield");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-retail");
		listaURL_01.add("https://es.investing.com/etfs/powershares-bldrs-asia-50-adr");
		listaURL_01.add("https://es.investing.com/etfs/global-x-ftse-asean-40");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-uk");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-home-construction");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-retail");
		listaURL_01.add("https://es.investing.com/etfs/global-x-ftse-nordic-30");
		listaURL_01.add("https://es.investing.com/etfs/market-vectors-biotech");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-acwi-x-us");
		listaURL_01.add("https://es.investing.com/etfs/msci-europe-financials-secto");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-mortgage-reit-income");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-philippines-invest.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-cohen--steers-realty-major");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-acwi-ex-us");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-sweden");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-south-africa");
		listaURL_01.add("https://es.investing.com/etfs/s-p-emerging-mrkt-infrastructure");
		listaURL_01.add("https://es.investing.com/etfs/global-x-china-consumer");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-ftse-all-world-x-us");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-europe-pacific");
		listaURL_01.add("https://es.investing.com/etfs/morningstar-g.-upstream-nat.-res.");
		listaURL_01.add("https://es.investing.com/etfs/quantshares-us-market-neutral");
		listaURL_01.add("https://es.investing.com/etfs/s-p-500-buywrite-portfolio");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-asia-local-debt-fund");
		listaURL_01.add("https://es.investing.com/etfs/global-x-lithium");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-hard-assets-prod.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dow-jones-us-real-est");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-italy-capped-fund");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-pharmaceutical-index");
		listaURL_01.add("https://es.investing.com/etfs/first-trust-bick");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-gaming");
		listaURL_01.add("https://es.investing.com/etfs/iq-arb-global-resources-etf");
		listaURL_01.add("https://es.investing.com/etfs/spdr-ftse-macquarie-gi-100");
		listaURL_01.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-financial");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-emg-markets");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-epra-nareit-global-re");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-eafe-index");
		listaURL_01.add("https://es.investing.com/etfs/powershares-ftse-rafi-dm-x-us");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-dreyfus-emerging-curr.");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-dj-global-select-div.");
		listaURL_01.add("https://es.investing.com/etfs/dj-high-yield-select-10-etn");
		listaURL_01.add("https://es.investing.com/etfs/holdrs-merrill-lynch-pharma.");
		listaURL_01.add("https://es.investing.com/etfs/powershares-db-g10-curr.-harvest");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-canada");
		listaURL_01.add("https://es.investing.com/etfs/spdr-kbw-capital-markets");
		listaURL_01.add("https://es.investing.com/etfs/quantshares-us-market-neutral-mom.");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-dev-int-opp");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-multi-asset-income");
		listaURL_01.add("https://es.investing.com/etfs/rydex-msci-em-eq-weight");
		listaURL_01.add("https://es.investing.com/etfs/powersh.-dynamic-pharmaceuticals");
		listaURL_01.add("https://es.investing.com/etfs/spdr-kbw-bank");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-france");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-pac-x-jp");
		listaURL_01.add("https://es.investing.com/etfs/ipath-gems-asia-8");
		listaURL_01.add("https://es.investing.com/etfs/powers.-wilderhill-prog.-energy");
		listaURL_01.add("https://es.investing.com/etfs/etracs-business-dev.-company");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-materials");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-peru");
		listaURL_01.add("https://es.investing.com/etfs/quantshares-us-mrkt-neut-size");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-ftse-emerging-markets");
		listaURL_01.add("https://es.investing.com/etfs/powershares-ft-rafi-emerging-mrkt");
		listaURL_01.add("https://es.investing.com/etfs/ishares-us-preferred-stock?cid=38209");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-europe-350");
		listaURL_01.add("https://es.investing.com/etfs/ipath-msci-india-index-etn");
		listaURL_01.add("https://es.investing.com/etfs/msci-indonesia-investable-market");
		listaURL_01.add("https://es.investing.com/etfs/powershares-financial-preferred");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-global-natural-resources");
		listaURL_01.add("https://es.investing.com/etfs/managed-futures-strategy-fund");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-telecom.");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-defa-fund");
		listaURL_01.add("https://es.investing.com/etfs/powersh.-dynamic-biotech---genome");
		listaURL_01.add("https://es.investing.com/etfs/egshares-low-volatility-em-div.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-financial");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-asia-pacific-x-japan");
		listaURL_01.add("https://es.investing.com/etfs/global-x-china-industrials");
		listaURL_01.add("https://es.investing.com/etfs/quantshares-us-mkt-neut-anti-beta");
		listaURL_01.add("https://es.investing.com/etfs/barclays-bank-ipath-bloomberg-lead");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-belgium");
		listaURL_01.add("https://es.investing.com/etfs/powershares---preferred-portfolio");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-msci-eu");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-indonesia-index");
		listaURL_01.add("https://es.investing.com/etfs/powershares-bldrs-em-50-adr");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-developed-x-us-prop.");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-em-local-debt");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-utilities");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-birc");
		listaURL_01.add("https://es.investing.com/etfs/global-x-ftse-norway-30");
		listaURL_01.add("https://es.investing.com/etfs/united-states-copper-index-fund");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-epra-nareit-europe");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-china");
		listaURL_01.add("https://es.investing.com/etfs/spdr-energy-select-sector-fund");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-emu");
		listaURL_01.add("https://es.investing.com/etfs/pshares-bldrs-dm-100-adr");
		listaURL_01.add("https://es.investing.com/etfs/peritus-high-yield-etf");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-europe-high-yielding-eq");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-latin-america-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-eme-income-fund");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-poland-inve.-mrkt");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-intl-mul.-asset-income");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-us-energy-sector-fund");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-intl-preferred-stock");
		listaURL_01.add("https://es.investing.com/etfs/ubs-etracs-cmci-livestock-tr");
		listaURL_01.add("https://es.investing.com/etfs/egshares-india-infrastructure");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-pharmaceuticals");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-energy");
		listaURL_01.add("https://es.investing.com/etfs/ishares-comex-gold-trust");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-north-amer.-nat.-res.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-australia-index");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-energy");
		listaURL_01.add("https://es.investing.com/etfs/etfs-physical-swiss-gold-shares");
		listaURL_01.add("https://es.investing.com/etfs/spdr-gold-trust");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-birc");
		listaURL_01.add("https://es.investing.com/etfs/global-x-china-energy");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-mexico");
		listaURL_01.add("https://es.investing.com/etfs/spdr-dj-stoxx-50");
		listaURL_01.add("https://es.investing.com/etfs/powershares-global-em-infr.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-chile");
		listaURL_01.add("https://es.investing.com/etfs/claymore-cef-gs-connect-etn");
		listaURL_01.add("https://es.investing.com/etfs/powershares-db-base-metals-fund");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-poland");
		listaURL_01.add("https://es.investing.com/etfs/ubs-etracs-bloomberg-cmci-industr");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-us-oil-gas-exp.---prod.");
		listaURL_01.add("https://es.investing.com/etfs/global-x-fertilizers-potash");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-xinhua-china-25");
		listaURL_01.add("https://es.investing.com/etfs/global-x-china-materials");
		listaURL_01.add("https://es.investing.com/etfs/powershares-bldrs-europe-100-adr");
		listaURL_01.add("https://es.investing.com/etfs/spdr-dj-euro-stoxx-50");
		listaURL_01.add("https://es.investing.com/etfs/ubs-etracs-cmci-gold-tr");
		listaURL_01.add("https://es.investing.com/etfs/sprott-physical-gold-trust");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-dreyfus-brazilian-real");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-defa-high-yielding-eq");
		listaURL_01.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-energy");
		listaURL_01.add("https://es.investing.com/etfs/powershares-db-gold-fund");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-frontier-mrkt");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ftepra-nareit-dev.-re-x-us");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-ubs-copper-subindex-tr");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-africa-index");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-austria");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-intl-real-estate-fund");
		listaURL_01.add("https://es.investing.com/etfs/barclays-bank-ipath-eurusd-exchange");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-ubs-tin-tr-sub-index");
		listaURL_01.add("https://es.investing.com/etfs/ipath-gems-index");
		listaURL_01.add("https://es.investing.com/etfs/spdr-dj-wilshire-intl-real-estate");
		listaURL_01.add("https://es.investing.com/etfs/alerian-mlp");
		listaURL_01.add("https://es.investing.com/etfs/ishares-latin-america-40-index");
		listaURL_01.add("https://es.investing.com/etfs/ubs-etracs-bloomberg-cmci-food");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-energy-e-p");
		listaURL_01.add("https://es.investing.com/etfs/physical-pr.-metals-basket-sh.");
		listaURL_01.add("https://es.investing.com/etfs/powershares-db-precious-metals");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-turkey");
		listaURL_01.add("https://es.investing.com/etfs/etracs-alerian-mlp-infrastructure");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-malaysia");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-vietnam-etf");
		listaURL_01.add("https://es.investing.com/etfs/ubs-etracs-bloomberg-cmci-agri");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-ind.-metals-tr-subindex");
		listaURL_01.add("https://es.investing.com/etfs/elements-linked-to-mlcx-biofuels-tr");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-ubs-pr.-metals-tr-subin.");
		listaURL_01.add("https://es.investing.com/etfs/jp-morgan-alerian-mlp-index");
		listaURL_01.add("https://es.investing.com/etfs/etracs-1xmon.-sh.-al.-mlp-infr.-tr");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-brazil-alphadex-fund");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-energy-alphadex-fund");
		listaURL_01.add("https://es.investing.com/etfs/ubs-etracs-bloomberg-cmci");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-ny-muni");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-spain");
		listaURL_01.add("https://es.investing.com/etfs/nexpoint-credit-strategies-fund");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-ubs-livestock-tr-subindex");
		listaURL_01.add("https://es.investing.com/etfs/etfs-physical-platinum-shares");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-telecommunications");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-s-p-global-div.-opp.");
		listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-msci-brazil-hgd-eq");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-emerging-markets");
		listaURL_01.add("https://es.investing.com/etfs/market-vectors-oil-services");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-oil-equipment---serv.");
		listaURL_01.add("https://es.investing.com/etfs/global-x-interbolsa-ftcolombia-20");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-djstoxx-select-div.-30");
		listaURL_01.add("https://es.investing.com/etfs/powershares-listed-private-equity");
		listaURL_01.add("https://es.investing.com/etfs/global-x-copper-miners");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-oil--gas-explor---product");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-ise-global-wind-energy");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-nuclear-energy");
		listaURL_01.add("https://es.investing.com/etfs/global-x-ftse-greece-20");
		listaURL_01.add("https://es.investing.com/etfs/powershares---global-clean-energy");
		listaURL_01.add("https://es.investing.com/etfs/salix-pharmaceuticals-ltd.");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-global-shipping");
		listaURL_01.add("https://es.investing.com/etfs/ise-global-platinum");
		listaURL_01.add("https://es.investing.com/etfs/ishares-brazil-index");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-tr-russia");
		listaURL_01.add("https://es.investing.com/etfs/egypt-index");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-ubs-platinum-tr-sub-index");
		listaURL_01.add("https://es.investing.com/etfs/s-p-500-dynamic-vix-etn");
		listaURL_01.add("https://es.investing.com/etfs/powersh.-global-gold---pr.-metals");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-metals---mining");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-total-earnings");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-earnings-500-fund");
		listaURL_01.add("https://es.investing.com/etfs/morgan-st.-cushing-mlp-high-income");
		listaURL_01.add("https://es.investing.com/etfs/silver-miners");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-ubs-aluminum-tr-sub-index");
		listaURL_01.add("https://es.investing.com/etfs/market-vectors-gold-miners");
		listaURL_01.add("https://es.investing.com/etfs/spdr-kbw-insurance");
		listaURL_01.add("https://es.investing.com/etfs/etfs-silver-trust-us");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-global-alt.-energy");
		listaURL_01.add("https://es.investing.com/etfs/ishares-silver-trust");
		listaURL_01.add("https://es.investing.com/etfs/spdr-russell-1000");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-oil---gas-eq---services");
		listaURL_01.add("https://es.investing.com/etfs/ubs-etracs-cmci-silver-tr");
		listaURL_01.add("https://es.investing.com/etfs/powershares-db-silver-fund");
		listaURL_01.add("https://es.investing.com/etfs/sprott-physical-silver-trust");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-aig-nickel");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-oil-services");
		listaURL_01.add("https://es.investing.com/etfs/ishares-nasdaq-biotech");
		listaURL_01.add("https://es.investing.com/etfs/market-vectors-coal");
		listaURL_01.add("https://es.investing.com/etfs/ise-global-copper");
		listaURL_01.add("https://es.investing.com/etfs/powershares-db-energy-fund");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-canada-energy-income");
		listaURL_01.add("https://es.investing.com/etfs/marketv.-rare-earth-strat.-metals");
		listaURL_01.add("https://es.investing.com/etfs/ishares-nareit-mortgage-reits");
		listaURL_01.add("https://es.investing.com/etfs/ubs-etracs-bloomberg-cmci-energy");
		listaURL_01.add("https://es.investing.com/etfs/spdr-dj-wilshire-total-market");
		listaURL_01.add("https://es.investing.com/etfs/ipath-gsci-tr-index");
		listaURL_01.add("https://es.investing.com/etfs/market-vectors-junior-gold-miners");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-clean-energy");
		listaURL_01.add("https://es.investing.com/etfs/powershares-db-oil-fund");
		listaURL_01.add("https://es.investing.com/etfs/ipath-global-carbon");
		listaURL_01.add("https://es.investing.com/etfs/powersh-wilderhill-clean-energy");
		listaURL_01.add("https://es.investing.com/etfs/global-x-gold-explorers");
		listaURL_01.add("https://es.investing.com/etfs/global-x-uranium");
		listaURL_01.add("https://es.investing.com/etfs/united-states-oil-fund");
		listaURL_01.add("https://es.investing.com/etfs/claymore-mac-global-solar-energy-in");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-ubs-energy-tr-sub-index");
		LISTA_URL_GROUP.add(new URLGroup("max-usa", listaURL_01));
		// --
		// -- max-ger
		// --
		List<String> listaURL_02 = new ArrayList<String>();
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-industrial-average");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-dow-jones-ind.-average?cid=46048");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-world-mon.-hedged?cid=46487");
		listaURL_02.add("https://es.investing.com/etfs/db-physical-palladium-euro-hdg-de");
		listaURL_02.add("https://es.investing.com/etfs/stoxx600-opt.-travel--lei.-sou.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-nasdaq-100");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-world---gbp?cid=38614");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-world---acc?cid=45873");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-nasdaq-100?cid=46049");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-500---gbp?cid=45782");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-s-p-500?cid=45844");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-usa?cid=45846");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-north-america-gbp?cid=37538");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-food---bev.?cid=46167");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-global-sust-screened-uk?cid=38617");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-ftse-100-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-japan-mon.-euro-hed.?cid=46741");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-msci-uk-gbp-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-global-titans-50");
		listaURL_02.add("https://es.investing.com/etfs/db-ftse-nareit-dev.-euro-re-1c");
		listaURL_02.add("https://es.investing.com/etfs/ubs-msci-n.-amrca-soc.-resp.-a-de");
		listaURL_02.add("https://es.investing.com/etfs/comstage-dj-industrial-average");
		listaURL_02.add("https://es.investing.com/etfs/amundi-real-estate-reit-ieif?cid=46112");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-msci-thailand-trn");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-north-america-trn");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-usa");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-usa-de");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-msci-usa-trn-index?cid=45827");
		listaURL_02.add("https://es.investing.com/etfs/dbx-s-p-500-class-1-uk?cid=38570");
		listaURL_02.add("https://es.investing.com/etfs/comstage-s-p-500");
		listaURL_02.add("https://es.investing.com/etfs/s-p-500-source");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-usa-trn");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-world-trn");
		listaURL_02.add("https://es.investing.com/etfs/amundi-s-p-500---eur-de");
		listaURL_02.add("https://es.investing.com/etfs/hsbc-s-p-500-de");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-world-x-europe---eur?cid=46114");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-world-financials---eur?cid=46140");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-msci-world-trn-uk?cid=46409");
		listaURL_02.add("https://es.investing.com/etfs/hsbc-msci-usa-de");
		listaURL_02.add("https://es.investing.com/etfs/msci-usa-source");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-usa");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-nasdaq-100?cid=46271");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-usa-lc");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-world-x-emu?cid=45985");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-acwi?cid=46046");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-acwi-imi-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-nareit-european-prop.-fund?cid=38612");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-msci-pan-euro-trn");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-world-a");
		listaURL_02.add("https://es.investing.com/etfs/msci-world-source?cid=46070");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-us-mrkt-it?cid=45899");
		listaURL_02.add("https://es.investing.com/etfs/ossiam-stoxx-eur-min-var-nr---gbp");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-msci-pacific-x-japan-de");
		listaURL_02.add("https://es.investing.com/etfs/ubs-msci-world-socially-resp.-a-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-info-tech-tr?cid=46225");
		listaURL_02.add("https://es.investing.com/etfs/comstage-atx");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-s-p-cnx-nifty-uk?cid=37482");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-europe-insurance?cid=45980");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-japan---acc?cid=46059");
		listaURL_02.add("https://es.investing.com/etfs/powershares-eqqq?cid=45852");
		listaURL_02.add("https://es.investing.com/etfs/msci-pacific-x-japan-trn-%C2%A3-uk?cid=46403");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-europe-(acc)-uk?cid=46060");
		listaURL_02.add("https://es.investing.com/etfs/comstage-nasdaq-100");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-nasdaq-100---eur?cid=45825");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-consumer-disc.-tr?cid=46122");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-portfolio-tr-index");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-europe-sust-screened-as?cid=46461");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-japan---ie?cid=45811");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-japan-mc");
		listaURL_02.add("https://es.investing.com/etfs/ishares-nikkei-225");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-glbl-water?cid=46491");
		listaURL_02.add("https://es.investing.com/etfs/ossiam-stoxx-europe-600-ew-nr");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-japan?cid=45843");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx600-food-beverage");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-nikkei-225?cid=46050");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-world-islamic---gbp?cid=46486");
		listaURL_02.add("https://es.investing.com/etfs/etfs-physical-palladium-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world?cid=46273");
		listaURL_02.add("https://es.investing.com/etfs/db-stoxx-europe-600-uk?cid=46414");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-europe-600-source");
		listaURL_02.add("https://es.investing.com/etfs/msci-europe-source");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-dj-industrial-average?cid=46142");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-ac-asia-pacific-x-japan?cid=45950");
		listaURL_02.add("https://es.investing.com/etfs/comstage-f.a.z.-index");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-stoxx-europe-600?cid=45967");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-pacific-trn");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-pacific-x-japan---eur?cid=46125");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-nr");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-europe?cid=45975");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-pacific-x-japan-trn");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-materials?cid=46216");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-europe-trn");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe?cid=46001");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-msci-europe-trn?cid=45815");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-technology?cid=46023");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-emerging-markets?cid=46020");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-east-euro-x-russia-de");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx600-food--bev.-nr");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-emu?cid=45841");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-asia-apex-50?cid=45956");
		listaURL_02.add("https://es.investing.com/etfs/db-stoxxeuro600-food--beverage");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-europe-materials?cid=46111");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-optimised-insurance");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-lpx-mm-private-eq");
		listaURL_02.add("https://es.investing.com/etfs/stoxx600-opt.-food--bev.-source");
		listaURL_02.add("https://es.investing.com/etfs/source-russell-2000?cid=46079");
		listaURL_02.add("https://es.investing.com/etfs/ishares-tecdax-(de)-de");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-europe-mc");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx600-fin.-ser.-nr");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxxeuro-600-insu.-nr");
		listaURL_02.add("https://es.investing.com/etfs/comstage-nikkei-225");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-korea?cid=46156");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-msci-japan-trn-etf?cid=45809");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-japan---eur?cid=45983");
		listaURL_02.add("https://es.investing.com/etfs/hsbc-msci-japan-de");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-msci-india-trn-uk?cid=46725");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-india---eur-de");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-japan-trn");
		listaURL_02.add("https://es.investing.com/etfs/msci-japan-source?cid=45891");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-japan");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-ftse-mib?cid=45842");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-usa-mc");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-emu-trn");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-emu-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-europe-ex-uk?cid=44523");
		listaURL_02.add("https://es.investing.com/etfs/amundi-cac-40-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dax-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-insurance?cid=46176");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-usa?cid=46270");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-s-p-500?cid=46039");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-emu-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-euro-aggregate-bd-eur?cid=46464");
		listaURL_02.add("https://es.investing.com/etfs/ishares-divdax");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-optimised-financial-ser.");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-financials?cid=46223");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-consumer-discr.?cid=46218");
		listaURL_02.add("https://es.investing.com/etfs/rbs-market-access-dax-global-asia");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-japan-lc");
		listaURL_02.add("https://es.investing.com/etfs/comstage-cac-40?cid=46287");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-cac-40-de");
		listaURL_02.add("https://es.investing.com/etfs/comstage-divdax-tr");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-financials-tr?cid=46006");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx600-ind.-goods--ser.");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-utilities-tr?cid=46272");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-world-water?cid=46017");
		listaURL_02.add("https://es.investing.com/etfs/db-stoxxeuro600-ind.-goods");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-euro-stoxx-50?cid=45848");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-energy?cid=46184");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-industrials-tr?cid=46010");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-europe-energy?cid=45955");
		listaURL_02.add("https://es.investing.com/etfs/comstage-dax-tr");
		listaURL_02.add("https://es.investing.com/etfs/etflab-dax-(preisindex)");
		listaURL_02.add("https://es.investing.com/etfs/ishares-mdax-(de)-de");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-dax");
		listaURL_02.add("https://es.investing.com/etfs/comstage-fr-dax");
		listaURL_02.add("https://es.investing.com/etfs/etflab-dax");
		listaURL_02.add("https://es.investing.com/etfs/ubs-euro--mideast-soc.-res.-a-de");
		listaURL_02.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liq.-non-finan.-diver.");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-dax-pa?cid=46694");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-industrials?cid=46217");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx600-travel--lei.-nr");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-europe-industrials?cid=45982");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-europe-it?cid=45981");
		listaURL_02.add("https://es.investing.com/etfs/ishares-stoxx600-ind.-goods--ser.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-emerging-market?cid=46607");
		listaURL_02.add("https://es.investing.com/etfs/stoxx600-opt.-ind.-good--ser.-sou.");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-materials-tr?cid=46025");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-euro-600-fin.-ser.?cid=46148");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-emerging-mrkt---acc?cid=46604");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-emu?cid=46026");
		listaURL_02.add("https://es.investing.com/etfs/stoxx600-opt.-auto.--parts-source");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-emerging-markets-trn");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-taiwan?cid=46170");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-ac-far-east-x-japan?cid=38609");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-emerging-markets?cid=45949");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-emerging-markets-a");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-600-ind.-goods---ser.?cid=46153");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-emerging-markets?cid=45850");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-emerging-markets");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-em-asia?cid=45840");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-europe-cons.-disc?cid=45971");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-timber-forestry-gbp?cid=46090");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-re-nr");
		listaURL_02.add("https://es.investing.com/etfs/ubs-msci-pacific-soc.-resp.-a-de");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-msci-em-trn-idx-etf?cid=45821");
		listaURL_02.add("https://es.investing.com/etfs/comstage-sdax-tr");
		listaURL_02.add("https://es.investing.com/etfs/msci-emerging-mrktssource-de");
		listaURL_02.add("https://es.investing.com/etfs/comstage-eurostoxx-div.-30-nr");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-euro-600-cons.---mat.?cid=46165");
		listaURL_02.add("https://es.investing.com/etfs/comstage-euro-stoxx-50-nr");
		listaURL_02.add("https://es.investing.com/etfs/euro-stoxx-50-source");
		listaURL_02.add("https://es.investing.com/etfs/db-euro-stoxx-50-1c-mc?cid=46383");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-euro-stoxx-50?cid=45963");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-msci-korea-de");
		listaURL_02.add("https://es.investing.com/etfs/easyetf-euro-stoxx-50---c-share?cid=46004");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxxeuro-600-chem.-nr");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-s-p-global-infr.-uk?cid=38694");
		listaURL_02.add("https://es.investing.com/etfs/powershares-ftse-rafi-us-1000-fund?cid=46078");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-korea-gbp?cid=45779");
		listaURL_02.add("https://es.investing.com/etfs/comstage-hsi");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-stoxxeuro-600-oil-gas");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-chemicals?cid=46117");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-optimised-oil---gas");
		listaURL_02.add("https://es.investing.com/etfs/ishares-markit-iboxx-eur-high-yld?cid=46480");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-optimised-chemicals");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-msci-korea-trn-uk?cid=37485");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-india?cid=46155");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-info-tech?cid=46215");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-em-asia-de");
		listaURL_02.add("https://es.investing.com/etfs/db-msci-asia-x-jap.-tr-uk?cid=46398");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-em-asia-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-macquarie-global-inf.-100?cid=38611");
		listaURL_02.add("https://es.investing.com/etfs/comstage-spi-tr");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-msci-em-asia-trn");
		listaURL_02.add("https://es.investing.com/etfs/stoxx600-opt.-cons.---mat.-source");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-auto.---parts?cid=46104");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxxeuro-600-oil-gas-nr");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx600-auto.--parts-nr");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-optimised-tech-source");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-sli");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-listed-private-eqty?cid=46492");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-china-offshore-50---de");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-msci-canada-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxxeuro-600-travel--lei.?cid=46175");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-tech");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx600-cons.---mat.-nr");
		listaURL_02.add("https://es.investing.com/etfs/ossiamus-minimum-variance-nr");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-ftse-100?cid=45819");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-tech-nr");
		listaURL_02.add("https://es.investing.com/etfs/ishares-stoxx-europe-600-chemicals");
		listaURL_02.add("https://es.investing.com/etfs/hsbc-msci-em-far-east-de");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-nordic?cid=46119");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-usa-islamic-gbp?cid=46821");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-uti.-nr");
		listaURL_02.add("https://es.investing.com/etfs/dbx-msci-indonesia-trn-uk?cid=46726");
		listaURL_02.add("https://es.investing.com/etfs/stoxx600-opt.-per.--house.-goods");
		listaURL_02.add("https://es.investing.com/etfs/comstage-smi");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx600-per.--house.-goods?cid=46174");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-europe-x-emu?cid=45994");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-europe-utilities?cid=45993");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-utilities?cid=46221");
		listaURL_02.add("https://es.investing.com/etfs/ftse-100-tr");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-australia-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-eurostoxx600-trvl---leis.");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-europe-cons.-stp?cid=45992");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-consumer-staples?cid=46219");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-cons.-st.-tr?cid=45990");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-msci-canada-trn-uk?cid=46540");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-taiwan-gbp?cid=45775");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-levdax");
		listaURL_02.add("https://es.investing.com/etfs/ishares-stoxxeuro-600-cons.---mat.");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-spain?cid=45991");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-stiftungs-wachstum");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-switzerland---eur?cid=46134");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-poland?cid=45870");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-iboxx-eur-liq.-high-yield-30?cid=46286");
		listaURL_02.add("https://es.investing.com/etfs/hsbc-euro-stoxx-50");
		listaURL_02.add("https://es.investing.com/etfs/etflab-euro-stoxx-select-div.-30");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-msci-taiwan-trn");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-europe---ie?cid=46485");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-uk?cid=45816");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-taiwan-trn");
		listaURL_02.add("https://es.investing.com/etfs/ishares-stoxx-global-sel-div.-100");
		listaURL_02.add("https://es.investing.com/etfs/easyetf-euro-stoxx-50---a-share?cid=46003");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-optimised-uti.");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-uk?cid=46136");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-europe");
		listaURL_02.add("https://es.investing.com/etfs/ishares-eb.rexx-jumbo-pfanbdriefe");
		listaURL_02.add("https://es.investing.com/etfs/ishares-smi");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-msci-china-trn-uk?cid=46543");
		listaURL_02.add("https://es.investing.com/etfs/comstage-fr-euro-stoxx-50");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-health-care-tr?cid=46008");
		listaURL_02.add("https://es.investing.com/etfs/hsbc-msci-europe-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx600-financial-svs");
		listaURL_02.add("https://es.investing.com/etfs/ishares-stoxx-europe-600-de");
		listaURL_02.add("https://es.investing.com/etfs/powershares-ftse-rafi-europe-fund?cid=45897");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-china");
		listaURL_02.add("https://es.investing.com/etfs/euro-stoxx-50-distributing-source");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-hong-kong---hsi?cid=46152");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-south-africa-(ftse-jse)?cid=45824");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-europe-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx600-insurance");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-europe-banks-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-pacific-ex-japan-uk?cid=45777");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-s-p-asx-200");
		listaURL_02.add("https://es.investing.com/etfs/hsbc-msci-pacific-x-japan-de");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-em-eastern-europetr");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-stoxx-glbl-div-100?cid=46096");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-emerging-mrkt-islamic?cid=46605");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-europe-lc");
		listaURL_02.add("https://es.investing.com/etfs/ishares-ftse-birc-50---ie?cid=38607");
		listaURL_02.add("https://es.investing.com/etfs/ishares-$-tips?cid=38619");
		listaURL_02.add("https://es.investing.com/etfs/rbs-market-access-dax-global-birc");
		listaURL_02.add("https://es.investing.com/etfs/etflab-euro-stoxx-50");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-pacific-x-japan-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-stoxx-600-pers-housld-gds");
		listaURL_02.add("https://es.investing.com/etfs/ishares-ftse-nareit-dev.-markets?cid=38605");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-msci-em-emea-trn");
		listaURL_02.add("https://es.investing.com/etfs/ishares-ftse-nareit-asia-fd?cid=38604");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-canada-de");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-smi?cid=45945");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-europe?cid=46183");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-asia-pacific-div.-30?cid=38615");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-telecom-ser.-tr?cid=46041");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-s-p-tsx-60?cid=46227");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-europe-real-estate?cid=46182");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-ftse-250?cid=46097");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-s-p-asx-200?cid=45957");
		listaURL_02.add("https://es.investing.com/etfs/db-feds-effective-rate-tr");
		listaURL_02.add("https://es.investing.com/etfs/comstage-cbfeds-effective-rate-tr");
		listaURL_02.add("https://es.investing.com/etfs/etflab-stoxx-europe-50");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-s-p-select-frontier");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-health-care?cid=46172");
		listaURL_02.add("https://es.investing.com/etfs/euro-stoxx-optimised-banks-source");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-ftse-all-share?cid=46093");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-optimised-health-care");
		listaURL_02.add("https://es.investing.com/etfs/dj-stoxx600-health-care");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx600-health-care-nr");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-ftse-100-ucits-dr?cid=46101");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx600-auto-parts");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-far-est-x-jap.-smlcp?cid=45872");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-media-nr");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-real-estate?cid=46031");
		listaURL_02.add("https://es.investing.com/etfs/easyetf-nmx30-infr.-global");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-em-europe-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-media?cid=46181");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-south-africa-de");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-opt.-banks-source-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-ftseurofirst-80---ie?cid=46478");
		listaURL_02.add("https://es.investing.com/etfs/hsbc-ftse-100-de");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-world-energy---eur?cid=45995");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-optimised-media-source");
		listaURL_02.add("https://es.investing.com/etfs/ishares-ftse-nareit-us-prop-fd?cid=38606");
		listaURL_02.add("https://es.investing.com/etfs/ishares-euro-stoxx-(de)-de");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-russia-30-cappedtr");
		listaURL_02.add("https://es.investing.com/etfs/ishares-ftseurofirst-100---ie?cid=38603");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-ftse-xinhua-china-25?cid=45808");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-oil---gas?cid=46021");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-usa-real-estate?cid=46188");
		listaURL_02.add("https://es.investing.com/etfs/db-msci-efm-africa-top-50-captr");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-uti.");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-energy-tr?cid=46033");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx600-basic-res.-nr");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-health-care?cid=46222");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-europe-healthcare?cid=45977");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-retail-nr");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-retail?cid=46035");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-em-latin-america-de");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-banks-nr");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx600-health-care");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-optimised-basic-res.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-emerging-mkts-infr.?cid=46608");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-eurozone-sust.-screened");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-em-latin-america-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-asia-pac.-div.-30");
		listaURL_02.add("https://es.investing.com/etfs/ishares-eb.rexx-money-market");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-optimised-retail-source");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-dj-euro-stoxx-50-etf?cid=45814");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-msci-malaysia-trn-uk?cid=46742");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-privex?cid=46192");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-russia?cid=45845");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-ftse-100?cid=46088");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-telecom-services?cid=46220");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-europe-telecom-ser.?cid=46135");
		listaURL_02.add("https://es.investing.com/etfs/etfs-forward-l.stock-djubsci-f3-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-euro-stoxx-50-uk?cid=45813");
		listaURL_02.add("https://es.investing.com/etfs/ishares-ftse-xinhua-china25?cid=45780");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-msci-mexico-trn-uk?cid=46743");
		listaURL_02.add("https://es.investing.com/etfs/ishares-stoxx-euro-600-retail");
		listaURL_02.add("https://es.investing.com/etfs/dj-stoxx600-telecommunications");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-ii-sterling-cash-etf");
		listaURL_02.add("https://es.investing.com/etfs/easyetf-epra-eurozone?cid=45999");
		listaURL_02.add("https://es.investing.com/etfs/xetra-gold");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-china-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx600-media");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-telecom-nr");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-malaysia?cid=45812");
		listaURL_02.add("https://es.investing.com/etfs/ishares-euro-stoxx-50-(de)-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-telecom?cid=46040");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-china-enterprise-(hscei)?cid=45807");
		listaURL_02.add("https://es.investing.com/etfs/db-physical-gold-etc-(eur)-de");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-optimised-telecom-source");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-euro-stoxx-50");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-europe-fund?cid=45898");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-japan-a");
		listaURL_02.add("https://es.investing.com/etfs/etfs-physical-swiss-gold-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-physical-gold-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-euro-stoxx-50?cid=45829");
		listaURL_02.add("https://es.investing.com/etfs/rbs-market-access-rici-metals");
		listaURL_02.add("https://es.investing.com/etfs/comstage-hscei");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-msci-em-latam-trn");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx-50?cid=46056");
		listaURL_02.add("https://es.investing.com/etfs/comstage-psi-20?cid=46190");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-eastern-europe-(cece-eur)-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-em-latin-america?cid=46173");
		listaURL_02.add("https://es.investing.com/etfs/ishares-stoxx-europe-50");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-utilities?cid=46024");
		listaURL_02.add("https://es.investing.com/etfs/etfs-gold-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-physical-pm-basket-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-eurostoxx-600-technology");
		listaURL_02.add("https://es.investing.com/etfs/db-stoxxeuro600-basic-res.");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-msci-brazil-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-foward-ex-energy-dj-ubsci-f3");
		listaURL_02.add("https://es.investing.com/etfs/ishares-atx-(de)");
		listaURL_02.add("https://es.investing.com/etfs/etfs-forward-ind.-metals-dj-ubsci");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-msci-brazil-trn-uk?cid=46295");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-russia---dj-russia-gdr?cid=46163");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx600-oil-gas");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-latin-america---gbp?cid=37521");
		listaURL_02.add("https://es.investing.com/etfs/etfs-copper-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-ex-energy-dj-ubsci-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-eurostoxx-600-real-estate");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-dj-euro-stoxx-div30");
		listaURL_02.add("https://es.investing.com/etfs/etfx-dj-ubs-all-comm.-fwd-3mth-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-basic-res.?cid=46068");
		listaURL_02.add("https://es.investing.com/etfs/rbs-market-access-daxglobal-russia");
		listaURL_02.add("https://es.investing.com/etfs/etfs-precious-metals-dj-ubsci-de");
		listaURL_02.add("https://es.investing.com/etfs/db-physical-gold-euro-hdg-etc-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-eurostoxx-600-telecom--de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-turkey-gbp?cid=44548");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-eastern-europe-10-40?cid=46484");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-euro-stoxx-select-div.?cid=46474");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-turkey---dj-turkey-titans-20?cid=46015");
		listaURL_02.add("https://es.investing.com/etfs/etfs-fwd-all-comm.-dj-ubsci-f3-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-brazil-ibovespa-pa?cid=46022");
		listaURL_02.add("https://es.investing.com/etfs/ishares-euro-stoxx-div.-30-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-new-energy?cid=46168");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-brazil-gbp?cid=45778");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx600-basicresources");
		listaURL_02.add("https://es.investing.com/etfs/hsbc-msci-brazil-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-zinc-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-industrial-metals-dj-ubsci-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-eurostoxx-600-utilities");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-ftse-mib-index");
		listaURL_02.add("https://es.investing.com/etfs/ishares-euro-stoxx-telecom---de");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-stoxx-euro-600-banks");
		listaURL_02.add("https://es.investing.com/etfs/etfs-physical-platinum-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-stoxx-euro-select-div.-30");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-select-div.-30?cid=46202");
		listaURL_02.add("https://es.investing.com/etfs/etfx-daxglobal-gold-mining-fund");
		listaURL_02.add("https://es.investing.com/etfs/db-physical-platinum-euro-hdg-de");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-ftse-vietnam-uk?cid=37490");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-banks?cid=46171");
		listaURL_02.add("https://es.investing.com/etfs/etfs-aluminium-de");
		listaURL_02.add("https://es.investing.com/etfs/db-physical-silver-etc-(eur)-de");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-dblci-oy-bal.---eur");
		listaURL_02.add("https://es.investing.com/etfs/etfs-physical-silver-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-silver-de");
		listaURL_02.add("https://es.investing.com/etfs/comstage-nyse-arca-gold-bugs");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx600-banks");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-us-energy-sector-fund?cid=21520");
		listaURL_02.add("https://es.investing.com/etfs/etfs-forward-petrol.-djubsci-f3-de");
		listaURL_02.add("https://es.investing.com/etfs/db-physical-silver-euro-hdg-etc-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-euro-stoxx-banks---de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-petroleum-dj-ubsci-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-nickel-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-foward-energy-dj-ubsci-f3");
		listaURL_02.add("https://es.investing.com/etfs/ishares-global-clean-energy?cid=45836");
		listaURL_02.add("https://es.investing.com/etfs/etfs-leve.-platinum-dj-ubsci-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-energy-dj-ubsci");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-ftse-athex-20?cid=46161");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-msci-europe-de");
		LISTA_URL_GROUP.add(new URLGroup("max-ger", listaURL_02));
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
