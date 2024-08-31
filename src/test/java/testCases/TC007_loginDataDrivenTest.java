package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilties.DataProviders;


public class TC007_loginDataDrivenTest extends BaseClass{

	HomePage homepage;
	LoginPage loginpage;
	MyAccountPage myaccount;
	
	@Test(priority=1, dataProvider = "logindata", dataProviderClass = DataProviders.class, groups = "Datadriven")
	public void testLogin(String email, String password, String status) throws IOException
	{
		logger.info("*******TC003_loginDataDrivenTest Started******");
		
		try
		{
			homepage = new HomePage(driver);
			loginpage = new LoginPage(driver);
			
			homepage.clickMyAccount();
			homepage.clickLogin();
			
			logger.info("******Enter Registered user credentials*********");
			
			loginpage.setEmail(email);
			loginpage.setPassword(password);
			loginpage.clickLogin();
			
			Thread.sleep(3000);
			
			//verification My Account page
			
			myaccount = new MyAccountPage(driver);
			boolean msgvalidate = myaccount.getDisplayStatus();
			
			//Click on logout page only if the login is successful 
			//Mark a test case passed only if the login is successful through valid credentials
			
			/*
			 * Data is valid - login success - test case Passed - logout
			 * Data is valid - login fail - test case fail 
			 * 
			 * Data is invalid - login success - test case fail - logout
			 * Data is invalid - login fail - test case pass
			 */
			
			if(status.equalsIgnoreCase("valid"))
			{
				if(msgvalidate==true)
				{
					myaccount.clickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					logger.error("*****Login Error Occured*****");
					logger.debug("******Debug Logging******");
					Assert.assertTrue(false);		
				}
					
			}
			
			else if(status.equalsIgnoreCase("invalid"))
			{
				if(msgvalidate==true)
				{
					logger.error("*****Login Error Occured*****");
					logger.debug("******Debug Logging******");
					
					myaccount.clickLogout();
					Assert.fail();
				}
				
				else
				{
					Assert.assertTrue(true);
				}
			}
	
		}
		catch(Exception e)
		{
			e.getMessage();
		//	Assert.fail();
		}
		
		logger.info("**********TC002_LoginTest Completed******");
	
	}
}
