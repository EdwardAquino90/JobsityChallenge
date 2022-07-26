package TestSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import ViewLayer.pom.PageObjects;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestSteps {

	public static void fillContactFields(WebDriver driver, int subject, String email, String order, String attachment,
			String Message) {

		PageObjects.getContact_subject(driver).click();
		new Select(PageObjects.getContact_subject(driver)).selectByIndex(subject);
		PageObjects.getContact_email(driver).sendKeys(email);
		PageObjects.getContact_order(driver).sendKeys(order);
		PageObjects.getContact_message(driver).sendKeys(Message);

		if (attachment != null) {
			PageObjects.getContact_attach(driver).sendKeys(attachment);
		}

	}

	
	public static WebDriver crossBrowseExec(String browser, String Url) {
		
		WebDriver driver =null;
		
		switch(browser) {
		  case "ie":
		      WebDriverManager.edgedriver().setup();
			  driver     = new EdgeDriver();
			  driver.get(Url);
		    
		    break;
		    
		  case "Firefox":
			  WebDriverManager.firefoxdriver().setup();
			  driver     = new FirefoxDriver();
			  driver.get(Url);
		    break;
		    
		  case "Chrome":
			  WebDriverManager.chromiumdriver().setup(); 
			  driver     = new ChromeDriver();
			  driver.get(Url);
			  
			  break;
			  
		    default:
		    	 WebDriverManager.chromiumdriver().setup(); 
				  driver     = new ChromeDriver();
				  driver.get(Url);
		}
		
		return driver;
	}

}

