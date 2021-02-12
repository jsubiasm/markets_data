/**
 * 
 */
package jsm.mdata.seguimiento.dto;

/**
 * @author jsubiasm
 *
 */
public class ProductoDTO
{

	/**
	 * 
	 */
	private String identificador = null;
	private String nombre = null;
	private String proveedor = null;
	private String instrumento = null;
	private String tipoActivo = null;
	private String subtipoActivo = null;
	private String moneda = null;
	private String usoIngresos = null;

	/**
	 * @return the identificador
	 */
	public String getIdentificador()
	{
		return identificador;
	}

	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(String identificador)
	{
		this.identificador = identificador;
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

}
