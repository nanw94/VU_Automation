package test;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class TestRemoteWebDriver {
	WebDriver driver;
	
@Test
	void setup() throws Exception{
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	
	driver = new RemoteWebDriver(new URL("http://localhost:7055/hub"),capabilities);
	driver.get("http://google.com");
	}
	
	//this method is currently unavailable as 
}
