/**
 * 
 */
package jsm.mdata.etl;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Empleado
 *
 */
public class ETLInvesting3 extends ETLBase
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ETLInvesting3.class);

	/**
	 * Ficheros
	 */
	private static final String C_COMENT = "--";
	private static final String DOWNLOAD_ROOT_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\urls\\investing3\\download";
	private static final String ALL_ETFS_URLS_FILE = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\urls\\investing3\\all_etfs_urls.txt";
	private static final String ALL_ETFS_FILE_PATH = "\\all_etfs";
	private static final String ALL_ETFS_TEMP_PATH = "\\all_etfs_tmp";

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Connection dbConnection = null;
		try
		{
			LOGGER.info("Abriendo conexión a base de datos");
			dbConnection = getConnection();
			dbConnection.setAutoCommit(false);
			LOGGER.info("Suprimiendo ficheros temporales antiguos");
			FileUtils.cleanDirectory(new File(DOWNLOAD_ROOT_PATH + ALL_ETFS_FILE_PATH));
			FileUtils.cleanDirectory(new File(DOWNLOAD_ROOT_PATH + ALL_ETFS_TEMP_PATH));
			FileUtils.cleanDirectory(new File(DOWNLOAD_ROOT_PATH));
			LOGGER.info("Descargando listados de ETFs");
			descargaFicherosTemporales(ALL_ETFS_URLS_FILE, DOWNLOAD_ROOT_PATH + ALL_ETFS_FILE_PATH);
			LOGGER.info("Procesando listados de ETFs");
			procesoFicherosAllETFs();
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
	 * @param urlsFile
	 * @param downloadPath
	 * @throws Exception
	 */
	private static void descargaFicherosTemporales(String urlsFile, String downloadPath) throws Exception
	{
		List<String> dataUrlLines = FileUtils.readLines(new File(urlsFile));
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
				LOGGER.info("Descargando URL [" + dataUrl + "]");
				if (USE_PROXY)
				{
					descargarFicheroConProxy(downloadPath, mercado, bolsa, indice, ticker, dataUrl);
				}
				else
				{
					descargarFicheroSinProxy(downloadPath, mercado, bolsa, indice, ticker, dataUrl);
				}
			}
		}
	}

	/**
	 * @throws Exception
	 */
	private static void procesoFicherosAllETFs() throws Exception
	{
		Collection<File> dataFileList = FileUtils.listFiles(new File(DOWNLOAD_ROOT_PATH + ALL_ETFS_FILE_PATH), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File dataFile : dataFileList)
		{
			LOGGER.info("Procesando fichero [" + dataFile.getName() + "]");
			String[] dataFileTokens = dataFile.getName().split(C_SEPARADOR);
			String mercado = dataFileTokens[1];
			String bolsa = dataFileTokens[2];
			String indice = dataFileTokens[3];
			List<String> dataFileLines = FileUtils.readLines(dataFile);
			List<String> newDataFileLines = new ArrayList<String>();
			for (String dataFileLine : dataFileLines)
			{
				if (dataFileLine.indexOf("plusIconTd\"><a href=\"/etfs/") != -1)
				{

					dataFileLine = dataFileLine.substring(dataFileLine.indexOf("plusIconTd\"><a href=\"") + "plusIconTd\"><a href=\"".length());
					dataFileLine = dataFileLine.replaceAll("plusIconTd\"><a href=\"", "\n");
					newDataFileLines.add(dataFileLine);
				}
			}
			File urlsFile = new File(DOWNLOAD_ROOT_PATH + ALL_ETFS_TEMP_PATH + dataFile.getName());
			FileUtils.writeLines(urlsFile, newDataFileLines);
			List<String> urlsFileLines = FileUtils.readLines(urlsFile);
			List<String> newUrlsFileLines = new ArrayList<String>();
			for (String urlFileLine : urlsFileLines)
			{
				urlFileLine = urlFileLine.substring(0, urlFileLine.indexOf("\"  title=\""));
				String etfName = urlFileLine;
				etfName = etfName.replaceAll("\\?", "-").replaceAll("/etfs/", "");
				if (urlFileLine.indexOf("?cid=") != -1)
				{
					urlFileLine = urlFileLine.replaceAll("\\?cid=", "-historical-data?cid=");
				}
				else
				{
					urlFileLine = urlFileLine + "-historical-data";
				}
				urlFileLine = mercado + C_SEPARADOR + bolsa + C_SEPARADOR + indice + C_SEPARADOR + etfName + C_SEPARADOR + "https://es.investing.com" + urlFileLine;
				newUrlsFileLines.add(urlFileLine);
			}
			FileUtils.writeLines(urlsFile, newUrlsFileLines);
		}
	}

}
