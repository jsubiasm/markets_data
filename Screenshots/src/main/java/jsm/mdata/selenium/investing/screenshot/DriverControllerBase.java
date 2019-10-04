/**
 * 
 */
package jsm.mdata.selenium.investing.screenshot;

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

/**
 * @author Empleado
 *
 */
public abstract class DriverControllerBase
{

	/**
	 * Web Driver
	 */
	protected static final String WEB_DRIVER_PROPERTY = "webdriver.chrome.driver";
	protected static final String WEB_DRIVER_EXE = "C:\\_PELAYO\\Software\\Selenium\\drivers\\chromedriver.exe";

	/**
	 * Rutas
	 */
	protected static final String DOWNLOAD_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\Screenshots\\investing\\download";

	/**
	 * 
	 */
	protected static final String TF_DIARIO = "interval=D";
	protected static final String TF_SEMANAL = "interval=W";
	protected static final String TF_MENSUAL = "interval=M";

	/**
	 * Formatos
	 */
	private static final String CHARSET = "UTF-8";

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
		LOGGER.info("Cargando URL [" + hrefElemento + "]");
		driver.get(hrefElemento);

		if (minCapitalizacion != null)
		{
			LOGGER.info("Buscando Capitalización");
			try
			{
				new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Gráfico técnico")));
				WebElement tablaDatos = driver.findElement(By.className("overviewDataTableWithTooltip"));
				List<WebElement> listaInlineBlock = tablaDatos.findElements(By.className("inlineblock"));
				for (WebElement inlineBlock : listaInlineBlock)
				{
					WebElement floatLangBase1 = inlineBlock.findElement(By.className("float_lang_base_1"));
					if (floatLangBase1.getAttribute("innerHTML") != null && floatLangBase1.getAttribute("innerHTML").equalsIgnoreCase("Cap. mercado"))
					{
						WebElement floatLangBase2 = inlineBlock.findElement(By.className("float_lang_base_2"));
						String capitalizacionStr = floatLangBase2.getAttribute("innerHTML");
						if (capitalizacionStr.endsWith("B"))
						{
							capitalizacionStr = capitalizacionStr.substring(0, capitalizacionStr.indexOf("B"));
							capitalizacionStr = capitalizacionStr.replaceAll(",", ".");
							Double capitalizacion = Double.valueOf(capitalizacionStr);
							if (capitalizacion < minCapitalizacion)
							{
								LOGGER.info("No se obtiene el chart de [" + hrefElemento + "] porque la capitalización [" + capitalizacion + "] B es menor que la capitalización mínima [" + minCapitalizacion + "] B");
								return;
							}
							else
							{
								break;
							}
						}
						else
						{
							LOGGER.info("No se obtiene el chart de [" + hrefElemento + "] porque la capitalización [" + capitalizacionStr + "] no supera la capitalización mínima [" + minCapitalizacion + "] B");
							return;
						}
					}
				}
			}
			catch (Exception e)
			{
				LOGGER.error("Error al obtener la Capitalización", e);
				return;
			}
		}

		if (minDividendo != null)
		{
			LOGGER.info("Buscando Dividendo");
			try
			{
				new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Gráfico técnico")));
				WebElement tablaDatos = driver.findElement(By.className("overviewDataTableWithTooltip"));
				List<WebElement> listaInlineBlock = tablaDatos.findElements(By.className("inlineblock"));
				for (WebElement inlineBlock : listaInlineBlock)
				{
					WebElement floatLangBase1 = inlineBlock.findElement(By.className("float_lang_base_1"));
					if (floatLangBase1.getAttribute("innerHTML") != null && floatLangBase1.getAttribute("innerHTML").equalsIgnoreCase("Dividendo"))
					{
						WebElement floatLangBase2 = inlineBlock.findElement(By.className("float_lang_base_2"));
						String dividendoStr = floatLangBase2.getAttribute("innerHTML");
						dividendoStr = dividendoStr.substring(dividendoStr.indexOf("(") + 1, dividendoStr.indexOf(")") - 1);
						dividendoStr = dividendoStr.replaceAll(",", ".");
						Double dividendo = Double.valueOf(dividendoStr);
						if (dividendo < minDividendo)
						{
							LOGGER.info("No se obtiene el chart porque la RPD [" + dividendo + "] es menor que la RPD mínima [" + minDividendo + "]");
							return;
						}
						else
						{
							break;
						}
					}
				}
			}
			catch (Exception e)
			{
				LOGGER.error("Error al obtener la RPD", e);
				return;
			}
		}

		LOGGER.info("Accediendo gráfico técnico");
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Gráfico técnico")));
		WebElement graficoLink = driver.findElement(By.partialLinkText("Gráfico técnico"));
		graficoLink.click();

		LOGGER.info("Recuperando URL primer IFrame");
		List<WebElement> listaIFrames = driver.findElements(By.tagName("iframe"));
		String iframe01Url = null;
		for (WebElement iframe : listaIFrames)
		{
			if (iframe.getAttribute("src") != null && iframe.getAttribute("src").indexOf("tvc4.forexpros.com") != -1)
			{
				iframe01Url = iframe.getAttribute("src");
				break;
			}
		}

		LOGGER.info("Cargando URL [" + iframe01Url + "]");
		driver.get(iframe01Url);

		LOGGER.info("Recuperando URL segundo IFrame");
		WebElement iframe02 = driver.findElement(By.tagName("iframe"));
		String iframe02Url = iframe02.getAttribute("src").replaceAll(TF_DIARIO, timeFrame);

		LOGGER.info("Cargando URL [" + iframe02Url + "]");
		driver.get(iframe02Url);

		LOGGER.info("Esperando gráfico en tercer IFrame");
		new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("pane-controls")));

		LOGGER.info("Buscando botonera tipo gráfico");
		List<WebElement> listaDivsQuick = driver.findElements(By.className("quick"));
		int element = 0;
		for (WebElement divQuick : listaDivsQuick)
		{
			if (element == 0)
			{
				element++;
				continue;
			}

			LOGGER.info("Seleccionando gráfico de velas");
			WebElement spanVelas = divQuick.findElement(By.tagName("span"));
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(spanVelas));
			spanVelas.click();

		}

		LOGGER.info("Buscando botonera escala gráfico");
		WebElement divBotoneraEscala = driver.findElement(By.className("chart-series-controls"));
		List<WebElement> listaBotonesEscala = divBotoneraEscala.findElements(By.className("apply-common-tooltip"));
		int elementIdx2 = 0;
		for (WebElement boton : listaBotonesEscala)
		{
			if (elementIdx2 == 2)
			{

				LOGGER.info("Seleccionando escala logarítmica");
				new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(boton));
				boton.click();

			}
			elementIdx2++;
		}

		LOGGER.info("Esperamos 500 milisegundos");
		Thread.sleep(500);

		LOGGER.info("Generando screenshot");
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(downloadPath + "\\" + URLEncoder.encode(hrefElemento, CHARSET) + timeFrame + ".png"));
	}

	/**
	 * @param driver
	 */
	protected static void gestionError(WebDriver driver)
	{
		LOGGER.info("Intentando cerrar popup bloqueante");
		try
		{
			new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By.className("popupCloseIcon")));
			WebElement closePopUp = driver.findElement(By.className("popupCloseIcon"));
			closePopUp.click();
		}
		catch (Exception e)
		{
			LOGGER.error("Error intentando cerrar popup bloqueante", e);
		}
	}

}
