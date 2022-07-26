package TestSuite;



import org.testng.Assert;
import org.testng.annotations.*;

import DataLayer.DataProvider;
import Utils.Util;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import ViewLayer.pom.*;

public class TestSuite {
	
	// Defining variables
	 WebDriver driver      =null, 
	 showReport            =null;
	 ExtentReports extend  =null;
	 ExtentTest report     =null;
	 WebDriverWait wait    =null;

	 String Projectpath   = System.getProperty("user.dir");
	 String sheet         = "Sheet1";
	 String file          = Projectpath+"\\DataPool\\DataPoolExcel.xlsx";
	 String DocumentTitle = "Test Run Report";
	 int iteration        = 0;
	
	 
	/* Setting up navigator
	 * @param: no parameters
	 */
	@BeforeTest
	public void setUp(){
		WebDriverManager.chromiumdriver().setup();		
		
		showReport = new ChromeDriver();
		driver     = new ChromeDriver();
		wait    = new WebDriverWait(driver,  Duration.ofSeconds(20));
		
		extend = Util.SetupReport(DocumentTitle);
	}
	
	
	/*Test Case #1 - Searching for products
	 * 	Methods: tc_01_search_Products: return WebPage objects
	 * @param: webPage, toSearch: WebPage(AUT) and search keyword.
	 * */
	@Test(dataProvider = "datapool")
	public void tc_01_search_Products(String webPage,String toSearch) throws IOException {
		
		 iteration++;
		 report = extend.createTest("Test Case 1 - Iteration: "+iteration);
		
		/*go to the WebPage
		 * @param URL
		 * */
		driver.get(webPage);
		driver.manage().window().maximize();
		
		wait.until(ExpectedConditions.elementToBeClickable(PageObjects.getLogo(driver)));
		
		/*searching products
		 * POM Class
		 * 	Methods: getSearch_txtbox, getSearch_button: return WebPage objects
		 * @param driver: WebDriver instance
		 * */
		PageObjects.getSearch_txtbox(driver).sendKeys(toSearch);
		PageObjects.getSearch_button(driver).click();
		
		/*products found
		 * Method(getProductos_fromsearch): returns a list of elements.
		 * @param driver: WebDriver instance
		 * */
		List<WebElement> Products =
				PageObjects.getProductos_fromsearch(driver);
	
		//Verification point: verifying if the query matches with the keyword provided.
     	for (WebElement products : Products) {
			
     		try {
     			
     			assertTrue(products.getText().toUpperCase().contains(toSearch.toUpperCase()));
     			
     			//Documenting result
     			report.log(Status.PASS, "PRODUCT: '"+products.getText()+"' contains the keyword ['"+toSearch+"']")
     				  .addScreenCaptureFromPath(Util.takeScreenshot(driver));
     			
     			
			} catch (AssertionError e) {
				//Documenting result
				report.fail("PRODUCT: '"+products.getText()+"' doesn't contain the keyword ['"+toSearch+"']", MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
			}
				    
				
		}      
     	
     
	}
	
	@Test
	public void tc_02_validate_contactform() throws IOException {

		iteration++;
		report = extend.createTest("Test Case 2 - Iteration: " + iteration);
		String expectedResults = "Please select a subject from the list provided.";
		String actualResults = null;

		/*
		 * go to the WebPage
		 * 
		 * @param URL
		 */
		// driver.get("http://automationpractice.com/");
		driver.get("http://automationpractice.com/index.php?controller=contact");
		driver.manage().window().maximize();

		TestSteps.fillContactFields(driver, 0, "edwardaquino90@gmail.com", "123456",
				System.getProperty("user.dir") + "\\src\\img\\jobsity.png", "This is the message to send");

		PageObjects.getContact_send_button(driver).click();
		actualResults = PageObjects.getContact_err_message(driver).getText();

		try {
			Assert.assertEquals(expectedResults, actualResults);

			// Documenting result
			report.log(Status.PASS, "Subject field is being validated.")
					.addScreenCaptureFromPath(Util.takeScreenshot(driver));
		} catch (AssertionError e) {
			// Documenting result
			report.fail("Subject field is not being validated.",
					MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
		}

	}
	
	@AfterTest
	 public void end_test_suite() {
		 
		/*Creating report*/
		extend.flush();
		
		/*Closing WebPage(AUT)*/
//		driver.close();
		
		/*Showing report*/
		showReport.get(Projectpath+"\\src\\Extent.html");
		showReport.manage().window().maximize();
 }
	
	/*Data Provider Method for TestNG cases.
	 * Method(dataPool): returns a list of elements.
	 * @param driver: no parameters
	 * */
	@org.testng.annotations.DataProvider(name="datapool")
	public Object[][] dataPool(){		
		
		DataProvider excel = new DataProvider(sheet, file);
		Object data[][] = excel.getDataPool();
		
		return data;
		
	}
	

}
