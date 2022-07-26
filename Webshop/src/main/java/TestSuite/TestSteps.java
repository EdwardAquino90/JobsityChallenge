package TestSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import ViewLayer.pom.PageObjects;

public class TestSteps {

	public static void fillContactFields(WebDriver driver,int subject, String email, String order, String attachment,
			String Message) {

		PageObjects.getContact_subject(driver).click();
		new Select(PageObjects.getContact_subject(driver)).selectByIndex(subject);
		PageObjects.getContact_email(driver).sendKeys(email);
		PageObjects.getContact_order(driver).sendKeys(order);
		PageObjects.getContact_attach(driver).sendKeys(attachment);
		PageObjects.getContact_message(driver).sendKeys(Message);

	}
	


}
