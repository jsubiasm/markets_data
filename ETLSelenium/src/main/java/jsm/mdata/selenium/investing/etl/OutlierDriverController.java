/**
 * 
 */
package jsm.mdata.selenium.investing.etl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * 
	 * URLs
	 * 
	 * delete from public.mercados_investing where ticker = 'xxxxxxxxxxxxxxxxxxxxxxxxx';
	 * 
	 * LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "xxxxxxxxxxxxxxxxxxxxxxxxx"));
	 * 
	 */
	private final static List<TipoURL> LISTA_URLS_OUTLIERS = new ArrayList<TipoURL>();
	static
	{
		// --
		// -- OUTLIERS
		// --
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/bnp-rici-enhanced-metals"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/bnp-rici-enhanced-indust-metals"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/coba--3x-mdaxf-daily-short"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/coba--4x-mdaxf-daily-short"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/coba-etc--3x-copper-daily-short"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/coba-etc--3x-wti-oil-daily-short"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/coba-etn--10x-bundf-daily-short"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/db-x-trackers-msci-bangladesh-imtr?cid=45810"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/db-xtrack-msci-emu-minvol?cid=1024388"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/db-xtrackers-ii-%E2%82%AC-sov.-euro-15-tr"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/dbxtrck-ii-eur-hiyld-corpbd-dr-1c"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/ubs-(irl)-msci-emu-cyclical-mi?cid=949271"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/boerse-stuttgart-secs-gold-ihs-2017"));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			//run();
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
	 * 
	 */
	/*private static void run() throws Exception
	{
		LOGGER.info("Iniciando driver");
		System.setProperty(DriverController.WEB_DRIVER_PROPERTY, DriverController.WEB_DRIVER_EXE);
		WebDriver driver = new ChromeDriver();

		LOGGER.info("Cargando URL inicial para introducir datos proxy");
		driver.get("https://es.investing.com/");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.className("disclaimer")));

		LOGGER.info("Iniciando proceso");
		int urlsIdx = 0;
		while (urlsIdx < LISTA_URLS_OUTLIERS.size())
		{
			try
			{
				TipoURL tipoUrl = LISTA_URLS_OUTLIERS.get(urlsIdx);
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
	}*/

}
