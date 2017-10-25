/**
 * 
 */
package jsm.mdata.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Empleado
 *
 */
public class Investing
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "C:\\_PELAYO\\Software\\Selenium\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://es.investing.com/etfs/germany-etfs?&issuer_filter=db%20X-trackers");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("etfs")));
		WebElement tablaElementos = driver.findElement(By.id("etfs"));
		List<WebElement> listaLinks = tablaElementos.findElements(By.tagName("a"));
		for (WebElement elementoLink : listaLinks)
		{
			try
			{
				new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.className("popupCloseIcon")));
				WebElement closePopUp = driver.findElement(By.className("popupCloseIcon"));
				closePopUp.click();
			}
			catch (Exception e)
			{
				System.out.println("No hay popup promocional");
			}
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(elementoLink));
			elementoLink.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Información histórica")));
			WebElement historicoLink = driver.findElement(By.linkText("Información histórica"));
			historicoLink.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("picker")));
			((JavascriptExecutor) driver).executeScript("document.getElementById('picker').value='01/01/2010 - 01/01/2018'");
			WebElement fechasLink = driver.findElement(By.id("flatDatePickerCanvasHol"));
			fechasLink.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("applyBtn")));
			WebElement aceptarBtn = driver.findElement(By.id("applyBtn"));
			aceptarBtn.click();
		}

	}

}
