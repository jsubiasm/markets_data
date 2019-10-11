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
public class DCChartFromWebUrl extends DriverControllerBase
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DCChartFromWebUrl.class);

	/**
	 * URL Groups
	 */
	private final static List<URLGroup> LISTA_URL_GROUP = new ArrayList<URLGroup>();
	static
	{
		// --
		// -- ETFs HEALTHCARE
		// --
		// List<String> listaURL_03 = new ArrayList<String>();
		// listaURL_03.add("https://es.investing.com/etfs/amundi-etf-msci-europe-healthcare?cid=45977");
		// listaURL_03.add("https://es.investing.com/etfs/comstage-stoxx600-health-care-nr");
		// listaURL_03.add("https://es.investing.com/etfs/db-x-trackers-msci-wrd-hlth-care-de");
		// listaURL_03.add("https://es.investing.com/etfs/dj-stoxx600-health-care");
		// listaURL_03.add("https://es.investing.com/etfs/fidelity-msci-healthcare-index");
		// listaURL_03.add("https://es.investing.com/etfs/firsttrust-health-care-alphadex");
		// listaURL_03.add("https://es.investing.com/etfs/rydex-s-p-equal-weight-health-care");
		// listaURL_03.add("https://es.investing.com/etfs/spdr---health-care");
		// listaURL_03.add("https://es.investing.com/etfs/ishares-edge-msci-mf-healthcare");
		// listaURL_03.add("https://es.investing.com/etfs/ishares-s-p-global-healthcare");
		// listaURL_03.add("https://es.investing.com/etfs/ishares-hc-innov-acc-mi?cid=1009026");
		// listaURL_03.add("https://es.investing.com/etfs/ishares-s-p-500-usd-health-care");
		// listaURL_03.add("https://es.investing.com/etfs/ishares-dj-stoxx600-health-care");
		// listaURL_03.add("https://es.investing.com/etfs/ishares-djsu-health-care-index");
		// listaURL_03.add("https://es.investing.com/etfs/ishares-djsu-health-care-providers");
		// listaURL_03.add("https://es.investing.com/etfs/john-hancock-multifactor-health");
		// listaURL_03.add("https://es.investing.com/etfs/lyxor-msci-world-health-care-tr?cid=46008");
		// listaURL_03.add("https://es.investing.com/etfs/lyxor-stoxx-europe-600-health-care?cid=46172");
		// listaURL_03.add("https://es.investing.com/etfs/powershares-dwa-healthcare-o");
		// listaURL_03.add("https://es.investing.com/etfs/powersh-s-p-smallcap-healthcare");
		// listaURL_03.add("https://es.investing.com/etfs/principal-healthcare-innovators");
		// listaURL_03.add("https://es.investing.com/etfs/stoxx-600-optimised-health-care");
		// listaURL_03.add("https://es.investing.com/etfs/spdr-msci-europe-health-care?cid=46222");
		// listaURL_03.add("https://es.investing.com/etfs/spdr-s-p-health-care-equipment");
		// listaURL_03.add("https://es.investing.com/etfs/spdr-s-p-health-care-services");
		// listaURL_03.add("https://es.investing.com/etfs/vanguard-health-care");
		// LISTA_URL_GROUP.add(new URLGroup("HEALTHCARE", listaURL_03));
		// --
		// -- ETFs BIOTECH
		// --
		// List<String> listaURL_04 = new ArrayList<String>();
		// listaURL_04.add("https://es.investing.com/etfs/bioshares-biotechnology-clinical");
		// listaURL_04.add("https://es.investing.com/etfs/bioshares-biotechnology-products");
		// listaURL_04.add("https://es.investing.com/etfs/firsttrust-amex-biotech");
		// listaURL_04.add("https://es.investing.com/etfs/powersh.-dynamic-biotech---genome");
		// listaURL_04.add("https://es.investing.com/etfs/spdr-s-p-biotech");
		// listaURL_04.add("https://es.investing.com/etfs/market-vectors-biotech");
		// LISTA_URL_GROUP.add(new URLGroup("BIOTECH", listaURL_04));
		// --
		// -- ETFs PHARMA
		// --
		// List<String> listaURL_05 = new ArrayList<String>();
		// listaURL_05.add("https://es.investing.com/etfs/ishares-djsu-pharmaceutical-index");
		// listaURL_05.add("https://es.investing.com/etfs/powersh.-dynamic-pharmaceuticals");
		// listaURL_05.add("https://es.investing.com/etfs/spdr-s-p-pharmaceuticals");
		// listaURL_05.add("https://es.investing.com/etfs/holdrs-merrill-lynch-pharma.");
		// LISTA_URL_GROUP.add(new URLGroup("PHARMA", listaURL_05));
		// --
		// -- ETFs MEDICAL
		// --
		// List<String> listaURL_06 = new ArrayList<String>();
		// listaURL_06.add("https://es.investing.com/etfs/alps-medical-breakthroughs");
		// listaURL_06.add("https://es.investing.com/etfs/ishares-djsu-medical-devices-index");
		// LISTA_URL_GROUP.add(new URLGroup("MEDICAL", listaURL_06));
		// --
		// -- ETFs ROBOTICS
		// --
		// List<String> listaURL_07 = new ArrayList<String>();
		// listaURL_07.add("https://es.investing.com/etfs/global-x-robotics---ai-usd");
		// listaURL_07.add("https://es.investing.com/etfs/ishares-automation-robotics-acc?cid=1009408");
		// listaURL_07.add("https://es.investing.com/etfs/robo-stox-global-robotics");
		// LISTA_URL_GROUP.add(new URLGroup("ROBOTICS", listaURL_07));
		// --
		// -- ETFs CHINA
		// --
		// List<String> listaURL_08 = new ArrayList<String>();
		// listaURL_08.add("https://es.investing.com/etfs/db-xtrackers-msci-china-trn-uk?cid=46543");
		// listaURL_08.add("https://es.investing.com/etfs/etflab-msci-china");
		// listaURL_08.add("https://es.investing.com/etfs/ishares-dj-china-offshore-50---de");
		// LISTA_URL_GROUP.add(new URLGroup("CHINA", listaURL_08));
		// --
		// -- ACCIONES CON DIVIDENDO
		// --
		List<String> listaURL_09 = new ArrayList<String>();
		listaURL_09.add("https://es.investing.com/equities/1st-source-corp");
		listaURL_09.add("https://es.investing.com/equities/3i");
		listaURL_09.add("https://es.investing.com/equities/3m-co");
		// listaURL_09.add("https://es.investing.com/equities/a.g-barr-plc");
		listaURL_09.add("https://es.investing.com/equities/a.o-smith-corp");
		listaURL_09.add("https://es.investing.com/equities/aalberts-industr");
		listaURL_09.add("https://es.investing.com/equities/abbott-laboratories");
		listaURL_09.add("https://es.investing.com/equities/abbvie-inc");
		listaURL_09.add("https://es.investing.com/equities/abcam-plc");
		listaURL_09.add("https://es.investing.com/equities/abm-industries-inc");
		listaURL_09.add("https://es.investing.com/equities/accenture-ltd");
		listaURL_09.add("https://es.investing.com/equities/acciona-sa");
		listaURL_09.add("https://es.investing.com/equities/acerinox");
		listaURL_09.add("https://es.investing.com/equities/acs-cons-y-serv");
		listaURL_09.add("https://es.investing.com/equities/adidas-salomon");
		// listaURL_09.add("https://es.investing.com/equities/adolfo-dominguez-sa");
		listaURL_09.add("https://es.investing.com/equities/aegon");
		listaURL_09.add("https://es.investing.com/equities/aena-aeropuertos-sa");
		listaURL_09.add("https://es.investing.com/equities/aflac-inc");
		listaURL_09.add("https://es.investing.com/equities/ahold-kon");
		listaURL_09.add("https://es.investing.com/equities/air-liquide");
		listaURL_09.add("https://es.investing.com/equities/air-prods---chem");
		// listaURL_09.add("https://es.investing.com/equities/alba-mineral-resources-plc");
		listaURL_09.add("https://es.investing.com/equities/alfa-laval");
		listaURL_09.add("https://es.investing.com/equities/allianz-ag");
		// listaURL_09.add("https://es.investing.com/equities/allianzgi-diversifiedome-convertibl");
		listaURL_09.add("https://es.investing.com/equities/almirall-sa");
		listaURL_09.add("https://es.investing.com/equities/altria-group");
		listaURL_09.add("https://es.investing.com/equities/amadeus");
		// listaURL_09.add("https://es.investing.com/equities/amcor-plc");
		listaURL_09.add("https://es.investing.com/equities/amer-tower-corp");
		listaURL_09.add("https://es.investing.com/equities/american-express");
		listaURL_09.add("https://es.investing.com/equities/american-realty-c");
		listaURL_09.add("https://es.investing.com/equities/american-states-water-comp");
		listaURL_09.add("https://es.investing.com/equities/american-water-works-inc");
		listaURL_09.add("https://es.investing.com/equities/ameriprise-fincl");
		listaURL_09.add("https://es.investing.com/equities/amgen-inc");
		// listaURL_09.add("https://es.investing.com/equities/amper-sa");
		listaURL_09.add("https://es.investing.com/equities/anheuser-busch-inbev");
		listaURL_09.add("https://es.investing.com/equities/apple-computer-inc");
		listaURL_09.add("https://es.investing.com/equities/applied-matls-inc");
		listaURL_09.add("https://es.investing.com/equities/aptargroup-inc");
		listaURL_09.add("https://es.investing.com/equities/aqua-america-inc.");
		listaURL_09.add("https://es.investing.com/equities/arcelormittal-reg");
		listaURL_09.add("https://es.investing.com/equities/archer-daniels-mid");
		// listaURL_09.add("https://es.investing.com/equities/armanino-foods-new");
		// listaURL_09.add("https://es.investing.com/equities/arrow-financial-corp");
		// listaURL_09.add("https://es.investing.com/equities/artesian-resource");
		listaURL_09.add("https://es.investing.com/equities/ashmore");
		listaURL_09.add("https://es.investing.com/equities/ashtead-group");
		listaURL_09.add("https://es.investing.com/equities/assoc.br.foods");
		listaURL_09.add("https://es.investing.com/equities/astrazeneca");
		listaURL_09.add("https://es.investing.com/equities/at-t");
		listaURL_09.add("https://es.investing.com/equities/atlantia");
		listaURL_09.add("https://es.investing.com/equities/atlas-copco-a");
		listaURL_09.add("https://es.investing.com/equities/atmos-energy-corp");
		// listaURL_09.add("https://es.investing.com/equities/atresmedia");
		listaURL_09.add("https://es.investing.com/equities/auto-data-process");
		listaURL_09.add("https://es.investing.com/equities/auxiliar-de-ferrocarriles");
		listaURL_09.add("https://es.investing.com/equities/avago-technologies");
		listaURL_09.add("https://es.investing.com/equities/aviva");
		listaURL_09.add("https://es.investing.com/equities/axa");
		listaURL_09.add("https://es.investing.com/equities/axel-springer");
		listaURL_09.add("https://es.investing.com/equities/b-g-foods-holdings-corp");
		listaURL_09.add("https://es.investing.com/equities/badger-meter-inc");
		listaURL_09.add("https://es.investing.com/equities/bae-systems");
		listaURL_09.add("https://es.investing.com/equities/bakkafrost-p-f");
		listaURL_09.add("https://es.investing.com/equities/ball-corp");
		listaURL_09.add("https://es.investing.com/equities/bancfirst-corp");
		listaURL_09.add("https://es.investing.com/equities/banco-santander");
		listaURL_09.add("https://es.investing.com/equities/bank-of-america");
		listaURL_09.add("https://es.investing.com/equities/bankers-investment-trust");
		listaURL_09.add("https://es.investing.com/equities/bankia");
		listaURL_09.add("https://es.investing.com/equities/bankinter");
		// listaURL_09.add("https://es.investing.com/equities/baron-de-ley-sa");
		listaURL_09.add("https://es.investing.com/equities/barrick-gold-corp.");
		listaURL_09.add("https://es.investing.com/equities/basf-ag");
		listaURL_09.add("https://es.investing.com/equities/bay-mot-werke");
		listaURL_09.add("https://es.investing.com/equities/bayer-ag");
		listaURL_09.add("https://es.investing.com/equities/bbva");
		listaURL_09.add("https://es.investing.com/equities/bce");
		listaURL_09.add("https://es.investing.com/equities/bco-de-sabadell");
		listaURL_09.add("https://es.investing.com/equities/bcolombia");
		listaURL_09.add("https://es.investing.com/equities/beazley-group");
		listaURL_09.add("https://es.investing.com/equities/becton-dickinsn");
		listaURL_09.add("https://es.investing.com/equities/bellway");
		listaURL_09.add("https://es.investing.com/equities/bemis-company");
		listaURL_09.add("https://es.investing.com/equities/bhp-billiton");
		// listaURL_09.add("https://es.investing.com/equities/biosearch-sa");
		listaURL_09.add("https://es.investing.com/equities/black-hills-corp");
		// listaURL_09.add("https://es.investing.com/equities/black-rock-mining-ltd");
		listaURL_09.add("https://es.investing.com/equities/blackrock,-inc.-c");
		// listaURL_09.add("https://es.investing.com/equities/bloomsbury-publishing");
		listaURL_09.add("https://es.investing.com/equities/bnp-paribas-fortis-sa");
		// listaURL_09.add("https://es.investing.com/equities/bodegas-riojanas-sa");
		listaURL_09.add("https://es.investing.com/equities/boeing-co");
		listaURL_09.add("https://es.investing.com/equities/bolsas-y-mer-esp");
		listaURL_09.add("https://es.investing.com/equities/borgwarner");
		listaURL_09.add("https://es.investing.com/equities/bouygues");
		listaURL_09.add("https://es.investing.com/equities/bovis-homes");
		listaURL_09.add("https://es.investing.com/equities/bp");
		listaURL_09.add("https://es.investing.com/equities/brady-corp");
		listaURL_09.add("https://es.investing.com/equities/brembo");
		listaURL_09.add("https://es.investing.com/equities/bristol-myer-squiib");
		listaURL_09.add("https://es.investing.com/equities/british-american-tobacco");
		listaURL_09.add("https://es.investing.com/equities/british-land");
		listaURL_09.add("https://es.investing.com/equities/britvic");
		listaURL_09.add("https://es.investing.com/equities/brookfield-asset-management");
		listaURL_09.add("https://es.investing.com/equities/brookfield-infrastructure-partners");
		listaURL_09.add("https://es.investing.com/equities/brookfield-renewable-energy-partner");
		// listaURL_09.add("https://es.investing.com/equities/brooks-macdonald-group-plc");
		listaURL_09.add("https://es.investing.com/equities/brown---brown-inc");
		listaURL_09.add("https://es.investing.com/equities/brown-forman-b");
		listaURL_09.add("https://es.investing.com/equities/brunswick-corp");
		listaURL_09.add("https://es.investing.com/equities/gbl");
		listaURL_09.add("https://es.investing.com/equities/bt-group");
		listaURL_09.add("https://es.investing.com/equities/buckeye-partners-lp");
		listaURL_09.add("https://es.investing.com/equities/bunzl");
		listaURL_09.add("https://es.investing.com/equities/burberry");
		listaURL_09.add("https://es.investing.com/equities/bureau-verita");
		// listaURL_09.add("https://es.investing.com/equities/caesarstone-sdot-yam-ltd");
		listaURL_09.add("https://es.investing.com/equities/caixabank-sa");
		listaURL_09.add("https://es.investing.com/equities/cal-maine-foods");
		listaURL_09.add("https://es.investing.com/equities/caledonia-investment");
		listaURL_09.add("https://es.investing.com/equities/california-water-service-group");
		listaURL_09.add("https://es.investing.com/equities/campari");
		listaURL_09.add("https://es.investing.com/equities/canadian-national-railway");
		listaURL_09.add("https://es.investing.com/equities/cardinal-health");
		// listaURL_09.add("https://es.investing.com/equities/caretech-hldg");
		listaURL_09.add("https://es.investing.com/equities/carlisle-companies-inc");
		listaURL_09.add("https://es.investing.com/equities/carnival-corp-exch");
		listaURL_09.add("https://es.investing.com/equities/caterpillar");
		listaURL_09.add("https://es.investing.com/equities/cellnex-telecom");
		listaURL_09.add("https://es.investing.com/equities/centrica");
		listaURL_09.add("https://es.investing.com/equities/cf-industries");
		listaURL_09.add("https://es.investing.com/equities/chesapeake-ener");
		// listaURL_09.add("https://es.investing.com/equities/chesnara-plc");
		listaURL_09.add("https://es.investing.com/equities/chevron");
		listaURL_09.add("https://es.investing.com/equities/chipotle-mexican-grill-inc");
		listaURL_09.add("https://es.investing.com/equities/chubb-corp");
		listaURL_09.add("https://es.investing.com/equities/church---dwight");
		listaURL_09.add("https://es.investing.com/equities/cibc");
		listaURL_09.add("https://es.investing.com/equities/cie-automotive-sa");
		listaURL_09.add("https://es.investing.com/equities/cincinnati-fin");
		listaURL_09.add("https://es.investing.com/equities/cintas-corp");
		listaURL_09.add("https://es.investing.com/equities/cisco-sys-inc");
		listaURL_09.add("https://es.investing.com/equities/city-of-london-investment-trust");
		// listaURL_09.add("https://es.investing.com/equities/clinica-baviera-sa");
		listaURL_09.add("https://es.investing.com/equities/clorox-co");
		listaURL_09.add("https://es.investing.com/equities/close-brothers");
		listaURL_09.add("https://es.investing.com/equities/cnp-assurances");
		listaURL_09.add("https://es.investing.com/equities/coca-cola-co");
		listaURL_09.add("https://es.investing.com/equities/cocnsol-edison");
		listaURL_09.add("https://es.investing.com/equities/colgate-palmo");
		listaURL_09.add("https://es.investing.com/equities/coloplast--b-");
		listaURL_09.add("https://es.investing.com/equities/comcast-corp-new");
		listaURL_09.add("https://es.investing.com/equities/commerce-bancshar");
		listaURL_09.add("https://es.investing.com/equities/community-bank-system-inc");
		// listaURL_09.add("https://es.investing.com/equities/community-trust-bancorp");
		listaURL_09.add("https://es.investing.com/equities/compass-group");
		listaURL_09.add("https://es.investing.com/equities/compass-minerals-intl-inc");
		// listaURL_09.add("https://es.investing.com/equities/concurrent-technologies");
		// listaURL_09.add("https://es.investing.com/equities/connecticut-water");
		listaURL_09.add("https://es.investing.com/equities/constellation-a");
		listaURL_09.add("https://es.investing.com/equities/corning-inc");
		listaURL_09.add("https://es.investing.com/equities/corporacion-financiera-alba-sa");
		listaURL_09.add("https://es.investing.com/equities/corticeira-amorim");
		listaURL_09.add("https://es.investing.com/equities/costco-whsl-corp-new");
		listaURL_09.add("https://es.investing.com/equities/cracker-barrelold-country");
		// listaURL_09.add("https://es.investing.com/equities/craneware-plc");
		listaURL_09.add("https://es.investing.com/equities/cranswick-plc");
		// listaURL_09.add("https://es.investing.com/equities/crest-nicholson");
		listaURL_09.add("https://es.investing.com/equities/croda");
		listaURL_09.add("https://es.investing.com/equities/csx-corp");
		// listaURL_09.add("https://es.investing.com/equities/ctt-correios-de-portugal-sa");
		listaURL_09.add("https://es.investing.com/equities/cubesmart");
		listaURL_09.add("https://es.investing.com/equities/cullen-frost-bankers-inc");
		listaURL_09.add("https://es.investing.com/equities/cummins-inc");
		listaURL_09.add("https://es.investing.com/equities/cvs-corp");
		listaURL_09.add("https://es.investing.com/equities/daimler");
		listaURL_09.add("https://es.investing.com/equities/danone");
		listaURL_09.add("https://es.investing.com/equities/davita-inc");
		listaURL_09.add("https://es.investing.com/equities/dechra-pharma");
		listaURL_09.add("https://es.investing.com/equities/deere---co");
		// listaURL_09.add("https://es.investing.com/equities/deoleo");
		listaURL_09.add("https://es.investing.com/equities/derwent-london");
		listaURL_09.add("https://es.investing.com/equities/deutsche-post");
		// listaURL_09.add("https://es.investing.com/equities/dewhurst-plc");
		// listaURL_09.add("https://es.investing.com/equities/dia");
		listaURL_09.add("https://es.investing.com/equities/diageo");
		listaURL_09.add("https://es.investing.com/equities/digital-realty-trust-inc");
		// listaURL_09.add("https://es.investing.com/equities/diplomat-pharmacy");
		listaURL_09.add("https://es.investing.com/equities/discover-financl");
		listaURL_09.add("https://es.investing.com/equities/disney");
		listaURL_09.add("https://es.investing.com/equities/dollar-general-corp");
		listaURL_09.add("https://es.investing.com/equities/dominion-res");
		listaURL_09.add("https://es.investing.com/equities/dominos-pizza");
		listaURL_09.add("https://es.investing.com/equities/dominos-pizza-inc");
		listaURL_09.add("https://es.investing.com/equities/donaldson-comp-inc");
		listaURL_09.add("https://es.investing.com/equities/dover-corp");
		listaURL_09.add("https://es.investing.com/equities/ds-smith");
		listaURL_09.add("https://es.investing.com/equities/dt-euroshop-exch");
		listaURL_09.add("https://es.investing.com/equities/dt-telekom");
		listaURL_09.add("https://es.investing.com/equities/duke-energy");
		listaURL_09.add("https://es.investing.com/equities/dunelm-group");
		// listaURL_09.add("https://es.investing.com/equities/duro-felguera-sa");
		listaURL_09.add("https://es.investing.com/equities/e.on");
		listaURL_09.add("https://es.investing.com/equities/eads");
		// listaURL_09.add("https://es.investing.com/equities/eagle-financial-services");
		listaURL_09.add("https://es.investing.com/equities/eaton");
		listaURL_09.add("https://es.investing.com/equities/eaton-vance");
		listaURL_09.add("https://es.investing.com/equities/ebro-foods");
		listaURL_09.add("https://es.investing.com/equities/ecolab-inc");
		listaURL_09.add("https://es.investing.com/equities/edinburgh-investment-trust");
		listaURL_09.add("https://es.investing.com/equities/edison-intl");
		listaURL_09.add("https://es.investing.com/equities/edp-energias-de-portugal-sa");
		listaURL_09.add("https://es.investing.com/equities/eiffage");
		// listaURL_09.add("https://es.investing.com/equities/elecnor-sa");
		listaURL_09.add("https://es.investing.com/equities/eli-lilly-and-co");
		listaURL_09.add("https://es.investing.com/equities/emerson-elec");
		listaURL_09.add("https://es.investing.com/equities/enagas");
		listaURL_09.add("https://es.investing.com/equities/enbridge-inc.");
		// listaURL_09.add("https://es.investing.com/equities/ence-energia-y-celulosa-sa");
		listaURL_09.add("https://es.investing.com/equities/endesa");
		// listaURL_09.add("https://es.investing.com/equities/ercros-sa");
		listaURL_09.add("https://es.investing.com/equities/erg-spa");
		listaURL_09.add("https://es.investing.com/equities/erie-indemnity-co");
		listaURL_09.add("https://es.investing.com/equities/essentra-plc");
		listaURL_09.add("https://es.investing.com/equities/essilor-internat");
		listaURL_09.add("https://es.investing.com/equities/euronext");
		listaURL_09.add("https://es.investing.com/equities/exxon-mobil");
		listaURL_09.add("https://es.investing.com/equities/faes-farma-sa");
		// listaURL_09.add("https://es.investing.com/equities/farmers---merchants-bancorp-inc");
		listaURL_09.add("https://es.investing.com/equities/fastenal-co");
		listaURL_09.add("https://es.investing.com/equities/fcc");
		listaURL_09.add("https://es.investing.com/equities/federal-realty-investment-trust");
		listaURL_09.add("https://es.investing.com/equities/fielmann-ag-exch");
		// listaURL_09.add("https://es.investing.com/equities/first-derivatives-plc");
		listaURL_09.add("https://es.investing.com/equities/flowers-foods");
		listaURL_09.add("https://es.investing.com/equities/fluidra-sa");
		listaURL_09.add("https://es.investing.com/equities/fortis-inc");
		listaURL_09.add("https://es.investing.com/equities/franklin-electric");
		listaURL_09.add("https://es.investing.com/equities/franklin-res");
		listaURL_09.add("https://es.investing.com/equities/fraport");
		listaURL_09.add("https://es.investing.com/equities/fresenius-medi");
		listaURL_09.add("https://es.investing.com/equities/fuchs-petrolub-l");
		listaURL_09.add("https://es.investing.com/equities/gamesa");
		listaURL_09.add("https://es.investing.com/equities/gas-natural-sdg");
		listaURL_09.add("https://es.investing.com/equities/gbl");
		listaURL_09.add("https://es.investing.com/equities/gdf-suez");
		listaURL_09.add("https://es.investing.com/equities/general-dynam");
		listaURL_09.add("https://es.investing.com/equities/general-electric");
		listaURL_09.add("https://es.investing.com/equities/general-mills");
		listaURL_09.add("https://es.investing.com/equities/gentex-corp");
		listaURL_09.add("https://es.investing.com/equities/genuine-parts-co");
		listaURL_09.add("https://es.investing.com/equities/genus");
		listaURL_09.add("https://es.investing.com/equities/gestamp-automocion-sa");
		listaURL_09.add("https://es.investing.com/equities/gilead-sciences-inc");
		listaURL_09.add("https://es.investing.com/equities/givaudan-sa-cfd");
		listaURL_09.add("https://es.investing.com/equities/glaxosmithkline");
		listaURL_09.add("https://es.investing.com/equities/glencore");
		listaURL_09.add("https://es.investing.com/equities/google-inc-c");
		// listaURL_09.add("https://es.investing.com/equities/gorman-rupp-co.");
		listaURL_09.add("https://es.investing.com/equities/greene-king");
		listaURL_09.add("https://es.investing.com/equities/grifols");
		listaURL_09.add("https://es.investing.com/equities/group-4-securicor");
		listaURL_09.add("https://es.investing.com/equities/grupo-catalana-occidente-sa");
		listaURL_09.add("https://es.investing.com/equities/grupo-ferrovial");
		// listaURL_09.add("https://es.investing.com/equities/gulf-marine");
		listaURL_09.add("https://es.investing.com/equities/h---r-block-inc");
		listaURL_09.add("https://es.investing.com/equities/halliburton-co");
		listaURL_09.add("https://es.investing.com/equities/halma");
		listaURL_09.add("https://es.investing.com/equities/hammerson");
		listaURL_09.add("https://es.investing.com/equities/hanesbrands");
		listaURL_09.add("https://es.investing.com/equities/harley-davidson");
		// listaURL_09.add("https://es.investing.com/equities/harmony-merger-corp");
		listaURL_09.add("https://es.investing.com/equities/hasbro-inc");
		listaURL_09.add("https://es.investing.com/equities/hb-fuller-comp");
		listaURL_09.add("https://es.investing.com/equities/hcp-inc");
		listaURL_09.add("https://es.investing.com/equities/health-care-reit");
		listaURL_09.add("https://es.investing.com/equities/heineken");
		listaURL_09.add("https://es.investing.com/equities/helmerich---payne");
		// listaURL_09.add("https://es.investing.com/equities/henderson-eurotrust-plc");
		// listaURL_09.add("https://es.investing.com/equities/henderson-sml-co-common-stock");
		listaURL_09.add("https://es.investing.com/equities/henkel-ag---co.-st");
		listaURL_09.add("https://es.investing.com/equities/hennes---mauritz");
		listaURL_09.add("https://es.investing.com/equities/hera-spa");
		listaURL_09.add("https://es.investing.com/equities/hermes-international");
		listaURL_09.add("https://es.investing.com/equities/hershey-co");
		// listaURL_09.add("https://es.investing.com/equities/highcroft-investments-plc-reit");
		// listaURL_09.add("https://es.investing.com/equities/hill---smith-holdings-plc");
		// listaURL_09.add("https://es.investing.com/equities/hilton-food-group-plc");
		listaURL_09.add("https://es.investing.com/equities/home-depot");
		listaURL_09.add("https://es.investing.com/equities/hormel-foods-corp");
		listaURL_09.add("https://es.investing.com/equities/howden-join");
		listaURL_09.add("https://es.investing.com/equities/hugo-boss");
		listaURL_09.add("https://es.investing.com/equities/iberdrola");
		// listaURL_09.add("https://es.investing.com/equities/iberpapel-gestion-sa");
		listaURL_09.add("https://es.investing.com/equities/ibm");
		listaURL_09.add("https://es.investing.com/equities/illinois-tool-wk-r");
		listaURL_09.add("https://es.investing.com/equities/imperial-tobacco");
		listaURL_09.add("https://es.investing.com/equities/inchcape");
		listaURL_09.add("https://es.investing.com/equities/inditex");
		listaURL_09.add("https://es.investing.com/equities/indra-sistemas");
		listaURL_09.add("https://es.investing.com/equities/industria-macchine-automatiche");
		listaURL_09.add("https://es.investing.com/equities/ingenico");
		listaURL_09.add("https://es.investing.com/equities/ingersoll-rand");
		listaURL_09.add("https://es.investing.com/equities/ingredion-inc");
		listaURL_09.add("https://es.investing.com/equities/inmob-colonial");
		listaURL_09.add("https://es.investing.com/equities/intel-corp");
		// listaURL_09.add("https://es.investing.com/equities/intermolecular");
		listaURL_09.add("https://es.investing.com/equities/intertek-testing-services");
		// listaURL_09.add("https://es.investing.com/equities/invesco-income-growth-trust-plc");
		listaURL_09.add("https://es.investing.com/equities/investec");
		// listaURL_09.add("https://es.investing.com/equities/inypsa-informes-y-proyectos-sa");
		// listaURL_09.add("https://es.investing.com/equities/iquel-y-costas---miquel-sa");
		// listaURL_09.add("https://es.investing.com/equities/j-smart-co-contractors-plc");
		listaURL_09.add("https://es.investing.com/equities/jack-henry---asso");
		// listaURL_09.add("https://es.investing.com/equities/james-fisher-and-sons");
		listaURL_09.add("https://es.investing.com/equities/jd-sports-fashion");
		listaURL_09.add("https://es.investing.com/equities/jiangsu-expres");
		listaURL_09.add("https://es.investing.com/equities/jm-smucker-co");
		listaURL_09.add("https://es.investing.com/equities/john-wiley---sons-a");
		listaURL_09.add("https://es.investing.com/equities/johnson-controls");
		listaURL_09.add("https://es.investing.com/equities/johnson-johnson");
		listaURL_09.add("https://es.investing.com/equities/johnson-matthey");
		listaURL_09.add("https://es.investing.com/equities/jp-morgan-chase");
		// listaURL_09.add("https://es.investing.com/equities/judges-scientific-plc");
		listaURL_09.add("https://es.investing.com/equities/jupiter-fund-management");
		listaURL_09.add("https://es.investing.com/equities/kellogg-co.");
		listaURL_09.add("https://es.investing.com/equities/kerry-group");
		listaURL_09.add("https://es.investing.com/equities/kimberly-clark");
		listaURL_09.add("https://es.investing.com/equities/kimco-realty");
		listaURL_09.add("https://es.investing.com/equities/kinder-morgan");
		listaURL_09.add("https://es.investing.com/equities/kingfisher");
		listaURL_09.add("https://es.investing.com/equities/klepierre");
		listaURL_09.add("https://es.investing.com/equities/konecranes");
		listaURL_09.add("https://es.investing.com/equities/korian");
		listaURL_09.add("https://es.investing.com/equities/kraft-foods-inc");
		listaURL_09.add("https://es.investing.com/equities/kroger-co");
		listaURL_09.add("https://es.investing.com/equities/l-oreal");
		listaURL_09.add("https://es.investing.com/equities/l.v.m.h.");
		listaURL_09.add("https://es.investing.com/equities/laboratorios-farmaceuticos-rovi-sa");
		listaURL_09.add("https://es.investing.com/equities/lagardere-s.c.a.");
		listaURL_09.add("https://es.investing.com/equities/lancaster-colony");
		listaURL_09.add("https://es.investing.com/equities/lear");
		listaURL_09.add("https://es.investing.com/equities/legal---general");
		listaURL_09.add("https://es.investing.com/equities/leggett---platt");
		listaURL_09.add("https://es.investing.com/equities/limited-brands");
		listaURL_09.add("https://es.investing.com/equities/linde");
		listaURL_09.add("https://es.investing.com/equities/lindt---sp-n");
		// listaURL_09.add("https://es.investing.com/equities/lingotes-especiales-sa");
		listaURL_09.add("https://es.investing.com/equities/lockheed-martin");
		listaURL_09.add("https://es.investing.com/equities/logista");
		listaURL_09.add("https://es.investing.com/equities/london-stock-exchange");
		listaURL_09.add("https://es.investing.com/equities/lowes-companies");
		// listaURL_09.add("https://es.investing.com/equities/lsl-property-services-plc");
		listaURL_09.add("https://es.investing.com/equities/ltc-properties-inc");
		listaURL_09.add("https://es.investing.com/equities/main-street-capital-corp");
		// listaURL_09.add("https://es.investing.com/equities/maintel-holdings");
		listaURL_09.add("https://es.investing.com/equities/manpower-inc");
		listaURL_09.add("https://es.investing.com/equities/mapfre");
		listaURL_09.add("https://es.investing.com/equities/marks---spencer-group");
		listaURL_09.add("https://es.investing.com/equities/mastercard-cl-a");
		// listaURL_09.add("https://es.investing.com/equities/mattioli-woods");
		listaURL_09.add("https://es.investing.com/equities/mccormick---co");
		listaURL_09.add("https://es.investing.com/equities/mcdonalds");
		listaURL_09.add("https://es.investing.com/equities/mcgrath-rentcorp");
		listaURL_09.add("https://es.investing.com/equities/mcgraw-hill");
		listaURL_09.add("https://es.investing.com/equities/mckesson-corp");
		listaURL_09.add("https://es.investing.com/equities/mdu-res-group-inc");
		// listaURL_09.add("https://es.investing.com/equities/mears-group");
		listaURL_09.add("https://es.investing.com/equities/mediaset-esp");
		listaURL_09.add("https://es.investing.com/equities/medtronic");
		listaURL_09.add("https://es.investing.com/equities/meggitt");
		listaURL_09.add("https://es.investing.com/equities/melia-hotels-international-sa");
		listaURL_09.add("https://es.investing.com/equities/mercury-general-corp");
		listaURL_09.add("https://es.investing.com/equities/meredith-corp");
		listaURL_09.add("https://es.investing.com/equities/merlin-properties-sa");
		listaURL_09.add("https://es.investing.com/equities/metlife-inc");
		listaURL_09.add("https://es.investing.com/equities/mge-energy-inc");
		listaURL_09.add("https://es.investing.com/equities/michael-kors-holdings");
		listaURL_09.add("https://es.investing.com/equities/michelin");
		listaURL_09.add("https://es.investing.com/equities/micro-focus");
		listaURL_09.add("https://es.investing.com/equities/microsoft-corp");
		listaURL_09.add("https://es.investing.com/equities/middlesex-water");
		listaURL_09.add("https://es.investing.com/equities/mine-safety-appliances-comp");
		listaURL_09.add("https://es.investing.com/equities/molson-coors");
		listaURL_09.add("https://es.investing.com/equities/mond");
		listaURL_09.add("https://es.investing.com/equities/mondelez-international-inc");
		listaURL_09.add("https://es.investing.com/equities/moneysuprmkt");
		listaURL_09.add("https://es.investing.com/equities/moodys-corp");
		listaURL_09.add("https://es.investing.com/equities/muench.-rueck");
		listaURL_09.add("https://es.investing.com/equities/murray-international-trust");
		// listaURL_09.add("https://es.investing.com/equities/nacco-industries-inc");
		listaURL_09.add("https://es.investing.com/equities/national-fuel-gas-comp");
		listaURL_09.add("https://es.investing.com/equities/national-grid");
		listaURL_09.add("https://es.investing.com/equities/national-retail");
		// listaURL_09.add("https://es.investing.com/equities/natraceutical-sa");
		listaURL_09.add("https://es.investing.com/equities/nestle-ag");
		listaURL_09.add("https://es.investing.com/equities/newmont-mining");
		listaURL_09.add("https://es.investing.com/equities/nextera-energy-inc");
		listaURL_09.add("https://es.investing.com/equities/nh-hoteles-sa");
		listaURL_09.add("https://es.investing.com/equities/nike");
		listaURL_09.add("https://es.investing.com/equities/nordson-corp");
		listaURL_09.add("https://es.investing.com/equities/northrop-grumman");
		listaURL_09.add("https://es.investing.com/equities/northwest-natural-gas-comp");
		listaURL_09.add("https://es.investing.com/equities/novartis");
		listaURL_09.add("https://es.investing.com/equities/novo-nordisk");
		listaURL_09.add("https://es.investing.com/equities/novozymes-b");
		listaURL_09.add("https://es.investing.com/equities/nucor");
		// listaURL_09.add("https://es.investing.com/equities/oeneo");
		// listaURL_09.add("https://es.investing.com/equities/ohl");
		listaURL_09.add("https://es.investing.com/equities/old-republic-international");
		listaURL_09.add("https://es.investing.com/equities/omega-healthcare");
		listaURL_09.add("https://es.investing.com/equities/orkla-a");
		listaURL_09.add("https://es.investing.com/equities/packaging-corp");
		listaURL_09.add("https://es.investing.com/equities/paddy-power");
		listaURL_09.add("https://es.investing.com/equities/pandora");
		listaURL_09.add("https://es.investing.com/equities/parkerhannifin");
		listaURL_09.add("https://es.investing.com/equities/paychex-inc");
		// listaURL_09.add("https://es.investing.com/equities/paypoint");
		listaURL_09.add("https://es.investing.com/equities/pearson");
		listaURL_09.add("https://es.investing.com/equities/pennon");
		listaURL_09.add("https://es.investing.com/equities/pentair");
		listaURL_09.add("https://es.investing.com/equities/peoples-united-financial");
		listaURL_09.add("https://es.investing.com/equities/pepsico");
		listaURL_09.add("https://es.investing.com/equities/pernod-ricard");
		// listaURL_09.add("https://es.investing.com/equities/personal-group-holdings-plc");
		listaURL_09.add("https://es.investing.com/equities/pfizer");
		// listaURL_09.add("https://es.investing.com/equities/pharma-mar-sau");
		listaURL_09.add("https://es.investing.com/equities/philip-morris-intl");
		listaURL_09.add("https://es.investing.com/equities/pilgrims-pride-corp");
		listaURL_09.add("https://es.investing.com/equities/playtech-limited");
		listaURL_09.add("https://es.investing.com/equities/ppg-industries");
		listaURL_09.add("https://es.investing.com/equities/ppl-corp");
		// listaURL_09.add("https://es.investing.com/equities/preferred-apartment-communities");
		// listaURL_09.add("https://es.investing.com/equities/prim-sa");
		listaURL_09.add("https://es.investing.com/equities/primary-health-properties-plc");
		listaURL_09.add("https://es.investing.com/equities/principal-fin");
		// listaURL_09.add("https://es.investing.com/equities/prisa");
		listaURL_09.add("https://es.investing.com/equities/procter-gamble");
		listaURL_09.add("https://es.investing.com/equities/prologis");
		listaURL_09.add("https://es.investing.com/equities/prosegur-sa");
		listaURL_09.add("https://es.investing.com/equities/prudential-fin");
		// listaURL_09.add("https://es.investing.com/equities/psb-holdings");
		listaURL_09.add("https://es.investing.com/equities/publicis-groupe");
		// listaURL_09.add("https://es.investing.com/equities/pz-cussons");
		listaURL_09.add("https://es.investing.com/equities/qualcomm-inc");
		listaURL_09.add("https://es.investing.com/equities/randstad");
		// listaURL_09.add("https://es.investing.com/equities/realia-business-sa");
		listaURL_09.add("https://es.investing.com/equities/realty-income");
		listaURL_09.add("https://es.investing.com/equities/reckitt-benckiser");
		listaURL_09.add("https://es.investing.com/equities/recordati");
		listaURL_09.add("https://es.investing.com/equities/red-electrica");
		// listaURL_09.add("https://es.investing.com/equities/regis-corp");
		listaURL_09.add("https://es.investing.com/equities/renault");
		// listaURL_09.add("https://es.investing.com/equities/renta-4-banco-sa");
		// listaURL_09.add("https://es.investing.com/equities/renta-corp");
		listaURL_09.add("https://es.investing.com/equities/repsol-ypf");
		listaURL_09.add("https://es.investing.com/equities/restaurant-brands-intrnational");
		listaURL_09.add("https://es.investing.com/equities/richemont");
		listaURL_09.add("https://es.investing.com/equities/rightmove");
		listaURL_09.add("https://es.investing.com/equities/rio-tinto");
		listaURL_09.add("https://es.investing.com/equities/rli-corp");
		listaURL_09.add("https://es.investing.com/equities/roche-holding");
		listaURL_09.add("https://es.investing.com/equities/rockwell-automat");
		listaURL_09.add("https://es.investing.com/equities/rolls-royce");
		listaURL_09.add("https://es.investing.com/equities/roper-industries");
		listaURL_09.add("https://es.investing.com/equities/rotork");
		listaURL_09.add("https://es.investing.com/equities/royal-bank-of-canada-rbc");
		listaURL_09.add("https://es.investing.com/equities/royal-dsm-nv");
		listaURL_09.add("https://es.investing.com/equities/royal-dutch-shell-a-shr");
		listaURL_09.add("https://es.investing.com/equities/royal-gold-inc.");
		listaURL_09.add("https://es.investing.com/equities/royal-mail");
		listaURL_09.add("https://es.investing.com/equities/rpc-group");
		listaURL_09.add("https://es.investing.com/equities/rpm-intl-inc");
		// listaURL_09.add("https://es.investing.com/equities/rps-group");
		listaURL_09.add("https://es.investing.com/equities/rubis");
		listaURL_09.add("https://es.investing.com/equities/rwe-st-a");
		listaURL_09.add("https://es.investing.com/equities/rws-holdings");
		listaURL_09.add("https://es.investing.com/equities/s.e.b");
		listaURL_09.add("https://es.investing.com/equities/sacyr-valle");
		listaURL_09.add("https://es.investing.com/equities/sage-group");
		listaURL_09.add("https://es.investing.com/equities/sainsbury");
		listaURL_09.add("https://es.investing.com/equities/saint-gobain");
		listaURL_09.add("https://es.investing.com/equities/samsonite-international-sa");
		listaURL_09.add("https://es.investing.com/equities/sanofi");
		listaURL_09.add("https://es.investing.com/equities/sap-ag");
		listaURL_09.add("https://es.investing.com/equities/saracen-mineral-holdings");
		listaURL_09.add("https://es.investing.com/equities/schlumberger-ltd");
		// listaURL_09.add("https://es.investing.com/equities/schnitzer-steel");
		listaURL_09.add("https://es.investing.com/equities/schroders");
		listaURL_09.add("https://es.investing.com/equities/scor");
		listaURL_09.add("https://es.investing.com/equities/scotiabank");
		listaURL_09.add("https://es.investing.com/equities/scottish---southern-energy");
		listaURL_09.add("https://es.investing.com/equities/sei-investments");
		listaURL_09.add("https://es.investing.com/equities/senior-housing");
		listaURL_09.add("https://es.investing.com/equities/sherwinwilliams");
		listaURL_09.add("https://es.investing.com/equities/siemens");
		listaURL_09.add("https://es.investing.com/equities/silver-wheaton-corp");
		listaURL_09.add("https://es.investing.com/equities/simon-prop-grp");
		listaURL_09.add("https://es.investing.com/equities/sjw-corp");
		listaURL_09.add("https://es.investing.com/equities/smith---nephew-snats-inc");
		// listaURL_09.add("https://es.investing.com/equities/sniace-sa");
		listaURL_09.add("https://es.investing.com/equities/societe-generale");
		listaURL_09.add("https://es.investing.com/equities/sodexho-alliance");
		// listaURL_09.add("https://es.investing.com/equities/solaria-energia-y-medio-ambiente");
		listaURL_09.add("https://es.investing.com/equities/solvay");
		listaURL_09.add("https://es.investing.com/equities/sonoco-products-comp");
		listaURL_09.add("https://es.investing.com/equities/southern-co");
		listaURL_09.add("https://es.investing.com/equities/spectris");
		listaURL_09.add("https://es.investing.com/equities/spirax");
		listaURL_09.add("https://es.investing.com/equities/spirit-aerosystems-holdings-inc");
		listaURL_09.add("https://es.investing.com/equities/st-james");
		listaURL_09.add("https://es.investing.com/equities/standard-life");
		listaURL_09.add("https://es.investing.com/equities/stanley-works");
		listaURL_09.add("https://es.investing.com/equities/starbucks-corp");
		listaURL_09.add("https://es.investing.com/equities/stepan-comp");
		listaURL_09.add("https://es.investing.com/equities/stericycle-inc");
		listaURL_09.add("https://es.investing.com/equities/store-capital-corp");
		listaURL_09.add("https://es.investing.com/equities/stryker");
		listaURL_09.add("https://es.investing.com/equities/suez-environnement-s.a.");
		listaURL_09.add("https://es.investing.com/equities/suncor-energy-inc.");
		listaURL_09.add("https://es.investing.com/equities/sysco-corp");
		listaURL_09.add("https://es.investing.com/equities/sz-expressway");
		listaURL_09.add("https://es.investing.com/equities/t-rowe-price-gp");
		// listaURL_09.add("https://es.investing.com/equities/talgo");
		listaURL_09.add("https://es.investing.com/equities/tanger-factory");
		listaURL_09.add("https://es.investing.com/equities/target");
		listaURL_09.add("https://es.investing.com/equities/tate---lyle");
		listaURL_09.add("https://es.investing.com/equities/tecnicas-reunidas");
		// listaURL_09.add("https://es.investing.com/equities/telecom-plus-common-stock");
		listaURL_09.add("https://es.investing.com/equities/telefonica");
		listaURL_09.add("https://es.investing.com/equities/telephone-and-data-systems-inc");
		listaURL_09.add("https://es.investing.com/equities/tennant-co.");
		listaURL_09.add("https://es.investing.com/equities/texas-instru");
		listaURL_09.add("https://es.investing.com/equities/the-travelers-co");
		// listaURL_09.add("https://es.investing.com/equities/thedirectory.com-inc");
		listaURL_09.add("https://es.investing.com/equities/thomson-reuters-corp");
		listaURL_09.add("https://es.investing.com/equities/tiffany---co");
		listaURL_09.add("https://es.investing.com/equities/tjx-co-inc");
		listaURL_09.add("https://es.investing.com/equities/tompkins-financial-corp");
		listaURL_09.add("https://es.investing.com/equities/tootsie-roll-industries-inc");
		listaURL_09.add("https://es.investing.com/equities/toronto-dominion-bank");
		listaURL_09.add("https://es.investing.com/equities/total");
		// listaURL_09.add("https://es.investing.com/equities/tr-european-growth-trust-plc");
		listaURL_09.add("https://es.investing.com/equities/tractor-supply-company");
		listaURL_09.add("https://es.investing.com/equities/travis-perkins");
		// listaURL_09.add("https://es.investing.com/equities/treatt-plc");
		// listaURL_09.add("https://es.investing.com/equities/tubacex-sa");
		listaURL_09.add("https://es.investing.com/equities/tullet-prebon");
		listaURL_09.add("https://es.investing.com/equities/tyson-foods");
		listaURL_09.add("https://es.investing.com/equities/us-bancorp");
		listaURL_09.add("https://es.investing.com/equities/ubs-group-n");
		listaURL_09.add("https://es.investing.com/equities/ugi");
		listaURL_09.add("https://es.investing.com/equities/ultra-electronics");
		listaURL_09.add("https://es.investing.com/equities/umb-financial-corp");
		listaURL_09.add("https://es.investing.com/equities/under-armour");
		listaURL_09.add("https://es.investing.com/equities/uniball");
		listaURL_09.add("https://es.investing.com/equities/unilever");
		listaURL_09.add("https://es.investing.com/equities/union-pacific");
		listaURL_09.add("https://es.investing.com/equities/united-bankshares");
		listaURL_09.add("https://es.investing.com/equities/united-continenta");
		listaURL_09.add("https://es.investing.com/equities/united-parcel");
		listaURL_09.add("https://es.investing.com/equities/united-tech");
		listaURL_09.add("https://es.investing.com/equities/united-utilities");
		listaURL_09.add("https://es.investing.com/equities/universal-health-realty-trust");
		listaURL_09.add("https://es.investing.com/equities/universal-health-services");
		// listaURL_09.add("https://es.investing.com/equities/uralita-sa");
		listaURL_09.add("https://es.investing.com/equities/us-bancorp");
		listaURL_09.add("https://es.investing.com/equities/vail-resorts-inc");
		listaURL_09.add("https://es.investing.com/equities/valero-energy");
		listaURL_09.add("https://es.investing.com/equities/ventas-inc");
		listaURL_09.add("https://es.investing.com/equities/veolia-environ");
		listaURL_09.add("https://es.investing.com/equities/verisign-inc");
		listaURL_09.add("https://es.investing.com/equities/verizon-communications");
		// listaURL_09.add("https://es.investing.com/equities/vertice-trescientos-sesenta-grados");
		listaURL_09.add("https://es.investing.com/equities/vf-corp");
		listaURL_09.add("https://es.investing.com/equities/victrex");
		listaURL_09.add("https://es.investing.com/equities/vidrala-sa");
		listaURL_09.add("https://es.investing.com/equities/vinci");
		listaURL_09.add("https://es.investing.com/equities/visa-inc");
		listaURL_09.add("https://es.investing.com/equities/viscofan-sa");
		// listaURL_09.add("https://es.investing.com/equities/vocento-sa");
		listaURL_09.add("https://es.investing.com/equities/vodafone");
		listaURL_09.add("https://es.investing.com/equities/w-p-carey-inc");
		listaURL_09.add("https://es.investing.com/equities/w-w-grainger-inc");
		listaURL_09.add("https://es.investing.com/equities/wal-mart-stores");
		listaURL_09.add("https://es.investing.com/equities/walgreen-co");
		listaURL_09.add("https://es.investing.com/equities/weir-group");
		listaURL_09.add("https://es.investing.com/equities/wells-fargo");
		listaURL_09.add("https://es.investing.com/equities/wesco-intl");
		listaURL_09.add("https://es.investing.com/equities/west-pharmaceutical-services-inc");
		listaURL_09.add("https://es.investing.com/equities/westamerica-banco");
		// listaURL_09.add("https://es.investing.com/equities/weyco-group");
		listaURL_09.add("https://es.investing.com/equities/wh-smith");
		listaURL_09.add("https://es.investing.com/equities/whitbread");
		listaURL_09.add("https://es.investing.com/equities/williams-sonoma-inc");
		listaURL_09.add("https://es.investing.com/equities/wolters-kluwer");
		listaURL_09.add("https://es.investing.com/equities/wpp");
		// listaURL_09.add("https://es.investing.com/equities/wynnstay-group");
		// listaURL_09.add("https://es.investing.com/equities/young---co-brewery-nvtg");
		listaURL_09.add("https://es.investing.com/equities/zardoya-otis-sa");
		listaURL_09.add("https://es.investing.com/equities/zhejiangexpres");
		listaURL_09.add("https://es.investing.com/equities/zon-optimus-sa");
		LISTA_URL_GROUP.add(new URLGroup("DIVIDENDO", listaURL_09));
	}

	/**
	 * 
	 */
	private static final Double RPD_MINIMA = 5.0;

	/**
	 * 
	 */
	private static final Double CAP_MINIMA_EN_B = 2.0;

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
					procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_MENSUAL, TF_MENSUAL, RPD_MINIMA, CAP_MINIMA_EN_B);
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
