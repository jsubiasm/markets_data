/**
 * 
 */
package jsm.mdata.seguimiento.dto;

import java.math.BigDecimal;

/**
 * @author jsubiasm
 *
 */
public class EfectivoDTO
{

	/**
	 * 
	 */
	private String nombreCuenta = null;
	private BigDecimal liquido = null;
	private BigDecimal inmovilizado = null;

	/**
	 * @return the nombreCuenta
	 */
	public String getNombreCuenta()
	{
		return nombreCuenta;
	}

	/**
	 * @param nombreCuenta the nombreCuenta to set
	 */
	public void setNombreCuenta(String nombreCuenta)
	{
		this.nombreCuenta = nombreCuenta;
	}

	/**
	 * @return the liquido
	 */
	public BigDecimal getLiquido()
	{
		return liquido;
	}

	/**
	 * @param liquido the liquido to set
	 */
	public void setLiquido(BigDecimal liquido)
	{
		this.liquido = liquido;
	}

	/**
	 * @return the inmovilizado
	 */
	public BigDecimal getInmovilizado()
	{
		return inmovilizado;
	}

	/**
	 * @param inmovilizado the inmovilizado to set
	 */
	public void setInmovilizado(BigDecimal inmovilizado)
	{
		this.inmovilizado = inmovilizado;
	}

}
