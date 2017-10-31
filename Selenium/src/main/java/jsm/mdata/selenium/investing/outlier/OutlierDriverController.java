/**
 * 
 */
package jsm.mdata.selenium.investing.outlier;

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

import jsm.mdata.selenium.investing.Activo;
import jsm.mdata.selenium.investing.Registro;
import jsm.mdata.selenium.investing.SAXHandler;
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
	 * Conexión a base de datos
	 */
	protected static final String DATABASE_DRIVER = "org.postgresql.Driver";
	protected static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
	protected static final String DATABASE_USERNAME = "Empleado";
	protected static final String DATABASE_PASSWORD = "Empleado";

	/**
	 * Web Driver
	 */
	private static final String WEB_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static final String WEB_DRIVER_EXE = "C:\\_PELAYO\\Software\\Selenium\\drivers\\chromedriver.exe";

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
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/amundi-etf-euro-stoxx-50?cid=45963"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/amundi-etf-euro-stoxx-smlcap?cid=1009032"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/amundi-leveraged-euro-stoxx-50?cid=1009033"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/amundi-s-p-500-eur-hedged-daily-de"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/amundi-short-euro-stoxx-50-daily?cid=45966"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/amundi-short-gov-bond-mts-10-15?cid=46037"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/boost-euro-stoxx-50-3x-short-daily?cid=1009020"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/boost-shortdax-3x-daily-de"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/boost-shortdax-3x-daily-de?cid=998509"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_ETF, TipoURL.BOLSA_GER, TipoURL.NA, "https://es.investing.com/etfs/rici-enhanced-zinn-tr-hedge?cid=1014060"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_FRA, TipoURL.INDICE_CAC40, "https://es.investing.com/equities/bnp-paribas"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_FRA, TipoURL.INDICE_CAC40, "https://es.investing.com/equities/credit-agricole"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_FRA, TipoURL.INDICE_CAC40, "https://es.investing.com/equities/eads"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_FRA, TipoURL.INDICE_CAC40, "https://es.investing.com/equities/gdf-suez"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_FRA, TipoURL.INDICE_CAC40, "https://es.investing.com/equities/publicis-groupe"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_FRA, TipoURL.INDICE_CAC40, "https://es.investing.com/equities/vinci"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_GER, TipoURL.INDICE_DAX30, "https://es.investing.com/equities/commerzbank-ag"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_GER, TipoURL.INDICE_DAX30, "https://es.investing.com/equities/fresenius-ag"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_GER, TipoURL.INDICE_DAX30, "https://es.investing.com/equities/infineon-tech"));
		LISTA_URLS.add(new TipoURL(TipoURL.MERCADO_STOCK, TipoURL.BOLSA_GER, TipoURL.INDICE_DAX30, "https://es.investing.com/equities/volkswagen-vz"));
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
		System.setProperty(WEB_DRIVER_PROPERTY, WEB_DRIVER_EXE);
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
					new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("applyBtn")));
					WebElement aceptarBtn = driver.findElement(By.id("applyBtn"));
					aceptarBtn.click();

					LOGGER.info("Recuperando tabla de datos");
					new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("curr_table")));
					WebElement tablaDatos = driver.findElement(By.id("curr_table"));

					LOGGER.info("Escribiendo fichero de datos sin formatear");
					List<String> lineasFicheroXMLInput = getLineasFicheroXML(tipoUrl.getMercado(), tipoUrl.getBolsa(), tipoUrl.getIndice(), hrefElemento, tablaDatos.getAttribute("innerHTML"));
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
					LOGGER.info("No hay datos que procesar");
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
	 * @return
	 * @throws Exception
	 */
	protected static Connection getConnection() throws Exception
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
	protected static void insertaRegistro(Connection dbConnection, String mercado, String bolsa, String indice, String ticker, Date fecha, BigDecimal apertura, BigDecimal maximo, BigDecimal minimo, BigDecimal cierre, BigDecimal volumen) throws Exception
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
				insertaRegistro(dbConnection, mercado, bolsa, indice, ticker, fecha, apertura, maximo, minimo, cierre, volumen);
			}
			LOGGER.info("Escribiendo fichero de datos formateados");
			List<String> lineasFicheroXMLOutput = getLineasFicheroXML(activoActual.getMercado(), activoActual.getBolsa(), activoActual.getIndice(), activoActual.getTicker(), datos);
			String fileNameXMLOutput = DOWNLOAD_PATH + "\\" + URLEncoder.encode(activoActual.getTicker(), CHARSET) + ".OUTPUT.xml";
			FileUtils.writeLines(new File(fileNameXMLOutput), CHARSET, lineasFicheroXMLOutput);
			LOGGER.info("Confirmando transacción");
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
