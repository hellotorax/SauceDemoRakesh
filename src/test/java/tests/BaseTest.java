package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import utilities.ConfigReader;
import utilities.ConfigWriter;


/**
 * Author: Rakesh Kumar Padhiary
 */
public class BaseTest {

	public static WebDriver driver;
	

	private static Logger _log = LoggerFactory.getLogger(BaseTest.class);

	@BeforeSuite(alwaysRun = true)
	void Config()
	{
		ConfigWriter.setPropertyValue("logger.file", "webLogger", ConfigReader.get("config.path"));
	}


	@Parameters({"browser","infra"})
	@BeforeClass
	public void launchBrowser(String browserName,String infrastructure) throws MalformedURLException{
		if(infrastructure.equals("local"))
		{
			try{
				if(browserName.equals("chrome")){
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
				}else if(browserName.equals("firefox")){
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
				}
				else if(browserName.equals("edge")){
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
				}
				else if(browserName.equals("iexplorer")){
					WebDriverManager.iedriver().setup();
					driver = new InternetExplorerDriver();
				}
				else if(browserName.equals("opera")){
					WebDriverManager.operadriver().setup();
					driver = new OperaDriver();
				}

				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
			}catch (WebDriverException e){
				System.out.println(e.getMessage());
			}
		}
		else {
			DesiredCapabilities dc = new DesiredCapabilities();
			if(browserName.equals("firefox")) {
			
			dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
			dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
	
			}
			
			else if(browserName.equals("chrome")){
				dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
				dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
			}
			URL url = new URL("https://localhost:4444/wd/hub");
			driver=new RemoteWebDriver(url,dc);
		
		}


	}

	@AfterClass
	public void tearDownBrowser(){
		driver.quit();
	}
}
