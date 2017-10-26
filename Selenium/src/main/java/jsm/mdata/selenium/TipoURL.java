/**
 * 
 */
package jsm.mdata.selenium;

/**
 * @author Empleado
 *
 */
public class TipoURL
{

	/**
	 * 
	 */
	public static final String TIPO_INDEX = "INDEX";

	/**
	 * 
	 */
	public static final String TIPO_STOCK = "STOCK";

	/**
	 * 
	 */
	public static final String TIPO_ETF = "ETF";

	/**
	 * 
	 */
	private String tipo;

	/**
	 * 
	 */
	private String url;

	/**
	 * @param tipo
	 * @param url
	 */
	public TipoURL(String tipo, String url)
	{
		super();
		this.tipo = tipo;
		this.url = url;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo()
	{
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
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
