package TestSuite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utils.Util;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PixelPerfect {

	// Defining variables
		 WebDriver driver      =null; 
		 ExtentReports extend  =null;
		 ExtentTest report     =null;
		 WebDriverWait wait    =null;

		 String Projectpath     = System.getProperty("user.dir");
		String Url             = "http://automationpractice.com/";
		// String Url             = "http://google.com/";
		 String ChallengeImage  =System.getProperty("user.dir")+"\\src\\img\\QA Challenge.jpg";
		 String DocumentTitle   = "Test Run Report";
		 int iteration          = 0;
	
	@BeforeTest
	public void setUp(){
	WebDriverManager.firefoxdriver().setup();		
		
	driver     = new FirefoxDriver();
	wait    = new WebDriverWait(driver,  Duration.ofSeconds(30));
		
	extend = Util.SetupReport(DocumentTitle);
	}
	
	
	@Test
	public void Pixel_Verification() throws IOException {
		
		report = extend.createTest("Test Case 13 - Pixel Perfect");
		
		String imagetoCompare = null;
		driver.get(Url);

		driver.manage().window().setPosition(new Point(0, 0));

		Dimension d = new Dimension(1200, 3305);
		driver.manage().window().setSize(d);


		imagetoCompare = Util.takeFullScreenshot(driver);

		BufferedImage QAchallengeSS = ImageIO.read(new File(ChallengeImage));
		BufferedImage imageToCompare = ImageIO.read(new File(imagetoCompare));

		try {
			Assert.assertTrue(Util.compareImage(QAchallengeSS, imageToCompare));
			// Documenting result
			report.log(Status.PASS, "Verification point status: PASS - The images match")
					.addScreenCaptureFromPath(Util.takeScreenshot(driver));

			report.log(Status.INFO,
					"QA Challenge SS Height: " + QAchallengeSS.getHeight() + " Width: " + QAchallengeSS.getWidth())
					.addScreenCaptureFromPath(ChallengeImage);
			
			report.log(Status.INFO,
					"Image to Compare Height: " + imageToCompare.getHeight() + " Width: " + imageToCompare.getWidth());
		} catch (AssertionError e) {
			// Documenting result
			report.fail("Verification point status: FAILED - the images do not match",
					MediaEntityBuilder.createScreenCaptureFromPath(Util.takeFullScreenshot(driver)).build());
			report.log(Status.INFO,
					"QA Challenge SS Height: " + QAchallengeSS.getHeight() + " Width: " + QAchallengeSS.getWidth())
			         .addScreenCaptureFromPath(ChallengeImage);
			
			report.log(Status.INFO,
					"Image to Compare Height: " + imageToCompare.getHeight() + " Width: " + imageToCompare.getWidth());
		}
		
		
		}
	
	
	@AfterTest
	public void End_TestSuite() {
				
	    /*Creating report*/
		extend.flush();
		
		/*Closing WebPage(AUT)*/
		driver.quit();
	}
	
}
