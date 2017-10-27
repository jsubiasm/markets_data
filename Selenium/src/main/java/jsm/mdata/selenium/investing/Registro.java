package jsm.mdata.selenium.investing;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Empleado
 *
 */
public class Registro
{

	/**
	 * 
	 */
	private Date fecha;
	private BigDecimal apertura;
	private BigDecimal maximo;
	private BigDecimal minimo;
	private BigDecimal cierre;
	private BigDecimal volumen;

	/**
	 * @return the fecha
	 */
	public Date getFecha()
	{
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}

	/**
	 * @return the apertura
	 */
	public BigDecimal getApertura()
	{
		return apertura;
	}

	/**
	 * @param apertura
	 *            the apertura to set
	 */
	public void setApertura(BigDecimal apertura)
	{
		this.apertura = apertura;
	}

	/**
	 * @return the maximo
	 */
	public BigDecimal getMaximo()
	{
		return maximo;
	}

	/**
	 * @param maximo
	 *            the maximo to set
	 */
	public void setMaximo(BigDecimal maximo)
	{
		this.maximo = maximo;
	}

	/**
	 * @return the minimo
	 */
	public BigDecimal getMinimo()
	{
		return minimo;
	}

	/**
	 * @param minimo
	 *            the minimo to set
	 */
	public void setMinimo(BigDecimal minimo)
	{
		this.minimo = minimo;
	}

	/**
	 * @return the cierre
	 */
	public BigDecimal getCierre()
	{
		return cierre;
	}

	/**
	 * @param cierre
	 *            the cierre to set
	 */
	public void setCierre(BigDecimal cierre)
	{
		this.cierre = cierre;
	}

	/**
	 * @return the volumen
	 */
	public BigDecimal getVolumen()
	{
		return volumen;
	}

	/**
	 * @param volumen
	 *            the volumen to set
	 */
	public void setVolumen(BigDecimal volumen)
	{
		this.volumen = volumen;
	}

}
