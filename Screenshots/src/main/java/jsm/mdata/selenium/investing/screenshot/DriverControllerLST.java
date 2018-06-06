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
		// -- ETFs Alemania temática Global y China
		// --
		List<String> listaURL_01 = new ArrayList<String>();
		listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-emerging-markets?cid=45949");
		listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-world-energy---eur?cid=45995");
		listaURL_01.add("https://es.investing.com/etfs/amundi-etf-msci-world-x-emu?cid=45985");
		listaURL_01.add("https://es.investing.com/etfs/amundi-msci-china-de");
		listaURL_01.add("https://es.investing.com/etfs/amundi-msci-world-de");
		listaURL_01.add("https://es.investing.com/etfs/amundi-msci-world-financials---eur");
		listaURL_01.add("https://es.investing.com/etfs/amundi-msci-world-x-europe---eur?cid=46114");
		listaURL_01.add("https://es.investing.com/etfs/comstage-alpha-dividende-plus");
		listaURL_01.add("https://es.investing.com/etfs/comstage-ftse-china-a50?cid=1024667");
		listaURL_01.add("https://es.investing.com/etfs/comstage-hscei");
		listaURL_01.add("https://es.investing.com/etfs/comstage-msci-emerging-markets-trn");
		listaURL_01.add("https://es.investing.com/etfs/comstage-msci-world-trn");
		listaURL_01.add("https://es.investing.com/etfs/comstage-s-p-smit-40-trn?cid=1024623");
		listaURL_01.add("https://es.investing.com/etfs/cs-etf(ie)on-cs-gbl-altrn.engy");
		listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-csi-300-index?cid=46094");
		listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-ftse-xinhua-china-25?cid=45808");
		listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-harvest-csi300");
		listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-msci-ac-world-index");
		listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-msci-china-trn-uk?cid=46543");
		listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-msci-em-emea-trn");
		listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-msci-em-trn-idx-etf?cid=45821");
		listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-msci-world-trn-uk?cid=46409");
		listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-msci-wrd-hlth-care-de");
		listaURL_01.add("https://es.investing.com/etfs/db-x-trackers-portfolio-tr-index?cid=1056526");
		listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-s-p-global-infr.-uk?cid=38694");
		listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-s-p-select-frontier");
		listaURL_01.add("https://es.investing.com/etfs/db-xtrackers-stoxx-glbl-div-100?cid=46096");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-world-dr-1d");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-world-energy-dr-1c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-world-finls-dr-1c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-world-indust-dr-1c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-world-mats-dr-1c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-world-minvol-dr-1c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-world-qlt-fct-dr-1c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-world-util-dr-1c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-world-val-fct-dr-1c");
		listaURL_01.add("https://es.investing.com/etfs/db-xtrack-msci-wrld-cons-disc-1c?cid=1024387");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-wrld-consstpl-dr-1c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrack-msci-wrld-tlcom-srvc-dr-1c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrck-msci-world-4c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrck-msci-world-mom-fact-dr1c");
		listaURL_01.add("https://es.investing.com/etfs/dbxtrck-msci-wrld-infotech-dr-1c-mi?cid=1009153");
		listaURL_01.add("https://es.investing.com/etfs/etflab-msci-china");
		listaURL_01.add("https://es.investing.com/etfs/etflab-msci-emerging-markets");
		listaURL_01.add("https://es.investing.com/etfs/etfs-ise-cyber-sec-go-accum");
		listaURL_01.add("https://es.investing.com/etfs/etfx-daxglobal-gold-mining-fund");
		listaURL_01.add("https://es.investing.com/etfs/fundlogic-alter-plc-ms-scibeta-glbl");
		listaURL_01.add("https://es.investing.com/etfs/hang-seng-h-share?cid=1010571");
		listaURL_01.add("https://es.investing.com/etfs/hsbc-eco-scale-worldwide-eq-ucits");
		listaURL_01.add("https://es.investing.com/etfs/hsbc-msci-world");
		listaURL_01.add("https://es.investing.com/etfs/hsbc-multifactor-worldwide-eq-ucits");
		listaURL_01.add("https://es.investing.com/etfs/icbc-crsuiss-wtre-sp-china-500-b-de");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ageing-population-acc?cid=1037050");
		listaURL_01.add("https://es.investing.com/etfs/ishares-automation-robotics-acc?cid=1009408");
		listaURL_01.add("https://es.investing.com/etfs/ishares-core-msci-em-imi?cid=1024491");
		listaURL_01.add("https://es.investing.com/etfs/ishares-digitalisation-acc?cid=1080073");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-china-offshore-50---de");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-em-select-dividend-%C2%A3?cid=46600");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-global-sust-screened-uk?cid=38617");
		listaURL_01.add("https://es.investing.com/etfs/ishares-dj-global-titans-50");
		listaURL_01.add("https://es.investing.com/etfs/ishares-edge-msci-world-mf-gbp?cid=994123");
		listaURL_01.add("https://es.investing.com/etfs/ishares-edge-msci-world-min-vol");
		listaURL_01.add("https://es.investing.com/etfs/ishares-edge-msci-wrld-multihdg-acc");
		listaURL_01.add("https://es.investing.com/etfs/ishares-exponential-tech-mu");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-birc-50---ie?cid=38607");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-nareit-dev.-markets?cid=38605");
		listaURL_01.add("https://es.investing.com/etfs/ishares-ftse-xinhua-china25?cid=45780");
		listaURL_01.add("https://es.investing.com/etfs/ishares-global-clean-energy?cid=45836");
		listaURL_01.add("https://es.investing.com/etfs/ishares-hc-innov-acc-mi?cid=1009026");
		listaURL_01.add("https://es.investing.com/etfs/ishares-macquarie-global-inf.-100?cid=38611");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-china-a-ucits-usd?cid=994116");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-em-consumer-growth");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-emerging-market?cid=46607");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-emerging-mrkt---acc?cid=46604");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-em-minimum-volatility?cid=994135");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-em-smlcap-$?cid=46606");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-global-gold-miners-be");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-world---acc?cid=45873");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-world---gbp?cid=38614");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-world-momentum-factor");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-world-mon.-hedged?cid=46487");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-wrld-quality-factor");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-wrld-size-factor");
		listaURL_01.add("https://es.investing.com/etfs/ishares-msci-wrld-value-factor");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-comm.-prod.-oil-gas");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-commodity-prod.-gold");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-emerging-mkts-infr.?cid=46608");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-glbl-water?cid=46491");
		listaURL_01.add("https://es.investing.com/etfs/ishares-s-p-listed-private-eqty?cid=46492");
		listaURL_01.add("https://es.investing.com/etfs/ishares-stoxx-global-sel-div.-100");
		listaURL_01.add("https://es.investing.com/etfs/ishares-sust-msci-em-sri");
		listaURL_01.add("https://es.investing.com/etfs/ishares-v-msci-acwi?cid=46483");
		listaURL_01.add("https://es.investing.com/etfs/ishrs-edge-msci-wrld-minvol-hdg-acc");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-china-enterprise-(hscei)?cid=45807");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-all-country-world-de");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-emerging-markets?cid=46020");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world?cid=46273");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-msci-world-cons.-st.-tr?cid=45990");
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
		listaURL_01.add("https://es.investing.com/etfs/lyxor-privex?cid=46192");
		listaURL_01.add("https://es.investing.com/etfs/lyxor-world-water?cid=46017");
		listaURL_01.add("https://es.investing.com/etfs/msci-emerging-mrktssource-de");
		listaURL_01.add("https://es.investing.com/etfs/msci-world-source?cid=46070");
		listaURL_01.add("https://es.investing.com/etfs/ossiam-em-min-variance-nr-(eur)-de");
		listaURL_01.add("https://es.investing.com/etfs/powershares-dynamic-europe-fund?cid=45898");
		listaURL_01.add("https://es.investing.com/etfs/rbs-market-access-dax-global-birc");
		listaURL_01.add("https://es.investing.com/etfs/robo-stox-global-robotics");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-acwi?cid=46046");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-acwi-imi-de");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-emerging-markets?cid=45850");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-em-smlcap?cid=45851");
		listaURL_01.add("https://es.investing.com/etfs/spdr-msci-world-small-cap?cid=1024392");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-emerging-mrktsdividend-uk?cid=45833");
		listaURL_01.add("https://es.investing.com/etfs/spdr-s-p-global-dividend-aristocrat?cid=1024489");
		listaURL_01.add("https://es.investing.com/etfs/ubs-(irl)-dj-global-select-dividend?cid=949270");
		listaURL_01.add("https://es.investing.com/etfs/ubs-etf-msci-emerging-markets-a");
		listaURL_01.add("https://es.investing.com/etfs/ubs-etf-msci-world-a");
		listaURL_01.add("https://es.investing.com/etfs/ubs-irl-plc-solactive-glbl-oil-adis?cid=1024391");
		listaURL_01.add("https://es.investing.com/etfs/ubs-irl-plc-sol-glbl-gold-min-adis?cid=1024390");
		listaURL_01.add("https://es.investing.com/etfs/ubs-msci-em-socially-resp-adis");
		listaURL_01.add("https://es.investing.com/etfs/ubs-msci-world-socially-resp.-a-de");
		listaURL_01.add("https://es.investing.com/etfs/ubs-plc-msci-world-(usd)-a-de");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-em-equity-incacc");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-em-equity-income-de");
		listaURL_01.add("https://es.investing.com/etfs/wisdomtree-em-smallcap-de");
		LISTA_URL_GROUP.add(new URLGroup("GER", listaURL_01));
		// --
		// -- ETFs USA temática Global y China
		// --
		List<String> listaURL_02 = new ArrayList<String>();
		listaURL_02.add("https://es.investing.com/etfs/aam-sp-em-sector-high-dividend");
		listaURL_02.add("https://es.investing.com/etfs/advisorshares-madrona-intl");
		listaURL_02.add("https://es.investing.com/etfs/advisorshrs-new-tech-media");
		listaURL_02.add("https://es.investing.com/etfs/affinity-world-leaders-equity");
		listaURL_02.add("https://es.investing.com/etfs/alpha-architect-value-mom-trend");
		listaURL_02.add("https://es.investing.com/etfs/alphaclone-international");
		listaURL_02.add("https://es.investing.com/etfs/alps-emerging-sector-dividend-dogs");
		listaURL_02.add("https://es.investing.com/etfs/amplify-online-retail");
		listaURL_02.add("https://es.investing.com/etfs/amplify-transform-data-sharing");
		listaURL_02.add("https://es.investing.com/etfs/anfield-capital-divers-alter");
		listaURL_02.add("https://es.investing.com/etfs/arrow-dogs-of-the-world");
		listaURL_02.add("https://es.investing.com/etfs/barclays-bank-fi-enhanced-global");
		listaURL_02.add("https://es.investing.com/etfs/barclays-bank-ipath-sp-mlp");
		listaURL_02.add("https://es.investing.com/etfs/barclays-etn-plus-select-mlp");
		listaURL_02.add("https://es.investing.com/etfs/barclays-women-in-leadership");
		listaURL_02.add("https://es.investing.com/etfs/bernstein-global-research-fund");
		listaURL_02.add("https://es.investing.com/etfs/beyond-bircs");
		listaURL_02.add("https://es.investing.com/etfs/cambria-emerg-shareholder-yield");
		listaURL_02.add("https://es.investing.com/etfs/claymore-mac-global-solar-energy-in");
		listaURL_02.add("https://es.investing.com/etfs/columbia-sust-glbl-equity-inc");
		listaURL_02.add("https://es.investing.com/etfs/columbia-sust-int-eqty-income");
		listaURL_02.add("https://es.investing.com/etfs/credit-suisse-x-links-multi-asset");
		listaURL_02.add("https://es.investing.com/etfs/csop-china-csi-300-a-h-dynamic");
		listaURL_02.add("https://es.investing.com/etfs/csop-ftse-china-a50-us");
		listaURL_02.add("https://es.investing.com/etfs/csop-msci-china-a-intl-hedged");
		listaURL_02.add("https://es.investing.com/etfs/cwa-income");
		listaURL_02.add("https://es.investing.com/etfs/davis-select-international");
		listaURL_02.add("https://es.investing.com/etfs/davis-select-worldwide");
		listaURL_02.add("https://es.investing.com/etfs/db-x-msci-eafe-currency-hedged-eq");
		listaURL_02.add("https://es.investing.com/etfs/db-x-msci-em-currency-hedged-eq");
		listaURL_02.add("https://es.investing.com/etfs/db-x-trackers-harvest-china");
		listaURL_02.add("https://es.investing.com/etfs/deutsche-xtrackers-csi-300-china-a");
		listaURL_02.add("https://es.investing.com/etfs/deutsche-x-trackers-ftse-developed");
		listaURL_02.add("https://es.investing.com/etfs/deutsche-x-trackers-harvest-csi-500");
		listaURL_02.add("https://es.investing.com/etfs/deutsche-xtrackers-msci-all-china");
		listaURL_02.add("https://es.investing.com/etfs/deutsche-xtrackers-msci-eafe-high");
		listaURL_02.add("https://es.investing.com/etfs/deutsche-x-trackers-msci-eafe-sc");
		listaURL_02.add("https://es.investing.com/etfs/deutsche-x-track-hgh-yld-corp");
		listaURL_02.add("https://es.investing.com/etfs/de-xtrack-ftse-emerging-comp-k");
		listaURL_02.add("https://es.investing.com/etfs/direxion-daily-csi-300-china");
		listaURL_02.add("https://es.investing.com/etfs/direxion-daily-csi-300-china-a-shar");
		listaURL_02.add("https://es.investing.com/etfs/direxiondrobartfclintlautobull3shrs");
		listaURL_02.add("https://es.investing.com/etfs/dow-jones-epac-select-dividend");
		listaURL_02.add("https://es.investing.com/etfs/dtec-alps-disruptive-tech");
		listaURL_02.add("https://es.investing.com/etfs/dwcr-arrow-dwa-country-rotation");
		listaURL_02.add("https://es.investing.com/etfs/eaton-vance-stock-nextshares");
		listaURL_02.add("https://es.investing.com/etfs/egshares-em-core-ex-china");
		listaURL_02.add("https://es.investing.com/etfs/egshares-emerging-markets-consumer");
		listaURL_02.add("https://es.investing.com/etfs/egshares-low-volatility-em-div.");
		listaURL_02.add("https://es.investing.com/etfs/emqq-em-internet-and-ecommerce");
		listaURL_02.add("https://es.investing.com/etfs/etfis-virtus-wmc-global-factor-opp");
		listaURL_02.add("https://es.investing.com/etfs/evolve-marijuana?cid=1075596");
		listaURL_02.add("https://es.investing.com/etfs/fidelity-international-high-div");
		listaURL_02.add("https://es.investing.com/etfs/fidelity-international-value-factor");
		listaURL_02.add("https://es.investing.com/etfs/fieldstone-merlin-dyn-large-cap");
		listaURL_02.add("https://es.investing.com/etfs/fieldstone-uva-unconst-medterm");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-alter-abso-rtrn-strtg");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-bick");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-china-alphadex-fund");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-developed-intl");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-dj-global-select-div.");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-dm-x-us-alphadex-fund");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-dm-x-us-smlcap-alphadex");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-dorsey-wright-internati");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-emerging-markets");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-emerging-mrkt-alphadex");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-em-small-cap-alphadex");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-heitman-glbl-prime-re");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-horizon-mng-vol-dm");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-indx-innvtv-trnsctnprc");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-inst-pref-secs-and-inc");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-international-ipo");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-ise-chindia");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-ise-glb-engnrg---const");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-ise-global-wind-energy");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-ise-water");
		listaURL_02.add("https://es.investing.com/etfs/firsttrustnasdaqartfclintlrobotics");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-nasdaq-global-auto");
		listaURL_02.add("https://es.investing.com/etfs/firsttrust-nq-technology-dividend");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-riverfront-dynamic-dm");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-riverfront-dynamic-em");
		listaURL_02.add("https://es.investing.com/etfs/first-trust-ssi-strtgconvert-sec");
		listaURL_02.add("https://es.investing.com/etfs/flexshares-core-select");
		listaURL_02.add("https://es.investing.com/etfs/flexshares-currhdgd-morng-dm-exus");
		listaURL_02.add("https://es.investing.com/etfs/flexshares-currhdgd-morningstar-em");
		listaURL_02.add("https://es.investing.com/etfs/flexshares-real-assets-allocation");
		listaURL_02.add("https://es.investing.com/etfs/flexshares-stoxx-global-esg-impact");
		listaURL_02.add("https://es.investing.com/etfs/franklin-ftse-china");
		listaURL_02.add("https://es.investing.com/etfs/franklin-liberty-interopp");
		listaURL_02.add("https://es.investing.com/etfs/franklin-liberty-invest-grade-corp");
		listaURL_02.add("https://es.investing.com/etfs/franklin-libertyq-emerging-markets");
		listaURL_02.add("https://es.investing.com/etfs/franklin-libertyq-global-dividend");
		listaURL_02.add("https://es.investing.com/etfs/franklin-libertyq-global-equity");
		listaURL_02.add("https://es.investing.com/etfs/franklin-libertyq-int-equity-hdg");
		listaURL_02.add("https://es.investing.com/etfs/gavekal-knowledge-leaders-developed");
		listaURL_02.add("https://es.investing.com/etfs/global-robotics-and-automation");
		listaURL_02.add("https://es.investing.com/etfs/global-x-auto-electric-vehicles");
		listaURL_02.add("https://es.investing.com/etfs/global-x-china-consumer");
		listaURL_02.add("https://es.investing.com/etfs/global-x-china-energy");
		listaURL_02.add("https://es.investing.com/etfs/global-x-china-financials");
		listaURL_02.add("https://es.investing.com/etfs/global-x-china-industrials");
		listaURL_02.add("https://es.investing.com/etfs/global-x-china-materials");
		listaURL_02.add("https://es.investing.com/etfs/global-x-copper-miners");
		listaURL_02.add("https://es.investing.com/etfs/global-x-fertilizers-potash");
		listaURL_02.add("https://es.investing.com/etfs/global-x-fintech-thematic");
		listaURL_02.add("https://es.investing.com/etfs/global-x-gold-explorers");
		listaURL_02.add("https://es.investing.com/etfs/global-x-internet-things-them-usd");
		listaURL_02.add("https://es.investing.com/etfs/global-x-jpmorgan-efficiente");
		listaURL_02.add("https://es.investing.com/etfs/global-x-lithium");
		listaURL_02.add("https://es.investing.com/etfs/global-x-mlp");
		listaURL_02.add("https://es.investing.com/etfs/global-x-msci-superdividend-eafe");
		listaURL_02.add("https://es.investing.com/etfs/global-x-nasdaq-china-technology");
		listaURL_02.add("https://es.investing.com/etfs/global-x-next-emer---frontier");
		listaURL_02.add("https://es.investing.com/etfs/global-x-robotics---ai-usd");
		listaURL_02.add("https://es.investing.com/etfs/global-x-social-media-index");
		listaURL_02.add("https://es.investing.com/etfs/global-x-superdividend");
		listaURL_02.add("https://es.investing.com/etfs/global-x-superdividend-alternatives");
		listaURL_02.add("https://es.investing.com/etfs/global-x-superdividend-emerging-mar");
		listaURL_02.add("https://es.investing.com/etfs/global-x-top-guru-holdings");
		listaURL_02.add("https://es.investing.com/etfs/global-x-uranium");
		listaURL_02.add("https://es.investing.com/etfs/global-x-yieldco");
		listaURL_02.add("https://es.investing.com/etfs/golden-dragon-halter-usx-china");
		listaURL_02.add("https://es.investing.com/etfs/goldman-sachs-activebeta-em");
		listaURL_02.add("https://es.investing.com/etfs/goldman-sachs-activebeta-inteqt");
		listaURL_02.add("https://es.investing.com/etfs/graniteshares-gold-trust");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-birc");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-china-all-cap");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-china-smlcap");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-china-technology");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-global-shipping");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-global-timber");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-intl-mul.-asset-income");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-s-p-global-div.-opp.");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-s-p-global-water");
		listaURL_02.add("https://es.investing.com/etfs/guggenheim-sp-high-income-infrastr");
		listaURL_02.add("https://es.investing.com/etfs/hartford-multi-lowvol-intern-equity");
		listaURL_02.add("https://es.investing.com/etfs/innovation-shares-nextgen-protocol");
		listaURL_02.add("https://es.investing.com/etfs/innovation-shares-nextgen-vhclstch");
		listaURL_02.add("https://es.investing.com/etfs/innovator-ibd-50");
		listaURL_02.add("https://es.investing.com/etfs/inspire-global-hope");
		listaURL_02.add("https://es.investing.com/etfs/invesco-s-p-intl-dev-low-vol.");
		listaURL_02.add("https://es.investing.com/etfs/iq-50-percent-hedged-ftse-intl");
		listaURL_02.add("https://es.investing.com/etfs/iq-arb-global-resources-etf");
		listaURL_02.add("https://es.investing.com/etfs/iq-arb-merger-arbitrage");
		listaURL_02.add("https://es.investing.com/etfs/iq-global-agribusiness-small-cap");
		listaURL_02.add("https://es.investing.com/etfs/ise-cloud-computing");
		listaURL_02.add("https://es.investing.com/etfs/ise-global-copper");
		listaURL_02.add("https://es.investing.com/etfs/ise-global-platinum");
		listaURL_02.add("https://es.investing.com/etfs/ishares-adapt-cur-hdg-msci-eafe");
		listaURL_02.add("https://es.investing.com/etfs/ishares-core-msci-eafe");
		listaURL_02.add("https://es.investing.com/etfs/ishares-core-msci-emerging-markets");
		listaURL_02.add("https://es.investing.com/etfs/ishares-core-msci-intern-dev-market");
		listaURL_02.add("https://es.investing.com/etfs/ishares-core-msci-total-intl-stock");
		listaURL_02.add("https://es.investing.com/etfs/ishares-currency-hdg-msci-acwi");
		listaURL_02.add("https://es.investing.com/etfs/ishares-currency-hdg-msci-eafe");
		listaURL_02.add("https://es.investing.com/etfs/ishares-currency-hdg-msci-em");
		listaURL_02.add("https://es.investing.com/etfs/ishares-currency-hedged-msci-acwi");
		listaURL_02.add("https://es.investing.com/etfs/ishares-currency-hedged-msci-eafe");
		listaURL_02.add("https://es.investing.com/etfs/ishares-edge-msci-international-siz");
		listaURL_02.add("https://es.investing.com/etfs/ishares-edge-msci-min-vol-eafe-cur");
		listaURL_02.add("https://es.investing.com/etfs/ishares-edge-msci-min-vol-em-cur-hd");
		listaURL_02.add("https://es.investing.com/etfs/ishares-edge-msci-multifactor-emerg");
		listaURL_02.add("https://es.investing.com/etfs/ishares-emerging-markets-dividend");
		listaURL_02.add("https://es.investing.com/etfs/ishares-exponential-tech-mu?cid=1043100");
		listaURL_02.add("https://es.investing.com/etfs/ishares-factorselect-msci-global");
		listaURL_02.add("https://es.investing.com/etfs/ishares-factorselect-msci-int");
		listaURL_02.add("https://es.investing.com/etfs/ishares-factorselect-msci-intl");
		listaURL_02.add("https://es.investing.com/etfs/ishares-ft-dev.-smlcap-x-north-am.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-ftse-xinhua-china-25");
		listaURL_02.add("https://es.investing.com/etfs/ishares-ibonds-dec-2026-term-corp");
		listaURL_02.add("https://es.investing.com/etfs/ishares-int-div-grwth");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-acwi-index-fund");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-acwi-low-carbon-target");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-acwi-x-us");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-all-country-min.-vol.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-argentina-glbl-exp");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-birc");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-china");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-china-a");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-china-small-cap");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-eafe-esg-optimized");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-eafe-index");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-eafe-min-volatility");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-eafe-small-cap");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-em-esg-optimized");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-em-ex-china");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-emg-markets");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-em-min-volatility");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-global-energy-prod.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-global-gold-miners-be?cid=44703");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-global-impact");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-growth");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-international-develope");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-intl-dev-quality");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-intl-dev-value");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci---kokusai");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-value");
		listaURL_02.add("https://es.investing.com/etfs/ishares-msci-world");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-developed-x-us-prop.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p---global-100");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-clean-energy");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-con.-stap.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-cons.discr.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-energy");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-financial");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-healthcare");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-industrials");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-infrastructure");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-materials");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-technology");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-telecom.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-timber---for.");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-global-utilities");
		listaURL_02.add("https://es.investing.com/etfs/ishares-s-p-intl-preferred-stock");
		listaURL_02.add("https://es.investing.com/etfs/janus-obesity");
		listaURL_02.add("https://es.investing.com/etfs/janus-organics");
		listaURL_02.add("https://es.investing.com/etfs/john-hancock-multifactor-dm");
		listaURL_02.add("https://es.investing.com/etfs/jpmorgan-disciplined-high-yield");
		listaURL_02.add("https://es.investing.com/etfs/jpmorgan-diverrtrn-intcurrhdgd");
		listaURL_02.add("https://es.investing.com/etfs/jpmorgan-diversi");
		listaURL_02.add("https://es.investing.com/etfs/jpmorgan-diversified-alternative");
		listaURL_02.add("https://es.investing.com/etfs/jpmorgan-diversified-return-emergin");
		listaURL_02.add("https://es.investing.com/etfs/jpmorgan-diversified-return-global");
		listaURL_02.add("https://es.investing.com/etfs/jpmorgan-managed-futures-strategy");
		listaURL_02.add("https://es.investing.com/etfs/kraneshares-bosera-msci-china-a");
		listaURL_02.add("https://es.investing.com/etfs/kraneshares-elctrcvhcls-fturemblty");
		listaURL_02.add("https://es.investing.com/etfs/kraneshares-em-consumer-tech");
		listaURL_02.add("https://es.investing.com/etfs/kraneshares-ftse-emerging-markets");
		listaURL_02.add("https://es.investing.com/etfs/kraneshares-msci-china-hlth-cr-indx");
		listaURL_02.add("https://es.investing.com/etfs/kraneshares-msci-one-belt-one-road");
		listaURL_02.add("https://es.investing.com/etfs/lattice-developed-markets-ex-us-str");
		listaURL_02.add("https://es.investing.com/etfs/lattice-emerging-markets-strategy");
		listaURL_02.add("https://es.investing.com/etfs/lattice-global-small-cap-strategy");
		listaURL_02.add("https://es.investing.com/etfs/legg-mason-dm-exus-diversifcore");
		listaURL_02.add("https://es.investing.com/etfs/legg-mason-em-diversified-core-etf");
		listaURL_02.add("https://es.investing.com/etfs/legg-mason-em-lowvol-hidiv");
		listaURL_02.add("https://es.investing.com/etfs/legg-mason-intl-lowvol-hidi");
		listaURL_02.add("https://es.investing.com/etfs/marketvectors-agribusiness");
		listaURL_02.add("https://es.investing.com/etfs/marketvectors-china");
		listaURL_02.add("https://es.investing.com/etfs/market-vectors-chinaamc-sme-chinext");
		listaURL_02.add("https://es.investing.com/etfs/market-vectors-coal");
		listaURL_02.add("https://es.investing.com/etfs/marketvectors-gaming");
		listaURL_02.add("https://es.investing.com/etfs/marketvectors-global-alt.-energy");
		listaURL_02.add("https://es.investing.com/etfs/market-vectors-global-spin-off");
		listaURL_02.add("https://es.investing.com/etfs/market-vectors-gold-miners");
		listaURL_02.add("https://es.investing.com/etfs/marketvectors-hard-assets-prod.");
		listaURL_02.add("https://es.investing.com/etfs/market-vectors-junior-gold-miners");
		listaURL_02.add("https://es.investing.com/etfs/market-vectors-morningstar-intl-moa");
		listaURL_02.add("https://es.investing.com/etfs/marketvectors-nuclear-energy");
		listaURL_02.add("https://es.investing.com/etfs/market-vectors-oil-refiners");
		listaURL_02.add("https://es.investing.com/etfs/marketvectors-unconv.-oil---gas");
		listaURL_02.add("https://es.investing.com/etfs/momentumshares-intl-quant-mom");
		listaURL_02.add("https://es.investing.com/etfs/morningstar-dm-x-us-factor-tilt");
		listaURL_02.add("https://es.investing.com/etfs/morningstar-em-factor-tilt");
		listaURL_02.add("https://es.investing.com/etfs/morningstar-g.-upstream-nat.-res.");
		listaURL_02.add("https://es.investing.com/etfs/msci-emerging-markets-smlcp-fund");
		listaURL_02.add("https://es.investing.com/etfs/msci-frontier-100");
		listaURL_02.add("https://es.investing.com/etfs/msci-global-agri.-producers-fund");
		listaURL_02.add("https://es.investing.com/etfs/msci-global-metals-mining-prod.");
		listaURL_02.add("https://es.investing.com/etfs/msci-global-silver-miners-fund");
		listaURL_02.add("https://es.investing.com/etfs/nationwide-risk-based-intl");
		listaURL_02.add("https://es.investing.com/etfs/natixis-seeyond-intl-minvol");
		listaURL_02.add("https://es.investing.com/etfs/oppenheimer-global-esg-revenue");
		listaURL_02.add("https://es.investing.com/etfs/oshares-ftse-rusl-intl-qual-div");
		listaURL_02.add("https://es.investing.com/etfs/pimco-rafi-dyn-multifactor-em");
		listaURL_02.add("https://es.investing.com/etfs/pimco-rafi-dyn-multifactor-intl");
		listaURL_02.add("https://es.investing.com/etfs/portfolioplus-developed-markets");
		listaURL_02.add("https://es.investing.com/etfs/portfolioplus-emerging-markets");
		listaURL_02.add("https://es.investing.com/etfs/powersh.-global-gold---pr.-metals");
		listaURL_02.add("https://es.investing.com/etfs/powersh.-intl-dividend-achievers");
		listaURL_02.add("https://es.investing.com/etfs/powershares-bldrs-em-50-adr");
		listaURL_02.add("https://es.investing.com/etfs/powershares-cleantech-portfolio");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dwa-momlowvol-rotation");
		listaURL_02.add("https://es.investing.com/etfs/powershares-dynamic-dev-int-opp");
		listaURL_02.add("https://es.investing.com/etfs/powershares-ft-rafi-emerging-mrkt");
		listaURL_02.add("https://es.investing.com/etfs/powershares-ftse-int-lowbeta-eqlwgt");
		listaURL_02.add("https://es.investing.com/etfs/powershares-ftse-rafi-dm-x-us");
		listaURL_02.add("https://es.investing.com/etfs/powershares-global-agriculture");
		listaURL_02.add("https://es.investing.com/etfs/powershares---global-clean-energy");
		listaURL_02.add("https://es.investing.com/etfs/powershares-global-em-infr.");
		listaURL_02.add("https://es.investing.com/etfs/powershares-global-water");
		listaURL_02.add("https://es.investing.com/etfs/powershares-international-buyback-a");
		listaURL_02.add("https://es.investing.com/etfs/powershares-purebeta-ftse-dev");
		listaURL_02.add("https://es.investing.com/etfs/powershares-purebeta-ftse-em");
		listaURL_02.add("https://es.investing.com/etfs/powershares-sp500-val-with-mom-port");
		listaURL_02.add("https://es.investing.com/etfs/powershares-s-p-em-low-volatility");
		listaURL_02.add("https://es.investing.com/etfs/powershares-sp-smallcap-qual-port");
		listaURL_02.add("https://es.investing.com/etfs/powershares-varrate-invest-grade");
		listaURL_02.add("https://es.investing.com/etfs/powershr-dwa-em-mom");
		listaURL_02.add("https://es.investing.com/etfs/powershrs-sp-intl-dvl-hidiv-lowvol");
		listaURL_02.add("https://es.investing.com/etfs/powersh-wilderhill-clean-energy");
		listaURL_02.add("https://es.investing.com/etfs/premise-cap-frnt-advant-divrs-tact");
		listaURL_02.add("https://es.investing.com/etfs/principal-active-global-div-inc");
		listaURL_02.add("https://es.investing.com/etfs/principal-millennials");
		listaURL_02.add("https://es.investing.com/etfs/proshares-dj-brookfield-global-infr");
		listaURL_02.add("https://es.investing.com/etfs/proshares-msci-eafe-dividend-grower");
		listaURL_02.add("https://es.investing.com/etfs/proshares-msci-em-div-gr");
		listaURL_02.add("https://es.investing.com/etfs/pshares-bldrs-dm-100-adr");
		listaURL_02.add("https://es.investing.com/etfs/pshares-ftse-rafi-dm-x-us-sml-mid");
		listaURL_02.add("https://es.investing.com/etfs/purefunds-drone-economy-strategy");
		listaURL_02.add("https://es.investing.com/etfs/purefunds-ise-cyber-security");
		listaURL_02.add("https://es.investing.com/etfs/purefunds-video-game-tech");
		listaURL_02.add("https://es.investing.com/etfs/reality-shares-nasdaq-nexgen-econ");
		listaURL_02.add("https://es.investing.com/etfs/rex-bkcm");
		listaURL_02.add("https://es.investing.com/etfs/riverfront-dynamic-core-income");
		listaURL_02.add("https://es.investing.com/etfs/riverfront-dynamic-unconstr-inc");
		listaURL_02.add("https://es.investing.com/etfs/rydex-msci-em-eq-weight");
		listaURL_02.add("https://es.investing.com/etfs/saba-interest-rate-hedged-cef");
		listaURL_02.add("https://es.investing.com/etfs/salix-pharmaceuticals-ltd.");
		listaURL_02.add("https://es.investing.com/etfs/schwab-1000");
		listaURL_02.add("https://es.investing.com/etfs/schwab-emerging-markets-equity");
		listaURL_02.add("https://es.investing.com/etfs/schwab-international-equity");
		listaURL_02.add("https://es.investing.com/etfs/schwab-intl-small-cap-equity");
		listaURL_02.add("https://es.investing.com/etfs/silver-miners");
		listaURL_02.add("https://es.investing.com/etfs/spdr-dj-global-titans");
		listaURL_02.add("https://es.investing.com/etfs/spdr-dj-wilshire-intl-real-estate");
		listaURL_02.add("https://es.investing.com/etfs/spdr-ftse-macquarie-gi-100");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-acwi-ex-us");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-acwi-imi");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-acwi-low-carbon-target");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-china-a-shares-imi");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-eafe-fossil-fuel-rsrvfree");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-eafe-quality-mix");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-emerging-markets-quality");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-em-fossil-fuel-free");
		listaURL_02.add("https://es.investing.com/etfs/spdr-msci-world-quality-mix");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-china");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-emerging-markets");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-emerging-markets-dividend");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-emerging-markets-smallcap");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-global-natural-resources");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-intl-dividend");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-intl-smallcap");
		listaURL_02.add("https://es.investing.com/etfs/spdr-s-p-world-ex-us");
		listaURL_02.add("https://es.investing.com/etfs/spdr-wilshire-global-real-estate");
		listaURL_02.add("https://es.investing.com/etfs/s-p-emerging-markets-high-beta");
		listaURL_02.add("https://es.investing.com/etfs/s-p-emerging-mrkt-infrastructure");
		listaURL_02.add("https://es.investing.com/etfs/spirited-funds-etfmg-whiskey");
		listaURL_02.add("https://es.investing.com/etfs/the-3d-printing");
		listaURL_02.add("https://es.investing.com/etfs/the-wear");
		listaURL_02.add("https://es.investing.com/etfs/ubs-ag-fi-enhanced-glbl-hgh-yld");
		listaURL_02.add("https://es.investing.com/etfs/usaa-msci-em-value-mom-blend");
		listaURL_02.add("https://es.investing.com/etfs/usaa-msci-intl-value-mom-blend");
		listaURL_02.add("https://es.investing.com/etfs/vaneck-vectors-generic-drugs");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-europe-pacific");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-ftse-all-wld-x-us-smcp");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-ftse-all-world?cid=1065342");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-ftse-all-world-x-us");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-ftse-emerging-markets");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-global-ex-su-real-estate");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-int-div-appreciation");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-int-high-div-yld");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-total-intl-stock");
		listaURL_02.add("https://es.investing.com/etfs/vanguard-total-world-stock-index");
		listaURL_02.add("https://es.investing.com/etfs/victory-cemp-international-high-div");
		listaURL_02.add("https://es.investing.com/etfs/victory-cemp-international-vol");
		listaURL_02.add("https://es.investing.com/etfs/victoryshares-em-hidiv-vol-wtd");
		listaURL_02.add("https://es.investing.com/etfs/vident-international-equity");
		listaURL_02.add("https://es.investing.com/etfs/vident-international-equity?cid=1072337");
		listaURL_02.add("https://es.investing.com/etfs/virtus-glovista-em");
		listaURL_02.add("https://es.investing.com/etfs/wbi-tactical-rotation-shares");
		listaURL_02.add("https://es.investing.com/etfs/wcm-bny-mellon-focused-growth-adr");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-defa-fund");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-defa-high-yielding-eq");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-dyn-currhgd-intl");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-dyn-curr-hgd-intl-div-gr");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-em-consumer-growth");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-em-dividend-fund");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-eme-income-fund");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-emerging-mrkt-smallcap");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-em-ex-state-owned-ent");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-glbl-hgd-smallcap-div");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-global-smallcap-div");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-icbccs-sp-china-500");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-intl-dividend-top-100");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-intl-hdg-quality-div");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-intl-hedged-equity-fund");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-intl-largecap-dividend");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-intl-midcap-dividend");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-intl-quality-div-gr");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-intl-smallcap-fund");
		listaURL_02.add("https://es.investing.com/etfs/wisdomtree-world-ex-su-growth-fund");
		listaURL_02.add("https://es.investing.com/etfs/wsdmtree-dyncurr-hgd-intl-smllcap");
		listaURL_02.add("https://es.investing.com/etfs/xtrackers-msci-all-world");
		listaURL_02.add("https://es.investing.com/etfs/xtrackers-msci-all-world-high-div");
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
