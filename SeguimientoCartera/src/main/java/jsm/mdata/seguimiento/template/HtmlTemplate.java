/**
 * 
 */
package jsm.mdata.seguimiento.template;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import jsm.mdata.seguimiento.dto.EfectivoDTO;
import jsm.mdata.seguimiento.dto.GanPerProdPesoDTO;

/**
 * @author jsubiasm
 *
 */
public class HtmlTemplate
{

	/**
	 * @param listTableRows
	 * @param nombreVista
	 * @return
	 * @throws Throwable
	 */
	public static String getTable_VWF_nombreVista(List<GanPerProdPesoDTO> listTableRows, String nombreVista) throws Throwable
	{
		StringBuilder strTableRows = new StringBuilder();
		for (GanPerProdPesoDTO tableRow : listTableRows)
		{
			strTableRows.append(getRow_VWF_nombreVista(tableRow, nombreVista)).append("\n");
		}
		return strTableRows.toString().substring(0, strTableRows.toString().length() - 1);
	}

	/**
	 * @param tableRow
	 * @param nombreVista
	 * @return
	 * @throws Throwable
	 */
	private static String getRow_VWF_nombreVista(GanPerProdPesoDTO tableRow, String nombreVista) throws Throwable
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
			else if ("SUBTIPO_ACTIVO_GLOBAL".equalsIgnoreCase(nombreVista))
			{
				strTableRow.append("<td>").append(tableRow.getSubtipoActivo()).append("</td>");
			}
			else if ("SUBTIPO_ACTIVO_ORO".equalsIgnoreCase(nombreVista))
			{
				strTableRow.append("<td>").append(tableRow.getSubtipoActivo()).append("</td>");
			}
			else if ("SUBTIPO_ACTIVO_RF".equalsIgnoreCase(nombreVista))
			{
				strTableRow.append("<td>").append(tableRow.getSubtipoActivo()).append("</td>");
			}
			else if ("SUBTIPO_ACTIVO_RV".equalsIgnoreCase(nombreVista))
			{
				strTableRow.append("<td>").append(tableRow.getSubtipoActivo()).append("</td>");
			}
			else if ("SUBTIPO_ACTIVO_CRIPTO".equalsIgnoreCase(nombreVista))
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
			strTableRow.append("<td>").append(tableRow.getTer().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		}
		strTableRow.append("<td>").append(tableRow.getPrecioTitulosComprados().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getPrecioTitulosVendidos().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getFlujoCaja().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getValorTitulosActuales().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td").append(getTDColor(tableRow.getGananciaPerdida())).append(">").append(tableRow.getGananciaPerdida().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td").append(getTDColor(tableRow.getGananciaPerdidaPrcnt())).append(">").append(tableRow.getGananciaPerdidaPrcnt().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getPesoEnCartera().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("</tr>");
		return strTableRow.toString();
	}

	/**
	 * @param listTableRows
	 * @return
	 * @throws Throwable
	 */
	public static String getTable_VWF_GAN_PER_PROD_PESO_GLOBAL(List<GanPerProdPesoDTO> listTableRows) throws Throwable
	{
		StringBuilder strTableRows = new StringBuilder();
		for (GanPerProdPesoDTO tableRow : listTableRows)
		{
			strTableRows.append(getRow_VWF_GAN_PER_PROD_PESO_GLOBAL(tableRow)).append("\n");
		}
		return strTableRows.toString().substring(0, strTableRows.toString().length() - 1);
	}

	/**
	 * @param tableRow
	 * @return
	 * @throws Throwable
	 */
	private static String getRow_VWF_GAN_PER_PROD_PESO_GLOBAL(GanPerProdPesoDTO tableRow) throws Throwable
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
		strTableRow.append("<td>").append(tableRow.getTer().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getTitulosComprados().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getPrecioTitulosComprados().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getTitulosVendidos().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getPrecioTitulosVendidos().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getFlujoCaja().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getTitulosActuales().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getValorTitulo().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getValorTitulosActuales().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td").append(getTDColor(tableRow.getGananciaPerdida())).append(">").append(tableRow.getGananciaPerdida().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td").append(getTDColor(tableRow.getGananciaPerdidaPrcnt())).append(">").append(tableRow.getGananciaPerdidaPrcnt().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td>").append(tableRow.getPesoEnCartera().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("</tr>");
		return strTableRow.toString();
	}

	/**
	 * @param valor
	 * @return
	 */
	private static String getTDColor(BigDecimal valor)
	{
		String tdColor = "";
		if (valor.compareTo(BigDecimal.ZERO) < 0)
		{
			tdColor = " style=\"color:red\"";
		}
		else if (valor.compareTo(BigDecimal.ZERO) > 0)
		{
			tdColor = " style=\"color:green\"";
		}
		return tdColor;
	}

	/**
	 * @return
	 */
	public static String getMensajeFechaFichero()
	{
		Calendar cl = Calendar.getInstance();
		String[] mo = new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
		return "Fichero generado el " + cl.get(Calendar.DAY_OF_MONTH) + " de " + mo[cl.get(Calendar.MONTH)] + " de " + cl.get(Calendar.YEAR) + " a las " + cl.get(Calendar.HOUR_OF_DAY) + " horas " + cl.get(Calendar.MINUTE) + " minutos";
	}

	/**
	 * @param listTableRows
	 * @return
	 * @throws Throwable
	 */
	public static String getChartData(List<GanPerProdPesoDTO> listTableRows) throws Throwable
	{
		StringBuilder output = new StringBuilder();
		for (GanPerProdPesoDTO tableRow : listTableRows)
		{
			if (tableRow.getPesoEnCartera().compareTo(BigDecimal.ZERO) > 0)
			{
				output.append(tableRow.getPesoEnCartera().setScale(3, RoundingMode.HALF_EVEN)).append(",");
			}
		}
		return output.toString().length() != 0 ? output.toString().substring(0, output.toString().length() - 1) : "";
	}

	/**
	 * @param listTableRows
	 * @param nombreVista
	 * @return
	 * @throws Throwable
	 */
	public static String getChartBGColor(List<GanPerProdPesoDTO> listTableRows, String nombreVista) throws Throwable
	{
		StringBuilder output = new StringBuilder();
		for (GanPerProdPesoDTO tableRow : listTableRows)
		{
			if (tableRow.getPesoEnCartera().compareTo(BigDecimal.ZERO) > 0)
			{
				if ("SUBTIPO_ACTIVO_ORO".equalsIgnoreCase(nombreVista) || "Oro".equalsIgnoreCase(tableRow.getTipoActivo()))
				{
					output.append("'#f2d974'").append(",");
				}
				else if ("SUBTIPO_ACTIVO_RF".equalsIgnoreCase(nombreVista) || "Renta Fija".equalsIgnoreCase(tableRow.getTipoActivo()))
				{
					output.append("'#72aded'").append(",");
				}
				else if ("SUBTIPO_ACTIVO_RV".equalsIgnoreCase(nombreVista) || "Renta Variable".equalsIgnoreCase(tableRow.getTipoActivo()))
				{
					output.append("'#f07380'").append(",");
				}
				else if ("SUBTIPO_ACTIVO_CRIPTO".equalsIgnoreCase(nombreVista) || "Criptomoneda".equalsIgnoreCase(tableRow.getTipoActivo()))
				{
					output.append("'#999999'").append(",");
				}				
				else
				{
					output.append("'#c3d6d1'").append(",");
				}
			}
		}
		return output.toString().length() != 0 ? output.toString().substring(0, output.toString().length() - 1) : "";
	}

	/**
	 * @param listTableRows
	 * @param nombreVista
	 * @return
	 * @throws Throwable
	 */
	public static String getChartLabel(List<GanPerProdPesoDTO> listTableRows, String nombreVista) throws Throwable
	{
		StringBuilder output = new StringBuilder();
		for (GanPerProdPesoDTO tableRow : listTableRows)
		{
			if (tableRow.getPesoEnCartera().compareTo(BigDecimal.ZERO) > 0)
			{
				if ("COMERCIALIZADOR".equalsIgnoreCase(nombreVista))
				{
					output.append("'").append(tableRow.getComercializador()).append("',");
				}
				else if ("INSTRUMENTO".equalsIgnoreCase(nombreVista))
				{
					output.append("'").append(tableRow.getInstrumento()).append("',");
				}
				else if ("MERCADO".equalsIgnoreCase(nombreVista))
				{
					output.append("'").append(tableRow.getMercado()).append("',");
				}
				else if ("MONEDA".equalsIgnoreCase(nombreVista))
				{
					output.append("'").append(tableRow.getMoneda()).append("',");
				}
				else if ("PROVEEDOR".equalsIgnoreCase(nombreVista))
				{
					output.append("'").append(tableRow.getProveedor()).append("',");
				}
				else if ("SUBTIPO_ACTIVO_GLOBAL".equalsIgnoreCase(nombreVista))
				{
					output.append("'").append(tableRow.getSubtipoActivo()).append("',");
				}
				else if ("SUBTIPO_ACTIVO_ORO".equalsIgnoreCase(nombreVista))
				{
					output.append("'").append(tableRow.getSubtipoActivo()).append("',");
				}
				else if ("SUBTIPO_ACTIVO_RF".equalsIgnoreCase(nombreVista))
				{
					output.append("'").append(tableRow.getSubtipoActivo()).append("',");
				}
				else if ("SUBTIPO_ACTIVO_RV".equalsIgnoreCase(nombreVista))
				{
					output.append("'").append(tableRow.getSubtipoActivo()).append("',");
				}
				else if ("SUBTIPO_ACTIVO_CRIPTO".equalsIgnoreCase(nombreVista))
				{
					output.append("'").append(tableRow.getSubtipoActivo()).append("',");
				}				
				else if ("TIPO_ACTIVO".equalsIgnoreCase(nombreVista))
				{
					output.append("'").append(tableRow.getTipoActivo()).append("',");
				}
				else if ("USO_INGRESOS".equalsIgnoreCase(nombreVista))
				{
					output.append("'").append(tableRow.getUsoIngresos()).append("',");
				}
				else
				{
					throw new Exception("Nombre de vista inesperado [" + nombreVista + "]");
				}
			}
		}
		return output.toString().length() != 0 ? output.toString().substring(0, output.toString().length() - 1) : "";
	}

	/**
	 * @param listTableRows
	 * @return
	 * @throws Throwable
	 */
	public static String getTable_VWF_EFECTIVO(List<EfectivoDTO> listTableRows) throws Throwable
	{
		StringBuilder strTableRows = new StringBuilder();
		for (EfectivoDTO tableRow : listTableRows)
		{
			strTableRows.append(getRow_VWF_EFECTIVO(tableRow)).append("\n");
		}
		return strTableRows.toString().substring(0, strTableRows.toString().length() - 1);
	}

	/**
	 * @param tableRow
	 * @return
	 * @throws Throwable
	 */
	private static String getRow_VWF_EFECTIVO(EfectivoDTO tableRow) throws Throwable
	{
		StringBuilder strTableRow = new StringBuilder();
		strTableRow.append("<tr>");
		String nombreCuenta = tableRow.getNombreCuenta();
		strTableRow.append("<td").append(getTDStyle(nombreCuenta)).append(">").append(nombreCuenta).append("</td>");
		strTableRow.append("<td").append(getTDStyle(nombreCuenta)).append(">").append(tableRow.getLiquido().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("<td").append(getTDStyle(nombreCuenta)).append(">").append(tableRow.getInmovilizado().setScale(3, RoundingMode.HALF_EVEN)).append("</td>");
		strTableRow.append("</tr>");
		return strTableRow.toString();
	}

	/**
	 * @param nombreCuenta
	 * @return
	 */
	private static String getTDStyle(String nombreCuenta)
	{
		String tdStyle = "";
		if (nombreCuenta.equalsIgnoreCase("Total"))
		{
			tdStyle = " style=\"font-size: 16px; font-weight: bold;\"";
		}
		return tdStyle;
	}

	/**
	 * @param listTableRows
	 * @return
	 * @throws Throwable
	 */
	public static String getChartEfectivoData(List<EfectivoDTO> listTableRows) throws Throwable
	{
		StringBuilder output = new StringBuilder();
		for (EfectivoDTO tableRow : listTableRows)
		{
			if (!tableRow.getNombreCuenta().equalsIgnoreCase("Total") && tableRow.getLiquido().compareTo(BigDecimal.valueOf(500d)) == 1)
			{
				output.append(tableRow.getLiquido().setScale(3, RoundingMode.HALF_EVEN)).append(",");
			}
		}
		return output.toString().length() != 0 ? output.toString().substring(0, output.toString().length() - 1) : "";
	}

	/**
	 * @param listTableRows
	 * @return
	 * @throws Throwable
	 */
	public static String getChartEfectivoBGColor(List<EfectivoDTO> listTableRows) throws Throwable
	{
		StringBuilder output = new StringBuilder();
		for (EfectivoDTO tableRow : listTableRows)
		{
			if (!tableRow.getNombreCuenta().equalsIgnoreCase("Total") && tableRow.getLiquido().compareTo(BigDecimal.valueOf(500d)) == 1)
			{
				output.append("'#7ad472'").append(",");
			}
		}
		return output.toString().length() != 0 ? output.toString().substring(0, output.toString().length() - 1) : "";
	}

	/**
	 * @param listTableRows
	 * @return
	 * @throws Throwable
	 */
	public static String getChartEfectivoLabel(List<EfectivoDTO> listTableRows) throws Throwable
	{
		StringBuilder output = new StringBuilder();
		for (EfectivoDTO tableRow : listTableRows)
		{
			if (!tableRow.getNombreCuenta().equalsIgnoreCase("Total") && tableRow.getLiquido().compareTo(BigDecimal.valueOf(500d)) == 1)
			{
				output.append("'").append(tableRow.getNombreCuenta()).append("',");
			}
		}
		return output.toString().length() != 0 ? output.toString().substring(0, output.toString().length() - 1) : "";
	}

	/**
	 * @param vistaEfectivo
	 * @param vistaTipoActivo
	 * @return
	 * @throws Throwable
	 */
	public static String getChartData(List<EfectivoDTO> vistaEfectivo, List<GanPerProdPesoDTO> vistaTipoActivo) throws Throwable
	{
		StringBuilder output = new StringBuilder();
		for (GanPerProdPesoDTO tableRow : vistaTipoActivo)
		{
			if (tableRow.getValorTitulosActuales().compareTo(BigDecimal.ZERO) > 0)
			{
				output.append(tableRow.getValorTitulosActuales().setScale(3, RoundingMode.HALF_EVEN)).append(",");
			}
		}
		for (EfectivoDTO tableRow : vistaEfectivo)
		{
			if (tableRow.getNombreCuenta().equalsIgnoreCase("Total"))
			{
				output.append(tableRow.getLiquido().setScale(3, RoundingMode.HALF_EVEN)).append(",");
			}
		}
		return output.toString().length() != 0 ? output.toString().substring(0, output.toString().length() - 1) : "";
	}

	/**
	 * @param vistaTipoActivo
	 * @return
	 * @throws Throwable
	 */
	public static String getChartBGColor(List<GanPerProdPesoDTO> vistaTipoActivo) throws Throwable
	{
		StringBuilder output = new StringBuilder();
		for (GanPerProdPesoDTO tableRow : vistaTipoActivo)
		{
			if (tableRow.getValorTitulosActuales().compareTo(BigDecimal.ZERO) > 0)
			{
				if ("Oro".equalsIgnoreCase(tableRow.getTipoActivo()))
				{
					output.append("'#f2d974'").append(",");
				}
				else if ("Renta Fija".equalsIgnoreCase(tableRow.getTipoActivo()))
				{
					output.append("'#72aded'").append(",");
				}
				else if ("Renta Variable".equalsIgnoreCase(tableRow.getTipoActivo()))
				{
					output.append("'#f07380'").append(",");
				}
				else if ("Criptomoneda".equalsIgnoreCase(tableRow.getTipoActivo()))
				{
					output.append("'#999999'").append(",");
				}				
			}
		}
		output.append("'#7ad472'").append(",");
		return output.toString().length() != 0 ? output.toString().substring(0, output.toString().length() - 1) : "";
	}

	/**
	 * @param vistaTipoActivo
	 * @return
	 * @throws Throwable
	 */
	public static String getChartLabel(List<GanPerProdPesoDTO> vistaTipoActivo) throws Throwable
	{
		StringBuilder output = new StringBuilder();
		for (GanPerProdPesoDTO tableRow : vistaTipoActivo)
		{
			if (tableRow.getValorTitulosActuales().compareTo(BigDecimal.ZERO) > 0)
			{
				output.append("'").append(tableRow.getTipoActivo()).append("',");
			}
		}
		output.append("'Liquido',");
		return output.toString().length() != 0 ? output.toString().substring(0, output.toString().length() - 1) : "";
	}

}
