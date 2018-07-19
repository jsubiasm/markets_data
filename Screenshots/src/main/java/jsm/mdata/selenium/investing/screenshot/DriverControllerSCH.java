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
public class DriverControllerSCH extends DriverControllerBase
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DriverControllerSCH.class);

	/**
	 * 
	 */
	private final static Integer MAX_ERROR_RETRY = 10;

	/**
	 * 
	 */
	private final static String URL_INICIAL = "https://es.investing.com";

	/**
	 * URL Groups
	 */
	private final static List<URLGroup> LISTA_URL_GROUP = new ArrayList<URLGroup>();
	static
	{
		// --
		// -- ETFs USA
		// --
		List<String> listaURL_01 = new ArrayList<String>();
		listaURL_01.add("ACIM");
		listaURL_01.add("ACWF");
		listaURL_01.add("ACWI");
		listaURL_01.add("ACWV");
		listaURL_01.add("BBP");
		listaURL_01.add("BFOR");
		listaURL_01.add("CDC");
		listaURL_01.add("CDL");
		listaURL_01.add("CFA");
		listaURL_01.add("CFO");
		listaURL_01.add("CIBR");
		listaURL_01.add("CRBN");
		listaURL_01.add("CSA");
		listaURL_01.add("CSB");
		listaURL_01.add("CSD");
		listaURL_01.add("CSF");
		listaURL_01.add("CSM");
		listaURL_01.add("CUT");
		listaURL_01.add("CVY");
		listaURL_01.add("CZA");
		listaURL_01.add("DBAP");
		listaURL_01.add("DBEZ");
		listaURL_01.add("DBUK");
		listaURL_01.add("DEF");
		listaURL_01.add("DES");
		listaURL_01.add("DGRO");
		listaURL_01.add("DGRS");
		listaURL_01.add("DGRW");
		listaURL_01.add("DHVW");
		listaURL_01.add("DLN");
		listaURL_01.add("DON");
		listaURL_01.add("DSI");
		listaURL_01.add("DTD");
		listaURL_01.add("DTN");
		listaURL_01.add("DVP");
		listaURL_01.add("DVY");
		listaURL_01.add("DWAQ");
		listaURL_01.add("DWAS");
		listaURL_01.add("EDEN");
		listaURL_01.add("EES");
		listaURL_01.add("EFAV");
		listaURL_01.add("EFG");
		listaURL_01.add("EFNL");
		listaURL_01.add("EIRL");
		listaURL_01.add("ENOR");
		listaURL_01.add("ENZL");
		listaURL_01.add("EPS");
		listaURL_01.add("EQAL");
		listaURL_01.add("EQL");
		listaURL_01.add("EQLT");
		listaURL_01.add("EQWL");
		listaURL_01.add("EQWM");
		listaURL_01.add("EQWS");
		listaURL_01.add("ERUS");
		listaURL_01.add("EUSA");
		listaURL_01.add("EVX");
		listaURL_01.add("EWQ");
		listaURL_01.add("EXT");
		listaURL_01.add("EZM");
		listaURL_01.add("FAB");
		listaURL_01.add("FAD");
		listaURL_01.add("FBT");
		listaURL_01.add("FDIS");
		listaURL_01.add("FDM");
		listaURL_01.add("FDN");
		listaURL_01.add("FEP");
		listaURL_01.add("FEX");
		listaURL_01.add("FFTY");
		listaURL_01.add("FHLC");
		listaURL_01.add("FILL");
		listaURL_01.add("FIW");
		listaURL_01.add("FMK");
		listaURL_01.add("FNCL");
		listaURL_01.add("FNDA");
		listaURL_01.add("FNDB");
		listaURL_01.add("FNDX");
		listaURL_01.add("FNK");
		listaURL_01.add("FNX");
		listaURL_01.add("FNY");
		listaURL_01.add("FPX");
		listaURL_01.add("FPXI");
		listaURL_01.add("FTC");
		listaURL_01.add("FTCS");
		listaURL_01.add("FTEC");
		listaURL_01.add("FUTY");
		listaURL_01.add("FV");
		listaURL_01.add("FVD");
		listaURL_01.add("FXD");
		listaURL_01.add("FXL");
		listaURL_01.add("FXO");
		listaURL_01.add("FYC");
		listaURL_01.add("FYT");
		listaURL_01.add("FYX");
		listaURL_01.add("GNR");
		listaURL_01.add("GUNR");
		listaURL_01.add("GURU");
		listaURL_01.add("HACK");
		listaURL_01.add("HEWC");
		listaURL_01.add("HEWU");
		listaURL_01.add("HSCZ");
		listaURL_01.add("IAI");
		listaURL_01.add("IAT");
		listaURL_01.add("IEUS");
		listaURL_01.add("IGM");
		listaURL_01.add("IGN");
		listaURL_01.add("IGV");
		listaURL_01.add("IHDG");
		listaURL_01.add("IHF");
		listaURL_01.add("IHI");
		listaURL_01.add("IJH");
		listaURL_01.add("IJJ");
		listaURL_01.add("IJK");
		listaURL_01.add("IJR");
		listaURL_01.add("IJS");
		listaURL_01.add("IJT");
		listaURL_01.add("IOO");
		listaURL_01.add("IPAY");
		listaURL_01.add("IPO");
		listaURL_01.add("IQLT");
		listaURL_01.add("ITA");
		listaURL_01.add("ITOT");
		listaURL_01.add("IUSG");
		listaURL_01.add("IUSV");
		listaURL_01.add("IVOG");
		listaURL_01.add("IVOO");
		listaURL_01.add("IVOV");
		listaURL_01.add("IVV");
		listaURL_01.add("IVW");
		listaURL_01.add("IWB");
		listaURL_01.add("IWC");
		listaURL_01.add("IWD");
		listaURL_01.add("IWF");
		listaURL_01.add("IWL");
		listaURL_01.add("IWM");
		listaURL_01.add("IWN");
		listaURL_01.add("IWO");
		listaURL_01.add("IWP");
		listaURL_01.add("IWR");
		listaURL_01.add("IWS");
		listaURL_01.add("IWV");
		listaURL_01.add("IWY");
		listaURL_01.add("IXC");
		listaURL_01.add("IXN");
		listaURL_01.add("IYC");
		listaURL_01.add("IYF");
		listaURL_01.add("IYG");
		listaURL_01.add("IYH");
		listaURL_01.add("IYJ");
		listaURL_01.add("IYW");
		listaURL_01.add("IYY");
		listaURL_01.add("JKD");
		listaURL_01.add("JKE");
		listaURL_01.add("JKG");
		listaURL_01.add("JKH");
		listaURL_01.add("JKI");
		listaURL_01.add("JKJ");
		listaURL_01.add("JKK");
		listaURL_01.add("JKL");
		listaURL_01.add("KBE");
		listaURL_01.add("KBWB");
		listaURL_01.add("KBWP");
		listaURL_01.add("KBWR");
		listaURL_01.add("KCE");
		listaURL_01.add("KLDW");
		listaURL_01.add("KNOW");
		listaURL_01.add("KRE");
		listaURL_01.add("KWEB");
		listaURL_01.add("LGLV");
		listaURL_01.add("LOWC");
		listaURL_01.add("LRGF");
		listaURL_01.add("MDYG");
		listaURL_01.add("MDYV");
		listaURL_01.add("MGC");
		listaURL_01.add("MGK");
		listaURL_01.add("MGV");
		listaURL_01.add("MMTM");
		listaURL_01.add("MOAT");
		listaURL_01.add("MOO");
		listaURL_01.add("MTUM");
		listaURL_01.add("NFO");
		listaURL_01.add("NLR");
		listaURL_01.add("NOBL");
		listaURL_01.add("NORW");
		listaURL_01.add("OEF");
		listaURL_01.add("ONEQ");
		listaURL_01.add("PBS");
		listaURL_01.add("PDP");
		listaURL_01.add("PEJ");
		listaURL_01.add("PEY");
		listaURL_01.add("PEZ");
		listaURL_01.add("PFI");
		listaURL_01.add("PFM");
		listaURL_01.add("PGAL");
		listaURL_01.add("PGJ");
		listaURL_01.add("PHO");
		listaURL_01.add("PIZ");
		listaURL_01.add("PNQI");
		listaURL_01.add("PPA");
		listaURL_01.add("PRF");
		listaURL_01.add("PRFZ");
		listaURL_01.add("PRN");
		listaURL_01.add("PSCC");
		listaURL_01.add("PSCD");
		listaURL_01.add("PSCF");
		listaURL_01.add("PSCH");
		listaURL_01.add("PSCI");
		listaURL_01.add("PSCM");
		listaURL_01.add("PSCT");
		listaURL_01.add("PSCU");
		listaURL_01.add("PSI");
		listaURL_01.add("PSJ");
		listaURL_01.add("PSL");
		listaURL_01.add("PSP");
		listaURL_01.add("PTF");
		listaURL_01.add("PTH");
		listaURL_01.add("PWB");
		listaURL_01.add("PWC");
		listaURL_01.add("PXLG");
		listaURL_01.add("PXLV");
		listaURL_01.add("PXMG");
		listaURL_01.add("PXMV");
		listaURL_01.add("PXQ");
		listaURL_01.add("PXSG");
		listaURL_01.add("PXSV");
		listaURL_01.add("PZD");
		listaURL_01.add("PZI");
		listaURL_01.add("QABA");
		listaURL_01.add("QDEF");
		listaURL_01.add("QDF");
		listaURL_01.add("QDYN");
		listaURL_01.add("QQEW");
		listaURL_01.add("QQQE");
		listaURL_01.add("QQXT");
		listaURL_01.add("QTEC");
		listaURL_01.add("QUAL");
		listaURL_01.add("QUS");
		listaURL_01.add("QVAL");
		listaURL_01.add("QVM");
		listaURL_01.add("QWLD");
		listaURL_01.add("QYLD");
		listaURL_01.add("RCD");
		listaURL_01.add("RDIV");
		listaURL_01.add("RDVY");
		listaURL_01.add("REGL");
		listaURL_01.add("RFG");
		listaURL_01.add("RFV");
		listaURL_01.add("ROGS");
		listaURL_01.add("ROUS");
		listaURL_01.add("RPG");
		listaURL_01.add("RPV");
		listaURL_01.add("RSP");
		listaURL_01.add("RSX");
		listaURL_01.add("RTH");
		listaURL_01.add("RWJ");
		listaURL_01.add("RWK");
		listaURL_01.add("RWL");
		listaURL_01.add("RXI");
		listaURL_01.add("RYH");
		listaURL_01.add("RYJ");
		listaURL_01.add("RYT");
		listaURL_01.add("RZG");
		listaURL_01.add("RZV");
		listaURL_01.add("SCHA");
		listaURL_01.add("SCHB");
		listaURL_01.add("SCHD");
		listaURL_01.add("SCHG");
		listaURL_01.add("SCHM");
		listaURL_01.add("SCHV");
		listaURL_01.add("SCHX");
		listaURL_01.add("SCIU");
		listaURL_01.add("SDY");
		listaURL_01.add("SIZE");
		listaURL_01.add("SKYY");
		listaURL_01.add("SLX");
		listaURL_01.add("SLY");
		listaURL_01.add("SLYG");
		listaURL_01.add("SLYV");
		listaURL_01.add("SMDV");
		listaURL_01.add("SMH");
		listaURL_01.add("SMLF");
		listaURL_01.add("SMLV");
		listaURL_01.add("SOCL");
		listaURL_01.add("SOXX");
		listaURL_01.add("SPHB");
		listaURL_01.add("SPHQ");
		listaURL_01.add("SPLG");
		listaURL_01.add("SPLV");
		listaURL_01.add("SPMD");
		listaURL_01.add("SPSM");
		listaURL_01.add("SPTM");
		listaURL_01.add("SPUN");
		listaURL_01.add("SPYB");
		listaURL_01.add("SPYG");
		listaURL_01.add("SUSA");
		listaURL_01.add("TDIV");
		listaURL_01.add("TILT");
		listaURL_01.add("TOK");
		listaURL_01.add("TUSA");
		listaURL_01.add("URTH");
		listaURL_01.add("USMV");
		listaURL_01.add("VB");
		listaURL_01.add("VBK");
		listaURL_01.add("VBR");
		listaURL_01.add("VCR");
		listaURL_01.add("VFH");
		listaURL_01.add("VGT");
		listaURL_01.add("VHT");
		listaURL_01.add("VIG");
		listaURL_01.add("VIOG");
		listaURL_01.add("VIOO");
		listaURL_01.add("VIOV");
		listaURL_01.add("VLU");
		listaURL_01.add("VLUE");
		listaURL_01.add("VO");
		listaURL_01.add("VOE");
		listaURL_01.add("VONE");
		listaURL_01.add("VONG");
		listaURL_01.add("VONV");
		listaURL_01.add("VOO");
		listaURL_01.add("VOOG");
		listaURL_01.add("VOT");
		listaURL_01.add("VPU");
		listaURL_01.add("VT");
		listaURL_01.add("VTHR");
		listaURL_01.add("VTI");
		listaURL_01.add("VTV");
		listaURL_01.add("VTWG");
		listaURL_01.add("VTWO");
		listaURL_01.add("VTWV");
		listaURL_01.add("VUG");
		listaURL_01.add("VUSE");
		listaURL_01.add("VV");
		listaURL_01.add("VXF");
		listaURL_01.add("VYM");
		listaURL_01.add("WMCR");
		listaURL_01.add("WOOD");
		listaURL_01.add("XAR");
		listaURL_01.add("XHE");
		listaURL_01.add("XLG");
		listaURL_01.add("XLK");
		listaURL_01.add("XLV");
		listaURL_01.add("XLY");
		listaURL_01.add("XME");
		listaURL_01.add("XMLV");
		listaURL_01.add("XNTK");
		listaURL_01.add("XRLV");
		listaURL_01.add("XSD");
		listaURL_01.add("XSLV");
		listaURL_01.add("XSW");
		listaURL_01.add("XT");
		listaURL_01.add("XTL");
		LISTA_URL_GROUP.add(new URLGroup("ETFs_USA", listaURL_01));
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

		LOGGER.info("Iniciando proceso");
		for (URLGroup urlGroup : LISTA_URL_GROUP)
		{
			String downloadFolder = urlGroup.getDownloadFolder();
			LOGGER.info("Procesando grupo [" + downloadFolder + "]");
			List<String> listaURL = urlGroup.getListaURL();
			int urlsIdx = 0;
			int errorRetry = 0;
			while (urlsIdx < listaURL.size())
			{
				try
				{
					LOGGER.info("Cargando URL inicial");
					driver.get(URL_INICIAL);
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("disclaimer")));

					String ticker = listaURL.get(urlsIdx);
					LOGGER.info("Buscando ticker [" + ticker + "]");
					if (errorRetry > 0)
					{
						LOGGER.info("Reintento [" + errorRetry + "]");
					}
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("searchTextTop")));
					WebElement textBoxBusqueda = driver.findElement(By.id("searchTextTop"));
					textBoxBusqueda.clear();
					textBoxBusqueda.sendKeys(ticker);

					LOGGER.info("Filtrando resultado solo para ETFs");
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("search_box_topETFs")));
					WebElement filterETFs = driver.findElement(By.id("search_box_topETFs"));
					filterETFs.click();

					LOGGER.info("Seleccionando primer resultado");
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("searchRowIdtop_0")));
					WebElement primerETF = driver.findElement(By.id("searchRowIdtop_0"));
					primerETF.click();

					LOGGER.info("Recuperando URL");
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Gráfico")));
					String hrefElemento = driver.getCurrentUrl();
					if (hrefElemento.equalsIgnoreCase(URL_INICIAL))
					{
						throw new Exception("URL inadecuada");
					}

					LOGGER.info("Procesando URL [" + hrefElemento + "]");
					procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_MENSUAL, TF_MENSUAL);
					procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_SEMANAL, TF_SEMANAL);
					// procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_DIARIO, TF_DIARIO);
					urlsIdx++;
				}
				catch (Exception e)
				{
					LOGGER.error("Se ha producido un error", e);
					errorRetry++;
					if (errorRetry >= MAX_ERROR_RETRY)
					{
						urlsIdx++;
					}
				}
			}
		}

	}

}
