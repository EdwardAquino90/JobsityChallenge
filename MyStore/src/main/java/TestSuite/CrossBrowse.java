package TestSuite;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import DataLayer.DataProvider;
import Utils.Util;
import ViewLayer.pom.PageObjects;



public class CrossBrowse {
	
	// Defining variables
	WebDriver driver      =null; 
	ExtentReports extend  =null;
	ExtentTest report     =null;
	 String Projectpath   = System.getProperty("user.dir");
	 String sheet         = "Cross";
	 String file          = Projectpath+"\\DataPool\\DataPoolExcel.xlsx";
	 int iter             =0;
	
	String DocumentTitle = "Cross Browse Test Report";
	
	@BeforeTest
	public void setUp(){

		extend = Util.SetupReport(DocumentTitle);
	}
	

	@Test(dataProvider = "datapool")
	public void Cross_browse_test(String Url, String Browser) throws IOException {
		
		iter++;
		report = extend.createTest("Test Case 12 - Iteration: "+iter+" - "+Browser);
		
		String expectedResult = "CUSTOMER SERVICE - CONTACT US";
		String currentResult = null;

		driver = TestSteps.crossBrowseExec(Browser, Url);

		driver.manage().window().maximize();
		
		currentResult = PageObjects.getContact_header(driver).getText();

		try {
			Assert.assertEquals(expectedResult, currentResult);

			// Documenting result
			report.log(Status.PASS, "").addScreenCaptureFromPath(Util.takeScreenshot(driver));

			report.log(Status.INFO, "The page works in the "+Browser+" browser as expected.");
			report.log(Status.INFO, "Verification point - Expected Results:  " + expectedResult);
			report.log(Status.INFO, "Verification point - Current Resultls:  " + currentResult);

		} catch (AssertionError e) {
			// Documenting result
			report.fail("Test Case Failed.",
					MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
			report.log(Status.INFO, "Verification point - Expected Results:  " + expectedResult);
			report.log(Status.INFO, "Verification point - Current Resultls:  " + currentResult);
		}
	}
	
	@AfterTest
	 public void end_test_suite() {
		 
		/*Creating report*/
		extend.flush();
		
		/*Closing WebPage(AUT)*/
		driver.quit();
		
}
	
	
	
	
	@org.testng.annotations.DataProvider(name="datapool")
	public Object[][] dataPool(){		
		
		DataProvider excel = new DataProvider(sheet, file);
		Object data[][] = excel.getDataPool();
		
		return data;
		
	}

}
