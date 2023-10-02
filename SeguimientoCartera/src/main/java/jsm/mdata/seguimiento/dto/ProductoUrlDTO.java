/**
 * 
 */
package jsm.mdata.seguimiento.dto;

/**
 * @author jsubiasm
 *
 */
public class ProductoUrlDTO
{

	/**
	 * 
	 */
	private String productoId = null;
	private String urlScraping = null;

	/**
	 * @return the productoId
	 */
	public String getProductoId()
	{
		return productoId;
	}

	/**
	 * @param productoId the productoId to set
	 */
	public void setProductoId(String productoId)
	{
		this.productoId = productoId;
	}

	/**
	 * @return the urlScraping
	 */
	public String getUrlScraping()
	{
		return urlScraping;
	}

	/**
	 * @param urlScraping the urlScraping to set
	 */
	public void setUrlScraping(String urlScraping)
	{
		this.urlScraping = urlScraping;
	}

}
