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
public class PrecioDTO
{

	/**
	 * 
	 */
	private String productoId = null;
	private BigDecimal valorTitulo = null;
	private Date fechaValor = null;
	private Date ultimaActualizacion = null;

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
	 * @return the ultimaActualizacion
	 */
	public Date getUltimaActualizacion()
	{
		return ultimaActualizacion;
	}

	/**
	 * @param ultimaActualizacion the ultimaActualizacion to set
	 */
	public void setUltimaActualizacion(Date ultimaActualizacion)
	{
		this.ultimaActualizacion = ultimaActualizacion;
	}

}
