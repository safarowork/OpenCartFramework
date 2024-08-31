package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC002_AccountRegistrationDDT extends BaseClass{


	HomePage homepage;
	AccountRegistrationPage accountregpage;

	@Test(priority=1, dataProvider = "registeruser", dataProviderClass = utilties.DataProviders.class, groups = "Datadriven")
	void testAccountRegistrationDDT(String fname, String lname, String email,
									String phone, String password, String conpassword, String status)
	{
			logger.info("*******Starting Test Case TC001_AccountRegistrationTest**********");
			
		try
		{
			homepage = new HomePage(driver);
			homepage.clickMyAccount();
			homepage.clickRegister();
			
			logger.info("********Providing customer details*********");
			
			accountregpage = new AccountRegistrationPage(driver);
			accountregpage.setFirstName(fname);
			accountregpage.setLastName(lname);
			accountregpage.setEmail(email);
			accountregpage.setTelephone(phone);
			accountregpage.setPassword(password);
			accountregpage.setConirmPassword(conpassword);
			accountregpage.clickContinue();
			
			if(status.equalsIgnoreCase("missingfname"))
			{
				Assert.assertEquals(accountregpage.getMissingFirstName(), "First Name must be between 1 and 32 characters!");
			}
			
			else if(status.equalsIgnoreCase("missinglname"))
			{
				Assert.assertEquals(accountregpage.getMissingLastName(), "Last Name must be between 1 and 32 characters!");
			}
			
			else if(status.equalsIgnoreCase("missingemail"))
			{
				Assert.assertEquals(accountregpage.getMissingEmail(), "E-Mail Address does not appear to be valid!");
			}
			
			else if(status.equalsIgnoreCase("invalidemail"))
			{
				Assert.assertEquals(accountregpage.getMissingEmail(), "E-Mail Address does not appear to be valid!");
			}
			
			else if(status.equalsIgnoreCase("missingphone"))
			{
				Assert.assertEquals(accountregpage.getMissingPhone(), "Telephone must be between 3 and 32 characters!");
			}
			
			else if(status.equalsIgnoreCase("invalidphone"))
			{
				Assert.assertEquals(accountregpage.getMissingPhone(), "Telephone must be between 3 and 32 characters!");
			}
			
			else if(status.equalsIgnoreCase("missingpassword"))
			{
				Assert.assertEquals(accountregpage.getMissingPassword(), "Password must be between 4 and 20 characters!");
			}
			
			else if(status.equalsIgnoreCase("missingconpassword"))
			{
				Assert.assertEquals(accountregpage.getMissingConirmPassword(), "Password confirmation does not match password!");
			}
			
			else if(status.equalsIgnoreCase("missingchkbox"))
			{
				Assert.assertEquals(accountregpage.getPPUncheck(), "Warning: You must agree to the Privacy Policy!");
			}
			
			else
			{
				logger.error("****Test Failed*****");
				logger.debug("****Debug Logs******");
				Assert.fail();
			}
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		
	}

}