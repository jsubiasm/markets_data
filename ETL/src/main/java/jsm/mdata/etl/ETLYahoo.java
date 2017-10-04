/**
 * 
 */
package jsm.mdata.etl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

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
public class ETLYahoo
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ETLYahoo.class);

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
	private static final String DATA_URLS_FILE = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\consultas\\yahoo\\consultas.txt";
	private static final String TMP_DATA_FILE_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\consultas\\yahoo\\download\\";
	private static final String TMP_DATA_FILE_PREFIX = "_data_file_";

	/**
	 * Formatos fichero datos
	 */
	private static final SimpleDateFormat DATA_FILE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");

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
		try
		{
			// Leemos el fichero de URLs de datos
			LOGGER.info("Leyendo fichero de URLs de datos");
			List<String> dataUrlList = FileUtils.readLines(new File(DATA_URLS_FILE));

			// Descargamos las URLs de datos y las guardamos en ficheros en
			// disco
			int index = 100;
			for (String dataUrl : dataUrlList)
			{
				index++;
				LOGGER.info("Descargando URL [" + dataUrl + "]");
				if (USE_PROXY)
				{
					descargarFicheroConProxy(dataUrl, index);
				}
				else
				{
					descargarFicheroSinProxy(dataUrl, index);
				}
			}

			// Leemos los ficheros de datos descargados
			Collection<File> dataFileList = FileUtils.listFiles(new File(TMP_DATA_FILE_PATH), null, null);
			for (File dataFile : dataFileList)
			{

			}

			LOGGER.info("Abriendo conexión a base de datos");
			Connection connection = getConnection();
		}
		catch (Exception e)
		{
			LOGGER.error("ERROR", e);
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
	private static void descargarFicheroConProxy(String dataUrl, int index)
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
			FileUtils.copyInputStreamToFile(response.getEntity().getContent(), new File(TMP_DATA_FILE_PATH + TMP_DATA_FILE_PREFIX + index));
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
	private static void descargarFicheroSinProxy(String dataUrl, int index)
	{
		throw new UnsupportedOperationException("Operación no implementada");
	}

}
