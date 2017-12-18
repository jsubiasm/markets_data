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
		// -- var-volumen-usa
		// --
		List<String> listaURL_01 = new ArrayList<String>();
		listaURL_01.add("https://es.investing.com/etfs/global-x-lithium");
		listaURL_01.add("https://es.investing.com/etfs/spdr-russell-1000");
		listaURL_01.add("https://es.investing.com/etfs/spdr-russell-small-cap-comp");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-china-div-ex-fincls");
		listaURL_01.add("https://es.investing.com/etfs/marketv.-rare-earth-strat.-metals");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-china-technology");
		listaURL_01.add("https://es.investing.com/etfs/ishares-nareit-real-estate-50");
		listaURL_01.add("https://es.investing.com/etfs/wcm-bny-mellon-focused-growth-adr");
		listaURL_01.add("https://es.investing.com/etfs/spdr-dj-wilshire-total-market");
		listaURL_01.add("https://es.investing.com/etfs/spdr-russell-2000");
		listaURL_01.add("https://es.investing.com/etfs/kraneshares-csi-china-internet");
		listaURL_01.add("https://es.investing.com/etfs/global-x-mlp");
		listaURL_01.add("https://es.investing.com/etfs/spdr-cap.-invest-grade-float-rate");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ibds-mar-2020-corp-trm");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-ubs-cocoa-subindex-tr");
		listaURL_01.add("https://es.investing.com/etfs/global-x-china-consumer");
		listaURL_01.add("https://es.investing.com/etfs/marketv.-fallen-angel-high-y-bond");
		listaURL_01.add("https://es.investing.com/etfs/marketv.-invest.-grade-float-rate");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-emerging-markets");
		listaURL_01.add("https://es.investing.com/etfs/united-states-heating-oil-fund-lp");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-aerospace---defense");
		listaURL_01.add("https://es.investing.com/etfs/ishares-0-5-year-invmt-grd-corp-bd");
		listaURL_01.add("https://es.investing.com/etfs/ishares-djsu-aerospace---defense");
		listaURL_01.add("https://es.investing.com/etfs/msci-europe-financials-secto");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-india-small-cap");
		listaURL_01.add("https://es.investing.com/etfs/powershares-fund.-invest-corp-bond");
		listaURL_01.add("https://es.investing.com/etfs/first-trust-preferred-sec-inc");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ibds-mar-2020-corp-exfincl");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-semiconductors");
		listaURL_01.add("https://es.investing.com/etfs/spdr-barclays-em-local-bond");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-usa-value-factor");
		listaURL_01.add("https://es.investing.com/etfs/first-trust-bick");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-global-gold-miners-be?cid=44703");
		listaURL_01.add("https://es.investing.com/etfs/powershares-glbl-s-t-hi-yld-bd");
		listaURL_01.add("https://es.investing.com/etfs/spdr-barclays-interm.-term-bond");
		listaURL_01.add("https://es.investing.com/etfs/spdr-blackstone-gso-sen-loan");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-brazil-small-cap");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-usa-moment-factor");
		listaURL_01.add("https://es.investing.com/etfs/recon-capital-nasdaq-100-covered");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-500-value");
		listaURL_01.add("https://es.investing.com/etfs/ishares-russell-3000-value");
		listaURL_01.add("https://es.investing.com/etfs/global-x-copper-miners");
		listaURL_01.add("https://es.investing.com/etfs/teucrium-soybean");
		listaURL_01.add("https://es.investing.com/etfs/powersharesamental-pure-mid-growth");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-em-consumer-growth");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-nasdaq-cea-smartphone");
		listaURL_01.add("https://es.investing.com/etfs/vix-medium-term-etn");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-aig-nickel");
		listaURL_01.add("https://es.investing.com/etfs/schwab-fdmtl-us-small-company");
		listaURL_01.add("https://es.investing.com/etfs/teucrium-sugar");
		listaURL_01.add("https://es.investing.com/etfs/direxion-nq-100-equal-weighted");
		listaURL_01.add("https://es.investing.com/etfs/ishares-core-msci-eafe");
		listaURL_01.add("https://es.investing.com/etfs/iq-arb-merger-arbitrage");
		listaURL_01.add("https://es.investing.com/etfs/proshares-30-year-tips-tsy-spread");
		listaURL_01.add("https://es.investing.com/etfs/ishares-russell-top-200-growth");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-em-local-curr.-bond");
		listaURL_01.add("https://es.investing.com/etfs/ishares-0-5-y-high-yld-corp-bd");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ibds-mar-2023-corp-trm");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-interm.-term-corp-bond");
		listaURL_01.add("https://es.investing.com/etfs/global-x-ftse-argentina-20");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-ubs-sugar-subindex-tr");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-400-mid-cap-value");
		listaURL_01.add("https://es.investing.com/etfs/alps-alerian-energy-infra");
		listaURL_01.add("https://es.investing.com/etfs/ishares-em-high-yield-bond");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-mortgage-backed-sec.");
		listaURL_01.add("https://es.investing.com/etfs/schwab-fdmtl-emerg-mkts-lg-co");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-germany-small-cap");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-clean-energy");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-shrt-term-infl-prot-sec");
		listaURL_01.add("https://es.investing.com/etfs/rydex-s-p-smallcap-600-pure-value");
		listaURL_01.add("https://es.investing.com/etfs/spdr-barclays-aggregate-bond");
		listaURL_01.add("https://es.investing.com/etfs/proshares-vix-mid-term-futures");
		listaURL_01.add("https://es.investing.com/etfs/powershares-kbw-premium-y-eq-reit");
		listaURL_01.add("https://es.investing.com/etfs/first-trust-capital-strength");
		listaURL_01.add("https://es.investing.com/etfs/global-x-fertilizers-potash");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-em-small-cap-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-earnings-500-fund");
		listaURL_01.add("https://es.investing.com/etfs/spdr-russell-2000-low-vol");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-400-mid-cap-growth");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ft-dev.-smlcap-x-north-am.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-comex-gold-trust");
		listaURL_01.add("https://es.investing.com/etfs/spdr-kbw-insurance");
		listaURL_01.add("https://es.investing.com/etfs/global-x-ftse-norway-30");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-midcap-earnings-fund");
		listaURL_01.add("https://es.investing.com/etfs/ishares-russell-3000-growth");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-telecom");
		listaURL_01.add("https://es.investing.com/etfs/schwab-fundamental-intl-sm-co");
		listaURL_01.add("https://es.investing.com/etfs/global-x-uranium");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ibds-mar-2018-corp-trm");
		listaURL_01.add("https://es.investing.com/etfs/spdr-bofa-crossover-corp-bond");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci---kokusai");
		listaURL_01.add("https://es.investing.com/etfs/ishares-inv-g-bond");
		listaURL_01.add("https://es.investing.com/etfs/teucrium-wheat");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-conser.-allocation");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-asia-50");
		listaURL_01.add("https://es.investing.com/etfs/ise-cloud-computing");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ibds-mar-2018-corp-exfincl");
		listaURL_01.add("https://es.investing.com/etfs/proshares-inv-grd-intr-rt-hdgd");
		listaURL_01.add("https://es.investing.com/etfs/msci-global-metals-mining-prod.");
		listaURL_01.add("https://es.investing.com/etfs/alerian-mlp");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-build---const.");
		listaURL_01.add("https://es.investing.com/etfs/spdr-barclays-1-10-year-tips");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-china");
		listaURL_01.add("https://es.investing.com/etfs/ishares-core-1-5-usd-mu?cid=45138");
		listaURL_01.add("https://es.investing.com/etfs/ishares-core-msci-emerging-markets");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-india");
		listaURL_01.add("https://es.investing.com/etfs/powershares-cleantech-portfolio");
		listaURL_01.add("https://es.investing.com/etfs/s-p-emerging-markets-high-beta");
		listaURL_01.add("https://es.investing.com/etfs/ishares-barclays-us-treasury-bond");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-ind.-metals-tr-subindex");
		listaURL_01.add("https://es.investing.com/etfs/etracs-alerian-mlp-infrastructure");
		listaURL_01.add("https://es.investing.com/etfs/pwshr-s-p-4");
		listaURL_01.add("https://es.investing.com/etfs/global-x-nasdaq-china-technology");
		listaURL_01.add("https://es.investing.com/etfs/ishares-core-s-p-tot-us-stock-mrkt");
		listaURL_01.add("https://es.investing.com/etfs/united-states-gasoline-fund-lp");
		listaURL_01.add("https://es.investing.com/etfs/ipath-dj-ubs-coffee-tr-sub-index");
		listaURL_01.add("https://es.investing.com/etfs/market-vectors-bdc-income");
		listaURL_01.add("https://es.investing.com/etfs/velocityshares-daily-invers-vix");
		listaURL_01.add("https://es.investing.com/etfs/marketvectors-poland");
		listaURL_01.add("https://es.investing.com/etfs/schwab-us-tips");
		listaURL_01.add("https://es.investing.com/etfs/rydex-currencyshares-gbp-tr.");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-nq-100-technology");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-value-line-100-fund");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-usa-size-factor");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-timber---for.");
		listaURL_01.add("https://es.investing.com/etfs/powershares-s-p-midcap-low-vol");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-dm-x-us-alphadex-fund");
		listaURL_01.add("https://es.investing.com/etfs/spdr-barclays-srt-term-trsy-bd");
		listaURL_01.add("https://es.investing.com/etfs/powersharesamental-pure-large");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-s-p-mid-cap-400-growth");
		listaURL_01.add("https://es.investing.com/etfs/barclays-ipath-pure-beta-energy");
		listaURL_01.add("https://es.investing.com/etfs/global-x-mlp---energy-infrs");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-north-am.-energy-infr.");
		listaURL_01.add("https://es.investing.com/etfs/rydex-currencyshares-swedish-krona");
		listaURL_01.add("https://es.investing.com/etfs/yieldshares-high-income");
		listaURL_01.add("https://es.investing.com/etfs/powershares-s-p-smallcap-energy");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-utilities-alphadex-fund");
		listaURL_01.add("https://es.investing.com/etfs/ishares-floating-rate-note-fund");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-mid-cap-core");
		listaURL_01.add("https://es.investing.com/etfs/schwab-fundamental-intl-lg-co");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-asia-pacific-x-japan");
		listaURL_01.add("https://es.investing.com/etfs/ishares-emerging-markets-corp-bond");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-global-technology");
		listaURL_01.add("https://es.investing.com/etfs/etracs-alerian-mlp-index-etn");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-inter.-term-gov-bond");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-s-p-small-cap-600");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-us-quality-dividend-grow");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-health-care-equipment");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-russell-2000");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-world-ex-us");
		listaURL_01.add("https://es.investing.com/etfs/barclays-ipath-pure-beta-cotton");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-smallcap-earnings-fund");
		listaURL_01.add("https://es.investing.com/etfs/quantshares-us-market-neutral-mom.");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-ny-muni");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-cali-muni-bond");
		listaURL_01.add("https://es.investing.com/etfs/schwab-fdmtl-us-large-company");
		listaURL_01.add("https://es.investing.com/etfs/proshares-hedge-replication");
		listaURL_01.add("https://es.investing.com/etfs/advisorshares-newfleet-mult-sect");
		listaURL_01.add("https://es.investing.com/etfs/first-trust-senior-loan");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-600-small-cap-value");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-em-quality-div-growth");
		listaURL_01.add("https://es.investing.com/etfs/firsttrust-technology-alphadex");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-oil---gas-eq---services");
		listaURL_01.add("https://es.investing.com/etfs/ishares-latin-america-40-index");
		listaURL_01.add("https://es.investing.com/etfs/jp-morgan-alerian-mlp-index");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-midcap-dividend-fund");
		listaURL_01.add("https://es.investing.com/etfs/barclays-etn-plus-select-mlp");
		listaURL_01.add("https://es.investing.com/etfs/guggenheim-s-p-global-water");
		listaURL_01.add("https://es.investing.com/etfs/schwab-emerging-markets-equity");
		listaURL_01.add("https://es.investing.com/etfs/powershares-db-agriculture-fund");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-trust-smallcap-dividend");
		listaURL_01.add("https://es.investing.com/etfs/pshares-ftse-rafi-dm-x-us-sml-mid");
		listaURL_01.add("https://es.investing.com/etfs/ishares-global-high-y-corp-bond");
		listaURL_01.add("https://es.investing.com/etfs/ishares-barclays-0-5-y-tips-bond");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-1500-value-tilt");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-moderate-allocation");
		listaURL_01.add("https://es.investing.com/etfs/vanguard-russell-1000-growth");
		listaURL_01.add("https://es.investing.com/etfs/powersh-s-p-smallcap-financials");
		listaURL_01.add("https://es.investing.com/etfs/ishares-2018-amtfree-muni-term");
		listaURL_01.add("https://es.investing.com/etfs/rydex-currencyshares-swiss-franc");
		listaURL_01.add("https://es.investing.com/etfs/ishares-phlx-sox-semiconductor");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-citigroup-intl-treas.");
		listaURL_01.add("https://es.investing.com/etfs/powershares-insured-ca-mun.-bond");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-japan-smallcap-fund");
		listaURL_01.add("https://es.investing.com/etfs/holdrs-merrill-lynch-semiconductor");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-usa-qlty-factor");
		listaURL_01.add("https://es.investing.com/etfs/powershares-db-oil-fund");
		listaURL_01.add("https://es.investing.com/etfs/msci-japan-small-cap");
		listaURL_01.add("https://es.investing.com/etfs/ishares-morningstar-small-value");
		listaURL_01.add("https://es.investing.com/etfs/powershares-aerospace---defense");
		listaURL_01.add("https://es.investing.com/etfs/schwab-us-aggregate-bond");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ibds-mar-2023-corp-exfincl");
		LISTA_URL_GROUP.add(new URLGroup("var-volumen-usa", listaURL_01));
		// --
		// -- var-volumen-ger
		// --
		List<String> listaURL_02 = new ArrayList<String>();
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-1-3-tr-1d");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-ii-%E2%82%AC-germany-3-5-tri");
		listaURL_02.add("https://es.investing.com/etfs/etfs-gold-eur-daily-hedged-de");
		listaURL_02.add("https://es.investing.com/etfs/etflab-stoxx-europe-strong-gr.-20");
		listaURL_02.add("https://es.investing.com/etfs/etflab-stoxx-strong-style-comp.-40");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-emu-values-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-cotton-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-s-p500-vix-fut-enh.-rl?cid=46045");
		listaURL_02.add("https://es.investing.com/etfs/db-physical-palladium-euro-hdg-de");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-ii-sterling-cash-etf");
		listaURL_02.add("https://es.investing.com/etfs/etfs-lean-hogs-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-emerging-mrkt-islamic?cid=46605");
		listaURL_02.add("https://es.investing.com/etfs/comstage-%E2%82%AC-german-cov.-cap-oval-tr");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx600-ind.-goods--ser.");
		listaURL_02.add("https://es.investing.com/etfs/spdr-citi-asia-local-gov-bond-de");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-pacific-x-japan-de");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-ii-markit-iboxx-japan?cid=949283");
		listaURL_02.add("https://es.investing.com/etfs/ubs-msci-world-socially-resp.-a-de");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-%E2%82%AC-sov.-euro-tr-4-d");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-uk-small-cap");
		listaURL_02.add("https://es.investing.com/etfs/ubs-msci-n.-amrca-soc.-resp.-a-de");
		listaURL_02.add("https://es.investing.com/etfs/stoxx-600-optimised-uti.");
		listaURL_02.add("https://es.investing.com/etfs/hsbc-msci-europe-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-euromts-aaa-gov-1-3-y?cid=46177");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-ftse-100-i-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx600-media");
		listaURL_02.add("https://es.investing.com/etfs/etfs-eur-daily-hedge-physical-gold");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-south-africa-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-eurostoxx-600-technology");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-asia-apex-50?cid=45956");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-financials?cid=46223");
		listaURL_02.add("https://es.investing.com/etfs/ubs-msci-pacific-soc.-resp.-a-de");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-telecom-services?cid=46220");
		listaURL_02.add("https://es.investing.com/etfs/amundi-gov-bond-mts-broad-invest?cid=45968");
		listaURL_02.add("https://es.investing.com/etfs/etfs-gasoline-de");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-nikkei-225");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-msci-emu-index");
		listaURL_02.add("https://es.investing.com/etfs/etfs-sugar-de");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-emerging-markets-a");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-em-asia?cid=45840");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx600-insurance");
		listaURL_02.add("https://es.investing.com/etfs/amundi-cash-3-months-mts-invest?cid=45962");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-3-5-tr-1d?cid=949560");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-retail-nr");
		listaURL_02.add("https://es.investing.com/etfs/spdr-barclays-us-treasury-bond-de");
		listaURL_02.add("https://es.investing.com/etfs/db-mts-x-bank-of-italy-btp-de");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-msci-uk?cid=45816");
		listaURL_02.add("https://es.investing.com/etfs/comstage-cac-40?cid=46287");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-atx-de");
		listaURL_02.add("https://es.investing.com/etfs/comstage-sdax-tr");
		listaURL_02.add("https://es.investing.com/etfs/comstage-commerzbank-eonia-tr");
		listaURL_02.add("https://es.investing.com/etfs/ubs-euro--mideast-soc.-res.-a-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-forward-petrol.-djubsci-f3-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-nickel-de");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-europe-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-financials-tr?cid=46006");
		listaURL_02.add("https://es.investing.com/etfs/etflab-stoxx-euro-strong-value-20");
		listaURL_02.add("https://es.investing.com/etfs/etfs-industrial-metals-dj-ubsci-de");
		listaURL_02.add("https://es.investing.com/etfs/db-physical-platinum-euro-hdg-de");
		listaURL_02.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-3-5-tr");
		listaURL_02.add("https://es.investing.com/etfs/hsbc-msci-em-far-east-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-euro-stoxx-smallcap?cid=46475");
		listaURL_02.add("https://es.investing.com/etfs/ishares-ftseurofirst-80---ie?cid=46478");
		listaURL_02.add("https://es.investing.com/etfs/etfs-energy-dj-ubsci");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-europe-consumer-staples?cid=46219");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-emerging-markets?cid=45949");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-msci-europe-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-eurostoxx-600-real-estate");
		listaURL_02.add("https://es.investing.com/etfs/ishares-eurostoxx-600-telecom--de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-timber-forestry-gbp?cid=46090");
		listaURL_02.add("https://es.investing.com/etfs/spdr-barclays-euro-corp-bond");
		listaURL_02.add("https://es.investing.com/etfs/etflab-deutscheb.-eurogov-france");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-new-energy?cid=46168");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-far-est-x-jap.-smlcp?cid=45872");
		listaURL_02.add("https://es.investing.com/etfs/etfs-all-comm.-dj-ubs-eur-hedged");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-msci-europe-energy?cid=45955");
		listaURL_02.add("https://es.investing.com/etfs/comstage-stoxx-europe-600-uti.-nr");
		listaURL_02.add("https://es.investing.com/etfs/etfs-physical-gold-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-msci-world-info-tech-tr?cid=46225");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-emu-small-cap-a-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-brent-crude-de");
		listaURL_02.add("https://es.investing.com/etfs/db-physical-silver-etc-(eur)-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-coffee-de");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-msci-japantr---eur");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-msci-pacific-x-japan-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-physical-platinum-de");
		listaURL_02.add("https://es.investing.com/etfs/db-xtrackers-msci-brazil-trn-uk?cid=46295");
		listaURL_02.add("https://es.investing.com/etfs/cs-etf-(ie)-on-euro-stoxx-50?cid=45848");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-euro-stoxx-midcap?cid=46473");
		listaURL_02.add("https://es.investing.com/etfs/etflab-eur-liq.-ger.-cov.-diver.");
		listaURL_02.add("https://es.investing.com/etfs/etfs-precious-metals-dj-ubsci-de");
		listaURL_02.add("https://es.investing.com/etfs/etfx-dj-ubs-all-comm.-fwd-3mth-de");
		listaURL_02.add("https://es.investing.com/etfs/s-p-commodity-producers-agri.?cid=46488");
		listaURL_02.add("https://es.investing.com/etfs/ishares-barc-cap-emg-mkt-loc-gvt-%C2%A3?cid=46601");
		listaURL_02.add("https://es.investing.com/etfs/etfs-brent-crude-eur-daily-hdg-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-barclays-euro-corp.-bd-eur?cid=46455");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-world-trn");
		listaURL_02.add("https://es.investing.com/etfs/ishares-atx-(de)");
		listaURL_02.add("https://es.investing.com/etfs/comstage-cbcommodity-x-agri.-ew-tr");
		listaURL_02.add("https://es.investing.com/etfs/comstage-msci-emerging-markets-trn");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-stoxx600-auto-parts");
		listaURL_02.add("https://es.investing.com/etfs/amundi-msci-em-asia-de");
		listaURL_02.add("https://es.investing.com/etfs/amundi-etf-stoxx-europe-600?cid=45967");
		listaURL_02.add("https://es.investing.com/etfs/ishares-eurostoxx-600-utilities");
		listaURL_02.add("https://es.investing.com/etfs/easyetf-nmx30-infr.-global");
		listaURL_02.add("https://es.investing.com/etfs/etfs-corn-de");
		listaURL_02.add("https://es.investing.com/etfs/ubs-etf-msci-emu-de");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-msci-emu-smlcap-uk?cid=38651");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-europe-fund?cid=45898");
		listaURL_02.add("https://es.investing.com/etfs/cs-(ie)-on-msci-usa-smlcap-uk?cid=46810");
		listaURL_02.add("https://es.investing.com/etfs/rbs-topix-eur-hedged-index-de");
		listaURL_02.add("https://es.investing.com/etfs/etfs-copper-eur-daily-hedged-de");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-msci-bangladesh-imtr?cid=45810");
		listaURL_02.add("https://es.investing.com/etfs/ishares-$-tips?cid=38619");
		listaURL_02.add("https://es.investing.com/etfs/amundi-cac-40-de");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-eastern-europe-10-40?cid=46484");
		listaURL_02.add("https://es.investing.com/etfs/ishares-stoxx600-ind.-goods--ser.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-global-clean-energy?cid=45836");
		listaURL_02.add("https://es.investing.com/etfs/spdr-barclays-euro-high-yield-bond");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-world-islamic---gbp?cid=46486");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-acwi-imi-de");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-emerging-markets");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-japan-small-cap");
		listaURL_02.add("https://es.investing.com/etfs/ishares-dj-europe-sust-screened-as?cid=46461");
		listaURL_02.add("https://es.investing.com/etfs/etflab-msci-europe-mc");
		listaURL_02.add("https://es.investing.com/etfs/etfs-foward-ex-energy-dj-ubsci-f3");
		listaURL_02.add("https://es.investing.com/etfs/ishares-tecdax-(de)-de");
		listaURL_02.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-telecom?cid=46040");
		listaURL_02.add("https://es.investing.com/etfs/comstage-%E2%82%AC-german-cov.-cap-5-7-tr");
		LISTA_URL_GROUP.add(new URLGroup("var-volumen-ger", listaURL_02));
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
