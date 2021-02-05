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

	private String productoId = null;
	private String nombre = null;
	private String comercializador = null;
	private String mercado = null;
	private String proveedor = null;
	private String instrumento = null;
	private String tipoActivo = null;
	private String subtipoActivo = null;
	private String moneda = null;
	private String usoIngresos = null;
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

	/**
	 * @return the proveedor
	 */
	public String getProveedor()
	{
		return proveedor;
	}

	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(String proveedor)
	{
		this.proveedor = proveedor;
	}

	/**
	 * @return the instrumento
	 */
	public String getInstrumento()
	{
		return instrumento;
	}

	/**
	 * @param instrumento the instrumento to set
	 */
	public void setInstrumento(String instrumento)
	{
		this.instrumento = instrumento;
	}

	/**
	 * @return the tipoActivo
	 */
	public String getTipoActivo()
	{
		return tipoActivo;
	}

	/**
	 * @param tipoActivo the tipoActivo to set
	 */
	public void setTipoActivo(String tipoActivo)
	{
		this.tipoActivo = tipoActivo;
	}

	/**
	 * @return the subtipoActivo
	 */
	public String getSubtipoActivo()
	{
		return subtipoActivo;
	}

	/**
	 * @param subtipoActivo the subtipoActivo to set
	 */
	public void setSubtipoActivo(String subtipoActivo)
	{
		this.subtipoActivo = subtipoActivo;
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda()
	{
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda)
	{
		this.moneda = moneda;
	}

	/**
	 * @return the usoIngresos
	 */
	public String getUsoIngresos()
	{
		return usoIngresos;
	}

	/**
	 * @param usoIngresos the usoIngresos to set
	 */
	public void setUsoIngresos(String usoIngresos)
	{
		this.usoIngresos = usoIngresos;
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
