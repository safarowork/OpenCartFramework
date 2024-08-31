package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}


	@FindBy(xpath="//input[@id='input-firstname']")   
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")   
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")  
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")  
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")  
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")  
	WebElement txtConfirmPassword;
	

	@FindBy(xpath="//input[@name='agree']")  
	WebElement chkPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")  
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")  
	WebElement msgConfirmation;
	
	//error messages
	@FindBy(xpath="//div[.='First Name must be between 1 and 32 characters!']")  
	WebElement msgMissingFirstName;
	
	@FindBy(xpath="//div[.='Last Name must be between 1 and 32 characters!']")  
	WebElement msgMissingLastName;
	
	@FindBy(xpath="//div[.='E-Mail Address does not appear to be valid!']")  
	WebElement msgMissingEmail;
	
	@FindBy(xpath="//div[.='Telephone must be between 3 and 32 characters!']")  
	WebElement msgMissingPhone;
	
	@FindBy(xpath="//div[.='Password must be between 4 and 20 characters!']")  
	WebElement msgMissingPassword;
	
	@FindBy(xpath="//div[.='Password confirmation does not match password!']")  
	WebElement msgMissingConirmPassword;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")  
	WebElement btnPPUncheck;
	
	@FindBy(xpath="//div[@class='list-group']//a")  
	List<WebElement> linksmain;
	
	@FindBy(xpath="//footer//div[@class=\"row\"]//div[1]//li//a")  
	List<WebElement> linksfooterInformation;
	
	@FindBy(xpath="//footer//div[@class=\"row\"]//div[2]//li//a")  
	List<WebElement> linksfooterCustomerService;
	
	@FindBy(xpath="//footer//div[@class=\"row\"]//div[3]//li//a")  
	List<WebElement> linksfooterExtras;
	
	@FindBy(xpath="//footer//div[@class=\"row\"]//div[4]//li//a")  
	List<WebElement> linksfooterMyAccount;
	
	@FindBy(xpath="//div[@id='top-links']//li//span")  
	List<WebElement> linksTop;
	
	@FindBy(xpath="//ul[@class=\"nav navbar-nav\"]//li//a")  
	List<WebElement> linksHeader;	
	
	
	
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String phone)
	{
		txtTelephone.sendKeys(phone);
	}
	
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void setConirmPassword(String password)
	{
		txtConfirmPassword.sendKeys(password);
	}
	
	public void setPrivacyPolicy()
	{
		chkPolicy.click();
	}
	
	public void clickContinue()
	{
		//sol1
		btnContinue.click();
		
		//sol2
		//btnContinue.submit();
		
		//sol3
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].click();", btnContinue);
		
		//sol4
		//Actions act = new Actions(driver);
		//act.moveToElement(btnContinue).click();
		
		//sol5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//sol6
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();	
	}
	
	public String getConfirmationMessage()
	{
		try {
			return msgConfirmation.getText();
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}
	
	public String getMissingFirstName()
	{
		String msg = msgMissingFirstName.getText();
		return msg;
	}
	
	public String getMissingLastName()
	{
		String msg = msgMissingLastName.getText();
		return msg;
	}
	
	public String getMissingEmail()
	{
		String msg = msgMissingEmail.getText();
		return msg;
	}
	
	public String getMissingPhone()
	{
		String msg = msgMissingPhone.getText();
		return msg;
	}
	
	public String getMissingPassword()
	{
		String msg = msgMissingPassword.getText();
		return msg;
	}
	
	public String getMissingConirmPassword()
	{
		String msg = msgMissingConirmPassword.getText();
		return msg;
	}
	
	public String getPPUncheck()
	{
		String msg = btnPPUncheck.getText();
		return msg;
	}
	
	public List<String> getmainlinks()
	{
		List<String> linknames = new ArrayList<String>();
		
		for(int i=0;i<linksmain.size();i++)
		{
			String name = linksmain.get(i).getText();
			linknames.add(i, name);
		}
		
		return linknames;
	}
	
	
	public List<String> getFooterInformationLinks()
	{
		List<String> footerInfoLinks = new ArrayList<String>();
		
		for(WebElement l:linksfooterInformation)
		{
			String name =l.getText();
			footerInfoLinks.add(name);
		}
		
		return footerInfoLinks;
	}
	
	public List<String> getFooterCustomerServiceLinks()
	{
		List<String> footerCustomerServiceLinks = new ArrayList<String>();
		
		for(WebElement l:linksfooterCustomerService)
		{
			String name =l.getText();
			footerCustomerServiceLinks.add(name);
		}
		
		return footerCustomerServiceLinks;
	}
	
	public List<String> getFooterExtrasLinks()
	{
		List<String> footerExtras = new ArrayList<String>();
		
		for(WebElement l:linksfooterExtras)
		{
			String name =l.getText();
			footerExtras.add(name);
		}
		
		return footerExtras;
	}
	
	public List<String> getFooterMyAccountLinks()
	{
		List<String> footerMyAccount = new ArrayList<String>();
		
		for(WebElement l:linksfooterMyAccount)
		{
			String name =l.getText();
			footerMyAccount.add(name);
		}
		
		return footerMyAccount;
	}
	
	public List<String> getTopLinks()
	{
		List<String> toplink = new ArrayList<String>();
		
		for(WebElement l:linksTop)
		{
			String name =l.getText();
			toplink.add(name);
		}
		
		return toplink;
	}
	
	public List<String> getHeaderLinks()
	{
		List<String> headerlink = new ArrayList<String>();
		
		for(WebElement l:linksHeader)
		{
			String name =l.getText();
			headerlink.add(name);
		}
		
		return headerlink;
	}
	
}
