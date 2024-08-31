package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.github.javafaker.Faker;

public class BaseClass {
	
	public static WebDriver driver;
	Faker fake;
	public Logger logger;
	Properties prop;
	
	public Properties readConfig() throws IOException
	{
		prop = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop.load(fis);
		return prop;
	}
	
	
	@BeforeClass(groups = {"Sanity", "Regression", "Master", "Datadriven"})
	@Parameters({"os", "browser"})
	public void setUp(String os, String br) throws IOException, URISyntaxException
	{
		logger = LogManager.getLogger(this.getClass());
		
		//Local execution of the framework
		if(readConfig().getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome" : driver = new ChromeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "safari" : driver = new SafariDriver(); break;
			default: System.out.println("Invalid Browser"); return;		
			}
			
		}
		
		//Remote Execution
		else if(readConfig().getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			  DesiredCapabilities desiredcap = new DesiredCapabilities();

	            // Set platform
	            if (os.equalsIgnoreCase("Windows")) {
	                desiredcap.setPlatform(Platform.WIN10);
	            }
	              else if (os.equalsIgnoreCase("mac")) {
	                desiredcap.setPlatform(Platform.MAC);
	            } else if (os.equalsIgnoreCase("linux")) {
	                desiredcap.setPlatform(Platform.LINUX);
	            } else {
	                throw new IllegalArgumentException("Invalid OS name provided: " + os);
	            }

	            // Set browser
	            if (br.equalsIgnoreCase("chrome")) {
	                desiredcap.setBrowserName("chrome");
	            } else if (br.equalsIgnoreCase("edge")) {
	                desiredcap.setBrowserName("MicrosoftEdge");
	            } else {
	                throw new IllegalArgumentException("Invalid browser name provided: " + br);
	            }

	            // Initialize RemoteWebDriver
	            URI uri = new URI("http://localhost:4444/wd/hub");
	            URL url = uri.toURL();
	            driver = new RemoteWebDriver(url, desiredcap);			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		driver.get(readConfig().getProperty("appurl"));
	}
	

	
	@AfterClass(groups = {"Sanity", "Regression", "Master", "Datadriven"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String fname()
	{
		fake = new Faker();
		String name = fake.name().firstName();
		return name;
	}	
	public String lname()
	{
		fake = new Faker();
		String lname = fake.name().lastName();
		return lname;
	}
	public String email()
	{
		fake = new Faker();
		String email = fake.internet().emailAddress();
		return email;
	}
	public String phone()
	{
		fake = new Faker();
		String phone = fake.phoneNumber().cellPhone();
		return phone;
	}
	public String password()
	{
		fake = new Faker();
		String password = fake.internet().password(5, 8, true, true, true);
		return password;
	}


	public String captureScreen(String tname) throws IOException  {
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date()); //TimeStamp	
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tname +"_"+ timestamp+".png";
		
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
