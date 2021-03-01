/**
 * 
 */
package jsm.mdata.seguimiento.dao;

import java.util.List;

import jsm.mdata.seguimiento.dto.GanPerProdPesoDTO;

/**
 * @author jsubiasm
 *
 */
public class TemplateDAO
{

	/**
	 * @param listTableRows
	 * @param nombreVista
	 * @return
	 * @throws Throwable
	 */
	public static final String getTableRows(List<GanPerProdPesoDTO> listTableRows, String nombreVista) throws Throwable
	{
		StringBuilder strTableRows = new StringBuilder();
		for (GanPerProdPesoDTO tableRow : listTableRows)
		{
			strTableRows.append(getTableRow(tableRow, nombreVista)).append("\n");
		}
		return strTableRows.toString();
	}

	/**
	 * @param tableRow
	 * @param nombreVista
	 * @return
	 * @throws Throwable
	 */
	public static final String getTableRow(GanPerProdPesoDTO tableRow, String nombreVista) throws Throwable
	{
		StringBuilder strTableRow = new StringBuilder();
		strTableRow.append("<tr><td>");
		if (nombreVista != null)
		{
			if ("COMERCIALIZADOR".equalsIgnoreCase(nombreVista))
			{
			}
			else if ("INSTRUMENTO".equalsIgnoreCase(nombreVista))
			{
			}
			else if ("MERCADO".equalsIgnoreCase(nombreVista))
			{
			}
			else if ("MONEDA".equalsIgnoreCase(nombreVista))
			{
			}
			else if ("PROVEEDOR".equalsIgnoreCase(nombreVista))
			{
			}
			else if (nombreVista.equalsIgnoreCase("SUBTIPO_ACTIVO_GLOBAL"))
			{
			}
			else if (nombreVista.equalsIgnoreCase("SUBTIPO_ACTIVO_ORO"))
			{
			}
			else if (nombreVista.equalsIgnoreCase("SUBTIPO_ACTIVO_RF"))
			{
			}
			else if (nombreVista.equalsIgnoreCase("SUBTIPO_ACTIVO_RV"))
			{
			}
			else if ("TIPO_ACTIVO".equalsIgnoreCase(nombreVista))
			{
			}
			else if ("USO_INGRESOS".equalsIgnoreCase(nombreVista))
			{
			}
			else
			{
				throw new Exception("Nombre de vista inesperado [" + nombreVista + "]");
			}
		}
		else
		{
			strTableRow.append(tableRow.getTer()).append("</td><td>");
			strTableRow.append(tableRow.getPrecioTitulosComprados()).append("</td><td>");
			strTableRow.append(tableRow.getPrecioTitulosVendidos()).append("</td><td>");
			strTableRow.append(tableRow.getFlujoCaja()).append("</td><td>");
			strTableRow.append(tableRow.getValorTitulosActuales()).append("</td><td>");
			strTableRow.append(tableRow.getGananciaPerdida()).append("</td><td>");
			strTableRow.append(tableRow.getGananciaPerdidaPrcnt()).append("</td><td>");
			strTableRow.append(tableRow.getPesoEnCartera()).append("</td></tr>");
		}
		return strTableRow.toString();
	}

}
