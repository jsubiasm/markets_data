/**
 * 
 */
package jsm.mdata.selenium.investing.outlier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsm.mdata.selenium.investing.DriverController;
import jsm.mdata.selenium.investing.TipoURL;

/**
 * @author Empleado
 *
 */
public class OutlierDriverController
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(OutlierDriverController.class);

	/**
	 * Rutas
	 */
	protected static final String DOWNLOAD_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\Selenium\\investing\\outlier";

	/**
	 * URLs
	 */
	private final static List<TipoURL> LISTA_URLS = new ArrayList<TipoURL>();
	static
	{
		// --
		// -- OUTLIERS
		// --
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/comstage-msci-europe-large-cap-trn"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/comstage-msci-europe-mid-cap-trn"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/comstage-stoxx-europe-600-uti.-nr"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/comstage-nikkei-225?cid=997961"));
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
	}

	/**
	 * @throws Exception
	 * 
	 */
	private static void run() throws Exception
	{
		LOGGER.info("Iniciando driver");
		System.setProperty(DriverController.WEB_DRIVER_PROPERTY, DriverController.WEB_DRIVER_EXE);
		WebDriver driver = new ChromeDriver();

		LOGGER.info("Cargando URL inicial para introducir datos proxy");
		driver.get("https://es.investing.com/");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.className("disclaimer")));

		LOGGER.info("Suprimiendo ficheros temporales antiguos");
		FileUtils.cleanDirectory(new File(DOWNLOAD_PATH));

		int urlsIdx = 0;
		while (urlsIdx < LISTA_URLS.size())
		{
			try
			{
				TipoURL tipoUrl = LISTA_URLS.get(urlsIdx);
				String hrefElemento = tipoUrl.getUrl();
				DriverController.procesarElemento(driver, tipoUrl, hrefElemento);
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
