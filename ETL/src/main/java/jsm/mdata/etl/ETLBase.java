/**
 * 
 */
package jsm.mdata.etl;

import java.io.File;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

import org.apache.commons.io.FileUtils;
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
public class ETLBase
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ETLBase.class);

	/**
	 * Conexiones a URLs
	 */
	protected static final Boolean USE_PROXY = true;
	protected static final String PROXY_URL = "192.6.2.109";
	protected static final Integer PROXY_PORT = 81;
	protected static final String PROXY_USERNAME = "panda";
	protected static final String PROXY_PASSWORD = "panda";
	protected static final int URL_TIMEOUT = 15 * 1000;
	protected static final int MAX_INTENTOS = 3;
	protected static final int DOWNLOAD_DELAY = 500;
	protected static final String USER_AGENT = "Mozilla/5.0 Firefox/26.0";

	/**
	 * Ficheros
	 */
	protected static final String TMP_DATA_FILE_PREFIX = "_data_file_";
	protected static final String TMP_DATA_FILE_EXT = ".txt";
	protected static final String C_SEPARADOR = "##CSEP##";
	protected static final String C_COMENT = "--";
	protected static final String CHARSET = "UTF-8";

	/**
	 * Conexión a base de datos
	 */
	protected static final String DATABASE_DRIVER = "org.postgresql.Driver";
	protected static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
	protected static final String DATABASE_USERNAME = "Empleado";
	protected static final String DATABASE_PASSWORD = "Empleado";

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
	 * @param tmpDataFilePath
	 * @param mercado
	 * @param bolsa
	 * @param indice
	 * @param ticker
	 * @param dataUrl
	 * @throws Exception
	 */
	protected static void descargarFicheroConProxy(String tmpDataFilePath, String mercado, String bolsa, String indice, String ticker, String dataUrl) throws Exception
	{
		int intentos = 0;
		boolean retry = false;
		do
		{
			try
			{
				Thread.sleep(DOWNLOAD_DELAY);
				intentos++;
				CredentialsProvider credsProvider = new BasicCredentialsProvider();
				credsProvider.setCredentials(new AuthScope(PROXY_URL, PROXY_PORT), new UsernamePasswordCredentials(PROXY_USERNAME, PROXY_PASSWORD));
				CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).setUserAgent(USER_AGENT).build();
				HttpHost proxy = new HttpHost(PROXY_URL, PROXY_PORT);
				RequestConfig config = RequestConfig.custom().setConnectTimeout(URL_TIMEOUT).setProxy(proxy).build();
				HttpGet httpGET = new HttpGet(dataUrl);
				httpGET.setConfig(config);
				CloseableHttpResponse response = httpClient.execute(httpGET);
				String fileName = TMP_DATA_FILE_PREFIX + C_SEPARADOR + mercado + C_SEPARADOR + bolsa + C_SEPARADOR + indice + C_SEPARADOR + URLEncoder.encode(ticker, CHARSET) + C_SEPARADOR + TMP_DATA_FILE_EXT;
				FileUtils.copyInputStreamToFile(response.getEntity().getContent(), new File(tmpDataFilePath + fileName));
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
	 * @param tmpDataFilePath
	 * @param mercado
	 * @param bolsa
	 * @param indice
	 * @param ticker
	 * @param dataUrl
	 */
	protected static void descargarFicheroSinProxy(String tmpDataFilePath, String mercado, String bolsa, String indice, String ticker, String dataUrl)
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
	protected static void insertaRegistro(Connection dbConnection, String mercado, String bolsa, String indice, String ticker, Date fecha, BigDecimal maximo, BigDecimal minimo, BigDecimal cierre, BigDecimal volumen) throws Exception
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

}
