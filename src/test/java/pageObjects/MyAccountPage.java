package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']") 
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][.='Logout']") 
	WebElement lnkLogout;
	
	
	public boolean getDisplayStatus()
	{
		boolean display_status = msgHeading.isDisplayed();
		return display_status;
	}
	
	public void clickLogout()
	{
		lnkLogout.click();
	}

}
