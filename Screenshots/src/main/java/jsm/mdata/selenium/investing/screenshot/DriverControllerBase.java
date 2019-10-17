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
import org.openqa.selenium.SearchContext;
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
		LOGGER.info("Cargando URL [" + hrefElemento + "] ");
		driver.get(hrefElemento);

		Double capitalizacion = null;
		if (minCapitalizacion != null)
		{
			LOGGER.info("Buscando Capitalizaci�n");
			try
			{
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("overviewDataTableWithTooltip")));
				WebElement tablaDatos = driver.findElement(By.className("overviewDataTableWithTooltip"));
				List<WebElement> listaInlineBlock = tablaDatos.findElements(By.className("inlineblock"));
				for (WebElement inlineBlock : listaInlineBlock)
				{
					WebElement floatLangBase1 = inlineBlock.findElement(By.className("float_lang_base_1"));
					if (floatLangBase1.getAttribute("innerHTML") != null && floatLangBase1.getAttribute("innerHTML").equalsIgnoreCase("Cap. mercado"))
					{
						WebElement floatLangBase2 = inlineBlock.findElement(By.className("float_lang_base_2"));
						String capitalizacionStr = floatLangBase2.getAttribute("innerHTML");
						if (capitalizacionStr.endsWith("T"))
						{
							break;
						}
						else if (capitalizacionStr.endsWith("B"))
						{
							capitalizacionStr = capitalizacionStr.substring(0, capitalizacionStr.indexOf("B"));
							capitalizacionStr = capitalizacionStr.replaceAll("\\.", "");
							capitalizacionStr = capitalizacionStr.replaceAll(",", ".");
							capitalizacion = Double.valueOf(capitalizacionStr);
							if (capitalizacion < minCapitalizacion)
							{
								LOGGER.info("No se obtiene el chart de [" + hrefElemento + "] porque la capitalizaci�n [" + capitalizacion + "] B es menor que la capitalizaci�n m�nima [" + minCapitalizacion + "] B");
								return;
							}
							else
							{
								break;
							}
						}
						else
						{
							LOGGER.info("No se obtiene el chart de [" + hrefElemento + "] porque la capitalizaci�n [" + capitalizacionStr + "] no supera la capitalizaci�n m�nima [" + minCapitalizacion + "] B");
							return;
						}
					}
				}
			}
			catch (Exception e)
			{
				LOGGER.error("Error al obtener la Capitalizaci�n", e);
				return;
			}
		}

		Double dividendo = null;
		if (minDividendo != null)
		{
			LOGGER.info("Buscando Dividendo");
			try
			{
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("overviewDataTableWithTooltip")));
				WebElement tablaDatos = driver.findElement(By.className("overviewDataTableWithTooltip"));
				List<WebElement> listaInlineBlock = tablaDatos.findElements(By.className("inlineblock"));
				for (WebElement inlineBlock : listaInlineBlock)
				{
					WebElement floatLangBase1 = inlineBlock.findElement(By.className("float_lang_base_1"));
					if (floatLangBase1.getAttribute("innerHTML") != null && floatLangBase1.getAttribute("innerHTML").equalsIgnoreCase("Dividendo"))
					{
						WebElement floatLangBase2 = inlineBlock.findElement(By.className("float_lang_base_2"));
						String dividendoStr = floatLangBase2.getAttribute("innerHTML");
						if (dividendoStr.indexOf("N/A") != -1)
						{
							LOGGER.info("No se obtiene el chart porque la RPD [" + dividendoStr + "] no est� disponible");
							return;
						}
						dividendoStr = dividendoStr.substring(dividendoStr.indexOf("(") + 1, dividendoStr.indexOf(")") - 1);
						dividendoStr = dividendoStr.replaceAll("\\.", "");
						dividendoStr = dividendoStr.replaceAll(",", ".");
						dividendo = Double.valueOf(dividendoStr);
						if (dividendo < minDividendo)
						{
							LOGGER.info("No se obtiene el chart porque la RPD [" + dividendo + "] es menor que la RPD m�nima [" + minDividendo + "] ");
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

		LOGGER.info("Buscando sector, industria y nombre de empresa");
		String industria = "";
		String sector = "";
		String nombreEmpresa = "";
		WebElement companyProfileHeader = driver.findElement(By.className("companyProfileHeader"));
		List<WebElement> listaDatosProfile = companyProfileHeader.findElements(By.tagName("div"));
		for (WebElement datoProfile : listaDatosProfile)
		{
			if (datoProfile.getAttribute("innerHTML") != null && datoProfile.getAttribute("innerHTML").startsWith("Industria"))
			{
				industria = datoProfile.findElements(By.tagName("a")).get(0).getAttribute("innerHTML").trim();
			}
			else if (datoProfile.getAttribute("innerHTML") != null && datoProfile.getAttribute("innerHTML").startsWith("Sector"))
			{
				sector = datoProfile.findElements(By.tagName("a")).get(0).getAttribute("innerHTML").trim();
			}
		}
		WebElement instrumentHead = driver.findElement(By.className("instrumentHead"));
		nombreEmpresa = instrumentHead.findElements(By.tagName("h1")).get(0).getAttribute("innerHTML").trim();

		LOGGER.info("Buscando PER");
		String per = "";
		// try
		// {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("overviewDataTableWithTooltip")));
		WebElement tablaDatos = driver.findElement(By.className("overviewDataTableWithTooltip"));
		List<WebElement> listaInlineBlock = tablaDatos.findElements(By.className("inlineblock"));
		for (WebElement inlineBlock : listaInlineBlock)
		{
			WebElement floatLangBase1 = inlineBlock.findElement(By.className("float_lang_base_1"));
			if (floatLangBase1.getAttribute("innerHTML") != null && floatLangBase1.getAttribute("innerHTML").equalsIgnoreCase("PER"))
			{
				WebElement floatLangBase2 = inlineBlock.findElement(By.className("float_lang_base_2"));
				String perStr = floatLangBase2.getAttribute("innerHTML");
				per = perStr;

				// if (perStr.indexOf("N/A") != -1)
				// {
				// LOGGER.info("El PER [" + perStr + "] no est� disponible");
				// }
				// else
				// {
				// perStr = perStr.replaceAll("\\.", "");
				// perStr = perStr.replaceAll(",", ".");
				// Double perDouble = Double.valueOf(perStr);
				// per = perDouble.toString();
				// }

				break;
			}
		}
		// }
		// catch (Exception e)
		// {
		// LOGGER.error("Error al obtener el PER", e);
		// }

		LOGGER.info("Accediendo a ratios");

		// String precioUtilidadEmprs = "";
		// String precioUtilidadIndus = "";
		String precioVentasEmprs = "";
		String precioVentasIndus = "";
		String precioFlujoCajaEmprs = "";
		String precioFlujoCajaIndus = "";
		// String precioFlujoCajaLibreEmprs = "";
		// String precioFlujoCajaLibreIndus = "";
		String precioValorContableEmprs = "";
		String precioValorContableIndus = "";
		// String precioValorContableNetoEmprs = "";
		// String precioValorContableNetoIndus = "";
		String margenBrutoTTMEmprs = "";
		String margenBrutoTTMIndus = "";
		String margenBruto5YAEmprs = "";
		String margenBruto5YAIndus = "";
		String margenOperativoTTMEmprs = "";
		String margenOperativoTTMIndus = "";
		String margenOperativo5YAEmprs = "";
		String margenOperativo5YAIndus = "";
		String margenAntesImpuestosTTMEmprs = "";
		String margenAntesImpuestosTTMIndus = "";
		String margenAntesImpuestos5YAEmprs = "";
		String margenAntesImpuestos5YAIndus = "";
		String margenUtilidadNetoTTMEmprs = "";
		String margenUtilidadNetoTTMIndus = "";
		String margenUtilidadNeto5YAEmprs = "";
		String margenUtilidadNeto5YAIndus = "";
		String beneficioAccionEmprs = "";
		String beneficioAccionIndus = "";
		// String BPABasicoEmprs = "";
		// String BPABasicoIndus = "";
		// String BPADiluidoEmprs = "";
		// String BPADiluidoIndus = "";
		String bolsaLibrosEmprs = "";
		String bolsaLibrosIndus = "";
		String valorContableNetoAccionEmprs = "";
		String valorContableNetoAccionIndus = "";
		String efectivoAccionEmprs = "";
		String efectivoAccionIndus = "";
		String flujoCajaAccionEmprs = "";
		String flujoCajaAccionIndus = "";
		String rentabilidadSobreInversionTTMEmprs = "";
		String rentabilidadSobreInversionTTMIndus = "";
		String rentabilidadSobreInversion5YAEmprs = "";
		String rentabilidadSobreInversion5YAIndus = "";
		String rentabilidadSobreActivosTTMEmprs = "";
		String rentabilidadSobreActivosTTMIndus = "";
		String rentabilidadSobreActivos5YAEmprs = "";
		String rentabilidadSobreActivos5YAIndus = "";
		String rentabilidadSobreFondoPropiosTTMEmprs = "";
		String rentabilidadSobreFondoPropiosTTMIndus = "";
		String rentabilidadSobreFondoPropios5YAEmprs = "";
		String rentabilidadSobreFondoPropios5YAIndus = "";
		String BPAVsTrimestre1AnioAtrasEmprs = "";
		String BPAVsTrimestre1AnioAtrasIndus = "";
		String BPAVs1AnioAtrasEmprs = "";
		String BPAVs1AnioAtrasIndus = "";
		String crecimientoBPA5YAEmprs = "";
		String crecimientoBPA5YAIndus = "";
		String ventasVsTrimestre1AnioAtrasEmprs = "";
		String ventasVsTrimestre1AnioAtrasIndus = "";
		String ventasVs1AnioAtrasEmprs = "";
		String ventasVs1AnioAtrasIndus = "";
		String crecimientoVentas5YAEmprs = "";
		String crecimientoVentas5YAIndus = "";
		String crecimientoConsumoCapital5YAEmprs = "";
		String crecimientoConsumoCapital5YAIndus = "";
		String testAcidoEmprs = "";
		String testAcidoIndus = "";
		String ratioSolvenciaEmprs = "";
		String ratioSolvenciaIndus = "";
		String deudaLPTotalFondosPropiosEmprs = "";
		String deudaLPTotalFondosPropiosIndus = "";
		String totalDeudaTotalFondosPropiosEmprs = "";
		String totalDeudaTotalFondosPropiosIndus = "";
		// String rotacionActivosEmprs = "";
		// String rotacionActivosIndus = "";
		// String rotacionInventariosEmprs = "";
		// String rotacionInventariosIndus = "";
		// String ingresosEmpleadoEmprs = "";
		// String ingresosEmpleadoIndus = "";
		// String beneficioNetoEmpleadoEmprs = "";
		// String beneficioNetoEmpleadoIndus = "";
		// String rotacionCuentasCobrarEmprs = "";
		// String rotacionCuentasCobrarIndus = "";
		String rentabilidadPorDividendoEmprs = "";
		String rentabilidadPorDividendoIndus = "";
		String promedioRendimientoDividendo5YAEmprs = "";
		String promedioRendimientoDividendo5YAIndus = "";
		String tasaCrecimientoDividendosEmprs = "";
		String tasaCrecimientoDividendosIndus = "";
		String ratioPayoutEmprs = "";
		String ratioPayoutIndus = "";

		// new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Fundamental")));
		// WebElement fundamentalLink = driver.findElement(By.partialLinkText("Fundamental"));
		// fundamentalLink.click();

		clickElementByLinkText(driver, driver, "Fundamental");

		// new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Ratios")));
		// WebElement ratiosLink = driver.findElement(By.partialLinkText("Ratios"));
		// ratiosLink.click();

		clickElementByLinkText(driver, driver, "Ratios");

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("reportTbl")));
		List<WebElement> reportTableList = driver.findElements(By.className("reportTbl"));
		for (WebElement reportTable : reportTableList)
		{
			List<WebElement> datosReportList = reportTable.findElements(By.tagName("tr"));
			for (WebElement datoReport : datosReportList)
			{
				List<WebElement> celdaDatoList = datoReport.findElements(By.tagName("td"));
				// if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Ratio Precio/Utilidad <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				// {
				// precioUtilidadEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
				// precioUtilidadIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				// }
				if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Precio/Ventas <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					precioVentasEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					precioVentasIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Precio/Flujo de caja <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					precioFlujoCajaEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					precioFlujoCajaIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				// else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Precio/Flujo de caja libre <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				// {
				// precioFlujoCajaLibreEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
				// precioFlujoCajaLibreIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				// }
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Precio/Valor Contable <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					precioValorContableEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					precioValorContableIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				// else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Precio/Valor Contable Neto <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				// {
				// precioValorContableNetoEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
				// precioValorContableNetoIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				// }
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Margen bruto <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					margenBrutoTTMEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					margenBrutoTTMIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Margen bruto <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					margenBruto5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					margenBruto5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Margen operativo <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					margenOperativoTTMEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					margenOperativoTTMIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Margen operativo <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					margenOperativo5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					margenOperativo5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Margen Antes de Impuestos <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					margenAntesImpuestosTTMEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					margenAntesImpuestosTTMIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Margen Antes de Impuestos <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					margenAntesImpuestos5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					margenAntesImpuestos5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Margen de utilidad neto <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					margenUtilidadNetoTTMEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					margenUtilidadNetoTTMIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Margen de utilidad neto <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					margenUtilidadNeto5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					margenUtilidadNeto5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Beneficio/Acci�n <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					beneficioAccionEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					beneficioAccionIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				// else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">BPA B�sico <i class=\"lighterGrayFont arial_11\">ANN<") != -1)
				// {
				// BPABasicoEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
				// BPABasicoIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				// }
				// else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">BPA Diluido <i class=\"lighterGrayFont arial_11\">ANN<") != -1)
				// {
				// BPADiluidoEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
				// BPADiluidoIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				// }
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Ratio Bolsa/Libros <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					bolsaLibrosEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					bolsaLibrosIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Valor Contable Neto/Acci�n <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					valorContableNetoAccionEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					valorContableNetoAccionIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Efectivo/Acci�n <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					efectivoAccionEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					efectivoAccionIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Flujo de caja/Acci�n <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					flujoCajaAccionEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					flujoCajaAccionIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Rentabilidad sobre la inversi�n <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					rentabilidadSobreInversionTTMEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					rentabilidadSobreInversionTTMIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Rentabilidad sobre la inversi�n <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					rentabilidadSobreInversion5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					rentabilidadSobreInversion5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Rentabilidad sobre los activos <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					rentabilidadSobreActivosTTMEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					rentabilidadSobreActivosTTMIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Rentabilidad sobre los activos <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					rentabilidadSobreActivos5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					rentabilidadSobreActivos5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Rentabilidad sobre fondos propios <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					rentabilidadSobreFondoPropiosTTMEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					rentabilidadSobreFondoPropiosTTMIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Rentabilidad sobre fondos propios <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					rentabilidadSobreFondoPropios5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					rentabilidadSobreFondoPropios5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">BPA(MRQ) vs Trimestre 1 A�o Atr�s <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					BPAVsTrimestre1AnioAtrasEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					BPAVsTrimestre1AnioAtrasIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">BPA(TTM) vs TTM 1 A�o Atr�s <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					BPAVs1AnioAtrasEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					BPAVs1AnioAtrasIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Crecimiento del BPA en 5 A�os <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					crecimientoBPA5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					crecimientoBPA5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Ventas (MRQ) vs Trimestre 1 A�o Atr�s <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					ventasVsTrimestre1AnioAtrasEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					ventasVsTrimestre1AnioAtrasIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Ventas (TTM) vs TTM 1 A�o Atr�s <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					ventasVs1AnioAtrasEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					ventasVs1AnioAtrasIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Crecimiento de las Ventas en 5 A�os <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					crecimientoVentas5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					crecimientoVentas5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Crecimiento del Consumo de Capital en 5 A�os <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					crecimientoConsumoCapital5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					crecimientoConsumoCapital5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Test �cido <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					testAcidoEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					testAcidoIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Ratio de solvencia <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					ratioSolvenciaEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					ratioSolvenciaIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Deuda a largo plazo/Total fondos propios <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					deudaLPTotalFondosPropiosEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					deudaLPTotalFondosPropiosIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Total deuda/Total fondos propios <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					totalDeudaTotalFondosPropiosEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					totalDeudaTotalFondosPropiosIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				// else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Rotaci�n de Activos <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				// {
				// rotacionActivosEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
				// rotacionActivosIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				// }
				// else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Rotaci�n de Inventarios <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				// {
				// rotacionInventariosEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
				// rotacionInventariosIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				// }
				// else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Ingresos /Empleado <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				// {
				// ingresosEmpleadoEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
				// ingresosEmpleadoIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				// }
				// else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Beneficio neto/Empleado <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				// {
				// beneficioNetoEmpleadoEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
				// beneficioNetoEmpleadoIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				// }
				// else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Rotaci�n de Cuentas a Cobrar <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				// {
				// rotacionCuentasCobrarEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
				// rotacionCuentasCobrarIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				// }
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Rentabilidad por Dividendo <i class=\"lighterGrayFont arial_11\">ANN<") != -1)
				{
					rentabilidadPorDividendoEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					rentabilidadPorDividendoIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Promedio de Rendimiento del Dividendo en 5 A�os <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					promedioRendimientoDividendo5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					promedioRendimientoDividendo5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Tasa de Crecimiento de los Dividendos <i class=\"lighterGrayFont arial_11\">ANN<") != -1)
				{
					tasaCrecimientoDividendosEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					tasaCrecimientoDividendosIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Ratio Payout <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					ratioPayoutEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					ratioPayoutIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
			}
		}

		LOGGER.info("Accediendo gr�fico t�cnico");

		// new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Gr�fico t�cnico")));
		// WebElement graficoLink = driver.findElement(By.partialLinkText("Gr�fico t�cnico"));
		// graficoLink.click();

		clickElementByLinkText(driver, driver, "Gr�fico");

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

		LOGGER.info("Cargando URL [" + iframe01Url + "] ");
		driver.get(iframe01Url);

		LOGGER.info("Recuperando URL segundo IFrame");
		WebElement iframe02 = driver.findElement(By.tagName("iframe"));
		String iframe02Url = iframe02.getAttribute("src").replaceAll(TF_DIARIO, timeFrame);

		LOGGER.info("Cargando URL [" + iframe02Url + "] ");
		driver.get(iframe02Url);

		LOGGER.info("Esperando gr�fico en tercer IFrame");
		new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("pane-controls")));

		LOGGER.info("Buscando botonera tipo gr�fico");
		List<WebElement> listaDivsQuick = driver.findElements(By.className("quick"));
		int element = 0;
		for (WebElement divQuick : listaDivsQuick)
		{
			if (element == 0)
			{
				element++;
				continue;
			}

			LOGGER.info("Seleccionando gr�fico de velas");

			// WebElement spanVelas = divQuick.findElement(By.tagName("span"));
			// new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(spanVelas));
			// spanVelas.click();

			clickElementByTagName(driver, divQuick, "span");

		}

		LOGGER.info("Buscando botonera escala gr�fico");
		WebElement divBotoneraEscala = driver.findElement(By.className("chart-series-controls"));
		List<WebElement> listaBotonesEscala = divBotoneraEscala.findElements(By.className("apply-common-tooltip"));
		int elementIdx2 = 0;
		for (WebElement boton : listaBotonesEscala)
		{
			if (elementIdx2 == 2)
			{

				LOGGER.info("Seleccionando escala logar�tmica");

				// new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(boton));
				// boton.click();

				clickElement(driver, boton);

			}
			elementIdx2++;
		}

		LOGGER.info("Esperamos 500 milisegundos");
		Thread.sleep(500);

		String loggerExcelLine = "Generando screenshot [" + sector + "] [" + industria + "] [" + nombreEmpresa + "] [" + dividendo + "] [" + capitalizacion + "] [" + per + "] ";
		loggerExcelLine = loggerExcelLine + "[" + precioVentasEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + precioVentasIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + precioFlujoCajaEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + precioFlujoCajaIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + precioValorContableEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + precioValorContableIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenBrutoTTMEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenBrutoTTMIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenBruto5YAEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenBruto5YAIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenOperativoTTMEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenOperativoTTMIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenOperativo5YAEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenOperativo5YAIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenAntesImpuestosTTMEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenAntesImpuestosTTMIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenAntesImpuestos5YAEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenAntesImpuestos5YAIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenUtilidadNetoTTMEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenUtilidadNetoTTMIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenUtilidadNeto5YAEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + margenUtilidadNeto5YAIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + beneficioAccionEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + beneficioAccionIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + bolsaLibrosEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + bolsaLibrosIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + valorContableNetoAccionEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + valorContableNetoAccionIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + efectivoAccionEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + efectivoAccionIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + flujoCajaAccionEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + flujoCajaAccionIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadSobreInversionTTMEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadSobreInversionTTMIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadSobreInversion5YAEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadSobreInversion5YAIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadSobreActivosTTMEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadSobreActivosTTMIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadSobreActivos5YAEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadSobreActivos5YAIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadSobreFondoPropiosTTMEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadSobreFondoPropiosTTMIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadSobreFondoPropios5YAEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadSobreFondoPropios5YAIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + BPAVsTrimestre1AnioAtrasEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + BPAVsTrimestre1AnioAtrasIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + BPAVs1AnioAtrasEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + BPAVs1AnioAtrasIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + crecimientoBPA5YAEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + crecimientoBPA5YAIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + ventasVsTrimestre1AnioAtrasEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + ventasVsTrimestre1AnioAtrasIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + ventasVs1AnioAtrasEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + ventasVs1AnioAtrasIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + crecimientoVentas5YAEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + crecimientoVentas5YAIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + crecimientoConsumoCapital5YAEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + crecimientoConsumoCapital5YAIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + testAcidoEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + testAcidoIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + ratioSolvenciaEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + ratioSolvenciaIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + deudaLPTotalFondosPropiosEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + deudaLPTotalFondosPropiosIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + totalDeudaTotalFondosPropiosEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + totalDeudaTotalFondosPropiosIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadPorDividendoEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + rentabilidadPorDividendoIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + promedioRendimientoDividendo5YAEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + promedioRendimientoDividendo5YAIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + tasaCrecimientoDividendosEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + tasaCrecimientoDividendosIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + ratioPayoutEmprs + "] ";
		loggerExcelLine = loggerExcelLine + "[" + ratioPayoutIndus + "] ";
		loggerExcelLine = loggerExcelLine + "[" + hrefElemento + "] ";

		LOGGER.info(loggerExcelLine);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(downloadPath + "\\" + "RPD_" + dividendo.intValue() + "\\" + URLEncoder.encode(hrefElemento, CHARSET) + timeFrame + ".png"));
	}

	/**
	 * @param driver
	 */
	protected static void gestionError(WebDriver driver)
	{
		LOGGER.info("Intentando cerrar popup bloqueante");
		try
		{

			// new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By.className("popupCloseIcon")));
			// WebElement closePopUp = driver.findElement(By.className("popupCloseIcon"));
			// closePopUp.click();

			clickElementByClassName(driver, driver, "popupCloseIcon");
		}
		catch (Exception e)
		{
			LOGGER.error("Error intentando cerrar popup bloqueante", e);
		}
	}

	/**
	 * @param driver
	 * @param context
	 * @param linkText
	 */
	protected static void clickElementByLinkText(WebDriver driver, SearchContext context, String linkText)
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
		WebElement element = context.findElement(By.linkText(linkText));
		element.click();
	}

	/**
	 * @param driver
	 * @param context
	 * @param className
	 */
	protected static void clickElementByClassName(WebDriver driver, SearchContext context, String className)
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.className(className)));
		WebElement element = context.findElement(By.className(className));
		element.click();
	}

	/**
	 * @param driver
	 * @param context
	 * @param tagName
	 */
	protected static void clickElementByTagName(WebDriver driver, SearchContext context, String tagName)
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.tagName(tagName)));
		WebElement element = context.findElement(By.tagName(tagName));
		element.click();
	}

	/**
	 * @param driver
	 * @param element
	 */
	protected static void clickElement(WebDriver driver, WebElement element)
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

}
