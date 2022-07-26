package ViewLayer.pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	static private WebElement contact_sus_message = null;
	
	//cart elements
	static private WebElement car_header_counter  = null;
	static private WebElement add_to_cart_bttn    = null;
	static private WebElement added_to_cart_mssg  = null;
	static private WebElement continue_shop_bttn  = null;
	static private WebElement categories_bttn     = null;
	static private WebElement product             = null;
	static private WebElement cont_shopping_bttn  = null;
	static private WebElement remove_button       = null;
	static private WebElement home_button         = null;
	static private WebElement cart_cnt_prod       = null;
	
	
	public static WebElement getCar_Header_Counter(WebDriver driver) {

		car_header_counter = driver.findElement(By.xpath("//span[@class='ajax_cart_quantity unvisible']"));

		return car_header_counter;
	}
	
	public static WebElement getCar_Header_Counter_afterR(WebDriver driver) {

		car_header_counter = driver.findElement(By.xpath("//span[@class='ajax_cart_quantity']"));

		return car_header_counter;
	}
	
	public static WebElement getCar_cnt_CartProd(WebDriver driver) {

		cart_cnt_prod = driver.findElement(By.xpath("//span[@class='ajax_cart_no_product']"));

		return cart_cnt_prod;
	}
	
	public static WebElement getCar_Home_Button(WebDriver driver) {

		home_button = driver.findElement(By.xpath("//div[@id='header_logo']/a"));

		return home_button;
	}
 
	public static WebElement Add_Pro_to_Cart(WebDriver driver, String name) {

		add_to_cart_bttn = driver.findElement(By.xpath("//a[@class='product-name' and contains(text(),'"+name+"')]/ancestor::div[@class='product-container']/div[@class='right-block']/div[@class='button-container']/a[1]"));

		return add_to_cart_bttn;
	}

	public static WebElement get_Add_to_Cart2(WebDriver driver) {

		add_to_cart_bttn = driver.findElement(By.xpath("//div[@class='button-container']/a[@data-id-product='2']/span"));

		return add_to_cart_bttn;
	}
	
	public static WebElement get_Product(WebDriver driver, String name) {

		product = driver.findElement(By.cssSelector(".product-container .product-name[title='"+name+"']"));

		return product;
	}
	
	
	public static WebElement get_Categories_Bttn(WebDriver driver) {

		categories_bttn = driver.findElement(By.xpath("//div[@class='cat-title']/parent::div/ul/li[1]/a"));

		return categories_bttn;
	}
	
	public static WebElement get_Added_Message(WebDriver driver) {

		added_to_cart_mssg = driver.findElement(By.xpath("//div[@class='layer_cart_product col-xs-12 col-md-6']/h2"));

		return added_to_cart_mssg;
	}
	
	public static WebElement Contitnue_shopping_action(WebDriver driver) {

		cont_shopping_bttn = driver.findElement(By.cssSelector(".button[title='Continue shopping']"));

		return cont_shopping_bttn;
	}
	
	public static WebElement get_cont_Shopping_Bttn(WebDriver driver) {

		continue_shop_bttn = driver.findElement(By.xpath("//div[@class='button-container']/span/span"));

		return continue_shop_bttn;
	}
	
	public static WebElement Remove_Product_From_Cart(WebDriver driver) {

		remove_button = driver.findElement(By.xpath("//a[@class='ajax_cart_block_remove_link']"));

		return remove_button;
	}
	
	public static WebElement getContact_err_message(WebDriver driver) {

		contact_err_message = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));

		return contact_err_message;
	}

	public static WebElement getContact_sus_message(WebDriver driver) {

		contact_sus_message = driver.findElement(By.xpath("//div[@id='center_column']/p"));

		return contact_sus_message;
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


	public static void scrollDownUntilElementFound(WebElement locator, WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", (locator));

	}
	
	public static void HighlightElement(WebElement locator, WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor = 'yellow';",  (locator));

	}
	public static void HoverElement(WebElement locator, WebDriver driver) {

		//Creating object of an Actions class
		Actions action = new Actions(driver);
		
		//Performing the mouse hover action on the target element.
		action.moveToElement(locator).perform();

	}
	
	public static void WaitForPageToLoad( WebDriver driver) {

		new WebDriverWait(driver,  Duration.ofSeconds(20)).until(
			      webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		

	}
	
	public static void refreshPage(WebDriver driver) {
		
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
	}
	
	public static void resizeBrowser(WebDriver driver, int Width, int Height) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.resizeTo("+Width+","+Height+");");
	}
	
	public static void zoomBrowser(WebDriver driver, int zoom) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("document.body.style.zoom='"+zoom+"%'");
	}
}
