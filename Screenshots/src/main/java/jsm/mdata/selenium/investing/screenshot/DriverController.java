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
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-foward-ex-energy-dj-ubsci-f3");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-(ie)-iboxx-usd-govt-1-3-uk?cid=38652");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-sov.-germany-cap-1-5-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-iboxx-usd-govt-3-7");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-15-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-5-7-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/coms.-%E2%82%AC-liq.-sov.-diver.-10-15-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-sov.-germany-cap-10-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-iboxx-usd-govt-7-10");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-3-5-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-25-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-iboxx-eur-govt-7-10");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-7-10-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-ex-energy-dj-ubsci-de");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liquid-sov.-diver.-10");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-gov-bond-5-7-eur-uk?cid=46456");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-sov.-ger.-cap-5-10-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liquid-sov.-diver.-1-10");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-ii-%E2%82%AC-sov.-euro-25-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-foward-energy-dj-ubsci-f3");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-iboxx-eur-govt-1-3?cid=45788");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-forward-ind.-metals-dj-ubsci");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-sov.-diver.-overall-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liquid-sov.-diver.-3-5");
		LISTA_URLS.add("https://es.investing.com/etfs/cs-etf-(ie)-on-iboxx-eur-govt-3-7");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liquid-sov.-diver.-7-10");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-ii-%E2%82%AC-sov.-euro-15-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-retail-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-euromts-covered-bond-agg.?cid=46145");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liquid-sov.-diver.-5-7");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-10-15-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/rbs-market-access-rici-metals");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-%E2%82%AC-liquid-sov.-1-3-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx-euro-600-telecom-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-ii-global-sov.-1c");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-retail-source");
		LISTA_URLS.add("https://es.investing.com/etfs/easyetf-nmx30-infr.-global");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-gov.-bond-15-30-it?cid=38602");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-euro-corporate-bond?cid=46127");
		LISTA_URLS.add("https://es.investing.com/etfs/comstage-stoxx600-health-care-nr");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-global-infl.-link-bond-$?cid=46479");
		LISTA_URLS.add("https://es.investing.com/etfs/db-em-liquid-eurobond---eur");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-7-10-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-retail?cid=46035");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-deutsche-borse-germany-10");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-new-energy?cid=46168");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-%E2%82%AC-liquid-sov.-diver.-1-3");
		LISTA_URLS.add("https://es.investing.com/etfs/stoxx-600-optimised-telecom-source");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-active-us-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-markit-%E2%82%AC-sov.-cap-1.5-10.5");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-euro-tr.-bond?cid=46457");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-deutsche-borse-germany-1-3");
		LISTA_URLS.add("https://es.investing.com/etfs/global-x-china-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-deutsche-borse-germany-3-5");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-emerging-mkts-infr.?cid=46608");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-gov-bond-7-10-eur-uk?cid=37559");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-3-5-tr-1c");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-usd-corporate-bond?cid=38601");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-$-treasury-bond-7-10?cid=38618");
		LISTA_URLS.add("https://es.investing.com/etfs/dj-stoxx600-telecommunications");
		LISTA_URLS.add("https://es.investing.com/etfs/db-x-trackers-ii-crossover-5-y-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etracs-bloomberg-cmci-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-gov.-bond-3-5-it?cid=37558");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eur-covered-bond?cid=46482");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-%E2%82%AC-government-bond-1-3?cid=38600");
		LISTA_URLS.add("https://es.investing.com/etfs/nq-clean-edge-smart-grid-infras.");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-ii-%E2%82%AC-sov.-euro-5-7-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/ubs-etracs-cmci-gold-tr");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-glbl-water?cid=46491");
		LISTA_URLS.add("https://es.investing.com/etfs/dj-stoxx600-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-gold-de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-citigroup-global-gov.-bd-$?cid=46459");
		LISTA_URLS.add("https://es.investing.com/etfs/ipath-dj-ubs-pr.-metals-tr-subin.");
		LISTA_URLS.add("https://es.investing.com/etfs/etfx-daxglobal-gold-mining-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-corp.-bond-1-5?cid=46465");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-world-water?cid=46017");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-health-care?cid=46172");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-telecom?cid=46040");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-stoxx-euro-600-retail");
		LISTA_URLS.add("https://es.investing.com/etfs/lyxor-commodity-crb-x-energy-tr?cid=45989");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-energy-dj-ubsci");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-nareit-asia-fd?cid=38604");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-deutsche-borse-germany");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-s-p-global-infr.-uk?cid=38694");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-$-treasury-bond-1-3?cid=38620");
		LISTA_URLS.add("https://es.investing.com/etfs/ipath-dj-ubs-energy-tr-sub-index");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-ii-%E2%82%AC-sov.-euro-tr-1c");
		LISTA_URLS.add("https://es.investing.com/etfs/db-xtrackers-%E2%82%AC-sov.-euro-1-3-tr-1c");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-euro-stoxx-telecom---de");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-industrial-metals-dj-ubsci-de");
		LISTA_URLS.add("https://es.investing.com/etfs/powers.-wilderhill-prog.-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-epra-nareit-europe");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-precious-metals-dj-ubsci-de");
		LISTA_URLS.add("https://es.investing.com/etfs/ipath-dj-ind.-metals-tr-subindex");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eb.rexx-gov-germany-10,5");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-insured-ny-mun.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-jp-usd-emerging-mkts-bond?cid=46602");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-epra-nareit-global-re");
		LISTA_URLS.add("https://es.investing.com/etfs/etflab-deutsche-borse-germany-5-10");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx-600-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/s-p-emerging-mrkt-infrastructure");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nareit-real-estate-50");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eur-corporate-bond?cid=46481");
		LISTA_URLS.add("https://es.investing.com/etfs/marketvectors-nuclear-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-global-em-infr.");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-physical-gold-de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eb.rexx-gov-germany");
		LISTA_URLS.add("https://es.investing.com/etfs/marketvectors-global-alt.-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eb.rexx-gov-ger.-2.5-5.5");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-lehman-gov.-credit-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-cali-muni-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-cleantech-portfolio");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh.-global-gold---pr.-metals");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-global-clean-energy?cid=45836");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-nareit-us-prop-fd?cid=38606");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nareit-european-prop.-fund?cid=38612");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-insured-ca-mun.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-stoxx600-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/pimco-inter.-mun.-bond-strategy");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-nareit-dev.-markets?cid=38605");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eb.rexx-gov-ger.-1.5-2.5");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-mortgage-backed-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-ise-water");
		LISTA_URLS.add("https://es.investing.com/etfs/wisdomtree-intl-real-estate-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eurostoxx-600-telecom--de");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-eb.rexx-gov-ger.-5.5-10.5");
		LISTA_URLS.add("https://es.investing.com/etfs/pimco-1-3-year-us-treasury-index");
		LISTA_URLS.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-barclays-agency-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-retail");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttr.-nq-clean-edge-green-en.");
		LISTA_URLS.add("https://es.investing.com/etfs/rogers-intl-commodity-metal");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-clean-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-pharmaceutical-index");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-lehman-interm.-term-treasury");
		LISTA_URLS.add("https://es.investing.com/etfs/guggenheim-canada-energy-income");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-telecom.");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-sh.term-nat.-muni-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/claymore-china-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares---global-clean-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-dynamic-energy-e-p");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftse-nareit-residential");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-inter.-term-gov-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-lehman-10-20-y-tr.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-ise-global-wind-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-inter.-gov.-credit-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-lehman-credit-bond-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-telecom");
		LISTA_URLS.add("https://es.investing.com/etfs/rogers-intl-commodity-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-healthcare");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-global-water");
		LISTA_URLS.add("https://es.investing.com/etfs/guggenheim-s-p-global-water");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-db-gold-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-db-precious-metals");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh.-dynamic-biotech---genome");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh-wilderhill-clean-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-aggregate-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-health-care-providers");
		LISTA_URLS.add("https://es.investing.com/etfs/etfs-physical-swiss-gold-shares");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-10-year-credit-bond-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/powersh.-dynamic-pharmaceuticals");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-db-energy-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-db-intl-gov.-tips-prot.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-amex-biotech");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-pharmaceuticals");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-infrastructure");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-health-care-index");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-s-p-reit-index-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-interm.-term-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-ftepra-nareit-dev.-re-x-us");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-wilshire-global-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/salix-pharmaceuticals-ltd.");
		LISTA_URLS.add("https://es.investing.com/etfs/claymore-mac-global-solar-energy-in");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nareit-mortgage-reits");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-lehman-municipal-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/golden-dragon-halter-usx-china");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-global-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-health-care-alphadex");
		LISTA_URLS.add("https://es.investing.com/etfs/build-america-bond-portfolio");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-energy");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-ins.-nat.-mun.-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-db-base-metals-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/xetra-gold");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-s-p-national-mun-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-dj-wilshire-reit");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-lehman-intl-treasury-bd");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-water-resource-port");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-bond-etf");
		LISTA_URLS.add("https://es.investing.com/etfs/firsttrust-energy-alphadex-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-djsu-telecommunications");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-high-yield-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-interm.-term-corp-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-dj-wilshire-intl-real-estate");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-lehman-1-3-y-credit-bond?cid=45158");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-convertible-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-dj-us-energy-sector-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/powershares-em-sovereign-debt");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-nasdaq-biotech");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-total-bond-market");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-inv-g-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-biotech");
		LISTA_URLS.add("https://es.investing.com/etfs/ishare-msci-germany");
		LISTA_URLS.add("https://es.investing.com/etfs/vanguard-reit");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-metals---mining");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-h-y-corporate-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/ishares-comex-gold-trust");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr---health-care");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-barclays-high-yield-bond");
		LISTA_URLS.add("https://es.investing.com/etfs/market-vectors-junior-gold-miners");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-gold-trust");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-s-p-retail");
		LISTA_URLS.add("https://es.investing.com/etfs/spdr-energy-select-sector-fund");
		LISTA_URLS.add("https://es.investing.com/etfs/market-vectors-gold-miners");
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
