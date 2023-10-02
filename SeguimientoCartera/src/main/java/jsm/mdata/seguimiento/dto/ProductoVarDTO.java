/**
 * 
 */
package jsm.mdata.seguimiento.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jsubiasm
 *
 */
public class ProductoVarDTO
{

	/**
	 * 
	 */
	private String productoId = null;
	private BigDecimal valorTitulo = null;
	private Date fechaValor = null;
	private BigDecimal ter = null;
	private Date fechaTer = null;

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
	 * @return the valorTitulo
	 */
	public BigDecimal getValorTitulo()
	{
		return valorTitulo;
	}

	/**
	 * @param valorTitulo the valorTitulo to set
	 */
	public void setValorTitulo(BigDecimal valorTitulo)
	{
		this.valorTitulo = valorTitulo;
	}

	/**
	 * @return the fechaValor
	 */
	public Date getFechaValor()
	{
		return fechaValor;
	}

	/**
	 * @param fechaValor the fechaValor to set
	 */
	public void setFechaValor(Date fechaValor)
	{
		this.fechaValor = fechaValor;
	}

	/**
	 * @return the ter
	 */
	public BigDecimal getTer()
	{
		return ter;
	}

	/**
	 * @param ter the ter to set
	 */
	public void setTer(BigDecimal ter)
	{
		this.ter = ter;
	}

	/**
	 * @return the fechaTer
	 */
	public Date getFechaTer()
	{
		return fechaTer;
	}

	/**
	 * @param fechaTer the fechaTer to set
	 */
	public void setFechaTer(Date fechaTer)
	{
		this.fechaTer = fechaTer;
	}

}
