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
	private BigDecimal disponible = null;
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
	 * @return the disponible
	 */
	public BigDecimal getDisponible()
	{
		return disponible;
	}

	/**
	 * @param disponible the disponible to set
	 */
	public void setDisponible(BigDecimal disponible)
	{
		this.disponible = disponible;
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
