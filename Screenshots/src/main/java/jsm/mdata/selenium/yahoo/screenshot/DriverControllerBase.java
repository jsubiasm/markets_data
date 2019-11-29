/**
 * 
 */
package jsm.mdata.selenium.yahoo.screenshot;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Empleado
 *
 */
public abstract class DriverControllerBase
{

	/**
	 * Rutas
	 */
	protected static final String DOWNLOAD_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\Screenshots\\yahoo\\download";

	/**
	 * 
	 */
	protected static final String TF_MENSUAL = "TF_MENSUAL";

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DriverControllerBase.class);

	/**
	 * @param driver
	 * @param hrefElemento
	 * @param downloadPath
	 * @param charset
	 * @throws Exception
	 */
	protected static void procesarElemento(WebDriver driver, String hrefElemento, String downloadPath, String timeFrame, Double minDividendo, Double minCapitalizacion) throws Exception
	{
		LOGGER.info("Cargando URL [" + hrefElemento + "] ");
		driver.get(hrefElemento);
		LOGGER.info("Buscando nombre de empresa");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("quote-header-info")));
		WebElement infoCabecera = driver.findElement(By.id("quote-header-info"));
		String resultNombreEmpresa = infoCabecera.findElements(By.tagName("h1")).get(0).getAttribute("innerHTML").trim();
		LOGGER.info("Buscando Capitalización y RPD");
		WebElement tablaDatos = driver.findElement(By.id("quote-summary"));
		List<WebElement> listaFilasTabla = tablaDatos.findElements(By.tagName("tr"));
		String resultCAP = null;
		String resultCAP_UM = null;
		String resultRPD = null;
		for (WebElement filaTabla : listaFilasTabla)
		{
			if (filaTabla.getAttribute("innerHTML").indexOf("Capitalización de mercado") != -1)
			{
				List<WebElement> elementosFila = filaTabla.findElements(By.tagName("span"));
				String capitalizacionStr = elementosFila.get(1).getAttribute("innerHTML").trim();
				capitalizacionStr = capitalizacionStr.replaceAll(",", "");
				capitalizacionStr = capitalizacionStr.replaceAll("\\.", ",");
				resultCAP = capitalizacionStr.substring(0, capitalizacionStr.length() - 1);
				resultCAP_UM = capitalizacionStr.substring(capitalizacionStr.length() - 1, capitalizacionStr.length());
				if (capitalizacionStr.endsWith("T"))
				{
					continue;
				}
				else if (resultCAP_UM.equalsIgnoreCase("B"))
				{
					resultCAP = resultCAP.substring(0, resultCAP.indexOf("B"));
					resultCAP = resultCAP.replaceAll("\\.", "");
					resultCAP = resultCAP.replaceAll(",", ".");
					Double doubleResultCAP = Double.valueOf(resultCAP);
					if (doubleResultCAP < minCapitalizacion)
					{
						LOGGER.info("No se obtiene el chart de [" + resultNombreEmpresa.replaceAll("&amp;", "&") + "] [" + hrefElemento + "] porque la capitalización [" + doubleResultCAP + "] B es menor que la capitalización mínima [" + minCapitalizacion + "] B");
						LOGGER.info("NO screenshot --> " + resultNombreEmpresa.replaceAll("&amp;", "&") + ";" + hrefElemento + ";" + doubleResultCAP + ";-");
						return;
					}
					else
					{
						continue;
					}
				}
				else
				{
					LOGGER.info("No se obtiene el chart de [" + resultNombreEmpresa.replaceAll("&amp;", "&") + "] [" + hrefElemento + "] porque la capitalización [" + resultCAP + "] no supera la capitalización mínima [" + minCapitalizacion + "] B");
					LOGGER.info("NO screenshot --> " + resultNombreEmpresa.replaceAll("&amp;", "&") + ";" + hrefElemento + ";" + resultCAP + ";-");
					return;
				}
			}
			else if (filaTabla.getAttribute("innerHTML").indexOf("Previsión de rentabilidad y dividendo") != -1)
			{
				List<WebElement> elementosFila = filaTabla.findElements(By.tagName("td"));
				resultRPD = elementosFila.get(1).getAttribute("innerHTML").trim();
				resultRPD = resultRPD.substring(resultRPD.indexOf("(") + 1, resultRPD.indexOf(")") - 1);
				resultRPD = resultRPD.replaceAll(",", "");
				resultRPD = resultRPD.replaceAll("\\.", ",");
				if (resultRPD.indexOf("N/A") != -1)
				{
					LOGGER.info("No se obtiene el chart de [" + resultNombreEmpresa.replaceAll("&amp;", "&") + "] [" + hrefElemento + "] porque la RPD [" + resultRPD + "] no está disponible");
					LOGGER.info("NO screenshot --> " + resultNombreEmpresa.replaceAll("&amp;", "&") + ";" + hrefElemento + ";-;-");
					return;
				}
				resultRPD = resultRPD.substring(resultRPD.indexOf("(") + 1, resultRPD.indexOf(")") - 1);
				resultRPD = resultRPD.replaceAll("\\.", "");
				resultRPD = resultRPD.replaceAll(",", ".");
				Double doubleResultRPD = Double.valueOf(resultRPD);
				if (doubleResultRPD < minDividendo)
				{
					LOGGER.info("No se obtiene el chart de [" + resultNombreEmpresa.replaceAll("&amp;", "&") + "] [" + hrefElemento + "] porque la RPD [" + doubleResultRPD + "] es menor que la RPD mínima [" + minDividendo + "] ");
					LOGGER.info("NO screenshot --> " + resultNombreEmpresa.replaceAll("&amp;", "&") + ";" + hrefElemento + ";-;" + doubleResultRPD);
					return;
				}
				else
				{
					continue;
				}
			}
		}

		// TODO: FALTA TERMINAR

		LOGGER.info("Generando screenshot --> " + resultNombreEmpresa.replaceAll("&amp;", "&") + ";" + resultRPD + ";" + resultCAP + ";" + resultCAP_UM + ";" + hrefElemento);
	}

}
