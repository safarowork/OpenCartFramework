package testCases;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

@Listeners
public class TC001_AccountRegistrationTest extends BaseClass{

	HomePage homepage;
	AccountRegistrationPage accountregpage;
	Logger log;
	
	@Test(priority=1, groups = {"Regression", "Master"})
	public void testAccountRegistration()
	{
		
		logger.info("*******Starting Test Case TC001_AccountRegistrationTest**********");
		
		try {
		homepage = new HomePage(driver);
		homepage.clickMyAccount();
		homepage.clickRegister();
		
		logger.info("********Providing customer details*********");
		
		accountregpage = new AccountRegistrationPage(driver);
		accountregpage.setFirstName(fname());
		accountregpage.setLastName(lname());
		accountregpage.setEmail(email());
		accountregpage.setTelephone(phone());
		
		String password = password();
		accountregpage.setPassword(password);
		accountregpage.setConirmPassword(password);
		accountregpage.setPrivacyPolicy();
		accountregpage.clickContinue();
		
		String message = accountregpage.getConfirmationMessage();
		if(message.equals("Your Account Has Been Created!")){
			Assert.assertTrue(true);;
		}
		
		else {
			logger.error("****Test Failed*****");
			logger.debug("****Debug Logs******");
			Assert.assertTrue(false);
		}	
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	

}
