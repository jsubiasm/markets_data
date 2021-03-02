/**
 * 
 */
package jsm.mdata.seguimiento.template;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import jsm.mdata.seguimiento.dto.GanPerProdPesoDTO;

/**
 * @author jsubiasm
 *
 */
public class HtmlConverter
{

	/**
	 * @param listTableRows
	 * @param nombreVista
	 * @return
	 * @throws Throwable
	 */
	public static final String getTable_VWF_nombreVista(List<GanPerProdPesoDTO> listTableRows, String nombreVista) throws Throwable
	{
		StringBuilder strTableRows = new StringBuilder();
		for (GanPerProdPesoDTO tableRow : listTableRows)
		{
			strTableRows.append(getRow_VWF_nombreVista(tableRow, nombreVista)).append("\n");
		}
		return strTableRows.toString();
	}

	/**
	 * @param tableRow
	 * @param nombreVista
	 * @return
	 * @throws Throwable
	 */
	public static final String getRow_VWF_nombreVista(GanPerProdPesoDTO tableRow, String nombreVista) throws Throwable
	{
		StringBuilder strTableRow = new StringBuilder();
		strTableRow.append("<tr>");
		if (nombreVista != null)
		{
			if ("COMERCIALIZADOR".equalsIgnoreCase(nombreVista))
			{
				strTableRow.append("<td>").append(tableRow.getComercializador()).append("</td>");
			}
			else if ("INSTRUMENTO".equalsIgnoreCase(nombreVista))
			{
				strTableRow.append("<td>").append(tableRow.getInstrumento()).append("</td>");
			}
			else if ("MERCADO".equalsIgnoreCase(nombreVista))
			{
				strTableRow.append("<td>").append(tableRow.getMercado()).append("</td>");
			}
			else if ("MONEDA".equalsIgnoreCase(nombreVista))
			{
				strTableRow.append("<td>").append(tableRow.getMoneda()).append("</td>");
			}
			else if ("PROVEEDOR".equalsIgnoreCase(nombreVista))
			{
				strTableRow.append("<td>").append(tableRow.getProveedor()).append("</td>");
			}
			else if (nombreVista.equalsIgnoreCase("SUBTIPO_ACTIVO_GLOBAL"))
			{
				strTableRow.append("<td>").append(tableRow.getSubtipoActivo()).append("</td>");
			}
			else if (nombreVista.equalsIgnoreCase("SUBTIPO_ACTIVO_ORO"))
			{
				strTableRow.append("<td>").append(tableRow.getSubtipoActivo()).append("</td>");
			}
			else if (nombreVista.equalsIgnoreCase("SUBTIPO_ACTIVO_RF"))
			{
				strTableRow.append("<td>").append(tableRow.getSubtipoActivo()).append("</td>");
			}
			else if (nombreVista.equalsIgnoreCase("SUBTIPO_ACTIVO_RV"))
			{
				strTableRow.append("<td>").append(tableRow.getSubtipoActivo()).append("</td>");
			}
			else if ("TIPO_ACTIVO".equalsIgnoreCase(nombreVista))
			{
				strTableRow.append("<td>").append(tableRow.getTipoActivo()).append("</td>");
			}
			else if ("USO_INGRESOS".equalsIgnoreCase(nombreVista))
			{
				strTableRow.append("<td>").append(tableRow.getUsoIngresos()).append("</td>");
			}
			else
			{
				throw new Exception("Nombre de vista inesperado [" + nombreVista + "]");
			}
		}
		else
		{
			strTableRow.append("<td>").append(tableRow.getTer()).append("</td>");
		}
		strTableRow.append("<td>").append(tableRow.getPrecioTitulosComprados()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getPrecioTitulosVendidos()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getFlujoCaja()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getValorTitulosActuales()).append("</td>");
		if (tableRow.getGananciaPerdida().compareTo(BigDecimal.ZERO) < 0)
		{
			strTableRow.append("<td style=\"color:red\">").append(tableRow.getGananciaPerdida()).append("</td>");
		}
		if (tableRow.getGananciaPerdida().compareTo(BigDecimal.ZERO) > 0)
		{
			strTableRow.append("<td style=\"color:green\">").append(tableRow.getGananciaPerdida()).append("</td>");
		}
		if (tableRow.getGananciaPerdidaPrcnt().compareTo(BigDecimal.ZERO) < 0)
		{
			strTableRow.append("<td style=\"color:red\">").append(tableRow.getGananciaPerdidaPrcnt()).append("</td>");
		}
		if (tableRow.getGananciaPerdidaPrcnt().compareTo(BigDecimal.ZERO) > 0)
		{
			strTableRow.append("<td style=\"color:green\">").append(tableRow.getGananciaPerdidaPrcnt()).append("</td>");
		}
		strTableRow.append("<td>").append(tableRow.getPesoEnCartera()).append("</td>");
		strTableRow.append("</tr>");
		return strTableRow.toString();
	}

	/**
	 * @param listTableRows
	 * @return
	 * @throws Throwable
	 */
	public static final String getTable_VWF_GAN_PER_PROD_PESO_GLOBAL(List<GanPerProdPesoDTO> listTableRows) throws Throwable
	{
		StringBuilder strTableRows = new StringBuilder();
		for (GanPerProdPesoDTO tableRow : listTableRows)
		{
			strTableRows.append(getRow_VWF_GAN_PER_PROD_PESO_GLOBAL(tableRow)).append("\n");
		}
		return strTableRows.toString();
	}

	/**
	 * @param tableRow
	 * @return
	 * @throws Throwable
	 */
	public static final String getRow_VWF_GAN_PER_PROD_PESO_GLOBAL(GanPerProdPesoDTO tableRow) throws Throwable
	{
		StringBuilder strTableRow = new StringBuilder();
		strTableRow.append("<tr>");
		strTableRow.append("<td>").append(tableRow.getProductoId()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getNombre()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getComercializador()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getMercado()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getProveedor()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getInstrumento()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getTipoActivo()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getSubtipoActivo()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getMoneda()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getUsoIngresos()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getTer()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getTitulosComprados()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getPrecioTitulosComprados()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getTitulosVendidos()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getPrecioTitulosVendidos()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getFlujoCaja()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getTitulosActuales()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getValorTitulo()).append("</td>");
		strTableRow.append("<td>").append(tableRow.getValorTitulosActuales()).append("</td>");
		if (tableRow.getGananciaPerdida().compareTo(BigDecimal.ZERO) < 0)
		{
			strTableRow.append("<td style=\"color:red\">").append(tableRow.getGananciaPerdida()).append("</td>");
		}
		if (tableRow.getGananciaPerdida().compareTo(BigDecimal.ZERO) > 0)
		{
			strTableRow.append("<td style=\"color:green\">").append(tableRow.getGananciaPerdida()).append("</td>");
		}
		if (tableRow.getGananciaPerdidaPrcnt().compareTo(BigDecimal.ZERO) < 0)
		{
			strTableRow.append("<td style=\"color:red\">").append(tableRow.getGananciaPerdidaPrcnt()).append("</td>");
		}
		if (tableRow.getGananciaPerdidaPrcnt().compareTo(BigDecimal.ZERO) > 0)
		{
			strTableRow.append("<td style=\"color:green\">").append(tableRow.getGananciaPerdidaPrcnt()).append("</td>");
		}
		strTableRow.append("<td>").append(tableRow.getPesoEnCartera()).append("</td>");
		strTableRow.append("</tr>");
		return strTableRow.toString();
	}

	/**
	 * @return
	 */
	public static String getMensajeFechaFichero()
	{
		Calendar cl = Calendar.getInstance();
		String[] mo = new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
		return "Fichero generado el " + cl.get(Calendar.DAY_OF_MONTH) + " de " + mo[cl.get(Calendar.MONTH)] + " de " + cl.get(Calendar.YEAR) + " a las " + cl.get(Calendar.HOUR_OF_DAY) + ":" + cl.get(Calendar.MINUTE);
	}

}
