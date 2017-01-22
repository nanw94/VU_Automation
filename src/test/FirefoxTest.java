package test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.xbosoft.automation.Util_VU;

public class FirefoxTest {

	@Test

	void openNewTab() throws Exception {
		System.setProperty("webdriver.gecko.driver", Util_VU.GeckodriverPath);

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();

		driver.get("https://www.textnow.com/login");

		// Assert.assertTrue(driver.findElement(By.id("loginUsername")).isEnabled());
		driver.findElement(By.id("loginUsername")).sendKeys("dffff");

	}
}
