/**
 * 
 */
package jsm.mdata.etl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Empleado
 *
 */
public class ETLEleconomista
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ETLEleconomista.class);

	/**
	 * Conexiones a URLs
	 */
	private static final Boolean USE_PROXY = true;
	private static final String PROXY_URL = "192.6.2.109";
	private static final Integer PROXY_PORT = 81;
	private static final String PROXY_USERNAME = "panda";
	private static final String PROXY_PASSWORD = "panda";
	private static final int SG_TIMEOUT = 15 * 1000;
	private static final int MAX_INTENTOS = 3;

	/**
	 * Ficheros
	 */
	private static final String DATA_URLS_FILE = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\urls\\eleconomista\\urls.txt";
	private static final String TMP_DATA_FILE_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\urls\\eleconomista\\download\\";
	private static final String TMP_DATA_FILE_PREFIX = "_data_file_";
	private static final String TMP_DATA_FILE_EXT = ".txt";
	private static final String C_COMENT = "--";
	private static final String C_SEPARADOR = "##CSEP##";
	private static final String C_FEC_INI = "##FINI##";
	private static final String C_FEC_FIN = "##FFIN##";
	private static final String C_CABECERA_INI = "Fecha";
	private static final SimpleDateFormat C_FEC_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final String D_SEPARADOR = "\t";
	private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.GERMAN);

	/**
	 * Conexión a base de datos
	 */
	private static final String DATABASE_DRIVER = "org.postgresql.Driver";
	private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String DATABASE_USERNAME = "Empleado";
	private static final String DATABASE_PASSWORD = "Empleado";

	/**
	 * Variables
	 */
	private static String fecIniDescargaInicial = null;

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Connection dbConnection = null;
		try
		{
			getFechaInicioDescargaInicial(args);
			LOGGER.info("Abriendo conexión a base de datos");
			dbConnection = getConnection();
			dbConnection.setAutoCommit(false);
			LOGGER.info("Descargando ficheros temporales");
			descargaFicherosTemporales(dbConnection);
			LOGGER.info("Procesando ficheros temporales");
			procesoFicherosTemporales(dbConnection);
			LOGGER.info("Confirmando transacción");
			dbConnection.commit();
		}
		catch (Exception e1)
		{
			LOGGER.error("ERROR", e1);
			if (dbConnection != null)
			{
				try
				{
					LOGGER.info("Deshaciendo transacción");
					dbConnection.rollback();
				}
				catch (Exception e2)
				{
					LOGGER.error("ERROR", e2);
				}
			}
		}
		finally
		{
			if (dbConnection != null)
			{
				try
				{
					LOGGER.info("Cerrando conexión a base de datos");
					dbConnection.close();
				}
				catch (Exception e3)
				{
					LOGGER.error("ERROR", e3);
				}
			}
		}

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	private static void getFechaInicioDescargaInicial(String[] args) throws Exception
	{
		if (args == null || args.length == 0)
		{
			throw new Exception("Se debe proporcionar la fecha de inicio de descarga");
		}
		else if (args.length > 1)
		{
			throw new Exception("Se espera un solo argumento y se han recuperado " + args.length);
		}
		else
		{
			try
			{
				C_FEC_FORMAT.parse(args[0]);
			}
			catch (Exception e)
			{
				throw new Exception("La fecha de inicio de descarga debe estar en formato 'yyyy-MM-dd'");
			}
			fecIniDescargaInicial = args[0];
		}
	}

	/**
	 * @throws Exception
	 */
	private static void descargaFicherosTemporales(Connection dbConnection) throws Exception
	{
		List<String> dataUrlLines = FileUtils.readLines(new File(DATA_URLS_FILE));
		for (String dataUrlLine : dataUrlLines)
		{
			if (!dataUrlLine.startsWith(C_COMENT))
			{
				String[] dataUrlLineTokens = dataUrlLine.split(C_SEPARADOR);
				String mercado = dataUrlLineTokens[0];
				String bolsa = dataUrlLineTokens[1];
				String indice = dataUrlLineTokens[2];
				String ticker = dataUrlLineTokens[3];
				String dataUrl = dataUrlLineTokens[4];
				String fechaIni = getFechaInicioDescarga(dbConnection, mercado, bolsa, indice, ticker);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(C_FEC_FORMAT.parse(fechaIni));
				calendar.add(Calendar.YEAR, 1);
				String fechaFin = C_FEC_FORMAT.format(calendar.getTime());
				dataUrl = dataUrl.replaceAll(C_FEC_INI, fechaIni);
				dataUrl = dataUrl.replaceAll(C_FEC_FIN, fechaFin);
				LOGGER.info("Descargando URL [" + dataUrl + "]");
				if (USE_PROXY)
				{
					descargarFicheroConProxy(mercado, bolsa, indice, ticker, dataUrl);
				}
				else
				{
					descargarFicheroSinProxy(mercado, bolsa, indice, ticker, dataUrl);
				}
			}
		}
	}

	/**
	 * @throws Exception
	 */
	private static void procesoFicherosTemporales(Connection dbConnection) throws Exception
	{
		Collection<File> dataFileList = FileUtils.listFiles(new File(TMP_DATA_FILE_PATH), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File dataFile : dataFileList)
		{
			LOGGER.info("Procesando fichero [" + dataFile.getName() + "]");
			String[] dataFileTokens = dataFile.getName().split(C_SEPARADOR);
			String mercado = dataFileTokens[1];
			String bolsa = dataFileTokens[2];
			String indice = dataFileTokens[3];
			String ticker = dataFileTokens[4];
			List<String> dataFileLines = FileUtils.readLines(dataFile);
			for (String dataFileLine : dataFileLines)
			{
				if (!dataFileLine.startsWith(C_CABECERA_INI))
				{
					String[] dataFields = dataFileLine.split(D_SEPARADOR);
					Date fecha = C_FEC_FORMAT.parse(dataFields[0]);
					BigDecimal cierre = new BigDecimal(NUMBER_FORMAT.parse(dataFields[1]).toString());
					BigDecimal maximo = new BigDecimal(NUMBER_FORMAT.parse(dataFields[4]).toString());
					BigDecimal minimo = new BigDecimal(NUMBER_FORMAT.parse(dataFields[5]).toString());
					BigDecimal volumen = (dataFields.length == 7) ? new BigDecimal(NUMBER_FORMAT.parse(dataFields[6]).toString()) : BigDecimal.ZERO;
					insertaRegistro(dbConnection, mercado, bolsa, indice, ticker, fecha, maximo, minimo, cierre, volumen);
				}
			}
			LOGGER.info("Confirmando transacción");
			dbConnection.commit();
		}
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
	 * @param dataUrl
	 * @param index
	 */
	private static void descargarFicheroConProxy(String mercado, String bolsa, String indice, String ticker, String dataUrl) throws Exception
	{
		int intentos = 0;
		boolean retry = false;
		do
		{
			try
			{
				intentos++;
				CredentialsProvider credsProvider = new BasicCredentialsProvider();
				credsProvider.setCredentials(new AuthScope(PROXY_URL, PROXY_PORT), new UsernamePasswordCredentials(PROXY_USERNAME, PROXY_PASSWORD));
				CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
				HttpHost proxy = new HttpHost(PROXY_URL, PROXY_PORT);
				RequestConfig config = RequestConfig.custom().setConnectTimeout(SG_TIMEOUT).setProxy(proxy).build();
				HttpGet httpGET = new HttpGet(dataUrl);
				httpGET.setConfig(config);
				CloseableHttpResponse response = httpClient.execute(httpGET);
				String fileName = TMP_DATA_FILE_PREFIX + C_SEPARADOR + mercado + C_SEPARADOR + bolsa + C_SEPARADOR + indice + C_SEPARADOR + ticker + C_SEPARADOR + TMP_DATA_FILE_EXT;
				FileUtils.copyInputStreamToFile(response.getEntity().getContent(), new File(TMP_DATA_FILE_PATH + fileName));
				response.close();
				httpClient.close();
			}
			catch (Exception e)
			{
				LOGGER.error("ERROR", e);
				if (intentos <= MAX_INTENTOS)
				{
					LOGGER.info("Se reintenta la descarga del fichero");
					retry = true;
				}
				else
				{
					LOGGER.info("Se aborta la descarga del fichero");
					retry = false;
				}
			}
		}
		while (retry);
	}

	/**
	 * @param dataUrl
	 * @param index
	 */
	private static void descargarFicheroSinProxy(String mercado, String bolsa, String indice, String ticker, String dataUrl)
	{
		throw new UnsupportedOperationException("Operación no implementada");
	}

	/**
	 * @param dbConnection
	 * @param mercado
	 * @param bolsa
	 * @param indice
	 * @param ticker
	 * @param fecha
	 * @param maximo
	 * @param minimo
	 * @param cierre
	 * @param volumen
	 * @throws Exception
	 */
	private static void insertaRegistro(Connection dbConnection, String mercado, String bolsa, String indice, String ticker, Date fecha, BigDecimal maximo, BigDecimal minimo, BigDecimal cierre, BigDecimal volumen) throws Exception
	{
		PreparedStatement pStatement = null;
		try
		{
			String insertSQL = "INSERT INTO public.mercados (mercado, bolsa, indice, ticker, fecha, maximo, minimo, cierre, volumen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pStatement = dbConnection.prepareStatement(insertSQL);
			int paramIdx = 1;
			pStatement.setString(paramIdx++, mercado);
			pStatement.setString(paramIdx++, bolsa);
			pStatement.setString(paramIdx++, indice);
			pStatement.setString(paramIdx++, ticker);
			pStatement.setDate(paramIdx++, new java.sql.Date(fecha.getTime()));
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
		catch (Exception e)
		{
			LOGGER.error("ERROR", e);
			throw e;
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
	 * @return
	 * @throws Exception
	 */
	private static String getFechaInicioDescarga(Connection dbConnection, String mercado, String bolsa, String indice, String ticker) throws Exception
	{
		PreparedStatement pStatement = null;
		try
		{
			String consultaSQL = "select max(fecha) as ultima_fecha from public.mercados where mercado = ? and bolsa = ? and indice = ? and ticker = ? group by mercado, bolsa, indice, ticker";
			pStatement = dbConnection.prepareStatement(consultaSQL);
			int paramIdx = 1;
			pStatement.setString(paramIdx++, mercado);
			pStatement.setString(paramIdx++, bolsa);
			pStatement.setString(paramIdx++, indice);
			pStatement.setString(paramIdx++, ticker);
			ResultSet rSet = pStatement.executeQuery();
			String fechaInicioDescarga = fecIniDescargaInicial;
			if (rSet.next())
			{
				Date ultimaFecha = rSet.getDate("ultima_fecha");
				if (ultimaFecha != null)
				{
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(ultimaFecha);
					calendar.add(Calendar.DATE, 1);
					fechaInicioDescarga = C_FEC_FORMAT.format(calendar.getTime());
				}
			}
			return fechaInicioDescarga;
		}
		catch (Exception e)
		{
			LOGGER.error("ERROR", e);
			throw e;
		}
		finally
		{
			if (pStatement != null)
			{
				pStatement.close();
			}
		}
	}

}
