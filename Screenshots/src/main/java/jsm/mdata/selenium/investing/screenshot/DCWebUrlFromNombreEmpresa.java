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
import org.openqa.selenium.WebElement;
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
public class DCWebUrlFromNombreEmpresa extends DriverControllerBase
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DCWebUrlFromNombreEmpresa.class);

	/**
	 * URL Groups
	 */
	private final static List<URLGroup> LISTA_EMPRESAS_GROUP = new ArrayList<URLGroup>();
	static
	{
		// --
		// -- LISTA DE EMPRESAS
		// --
		List<String> listaEmpresas = new ArrayList<String>();
		listaEmpresas.add("1ST SOURCE");
		listaEmpresas.add("3I GROUP");
		listaEmpresas.add("3M");
		listaEmpresas.add("A.G. BARR");
		listaEmpresas.add("A.O. SMITH");
		listaEmpresas.add("AALBERTS INDUSTRIES");
		listaEmpresas.add("ABBOTT LABORATORIES");
		listaEmpresas.add("ABBVIE");
		listaEmpresas.add("ABCAM");
		listaEmpresas.add("ABERDEEN ASSET MANAGEMENT");
		listaEmpresas.add("ABM INDUSTRIES");
		listaEmpresas.add("ACCENTURE");
		listaEmpresas.add("ACCIONA");
		listaEmpresas.add("ACERINOX");
		listaEmpresas.add("ACS");
		listaEmpresas.add("ADIDAS");
		listaEmpresas.add("ADOLFO DOMINGUEZ");
		listaEmpresas.add("AEGON");
		listaEmpresas.add("AENA");
		listaEmpresas.add("AFLAC");
		listaEmpresas.add("AHOLD");
		listaEmpresas.add("AIR LIQUIDE SA");
		listaEmpresas.add("AIR PRODUCTS AND CHEMICAL");
		listaEmpresas.add("AIRBUS");
		listaEmpresas.add("ALBA");
		listaEmpresas.add("ALFA LAVAL");
		listaEmpresas.add("ALLIANZ");
		listaEmpresas.add("ALMIRALL");
		listaEmpresas.add("ALTRIA GROUP");
		listaEmpresas.add("AMADEUS");
		listaEmpresas.add("AMCOR");
		listaEmpresas.add("AMERICAN EXPRESS");
		listaEmpresas.add("AMERICAN STATES WATER");
		listaEmpresas.add("AMERICAN TOWER");
		listaEmpresas.add("AMERICAN WATER WORKS");
		listaEmpresas.add("AMERIPRISE FINANCIAL");
		listaEmpresas.add("AMGEN");
		listaEmpresas.add("AMPER");
		listaEmpresas.add("ANHEUSER BUSCH INBEV");
		listaEmpresas.add("APPLE");
		listaEmpresas.add("APPLIED MATERIALS");
		listaEmpresas.add("APTARGROUP");
		listaEmpresas.add("AQUA AMERICA");
		listaEmpresas.add("ARCELORMITTAL");
		listaEmpresas.add("ARCHER DANIELS MIDLAND");
		listaEmpresas.add("ARMANINO FOODS OF DISTINCTION");
		listaEmpresas.add("ARROW FINANCIAL");
		listaEmpresas.add("ARTESIAN RESOURCES");
		listaEmpresas.add("ASHMORE GROUP");
		listaEmpresas.add("ASHTEAD GROUP");
		listaEmpresas.add("ASSOCIATED BRITISH FOODS");
		listaEmpresas.add("ASTRAZENECA");
		listaEmpresas.add("AT AND T");
		listaEmpresas.add("ATLANTIA");
		listaEmpresas.add("ATLAS COPCO");
		listaEmpresas.add("ATMOS ENERGY");
		listaEmpresas.add("ATRESMEDIA");
		listaEmpresas.add("AUTOMATIC DATA PROCESSING");
		listaEmpresas.add("AVIVA");
		listaEmpresas.add("AXA");
		listaEmpresas.add("AXEL SPRINGER SE");
		listaEmpresas.add("B AND G FOODS");
		listaEmpresas.add("BADGER METER");
		listaEmpresas.add("BAE SYSTEMS");
		listaEmpresas.add("BAKKAFROST");
		listaEmpresas.add("BALL");
		listaEmpresas.add("BANCFIRST");
		listaEmpresas.add("BANCO SABADELL");
		listaEmpresas.add("BANCO SANTANDER");
		listaEmpresas.add("BANK OF AMERICA");
		listaEmpresas.add("BANK OF NOVA SCOTIA");
		listaEmpresas.add("BANKERS INVESTMENT TRUST");
		listaEmpresas.add("BANKIA");
		listaEmpresas.add("BANKINTER");
		listaEmpresas.add("BARRICK GOLD");
		listaEmpresas.add("BARÓN DE LEY");
		listaEmpresas.add("BASF");
		listaEmpresas.add("BAYER AG");
		listaEmpresas.add("BBVA");
		listaEmpresas.add("BCE");
		listaEmpresas.add("BEAZLEY");
		listaEmpresas.add("BECTON DICKINSON");
		listaEmpresas.add("BELLWAY");
		listaEmpresas.add("BEMIS");
		listaEmpresas.add("BHP BILLITON");
		listaEmpresas.add("BIC");
		listaEmpresas.add("BIOSEARCH");
		listaEmpresas.add("BLACK HILLS");
		listaEmpresas.add("BLACK ROCK");
		listaEmpresas.add("BLOOMSBURY PUBLISHING");
		listaEmpresas.add("BME");
		listaEmpresas.add("BMW");
		listaEmpresas.add("BODEGAS RIOJANAS");
		listaEmpresas.add("BOEING");
		listaEmpresas.add("BORGWARNER");
		listaEmpresas.add("BOUYGUES SA");
		listaEmpresas.add("BOVIS HOMES GROUP");
		listaEmpresas.add("BP");
		listaEmpresas.add("BRADY");
		listaEmpresas.add("BREMBO");
		listaEmpresas.add("BRISTOL MYERS");
		listaEmpresas.add("BRITISH AMERICAN TOBACCO");
		listaEmpresas.add("BRITISH LAND");
		listaEmpresas.add("BRITVIC");
		listaEmpresas.add("BROADCOM");
		listaEmpresas.add("BROOKFIELD ASSET MANAGEMENT");
		listaEmpresas.add("BROOKFIELD INFRAESTRUCTURE PARTNERS");
		listaEmpresas.add("BROOKS MACDONALD");
		listaEmpresas.add("BROWN AND BROWN");
		listaEmpresas.add("BROWN FORMAN");
		listaEmpresas.add("BRUNSWICK");
		listaEmpresas.add("BT GROUP");
		listaEmpresas.add("BUCKEYE");
		listaEmpresas.add("BUNZL");
		listaEmpresas.add("BURBERRY");
		listaEmpresas.add("BUREAU VERITAS SA");
		listaEmpresas.add("CAESARSTONE");
		listaEmpresas.add("CAF");
		listaEmpresas.add("CAIXABANK");
		listaEmpresas.add("CAL MAINE FOODS");
		listaEmpresas.add("CALEDONIA INVESTMENTS");
		listaEmpresas.add("CALIFORNIA WATER SERVICE");
		listaEmpresas.add("CAMPARI");
		listaEmpresas.add("CANADIAN IMPERIAL BANK OF COMMERCE");
		listaEmpresas.add("CANADIAN NATIONAL RAILWAY");
		listaEmpresas.add("CARDINAL HEALTH");
		listaEmpresas.add("CARETECH HOLDINGS");
		listaEmpresas.add("CARLISLE COMPANIES");
		listaEmpresas.add("CARNIVAL");
		listaEmpresas.add("CATALANA OCCIDENTE");
		listaEmpresas.add("CATERPILLAR");
		listaEmpresas.add("CELLNEX");
		listaEmpresas.add("CENTRICA");
		listaEmpresas.add("CF INDUSTRIES");
		listaEmpresas.add("CHESAPEAKE FINANCIAL SHARES");
		listaEmpresas.add("CHESNARA");
		listaEmpresas.add("CHEVRON");
		listaEmpresas.add("CHIPOTLE");
		listaEmpresas.add("CHOCOLADEFABRIKEN LINDT AND SPRUENGLI AG PARTICIPATION");
		listaEmpresas.add("CHUBB LIMITED");
		listaEmpresas.add("CHURCH AND DWIGHT");
		listaEmpresas.add("CIE AUTOMOTIVE");
		listaEmpresas.add("CINCINNATI FINANCIAL");
		listaEmpresas.add("CINTAS");
		listaEmpresas.add("CISCO SYSTEMS");
		listaEmpresas.add("CITY OF LONDON INVESTMENT TRUST");
		listaEmpresas.add("CLOROX");
		listaEmpresas.add("CLOSE BROTHERS GROUP");
		listaEmpresas.add("CLÍNICA BAVIERA");
		listaEmpresas.add("CNP ASSURANCES SA");
		listaEmpresas.add("COCA COLA");
		listaEmpresas.add("COEMAC");
		listaEmpresas.add("COLGATE PALMOLIVE");
		listaEmpresas.add("COLONIAL");
		listaEmpresas.add("COLOPLAST");
		listaEmpresas.add("COMCAST");
		listaEmpresas.add("COMMERCE BANCSHARES");
		listaEmpresas.add("COMMUNITY BANK SYSTEM");
		listaEmpresas.add("COMMUNITY TRUST BANC");
		listaEmpresas.add("COMPAGNIE FINANCIERE RICHEMONT");
		listaEmpresas.add("COMPASS GROUP");
		listaEmpresas.add("COMPASS MINERALS");
		listaEmpresas.add("COMPUTER SERVICES");
		listaEmpresas.add("CONCURRENT TECHNOLOGIES");
		listaEmpresas.add("CONNECTICUT WATER SERVICE");
		listaEmpresas.add("CONSOLIDATED EDISON");
		listaEmpresas.add("CONSTELLATION BRANDS A");
		listaEmpresas.add("CONTINENTAL");
		listaEmpresas.add("CORNING");
		listaEmpresas.add("CORPORACIÓN FINANCIERA ALBA");
		listaEmpresas.add("CORREIOS");
		listaEmpresas.add("CORTICEIRA AMORIM");
		listaEmpresas.add("COSTCO");
		listaEmpresas.add("CRACKER BARREL OLD COUNTRY");
		listaEmpresas.add("CRANEWARE");
		listaEmpresas.add("CRANSWICK");
		listaEmpresas.add("CRODA INTERNATIONAL");
		listaEmpresas.add("CSX");
		listaEmpresas.add("CUBESMART");
		listaEmpresas.add("CULLEN/FROST BANKERS");
		listaEmpresas.add("CUMMINS");
		listaEmpresas.add("CVS HEALTH");
		listaEmpresas.add("DAIMLER");
		listaEmpresas.add("DANONE");
		listaEmpresas.add("DAVITA HEALTHCARE");
		listaEmpresas.add("DECHRA PHARMACEUTICALS");
		listaEmpresas.add("DEERE");
		listaEmpresas.add("DEOLEO");
		listaEmpresas.add("DERWENT LONDON");
		listaEmpresas.add("DEUTSCHE EUROSHOP AG");
		listaEmpresas.add("DEUTSCHE POST");
		listaEmpresas.add("DEUTSCHE TELEKOM");
		listaEmpresas.add("DEWHURST");
		listaEmpresas.add("DIA");
		listaEmpresas.add("DIAGEO");
		listaEmpresas.add("DIGITAL REALTY TRUST");
		listaEmpresas.add("DIPLOMA");
		listaEmpresas.add("DISCOVER FINANCIAL SERVICES");
		listaEmpresas.add("DISNEY");
		listaEmpresas.add("DLR");
		listaEmpresas.add("DOLLAR GENERAL");
		listaEmpresas.add("DOMINION ENERGY");
		listaEmpresas.add("DOMINO'S PIZZA GROUP");
		listaEmpresas.add("DONALDSON");
		listaEmpresas.add("DOVER");
		listaEmpresas.add("DS SMITH");
		listaEmpresas.add("DUKE ENERGY");
		listaEmpresas.add("DUNELM GROUP");
		listaEmpresas.add("DURO FELGUERA");
		listaEmpresas.add("E.ON");
		listaEmpresas.add("EAGLE FINANCIAL SERVICES");
		listaEmpresas.add("EATON VANCE");
		listaEmpresas.add("EBRO FOODS");
		listaEmpresas.add("ECOLAB");
		listaEmpresas.add("EDINBURGH INVESTMENT TRUST");
		listaEmpresas.add("EDISON INTERNACIONAL");
		listaEmpresas.add("EDP ENERGIAS DE PORTUGAL SA");
		listaEmpresas.add("EIFFAGE SA");
		listaEmpresas.add("ELECNOR");
		listaEmpresas.add("ELI LILLY AND CO");
		listaEmpresas.add("EMERSON ELECTRIC");
		listaEmpresas.add("ENAGAS");
		listaEmpresas.add("ENBRIDGE");
		listaEmpresas.add("ENCE");
		listaEmpresas.add("ENDESA");
		listaEmpresas.add("ENGIE");
		listaEmpresas.add("ERCROS");
		listaEmpresas.add("ERG");
		listaEmpresas.add("ERIE INDEMNITY");
		listaEmpresas.add("ESSENTRA");
		listaEmpresas.add("ESSILOR");
		listaEmpresas.add("ESSILORLUXOTTICA SA");
		listaEmpresas.add("EURONEXT");
		listaEmpresas.add("EXXON MOBIL");
		listaEmpresas.add("FAES");
		listaEmpresas.add("FARMERS AND MERCHANTS BANCORP");
		listaEmpresas.add("FASTENAL");
		listaEmpresas.add("FCC");
		listaEmpresas.add("FEDERAL REALTY INVESTMENT TRUST");
		listaEmpresas.add("FERROVIAL");
		listaEmpresas.add("FIELMANN AG");
		listaEmpresas.add("FIRST DERIVATIVES");
		listaEmpresas.add("FLOWERS FOODS");
		listaEmpresas.add("FLUIDRA");
		listaEmpresas.add("FLUTTER ENTERTAINMENT");
		listaEmpresas.add("FORTIS");
		listaEmpresas.add("FRANKLIN ELECTRIC");
		listaEmpresas.add("FRANKLIN RESOURCES");
		listaEmpresas.add("FRAPORT AG");
		listaEmpresas.add("FRESENIUS MEDICAL CARE");
		listaEmpresas.add("FUCHS PETROLUB SE PREF");
		listaEmpresas.add("G4S");
		listaEmpresas.add("GAMESA");
		listaEmpresas.add("GENERAL DYNAMICS");
		listaEmpresas.add("GENERAL ELECTRIC");
		listaEmpresas.add("GENERAL MILLS");
		listaEmpresas.add("GENTEX");
		listaEmpresas.add("GENUINE PARTS");
		listaEmpresas.add("GENUS");
		listaEmpresas.add("GESTAMP");
		listaEmpresas.add("GILEAD SCIENCES");
		listaEmpresas.add("GIVAUDAN");
		listaEmpresas.add("GLAXOSMITHKLINE");
		listaEmpresas.add("GLENCORE");
		listaEmpresas.add("GOLDCORP");
		listaEmpresas.add("GOOGLE");
		listaEmpresas.add("GORMAN RUPP");
		listaEmpresas.add("GREENE KING");
		listaEmpresas.add("GRIFOLS");
		listaEmpresas.add("GROEP BRUSSEL LAMBERT");
		listaEmpresas.add("GROUPE BRUXELLES LAMBERT SA");
		listaEmpresas.add("GULF MARINE SERVICES");
		listaEmpresas.add("H AND R BLOCK");
		listaEmpresas.add("H.B. FULLER");
		listaEmpresas.add("HALLIBURTON");
		listaEmpresas.add("HALMA");
		listaEmpresas.add("HAMMERSON");
		listaEmpresas.add("HANESBRANDS");
		listaEmpresas.add("HARLEY DAVIDSON");
		listaEmpresas.add("HASBRO");
		listaEmpresas.add("HCP");
		listaEmpresas.add("HEINEKEN");
		listaEmpresas.add("HELMERICH AND PAYNE");
		listaEmpresas.add("HENDERSON EUROTRUST");
		listaEmpresas.add("HENDERSON SMALLER COMPANIES INVESTMENT TRUST");
		listaEmpresas.add("HENKEL");
		listaEmpresas.add("HENNES AND MAURITZ AB");
		listaEmpresas.add("HERA");
		listaEmpresas.add("HERMES INTERNATIONAL SCA");
		listaEmpresas.add("HERSHEY");
		listaEmpresas.add("HIGHCROFT INVESTMENTS");
		listaEmpresas.add("HILL AND SMITH HOLDINGS");
		listaEmpresas.add("HILTON FOOD GROUP");
		listaEmpresas.add("HOME DEPOT");
		listaEmpresas.add("HORMEL FOODS");
		listaEmpresas.add("HOWDEN JOINERY");
		listaEmpresas.add("HUGO BOSS");
		listaEmpresas.add("I.M.A. INDUSTRIA MACCHINE AUTOMATICHE");
		listaEmpresas.add("IBERDROLA");
		listaEmpresas.add("IBERPAPEL");
		listaEmpresas.add("IBM");
		listaEmpresas.add("ILLINOIS TOOL WORKS");
		listaEmpresas.add("IMI");
		listaEmpresas.add("IMPERIAL BRANDS");
		listaEmpresas.add("INCHCAPE");
		listaEmpresas.add("INDITEX");
		listaEmpresas.add("INDRA");
		listaEmpresas.add("INFORMA");
		listaEmpresas.add("INGENICO GROUP SA");
		listaEmpresas.add("INGERSOLL RAND");
		listaEmpresas.add("INGREDION");
		listaEmpresas.add("INTEL");
		listaEmpresas.add("INTERTEK GROUP");
		listaEmpresas.add("INVESCO INCOME GROWTH TRUST");
		listaEmpresas.add("INVESTEC");
		listaEmpresas.add("J. SMART");
		listaEmpresas.add("J.M. SMUCKER");
		listaEmpresas.add("JACK HENRY AND ASSOCIATES");
		listaEmpresas.add("JAMES FISHER AND SONS");
		listaEmpresas.add("JD SPORTS FASHION");
		listaEmpresas.add("JIANGSU EXPRESSWAY");
		listaEmpresas.add("JOHN WILEY AND SONS");
		listaEmpresas.add("JOHNSON AND JOHNSON");
		listaEmpresas.add("JOHNSON CONTROLS");
		listaEmpresas.add("JOHNSON MATTHEY");
		listaEmpresas.add("JPMORGAN CHASE");
		listaEmpresas.add("JUDGES SCIENTIFIC");
		listaEmpresas.add("JUPITER FUND MANAGEMENT");
		listaEmpresas.add("KELLOGG");
		listaEmpresas.add("KERRY GROUP");
		listaEmpresas.add("KIMBERLY CLARK");
		listaEmpresas.add("KIMCO REALTY");
		listaEmpresas.add("KINDER MORGAN");
		listaEmpresas.add("KINGFISHER");
		listaEmpresas.add("KLEPIERRE SA");
		listaEmpresas.add("KONECRANES OYJ");
		listaEmpresas.add("KORIAN SA");
		listaEmpresas.add("KRAFT HEINZ");
		listaEmpresas.add("KROGER");
		listaEmpresas.add("L BRANDS");
		listaEmpresas.add("L'OREAL");
		listaEmpresas.add("L'OREAL SA");
		listaEmpresas.add("LAGARDERE SCA");
		listaEmpresas.add("LANCASTER COLONY");
		listaEmpresas.add("LEAR");
		listaEmpresas.add("LEGAL AND GENERAL");
		listaEmpresas.add("LEGAL AND GENERAL GROUP");
		listaEmpresas.add("LEGGETT AND PLATT");
		listaEmpresas.add("LINDE");
		listaEmpresas.add("LINDT AND SPRUNGLI");
		listaEmpresas.add("LINGOTES ESPECIALES");
		listaEmpresas.add("LOCKHEED MARTIN");
		listaEmpresas.add("LOGISTA");
		listaEmpresas.add("LOUIS VUITTON");
		listaEmpresas.add("LOWE'S COMPANIES");
		listaEmpresas.add("LSE");
		listaEmpresas.add("LSL PROPERTIES");
		listaEmpresas.add("LTC PROPERTIES");
		listaEmpresas.add("MAIN STREET CAPITAL");
		listaEmpresas.add("MAINTEL HOLDINGS");
		listaEmpresas.add("MANPOWER GROUP");
		listaEmpresas.add("MAPFRE");
		listaEmpresas.add("MARKS AND SPENCER GROUP");
		listaEmpresas.add("MASTERCARD");
		listaEmpresas.add("MATTIOLI WOODS");
		listaEmpresas.add("MCCORMICK");
		listaEmpresas.add("MCDONALD'S");
		listaEmpresas.add("MCGRATH RENTCORP");
		listaEmpresas.add("MCKESSON CORP");
		listaEmpresas.add("MDU RESOURCES");
		listaEmpresas.add("MEARS GROUP");
		listaEmpresas.add("MEDIASET");
		listaEmpresas.add("MEDTRONIC");
		listaEmpresas.add("MEGGITT");
		listaEmpresas.add("MELIÁ HOTELS");
		listaEmpresas.add("MERCURY GENERAL");
		listaEmpresas.add("MEREDITH");
		listaEmpresas.add("MERLÍN PROPERTIES");
		listaEmpresas.add("METLIFE");
		listaEmpresas.add("MGE ENERGY");
		listaEmpresas.add("MICHAEL KORS");
		listaEmpresas.add("MICHELIN");
		listaEmpresas.add("MICRO FOCUS INTERNATIONAL");
		listaEmpresas.add("MICROSOFT");
		listaEmpresas.add("MIDDLESEX WATER");
		listaEmpresas.add("MIQUEL Y COSTAS");
		listaEmpresas.add("MOLSON COORS");
		listaEmpresas.add("MONDELEZ INTERNATIONAL");
		listaEmpresas.add("MONDI");
		listaEmpresas.add("MONEYSUPERMARKET.COM GROUP");
		listaEmpresas.add("MOODYS");
		listaEmpresas.add("MSA SAFETY");
		listaEmpresas.add("MUNICH RE.");
		listaEmpresas.add("MUNICH REINSURANCE");
		listaEmpresas.add("MURRAY INTERNATIONAL TRUST");
		listaEmpresas.add("NACCO INDUSTRIES");
		listaEmpresas.add("NATIONAL FUEL GAS");
		listaEmpresas.add("NATIONAL GRID");
		listaEmpresas.add("NATIONAL RETAIL PROPERTIES");
		listaEmpresas.add("NATRA");
		listaEmpresas.add("NATURGY (GAS NATURAL)");
		listaEmpresas.add("NESTLE");
		listaEmpresas.add("NESTLE SA");
		listaEmpresas.add("NEXT");
		listaEmpresas.add("NEXTERA ENERGY INC");
		listaEmpresas.add("NH HOTELES");
		listaEmpresas.add("NICHOLS");
		listaEmpresas.add("NIKE");
		listaEmpresas.add("NORDSON");
		listaEmpresas.add("NORTHROP GRUMMAN");
		listaEmpresas.add("NORTHWEST NATURAL GAS");
		listaEmpresas.add("NORTHWEST NATURAL HOLDING");
		listaEmpresas.add("NOS");
		listaEmpresas.add("NOVARTIS");
		listaEmpresas.add("NOVO NORDISK");
		listaEmpresas.add("NOVOZYMES");
		listaEmpresas.add("NUCOR");
		listaEmpresas.add("OENEO");
		listaEmpresas.add("OHL");
		listaEmpresas.add("OLD REPUBLIC INTERNATIONAL");
		listaEmpresas.add("OMEGA HEALTH CARE");
		listaEmpresas.add("ORKLA");
		listaEmpresas.add("PANDORA");
		listaEmpresas.add("PARKER HANNIFIN");
		listaEmpresas.add("PAYCHEX");
		listaEmpresas.add("PAYPOINT");
		listaEmpresas.add("PEARSON");
		listaEmpresas.add("PENNON GROUP");
		listaEmpresas.add("PENTAIR");
		listaEmpresas.add("PEOPLE'S UNITED FINANCIAL");
		listaEmpresas.add("PEPSICO");
		listaEmpresas.add("PERNOD RICARD");
		listaEmpresas.add("PERSONAL GROUP HOLDINGS");
		listaEmpresas.add("PFIZER");
		listaEmpresas.add("PHARMAMAR");
		listaEmpresas.add("PHILIP MORRIS INTERNATIONAL");
		listaEmpresas.add("PILGRIM´S PRIDE");
		listaEmpresas.add("PKG");
		listaEmpresas.add("PLAYTECH");
		listaEmpresas.add("PPG INDUSTRIES");
		listaEmpresas.add("PPL");
		listaEmpresas.add("PREFERRED APARTMENT COMMUNITIES");
		listaEmpresas.add("PRIM");
		listaEmpresas.add("PRIMARY HEALTH PROPERTIES");
		listaEmpresas.add("PRINCIPAL FINANCIAL GROUP");
		listaEmpresas.add("PRISA");
		listaEmpresas.add("PROCTER AND GAMBLE");
		listaEmpresas.add("PROLOGIS");
		listaEmpresas.add("PROSEGUR");
		listaEmpresas.add("PRUDENTIAL");
		listaEmpresas.add("PSB HOLDINGS");
		listaEmpresas.add("PUBLICIS GROUPE SA");
		listaEmpresas.add("PZ CUSSONS");
		listaEmpresas.add("QUALCOMM");
		listaEmpresas.add("RANDSTAD");
		listaEmpresas.add("REALIA");
		listaEmpresas.add("REALTY INCOME");
		listaEmpresas.add("RECKITT BECKINSER");
		listaEmpresas.add("RECORDATI");
		listaEmpresas.add("RED ELECTRICA");
		listaEmpresas.add("REGIS");
		listaEmpresas.add("REIG JOFRE");
		listaEmpresas.add("RENAULT");
		listaEmpresas.add("RENTA 4");
		listaEmpresas.add("RENTA CORPORACIÓN");
		listaEmpresas.add("REPSOL");
		listaEmpresas.add("RESTAURANT BRANDS");
		listaEmpresas.add("REXAM");
		listaEmpresas.add("RIGHTMOVE");
		listaEmpresas.add("RIO TINTO");
		listaEmpresas.add("RLI");
		listaEmpresas.add("ROCHE");
		listaEmpresas.add("ROCHE HOLDING GENUSSSCHEINE");
		listaEmpresas.add("ROCKWELL AUTOMATION");
		listaEmpresas.add("ROLLS ROYCE");
		listaEmpresas.add("ROPER TECHNOLOGIES");
		listaEmpresas.add("ROTORK");
		listaEmpresas.add("ROVI");
		listaEmpresas.add("ROYAL BANK OF CANADA");
		listaEmpresas.add("ROYAL DSM");
		listaEmpresas.add("ROYAL DUTCH SHELL");
		listaEmpresas.add("ROYAL GOLD");
		listaEmpresas.add("ROYAL MAIL");
		listaEmpresas.add("ROYAL PHILIPS");
		listaEmpresas.add("ROYAL VOPAK");
		listaEmpresas.add("RPC GROUP");
		listaEmpresas.add("RPM INTERNATIONAL");
		listaEmpresas.add("RPS GROUP");
		listaEmpresas.add("RUBIS SCA");
		listaEmpresas.add("RWE");
		listaEmpresas.add("RWS HOLDINGS");
		listaEmpresas.add("S AND P GLOBAL");
		listaEmpresas.add("SACYR");
		listaEmpresas.add("SAINSBURY'S");
		listaEmpresas.add("SAINT GOBAIN");
		listaEmpresas.add("SAMSONITE");
		listaEmpresas.add("SANOFI");
		listaEmpresas.add("SAP");
		listaEmpresas.add("SARACEN");
		listaEmpresas.add("SCHLUMBERGER");
		listaEmpresas.add("SCHNITZER STEEL");
		listaEmpresas.add("SCHRODERS");
		listaEmpresas.add("SCOR SE");
		listaEmpresas.add("SEEK");
		listaEmpresas.add("SEI INVESTMENTS");
		listaEmpresas.add("SENIOR HOUSING PROPERTIES");
		listaEmpresas.add("SES SA");
		listaEmpresas.add("SHENZHEN EXPRESSWAY");
		listaEmpresas.add("SHERWIN WILLIAMS");
		listaEmpresas.add("SIEMENS AG");
		listaEmpresas.add("SILVER WHEATON");
		listaEmpresas.add("SIMON PROPERTY GROUP");
		listaEmpresas.add("SJW");
		listaEmpresas.add("SKANDINAVISKA ENSKILDA BANKEN AB CLASS A");
		listaEmpresas.add("SKF");
		listaEmpresas.add("SMITH AO");
		listaEmpresas.add("SMITH NEPHEW");
		listaEmpresas.add("SNIACE");
		listaEmpresas.add("SOCIETE BIC SA");
		listaEmpresas.add("SODEXO SA");
		listaEmpresas.add("SOLARIA");
		listaEmpresas.add("SOLVAY SA");
		listaEmpresas.add("SONOCO PRODUCTS");
		listaEmpresas.add("SOUTHERN");
		listaEmpresas.add("SPECTRIS GROUP");
		listaEmpresas.add("SPIRAX SARCO ENGINEERING");
		listaEmpresas.add("SPIRIT AEROSYSTEMS HOLDING");
		listaEmpresas.add("SSE");
		listaEmpresas.add("ST. JAMES'S PLACE");
		listaEmpresas.add("STANDARD LIFE ABERDEEN");
		listaEmpresas.add("STANLEY BLACK AND DECKER");
		listaEmpresas.add("STARBUCKS");
		listaEmpresas.add("STEPAN");
		listaEmpresas.add("STERICYCLE");
		listaEmpresas.add("STORE CAPITAL");
		listaEmpresas.add("STRYKER");
		listaEmpresas.add("SUEZ");
		listaEmpresas.add("SUNCOR");
		listaEmpresas.add("SYSCO");
		listaEmpresas.add("T. ROWE PRICE GROUP");
		listaEmpresas.add("TALGO");
		listaEmpresas.add("TANGER FACTORY OUTLET CENTERS");
		listaEmpresas.add("TARGET");
		listaEmpresas.add("TATE AND LYLE");
		listaEmpresas.add("TECNICAS REUNIDAS");
		listaEmpresas.add("TELECOM PLUS");
		listaEmpresas.add("TELEFÓNICA");
		listaEmpresas.add("TELEPHONE AND DATA SYS.");
		listaEmpresas.add("TENNANT");
		listaEmpresas.add("TEXAS INSTRUMENTS");
		listaEmpresas.add("THE SAGE GROUP");
		listaEmpresas.add("THOMSON REUTERS");
		listaEmpresas.add("TIFFANY");
		listaEmpresas.add("TJX COMPANIES");
		listaEmpresas.add("TOMPKINS FINANCIAL");
		listaEmpresas.add("TOOTSIE ROLL INDUSTRIES");
		listaEmpresas.add("TORONTO DOMINION BANK");
		listaEmpresas.add("TOTAL SA");
		listaEmpresas.add("TP ICAP");
		listaEmpresas.add("TR EUROPEAN GROWTH TRUST");
		listaEmpresas.add("TRACTOR SUPPLY");
		listaEmpresas.add("TRAVELERS COMPANIES");
		listaEmpresas.add("TRAVIS PERKINS");
		listaEmpresas.add("TREATT");
		listaEmpresas.add("TUBACEX");
		listaEmpresas.add("TYSON FOODS");
		listaEmpresas.add("TÉCNICAS REUNIDAS");
		listaEmpresas.add("U.S. BANCORP");
		listaEmpresas.add("UBS GROUP");
		listaEmpresas.add("UGI");
		listaEmpresas.add("ULTRA ELECTRONICS HOLDINGS");
		listaEmpresas.add("UMB FINANCIAL");
		listaEmpresas.add("UNDER ARMOUR");
		listaEmpresas.add("UNIBAIL RODAMCO");
		listaEmpresas.add("UNILEVER");
		listaEmpresas.add("UNION PACIFIC");
		listaEmpresas.add("UNITED BANKSHARES");
		listaEmpresas.add("UNITED PARCEL SERVICE");
		listaEmpresas.add("UNITED TECHNOLOGIES");
		listaEmpresas.add("UNITED UTILITIES");
		listaEmpresas.add("UNIVERSAL");
		listaEmpresas.add("UNIVERSAL HEALTH REALTY TRUST");
		listaEmpresas.add("V.F.");
		listaEmpresas.add("VAIL RESORTS");
		listaEmpresas.add("VALERO ENERGY");
		listaEmpresas.add("VECTREN");
		listaEmpresas.add("VENTAS");
		listaEmpresas.add("VEOLIA");
		listaEmpresas.add("VEREIT");
		listaEmpresas.add("VERISIGN");
		listaEmpresas.add("VERIZON COMMUNICATIONS");
		listaEmpresas.add("VF");
		listaEmpresas.add("VICTREX");
		listaEmpresas.add("VIDRALA");
		listaEmpresas.add("VINCI SA");
		listaEmpresas.add("VISA");
		listaEmpresas.add("VISCOFAN");
		listaEmpresas.add("VOCENTO");
		listaEmpresas.add("VODAFONE GROUP");
		listaEmpresas.add("W.P. CAREY");
		listaEmpresas.add("W.W. GRAINGER");
		listaEmpresas.add("WALGREENS BOOTS ALLIANCE");
		listaEmpresas.add("WALMART");
		listaEmpresas.add("WEIR GROUP");
		listaEmpresas.add("WELLS FARGO");
		listaEmpresas.add("WELLTOWER");
		listaEmpresas.add("WESCO INTERNATIONAL");
		listaEmpresas.add("WEST PHARMACEUTICAL SERVICES");
		listaEmpresas.add("WESTAMERICA BANCORP");
		listaEmpresas.add("WEYCO GROUP");
		listaEmpresas.add("WH SMITH");
		listaEmpresas.add("WHITBREAD");
		listaEmpresas.add("WILLIAMS SONOMA");
		listaEmpresas.add("WOLTERS KLUWER");
		listaEmpresas.add("WPP");
		listaEmpresas.add("WYNNSTAY GROUP");
		listaEmpresas.add("YOUNG AND CO'S BREWERY");
		listaEmpresas.add("ZARDOYA OTIS");
		listaEmpresas.add("ZHEJIANG EXPRESSWAY");
		LISTA_EMPRESAS_GROUP.add(new URLGroup("EMPRESAS", listaEmpresas));
	}

	/**
	 * 
	 */
	private final static Integer MAX_ERROR_RETRY = 2;

	/**
	 * 
	 */
	private final static String URL_INICIAL = "https://es.investing.com";

	/**
	 * 
	 */
	// private static final Double RPD_MINIMA = 5.0;

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

		LOGGER.info("Iniciando proceso");
		for (URLGroup empresasGroup : LISTA_EMPRESAS_GROUP)
		{
			String downloadFolder = empresasGroup.getDownloadFolder();
			LOGGER.info("Procesando grupo [" + downloadFolder + "]");
			List<String> listaEmpresas = empresasGroup.getListaURL();
			int empresaIdx = 0;
			int errorRetry = 0;
			while (empresaIdx < listaEmpresas.size())
			{
				String nombreEmpresa = null;
				try
				{
					LOGGER.info("Cargando URL inicial");
					driver.get(URL_INICIAL);
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("disclaimer")));

					nombreEmpresa = listaEmpresas.get(empresaIdx);
					LOGGER.info("Buscando empresa [" + nombreEmpresa + "]");
					if (errorRetry > 0)
					{
						LOGGER.info("Reintento [" + errorRetry + "]");
					}
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("searchText")));
					WebElement textBoxBusqueda = driver.findElement(By.className("searchText"));
					textBoxBusqueda.clear();
					textBoxBusqueda.sendKeys(nombreEmpresa);
					new WebDriverWait(driver, 10).until(ExpectedConditions.attributeToBe(By.className("js-footer-link-text"), "innerHTML", nombreEmpresa));

					// LOGGER.info("Filtrando resultado solo para Acciones");
					// new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.className("js-dropdown-text-input")));
					// WebElement filtroResultados = driver.findElement(By.className("js-dropdown-text-input"));
					// LOGGER.info("-------------------------------" + filtroResultados.getAttribute("innerHTML"));
					// filtroResultados.click();
					// new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("js-quote-types-dropdown-ul")));
					// WebElement panelFiltro = driver.findElement(By.className("js-quote-types-dropdown-ul"));
					// List<WebElement> listaElementos = panelFiltro.findElements(By.tagName("li"));
					// for (WebElement filtro : listaElementos)
					// {
					// new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(filtro));
					// if (filtro.getAttribute("innerHTML") != null && filtro.getAttribute("innerHTML").equalsIgnoreCase("Acciones"))
					// {
					// filtro.click();
					// break;
					// }
					// }

					LOGGER.info("Seleccionando primer resultado");
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.className("js-quote-row-template")));
					List<WebElement> listaResultados = driver.findElements(By.className("js-quote-row-template"));
					listaResultados.get(0).click();

					LOGGER.info("Recuperando URL");
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Gráfico")));
					String hrefElemento = driver.getCurrentUrl();
					if (hrefElemento.equalsIgnoreCase(URL_INICIAL))
					{
						throw new Exception("URL inadecuada");
					}

					LOGGER.info("Empresa [" + nombreEmpresa + "] URL [" + hrefElemento + "]");
					// procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_MENSUAL, TF_MENSUAL, RPD_MINIMA);
					// procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_SEMANAL, TF_SEMANAL, null);
					// procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_DIARIO, TF_DIARIO);
					empresaIdx++;
					errorRetry = 0;
				}
				catch (Exception e)
				{
					LOGGER.error("Se ha producido un error", e);
					errorRetry++;
					if (errorRetry >= MAX_ERROR_RETRY)
					{
						LOGGER.info("Empresa [" + nombreEmpresa + "] URL [desconocida]");
						empresaIdx++;
						errorRetry = 0;
					}
				}
			}
		}

	}

}
