package com.xbosoft.automation;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import pageFactory.SignInPage;

public class VUTestScript {
	private WebDriver driver;
	private String scrFolder;
	public String url;
	public String currentUserID;

	public void waitForLoad(WebDriver driver) {
		new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	@BeforeTest
	public void setUp() throws Exception {

		// System.setProperty("webdriver.gecko.driver",
		// Util_VU.GeckodriverPath);
		System.setProperty("webdriver.chrome.driver", Util_VU.ChromedriverPath);

		// load the Firefox profile that has already added the product url into
		// exceptions.
		// File profileDirectory = new File(
		// "C:\\Users\\qabuild\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\5yv76l0t.Selenium");
		// FirefoxProfile profile = new FirefoxProfile(profileDirectory);
		// driver = new FirefoxDriver(profile);

		driver = new ChromeDriver();

		// WebDriver driver=new FirefoxDriver();
		// driver=new ChromeDriver();

		// Setting Wait Time
		driver.manage().timeouts().implicitlyWait(Util_VU.WAIT_TIME, TimeUnit.SECONDS);

		// create folder for screenshots
		scrFolder = "d:/Result/"
				+ new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(Calendar.getInstance().getTime()).toString();
		new File(scrFolder).mkdir();

		// Go to BaseURL
		driver.get(Util_VU.BASEURL);
		driver.manage().window().maximize();
	}

	@Test(priority = 0) // VU-10:Login-Correct Username/Password

	public void login() throws Exception {

		SignInPage objSignInPage = new SignInPage(driver);

		// insert username and password, login
		objSignInPage.signIn(Util_VU.LOGIN_ID, Util_VU.PASSWORD);

		// get
		String step2 = driver.getTitle();
		System.out.println(step2);
		Thread.sleep(3000);

	}

	/**
	 * The below test will be run when the 2-step verification mode is on steps:
	 * 1. click send secure code to phone number 2. open a new tab, go to
	 * textnot.com to get the number and 3. get back to the original tab and
	 * input the number 4. click verify button
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void verification() throws Exception {
		// click Send Verification Code button
		driver.findElement(By.xpath("//*[@id='login']/body/div/div[1]/form/div/ul/li[2]/button")).click();

		Thread.sleep(3000);

		// open a new tab in Firefox
		// driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +
		// "t");

		// use below to open new tab for Chrome
		((JavascriptExecutor) driver).executeScript("window.open();");

		// switch to new tab
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

		// Go to textnow.com
		driver.get("https://www.textnow.com/messaging");
		Thread.sleep(3000);

		// Sign in
		driver.findElement(By.id("loginUsername")).sendKeys("elaineren");
		driver.findElement(By.id("loginPassword")).sendKeys("5691219-kk");
		driver.findElement(By.id("submitLogin")).click();

		// wait until messages is visible
		new WebDriverWait(driver, 60).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='chat-item'][last()]//span")));

		// go to text message page
		// driver.findElement(By.xpath("//*[@id='textnow-navbar']/div/nav[1]/div/div[2]/a[1]")).click();
		// Thread.sleep(30000);

		// if (driver.findElement(By.id("buttons")).isEnabled()) {
		// WebElement element = driver.findElement(By.id("close-button"));
		// Actions actions = new Actions(driver);
		// actions.moveToElement(element).click().perform();
		// driver.findElement(By.id("buttons")).click();
		// }
		// Thread.sleep(10000);
		// driver.findElement(By.xpath("//body/div[8]/div/div[1]/a[3]")).click();

		// find out the last message and get the text message
		String secureMsg = driver.findElement(By.xpath("//li[@class='chat-item'][last()]//span")).getText();
		System.out.println(secureMsg);

		// trim the message to get the secure code only (the last 6 digital
		// number)
		String secureCode = secureMsg.substring(secureMsg.length() - 6, secureMsg.length());
		System.out.println(secureCode);

		// Log out from textnow.com
		// driver.findElement(By.linkText("Log Out"));

		// close the tab for textnow.com
		driver.close();

		// Switch back to original tab
		driver.switchTo().window(tabs.get(0));

		// input secure code and click Verify button
		driver.findElement(By.id("AuthyCode")).sendKeys(secureCode);
		driver.findElement(By.id("VerifyAuthCode")).click();
		waitForLoad(driver);
		System.out.println("after insert the secure code and click verify the page is " + driver.getTitle());
	}

	// VU-11:Accounts Overview
	/**
	 * VU-11:Accounts Overview * Check Points: (Automation will verify this by
	 * take screenshot only.) 1'MyVeteransUnited' page displays. There are two
	 * parts: 'myAccounts' card with 'Account ID', 'Finance Account' and
	 * 'Current Stage' info displays, a button named 'View Account'; 'I want to
	 * ...' sidebar with 'Start a New Home Loan', 'View Helpful Resources',
	 * 'Speak to a Representative' and 'Change my Account Settings'. All these
	 * with no format confusion. 2 A sub-navigator displays with four items:
	 * 'To-Do's', 'Details', 'Documents', ' My Loan Team' under 'myAccounts'
	 * navigator; Current page is 'To-Do's', 'To-Do's CheckList' display with
	 * your current stage, and with no format confusion.
	 * 
	 */
	@Test(priority = 2, dependsOnMethods = { "verification" })
	public void viewMyAccount() throws Exception {

		// AccountPage objAccountPage = new AccountPage(driver);
		//
		// System.out.println("viewMyAccount " + driver.getTitle());

		// take screen shot for Account page
		Thread.sleep(5000);
		System.out.println("take screenshot for account page");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(scrFolder + "/" + "AccountPage1" + ".png"));
		
		
//		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//[@id='wrapper']/div/div[2]/div/vu-opportunity-card/div/div[3]/a[1]/span")));
		driver.findElement(By.xpath("//*[@id='wrapper']/div/div[2]/div/vu-opportunity-card/div/div[3]/a[1]")).click();
		System.out.println("take screenshot for todo page");
		scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(scrFolder + "/" + "todo" + ".png"));
		
		url = driver.getCurrentUrl();
		currentUserID=url.substring(url.indexOf("/")+1);
		System.out.println(currentUserID);
		
		driver.get("https://68.66.0.106/#/checklist/"+currentUserID);
		

//		Details
//		//*[@id="wrapper"]/div/div[2]/div/vu-opportunity-card/div/div[3]/a[2]
//		/html/body/div[2]/div[1]/vu-chrome-header/div/div[2]/vu-local-navigation/div/nav/ul/li[2]
//		/html/body/div[2]/div[1]/vu-chrome-header/div/div[2]/vu-local-navigation/div/nav/ul/li[2]
//		/html/body/div[2]/div[1]/vu-chrome-header/div/div[2]/vu-local-navigation/div/nav/ul/li[2]/a
//		<vu-icon id="Details"><svg xmlns="http://www.w3.org/2000/svg" version="1.1" x="0" y="0" viewBox="0 0 64 64" xml:space="preserve"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svgIconDetails"></use></svg></vu-icon>
	}
}

/**
 * Click 'Profile Settings' link on the right top corner page(If no this link on
 * the top right corner, then click 'Profile & Settings' button), or you can
 * click 'Chang my Account Settings' link in the sidebar. 'myVeteransUnited
 * Profile & Settings' page displays. There are two parts on this page: 'Contact
 * Info' card with 'ACCOUNT HOLDER', 'CURRENT ADDRESS', 'EMAIL ADDRESS' and
 * 'PHONE NUMBER' info; 'Security Settings' card with 'USERNAME', 'PASSWORD',
 * 'SECURITY QUESTION 1', 'SECURITY QUESTION 2', 'SECURITY QUESTION 3'. All
 * these with no format confusion.
 */
