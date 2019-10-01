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
public class DriverControllerSCR extends DriverControllerBase
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DriverControllerSCR.class);

	/**
	 * URL Groups
	 */
	private final static List<URLGroup> LISTA_URL_GROUP = new ArrayList<URLGroup>();
	static
	{
		// --
		// -- Alemania
		// --
		List<String> listaURL_01 = new ArrayList<String>();
		listaURL_01.add("https://es.investing.com/stock-screener/?sp=country::17|sector::a|industry::a|equityType::ORD|exchange::4|eq_market_cap::2000000000,392150000000|yield_us::3,252|a52_week_high_diff::-10,1.4210854715202e-14%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Alemania", listaURL_01));
		// --
		// -- China
		// --
		List<String> listaURL_02 = new ArrayList<String>();
		listaURL_02.add("https://es.investing.com/stock-screener/?sp=country::37|sector::a|industry::a|equityType::ORD|eq_market_cap::2000000000,1969180000000|yield_us::3,26.02|a52_week_high_diff::-10,0%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("China", listaURL_02));
		// --
		// -- Bélgica
		// --
		// List<String> listaURL_03 = new ArrayList<String>();
		// listaURL_03.add("https://es.investing.com/stock-screener/?sp=country::34|sector::a|industry::a|equityType::ORD|exchange::14|eq_market_cap::1000000000,158650000000|eq_one_year_return::0,301.88|ytd::0,208.64|a52_week_high_diff::-6,0%3Ceq_market_cap;1");
		// LISTA_URL_GROUP.add(new URLGroup("Bélgica", listaURL_03));
		// --
		// -- España
		// --
		List<String> listaURL_04 = new ArrayList<String>();
		listaURL_04.add("https://es.investing.com/stock-screener/?sp=country::26|sector::a|industry::a|equityType::ORD|exchange::11|eq_market_cap::2000000000,79000000000|yield_us::3,610.91|a52_week_high_diff::-10,0%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("España", listaURL_04));
		// --
		// -- USA - Nasdaq
		// --
		List<String> listaURL_05 = new ArrayList<String>();
		listaURL_05.add("https://es.investing.com/stock-screener/?sp=country::5|sector::a|industry::a|equityType::ORD|exchange::2|eq_market_cap::2000000000,807650000000|yield_us::3,2887.5|a52_week_high_diff::-10,1.4210854715202e-14%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("USA - Nasdaq", listaURL_05));
		// --
		// -- USA - Nueva York
		// --
		List<String> listaURL_06 = new ArrayList<String>();
		listaURL_06.add("https://es.investing.com/stock-screener/?sp=country::5|sector::a|industry::a|equityType::ORD|exchange::1|eq_market_cap::2000000000,807650000000|yield_us::3,2887.5|a52_week_high_diff::-10,1.4210854715202e-14%3Ceq_market_cap;1");
		listaURL_06.add("https://es.investing.com/stock-screener/?sp=country::5|sector::a|industry::a|equityType::ORD|exchange::1|eq_market_cap::2000000000,807650000000|yield_us::3,2887.5|a52_week_high_diff::-10,1.4210854715202e-14%3Ceq_market_cap;2");
		LISTA_URL_GROUP.add(new URLGroup("USA - Nueva York", listaURL_06));
		// --
		// -- USA
		// --
		// List<String> listaURL_06 = new ArrayList<String>();
		// listaURL_06.add("https://es.investing.com/stock-screener/?sp=country::5|sector::a|industry::a|equityType::a|exchange::a|eq_pe_ratio::0,100|yield_us::4,420|eq_market_cap::15000000000,819130000000%3Ceq_market_cap;1");
		// listaURL_06.add("https://es.investing.com/stock-screener/?sp=country::5|sector::a|industry::a|equityType::a|exchange::a|eq_pe_ratio::0,100|yield_us::4,420|eq_market_cap::15000000000,819130000000%3Ceq_market_cap;2");
		// listaURL_06.add("https://es.investing.com/stock-screener/?sp=country::5|sector::a|industry::a|equityType::a|exchange::a|eq_pe_ratio::0,100|yield_us::4,420|eq_market_cap::15000000000,819130000000%3Ceq_market_cap;3");
		// LISTA_URL_GROUP.add(new URLGroup("USA", listaURL_06));
		// --
		// -- Eurozona
		// --
		List<String> listaURL_07 = new ArrayList<String>();
		listaURL_07.add("https://es.investing.com/stock-screener/?sp=country::72|sector::a|industry::a|equityType::ORD|eq_market_cap::2000000000,581800000000|yield_us::3,55.7|a52_week_high_diff::-10,0%3Ceq_market_cap;1");
		listaURL_07.add("https://es.investing.com/stock-screener/?sp=country::72|sector::a|industry::a|equityType::ORD|eq_market_cap::2000000000,581800000000|yield_us::3,55.7|a52_week_high_diff::-10,0%3Ceq_market_cap;2");
		LISTA_URL_GROUP.add(new URLGroup("Eurozona", listaURL_07));
		// --
		// -- Francia
		// --
		List<String> listaURL_08 = new ArrayList<String>();
		listaURL_08.add("https://es.investing.com/stock-screener/?sp=country::22|sector::a|industry::a|equityType::ORD|eq_market_cap::2000000000,216050000000|yield_us::3,39.24|a52_week_high_diff::-10,0%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Francia", listaURL_08));
		// --
		// -- Holanda
		// --
		// List<String> listaURL_09 = new ArrayList<String>();
		// listaURL_09.add("https://es.investing.com/stock-screener/?sp=country::21|sector::a|industry::a|equityType::a|eq_pe_ratio::2.47,100|yield_us::4,909.09|eq_market_cap::2000000000,216900000000%3Ceq_market_cap;1");
		// LISTA_URL_GROUP.add(new URLGroup("Holanda", listaURL_09));
		// --
		// -- Reino Unido
		// --
		List<String> listaURL_10 = new ArrayList<String>();
		listaURL_10.add("https://es.investing.com/stock-screener/?sp=country::4|sector::a|industry::a|equityType::ORD|eq_market_cap::2000000000,651370000000|yield_us::3,252|a52_week_high_diff::-10,0%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Reino Unido", listaURL_10));
		// --
		// -- Italia
		// --
		List<String> listaURL_11 = new ArrayList<String>();
		listaURL_11.add("https://es.investing.com/stock-screener/?sp=country::10|sector::a|industry::a|equityType::ORD|eq_market_cap::2000000000,145500000000|yield_us::3,76.92|a52_week_high_diff::-10,0%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Italia", listaURL_11));
		// --
		// -- Japón
		// --
		List<String> listaURL_12 = new ArrayList<String>();
		listaURL_12.add("https://es.investing.com/stock-screener/?sp=country::35|sector::a|industry::a|equityType::ORD|eq_market_cap::2000000000,21454210000000|yield_us::3,14.29|a52_week_high_diff::-10,0%3Ceq_market_cap;1");
		LISTA_URL_GROUP.add(new URLGroup("Japón", listaURL_12));
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
					String hrefScreener = listaURL.get(urlsIdx);

					LOGGER.info("Cargando URL [" + hrefScreener + "]");
					driver.get(hrefScreener);
					String tableId = "resultsTable";

					LOGGER.info("Recuperando tabla de elementos [" + tableId + "]");
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id(tableId)));
					WebElement tablaElementos = driver.findElement(By.id(tableId));
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(tablaElementos, By.tagName("tbody")));
					WebElement tableBody = tablaElementos.findElement(By.tagName("tbody"));
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(tableBody, By.tagName("a")));
					List<WebElement> listaLinks = tableBody.findElements(By.tagName("a"));

					LOGGER.info("Guardando enlaces de elementos");
					List<String> listaHrefs = new ArrayList<String>();
					for (WebElement link : listaLinks)
					{
						listaHrefs.add(link.getAttribute("href"));
					}

					LOGGER.info("Procesando elementos");
					int hrefsIdx = 0;
					while (hrefsIdx < listaHrefs.size())
					{
						try
						{
							String hrefElemento = listaHrefs.get(hrefsIdx);
							procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_MENSUAL, TF_MENSUAL, null);
							// procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_SEMANAL, TF_SEMANAL);
							// procesarElemento(driver, hrefElemento, DOWNLOAD_PATH + "\\" + downloadFolder + "\\" + TF_DIARIO, TF_DIARIO);
							hrefsIdx++;
						}
						catch (Exception e)
						{
							LOGGER.error("Se ha producido un error", e);
							gestionError(driver);
						}
					}
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
