package test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

//import com.xbosoft.automation.Util_VU;

public class ScrollTest {
	AppiumDriver driver;

	public void swipeToDown(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, during);
	}

	public void swipeToLeft(AndroidDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, during);
	}

	@Test

	public void test1() throws MalformedURLException, Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\extension\\chromedriver_win32\\chromedriver.exe");
		// Create object of DesiredCapabilities class and specify android
		// platform
		// DesiredCapabilities capabilities = new DesiredCapabilities();

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "4.4");

		// // set the capability to execute test in chrome browser
		// capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		//
		// // set the capability to execute our test in Android Platform
		// capabilities.setCapability(MobileCapabilityType.PLATFORM,
		// Platform.ANDROID);
		//
		// // we need to define platform name
		// capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
		// "Android");
		//
		// // Set the device name as well (you can give any name)
		// capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "my
		// phone");
		//
		// // set the android version as well
		// capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,
		// "0");
		// // set the android version as well
		// capabilities.setCapability(MobileCapabilityType.VERSION, "5.0.1");

		// Create object of URL class and specify the appium server address
		URL url = new URL("http://127.0.0.1:4723/wd/hub");

		// Create object of AndroidDriver class and pass the url and capability
		// that we created
		driver = new AndroidDriver(url, capabilities);

		driver.get("http://google.com");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("D:\\1.jpg"));

//		jse.executeScript("window.scrollBy(0,250)", "");

		// // Open url
		// driver.get("http://google.com");
		//
		// File scrFile = ((TakesScreenshot)
		// driver).getScreenshotAs(OutputType.FILE);
		//
		// FileUtils.copyFile(scrFile, new File("D:\\1.jpg"));
		//
		//
		// swipeToDown(driver,1);
		//
		// Thread.sleep(2000);
		//
		// File scrFile1 = ((TakesScreenshot)
		// driver).getScreenshotAs(OutputType.FILE);
		//
		// FileUtils.copyFile(scrFile1, new File("D:\\2.jpg"));

		// WebDriver pcdriver = new ChromeDriver();
		// pcdriver.get("http://baidu.com");

		// close the browser
		// driver.quit();

	}

}