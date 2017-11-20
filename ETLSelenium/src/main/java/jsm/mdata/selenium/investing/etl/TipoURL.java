/**
 * 
 */
package jsm.mdata.selenium.investing.etl;

/**
 * @author Empleado
 *
 */
public class TipoURL
{

	/**
	 * Mercados
	 */
	public static final String MERCADO_INDEX = "INDEX";
	public static final String MERCADO_STOCK = "STOCK";
	public static final String MERCADO_ETF = "ETF";

	/**
	 * Bolsas
	 */
	public static final String BOLSA_ESP = "ESP";
	public static final String BOLSA_GER = "GER";
	public static final String BOLSA_FRA = "FRA";
	public static final String BOLSA_USA = "USA";

	/**
	 * Indices
	 */
	public static final String INDICE_IBEX35 = "IBEX35";
	public static final String INDICE_DAX30 = "DAX30";
	public static final String INDICE_CAC40 = "CAC40";

	/**
	 * No aplica
	 */
	public static final String NA = "-";

	/**
	 * 
	 */
	private String mercado;
	private String bolsa;
	private String indice;
	private String url;

	/**
	 * @param mercado
	 * @param bolsa
	 * @param indice
	 * @param url
	 */
	public TipoURL(String mercado, String bolsa, String indice, String url)
	{
		this.mercado = mercado;
		this.bolsa = bolsa;
		this.indice = indice;
		this.url = url;
	}

	/**
	 * @return the mercado
	 */
	public String getMercado()
	{
		return mercado;
	}

	/**
	 * @param mercado
	 *            the mercado to set
	 */
	public void setMercado(String mercado)
	{
		this.mercado = mercado;
	}

	/**
	 * @return the bolsa
	 */
	public String getBolsa()
	{
		return bolsa;
	}

	/**
	 * @param bolsa
	 *            the bolsa to set
	 */
	public void setBolsa(String bolsa)
	{
		this.bolsa = bolsa;
	}

	/**
	 * @return the indice
	 */
	public String getIndice()
	{
		return indice;
	}

	/**
	 * @param indice
	 *            the indice to set
	 */
	public void setIndice(String indice)
	{
		this.indice = indice;
	}

	/**
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

}
