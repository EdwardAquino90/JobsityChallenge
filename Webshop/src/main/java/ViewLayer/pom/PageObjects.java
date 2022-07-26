package ViewLayer.pom;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjects {

	// search elements objects
	static private WebElement search_txtbox       = null;
	static private WebElement search_button       = null;
	static private List<WebElement> productos     = null;
	static private WebElement logo                = null;
	
	// contact form objects
	static private WebElement contact_button      = null;
	static private WebElement contact_subject     = null;
	static private WebElement contact_email       = null;
	static private WebElement contact_order       = null;
	static private WebElement contact_attach      = null;
	static private WebElement contact_message     = null;
	static private WebElement contact_send_button = null;
	static private WebElement contact_err_message = null;
	static private WebElement contact_header      = null;
	
	public static WebElement getContact_err_message(WebDriver driver) {

		contact_err_message = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));

		return contact_err_message;
	}
	
	
	public static WebElement getContact_button(WebDriver driver) {

		contact_button = driver.findElement(By.xpath("//div[@id='contact-link']"));

		return contact_button;
	}
	
	public static WebElement getContact_header(WebDriver driver) {

		contact_header = driver.findElement(By.xpath("//div[@id='center_column']/h1"));

		return contact_header;
	}

	
	public static WebElement getContact_subject(WebDriver driver) {

		contact_subject = driver.findElement(By.xpath("//select[@id='id_contact']"));

		return contact_subject;
	}
	
	public static WebElement getContact_email(WebDriver driver) {

		contact_email = driver.findElement(By.xpath("//input[@class='form-control grey validate']"));

		return contact_email;
	}
	
	public static WebElement getContact_attach(WebDriver driver) {

		contact_attach = driver.findElement(By.xpath("//input[@name='fileUpload']"));

		return contact_attach;
	}
	
	public static WebElement getContact_message(WebDriver driver) {

		contact_message = driver.findElement(By.xpath("//textarea[@class='form-control']"));

		return contact_message;
	}
	
	public static WebElement getContact_send_button(WebDriver driver) {

		contact_send_button = driver.findElement(By.xpath("//button[@id='submitMessage']"));

		return contact_send_button;
	}
	
	public static WebElement getContact_order(WebDriver driver) {

		contact_order = driver.findElement(By.xpath("//input[@class='form-control grey']"));

		return contact_order;
	}

	public static WebElement getSearch_txtbox(WebDriver driver) {

		search_txtbox = driver.findElement(By.xpath("//input[@class='search_query form-control ac_input']"));

		return search_txtbox;
	}

	public static void setSearch_txtbox(WebElement search_txtbox) {
		PageObjects.search_txtbox = search_txtbox;
	}

	public static WebElement getSearch_button(WebDriver driver) {

		search_button = driver.findElement(By.xpath("//button[@name='submit_search']"));

		return search_button;
	}

	public static void setSearch_button(WebElement search_button) {
		PageObjects.search_button = search_button;
	}

	public static List<WebElement> getProductos_fromsearch(WebDriver driver) {

		productos = driver.findElements(By.xpath("//ul[@class='product_list grid row']/li/div/div[2]/h5/a"));

		return productos;
	}

	public static WebElement getLogo(WebDriver driver) {

		logo = driver.findElement(By.xpath("//img[@class='logo img-responsive']"));

		return logo;
	}

	public static void setProductos(List<WebElement> productos) {
		PageObjects.productos = productos;
	}

	public static WebElement find(By locator, WebDriver driver) {

		return driver.findElement(locator);
	}

	public static void scrollDownUntilElementFound(By locator, WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='50%'");
		js.executeScript("arguments[0].scrollIntoView();", find(locator, driver));

	}

}
