package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	WebDriver driver;
	
	BasePage(WebDriver driver)  //base constructor for all the pageobject classes
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
