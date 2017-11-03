/**
 * 
 */
package jsm.mdata.selenium.investing.outlier;

import java.util.ArrayList;
import java.util.List;

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
	 * 
	 * URLs
	 * 
	 * select mercado, bolsa, indice, ticker, count(1) as num_dias, min(fecha) as fecha_ini, max(fecha) fecha_fin from public.mercados_investing group by mercado, bolsa, indice, ticker order by fecha_ini desc, mercado, bolsa, indice, ticker;
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
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/etfs-all-commodities-go-ucits-de?cid=1031770"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/etfs-longer-dated-allcomm-exagri-de"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/etfs-longer-dated-allcomm-exagri-de?cid=1031773"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/ishares-msci-europe-value-factor?cid=956308"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/deka-mdax?cid=1052314"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/hsbc-msci-world"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/ishares-msci-asia-ex-japan-minimum?cid=1031774"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/etfs-all-commodities-go-ucits-de?cid=1031772"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/etfs-all-commodities-go-ucits-de"));
		LISTA_URLS_OUTLIERS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/etfs-longer-dated-allcomm-exagri-de?cid=1031771"));
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
	}

}
