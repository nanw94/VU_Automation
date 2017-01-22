package test;

/**
 * This will be run on Android Emulator
 * Precondition:
 * 		1. Google Chrome should be installed on Emulator
 * 		2. As there will be 2-step verification, the login and verification MUST BE done on the Emulator (Manually or Specified script) BEFORE running this script
 * 		
 */

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.xbosoft.automation.Util_VU;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidChrome {
	private WebDriver driver;
	


	@SuppressWarnings("rawtypes")
	@Test
	public void setUp() throws Exception {
		// Create object of  DesiredCapabilities class and specify android platform
		DesiredCapabilities capabilities=DesiredCapabilities.android();
		 
		 
		// set the capability to execute test in chrome browser
		 capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
		 
		// set the capability to execute our test in Android Platform
		   capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
		 
		// we need to define platform name
		  capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		 
		// Set the device name as well (you can give any name)
		 capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"my phone");
		 
		 // set the android version as well 
		   capabilities.setCapability(MobileCapabilityType.VERSION,"5.0.1");
		 
		 // Create object of URL class and specify the appium server address
		 URL url= new URL("http://127.0.0.1:4723/wd/hub");
		 
		// Create object of  AndroidDriver class and pass the url and capability that we created
		 driver = new AndroidDriver(url, capabilities);
		
//		System.setProperty("webdriver.gecko.driver",Util_VU.GeckodriverPath);
//		System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\extension\\chromedriver.exe");

//		File profileDirectory = new File("C:\\Users\\qabuild\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\5yv76l0t.Selenium");		
//		FirefoxProfile profile = new FirefoxProfile(profileDirectory);		
//		driver = new FirefoxDriver(profile);
		
//		WebDriver driver=new FirefoxDriver();
		//driver=new ChromeDriver();
		
		// Setting Wait Time
		driver.manage().timeouts().implicitlyWait(Util_VU.WAIT_TIME, TimeUnit.SECONDS);
		
		
		// Go to BaseURL
		driver.get("https://68.66.0.106/#/accounts/");
		System.out.println("page has been loaded");

	}
	
	
}
