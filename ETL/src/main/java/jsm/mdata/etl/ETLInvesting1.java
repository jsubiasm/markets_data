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
public class ETLInvesting1 extends ETLBase
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ETLInvesting1.class);

	/**
	 * Ficheros
	 */
	private static final String DATA_URLS_FILE = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\urls\\investing1\\urls.txt";
	private static final String TMP_DATA_FILE_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\ETL\\urls\\investing1\\download\\";
	private static final SimpleDateFormat IN_FEC_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
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
			FileUtils.cleanDirectory(new File(TMP_DATA_FILE_PATH));
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
	 * @throws Exception
	 */
	private static void descargaFicherosTemporales(Connection dbConnection) throws Exception
	{
		List<String> dataUrlLines = FileUtils.readLines(new File(DATA_URLS_FILE), CHARSET);
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

}
