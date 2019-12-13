/**
 * 
 */
package jsm.mdata.selenium.screenshot.yahoo;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsm.mdata.selenium.screenshot.base.Utils;
import jsm.mdata.selenium.screenshot.base.WebDriverBase;

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
		Double doubleResultCAP = null;
		Double doubleResultRPD = null;
		for (WebElement filaTabla : listaFilasTabla)
		{
			if (filaTabla.getAttribute("innerHTML").indexOf("Capitalización de mercado") != -1)
			{
				List<WebElement> elementosFila = filaTabla.findElements(By.tagName("span"));
				String capitalizacionStr = elementosFila.get(1).getAttribute("innerHTML").trim();
				resultCAP = capitalizacionStr.substring(0, capitalizacionStr.length() - 1);
				resultCAP_UM = capitalizacionStr.substring(capitalizacionStr.length() - 1, capitalizacionStr.length());
				if (capitalizacionStr.endsWith("T"))
				{
					continue;
				}
				else if (resultCAP_UM.equalsIgnoreCase("B"))
				{
					resultCAP = resultCAP.replaceAll("\\.", "");
					resultCAP = resultCAP.replaceAll(",", ".");
					doubleResultCAP = Double.valueOf(resultCAP);
					if (doubleResultCAP < minCapitalizacion)
					{
						LOGGER.info("No se obtiene el chart de [" + Utils.normalizaTexto(resultNombreEmpresa) + "] [" + hrefElemento + "] porque la capitalización [" + doubleResultCAP + "] B es menor que la capitalización mínima [" + minCapitalizacion + "] B");
						LOGGER.info("NO screenshot --> " + Utils.normalizaTexto(resultNombreEmpresa) + ";" + hrefElemento + ";" + capitalizacionStr + ";-");
						return;
					}
					else
					{
						continue;
					}
				}
				else
				{
					LOGGER.info("No se obtiene el chart de [" + Utils.normalizaTexto(resultNombreEmpresa) + "] [" + hrefElemento + "] porque la capitalización [" + resultCAP + "] no supera la capitalización mínima [" + minCapitalizacion + "] B");
					LOGGER.info("NO screenshot --> " + Utils.normalizaTexto(resultNombreEmpresa) + ";" + hrefElemento + ";" + capitalizacionStr + ";-");
					return;
				}
			}
			else if (filaTabla.getAttribute("innerHTML").indexOf("Previsión de rentabilidad y dividendo") != -1)
			{
				List<WebElement> elementosFila = filaTabla.findElements(By.tagName("td"));
				resultRPD = elementosFila.get(1).getAttribute("innerHTML").trim();
				resultRPD = resultRPD.substring(resultRPD.indexOf("(") + 1, resultRPD.indexOf(")") - 1);
				if (resultRPD.indexOf("N/") != -1)
				{
					LOGGER.info("No se obtiene el chart de [" + Utils.normalizaTexto(resultNombreEmpresa) + "] [" + hrefElemento + "] porque la RPD [" + resultRPD + "] no está disponible");
					LOGGER.info("NO screenshot --> " + Utils.normalizaTexto(resultNombreEmpresa) + ";" + hrefElemento + ";-;-");
					return;
				}
				resultRPD = resultRPD.replaceAll("\\.", "");
				resultRPD = resultRPD.replaceAll(",", ".");
				doubleResultRPD = Double.valueOf(resultRPD);
				if (doubleResultRPD < minDividendo)
				{
					LOGGER.info("No se obtiene el chart de [" + Utils.normalizaTexto(resultNombreEmpresa) + "] [" + hrefElemento + "] porque la RPD [" + doubleResultRPD + "] es menor que la RPD mínima [" + minDividendo + "] ");
					LOGGER.info("NO screenshot --> " + Utils.normalizaTexto(resultNombreEmpresa) + ";" + hrefElemento + ";-;" + resultRPD);
					return;
				}
				else
				{
					continue;
				}
			}
		}
		String chartUrl = hrefElemento.replaceAll("quote", "chart");
		LOGGER.info("Cargando chart URL [" + chartUrl + "] ");
		driver.get(chartUrl);
		LOGGER.info("Ajustando el chart");
		WebElement chartToolbar = driver.findElement(By.id("chart-toolbar"));
		List<WebElement> elementosToolbar = chartToolbar.findElements(By.tagName("span"));
		for (WebElement elemento : elementosToolbar)
		{
			if (elemento.getAttribute("innerHTML").indexOf("Eventos") != -1 && elemento.getAttribute("innerHTML").indexOf("span") == -1)
			{
				WebDriverBase.clickElement(driver, elemento);
				WebElement dropdownMenu = driver.findElement(By.id("dropdown-menu"));
				List<WebElement> listaOpciones = dropdownMenu.findElements(By.tagName("span"));
				for (WebElement opcion : listaOpciones)
				{
					if (opcion.getAttribute("innerHTML").indexOf("Dividendos") != -1 && opcion.getAttribute("innerHTML").indexOf("span") == -1)
					{
						WebDriverBase.clickElement(driver, opcion);
					}
					else if (opcion.getAttribute("innerHTML").indexOf("Splits de acciones") != -1 && opcion.getAttribute("innerHTML").indexOf("span") == -1)
					{
						WebDriverBase.clickElement(driver, opcion);
					}
				}
				continue;
			}
			else if (elemento.getAttribute("innerHTML").indexOf("Máx.") != -1)
			{
				WebDriverBase.clickElement(driver, elemento);
				continue;
			}
			else if (elemento.getAttribute("innerHTML").indexOf("Intervalo") != -1 && elemento.getAttribute("innerHTML").indexOf("Intervalo de fechas") == -1)
			{
				WebDriverBase.clickElement(driver, elemento);
				WebElement presetDiv = driver.findElement(By.id("preset"));
				List<WebElement> listaIntervalos = presetDiv.findElements(By.tagName("span"));
				for (WebElement intervalo : listaIntervalos)
				{
					if (intervalo.getAttribute("innerHTML").indexOf("1&nbsp;mes") != -1)
					{
						WebDriverBase.clickElement(driver, intervalo);
						break;
					}
				}
				continue;
			}
			else if (elemento.getAttribute("innerHTML").indexOf("Línea") != -1)
			{
				WebDriverBase.clickElement(driver, elemento);
				WebElement presetDiv = driver.findElement(By.id("dropdown-menu"));
				List<WebElement> listaTiposChart = presetDiv.findElements(By.tagName("span"));
				for (WebElement tipoChart : listaTiposChart)
				{
					if (tipoChart.getAttribute("innerHTML").indexOf("Vela") != -1)
					{
						WebDriverBase.clickElement(driver, tipoChart);
						break;
					}
				}
				continue;
			}
		}
		LOGGER.info("Esperamos 500 milisegundos");
		Thread.sleep(500);
		if (doubleResultRPD != null)
		{
			LOGGER.info("Generando screenshot --> " + Utils.normalizaTexto(resultNombreEmpresa) + ";" + resultRPD.replaceAll("\\.", ",") + ";" + resultCAP.replaceAll("\\.", ",") + ";" + resultCAP_UM + ";" + hrefElemento);
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(downloadPath + "\\" + "RPD_" + doubleResultRPD.intValue() + "\\" + URLEncoder.encode(hrefElemento, WebDriverBase.CHARSET) + timeFrame + ".png"));
		}
		else
		{
			LOGGER.info("No se obtiene el chart de [" + Utils.normalizaTexto(resultNombreEmpresa) + "] [" + hrefElemento + "] porque la RPD [" + resultRPD + "] no está disponible");
			LOGGER.info("NO screenshot --> " + Utils.normalizaTexto(resultNombreEmpresa) + ";" + hrefElemento + ";-;-");
		}
	}

}
