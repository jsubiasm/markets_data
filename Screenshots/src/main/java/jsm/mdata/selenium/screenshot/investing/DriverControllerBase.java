/**
 * 
 */
package jsm.mdata.selenium.screenshot.investing;

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
	protected static final String DOWNLOAD_PATH = "C:\\_PELAYO\\Software\\Eclipse Neon\\workspace\\markets_data\\Screenshots\\investing\\download";

	/**
	 * 
	 */
	protected static final String TF_DIARIO = "interval=D";
	protected static final String TF_SEMANAL = "interval=W";
	protected static final String TF_MENSUAL = "interval=M";

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DriverControllerBase.class);

	/**
	 * @param driver
	 * @param hrefElemento
	 * @param downloadPath
	 * @param timeFrame
	 * @param minDividendo
	 * @param minCapitalizacion
	 * @param nacionalidades
	 * @param esAristocrata
	 * @throws Exception
	 */
	protected static void procesarElemento(WebDriver driver, String hrefElemento, String downloadPath, String timeFrame, Double minDividendo, Double minCapitalizacion, String[] nacionalidades, boolean esAristocrata) throws Exception
	{
		String labelAristocrata = esAristocrata ? "S" : "-";

		LOGGER.info("Cargando URL [" + hrefElemento + "] ");
		driver.get(hrefElemento);

		LOGGER.info("Buscando nombre de empresa");
		WebElement instrumentHead = driver.findElement(By.className("instrumentHead"));
		String nombreEmpresa = instrumentHead.findElements(By.tagName("h1")).get(0).getAttribute("innerHTML").trim();

		String nacionalidad = null;
		if (nacionalidades != null && nacionalidades.length > 0)
		{
			LOGGER.info("Buscando Nacionalidad");
			try
			{
				new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("DropdownBtn")));
				WebElement botonNacionalidad = driver.findElement(By.id("DropdownBtn"));
				WebElement bandera = botonNacionalidad.findElement(By.tagName("span"));
				nacionalidad = bandera.getAttribute("class").trim();
				nacionalidad = nacionalidad.substring(7).trim();
				boolean nacionalidadOk = false;
				for (int i = 0; i < nacionalidades.length; i++)
				{
					if (nacionalidades[i].equalsIgnoreCase(nacionalidad))
					{
						nacionalidadOk = true;
						break;
					}
				}
				if (!nacionalidadOk)
				{
					LOGGER.info("No se obtiene el chart de [" + nombreEmpresa.replaceAll("&amp;", "&") + "] [" + hrefElemento + "] porque la Nacionalidad [" + nacionalidad + "] no es ninguna de las seleccionadas");
					LOGGER.info("NO screenshot --> " + nombreEmpresa.replaceAll("&amp;", "&") + ";" + hrefElemento + ";-;-;" + nacionalidad + ";" + labelAristocrata);
					return;
				}
			}
			catch (Exception e)
			{
				LOGGER.error("Error al obtener la Nacionalidad de [" + nombreEmpresa + "] [" + hrefElemento + "]", e);
				LOGGER.info("NO screenshot --> " + nombreEmpresa.replaceAll("&amp;", "&") + ";" + hrefElemento + ";-;-;-;" + labelAristocrata);
				return;
			}
		}

		Double capitalizacion = null;
		if (minCapitalizacion != null)
		{
			LOGGER.info("Buscando Capitalización");
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
								LOGGER.info("No se obtiene el chart de [" + nombreEmpresa.replaceAll("&amp;", "&") + "] [" + hrefElemento + "] porque la capitalización [" + capitalizacion + "] B es menor que la capitalización mínima [" + minCapitalizacion + "] B");
								LOGGER.info("NO screenshot --> " + nombreEmpresa.replaceAll("&amp;", "&") + ";" + hrefElemento + ";" + capitalizacion + ";-;-;" + labelAristocrata);
								return;
							}
							else
							{
								break;
							}
						}
						else
						{
							LOGGER.info("No se obtiene el chart de [" + nombreEmpresa.replaceAll("&amp;", "&") + "] [" + hrefElemento + "] porque la capitalización [" + capitalizacionStr + "] no supera la capitalización mínima [" + minCapitalizacion + "] B");
							LOGGER.info("NO screenshot --> " + nombreEmpresa.replaceAll("&amp;", "&") + ";" + hrefElemento + ";" + capitalizacionStr + ";-;-;" + labelAristocrata);
							return;
						}
					}
				}
			}
			catch (Exception e)
			{
				LOGGER.error("Error al obtener la Capitalización de [" + nombreEmpresa + "] [" + hrefElemento + "]", e);
				LOGGER.info("NO screenshot --> " + nombreEmpresa.replaceAll("&amp;", "&") + ";" + hrefElemento + ";-;-;-;" + labelAristocrata);
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
							LOGGER.info("No se obtiene el chart de [" + nombreEmpresa.replaceAll("&amp;", "&") + "] [" + hrefElemento + "] porque la RPD [" + dividendoStr + "] no está disponible");
							LOGGER.info("NO screenshot --> " + nombreEmpresa.replaceAll("&amp;", "&") + ";" + hrefElemento + ";-;-;-;" + labelAristocrata);
							return;
						}
						dividendoStr = dividendoStr.substring(dividendoStr.indexOf("(") + 1, dividendoStr.indexOf(")") - 1);
						dividendoStr = dividendoStr.replaceAll("\\.", "");
						dividendoStr = dividendoStr.replaceAll(",", ".");
						dividendo = Double.valueOf(dividendoStr);
						if (dividendo < minDividendo)
						{
							LOGGER.info("No se obtiene el chart de [" + nombreEmpresa.replaceAll("&amp;", "&") + "] [" + hrefElemento + "] porque la RPD [" + dividendo + "] es menor que la RPD mínima [" + minDividendo + "] ");
							LOGGER.info("NO screenshot --> " + nombreEmpresa.replaceAll("&amp;", "&") + ";" + hrefElemento + ";-;" + dividendo + ";-;" + labelAristocrata);
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
				LOGGER.error("Error al obtener la RPD de [" + nombreEmpresa + "] [" + hrefElemento + "]", e);
				LOGGER.info("NO screenshot --> " + nombreEmpresa.replaceAll("&amp;", "&") + ";" + hrefElemento + ";-;-;-;" + labelAristocrata);
				return;
			}
		}

		LOGGER.info("Buscando sector e industria");
		String industria = "";
		String sector = "";
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

		LOGGER.info("Buscando PER");
		String per = "";
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
				break;
			}
		}

		LOGGER.info("Accediendo a ratios");
		String precioVentasEmprs = "";
		String precioVentasIndus = "";
		String precioFlujoCajaEmprs = "";
		String precioFlujoCajaIndus = "";
		String precioValorContableEmprs = "";
		String precioValorContableIndus = "";
		String bolsaLibrosEmprs = "";
		String bolsaLibrosIndus = "";
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
		String testAcidoEmprs = "";
		String testAcidoIndus = "";
		String ratioSolvenciaEmprs = "";
		String ratioSolvenciaIndus = "";
		String deudaLPTotalFondosPropiosEmprs = "";
		String deudaLPTotalFondosPropiosIndus = "";
		String totalDeudaTotalFondosPropiosEmprs = "";
		String totalDeudaTotalFondosPropiosIndus = "";
		String rentabilidadPorDividendoEmprs = "";
		String rentabilidadPorDividendoIndus = "";
		String promedioRendimientoDividendo5YAEmprs = "";
		String promedioRendimientoDividendo5YAIndus = "";
		String tasaCrecimientoDividendosEmprs = "";
		String tasaCrecimientoDividendosIndus = "";
		String ratioPayoutEmprs = "";
		String ratioPayoutIndus = "";
		WebDriverBase.clickElementByLinkText(driver, driver, "Fundamental");
		WebDriverBase.clickElementByLinkText(driver, driver, "Ratios");
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("reportTbl")));
		List<WebElement> reportTableList = driver.findElements(By.className("reportTbl"));
		for (WebElement reportTable : reportTableList)
		{
			List<WebElement> datosReportList = reportTable.findElements(By.tagName("tr"));
			for (WebElement datoReport : datosReportList)
			{
				List<WebElement> celdaDatoList = datoReport.findElements(By.tagName("td"));
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
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Precio/Valor Contable <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					precioValorContableEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					precioValorContableIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Ratio Bolsa/Libros <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					bolsaLibrosEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					bolsaLibrosIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">BPA(MRQ) vs Trimestre 1 Año Atrás <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					BPAVsTrimestre1AnioAtrasEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					BPAVsTrimestre1AnioAtrasIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">BPA(TTM) vs TTM 1 Año Atrás <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					BPAVs1AnioAtrasEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					BPAVs1AnioAtrasIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Crecimiento del BPA en 5 Años <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					crecimientoBPA5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					crecimientoBPA5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Ventas (MRQ) vs Trimestre 1 Año Atrás <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
				{
					ventasVsTrimestre1AnioAtrasEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					ventasVsTrimestre1AnioAtrasIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Ventas (TTM) vs TTM 1 Año Atrás <i class=\"lighterGrayFont arial_11\">TTM<") != -1)
				{
					ventasVs1AnioAtrasEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					ventasVs1AnioAtrasIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Crecimiento de las Ventas en 5 Años <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
				{
					crecimientoVentas5YAEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					crecimientoVentas5YAIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Test ácido <i class=\"lighterGrayFont arial_11\">MRQ<") != -1)
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
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Rentabilidad por Dividendo <i class=\"lighterGrayFont arial_11\">ANN<") != -1)
				{
					rentabilidadPorDividendoEmprs = celdaDatoList.get(1).getAttribute("innerHTML").trim();
					rentabilidadPorDividendoIndus = celdaDatoList.get(2).getAttribute("innerHTML").trim();
				}
				else if (celdaDatoList.size() > 1 && celdaDatoList.get(0).getAttribute("innerHTML") != null && celdaDatoList.get(0).getAttribute("innerHTML").indexOf(">Promedio de Rendimiento del Dividendo en 5 Años <i class=\"lighterGrayFont arial_11\">5YA<") != -1)
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

		LOGGER.info("Accediendo gráfico técnico");
		WebDriverBase.clickElementByLinkText(driver, driver, "Gráfico");

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
			WebDriverBase.clickElementByTagName(driver, divQuick, "span");
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
				WebDriverBase.clickElement(driver, boton);
			}
			elementIdx2++;
		}

		LOGGER.info("Esperamos 500 milisegundos");
		Thread.sleep(500);

		String loggerExcelLine = "Generando screenshot --> ";
		loggerExcelLine = loggerExcelLine + labelAristocrata;
		loggerExcelLine = loggerExcelLine + ";" + nacionalidad;
		loggerExcelLine = loggerExcelLine + ";" + sector;
		loggerExcelLine = loggerExcelLine + ";" + industria;
		loggerExcelLine = loggerExcelLine + ";" + nombreEmpresa.replaceAll("&amp;", "&");
		loggerExcelLine = loggerExcelLine + ";" + dividendo.toString().replaceAll("\\.", ",");
		loggerExcelLine = loggerExcelLine + ";" + capitalizacion.toString().replaceAll("\\.", ",");
		loggerExcelLine = loggerExcelLine + ";" + per.replaceAll("N/A", "-");
		loggerExcelLine = loggerExcelLine + ";" + precioVentasEmprs;
		loggerExcelLine = loggerExcelLine + ";" + precioVentasIndus;
		loggerExcelLine = loggerExcelLine + ";" + precioFlujoCajaEmprs;
		loggerExcelLine = loggerExcelLine + ";" + precioFlujoCajaIndus;
		loggerExcelLine = loggerExcelLine + ";" + precioValorContableEmprs;
		loggerExcelLine = loggerExcelLine + ";" + precioValorContableIndus;
		loggerExcelLine = loggerExcelLine + ";" + bolsaLibrosEmprs;
		loggerExcelLine = loggerExcelLine + ";" + bolsaLibrosIndus;
		loggerExcelLine = loggerExcelLine + ";" + BPAVsTrimestre1AnioAtrasEmprs;
		loggerExcelLine = loggerExcelLine + ";" + BPAVsTrimestre1AnioAtrasIndus;
		loggerExcelLine = loggerExcelLine + ";" + BPAVs1AnioAtrasEmprs;
		loggerExcelLine = loggerExcelLine + ";" + BPAVs1AnioAtrasIndus;
		loggerExcelLine = loggerExcelLine + ";" + crecimientoBPA5YAEmprs;
		loggerExcelLine = loggerExcelLine + ";" + crecimientoBPA5YAIndus;
		loggerExcelLine = loggerExcelLine + ";" + ventasVsTrimestre1AnioAtrasEmprs;
		loggerExcelLine = loggerExcelLine + ";" + ventasVsTrimestre1AnioAtrasIndus;
		loggerExcelLine = loggerExcelLine + ";" + ventasVs1AnioAtrasEmprs;
		loggerExcelLine = loggerExcelLine + ";" + ventasVs1AnioAtrasIndus;
		loggerExcelLine = loggerExcelLine + ";" + crecimientoVentas5YAEmprs;
		loggerExcelLine = loggerExcelLine + ";" + crecimientoVentas5YAIndus;
		loggerExcelLine = loggerExcelLine + ";" + testAcidoEmprs;
		loggerExcelLine = loggerExcelLine + ";" + testAcidoIndus;
		loggerExcelLine = loggerExcelLine + ";" + ratioSolvenciaEmprs;
		loggerExcelLine = loggerExcelLine + ";" + ratioSolvenciaIndus;
		loggerExcelLine = loggerExcelLine + ";" + deudaLPTotalFondosPropiosEmprs;
		loggerExcelLine = loggerExcelLine + ";" + deudaLPTotalFondosPropiosIndus;
		loggerExcelLine = loggerExcelLine + ";" + totalDeudaTotalFondosPropiosEmprs;
		loggerExcelLine = loggerExcelLine + ";" + totalDeudaTotalFondosPropiosIndus;
		loggerExcelLine = loggerExcelLine + ";" + rentabilidadPorDividendoEmprs;
		loggerExcelLine = loggerExcelLine + ";" + rentabilidadPorDividendoIndus;
		loggerExcelLine = loggerExcelLine + ";" + promedioRendimientoDividendo5YAEmprs;
		loggerExcelLine = loggerExcelLine + ";" + promedioRendimientoDividendo5YAIndus;
		loggerExcelLine = loggerExcelLine + ";" + tasaCrecimientoDividendosEmprs;
		loggerExcelLine = loggerExcelLine + ";" + tasaCrecimientoDividendosIndus;
		loggerExcelLine = loggerExcelLine + ";" + ratioPayoutEmprs;
		loggerExcelLine = loggerExcelLine + ";" + ratioPayoutIndus;
		loggerExcelLine = loggerExcelLine + ";" + hrefElemento;
		LOGGER.info(loggerExcelLine);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(downloadPath + "\\" + nacionalidad + "\\RPD_" + dividendo.intValue() + "\\" + URLEncoder.encode(hrefElemento, WebDriverBase.CHARSET) + timeFrame + ".png"));
	}

	/**
	 * @param driver
	 */
	protected static void gestionError(WebDriver driver)
	{
		LOGGER.info("Intentando cerrar popup bloqueante");
		try
		{
			WebDriverBase.clickElementByClassName(driver, driver, "popupCloseIcon");
		}
		catch (Exception e)
		{
			LOGGER.error("Error intentando cerrar popup bloqueante", e);
		}
	}

}
