/**
 * 
 */
package jsm.mdata.etl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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
	 * Proxy
	 */
	private static final Boolean USE_PROXY = true;
	private static final String PROXY_URL = "192.6.2.109";
	private static final Integer PROXY_PORT = 81;
	private static final String PROXY_USERNAME = "panda";
	private static final String PROXY_PASSWORD = "panda";

	/**
	 * Ficheros
	 */
	private static final String DATA_URLS_FILE = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\consultas\\eleconomista\\consultas.txt";
	private static final String TMP_DATA_FILE_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\consultas\\eleconomista\\download\\";
	private static final String TMP_DATA_FILE_PREFIX = "_data_file_";
	private static final String TMP_DATA_FILE_EXT = ".txt";
	private static final String CCOMENT = "--";
	private static final String CSEPARADOR = "##CSEP##";
	private static final String CFECINI = "##FINI##";
	private static final String CFECFIN = "##FFIN##";
	private static final String CCABECERAINI = "Fecha";
	private static final SimpleDateFormat CFECFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final String DFSEPARADOR = "\t";
	private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.GERMAN);

	/**
	 * Conexión a base de datos
	 */
	private static final String DATABASE_DRIVER = "org.postgresql.Driver";
	private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String DATABASE_USERNAME = "Empleado";
	private static final String DATABASE_PASSWORD = "Empleado";

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Connection dbConnection = null;
		try
		{
			LOGGER.info("Descargando ficheros temporales");
			// descargaFicherosTemporales();
			LOGGER.info("Abriendo conexión a base de datos");
			dbConnection = getConnection();
			dbConnection.setAutoCommit(false);
			LOGGER.info("Procesando ficheros temporales");
			procesoFicherosTemporales(dbConnection);
			LOGGER.info("Confirmando transacción");
			dbConnection.commit();
		}
		catch (Exception e)
		{
			LOGGER.error("ERROR", e);
		}
		finally
		{
			LOGGER.info("Cerrando conexión a base de datos");
			if (dbConnection != null)
			{
				try
				{
					dbConnection.close();
				}
				catch (Exception e)
				{
					LOGGER.error("ERROR", e);
				}
			}
		}

	}

	/**
	 * @throws Exception
	 */
	private static void descargaFicherosTemporales() throws Exception
	{
		try
		{
			List<String> dataUrlLines = FileUtils.readLines(new File(DATA_URLS_FILE));
			for (String dataUrlLine : dataUrlLines)
			{
				if (!dataUrlLine.startsWith(CCOMENT))
				{
					String[] dataUrlLineTokens = dataUrlLine.split(CSEPARADOR);
					String mercado = dataUrlLineTokens[0];
					String bolsa = dataUrlLineTokens[1];
					String indice = dataUrlLineTokens[2];
					String ticker = dataUrlLineTokens[3];
					String dataUrl = dataUrlLineTokens[4];
					dataUrl = dataUrl.replaceAll(CFECINI, "2015-01-01");
					dataUrl = dataUrl.replaceAll(CFECFIN, "2016-01-01");
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
		catch (Exception e)
		{
			LOGGER.error("Error descargando ficheros temporales", e);
			throw e;
		}
	}

	/**
	 * @throws Exception
	 */
	private static void procesoFicherosTemporales(Connection dbConnection) throws Exception
	{
		try
		{
			Collection<File> dataFileList = FileUtils.listFiles(new File(TMP_DATA_FILE_PATH), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
			for (File dataFile : dataFileList)
			{
				LOGGER.info("Procesando fichero [" + dataFile.getName() + "]");
				String[] dataFileTokens = dataFile.getName().split(CSEPARADOR);
				String mercado = dataFileTokens[1];
				String bolsa = dataFileTokens[2];
				String indice = dataFileTokens[3];
				String ticker = dataFileTokens[4];
				List<String> dataFileLines = FileUtils.readLines(dataFile);
				for (String dataFileLine : dataFileLines)
				{
					if (!dataFileLine.startsWith(CCABECERAINI))
					{
						String[] dataFields = dataFileLine.split(DFSEPARADOR);
						Date fecha = CFECFORMAT.parse(dataFields[0]);
						BigDecimal cierre = new BigDecimal(NUMBER_FORMAT.parse(dataFields[1]).toString());
						BigDecimal maximo = new BigDecimal(NUMBER_FORMAT.parse(dataFields[4]).toString());
						BigDecimal minimo = new BigDecimal(NUMBER_FORMAT.parse(dataFields[5]).toString());
						BigDecimal volumen = (dataFields.length == 7) ? new BigDecimal(NUMBER_FORMAT.parse(dataFields[6]).toString()) : null;
						insertaRegistro(dbConnection, mercado, bolsa, indice, ticker, fecha, maximo, minimo, cierre, volumen);
					}
				}
			}
		}
		catch (Exception e)
		{
			LOGGER.error("Error procesando ficheros temporales", e);
			throw e;
		}
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private static Connection getConnection() throws Exception
	{
		try
		{
			Class.forName(DATABASE_DRIVER);
			return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
		}
		catch (Exception e)
		{
			LOGGER.error("Error abriendo conexión a base de datos", e);
			throw e;
		}
	}

	/**
	 * @param dataUrl
	 * @param index
	 */
	private static void descargarFicheroConProxy(String mercado, String bolsa, String indice, String ticker, String dataUrl)
	{
		try
		{
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(new AuthScope(PROXY_URL, PROXY_PORT), new UsernamePasswordCredentials(PROXY_USERNAME, PROXY_PASSWORD));
			CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
			HttpHost proxy = new HttpHost(PROXY_URL, PROXY_PORT);
			RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
			HttpGet httpget = new HttpGet(dataUrl);
			httpget.setConfig(config);
			CloseableHttpResponse response = httpclient.execute(httpget);
			String fileName = TMP_DATA_FILE_PREFIX + CSEPARADOR + mercado + CSEPARADOR + bolsa + CSEPARADOR + indice + CSEPARADOR + ticker + CSEPARADOR + TMP_DATA_FILE_EXT;
			FileUtils.copyInputStreamToFile(response.getEntity().getContent(), new File(TMP_DATA_FILE_PATH + fileName));
			response.close();
			httpclient.close();
		}
		catch (Exception e)
		{
			LOGGER.error("Error descargando URL [" + dataUrl + "]", e);
		}
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
			LOGGER.error("Error insertando registro", e);
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
