/**
 * 
 */
package jsm.mdata.etl;

import java.io.File;
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
public class ETLInvesting2 extends ETLBase
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ETLInvesting2.class);

	/**
	 * Ficheros
	 */
	private static final String DATA_URLS_FILE = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\urls\\investing2\\urls.txt";
	private static final String TMP_DATA_FILE_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\urls\\investing2\\download\\";
	private static final String C_COMENT = "--";
	private static final String C_SEPARADOR = "##CSEP##";

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			LOGGER.info("Suprimiendo ficheros temporales antiguos");
			FileUtils.cleanDirectory(new File(TMP_DATA_FILE_PATH));
			LOGGER.info("Descargando ficheros temporales");
			descargaFicherosTemporales();
			LOGGER.info("Procesando ficheros temporales");
			procesoFicherosTemporales();
		}
		catch (Exception e1)
		{
			LOGGER.error("ERROR", e1);
		}
	}

	/**
	 * @throws Exception
	 */
	private static void descargaFicherosTemporales() throws Exception
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
				LOGGER.info("Descargando URL [" + dataUrl + "]");
				if (USE_PROXY)
				{
					descargarFicheroConProxy(TMP_DATA_FILE_PATH, mercado, bolsa, indice, ticker, dataUrl);
				}
				else
				{
					descargarFicheroSinProxy(TMP_DATA_FILE_PATH, mercado, bolsa, indice, ticker, dataUrl);
				}
			}
		}
	}

	/**
	 * @throws Exception
	 */
	private static void procesoFicherosTemporales() throws Exception
	{
		Collection<File> dataFileList = FileUtils.listFiles(new File(TMP_DATA_FILE_PATH), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
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
			File urlsFile = new File(dataFile.getAbsolutePath() + ".urls.txt");
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
