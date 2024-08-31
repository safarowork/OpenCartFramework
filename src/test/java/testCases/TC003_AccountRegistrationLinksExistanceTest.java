package testCases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC003_AccountRegistrationLinksExistanceTest extends BaseClass{
	
	AccountRegistrationPage accountreg;
	HomePage homepage;
	
	@Test(priority=1, groups = {"Regression","Master"})
	void testAccountRegistrationMainLinks()
	{
		logger.info("********TC005_AccountRegistrationLinksTest started*******");
		
		logger.info("********AccountRegistrationMainLinksTest started*******");
		
		try 
		{
			homepage = new HomePage(driver);
			accountreg = new AccountRegistrationPage(driver);
			
			homepage.clickMyAccount();
			homepage.clickRegister();
			
			List<String> expectedMainLinkList = Arrays.asList("Login", "Register", "Forgotten Password", 
					"My Account", "Address Book", "Wish List", "Order History", "Downloads", "Recurring payments", "Reward Points",
					"Returns", "Transactions", "Newsletter") ;
			
			List<String> actualLinkList = accountreg.getmainlinks();
			
			Assert.assertTrue(actualLinkList.containsAll(expectedMainLinkList));
		}
		catch(Exception e)
		{
			logger.error("**********Error Logging*********");
			logger.debug("******Debug Logging********");
			Assert.fail();
		}
	}
	
	@Test(priority=2)
	void testAccountRegistrationFooterLinks()
	{
		
		logger.info("********AccountRegistrationFooterLinksTest started*******");
		
		List<String> expectedInfoLinks = Arrays.asList("About Us", "Delivery Information", "Privacy Policy", "Terms & Conditions");
		List<String> expectedCustomerServiceLinks = Arrays.asList("Contact Us", "Returns", "Site Map");
		List<String> expectedExtrasLinks = Arrays.asList("Brands", "Gift Certificates", "Affiliate", "Specials");
		List<String> expectedMyAccountLinks = Arrays.asList("My Account", "Order History", "Wish List", "Newsletter");
		
		try
		{
			List<String> actualInfoLinks = accountreg.getFooterInformationLinks();
			List<String> actualCustomerServiceLinks = accountreg.getFooterCustomerServiceLinks();
			List<String> actualExtrasLinks = accountreg.getFooterExtrasLinks();
			List<String> actualMyAccountLinks = accountreg.getFooterMyAccountLinks();
			
			Assert.assertTrue(actualInfoLinks.containsAll(expectedInfoLinks));
			Assert.assertTrue(actualCustomerServiceLinks.containsAll(expectedCustomerServiceLinks));
			Assert.assertTrue(actualExtrasLinks.containsAll(expectedExtrasLinks));
			Assert.assertTrue(actualMyAccountLinks.containsAll(expectedMyAccountLinks));
		}
		catch(Exception e)
		{
			logger.error("**********Error Logging*********");
			logger.debug("******Debug Logging********");
			Assert.fail();
		}
	}

	@Test(priority=3)
	void testAccountRegistrationTopLinks()
	{
		
		logger.info("********AccountRegistrationTopLinksTest started*******");
		
		List<String> expectedTopLinks = Arrays.asList("123456789", "My Account", "Wish List (0)", "Shopping Cart", "Checkout");
		
		try
		{
			List<String> actualTopLinks = accountreg.getTopLinks();
			System.out.println(actualTopLinks);
			Assert.assertTrue(actualTopLinks.containsAll(expectedTopLinks));
		}
		catch(Exception e)
		{
			logger.error("**********Error Logging*********");
			logger.debug("******Debug Logging********");
			Assert.fail();
		}
	}
	
	@Test(priority=4)
	void testAccountRegistrationHeaderLinks()
	{
		
		logger.info("********AccountRegistrationHeaderLinksTest started*******");
		
		List<String> expectedHeaderLinks = Arrays.asList("Desktops", "Laptops & Notebooks", "Components", "Tablets",
				"Software", "Phones & PDAs", "Cameras", "MP3 Players");
		
		try
		{
			List<String> actualHeaderLinks = accountreg.getHeaderLinks();
			System.out.println(actualHeaderLinks);
			Assert.assertTrue(actualHeaderLinks.containsAll(expectedHeaderLinks));
		}
		catch(Exception e)
		{
			logger.error("**********Error Logging*********");
			logger.debug("******Debug Logging********");
			
			Assert.fail();
		}
	}
	
}
