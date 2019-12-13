/**
 * 
 */
package jsm.mdata.selenium.screenshot.base;

/**
 * @author Empleado
 *
 */
public class Utils
{

	/**
	 * @param texto
	 * @return
	 */
	public static String normalizaTexto(String texto)
	{
		return texto.replaceAll("&amp;", "&").replaceAll("&nbsp;", " ");
	}

}
