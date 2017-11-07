/**
 * 
 */
package jsm.mdata.selenium.investing.etl;

import java.io.File;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	 * Conexión a base de datos
	 */
	private static final String DATABASE_DRIVER = "org.postgresql.Driver";
	private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String DATABASE_USERNAME = "Empleado";
	private static final String DATABASE_PASSWORD = "Empleado";

	/**
	 * Web Driver
	 */
	public static final String WEB_DRIVER_PROPERTY = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_EXE = "C:\\_PELAYO\\Software\\Selenium\\drivers\\chromedriver.exe";

	/**
	 * Formatos
	 */
	private static final String CSV_SEPARATOR = ";";
	private static final String CHARSET = "UTF-8";
	private static final String CSV_RETURN = "\n";
	private static final SimpleDateFormat DATA_FEC_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat WEB_FEC_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.GERMAN);

	/**
	 * Descarga
	 */
	private static final String FEC_INI_DOWNLOAD = "01/01/2000";

	/**
	 * Rutas
	 */
	private static final String DOWNLOAD_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETLSelenium\\investing\\download";

	/**
	 * Lista URLs 01
	 */
	private final static List<TipoURL> LISTA_URLS_01 = new ArrayList<TipoURL>();
	static
	{
		// --
		// -- LISTA INDICES
		// --
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_INDEX, TipoURL.NA, TipoURL.NA, "https://es.investing.com/indices/major-indices"));
		// --
		// -- LISTA ACCIONES POR INDICE
		// --
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_ESP, TipoURL.INDICE_IBEX35, "https://es.investing.com/indices/spain-35-components"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_GER, TipoURL.INDICE_DAX30, "https://es.investing.com/indices/germany-30-components"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_FRA, TipoURL.INDICE_CAC40, "https://es.investing.com/indices/france-40-components"));
		// --
		// -- ETFS ALEMANIA POR EMISOR
		// --
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=BNP%20Paribas"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Boost"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=ChinaAMC"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Commerzbank%20AG"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=ComStage"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Credit%20Suisse"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=DB%20ETC"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Deka"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Deutsche%20X-trackers"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Direxion"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=First%20Trust"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Global%20X"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Hang%20Seng"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=HBSC"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Nomura"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Ossiam"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Other"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Pimco"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=PowerShares"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=ProShares"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=RBS"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=SG"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Source"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=SPDR"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=THEAM"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=UBS"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=UBS%20UK"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Vanguard"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=VelocityShares"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=WisdomTree"));
		LISTA_URLS_01.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=X-Trackers"));
	}

	/**
	 * Lista URLs 02
	 */
	private final static List<TipoURL> LISTA_URLS_02 = new ArrayList<TipoURL>();
	static
	{
		// --
		// -- ETFS ALEMANIA POR EMISOR
		// --
		LISTA_URLS_02.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Amundi"));
		LISTA_URLS_02.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=db%20X-trackers"));
		LISTA_URLS_02.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=ETFS"));
		LISTA_URLS_02.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=iShares"));
		LISTA_URLS_02.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/germany-etfs?&issuer_filter=Lyxor"));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			LOGGER.info("Recuperando lista de URLs");
			List<TipoURL> listaURLs = seleccionListaURLs(args);
			LOGGER.info("Iniciando proceso automatizado");
			run(listaURLs);
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
	 * @param args
	 * @return
	 * @throws Exception
	 */
	private static List<TipoURL> seleccionListaURLs(String[] args) throws Exception
	{
		List<TipoURL> listaURLs = null;
		if (args == null || args.length == 0)
		{
			throw new Exception("Se debe proporcionar el número de lista de descarga [1 | 2]");
		}
		else if (args.length > 1)
		{
			throw new Exception("Se espera un solo argumento y se han recuperado [" + args.length + "]");
		}
		else
		{
			Integer numeroLista = null;
			try
			{
				numeroLista = new Integer(args[0]);
			}
			catch (Exception e)
			{
				throw new Exception("Los valores permitidos para el número de lista de descarga son [1 | 2]");
			}
			if (numeroLista.intValue() < 1 || numeroLista.intValue() > 2)
			{
				throw new Exception("Los valores permitidos para el número de lista de descarga son [1 | 2]");
			}
			else if (numeroLista.intValue() == 1)
			{
				listaURLs = LISTA_URLS_01;
			}
			else if (numeroLista.intValue() == 2)
			{
				listaURLs = LISTA_URLS_02;
			}
			else
			{
				throw new Exception("Los valores permitidos para el número de lista de descarga son [1 | 2]");
			}
			return listaURLs;
		}
	}

	/**
	 * @throws Exception
	 * 
	 */
	private static void run(List<TipoURL> listaURLs) throws Exception
	{
		LOGGER.info("Suprimiendo ficheros temporales antiguos");
		FileUtils.cleanDirectory(new File(DOWNLOAD_PATH));

		LOGGER.info("Iniciando driver");
		System.setProperty(WEB_DRIVER_PROPERTY, WEB_DRIVER_EXE);
		WebDriver driver = new ChromeDriver();

		LOGGER.info("Cargando URL inicial para introducir datos proxy");
		driver.get("https://es.investing.com/");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.className("disclaimer")));

		LOGGER.info("Iniciando proceso");
		int urlsIdx = 0;
		while (urlsIdx < listaURLs.size())
		{
			try
			{
				TipoURL tipoUrl = listaURLs.get(urlsIdx);

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

				LOGGER.info("Procesando elementos");
				int hrefsIdx = 0;
				while (hrefsIdx < listaHrefs.size())
				{
					try
					{
						String hrefElemento = listaHrefs.get(hrefsIdx);
						procesarElemento(driver, tipoUrl, hrefElemento);
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
	 * @param tipoUrl
	 * @param hrefElemento
	 * @throws Exception
	 */
	public static void procesarElemento(WebDriver driver, TipoURL tipoUrl, String hrefElemento) throws Exception
	{
		LOGGER.info("Cargando URL [" + hrefElemento + "]");
		driver.get(hrefElemento);

		LOGGER.info("Accediendo información histórica");
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Información histórica")));
		WebElement historicoLink = driver.findElement(By.linkText("Información histórica"));
		historicoLink.click();

		LOGGER.info("Actualizando fechas de descarga de datos");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("picker")));
		Date fechaInicioDescarga = getFechaInicioDescarga(tipoUrl.getMercado(), tipoUrl.getBolsa(), tipoUrl.getIndice(), hrefElemento);
		Date fechaFinDescarga = getFechaFinDescarga();

		LOGGER.info("Fecha inicio [" + WEB_FEC_FORMAT.format(fechaInicioDescarga) + "] Fecha fin [" + WEB_FEC_FORMAT.format(fechaFinDescarga) + "]");
		if (!fechaInicioDescarga.after(fechaFinDescarga))
		{
			((JavascriptExecutor) driver).executeScript("document.getElementById('picker').value='" + WEB_FEC_FORMAT.format(fechaInicioDescarga) + " - " + WEB_FEC_FORMAT.format(fechaFinDescarga) + "'");
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("flatDatePickerCanvasHol")));
			WebElement fechasLink = driver.findElement(By.id("flatDatePickerCanvasHol"));
			fechasLink.click();

			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("startDate")));
			WebElement startDate = driver.findElement(By.id("startDate"));
			startDate.clear();
			startDate.sendKeys(WEB_FEC_FORMAT.format(fechaInicioDescarga));
			new WebDriverWait(driver, 10).until(ExpectedConditions.attributeToBe(By.id("startDate"), "value", WEB_FEC_FORMAT.format(fechaInicioDescarga)));

			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("endDate")));
			WebElement endDate = driver.findElement(By.id("endDate"));
			endDate.clear();
			endDate.sendKeys(WEB_FEC_FORMAT.format(fechaFinDescarga));
			new WebDriverWait(driver, 10).until(ExpectedConditions.attributeToBe(By.id("endDate"), "value", WEB_FEC_FORMAT.format(fechaFinDescarga)));

			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("applyBtn")));
			WebElement aceptarBtn = driver.findElement(By.id("applyBtn"));
			aceptarBtn.click();

			LOGGER.info("Recuperando tabla de datos");
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("curr_table")));
			WebElement tablaDatos = driver.findElement(By.id("curr_table"));
			String tablaDatosHtml = tablaDatos.getAttribute("innerHTML");
			if (tablaDatosHtml.indexOf("No se encontraron resultados") == -1)
			{
				LOGGER.info("Escribiendo fichero de datos sin formatear");
				List<String> lineasFicheroXMLInput = getLineasFicheroXML(tipoUrl.getMercado(), tipoUrl.getBolsa(), tipoUrl.getIndice(), hrefElemento, tablaDatosHtml);
				String fileNameXMLInput = DOWNLOAD_PATH + "\\" + URLEncoder.encode(hrefElemento, CHARSET) + ".INPUT.xml";
				FileUtils.writeLines(new File(fileNameXMLInput), CHARSET, lineasFicheroXMLInput);

				LOGGER.info("Formateando datos");
				SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
				SAXHandler saxHandler = new SAXHandler();
				saxParser.parse(new File(fileNameXMLInput), saxHandler);

				LOGGER.info("Guardando datos");
				guardarDatosHistoricoActivoActual(saxHandler.getActivoActual());
			}
			else
			{
				LOGGER.info("No se han encontrado datos");
			}
		}
		else
		{
			LOGGER.info("No hay datos que procesar");
		}
	}

	/**
	 * @param mercado
	 * @param bolsa
	 * @param indice
	 * @param hrefElemento
	 * @param datos
	 * @return
	 */
	private static List<String> getLineasFicheroXML(String mercado, String bolsa, String indice, String hrefElemento, String datos)
	{
		List<String> lineasFicheroXML = new ArrayList<String>();
		lineasFicheroXML.add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
		lineasFicheroXML.add("<datos>");
		lineasFicheroXML.add("<mercado>" + mercado + "</mercado>");
		lineasFicheroXML.add("<bolsa>" + bolsa + "</bolsa>");
		lineasFicheroXML.add("<indice>" + indice + "</indice>");
		lineasFicheroXML.add("<ticker>" + hrefElemento + "</ticker>");
		lineasFicheroXML.add("<table>");
		lineasFicheroXML.add(datos);
		lineasFicheroXML.add("</table>");
		lineasFicheroXML.add("</datos>");
		return lineasFicheroXML;
	}

	/**
	 * @param driver
	 */
	public static void gestionError(WebDriver driver)
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
		if (tipoUrl.getMercado().equalsIgnoreCase(TipoURL.MERCADO_INDEX))
		{
			return "cr_12";
		}
		if (tipoUrl.getMercado().equalsIgnoreCase(TipoURL.MERCADO_STOCK))
		{
			return "cr1";
		}
		if (tipoUrl.getMercado().equalsIgnoreCase(TipoURL.MERCADO_ETF))
		{
			return "etfs";
		}
		return null;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private static Connection getConnection() throws Exception
	{
		Class.forName(DATABASE_DRIVER);
		return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
	}

	/**
	 * @param mercado
	 * @param bolsa
	 * @param indice
	 * @param ticker
	 * @return
	 * @throws Exception
	 */
	private static Date getFechaInicioDescarga(String mercado, String bolsa, String indice, String ticker) throws Exception
	{
		Connection dbConnection = null;
		try
		{
			LOGGER.info("Abriendo conexión a base de datos");
			dbConnection = getConnection();
			String consultaSQL = "select max(fecha) as ultima_fecha from public.mercados_investing where mercado = ? and bolsa = ? and indice = ? and ticker = ? group by mercado, bolsa, indice, ticker";
			PreparedStatement pStatement = dbConnection.prepareStatement(consultaSQL);
			int paramIdx = 1;
			pStatement.setString(paramIdx++, mercado);
			pStatement.setString(paramIdx++, bolsa);
			pStatement.setString(paramIdx++, indice);
			pStatement.setString(paramIdx++, ticker);
			ResultSet rSet = pStatement.executeQuery();
			Date fechaInicioDescarga = WEB_FEC_FORMAT.parse(FEC_INI_DOWNLOAD);
			if (rSet.next())
			{
				Date ultimaFecha = rSet.getDate("ultima_fecha");
				if (ultimaFecha != null)
				{
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(ultimaFecha);
					calendar.add(Calendar.DATE, 1);
					fechaInicioDescarga = calendar.getTime();
				}
			}
			return fechaInicioDescarga;
		}
		finally
		{
			if (dbConnection != null)
			{
				LOGGER.info("Cerrando conexión a base de datos");
				dbConnection.close();
			}
		}
	}

	/**
	 * @return
	 */
	private static Date getFechaFinDescarga()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * @param dbConnection
	 * @param mercado
	 * @param bolsa
	 * @param indice
	 * @param ticker
	 * @param fecha
	 * @param apertura
	 * @param maximo
	 * @param minimo
	 * @param cierre
	 * @param volumen
	 * @throws Exception
	 */
	private static void insertaRegistro(Connection dbConnection, String mercado, String bolsa, String indice, String ticker, Date fecha, BigDecimal apertura, BigDecimal maximo, BigDecimal minimo, BigDecimal cierre, BigDecimal volumen) throws Exception
	{
		PreparedStatement pStatement = null;
		try
		{
			String insertSQL = "insert into public.mercados_investing (mercado, bolsa, indice, ticker, fecha, apertura, maximo, minimo, cierre, volumen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pStatement = dbConnection.prepareStatement(insertSQL);
			int paramIdx = 1;
			pStatement.setString(paramIdx++, mercado);
			pStatement.setString(paramIdx++, bolsa);
			pStatement.setString(paramIdx++, indice);
			pStatement.setString(paramIdx++, ticker);
			pStatement.setDate(paramIdx++, new java.sql.Date(fecha.getTime()));
			pStatement.setBigDecimal(paramIdx++, apertura);
			pStatement.setBigDecimal(paramIdx++, maximo);
			pStatement.setBigDecimal(paramIdx++, minimo);
			pStatement.setBigDecimal(paramIdx++, cierre);
			pStatement.setBigDecimal(paramIdx++, volumen);
			int rowsInserted = pStatement.executeUpdate();
			if (rowsInserted != 1)
			{
				throw new Exception("Se han insertado [" + rowsInserted + "] registros");
			}
		}
		finally
		{
			if (pStatement != null)
			{
				pStatement.close();
			}
		}
	}

	/**
	 * @param dbConnection
	 * @param mercado
	 * @param bolsa
	 * @param indice
	 * @param ticker
	 * @param fecha
	 * @return
	 * @throws Exception
	 */
	private static boolean existeRegistro(Connection dbConnection, String mercado, String bolsa, String indice, String ticker, Date fecha) throws Exception
	{
		PreparedStatement pStatement = null;
		try
		{
			String consultaSQL = "select count(1) as existe from public.mercados_investing where mercado = ? and bolsa = ? and indice = ? and ticker = ? and fecha = ?";
			pStatement = dbConnection.prepareStatement(consultaSQL);
			int paramIdx = 1;
			pStatement.setString(paramIdx++, mercado);
			pStatement.setString(paramIdx++, bolsa);
			pStatement.setString(paramIdx++, indice);
			pStatement.setString(paramIdx++, ticker);
			pStatement.setDate(paramIdx++, new java.sql.Date(fecha.getTime()));
			ResultSet rSet = pStatement.executeQuery();
			Integer existe = null;
			if (rSet.next())
			{
				existe = rSet.getInt("existe");
			}
			return (existe != null && existe.intValue() > 0);
		}
		finally
		{
			if (pStatement != null)
			{
				pStatement.close();
			}
		}
	}

	/**
	 * @param activoActual
	 * @throws Exception
	 */
	private static void guardarDatosHistoricoActivoActual(Activo activoActual) throws Exception
	{
		Connection dbConnection = null;
		try
		{
			LOGGER.info("Abriendo conexión a base de datos");
			dbConnection = getConnection();
			dbConnection.setAutoCommit(false);
			String datos = "FECHA" + CSV_SEPARATOR;
			datos = datos + "APERTURA" + CSV_SEPARATOR;
			datos = datos + "MAXIMO" + CSV_SEPARATOR;
			datos = datos + "MINIMO" + CSV_SEPARATOR;
			datos = datos + "CIERRE" + CSV_SEPARATOR;
			datos = datos + "VOLUMEN" + CSV_RETURN;
			LOGGER.info("Grabando registros en base de datos");
			int numRegInsertados = 0;
			for (Registro registro : activoActual.getListaRegistros())
			{
				Date fecha = registro.getFecha();
				BigDecimal apertura = registro.getApertura();
				BigDecimal maximo = registro.getMaximo();
				BigDecimal minimo = registro.getMinimo();
				BigDecimal cierre = registro.getCierre();
				BigDecimal volumen = registro.getVolumen();
				datos = datos + DATA_FEC_FORMAT.format(fecha) + CSV_SEPARATOR;
				datos = datos + NUMBER_FORMAT.format(apertura) + CSV_SEPARATOR;
				datos = datos + NUMBER_FORMAT.format(maximo) + CSV_SEPARATOR;
				datos = datos + NUMBER_FORMAT.format(minimo) + CSV_SEPARATOR;
				datos = datos + NUMBER_FORMAT.format(cierre) + CSV_SEPARATOR;
				datos = datos + NUMBER_FORMAT.format(volumen) + CSV_RETURN;
				String mercado = activoActual.getMercado();
				String bolsa = activoActual.getBolsa();
				String indice = activoActual.getIndice();
				String ticker = activoActual.getTicker();
				if (!existeRegistro(dbConnection, mercado, bolsa, indice, ticker, fecha))
				{
					insertaRegistro(dbConnection, mercado, bolsa, indice, ticker, fecha, apertura, maximo, minimo, cierre, volumen);
					numRegInsertados++;
				}
				else
				{
					LOGGER.info("El registro [" + mercado + "] [" + bolsa + "] [" + indice + "] [" + ticker + "] [" + DATA_FEC_FORMAT.format(fecha) + "] ya existe");
				}
			}
			LOGGER.info("Escribiendo fichero de datos formateados");
			List<String> lineasFicheroXMLOutput = getLineasFicheroXML(activoActual.getMercado(), activoActual.getBolsa(), activoActual.getIndice(), activoActual.getTicker(), datos);
			String fileNameXMLOutput = DOWNLOAD_PATH + "\\" + URLEncoder.encode(activoActual.getTicker(), CHARSET) + ".OUTPUT.xml";
			FileUtils.writeLines(new File(fileNameXMLOutput), CHARSET, lineasFicheroXMLOutput);
			LOGGER.info("Confirmando transacción para [" + numRegInsertados + "] registros");
			dbConnection.commit();
		}
		catch (Exception e1)
		{
			LOGGER.error("ERROR", e1);
			if (dbConnection != null)
			{
				LOGGER.info("Deshaciendo transacción");
				dbConnection.rollback();
			}
		}
		finally
		{
			if (dbConnection != null)
			{
				LOGGER.info("Cerrando conexión a base de datos");
				dbConnection.close();
			}
		}
	}

}
