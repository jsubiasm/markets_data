/**
 * 
 */
package jsm.mdata.selenium.investing;

import java.util.ArrayList;

/**
 * @author Empleado
 *
 */
public class Activo
{

	/**
	 * 
	 */
	private String mercado;
	private String bolsa;
	private String indice;
	private String ticker;
	private ArrayList<Registro> listaRegistros = new ArrayList<Registro>();

	/**
	 * @return the mercado
	 */
	public String getMercado()
	{
		return mercado;
	}

	/**
	 * @param mercado
	 *            the mercado to set
	 */
	public void setMercado(String mercado)
	{
		this.mercado = mercado;
	}

	/**
	 * @return the bolsa
	 */
	public String getBolsa()
	{
		return bolsa;
	}

	/**
	 * @param bolsa
	 *            the bolsa to set
	 */
	public void setBolsa(String bolsa)
	{
		this.bolsa = bolsa;
	}

	/**
	 * @return the indice
	 */
	public String getIndice()
	{
		return indice;
	}

	/**
	 * @param indice
	 *            the indice to set
	 */
	public void setIndice(String indice)
	{
		this.indice = indice;
	}

	/**
	 * @return the ticker
	 */
	public String getTicker()
	{
		return ticker;
	}

	/**
	 * @param ticker
	 *            the ticker to set
	 */
	public void setTicker(String ticker)
	{
		this.ticker = ticker;
	}

	/**
	 * @return the listaRegistros
	 */
	public ArrayList<Registro> getListaRegistros()
	{
		return listaRegistros;
	}

	/**
	 * @param listaRegistros
	 *            the listaRegistros to set
	 */
	public void setListaRegistros(ArrayList<Registro> listaRegistros)
	{
		this.listaRegistros = listaRegistros;
	}

}
