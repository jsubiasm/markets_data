/**
 * 
 */
package jsm.mdata.selenium.yahoo.screenshot;

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

import jsm.mdata.selenium.common.URLGroup;
import jsm.mdata.selenium.common.WebDriverBase;

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
		listaEmpresas.add("1st Source Corporation");
		listaEmpresas.add("3I Group PLC");
		listaEmpresas.add("3I Infrastructure PLC");
		listaEmpresas.add("3M Company");
		listaEmpresas.add("ABM Industries Incorporated");
		listaEmpresas.add("AENA SME S.A.");
		listaEmpresas.add("ASML Holding NV");
		listaEmpresas.add("AT&T Inc");
		listaEmpresas.add("ATRION Corporation");
		listaEmpresas.add("AXA SA");
		listaEmpresas.add("AXIS Capital Holdings Ltd");
		listaEmpresas.add("Aalberts Industries NV");
		listaEmpresas.add("Aarons Inc");
		listaEmpresas.add("AbbVie Inc");
		listaEmpresas.add("Abbott Laboratories");
		listaEmpresas.add("Abcam PLC");
		listaEmpresas.add("Accenture plc");
		listaEmpresas.add("Acciona S.A.");
		listaEmpresas.add("Accor SA");
		listaEmpresas.add("Acerinox S.A.");
		listaEmpresas.add("Ackermans & Van Haaren NV");
		listaEmpresas.add("Actividades de Construcci�n y Servicios S.A.");
		listaEmpresas.add("Activision Blizzard Inc");
		listaEmpresas.add("Adidas AG");
		listaEmpresas.add("Aegon NV");
		listaEmpresas.add("Aflac Inc");
		listaEmpresas.add("Ageas");
		listaEmpresas.add("Air Liquide SA");
		listaEmpresas.add("Air Products and Chemicals Inc");
		listaEmpresas.add("Airbus Group SE");
		listaEmpresas.add("Akzo Nobel NV");
		listaEmpresas.add("Albemarle Corp");
		listaEmpresas.add("Alfa Laval AB");
		listaEmpresas.add("Algonquin Power & Utilities Corp");
		listaEmpresas.add("Alimentation Couche-Tard Inc");
		listaEmpresas.add("Alliance Trust PLC");
		listaEmpresas.add("Alliant Energy Corp");
		listaEmpresas.add("Allianz SE VNA O.N.");
		listaEmpresas.add("Allied Properties REIT");
		listaEmpresas.add("Almirall S.A.");
		listaEmpresas.add("Alphabet Inc Class C");
		listaEmpresas.add("Altria Group");
		listaEmpresas.add("Amadeus IT Holding S.A.");
		listaEmpresas.add("Amcor");
		listaEmpresas.add("American Equity Investment Life Holding Co");
		listaEmpresas.add("American Express Company");
		listaEmpresas.add("American Financial Group Inc");
		listaEmpresas.add("American States Water Company");
		listaEmpresas.add("American Tower Corp");
		listaEmpresas.add("American Water Works");
		listaEmpresas.add("Ameriprise Financial Inc");
		listaEmpresas.add("AmerisourceBergen");
		listaEmpresas.add("Amgen Inc");
		listaEmpresas.add("Analog Devices Inc");
		listaEmpresas.add("Anheuser Busch Inbev SA NV");
		listaEmpresas.add("Apple Inc");
		listaEmpresas.add("Applied Industrial Technologies");
		listaEmpresas.add("Applied Materials Inc");
		listaEmpresas.add("AptarGroup Inc");
		listaEmpresas.add("Aqua America Inc");
		listaEmpresas.add("ArcelorMittal S.A.");
		listaEmpresas.add("Archer-Daniels-Midland Company");
		listaEmpresas.add("Arkema SA");
		listaEmpresas.add("Ashland Global Holdings Inc");
		listaEmpresas.add("Ashmore Group Plc");
		listaEmpresas.add("Ashtead Group PLC");
		listaEmpresas.add("Associated British Foods PLC");
		listaEmpresas.add("Assurant Inc");
		listaEmpresas.add("AstraZeneca PLC");
		listaEmpresas.add("Atco Ltd");
		listaEmpresas.add("Atlantia SpA");
		listaEmpresas.add("Atlas Copco AB Class A");
		listaEmpresas.add("Atmos Energy Corporation");
		listaEmpresas.add("Atos SE");
		listaEmpresas.add("Automatic Data Processing Inc");
		listaEmpresas.add("Avista Corporation");
		listaEmpresas.add("Aviva PLC");
		listaEmpresas.add("Axel Springer SE");
		listaEmpresas.add("B&G Foods Inc");
		listaEmpresas.add("BAE Systems PLC");
		listaEmpresas.add("BASF SE NA O.N.");
		listaEmpresas.add("BCE Inc.");
		listaEmpresas.add("BHP Billiton Ltd");
		listaEmpresas.add("BHP Group PLC");
		listaEmpresas.add("BIC");
		listaEmpresas.add("BNP Paribas Fortis SA");
		listaEmpresas.add("BNP Paribas SA");
		listaEmpresas.add("BOK Financial Corporation");
		listaEmpresas.add("BP PLC");
		listaEmpresas.add("BT Group PLC");
		listaEmpresas.add("Badger Meter Inc");
		listaEmpresas.add("Balchem Corporation");
		listaEmpresas.add("Ball Corporation");
		listaEmpresas.add("Baloise Holding AG");
		listaEmpresas.add("BancFirst Corporation");
		listaEmpresas.add("Banco Bilbao Vizcaya Argentaria S.A.");
		listaEmpresas.add("Banco Santander S.A.");
		listaEmpresas.add("Banco de Sabadell S.A.");
		listaEmpresas.add("Bank Ozk");
		listaEmpresas.add("Bank of America Corp");
		listaEmpresas.add("Bank of Montreal");
		listaEmpresas.add("Bank of Nova Scotia");
		listaEmpresas.add("Bankers Investment Trust");
		listaEmpresas.add("Bankia S.A.");
		listaEmpresas.add("Bankinter S.A.");
		listaEmpresas.add("Barnes Group Inc");
		listaEmpresas.add("Barrick Gold Corp");
		listaEmpresas.add("Bayer AG NA");
		listaEmpresas.add("Bayerische Motoren Werke AG");
		listaEmpresas.add("Bcolombia");
		listaEmpresas.add("Beazley PLC");
		listaEmpresas.add("Becton Dickinson and Company");
		listaEmpresas.add("Beiersdorf AG O.N.");
		listaEmpresas.add("Bellway PLC");
		listaEmpresas.add("Bemis Company Inc");
		listaEmpresas.add("Best Buy Co Inc");
		listaEmpresas.add("Biomerieux SA");
		listaEmpresas.add("Black Hills Corporation");
		listaEmpresas.add("BlackRock Inc");
		listaEmpresas.add("Boeing Co");
		listaEmpresas.add("Bolsas y Mercados Espa�oles");
		listaEmpresas.add("BorgWarner Inc");
		listaEmpresas.add("Bouygues SA");
		listaEmpresas.add("Bovis Homes Group PLC");
		listaEmpresas.add("Boyd Group Income Fund");
		listaEmpresas.add("Brady Corporation");
		listaEmpresas.add("Brenntag AG");
		listaEmpresas.add("Brinker International Inc");
		listaEmpresas.add("Bristol-Myers Squibb Company");
		listaEmpresas.add("British American Tobacco PLC");
		listaEmpresas.add("British Land Company PLC");
		listaEmpresas.add("Britvic PLC");
		listaEmpresas.add("Broadcom Inc");
		listaEmpresas.add("Broadridge Financial Solutions Inc");
		listaEmpresas.add("Brookfield Asset Management Inc");
		listaEmpresas.add("Brookfield Infrastructure Partners LP");
		listaEmpresas.add("Brookfield Property Partners LP");
		listaEmpresas.add("Brookfield Renewable Energy Partners LP Pref E");
		listaEmpresas.add("Brookfield Renewable Partners LP");
		listaEmpresas.add("Brown & Brown Inc");
		listaEmpresas.add("Brown Forman");
		listaEmpresas.add("Brunswick Corporation");
		listaEmpresas.add("Buckeye Partners LP");
		listaEmpresas.add("Bunge Limited");
		listaEmpresas.add("Bunzl PLC");
		listaEmpresas.add("Burberry Group PLC");
		listaEmpresas.add("Bureau Veritas SA");
		listaEmpresas.add("CAE Inc.");
		listaEmpresas.add("CCL Industries Inc");
		listaEmpresas.add("CF Industries Holdings Inc");
		listaEmpresas.add("CH Robinson Worldwide Inc");
		listaEmpresas.add("CIE Automotive S.A.");
		listaEmpresas.add("CMS Energy Corporation");
		listaEmpresas.add("CNP Assurances SA");
		listaEmpresas.add("CRH PLC");
		listaEmpresas.add("CSX Corporation");
		listaEmpresas.add("CT Real Estate Investment Trust");
		listaEmpresas.add("CVS Health Corp");
		listaEmpresas.add("CaixaBank S.A.");
		listaEmpresas.add("Cal-Maine Foods Inc");
		listaEmpresas.add("Caledonia Investments");
		listaEmpresas.add("California Water Service Group");
		listaEmpresas.add("Canadian Apartment Properties REIT");
		listaEmpresas.add("Canadian Imperial Bank Of Commerce");
		listaEmpresas.add("Canadian National Railway Co");
		listaEmpresas.add("Canadian Natural Resources Ltd");
		listaEmpresas.add("Canadian Tire Corp Ltd");
		listaEmpresas.add("Canadian Utilities Limited");
		listaEmpresas.add("Canadian Western Bank");
		listaEmpresas.add("Cantel Medical Corp");
		listaEmpresas.add("Capgemini SE");
		listaEmpresas.add("Capital Power Corporation");
		listaEmpresas.add("Capri Holdings Ltd");
		listaEmpresas.add("Cardinal Health Inc");
		listaEmpresas.add("Carlisle Companies Incorporated");
		listaEmpresas.add("Carlsberg A/S B");
		listaEmpresas.add("Carnival Corporation");
		listaEmpresas.add("Caseys General Stores Inc");
		listaEmpresas.add("Casino Guichard Perrachon SA");
		listaEmpresas.add("Caterpillar Inc");
		listaEmpresas.add("Cboe Global Markets Inc");
		listaEmpresas.add("Celanese Corporation");
		listaEmpresas.add("Cellnex Telecom S.A.");
		listaEmpresas.add("CenterPoint Energy Inc");
		listaEmpresas.add("Centrica PLC");
		listaEmpresas.add("Chemed Corp");
		listaEmpresas.add("Chesapeake Energy Corporation");
		listaEmpresas.add("Chesapeake Utilities Corporation");
		listaEmpresas.add("Chevron Corp");
		listaEmpresas.add("Chipotle Mexican Grill Inc");
		listaEmpresas.add("Chocoladefabriken Lindt & Spruengli AG");
		listaEmpresas.add("Chubb Ltd");
		listaEmpresas.add("Church & Dwight Company Inc");
		listaEmpresas.add("Churchill Downs Incorporated");
		listaEmpresas.add("Cincinnati Financial Corporation");
		listaEmpresas.add("Cineplex Inc.");
		listaEmpresas.add("Cintas Corporation");
		listaEmpresas.add("Cisco Systems Inc");
		listaEmpresas.add("City Of London Investment Trust");
		listaEmpresas.add("Clorox Co");
		listaEmpresas.add("Close Brothers Group plc");
		listaEmpresas.add("Coca-Cola Company");
		listaEmpresas.add("Cogeco Communications Inc");
		listaEmpresas.add("Cogeco Inc.");
		listaEmpresas.add("Cohen & Steers Inc");
		listaEmpresas.add("Colgate-Palmolive Company");
		listaEmpresas.add("Coloplast A/S");
		listaEmpresas.add("Columbia Sportswear Company");
		listaEmpresas.add("Comcast Corp");
		listaEmpresas.add("Comerica Inc");
		listaEmpresas.add("Commerce Bancshares Inc");
		listaEmpresas.add("Community Bank System Inc");
		listaEmpresas.add("Compagnie Financiere Richemont SA");
		listaEmpresas.add("Compagnie Generale des Etablissements Michelin SCA");
		listaEmpresas.add("Compagnie Plastic Omnium SA");
		listaEmpresas.add("Compagnie de Saint Gobain SA");
		listaEmpresas.add("Compass Group PLC");
		listaEmpresas.add("Compass Minerals International Inc");
		listaEmpresas.add("Consolidated Edison Inc");
		listaEmpresas.add("Constellation Brands Inc Class A");
		listaEmpresas.add("Construcciones y Auxiliar de Ferrocarriles SA");
		listaEmpresas.add("Continental AG O.N.");
		listaEmpresas.add("CoreSite Realty Corporation");
		listaEmpresas.add("Corning Incorporated");
		listaEmpresas.add("Corporaci�n Financiera Alba S.A.");
		listaEmpresas.add("Corticeira Amorim");
		listaEmpresas.add("Costco Wholesale Corp");
		listaEmpresas.add("Cracker Barrel Old Country Store");
		listaEmpresas.add("Cranswick PLC");
		listaEmpresas.add("Crest Nicholson Holdings plc");
		listaEmpresas.add("Croda International PLC");
		listaEmpresas.add("Cr�dit Agricole SA");
		listaEmpresas.add("CubeSmart");
		listaEmpresas.add("Cullen/Frost Bankers Inc");
		listaEmpresas.add("Cummins Inc");
		listaEmpresas.add("DS Smith PLC");
		listaEmpresas.add("DSV");
		listaEmpresas.add("DTE Energy Company");
		listaEmpresas.add("DaVita HealthCare Partners Inc");
		listaEmpresas.add("Daimler AG NA O.N.");
		listaEmpresas.add("Danone SA");
		listaEmpresas.add("Dassault Aviation SA");
		listaEmpresas.add("Dassault Systemes SE");
		listaEmpresas.add("Davide Campari Milano SpA");
		listaEmpresas.add("Dechra Pharmaceuticals PLC");
		listaEmpresas.add("Deere & Company");
		listaEmpresas.add("Derwent London PLC");
		listaEmpresas.add("Deutsche Boerse AG");
		listaEmpresas.add("Deutsche Euroshop AG");
		listaEmpresas.add("Deutsche Post AG NA O.N.");
		listaEmpresas.add("Deutsche Telekom AG Na");
		listaEmpresas.add("Deutsche Wohnen AG");
		listaEmpresas.add("Diageo PLC");
		listaEmpresas.add("Digital Realty Trust Inc");
		listaEmpresas.add("Diploma PLC");
		listaEmpresas.add("Discover Financial Services");
		listaEmpresas.add("Dollar General Corporation");
		listaEmpresas.add("Dollarama Inc");
		listaEmpresas.add("Dominion Energy Inc");
		listaEmpresas.add("Domino'S Pizza Enterprises Ltd");
		listaEmpresas.add("Domino�s Pizza Group PLC");
		listaEmpresas.add("Domino�s Pizza Inc");
		listaEmpresas.add("Domtar Corporation");
		listaEmpresas.add("Donaldson Company Inc");
		listaEmpresas.add("Douglas Dynamics Inc");
		listaEmpresas.add("Dover Corporation");
		listaEmpresas.add("Duke Energy Corporation");
		listaEmpresas.add("Dunelm Group PLC");
		listaEmpresas.add("E.ON SE NA");
		listaEmpresas.add("EDP Energias de Portugal SA ADR");
		listaEmpresas.add("EDP");
		listaEmpresas.add("ERG");
		listaEmpresas.add("Eaton Corporation PLC");
		listaEmpresas.add("Eaton Vance Corp");
		listaEmpresas.add("Ebro Foods S.A.");
		listaEmpresas.add("Ecolab Inc");
		listaEmpresas.add("Edinburgh Investment Trust");
		listaEmpresas.add("Edison International");
		listaEmpresas.add("Eiffage SA");
		listaEmpresas.add("Eli Lilly and Company");
		listaEmpresas.add("Elia");
		listaEmpresas.add("Elisa Oyj");
		listaEmpresas.add("Emerson Electric Company");
		listaEmpresas.add("Empire Company Ltd");
		listaEmpresas.add("Ems Chemie Hld");
		listaEmpresas.add("Enag�s S.A.");
		listaEmpresas.add("Enbridge Inc");
		listaEmpresas.add("Endesa S.A.");
		listaEmpresas.add("Energy Transfer Equity LP");
		listaEmpresas.add("Enghouse Systems Ltd");
		listaEmpresas.add("Engie SA");
		listaEmpresas.add("Enterprise Products Partners LP");
		listaEmpresas.add("Equitable Group Inc.");
		listaEmpresas.add("Equity Lifestyle Properties Inc");
		listaEmpresas.add("Erie Indemnity Company");
		listaEmpresas.add("Essentra PLC");
		listaEmpresas.add("Essex Property Trust Inc");
		listaEmpresas.add("EssilorLuxottica SA");
		listaEmpresas.add("Etablissementen Franz Colruyt NV");
		listaEmpresas.add("Eurofins Scientific SE");
		listaEmpresas.add("Euronext");
		listaEmpresas.add("Eutelsat Communications SA");
		listaEmpresas.add("Evercore Partners Inc");
		listaEmpresas.add("Evergy Inc");
		listaEmpresas.add("Eversource Energy");
		listaEmpresas.add("Evonik Industries AG");
		listaEmpresas.add("Exchange Income Corporation");
		listaEmpresas.add("Expeditors International of Washington Inc");
		listaEmpresas.add("Extra Space Storage Inc");
		listaEmpresas.add("Exxon Mobil Corp");
		listaEmpresas.add("FactSet Research Systems Inc");
		listaEmpresas.add("Faes Farma SA");
		listaEmpresas.add("Fastenal Company");
		listaEmpresas.add("Faurecia");
		listaEmpresas.add("FedEx Corporation");
		listaEmpresas.add("Federal Realty Investment Trust");
		listaEmpresas.add("Ferrovial S.A.");
		listaEmpresas.add("Fielmann AG");
		listaEmpresas.add("Fiera Capital Corporation");
		listaEmpresas.add("Finning International Inc.");
		listaEmpresas.add("First Interstate BancSystem Inc");
		listaEmpresas.add("First Merchants Corporation");
		listaEmpresas.add("First National Financial Corp");
		listaEmpresas.add("Flowers Foods Inc");
		listaEmpresas.add("Fluidra S.A.");
		listaEmpresas.add("Flutter Entertainment PLC");
		listaEmpresas.add("Fomento de Construcciones y Contratas S.A.");
		listaEmpresas.add("Ford Motor Company");
		listaEmpresas.add("Fortis Inc");
		listaEmpresas.add("Fortum Oyj");
		listaEmpresas.add("Franco-Nevada Corporation");
		listaEmpresas.add("Franklin Electric Co Inc");
		listaEmpresas.add("Franklin Resources Inc");
		listaEmpresas.add("Fraport AG");
		listaEmpresas.add("Freenet AG NA");
		listaEmpresas.add("Freni Brembo SpA");
		listaEmpresas.add("Fresenius Medical Care KGAA ST");
		listaEmpresas.add("Fresenius SE & Co KGAA O.N.");
		listaEmpresas.add("Fuchs Petrolub AG O.N.");
		listaEmpresas.add("Fuchs Petrolub AG VZO Pref");
		listaEmpresas.add("G4S PLC");
		listaEmpresas.add("GEA Group AG");
		listaEmpresas.add("GN Store Nord");
		listaEmpresas.add("Galp Energia Nom");
		listaEmpresas.add("Gecina SA");
		listaEmpresas.add("General Dynamics Corporation");
		listaEmpresas.add("General Electric Company");
		listaEmpresas.add("General Mills Inc");
		listaEmpresas.add("Gentex Corporation");
		listaEmpresas.add("Genuine Parts Co");
		listaEmpresas.add("Genus PLC");
		listaEmpresas.add("Genworth MI Canada Inc.");
		listaEmpresas.add("George Weston Limited");
		listaEmpresas.add("Gestamp Automocion SA");
		listaEmpresas.add("Getlink SE");
		listaEmpresas.add("Gildan Activewear Inc.");
		listaEmpresas.add("Gilead Sciences Inc");
		listaEmpresas.add("Givaudan SA");
		listaEmpresas.add("Glanbia PLC");
		listaEmpresas.add("GlaxoSmithKline PLC");
		listaEmpresas.add("Glencore PLC");
		listaEmpresas.add("Goldman Sachs Group Inc");
		listaEmpresas.add("Gores Metropoulos Inc");
		listaEmpresas.add("Graco Inc");
		listaEmpresas.add("Granite Real Estate Investment Trust");
		listaEmpresas.add("Greene King PLC");
		listaEmpresas.add("Grifols S.A.");
		listaEmpresas.add("Group 1 Automotive Inc");
		listaEmpresas.add("Groupe Bruxelles Lambert SA");
		listaEmpresas.add("Grupo Catalana de Occidente S.A.");
		listaEmpresas.add("H & M Hennes & Mauritz AB B");
		listaEmpresas.add("H B Fuller Company");
		listaEmpresas.add("H&R Block Inc");
		listaEmpresas.add("HCP Inc");
		listaEmpresas.add("HDFC Bank Limited ADR");
		listaEmpresas.add("Halliburton Company");
		listaEmpresas.add("Halma PLC");
		listaEmpresas.add("Hammerson PLC");
		listaEmpresas.add("Hanesbrands Inc");
		listaEmpresas.add("Hannover Rueck SE");
		listaEmpresas.add("Harley-Davidson Inc");
		listaEmpresas.add("Hasbro Inc");
		listaEmpresas.add("Healthcare Services Group Inc");
		listaEmpresas.add("Heico Corporation");
		listaEmpresas.add("Heidelbergcement AG O.N.");
		listaEmpresas.add("Heineken Holding NV");
		listaEmpresas.add("Heineken");
		listaEmpresas.add("Helmerich and Payne Inc");
		listaEmpresas.add("Henderson Smaller Cos Inv Tst");
		listaEmpresas.add("Henkel AG & Co KGAA ST O.N.");
		listaEmpresas.add("Hera");
		listaEmpresas.add("Hermes International SCA");
		listaEmpresas.add("Hershey Company");
		listaEmpresas.add("Hillenbrand Inc");
		listaEmpresas.add("Hochtief AG");
		listaEmpresas.add("Holly Energy Partners LP");
		listaEmpresas.add("Home Depot Inc");
		listaEmpresas.add("Horace Mann Educators Corporation");
		listaEmpresas.add("Hormel Foods Corporation");
		listaEmpresas.add("Howden Joinery Group Plc");
		listaEmpresas.add("Hubbell Inc");
		listaEmpresas.add("Hugo Boss AG NA O.N.");
		listaEmpresas.add("Hyatt Hotels Corporation");
		listaEmpresas.add("ICADE");
		listaEmpresas.add("IDEX Corporation");
		listaEmpresas.add("Iberdrola S.A.");
		listaEmpresas.add("Iliad");
		listaEmpresas.add("Illinois Tool Works Inc");
		listaEmpresas.add("Imerys SA");
		listaEmpresas.add("Imperial Brands PLC");
		listaEmpresas.add("Imperial Oil Ltd");
		listaEmpresas.add("Inchcape PLC");
		listaEmpresas.add("Indra Sistemas S.A.");
		listaEmpresas.add("Industria Macchine Automatiche");
		listaEmpresas.add("Industria de Dise�o y Textil S.A.");
		listaEmpresas.add("Industrial Alliance Insurance and Financial Services Inc Pref Series G Class A");
		listaEmpresas.add("Infineon Technologies AG NA O.N.");
		listaEmpresas.add("Informa PLC");
		listaEmpresas.add("Ingenico Group SA");
		listaEmpresas.add("Ingersoll-Rand PLC");
		listaEmpresas.add("Ingredion Incorporated");
		listaEmpresas.add("Inmobiliaria Colonial S.A.");
		listaEmpresas.add("Innergex Renewable Energy Inc.");
		listaEmpresas.add("Intact Financial Corporation");
		listaEmpresas.add("Intel Corporation");
		listaEmpresas.add("Inter Parfums Inc");
		listaEmpresas.add("Inter Pipeline Ltd");
		listaEmpresas.add("InterRent REIT");
		listaEmpresas.add("International Bancshares Corporation");
		listaEmpresas.add("International Business Machines");
		listaEmpresas.add("International Flavors & Fragrances Inc");
		listaEmpresas.add("International Speedway Corporation");
		listaEmpresas.add("Intertek Group PLC");
		listaEmpresas.add("Invesco Plc");
		listaEmpresas.add("Investec PLC");
		listaEmpresas.add("Ipsen SA");
		listaEmpresas.add("J Sainsbury PLC");
		listaEmpresas.add("JB Hunt Transport Services Inc");
		listaEmpresas.add("JC Decaux SA");
		listaEmpresas.add("JD Sports Fashion PLC");
		listaEmpresas.add("JM Smucker Company");
		listaEmpresas.add("JPMorgan Chase & Co");
		listaEmpresas.add("Jack Henry & Associates Inc");
		listaEmpresas.add("James Fisher & Sons plc");
		listaEmpresas.add("Jiangsu Expressway Co Ltd");
		listaEmpresas.add("John Wiley & Sons");
		listaEmpresas.add("Johnson & Johnson");
		listaEmpresas.add("Johnson Controls International PLC");
		listaEmpresas.add("Johnson Matthey PLC");
		listaEmpresas.add("Jupiter Fund Management Plc");
		listaEmpresas.add("KONE Oyj");
		listaEmpresas.add("Kellogg Company");
		listaEmpresas.add("Kering SA");
		listaEmpresas.add("Kerry Group");
		listaEmpresas.add("Keyera Corp.");
		listaEmpresas.add("Kimberly-Clark Corporation");
		listaEmpresas.add("Kimco Realty Corporation");
		listaEmpresas.add("Kinder Morgan Inc");
		listaEmpresas.add("Kinepolis Group NV");
		listaEmpresas.add("Kingfisher PLC");
		listaEmpresas.add("Kion Group AG");
		listaEmpresas.add("Klepierre SA");
		listaEmpresas.add("Kobenhavns Lufthavne");
		listaEmpresas.add("Konecranes ABP");
		listaEmpresas.add("Koninklijke Ahold Delhaize NV");
		listaEmpresas.add("Koninklijke DSM NV");
		listaEmpresas.add("Koninklijke Philips NV");
		listaEmpresas.add("Koninklijke Vopak NV");
		listaEmpresas.add("Korian Medica SA");
		listaEmpresas.add("Kraft Heinz Co");
		listaEmpresas.add("Kroger Company");
		listaEmpresas.add("L Brands Inc");
		listaEmpresas.add("L'Or�al SA");
		listaEmpresas.add("L3Harris Technologies Inc");
		listaEmpresas.add("LEG Immobilien AG");
		listaEmpresas.add("LTC Properties Inc");
		listaEmpresas.add("LVMH Moet Hennessy Louis Vuitton SE");
		listaEmpresas.add("Laboratorios Farmaceuticos Rovi S.A.");
		listaEmpresas.add("Lagardere SCA");
		listaEmpresas.add("Lancaster Colony Corporation");
		listaEmpresas.add("Landstar System Inc");
		listaEmpresas.add("Lanxess AG");
		listaEmpresas.add("Laurentian Bank Of Canada");
		listaEmpresas.add("Lazard Ltd");
		listaEmpresas.add("Lear Corporation");
		listaEmpresas.add("Legal & General Group PLC");
		listaEmpresas.add("Leggett & Platt Incorporated");
		listaEmpresas.add("Lennox International Inc");
		listaEmpresas.add("Lincoln Electric Holdings Inc");
		listaEmpresas.add("Linde AG O.N.");
		listaEmpresas.add("Linde PLC");
		listaEmpresas.add("Lithia Motors Inc");
		listaEmpresas.add("Littelfuse Inc");
		listaEmpresas.add("Loblaw Companies Ltd");
		listaEmpresas.add("Lockheed Martin Corporation");
		listaEmpresas.add("Logista");
		listaEmpresas.add("London Stock Exchange Group PLC");
		listaEmpresas.add("Lotus Bakeries");
		listaEmpresas.add("Lowe�s Companies Inc");
		listaEmpresas.add("MDU Resources Group Inc");
		listaEmpresas.add("MGE Energy Inc");
		listaEmpresas.add("MSA Safety");
		listaEmpresas.add("MSC Industrial Direct Company Inc");
		listaEmpresas.add("Magellan Midstream Partners LP");
		listaEmpresas.add("Magna International Inc");
		listaEmpresas.add("Main Street Capital Corporation");
		listaEmpresas.add("ManpowerGroup Inc");
		listaEmpresas.add("Manulife Financial Corp");
		listaEmpresas.add("Mapfre S.A.");
		listaEmpresas.add("MarketAxess Holdings Inc");
		listaEmpresas.add("Marks and Spencer Group PLC");
		listaEmpresas.add("Marriott International Inc");
		listaEmpresas.add("Marsh & McLennan Companies Inc");
		listaEmpresas.add("Mastercard Inc");
		listaEmpresas.add("Matthews International Corporation");
		listaEmpresas.add("Maxim Integrated Products Inc");
		listaEmpresas.add("McCormick & Company Incorporated");
		listaEmpresas.add("McDonald�s Corporation");
		listaEmpresas.add("McGrath RentCorp");
		listaEmpresas.add("McKesson Corporation");
		listaEmpresas.add("Mediaset Espa�a Comunicaci�n S.A.");
		listaEmpresas.add("Mediobanca Banca di Credito Finanziario SpA");
		listaEmpresas.add("Medtronic PLC");
		listaEmpresas.add("Meggitt PLC");
		listaEmpresas.add("Meli� Hotels International S.A.");
		listaEmpresas.add("Merck & Company Inc");
		listaEmpresas.add("Merck KGaA");
		listaEmpresas.add("Mercury General Corporation");
		listaEmpresas.add("Meredith Corporation");
		listaEmpresas.add("Merlin Properties Socimi S.A.");
		listaEmpresas.add("MetLife Inc");
		listaEmpresas.add("Methanex Corporation");
		listaEmpresas.add("Micro Focus International PLC");
		listaEmpresas.add("Microchip Technology Inc");
		listaEmpresas.add("Microsoft Corporation");
		listaEmpresas.add("Middlesex Water Company");
		listaEmpresas.add("Molson Coors Brewing Co Class B");
		listaEmpresas.add("Mondelez International Inc");
		listaEmpresas.add("Mondi PLC");
		listaEmpresas.add("Moneysupermarket.Com Group PLC");
		listaEmpresas.add("Monro Muffler Brake Inc");
		listaEmpresas.add("Moodys Corporation");
		listaEmpresas.add("Munich Re");
		listaEmpresas.add("Murray International Trust");
		listaEmpresas.add("NH Hotel Group S.A.");
		listaEmpresas.add("National Fuel Gas Company");
		listaEmpresas.add("National Grid PLC");
		listaEmpresas.add("National Health Investors Inc");
		listaEmpresas.add("National HealthCare Corporation");
		listaEmpresas.add("National Retail Properties Inc");
		listaEmpresas.add("Naturgy Energy Group S.A.");
		listaEmpresas.add("Nestl� SA");
		listaEmpresas.add("NewMarket Corporation");
		listaEmpresas.add("Newmont Goldcorp Corp");
		listaEmpresas.add("Next PLC");
		listaEmpresas.add("Nextera Energy Inc");
		listaEmpresas.add("Nike Inc");
		listaEmpresas.add("Nokian Renkaat Oyj");
		listaEmpresas.add("Nordson Corporation");
		listaEmpresas.add("NorthWestern Corporation");
		listaEmpresas.add("Northrop Grumman Corporation");
		listaEmpresas.add("Northwest Bancshares Inc");
		listaEmpresas.add("Northwest Natural Gas Co");
		listaEmpresas.add("Nos SGPS SA");
		listaEmpresas.add("Novartis AG");
		listaEmpresas.add("Novo Nordisk A/S Class B");
		listaEmpresas.add("Novo Nordisk A/S");
		listaEmpresas.add("Novozymes A/S B");
		listaEmpresas.add("Nu Skin Enterprises Inc");
		listaEmpresas.add("Nucor Corp");
		listaEmpresas.add("OGE Energy Corporation");
		listaEmpresas.add("ONEOK Inc");
		listaEmpresas.add("Occidental Petroleum Corporation");
		listaEmpresas.add("Old Republic International Corp");
		listaEmpresas.add("Omega Healthcare Investors Inc");
		listaEmpresas.add("Omnicom Group Inc");
		listaEmpresas.add("Onex Corp");
		listaEmpresas.add("Open Text Corp");
		listaEmpresas.add("Oracle Corporation");
		listaEmpresas.add("Orion Oyj B");
		listaEmpresas.add("Orkla ASA");
		listaEmpresas.add("Osisko Gold Ro");
		listaEmpresas.add("Osram Licht AG");
		listaEmpresas.add("Ossur HF");
		listaEmpresas.add("P/f Bakkafrost");
		listaEmpresas.add("PPG Industries Inc");
		listaEmpresas.add("PPL Corporation");
		listaEmpresas.add("Packaging Corp of America");
		listaEmpresas.add("Pandora A/S");
		listaEmpresas.add("Parker-Hannifin Corporation");
		listaEmpresas.add("Parkland Fuel Corporation");
		listaEmpresas.add("Pattern Energy Group");
		listaEmpresas.add("Paychex Inc");
		listaEmpresas.add("Pearson PLC");
		listaEmpresas.add("Pembina Pipeline Corp");
		listaEmpresas.add("Pennon Group");
		listaEmpresas.add("Pentair PLC");
		listaEmpresas.add("People�s United Financial Inc");
		listaEmpresas.add("PepsiCo Inc");
		listaEmpresas.add("Pernod Ricard SA");
		listaEmpresas.add("Perrigo Company PLC");
		listaEmpresas.add("Pfizer Inc");
		listaEmpresas.add("Philip Morris International Inc");
		listaEmpresas.add("Pilgrims Pride Corp");
		listaEmpresas.add("Pioneer Natural Resources Co");
		listaEmpresas.add("Playtech Plc");
		listaEmpresas.add("Polaris Industries Inc");
		listaEmpresas.add("Portland General Electric Co");
		listaEmpresas.add("Premium Brands Holdings Corporation");
		listaEmpresas.add("Primary Health Properties");
		listaEmpresas.add("Primerica Inc");
		listaEmpresas.add("Principal Financial Group Inc");
		listaEmpresas.add("Procter & Gamble Company");
		listaEmpresas.add("Prologis Inc");
		listaEmpresas.add("Prosegur, Cia. De Seguridad S.A.");
		listaEmpresas.add("Prosperity Bancshares Inc");
		listaEmpresas.add("Prudential Financial Inc");
		listaEmpresas.add("Prudential PLC Cap Sec");
		listaEmpresas.add("Prysmian SpA");
		listaEmpresas.add("Publicis Groupe SA");
		listaEmpresas.add("Quaker Chemical Corporation");
		listaEmpresas.add("Qualcomm Incorporated");
		listaEmpresas.add("RLI Corp");
		listaEmpresas.add("RPC Group PLC");
		listaEmpresas.add("RPM International Inc");
		listaEmpresas.add("RWE AG ST O.N.");
		listaEmpresas.add("RWS Holdings PLC");
		listaEmpresas.add("Randstad NV");
		listaEmpresas.add("Raytheon Company");
		listaEmpresas.add("Realty Income Corp");
		listaEmpresas.add("Reckitt Benckiser Group PLC");
		listaEmpresas.add("Recordati");
		listaEmpresas.add("Red El�ctrica de Espa�a S.A.U");
		listaEmpresas.add("Regal Beloit Corporation");
		listaEmpresas.add("Reinsurance Group of America");
		listaEmpresas.add("Remy Cointreau");
		listaEmpresas.add("Renaissancere Holdings Ltd");
		listaEmpresas.add("Renault SA");
		listaEmpresas.add("Repsol S.A.");
		listaEmpresas.add("Republic Services Inc");
		listaEmpresas.add("Restaurant Brands International Inc");
		listaEmpresas.add("Retail Opportunity Investments");
		listaEmpresas.add("Richelieu Hardware Ltd.");
		listaEmpresas.add("Rightmove PLC");
		listaEmpresas.add("Rio Tinto PLC");
		listaEmpresas.add("Ritchie Bros Auctioneers");
		listaEmpresas.add("Robert Half International Inc");
		listaEmpresas.add("Roche Holding AG");
		listaEmpresas.add("Rockwell Automation Inc");
		listaEmpresas.add("Rockwool International B");
		listaEmpresas.add("Rollins Inc");
		listaEmpresas.add("Rolls-Royce Holdings PLC");
		listaEmpresas.add("Roper Technologies Inc");
		listaEmpresas.add("Ross Stores Inc");
		listaEmpresas.add("Rotork PLC");
		listaEmpresas.add("Royal Bank of Canada");
		listaEmpresas.add("Royal DSM NV");
		listaEmpresas.add("Royal Dutch Shell PLC Class A");
		listaEmpresas.add("Royal Gold Inc");
		listaEmpresas.add("Royal Mail PLC");
		listaEmpresas.add("Rubis SCA");
		listaEmpresas.add("Ryder System Inc");
		listaEmpresas.add("S&P Global Inc");
		listaEmpresas.add("SAP SE");
		listaEmpresas.add("SCOR SE");
		listaEmpresas.add("SEB SA");
		listaEmpresas.add("SEI Investments Company");
		listaEmpresas.add("SGS SA");
		listaEmpresas.add("SJW Corporation");
		listaEmpresas.add("SSE PLC");
		listaEmpresas.add("STERIS plc");
		listaEmpresas.add("Sacyr SA");
		listaEmpresas.add("Safran SA");
		listaEmpresas.add("Sage Group PLC");
		listaEmpresas.add("Sage Therapeutic");
		listaEmpresas.add("Sampo Oyj A");
		listaEmpresas.add("Samsonite International SA");
		listaEmpresas.add("Sanofi ADR");
		listaEmpresas.add("Sanofi SA");
		listaEmpresas.add("Saputo Inc");
		listaEmpresas.add("Saracen Mineral Holdings Ltd");
		listaEmpresas.add("Schlumberger NV");
		listaEmpresas.add("Schneider Electric SE");
		listaEmpresas.add("Schroders PLC");
		listaEmpresas.add("Scotts Miracle-Gro Company");
		listaEmpresas.add("Sempra Energy");
		listaEmpresas.add("Senior Housing Properties Trust");
		listaEmpresas.add("Sensient Technologies Corporation");
		listaEmpresas.add("Shenzhen Expressway Co Ltd");
		listaEmpresas.add("Shenzhen Expressway Co Ltd");
		listaEmpresas.add("Sherwin-Williams Co");
		listaEmpresas.add("Siemens AG Class N");
		listaEmpresas.add("Siemens Gamesa Renewable Energy S.A.");
		listaEmpresas.add("Silgan Holdings Inc");
		listaEmpresas.add("Simcorp A/S");
		listaEmpresas.add("Simon Property Group Inc");
		listaEmpresas.add("Skandinaviska Enskilda Banken AB A");
		listaEmpresas.add("Smith & Nephew SNATS Inc");
		listaEmpresas.add("Smith AO Corporation");
		listaEmpresas.add("Societe Generale SA");
		listaEmpresas.add("Sodexo SA");
		listaEmpresas.add("Sofina");
		listaEmpresas.add("Software AG");
		listaEmpresas.add("Solvay SA");
		listaEmpresas.add("Sonoco Products Company");
		listaEmpresas.add("South Jersey Industries Inc");
		listaEmpresas.add("Southern Company");
		listaEmpresas.add("Southside Bancshares Inc");
		listaEmpresas.add("Southwest Gas Holdings Inc");
		listaEmpresas.add("Spectris PLC");
		listaEmpresas.add("Spirax-Sarco Engineering PLC");
		listaEmpresas.add("Spire Inc");
		listaEmpresas.add("Spirit Aerosystems Holdings Inc");
		listaEmpresas.add("Sprint Corp");
		listaEmpresas.add("Sprouts Farmers Market Inc");
		listaEmpresas.add("St. James�s Place PLC");
		listaEmpresas.add("Standard Life Aberdeen PLC");
		listaEmpresas.add("Standard Motor Products Inc");
		listaEmpresas.add("Stanley Black & Decker Inc");
		listaEmpresas.add("Stantec Inc");
		listaEmpresas.add("Starbucks Corporation");
		listaEmpresas.add("Stella-Jones Inc.");
		listaEmpresas.add("Stepan Company");
		listaEmpresas.add("Stericycle Inc");
		listaEmpresas.add("Stora Enso Oyj R");
		listaEmpresas.add("Store Capital Corp");
		listaEmpresas.add("Stryker Corporation");
		listaEmpresas.add("Suez SA");
		listaEmpresas.add("Suncor Energy Inc");
		listaEmpresas.add("Swiss Life Holding AG");
		listaEmpresas.add("Swiss Prime Site AG");
		listaEmpresas.add("Swisscom AG");
		listaEmpresas.add("Sysco Corporation");
		listaEmpresas.add("T. Rowe Price Group Inc");
		listaEmpresas.add("TC Energy Corp");
		listaEmpresas.add("TFI International Inc");
		listaEmpresas.add("TJX Companies Inc");
		listaEmpresas.add("TP ICAP PLC");
		listaEmpresas.add("Taiwan Semiconductor Manufacturing");
		listaEmpresas.add("Tanger Factory Outlet Centers Inc");
		listaEmpresas.add("Target Corporation");
		listaEmpresas.add("Tate & Lyle PLC");
		listaEmpresas.add("Taubman Centers Inc");
		listaEmpresas.add("Telecom Italia SPA");
		listaEmpresas.add("Telef�nica S.A.");
		listaEmpresas.add("Teleperformance SE");
		listaEmpresas.add("Telephone and Data Systems Inc");
		listaEmpresas.add("Telus Corp");
		listaEmpresas.add("Tennant Company");
		listaEmpresas.add("Terna Rete Elettrica Nazionale SpA");
		listaEmpresas.add("Texas Instruments Incorporated");
		listaEmpresas.add("Texas Pacific Land Trust");
		listaEmpresas.add("The Ensign Group Inc");
		listaEmpresas.add("The Hanover Insurance Group Inc");
		listaEmpresas.add("The Travelers Companies Inc");
		listaEmpresas.add("The Wendy�s Co");
		listaEmpresas.add("Thomson Reuters Corp");
		listaEmpresas.add("Tiffany & Co");
		listaEmpresas.add("Tompkins Financial Corporation");
		listaEmpresas.add("Tootsie Roll Industries Inc");
		listaEmpresas.add("Toro Co");
		listaEmpresas.add("Toromont Industries Ltd.");
		listaEmpresas.add("Toronto Dominion Bank");
		listaEmpresas.add("Total SA");
		listaEmpresas.add("Tractor Supply Company");
		listaEmpresas.add("TransAlta Renewables Inc.");
		listaEmpresas.add("Transcontinental Inc");
		listaEmpresas.add("Travis Perkins PLC");
		listaEmpresas.add("Tyson Foods Inc");
		listaEmpresas.add("T�cnicas Reunidas S.A.");
		listaEmpresas.add("UBS Group AG");
		listaEmpresas.add("UGI Corporation");
		listaEmpresas.add("UMB Financial Corporation");
		listaEmpresas.add("UPM-Kymmene Oyj");
		listaEmpresas.add("US Bancorp");
		listaEmpresas.add("Ultra Electronics Holdings PLC");
		listaEmpresas.add("Umicore SA");
		listaEmpresas.add("Under Armour Inc A");
		listaEmpresas.add("Unilever NV");
		listaEmpresas.add("Unilever PLC");
		listaEmpresas.add("Union Pacific Corporation");
		listaEmpresas.add("UnipolSai Assicurazioni SpA");
		listaEmpresas.add("United Airlines Holdings Inc");
		listaEmpresas.add("United Bankshares Inc");
		listaEmpresas.add("United Parcel Service Inc");
		listaEmpresas.add("United Technologies Corporation");
		listaEmpresas.add("United Utilities Group PLC");
		listaEmpresas.add("UnitedHealth Group Incorporated");
		listaEmpresas.add("Universal Corporation");
		listaEmpresas.add("Universal Health Realty Income Trust");
		listaEmpresas.add("Universal Health Services Inc");
		listaEmpresas.add("Unum Group");
		listaEmpresas.add("VF Corporation");
		listaEmpresas.add("Vail Resorts Inc");
		listaEmpresas.add("Valeo SA");
		listaEmpresas.add("Valero Energy Corporation");
		listaEmpresas.add("Vector Group Ltd");
		listaEmpresas.add("Ventas Inc");
		listaEmpresas.add("Veolia Environnement VE SA");
		listaEmpresas.add("Vereit Inc");
		listaEmpresas.add("VeriSign Inc");
		listaEmpresas.add("Verizon Communications Inc");
		listaEmpresas.add("Victrex");
		listaEmpresas.add("Vidrala S.A.");
		listaEmpresas.add("Vinci SA");
		listaEmpresas.add("Visa Inc Class A");
		listaEmpresas.add("Viscofan S.A.");
		listaEmpresas.add("Vodafone Group PLC");
		listaEmpresas.add("Vonovia SE");
		listaEmpresas.add("W P Carey Inc");
		listaEmpresas.add("W. R. Berkley Corp");
		listaEmpresas.add("WD-40 Company");
		listaEmpresas.add("WEC Energy Group Inc");
		listaEmpresas.add("WESCO International Inc");
		listaEmpresas.add("WFD Unibail Rodamco NV");
		listaEmpresas.add("WH Smith PLC");
		listaEmpresas.add("WPP PLC");
		listaEmpresas.add("WW Grainger Inc");
		listaEmpresas.add("Walgreens Boots Alliance Inc");
		listaEmpresas.add("Walmart Inc");
		listaEmpresas.add("Walt Disney Company");
		listaEmpresas.add("Waste Connections Inc");
		listaEmpresas.add("Waste Management Inc");
		listaEmpresas.add("Weir Group PLC");
		listaEmpresas.add("Wells Fargo & Company");
		listaEmpresas.add("Welltower Inc");
		listaEmpresas.add("Wendel");
		listaEmpresas.add("West Pharmaceutical Services Inc");
		listaEmpresas.add("WestRock Co");
		listaEmpresas.add("Westamerica Bancorporation");
		listaEmpresas.add("Westlake Chemical Corporation");
		listaEmpresas.add("Wheaton Precious Metals Corp");
		listaEmpresas.add("Whitbread PLC");
		listaEmpresas.add("Williams-Sonoma Inc");
		listaEmpresas.add("Wirecard AG");
		listaEmpresas.add("Wolters Kluwer");
		listaEmpresas.add("Wyndham Destinations Inc");
		listaEmpresas.add("Xcel Energy Inc");
		listaEmpresas.add("Xilinx Inc");
		listaEmpresas.add("Zardoya Otis S.A.");
		listaEmpresas.add("Zhejiang Expressway Co Ltd");
		LISTA_EMPRESAS_GROUP.add(new URLGroup("EMPRESAS", listaEmpresas));
	}

	/**
	 * 
	 */
	private final static Integer MAX_ERROR_RETRY = 2;

	/**
	 * 
	 */
	private final static String URL_INICIAL = "https://es.finance.yahoo.com";

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
		System.setProperty(WebDriverBase.WEB_DRIVER_PROPERTY, WebDriverBase.WEB_DRIVER_EXE);
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
					nombreEmpresa = listaEmpresas.get(empresaIdx);

					LOGGER.info("Buscando empresa [" + nombreEmpresa + "]");
					if (errorRetry > 0)
					{
						LOGGER.info("Reintento [" + errorRetry + "]");
					}

					LOGGER.info("Cargando URL inicial");
					driver.get(URL_INICIAL);
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("yfin-usr-qry")));

					LOGGER.info("Refrescando pantalla");
					driver.navigate().refresh();
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("yfin-usr-qry")));
					WebElement textBoxBusqueda = driver.findElement(By.id("yfin-usr-qry"));

					textBoxBusqueda.click();
					textBoxBusqueda.clear();
					textBoxBusqueda.sendKeys(nombreEmpresa);

					LOGGER.info("Esperamos 1000 milisegundos");
					Thread.sleep(1000);

					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("ul")));
					List<WebElement> posiblesListas = driver.findElements(By.tagName("ul"));
					for (WebElement posibleLista : posiblesListas)
					{
						if (posibleLista.getAttribute("innerHTML").indexOf("Equity") != -1)
						{
							posibleLista.findElements(By.tagName("li")).get(0).click();
							break;
						}
					}

					// WebDriverBase.clickElementById(driver, driver, "search-button");

					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("quote-header-info")));
					WebElement infoCabecera = driver.findElement(By.id("quote-header-info"));
					String resultNombreEmpresa = infoCabecera.findElements(By.tagName("h1")).get(0).getAttribute("innerHTML").trim();
					String resultHrefElemento = driver.getCurrentUrl();
					WebElement tablaDatos = driver.findElement(By.id("quote-summary"));
					List<WebElement> listaFilasTabla = tablaDatos.findElements(By.tagName("tr"));
					String resultCAP = null;
					String resultCAP_UM = null;
					String resultRPD = null;
					for (WebElement filaTabla : listaFilasTabla)
					{
						if (filaTabla.getAttribute("innerHTML").indexOf("Capitalizaci�n de mercado") != -1)
						{
							List<WebElement> elementosFila = filaTabla.findElements(By.tagName("span"));
							String capitalizacionStr = elementosFila.get(1).getAttribute("innerHTML").trim();
							capitalizacionStr = capitalizacionStr.replaceAll("\\.", "");
							resultCAP = capitalizacionStr.substring(0, capitalizacionStr.length() - 1);
							resultCAP_UM = capitalizacionStr.substring(capitalizacionStr.length() - 1, capitalizacionStr.length());
						}
						else if (filaTabla.getAttribute("innerHTML").indexOf("Previsi�n de rentabilidad y dividendo") != -1)
						{
							List<WebElement> elementosFila = filaTabla.findElements(By.tagName("td"));
							resultRPD = elementosFila.get(1).getAttribute("innerHTML").trim();
							resultRPD = resultRPD.substring(resultRPD.indexOf("(") + 1, resultRPD.indexOf(")") - 1);
							resultRPD = resultRPD.replaceAll("\\.", "");
						}
					}

					LOGGER.info("Empresa [" + resultCAP + "] [" + resultCAP_UM + "] [" + resultRPD + "] [" + resultNombreEmpresa + "] [" + resultHrefElemento + "]");
					empresaIdx++;
					errorRetry = 0;
				}
				catch (Exception e)
				{
					LOGGER.error("Se ha producido un error", e);
					errorRetry++;
					if (errorRetry >= MAX_ERROR_RETRY)
					{
						LOGGER.info("Empresa [-] [-] [-] [" + nombreEmpresa + "] [-]");
						empresaIdx++;
						errorRetry = 0;
					}
				}
			}
		}
	}

}
