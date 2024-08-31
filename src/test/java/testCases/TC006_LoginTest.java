package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC006_LoginTest extends BaseClass{
	
	HomePage homepage;
	LoginPage loginpage;
	MyAccountPage myaccount;
	
	@Test(priority=1, groups = {"Sanity", "Master"})
	public void testLogin() throws IOException
	{
		logger.info("*******TC002_LoginTest Started******");
		
		homepage = new HomePage(driver);
		loginpage = new LoginPage(driver);
		
		homepage.clickMyAccount();
		homepage.clickLogin();
		
		logger.info("******Enter Registered user credentials*********");
		
		try
		{
			loginpage.setEmail(readConfig().getProperty("email"));
			loginpage.setPassword(readConfig().getProperty("password"));
			loginpage.clickLogin();
			
			//verification My Account page
			
			myaccount = new MyAccountPage(driver);
			
			boolean validate = myaccount.getDisplayStatus();
			if(validate==true)
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("*****Login Error Occured*****");
				logger.debug("******Debug Logging******");
				
				Assert.assertTrue(false);
			}
		}
		catch(Exception e)
		{
			e.getMessage();
			Assert.fail();
		}
		
		logger.info("**********TC002_LoginTest Completed******");
		
	}

}
