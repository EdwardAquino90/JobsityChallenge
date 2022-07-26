package Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Util {
	

	/*Set up Extent Report
	 * 	Method: SetupReport: set up
	 * @param DocumentTitle: Test Run Report Title
	 * */
	public static ExtentReports SetupReport (String DocumentTitle) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("src/Extent.html");
		ExtentReports extent = new ExtentReports();
		
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(DocumentTitle);
		extent.attachReporter(htmlReporter);
		
		return extent;
	}
	
	/*Screenshot method
	 * 	Method: takeScreenshot: set up
	 * @param driver: WebDriver instance.
	 * @return:       screenshot absolute path
	 * */
	public static String takeScreenshot(WebDriver driver) throws IOException {
		File File = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Path = new File(System.getProperty("user.dir")+"\\src\\img\\"+ System.currentTimeMillis()
		+ ".png");
		String screenshot = Path.getAbsolutePath();
		FileUtils.copyFile(File, Path);
		return screenshot;
		}
	
	/*Take page complete screenshot
	 * 	Method: takeScreenshot: set up
	 * @param driver: WebDriver instance.
	 * @return:       screenshot path
	 * */
	public static String takeFullScreenshot(WebDriver driver) throws IOException {
	    
		 String screenshot =System.getProperty("user.dir")+"\\src\\img\\"+ System.currentTimeMillis()
			+ ".png";
		 
		 Screenshot s=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		 ImageIO.write(s.getImage(),"PNG",new File(screenshot));
		
		return screenshot;
	
		}
	
		public static Boolean compareImage(BufferedImage img1, BufferedImage img2) {

			Boolean result = false;
			if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) 
			{
				for (int x = 0; x < img1.getWidth(); x++) 
				{
					for (int y = 0; y < img1.getHeight(); y++) 
					{
						if (img1.getRGB(x, y) != img2.getRGB(x, y)) 
						{
							
							result = false;
							return result;
						}
						else {
							
							result = true;
							return result;
						}
							
					}
				}
			} 
			
			return result;
		}
	
	
}
