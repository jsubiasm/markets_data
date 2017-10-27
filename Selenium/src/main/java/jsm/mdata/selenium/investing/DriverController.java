/**
 * 
 */
package jsm.mdata.selenium.investing;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	 * Formatos
	 */
	private static final String CSV_SEPARATOR = ";";
	private static final String CHARSET = "UTF-8";
	private static final String FILE_SEPARATOR = "#";

	/**
	 * Rutas
	 */
	protected static final String DOWNLOAD_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\Selenium\\investing\\download";

	/**
	 * URLs
	 */
	private final static List<TipoURL> LISTA_URLS = new ArrayList<TipoURL>();
	static
	{
		LISTA_URLS.add(new TipoURL(TipoURL.TIPO_ETF, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Lyxor"));
		LISTA_URLS.add(new TipoURL(TipoURL.TIPO_ETF, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=iShares"));
		LISTA_URLS.add(new TipoURL(TipoURL.TIPO_ETF, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Amundi"));
		LISTA_URLS.add(new TipoURL(TipoURL.TIPO_ETF, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=db%20X-trackers"));
		LISTA_URLS.add(new TipoURL(TipoURL.TIPO_ETF, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=ETFS"));
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
		System.setProperty("webdriver.chrome.driver", "C:\\_PELAYO\\Software\\Selenium\\drivers\\chromedriver.exe");
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

				LOGGER.info("Cargando URL [" + tipoUrl.getUrl() + "]");
				driver.get(tipoUrl.getUrl());
				String tableId = getTableIdByTipoURL(tipoUrl);

				LOGGER.info("Recuperando tabla de elementos [" + tableId + "]");
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id(tableId)));
				WebElement tablaElementos = driver.findElement(By.id(tableId));
				List<WebElement> listaLinks = tablaElementos.findElements(By.tagName("a"));

				LOGGER.info("Guardando enlaces de elementos");
				List<String> listaHrefs = new ArrayList<String>();
				for (WebElement link : listaLinks)
				{
					listaHrefs.add(link.getAttribute("href"));
				}

				int hrefsIdx = 0;
				while (hrefsIdx < listaHrefs.size())
				{
					try
					{
						String hrefElemento = listaHrefs.get(hrefsIdx);

						LOGGER.info("Cargando URL [" + hrefElemento + "]");
						driver.get(hrefElemento);

						LOGGER.info("Accediendo información histórica");
						new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Información histórica")));
						WebElement historicoLink = driver.findElement(By.linkText("Información histórica"));
						historicoLink.click();

						LOGGER.info("Actualizando fechas de descarga de datos");
						new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("picker")));
						((JavascriptExecutor) driver).executeScript("document.getElementById('picker').value='01/01/2000 - 01/01/2018'");
						new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("flatDatePickerCanvasHol")));
						WebElement fechasLink = driver.findElement(By.id("flatDatePickerCanvasHol"));
						fechasLink.click();
						new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("applyBtn")));
						WebElement aceptarBtn = driver.findElement(By.id("applyBtn"));
						aceptarBtn.click();

						LOGGER.info("Recuperando tabla de datos");
						new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("curr_table")));
						WebElement tablaDatos = driver.findElement(By.id("curr_table"));

						// LOGGER.info("Recuperando cabecera de datos");
						// List<WebElement> listaHeader = tablaDatos.findElements(By.tagName("thead"));
						// if (listaHeader.size() != 1)
						// {
						// throw new RuntimeException("Se esperaba una cabecera y se han recuperado [" + listaHeader.size() + "]");
						// }
						// WebElement tableHeader = listaHeader.get(0);
						//
						// LOGGER.info("Recuperando nombre campos");
						// List<WebElement> listaNombreCampos = tableHeader.findElements(By.tagName("th"));
						// if (listaNombreCampos.size() != 7)
						// {
						// throw new RuntimeException("Se esperaban 7 campos y se han recuperado [" + listaNombreCampos.size() + "]");
						// }
						// lineasFichero.add(getCabeceraFichero(listaNombreCampos));
						//
						// LOGGER.info("Recuperando bloque de datos");
						// List<WebElement> listaBody = tablaDatos.findElements(By.tagName("tbody"));
						// if (listaBody.size() != 1)
						// {
						// throw new RuntimeException("Se esperaba un bloque de datos y se han recuperado [" + listaBody.size() + "]");
						// }
						// WebElement tableBody = listaBody.get(0);
						//
						// LOGGER.info("Recuperando registros");
						// List<WebElement> listaRegistros = tableBody.findElements(By.tagName("tr"));
						// for (WebElement registro : listaRegistros)
						// {
						// List<WebElement> listaCampos = registro.findElements(By.tagName("td"));
						// String registroCSV = "";
						// registroCSV = registroCSV + listaCampos.get(0).getText() + CSV_SEPARATOR;
						// registroCSV = registroCSV + listaCampos.get(1).getAttribute("data-real-value") + CSV_SEPARATOR;
						// registroCSV = registroCSV + listaCampos.get(2).getAttribute("data-real-value") + CSV_SEPARATOR;
						// registroCSV = registroCSV + listaCampos.get(3).getAttribute("data-real-value") + CSV_SEPARATOR;
						// registroCSV = registroCSV + listaCampos.get(4).getAttribute("data-real-value") + CSV_SEPARATOR;
						// registroCSV = registroCSV + listaCampos.get(5).getAttribute("data-real-value");
						//
						// LOGGER.info("Guardando registro [" + registroCSV + "]");
						// lineasFichero.add(registroCSV);
						// }

						LOGGER.info("Escribiendo datos en formato XML");
						List<String> lineasFicheroXML = new ArrayList<String>();
						lineasFicheroXML.add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
						lineasFicheroXML.add("<datos>");
						lineasFicheroXML.add("<mercado>" + getMercadoByTipoURL(tipoUrl) + "</mercado>");
						lineasFicheroXML.add("<bolsa>GER</bolsa>");
						lineasFicheroXML.add("<indice>-</indice>");
						lineasFicheroXML.add("<ticker>" + hrefElemento + "</ticker>");
						lineasFicheroXML.add("<table>");
						lineasFicheroXML.add(tablaDatos.getAttribute("innerHTML"));
						lineasFicheroXML.add("</table>");
						lineasFicheroXML.add("</datos>");
						String fileNameXML = DOWNLOAD_PATH + "\\" + URLEncoder.encode(hrefElemento, CHARSET) + ".xml";
						FileUtils.writeLines(new File(fileNameXML), CHARSET, lineasFicheroXML);

						LOGGER.info("Parseando datos en formato XML");
						SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
						SAXHandler saxHandler = new SAXHandler();
						saxParser.parse(new File(fileNameXML), saxHandler);

						LOGGER.info("Escribiendo datos en formato CSV");
						List<String> lineasFicheroCSV = new ArrayList<String>();
						Activo activoActual = saxHandler.getActivoActual();

//						LOGGER.info("--- activo ---");
//						LOGGER.info("bolsa [" + saxHandler.getActivoActual().getBolsa() + "]");
//						LOGGER.info("indice [" + saxHandler.getActivoActual().getIndice() + "]");
//						LOGGER.info("mercado [" + saxHandler.getActivoActual().getMercado() + "]");
//						LOGGER.info("ticker [" + saxHandler.getActivoActual().getTicker() + "]");
//						for (Registro registro : saxHandler.getActivoActual().getListaRegistros())
//						{
//							LOGGER.info("--- datos ----");
//							LOGGER.info("apertura [" + registro.getApertura() + "]");
//							LOGGER.info("cierre [" + registro.getCierre() + "]");
//							LOGGER.info("fecha [" + registro.getFecha() + "]");
//							LOGGER.info("maximo [" + registro.getMaximo() + "]");
//							LOGGER.info("minimo [" + registro.getMinimo() + "]");
//							LOGGER.info("volumen [" + registro.getVolumen() + "]");
//						}

						String fileNameCSV = DOWNLOAD_PATH + "\\" + URLEncoder.encode(hrefElemento, CHARSET) + ".csv";
						FileUtils.writeLines(new File(fileNameCSV), CHARSET, lineasFicheroCSV);

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

	/**
	 * @param driver
	 */
	private static void gestionError(WebDriver driver)
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

	/**
	 * @param tipoUrl
	 * @return
	 */
	private static String getTableIdByTipoURL(TipoURL tipoUrl)
	{
		if (tipoUrl.getTipo().equalsIgnoreCase(TipoURL.TIPO_ETF))
		{
			return "etfs";
		}
		return null;
	}

	/**
	 * @param tipoUrl
	 * @return
	 */
	private static String getMercadoByTipoURL(TipoURL tipoUrl)
	{
		if (tipoUrl.getTipo().equalsIgnoreCase(TipoURL.TIPO_ETF))
		{
			return "ETF";
		}
		return null;
	}

	// /**
	// * @param listaCampos
	// */
	// private static String getCabeceraFichero(List<WebElement> listaCampos)
	// {
	// String cabeceraCSV = "";
	// if (!listaCampos.get(0).getAttribute("data-col-name").equalsIgnoreCase("date"))
	// {
	// throw new IllegalArgumentException("Se esperaba el valor [date] y se ha recuperado [" + listaCampos.get(0).getAttribute("data-col-name") + "]");
	// }
	// else
	// {
	// cabeceraCSV = cabeceraCSV + listaCampos.get(0).getAttribute("data-col-name") + CSV_SEPARATOR;
	// }
	// if (!listaCampos.get(1).getAttribute("data-col-name").equalsIgnoreCase("price"))
	// {
	// throw new IllegalArgumentException("Se esperaba el valor [price] y se ha recuperado [" + listaCampos.get(1).getAttribute("data-col-name") + "]");
	// }
	// else
	// {
	// cabeceraCSV = cabeceraCSV + listaCampos.get(1).getAttribute("data-col-name") + CSV_SEPARATOR;
	// }
	// if (!listaCampos.get(2).getAttribute("data-col-name").equalsIgnoreCase("open"))
	// {
	// throw new IllegalArgumentException("Se esperaba el valor [open] y se ha recuperado [" + listaCampos.get(2).getAttribute("data-col-name") + "]");
	// }
	// else
	// {
	// cabeceraCSV = cabeceraCSV + listaCampos.get(2).getAttribute("data-col-name") + CSV_SEPARATOR;
	// }
	// if (!listaCampos.get(3).getAttribute("data-col-name").equalsIgnoreCase("high"))
	// {
	// throw new IllegalArgumentException("Se esperaba el valor [high] y se ha recuperado [" + listaCampos.get(3).getAttribute("data-col-name") + "]");
	// }
	// else
	// {
	// cabeceraCSV = cabeceraCSV + listaCampos.get(3).getAttribute("data-col-name") + CSV_SEPARATOR;
	// }
	// if (!listaCampos.get(4).getAttribute("data-col-name").equalsIgnoreCase("low"))
	// {
	// throw new IllegalArgumentException("Se esperaba el valor [low] y se ha recuperado [" + listaCampos.get(4).getAttribute("data-col-name") + "]");
	// }
	// else
	// {
	// cabeceraCSV = cabeceraCSV + listaCampos.get(4).getAttribute("data-col-name") + CSV_SEPARATOR;
	// }
	// if (!listaCampos.get(5).getAttribute("data-col-name").equalsIgnoreCase("vol"))
	// {
	// throw new IllegalArgumentException("Se esperaba el valor [vol] y se ha recuperado [" + listaCampos.get(5).getAttribute("data-col-name") + "]");
	// }
	// else
	// {
	// cabeceraCSV = cabeceraCSV + listaCampos.get(5).getAttribute("data-col-name");
	// }
	// return cabeceraCSV;
	// }

}
