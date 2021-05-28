/**
 * 
 */
package jsm.mdata.seguimiento.main;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @author jsubiasm
 *
 */
public class ProcesosUtils
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			actualizarTotalesMovimientosCuantroDecimales();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private static final void actualizarTotalesMovimientosCuantroDecimales() throws Throwable
	{
		List<String> listLineasInput = FileUtils.readLines(new File("C:\\Users\\jsubiasm\\Desktop\\tmp\\01_movimientos_OLD.sql"), "UTF-8");
		if (listLineasInput != null && !listLineasInput.isEmpty())
		{
			for (String lineaInput : listLineasInput)
			{
				lineaInput = lineaInput.substring(lineaInput.lastIndexOf("(") + 1, lineaInput.lastIndexOf(")"));
				List<String> items = Arrays.asList(lineaInput.split(","));
				String compraVenta = items.get(1).trim();
				BigDecimal numTitulos = new BigDecimal(items.get(3).trim());
				BigDecimal precioTitulo = new BigDecimal(items.get(4).trim());
				BigDecimal comision = new BigDecimal(items.get(5).trim());
				BigDecimal total = new BigDecimal(items.get(6).trim());
				BigDecimal nuevoTotal = null;
				if (compraVenta.equalsIgnoreCase("'Compra'"))
				{
					nuevoTotal = numTitulos.multiply(precioTitulo).add(comision).setScale(6, RoundingMode.HALF_EVEN);
				}
				else
				{
					nuevoTotal = numTitulos.multiply(precioTitulo).subtract(comision).setScale(6, RoundingMode.HALF_EVEN);
				}
				if (!similar(total, nuevoTotal))
				{
					throw new Exception("NO SIMILAR [" + total.toString() + "] [" + nuevoTotal.toString() + "]");
				}
				System.out.println(nuevoTotal);
			}
		}
	}

	/**
	 * @param primero
	 * @param segundo
	 * @return
	 */
	private static boolean similar(BigDecimal primero, BigDecimal segundo)
	{
		double margenError = 0.01d;
		boolean similar = false;
		if (primero.equals(segundo))
		{
			similar = true;
		}
		else
		{
			BigDecimal diferencia = primero.subtract(segundo);
			similar = diferencia.doubleValue() >= -margenError && diferencia.doubleValue() <= margenError;
		}
		return similar;
	}

}
