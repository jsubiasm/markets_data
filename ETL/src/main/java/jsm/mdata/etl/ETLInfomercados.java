/**
 * 
 */
package jsm.mdata.etl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class ETLInfomercados
{

	/**
	 * Ficheros
	 */
	// http://www.infomercados.com
	private static final String DATA_URLS_FILE = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\consultas\\infomercados\\consultas.txt";
	private static final String TMP_DATA_FILE_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\consultas\\infomercados\\";
	private static final String TMP_DATA_FILE_PREFIX = "_data_file_";

	/**
	 * Conexión a base de datos
	 */
	private static final String DATABASE_DRIVER = "org.postgresql.Driver";
	private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String DATABASE_USERNAME = "Empleado";
	private static final String DATABASE_PASSWORD = "Empleado";

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ETLInfomercados.class);

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

			int index = 100;
			for (String dataUrl : dataUrlList)
			{
				index++;

				// Leemos el fichero de URLs de datos
				LOGGER.info("Descargando URL [" + dataUrl + "]");
				descargarFichero(dataUrl, index);

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
	public static Connection getConnection() throws Exception
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
	public static void descargarFichero(String dataUrl, int index)
	{
		try
		{
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(new AuthScope("newproxy", 81), new UsernamePasswordCredentials("panda", "panda"));
			CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
			HttpHost proxy = new HttpHost("newproxy", 81);
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

}
