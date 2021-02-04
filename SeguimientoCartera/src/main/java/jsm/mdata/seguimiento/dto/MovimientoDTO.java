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
public class MovimientoDTO
{

	/**
	 * 
	 */
	private Integer movimientoId = null;
	private String producto = null;
	private String compraVenta = null;
	private Date fecha = null;
	private BigDecimal numeroTitulos = null;
	private BigDecimal precioTitulo = null;
	private BigDecimal comision = null;
	private BigDecimal total = null;
	private String comercializador = null;
	private String mercado = null;

	/**
	 * @return the movimientoId
	 */
	public Integer getMovimientoId()
	{
		return movimientoId;
	}

	/**
	 * @param movimientoId the movimientoId to set
	 */
	public void setMovimientoId(Integer movimientoId)
	{
		this.movimientoId = movimientoId;
	}

	/**
	 * @return the producto
	 */
	public String getProducto()
	{
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto)
	{
		this.producto = producto;
	}

	/**
	 * @return the compraVenta
	 */
	public String getCompraVenta()
	{
		return compraVenta;
	}

	/**
	 * @param compraVenta the compraVenta to set
	 */
	public void setCompraVenta(String compraVenta)
	{
		this.compraVenta = compraVenta;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha()
	{
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}

	/**
	 * @return the numeroTitulos
	 */
	public BigDecimal getNumeroTitulos()
	{
		return numeroTitulos;
	}

	/**
	 * @param numeroTitulos the numeroTitulos to set
	 */
	public void setNumeroTitulos(BigDecimal numeroTitulos)
	{
		this.numeroTitulos = numeroTitulos;
	}

	/**
	 * @return the precioTitulo
	 */
	public BigDecimal getPrecioTitulo()
	{
		return precioTitulo;
	}

	/**
	 * @param precioTitulo the precioTitulo to set
	 */
	public void setPrecioTitulo(BigDecimal precioTitulo)
	{
		this.precioTitulo = precioTitulo;
	}

	/**
	 * @return the comision
	 */
	public BigDecimal getComision()
	{
		return comision;
	}

	/**
	 * @param comision the comision to set
	 */
	public void setComision(BigDecimal comision)
	{
		this.comision = comision;
	}

	/**
	 * @return the total
	 */
	public BigDecimal getTotal()
	{
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(BigDecimal total)
	{
		this.total = total;
	}

	/**
	 * @return the comercializador
	 */
	public String getComercializador()
	{
		return comercializador;
	}

	/**
	 * @param comercializador the comercializador to set
	 */
	public void setComercializador(String comercializador)
	{
		this.comercializador = comercializador;
	}

	/**
	 * @return the mercado
	 */
	public String getMercado()
	{
		return mercado;
	}

	/**
	 * @param mercado the mercado to set
	 */
	public void setMercado(String mercado)
	{
		this.mercado = mercado;
	}

}
