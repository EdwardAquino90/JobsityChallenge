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

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import ViewLayer.pom.*;

public class TestSuite {
	
	// Defining variables
	 WebDriver driver      =null; 
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
		
		driver     = new ChromeDriver();
		wait    = new WebDriverWait(driver,  Duration.ofSeconds(30));
		
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
		String currentResults = null;

		/*
		 * go to the WebPage
		 * 
		 * @param URL
		 */
		// driver.get("http://automationpractice.com/");
		driver.get("http://automationpractice.com/index.php?controller=contact");
		driver.manage().window().maximize();
         
		//Subject field validation
		TestSteps.fillContactFields(driver, 0, "edwardaquino90@gmail.com", "123456",
				System.getProperty("user.dir") + "\\src\\img\\jobsity.png", "This is the message to send");

		PageObjects.getContact_send_button(driver).click();
		currentResults = PageObjects.getContact_err_message(driver).getText();

		try {
			Assert.assertEquals(expectedResults, currentResults);
			
			PageObjects.scrollDownUntilElementFound(PageObjects.getContact_header(driver), driver);

			// Documenting result
			report.log(Status.PASS, "Subject field is being validated.")
					.addScreenCaptureFromPath(Util.takeScreenshot(driver));
			report.log(Status.INFO, "Expected Results: "+expectedResults);
			report.log(Status.INFO, "Current Resultls: "+currentResults);
			
		} catch (AssertionError e) {
			// Documenting result
			report.fail("Subject field is not being validated."
						+"Expected Results: " + expectedResults+
						 "Current Results: " + currentResults,
					MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
		}

	}
	
	
	
	
	@Test
	public void tc_03_validate_contactform() throws IOException {

		iteration++;
		report = extend.createTest("Test Case 3 - Iteration: " + iteration);
		String expectedResults = "Invalid email address.";
		String currentResults = null;

		/*
		 * go to the WebPage
		 * 
		 * @param URL
		 */
		// driver.get("http://automationpractice.com/");
		driver.get("http://automationpractice.com/index.php?controller=contact");
		driver.manage().window().maximize();
        
		//Email format validation
		TestSteps.fillContactFields(driver, 0, "edwardaquino90", "123456",
				System.getProperty("user.dir") + "\\src\\img\\jobsity.png", "This is the message to send");

		PageObjects.getContact_send_button(driver).click();
		currentResults = PageObjects.getContact_err_message(driver).getText();

		try {
			Assert.assertEquals(expectedResults, currentResults);

			PageObjects.scrollDownUntilElementFound(PageObjects.getContact_header(driver), driver);
			
			// Documenting result
			report.log(Status.PASS, "Email field is being validated.")
					.addScreenCaptureFromPath(Util.takeScreenshot(driver));
			report.log(Status.INFO, "Expected Results: "+expectedResults);
			report.log(Status.INFO, "Current Resultls: "+currentResults);
		} catch (AssertionError e) {
			// Documenting result
			report.fail("Email field is not being validated.",
					MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
		}

	}
	
	
	@Test
	public void tc_04_validate_contactform() throws IOException {

		iteration++;
		report = extend.createTest("Test Case 4 - Iteration: " + iteration);
		String expectedResults = "Invalid email address.";
		String currentResults = null;

		/*
		 * go to the WebPage
		 * 
		 * @param URL
		 */
		// driver.get("http://automationpractice.com/");
		driver.get("http://automationpractice.com/index.php?controller=contact");
		driver.manage().window().maximize();
        
		//Email - empty field validation
		TestSteps.fillContactFields(driver, 0, "", "123456",
				System.getProperty("user.dir") + "\\src\\img\\jobsity.png", "This is the message to send");

		PageObjects.getContact_send_button(driver).click();
		currentResults = PageObjects.getContact_err_message(driver).getText();

		try {
			Assert.assertEquals(expectedResults, currentResults);

			PageObjects.scrollDownUntilElementFound(PageObjects.getContact_header(driver), driver);
			
			// Documenting result
			report.log(Status.PASS, "Email field is being validated.")
					.addScreenCaptureFromPath(Util.takeScreenshot(driver));
			
			report.log(Status.INFO, "Expected Results: "+expectedResults);
			report.log(Status.INFO, "Current Resultls: "+currentResults);
		} catch (AssertionError e) {
			// Documenting result
			report.fail("Email field is not being validated.",
					MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
		}

	}
	
	
	@Test
	public void tc_05_validate_contactform() throws IOException {

		iteration++;
		report = extend.createTest("Test Case 5 - Iteration: " + iteration);
		String expectedResults = "The message cannot be blank.";
		String currentResults = null;

		/*
		 * go to the WebPage
		 * 
		 * @param URL
		 */
		// driver.get("http://automationpractice.com/");
		driver.get("http://automationpractice.com/index.php?controller=contact");
		driver.manage().window().maximize();
        
		//Message field validation
		TestSteps.fillContactFields(driver, 1, "edwardaquino90@gmail.com", "123445",
				System.getProperty("user.dir") + "\\src\\img\\jobsity.png", "");

		PageObjects.getContact_send_button(driver).click();
		currentResults = PageObjects.getContact_err_message(driver).getText();

		try {
			Assert.assertEquals(expectedResults, currentResults);
			
			PageObjects.scrollDownUntilElementFound(PageObjects.getContact_header(driver), driver);

			// Documenting result
			report.log(Status.PASS, "Message field is being validated.")
					.addScreenCaptureFromPath(Util.takeScreenshot(driver));
			
			report.log(Status.INFO, "Expected Results: "+expectedResults);
			report.log(Status.INFO, "Current Resultls: "+currentResults);
		} catch (AssertionError e) {
			// Documenting result
			report.fail("Message field is not being validated.",
					MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
		}

	}
	
	
	
	@Test
	public void tc_06_validate_contactform() throws IOException {

		iteration++;
		report = extend.createTest("Test Case 6 - Iteration: " + iteration);
		String expectedResults = "The Order cannot be blank.";
		String currentResults = null;

		/*
		 * go to the WebPage
		 * 
		 * @param URL
		 */
		// driver.get("http://automationpractice.com/");
		driver.get("http://automationpractice.com/index.php?controller=contact");
		driver.manage().window().maximize();

		// Order field validation
		TestSteps.fillContactFields(driver, 1, "edwardaquino90@gmail.com", "",
				System.getProperty("user.dir") + "\\src\\img\\jobsity.png", "This is the message to send");

		PageObjects.getContact_send_button(driver).click();
		Duration.ofSeconds(3);

		try {
			if (PageObjects.getContact_err_message(driver).isDisplayed()) {
				currentResults = PageObjects.getContact_err_message(driver).getText();
			}
		} catch (NoSuchElementException e) {
			currentResults = PageObjects.getContact_sus_message(driver).getText();
		}
	
		
		
		try {
			Assert.assertEquals(expectedResults, currentResults);

			// Documenting result
			report.log(Status.PASS, "Order field is being validated.")
					.addScreenCaptureFromPath(Util.takeScreenshot(driver));

			report.log(Status.INFO, "Expected Results: " + expectedResults);
			report.log(Status.INFO, "Current Resultls: " + currentResults);
		} catch (AssertionError e) {
			// Documenting result
			report.fail("Order field is not being validated.",
					MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
			report.log(Status.INFO, "Expected Results: " + expectedResults);
			report.log(Status.INFO, "Current Resultls: " + currentResults);
		}

	}
	
	
	
	@Test
	public void tc_07_validate_contactform() throws IOException {

		iteration++;
		report = extend.createTest("Test Case 7 - Iteration: " + iteration);
		String expectedResults = "There is not file attached.";
		String currentResults = null;

		/*
		 * go to the WebPage
		 * 
		 * @param URL
		 */
		// driver.get("http://automationpractice.com/");
		driver.get("http://automationpractice.com/index.php?controller=contact");
		driver.manage().window().maximize();

		// Attachment field validation
		TestSteps.fillContactFields(driver, 1, "edwardaquino90@gmail.com", "Order-15465",
				null, "This is the message to send");

		PageObjects.getContact_send_button(driver).click();
		Duration.ofSeconds(3);

		try {
			if (PageObjects.getContact_err_message(driver).isDisplayed()) {
				currentResults = PageObjects.getContact_err_message(driver).getText();
			}
		} catch (NoSuchElementException e) {
			currentResults = PageObjects.getContact_sus_message(driver).getText();
		}
	
		
		
		try {
			Assert.assertEquals(expectedResults, currentResults);

			// Documenting result
			report.log(Status.PASS, "Attach File field is being validated.")
					.addScreenCaptureFromPath(Util.takeScreenshot(driver));

			report.log(Status.INFO, "Expected Results: " + expectedResults);
			report.log(Status.INFO, "Current Resultls: " + currentResults);
		} catch (AssertionError e) {
			// Documenting result
			report.fail("Attach File field is not being validated.",
					MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
			report.log(Status.INFO, "Expected Results: " + expectedResults);
			report.log(Status.INFO, "Current Resultls: " + currentResults);
		}

	}
	
	@Test
	public void tc_08_validate_contactform() throws IOException {

		iteration++;
		report = extend.createTest("Test Case 8 - Iteration: " + iteration);
		String expectedResults = "Your message has been successfully sent to our team.";
		String currentResults = null;

		/*
		 * go to the WebPage
		 * 
		 * @param URL
		 */
		// driver.get("http://automationpractice.com/");
		driver.get("http://automationpractice.com/index.php?controller=contact");
		driver.manage().window().maximize();

		// sending message test
		TestSteps.fillContactFields(driver, 1, "edwardaquino90@gmail.com", "Order-15465",
				System.getProperty("user.dir") + "\\src\\img\\jobsity.png", "This is the message to send");

		PageObjects.getContact_send_button(driver).click();

			currentResults = PageObjects.getContact_sus_message(driver).getText();
		
		
		try {
			Assert.assertEquals(expectedResults, currentResults);

			// Documenting result
			report.log(Status.PASS, "Message successfully sent.")
					.addScreenCaptureFromPath(Util.takeScreenshot(driver));

			report.log(Status.INFO, "Expected Results: " + expectedResults);
			report.log(Status.INFO, "Current Resultls: " + currentResults);
		} catch (AssertionError e) {
			// Documenting result
			report.fail("Unsuccessful message delivery.",
					MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
			report.log(Status.INFO, "Expected Results: " + expectedResults);
			report.log(Status.INFO, "Current Resultls: " + currentResults);
		}

	}
	
	
	
	@Test
	public void tc_09_cart_validation() throws IOException {

		iteration++;
		report = extend.createTest("Test Case 9 - Iteration: " + iteration);
		int currentcntProducts  = 0;
		int expectedcntProducts = 0;

		/*
		 * go to the WebPage
		 * 
		 * @param URL
		 */
		driver.get("http://automationpractice.com/");
		// driver.get("http://automationpractice.com/index.php?controller=search&orderby=position&orderway=desc&search_query=&submit_search=");
		driver.manage().window().maximize();

		// sending message test

		PageObjects.get_Categories_Bttn(driver).click();

		PageObjects.scrollDownUntilElementFound(PageObjects.get_Product(driver,"Printed Chiffon Dress"), driver);
		PageObjects.HighlightElement(PageObjects.get_Product(driver,"Printed Chiffon Dress"), driver);
		PageObjects.HoverElement(PageObjects.get_Product(driver,"Printed Chiffon Dress"), driver);

		PageObjects.Add_Pro_to_Cart(driver,"Printed Chiffon Dress").click();

		PageObjects.WaitForPageToLoad(driver);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button[title='Continue shopping']")));

		// Documenting result
		report.log(Status.INFO, "Product Added").addScreenCaptureFromPath(Util.takeScreenshot(driver));

		expectedcntProducts = Integer
				.parseInt(driver.findElement(By.xpath("//span[@id='layer_cart_product_quantity']")).getText());

		PageObjects.Contitnue_shopping_action(driver).click();

		PageObjects.scrollDownUntilElementFound(PageObjects.getCar_Header_Counter(driver), driver);
		PageObjects.HoverElement(PageObjects.getCar_Header_Counter(driver), driver);

		currentcntProducts = Integer.parseInt(PageObjects.getCar_Header_Counter(driver).getText());
		System.out.println(currentcntProducts);

		try {
			Assert.assertEquals(expectedcntProducts, currentcntProducts);
			// Documenting result
			report.log(Status.PASS, "Verification point status: PASS")
					.addScreenCaptureFromPath(Util.takeScreenshot(driver));

			report.log(Status.INFO, "Expected Results: " + expectedcntProducts + " Product(s)");
			report.log(Status.INFO, "Current Results: " + currentcntProducts + " Product(s)");
		} catch (AssertionError e) {
			// Documenting result
			report.fail("Verification point status: FAILED",
					MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
			report.log(Status.INFO, "Expected Results: " + expectedcntProducts + " Product(s)");
			report.log(Status.INFO, "Current Results: " + currentcntProducts + " Product(s)");
		}

	}
	
	
	@Test
	public void tc_10_cart_validation() throws IOException {

		iteration++;
		report = extend.createTest("Test Case 10 - Iteration: " + iteration);
		int currentcntProducts  = 0;
		int expectedcntProducts = 0;

		/*
		 * go to the WebPage
		 * 
		 * @param URL
		 */
		driver.get("http://automationpractice.com/");
		
		driver.manage().window().maximize();

		// sending message test
		PageObjects.getSearch_txtbox(driver).sendKeys("Blouse");
		PageObjects.getSearch_button(driver).click();

		PageObjects.scrollDownUntilElementFound(PageObjects.get_Product(driver, "Blouse"), driver);
		PageObjects.HighlightElement(PageObjects.get_Product(driver, "Blouse"), driver);
		PageObjects.HoverElement(PageObjects.get_Product(driver, "Blouse"), driver);

		PageObjects.Add_Pro_to_Cart(driver, "Blouse").click();

		PageObjects.WaitForPageToLoad(driver);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button[title='Continue shopping']")));

		// Documenting result
		report.log(Status.INFO, "Product Added").addScreenCaptureFromPath(Util.takeScreenshot(driver));

		expectedcntProducts = Integer
				.parseInt(driver.findElement(By.xpath("//span[@id='layer_cart_product_quantity']")).getText());

		PageObjects.Contitnue_shopping_action(driver).click();

		PageObjects.scrollDownUntilElementFound(PageObjects.getCar_Header_Counter(driver), driver);
		PageObjects.HoverElement(PageObjects.getCar_Header_Counter(driver), driver);

		currentcntProducts = Integer.parseInt(PageObjects.getCar_Header_Counter(driver).getText());

		try {
			Assert.assertEquals(expectedcntProducts, currentcntProducts);
			// Documenting result
			report.log(Status.PASS, "Verification point status: PASS")
					.addScreenCaptureFromPath(Util.takeScreenshot(driver));

			report.log(Status.INFO, "Expected Results: " + expectedcntProducts + " Product(s)");
			report.log(Status.INFO, "Current Results: " + currentcntProducts + " Product(s)");
		} catch (AssertionError e) {
			// Documenting result
			report.fail("Verification point status: FAILED",
					MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
			report.log(Status.INFO, "Expected Results: " + expectedcntProducts + " Product(s)");
			report.log(Status.INFO, "Current Results: " + currentcntProducts + " Product(s)");
		}

	}
	
	
	@Test
	public void tc_11_cart_validation() throws IOException {

		iteration++;
		report = extend.createTest("Test Case 11 - Iteration: " + iteration);
		
		PageObjects.HoverElement(PageObjects.getCar_Header_Counter(driver), driver);
		report.log(Status.INFO, "Product to remove").addScreenCaptureFromPath(Util.takeScreenshot(driver));

		PageObjects.Remove_Product_From_Cart(driver).click();

		PageObjects.scrollDownUntilElementFound(PageObjects.getCar_Home_Button(driver), driver);
		PageObjects.getCar_Home_Button(driver).click();
		PageObjects.WaitForPageToLoad(driver);

		try {
			Assert.assertEquals(PageObjects.getCar_cnt_CartProd(driver).getText(),
					"(empty)");
			
			PageObjects.HighlightElement(PageObjects.getCar_cnt_CartProd(driver), driver);
			report.log(Status.PASS, "Product Removed").addScreenCaptureFromPath(Util.takeScreenshot(driver));
		} catch (Exception e) {
			report.log(Status.FAIL, "Product not Removed").addScreenCaptureFromPath(Util.takeScreenshot(driver));
		}
       
	}
	
	
	@AfterTest
	 public void end_test_suite() {
		 
		/*Creating report*/
		extend.flush();
		
		/*Closing WebPage(AUT)*/
		driver.quit();
		
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
