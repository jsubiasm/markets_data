/**
 * 
 */
package jsm.mdata.seguimiento.dto;

import java.math.BigDecimal;

/**
 * @author jsubiasm
 *
 */
public class GanPerProdPesoDTO
{

	/**
	 * 
	 */
	private String productoId = null;
	private String nombre = null;
	private BigDecimal titulosComprados = null;
	private BigDecimal precioTitulosComprados = null;
	private BigDecimal titulosVendidos = null;
	private BigDecimal precioTitulosVendidos = null;
	private BigDecimal titulosActuales = null;
	private BigDecimal valorTitulo = null;
	private BigDecimal valorTitulosActuales = null;
	private BigDecimal gananciaPerdida = null;
	private BigDecimal gananciaPerdidaPrcnt = null;
	private BigDecimal pesoEnCartera = null;

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
	 * @return the nombre
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * @return the titulosComprados
	 */
	public BigDecimal getTitulosComprados()
	{
		return titulosComprados;
	}

	/**
	 * @param titulosComprados the titulosComprados to set
	 */
	public void setTitulosComprados(BigDecimal titulosComprados)
	{
		this.titulosComprados = titulosComprados;
	}

	/**
	 * @return the precioTitulosComprados
	 */
	public BigDecimal getPrecioTitulosComprados()
	{
		return precioTitulosComprados;
	}

	/**
	 * @param precioTitulosComprados the precioTitulosComprados to set
	 */
	public void setPrecioTitulosComprados(BigDecimal precioTitulosComprados)
	{
		this.precioTitulosComprados = precioTitulosComprados;
	}

	/**
	 * @return the titulosVendidos
	 */
	public BigDecimal getTitulosVendidos()
	{
		return titulosVendidos;
	}

	/**
	 * @param titulosVendidos the titulosVendidos to set
	 */
	public void setTitulosVendidos(BigDecimal titulosVendidos)
	{
		this.titulosVendidos = titulosVendidos;
	}

	/**
	 * @return the precioTitulosVendidos
	 */
	public BigDecimal getPrecioTitulosVendidos()
	{
		return precioTitulosVendidos;
	}

	/**
	 * @param precioTitulosVendidos the precioTitulosVendidos to set
	 */
	public void setPrecioTitulosVendidos(BigDecimal precioTitulosVendidos)
	{
		this.precioTitulosVendidos = precioTitulosVendidos;
	}

	/**
	 * @return the titulosActuales
	 */
	public BigDecimal getTitulosActuales()
	{
		return titulosActuales;
	}

	/**
	 * @param titulosActuales the titulosActuales to set
	 */
	public void setTitulosActuales(BigDecimal titulosActuales)
	{
		this.titulosActuales = titulosActuales;
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
	 * @return the valorTitulosActuales
	 */
	public BigDecimal getValorTitulosActuales()
	{
		return valorTitulosActuales;
	}

	/**
	 * @param valorTitulosActuales the valorTitulosActuales to set
	 */
	public void setValorTitulosActuales(BigDecimal valorTitulosActuales)
	{
		this.valorTitulosActuales = valorTitulosActuales;
	}

	/**
	 * @return the gananciaPerdida
	 */
	public BigDecimal getGananciaPerdida()
	{
		return gananciaPerdida;
	}

	/**
	 * @param gananciaPerdida the gananciaPerdida to set
	 */
	public void setGananciaPerdida(BigDecimal gananciaPerdida)
	{
		this.gananciaPerdida = gananciaPerdida;
	}

	/**
	 * @return the gananciaPerdidaPrcnt
	 */
	public BigDecimal getGananciaPerdidaPrcnt()
	{
		return gananciaPerdidaPrcnt;
	}

	/**
	 * @param gananciaPerdidaPrcnt the gananciaPerdidaPrcnt to set
	 */
	public void setGananciaPerdidaPrcnt(BigDecimal gananciaPerdidaPrcnt)
	{
		this.gananciaPerdidaPrcnt = gananciaPerdidaPrcnt;
	}

	/**
	 * @return the pesoEnCartera
	 */
	public BigDecimal getPesoEnCartera()
	{
		return pesoEnCartera;
	}

	/**
	 * @param pesoEnCartera the pesoEnCartera to set
	 */
	public void setPesoEnCartera(BigDecimal pesoEnCartera)
	{
		this.pesoEnCartera = pesoEnCartera;
	}

}
