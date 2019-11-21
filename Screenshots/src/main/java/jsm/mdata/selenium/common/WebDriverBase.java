/**
 * 
 */
package jsm.mdata.selenium.common;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Empleado
 *
 */
public abstract class WebDriverBase
{

	/**
	 * Web Driver
	 */
	public static final String WEB_DRIVER_PROPERTY = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_EXE = "C:\\_PELAYO\\Software\\Selenium\\drivers\\chromedriver.exe";

	/**
	 * Formatos
	 */
	public static final String CHARSET = "UTF-8";

	/**
	 * @param driver
	 * @param context
	 * @param id
	 */
	public static void clickElementById(WebDriver driver, SearchContext context, String id)
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id(id)));
		WebElement element = context.findElement(By.id(id));
		element.click();
	}

	/**
	 * @param driver
	 * @param context
	 * @param linkText
	 */
	public static void clickElementByLinkText(WebDriver driver, SearchContext context, String linkText)
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
		WebElement element = context.findElement(By.linkText(linkText));
		element.click();
	}

	/**
	 * @param driver
	 * @param context
	 * @param partialLinkText
	 */
	public static void clickElementByPartialLinkText(WebDriver driver, SearchContext context, String partialLinkText)
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.partialLinkText(partialLinkText)));
		WebElement element = context.findElement(By.partialLinkText(partialLinkText));
		element.click();
	}

	/**
	 * @param driver
	 * @param context
	 * @param className
	 */
	public static void clickElementByClassName(WebDriver driver, SearchContext context, String className)
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
	public static void clickElementByTagName(WebDriver driver, SearchContext context, String tagName)
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.tagName(tagName)));
		WebElement element = context.findElement(By.tagName(tagName));
		element.click();
	}

	/**
	 * @param driver
	 * @param element
	 */
	public static void clickElement(WebDriver driver, WebElement element)
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

}
