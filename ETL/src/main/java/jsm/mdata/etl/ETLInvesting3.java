/**
 * 
 */
package jsm.mdata.etl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	private static final String DOWNLOAD_ROOT_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\urls\\investing3\\download\\";
	private static final String LIST_URLS_FILE = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\urls\\investing3\\list_urls.txt";
	private static final String LIST_TEMP_PATH = "list_tmp\\";
	private static final String LIST_PROCESADO_PATH = "list_procesado\\";
	private static final String HIST_TEMP_PATH = "historico_tmp\\";
	private static final SimpleDateFormat IN_FEC_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
	private static final SimpleDateFormat OUT_FEC_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.GERMAN);

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
			FileUtils.cleanDirectory(new File(DOWNLOAD_ROOT_PATH + LIST_TEMP_PATH));
			FileUtils.cleanDirectory(new File(DOWNLOAD_ROOT_PATH + LIST_PROCESADO_PATH));
			FileUtils.cleanDirectory(new File(DOWNLOAD_ROOT_PATH + HIST_TEMP_PATH));
			LOGGER.info("Descargando listados de elementos");
			descargaFicherosListados();
			LOGGER.info("Procesando listados de elementos");
			procesoFicherosListados();
			LOGGER.info("Descargando datos historicos de elementos");
			descargaFicherosHistorico();
			LOGGER.info("Procesando datos historicos de elementos");
			procesoFicherosHistorico(dbConnection);
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
	private static void descargaFicherosListados() throws Exception
	{
		descargaURLs(new File(LIST_URLS_FILE), DOWNLOAD_ROOT_PATH + LIST_TEMP_PATH);
	}

	/**
	 * @param downloadPath
	 * @throws Exception
	 */
	private static void descargaFicherosHistorico() throws Exception
	{
		Collection<File> listUrlsFile = FileUtils.listFiles(new File(DOWNLOAD_ROOT_PATH + LIST_PROCESADO_PATH), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File urlsFile : listUrlsFile)
		{
			descargaURLs(urlsFile, DOWNLOAD_ROOT_PATH + HIST_TEMP_PATH);
		}
	}

	/**
	 * @throws Exception
	 */
	private static void procesoFicherosListados() throws Exception
	{
		Collection<File> dataFileList = FileUtils.listFiles(new File(DOWNLOAD_ROOT_PATH + LIST_TEMP_PATH), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File dataFile : dataFileList)
		{
			LOGGER.info("Procesando fichero [" + dataFile.getName() + "]");
			String[] dataFileTokens = dataFile.getName().split(C_SEPARADOR);
			String mercado = dataFileTokens[1];
			String bolsa = dataFileTokens[2];
			String indice = dataFileTokens[3];
			List<String> dataFileLines = FileUtils.readLines(dataFile, CHARSET);
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
			File urlsFile = new File(DOWNLOAD_ROOT_PATH + LIST_PROCESADO_PATH + dataFile.getName());
			FileUtils.writeLines(urlsFile, CHARSET, newDataFileLines);
			List<String> urlsFileLines = FileUtils.readLines(urlsFile, CHARSET);
			List<String> newUrlsFileLines = new ArrayList<String>();
			for (String urlFileLine : urlsFileLines)
			{
				urlFileLine = urlFileLine.substring(0, urlFileLine.indexOf("\"  title=\""));
				String etfName = urlFileLine;
				etfName = etfName.replaceAll("/etfs/", "").replaceAll("£", "_").replaceAll("\\$", "_").replaceAll("€", "_").replaceAll("\\?", "_");
				if (urlFileLine.indexOf("?cid=") != -1)
				{
					urlFileLine = urlFileLine.replaceAll("\\?cid=", "-historical-data?cid=");
				}
				else
				{
					urlFileLine = urlFileLine + "-historical-data";
				}
				urlFileLine = urlFileLine.replaceAll("£", "%C2%A3").replaceAll("\\$", "%24").replaceAll("€", "%E2%82%AC");
				urlFileLine = mercado + C_SEPARADOR + bolsa + C_SEPARADOR + indice + C_SEPARADOR + etfName + C_SEPARADOR + "https://es.investing.com" + urlFileLine;
				newUrlsFileLines.add(urlFileLine);
			}
			FileUtils.writeLines(urlsFile, CHARSET, newUrlsFileLines);
		}
	}

	/**
	 * @param downloadPath
	 * @param dbConnection
	 * @throws Exception
	 */
	private static void procesoFicherosHistorico(Connection dbConnection) throws Exception
	{
		Collection<File> dataFileList = FileUtils.listFiles(new File(DOWNLOAD_ROOT_PATH + HIST_TEMP_PATH), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File dataFile : dataFileList)
		{
			LOGGER.info("Procesando fichero [" + dataFile.getName() + "]");
			String[] dataFileTokens = dataFile.getName().split(C_SEPARADOR);
			String mercado = dataFileTokens[1];
			String bolsa = dataFileTokens[2];
			String indice = dataFileTokens[3];
			String ticker = dataFileTokens[4];
			List<String> dataFileLines = FileUtils.readLines(dataFile, CHARSET);
			List<String> newDataFileLines = new ArrayList<String>();
			for (String dataFileLine : dataFileLines)
			{
				if (dataFileLine.indexOf("data-col-name=\"") != -1 || dataFileLine.indexOf("data-real-value=\"") != -1)
				{
					if (dataFileLine.indexOf("data-col-name=\"") != -1)
					{
						dataFileLine = dataFileLine.substring(dataFileLine.indexOf("data-col-name=\"") + "data-col-name=\"".length(), dataFileLine.indexOf("\" class=\""));
					}
					if (dataFileLine.indexOf("class=\"first left bold noWrap\">") != -1)
					{
						dataFileLine = dataFileLine.substring(dataFileLine.indexOf("class=\"first left bold noWrap\">") + "class=\"first left bold noWrap\">".length(), dataFileLine.indexOf("</td>"));
					}
					if (dataFileLine.indexOf("\" class=\"greenFont\"") != -1)
					{
						if (dataFileLine.indexOf("data-real-value=\"") != -1)
						{
							dataFileLine = dataFileLine.substring(dataFileLine.indexOf("data-real-value=\"") + "data-real-value=\"".length(), dataFileLine.indexOf("\" class=\"greenFont\""));
						}
					}
					else if (dataFileLine.indexOf("\" class=\"redFont\"") != -1)
					{
						if (dataFileLine.indexOf("data-real-value=\"") != -1)
						{
							dataFileLine = dataFileLine.substring(dataFileLine.indexOf("data-real-value=\"") + "data-real-value=\"".length(), dataFileLine.indexOf("\" class=\"redFont\""));
						}
					}
					else
					{
						if (dataFileLine.indexOf("data-real-value=\"") != -1)
						{
							dataFileLine = dataFileLine.substring(dataFileLine.indexOf("data-real-value=\"") + "data-real-value=\"".length(), dataFileLine.indexOf("\">"));
						}
					}
					newDataFileLines.add(dataFileLine);
				}
			}
			List<Object[]> listaRegistros = new ArrayList<Object[]>();
			int lineIndex = 0;
			for (String newDataFileLine : newDataFileLines)
			{
				if (lineIndex % 6 == 0)
				{
					listaRegistros.add(lineIndex / 6, new Object[6]);
				}
				if ((lineIndex / 6) == 0)
				{
					if ((lineIndex % 6) == 0 && !newDataFileLine.equalsIgnoreCase("date"))
					{
						throw new IllegalArgumentException("Se esperaba el valor [date] y se ha recuperado [" + newDataFileLine + "]");
					}
					else if ((lineIndex % 6) == 1 && !newDataFileLine.equalsIgnoreCase("price"))
					{
						throw new IllegalArgumentException("Se esperaba el valor [price] y se ha recuperado [" + newDataFileLine + "]");
					}
					else if ((lineIndex % 6) == 2 && !newDataFileLine.equalsIgnoreCase("open"))
					{
						throw new IllegalArgumentException("Se esperaba el valor [open] y se ha recuperado [" + newDataFileLine + "]");
					}
					else if ((lineIndex % 6) == 3 && !newDataFileLine.equalsIgnoreCase("high"))
					{
						throw new IllegalArgumentException("Se esperaba el valor [high] y se ha recuperado [" + newDataFileLine + "]");
					}
					else if ((lineIndex % 6) == 4 && !newDataFileLine.equalsIgnoreCase("low"))
					{
						throw new IllegalArgumentException("Se esperaba el valor [low] y se ha recuperado [" + newDataFileLine + "]");
					}
					else if ((lineIndex % 6) == 5 && !newDataFileLine.equalsIgnoreCase("vol"))
					{
						throw new IllegalArgumentException("Se esperaba el valor [vol] y se ha recuperado [" + newDataFileLine + "]");
					}
					listaRegistros.get(lineIndex / 6)[lineIndex % 6] = newDataFileLine;
				}
				else
				{
					if ((lineIndex % 6) == 0)
					{
						listaRegistros.get(lineIndex / 6)[lineIndex % 6] = IN_FEC_FORMAT.parse(newDataFileLine);
					}
					else if ((lineIndex % 6) == 1)
					{
						listaRegistros.get(lineIndex / 6)[lineIndex % 6] = new BigDecimal(NUMBER_FORMAT.parse(newDataFileLine).toString());
					}
					else if ((lineIndex % 6) == 2)
					{
						listaRegistros.get(lineIndex / 6)[lineIndex % 6] = new BigDecimal(NUMBER_FORMAT.parse(newDataFileLine).toString());
					}
					else if ((lineIndex % 6) == 3)
					{
						listaRegistros.get(lineIndex / 6)[lineIndex % 6] = new BigDecimal(NUMBER_FORMAT.parse(newDataFileLine).toString());
					}
					else if ((lineIndex % 6) == 4)
					{
						listaRegistros.get(lineIndex / 6)[lineIndex % 6] = new BigDecimal(NUMBER_FORMAT.parse(newDataFileLine).toString());
					}
					else if ((lineIndex % 6) == 5)
					{
						listaRegistros.get(lineIndex / 6)[lineIndex % 6] = new BigDecimal(NUMBER_FORMAT.parse(newDataFileLine).toString());
					}
				}
				lineIndex++;
			}
			int registroIndex = 0;
			for (Object[] registro : listaRegistros)
			{
				if (registroIndex != 0)
				{
					if (!existeRegistro(dbConnection, mercado, bolsa, indice, ticker, (Date) registro[0]))
					{
						insertaRegistro(dbConnection, mercado, bolsa, indice, ticker, (Date) registro[0], (BigDecimal) registro[3], (BigDecimal) registro[4], (BigDecimal) registro[1], (BigDecimal) registro[5]);
						LOGGER.info("Insertado registro [" + mercado + "] [" + bolsa + "] [" + indice + "] [" + ticker + "] [" + OUT_FEC_FORMAT.format(registro[0]) + "] [" + registro[3] + "] [" + registro[4] + "] [" + registro[1] + "] [" + registro[5] + "]");
					}
					else
					{
						LOGGER.info("NO INSERTADO: El registro [" + mercado + "] [" + bolsa + "] [" + indice + "] [" + ticker + "] [" + OUT_FEC_FORMAT.format(registro[0]) + "] ya existe");
					}
				}
				registroIndex++;
			}
			LOGGER.info("Confirmando transacción");
			dbConnection.commit();
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
			String consultaSQL = "select count(1) as existe from public.mercados where mercado = ? and bolsa = ? and indice = ? and ticker = ? and fecha = ?";
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
	 * @param urlsFile
	 * @param downloadPath
	 * @throws Exception
	 */
	private static void descargaURLs(File urlsFile, String downloadPath) throws Exception
	{
		List<String> dataUrlLines = FileUtils.readLines(urlsFile, CHARSET);
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

}
