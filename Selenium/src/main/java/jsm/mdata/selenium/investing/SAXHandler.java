/**
 * 
 */
package jsm.mdata.selenium.investing;

import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Empleado
 *
 */
public class SAXHandler extends DefaultHandler
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(SAXHandler.class);

	/**
	 * Formatos
	 */
	private static final SimpleDateFormat FEC_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
	private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.GERMAN);

	/**
	 * XML
	 */
	private static final String XML_DATOS = "datos";
	private static final String XML_MERCADO = "mercado";
	private static final String XML_BOLSA = "bolsa";
	private static final String XML_INDICE = "indice";
	private static final String XML_TICKER = "ticker";
	private static final String XML_TABLE = "table";
	private static final String XML_THEAD = "thead";
	private static final String XML_TR = "tr";
	private static final String XML_TH = "th";
	private static final String XML_TBODY = "tbody";
	private static final String XML_TD = "td";

	/**
	 * Parser
	 */
	private boolean xmlDatos = false;
	private boolean xmlMercado = false;
	private boolean xmlBolsa = false;
	private boolean xmlIndice = false;
	private boolean xmlTicker = false;
	private boolean xmlTable = false;
	private boolean xmlThead = false;
	private boolean xmlTr = false;
	private boolean xmlTh = false;
	private boolean xmlTbody = false;
	private boolean xmlTd = false;

	/**
	 * Posicion elementos
	 */
	private int numXmlTr = 0;
	private int numXmlTd = 0;
	private int numXmlTh = 0;

	/**
	 * Activo
	 */
	private Activo activoActual = new Activo();

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			String fileName = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\Selenium\\investing\\download\\https%3A%2F%2Fes.investing.com%2Fetfs%2Flyxor-stoxx-europe-600-banks%3Fcid%3D46171.xml";
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			SAXHandler saxHandler = new SAXHandler();
			saxParser.parse(new File(fileName), saxHandler);
			LOGGER.info("--- activo ---");
			LOGGER.info("bolsa [" + saxHandler.getActivoActual().getBolsa() + "]");
			LOGGER.info("indice [" + saxHandler.getActivoActual().getIndice() + "]");
			LOGGER.info("mercado [" + saxHandler.getActivoActual().getMercado() + "]");
			LOGGER.info("ticker [" + saxHandler.getActivoActual().getTicker() + "]");
			for (Registro registro : saxHandler.getActivoActual().getListaRegistros())
			{
				LOGGER.info("--- datos ----");
				LOGGER.info("apertura [" + registro.getApertura() + "]");
				LOGGER.info("cierre [" + registro.getCierre() + "]");
				LOGGER.info("fecha [" + registro.getFecha() + "]");
				LOGGER.info("maximo [" + registro.getMaximo() + "]");
				LOGGER.info("minimo [" + registro.getMinimo() + "]");
				LOGGER.info("volumen [" + registro.getVolumen() + "]");
			}
		}
		catch (Exception e)
		{
			LOGGER.error("ERROR", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		try
		{
			// LOGGER.info("------------- startElement -------------");
			// LOGGER.info("uri [" + uri + "]");
			// LOGGER.info("localName [" + localName + "]");
			// LOGGER.info("qName [" + qName + "]");
			// LOGGER.info("----------------------------------------");
			if (qName.equalsIgnoreCase(XML_DATOS))
			{
				xmlDatos = true;
			}
			if (qName.equalsIgnoreCase(XML_MERCADO))
			{
				xmlMercado = true;
			}
			if (qName.equalsIgnoreCase(XML_BOLSA))
			{
				xmlBolsa = true;
			}
			if (qName.equalsIgnoreCase(XML_INDICE))
			{
				xmlIndice = true;
			}
			if (qName.equalsIgnoreCase(XML_TICKER))
			{
				xmlTicker = true;
			}
			if (qName.equalsIgnoreCase(XML_TABLE))
			{
				xmlTable = true;
			}
			if (qName.equalsIgnoreCase(XML_THEAD))
			{
				xmlThead = true;
			}
			if (qName.equalsIgnoreCase(XML_TR))
			{
				xmlTr = true;
				numXmlTr++;
				numXmlTh = 0;
				numXmlTd = 0;
				if (numXmlTr > 1)
				{
					activoActual.getListaRegistros().add(new Registro());
				}
			}
			if (qName.equalsIgnoreCase(XML_TH))
			{
				xmlTh = true;
				numXmlTh++;
				String dataColName = attributes.getValue("data-col-name");
				if (numXmlTh == 1 && !dataColName.equalsIgnoreCase("date"))
				{
					throw new IllegalArgumentException("Se esperaba el valor [date] y se ha recuperado [" + dataColName + "]");
				}
				else if (numXmlTh == 2 && !dataColName.equalsIgnoreCase("price"))
				{
					throw new IllegalArgumentException("Se esperaba el valor [price] y se ha recuperado [" + dataColName + "]");
				}
				else if (numXmlTh == 3 && !dataColName.equalsIgnoreCase("open"))
				{
					throw new IllegalArgumentException("Se esperaba el valor [open] y se ha recuperado [" + dataColName + "]");
				}
				else if (numXmlTh == 4 && !dataColName.equalsIgnoreCase("high"))
				{
					throw new IllegalArgumentException("Se esperaba el valor [high] y se ha recuperado [" + dataColName + "]");
				}
				else if (numXmlTh == 5 && !dataColName.equalsIgnoreCase("low"))
				{
					throw new IllegalArgumentException("Se esperaba el valor [low] y se ha recuperado [" + dataColName + "]");
				}
				else if (numXmlTh == 6 && !dataColName.equalsIgnoreCase("vol"))
				{
					throw new IllegalArgumentException("Se esperaba el valor [vol] y se ha recuperado [" + dataColName + "]");
				}
			}
			if (qName.equalsIgnoreCase(XML_TBODY))
			{
				xmlTbody = true;
			}
			if (qName.equalsIgnoreCase(XML_TD))
			{
				xmlTd = true;
				numXmlTd++;
				String dataRealValue = attributes.getValue("data-real-value");
				if (numXmlTd == 2)
				{
					activoActual.getListaRegistros().get(activoActual.getListaRegistros().size() - 1).setCierre(getBigDecimalFromString(dataRealValue));
				}
				else if (numXmlTd == 3)
				{
					activoActual.getListaRegistros().get(activoActual.getListaRegistros().size() - 1).setApertura(getBigDecimalFromString(dataRealValue));
				}
				else if (numXmlTd == 4)
				{
					activoActual.getListaRegistros().get(activoActual.getListaRegistros().size() - 1).setMaximo(getBigDecimalFromString(dataRealValue));
				}
				else if (numXmlTd == 5)
				{
					activoActual.getListaRegistros().get(activoActual.getListaRegistros().size() - 1).setMinimo(getBigDecimalFromString(dataRealValue));
				}
				else if (numXmlTd == 6)
				{
					activoActual.getListaRegistros().get(activoActual.getListaRegistros().size() - 1).setVolumen(getBigDecimalFromString(dataRealValue));
				}
			}
		}
		catch (Exception e)
		{
			throw new SAXException("ERROR", e);
		}
	}

	/**
	 * @param numericString
	 * @return
	 */
	private BigDecimal getBigDecimalFromString(String numericString)
	{
		BigDecimal theBigDecimal = null;
		try
		{
			theBigDecimal = new BigDecimal(NUMBER_FORMAT.parse(numericString).toString());
		}
		catch (Exception e)
		{
			LOGGER.error("No se ha podido parsear el valor [" + numericString + "] y se sustituye por un cero", e);
			theBigDecimal = BigDecimal.ZERO;
		}
		return theBigDecimal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		// LOGGER.info("-------------- endElement --------------");
		// LOGGER.info("uri [" + uri + "]");
		// LOGGER.info("localName [" + localName + "]");
		// LOGGER.info("qName [" + qName + "]");
		// LOGGER.info("----------------------------------------");
		if (qName.equalsIgnoreCase(XML_DATOS))
		{
			xmlDatos = false;
		}
		if (qName.equalsIgnoreCase(XML_MERCADO))
		{
			xmlMercado = false;
		}
		if (qName.equalsIgnoreCase(XML_BOLSA))
		{
			xmlBolsa = false;
		}
		if (qName.equalsIgnoreCase(XML_INDICE))
		{
			xmlIndice = false;
		}
		if (qName.equalsIgnoreCase(XML_TICKER))
		{
			xmlTicker = false;
		}
		if (qName.equalsIgnoreCase(XML_TABLE))
		{
			xmlTable = false;
		}
		if (qName.equalsIgnoreCase(XML_THEAD))
		{
			xmlThead = false;
		}
		if (qName.equalsIgnoreCase(XML_TR))
		{
			xmlTr = false;
		}
		if (qName.equalsIgnoreCase(XML_TH))
		{
			xmlTh = false;
		}
		if (qName.equalsIgnoreCase(XML_TBODY))
		{
			xmlTbody = false;
		}
		if (qName.equalsIgnoreCase(XML_TD))
		{
			xmlTd = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		try
		{
			// LOGGER.info("-------------- characters --------------");
			// LOGGER.info("start [" + start + "]");
			// LOGGER.info("length [" + length + "]");
			// LOGGER.info("ch [" + new String(ch, start, length) + "]");
			// LOGGER.info("----------------------------------------");
			if (xmlDatos)
			{
				// No hacemos nada
			}
			if (xmlMercado)
			{
				activoActual.setMercado(new String(ch, start, length));
			}
			if (xmlBolsa)
			{
				activoActual.setBolsa(new String(ch, start, length));
			}
			if (xmlIndice)
			{
				activoActual.setIndice(new String(ch, start, length));
			}
			if (xmlTicker)
			{
				activoActual.setTicker(new String(ch, start, length));
			}
			if (xmlTable)
			{
				// No hacemos nada
			}
			if (xmlThead)
			{
				// No hacemos nada
			}
			if (xmlTr)
			{
				// No hacemos nada
			}
			if (xmlTh)
			{
				// No hacemos nada
			}
			if (xmlTbody)
			{
				// No hacemos nada
			}
			if (xmlTd)
			{
				if (numXmlTd == 1)
				{
					activoActual.getListaRegistros().get(activoActual.getListaRegistros().size() - 1).setFecha(FEC_FORMAT.parse(new String(ch, start, length)));
				}
			}
		}
		catch (Exception e)
		{
			throw new SAXException("ERROR", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException
	{
		checkBooleanValues();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException
	{
		checkBooleanValues();
	}

	/**
	 * 
	 */
	private void checkBooleanValues()
	{
		if (xmlDatos || xmlMercado || xmlBolsa || xmlIndice || xmlTicker || xmlTable || xmlThead || xmlTr || xmlTh || xmlTbody || xmlTd)
		{
			throw new IllegalStateException("Todos los valores booleanos del parser deben estar a false");
		}
	}

	/**
	 * @return the activoActual
	 */
	public Activo getActivoActual()
	{
		return activoActual;
	}

	/**
	 * @param activoActual
	 *            the activoActual to set
	 */
	public void setActivoActual(Activo activoActual)
	{
		this.activoActual = activoActual;
	}

}
