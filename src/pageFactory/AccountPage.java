package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//*[@id='wrapper']/div/div[2]/div/vu-opportunity-card/div/div[3]/a[1]/span")
	public WebElement Tasklist;
		
	@FindBy(id="Tasklist")
	public WebElement navTasklist;

	@FindBy(id="Details")
	public WebElement navDetails;
	
	@FindBy(id="Documents")
	public WebElement navDocuments;
	
	@FindBy(id="Payments")
	public WebElement navPayments;
	
	@FindBy(id="Team")
	public WebElement navTeam;
	
	@FindBy(id="Support")
	public WebElement footerSupport;
	
	@FindBy(id="Settings")
	public WebElement footerSettings;

		
		public AccountPage(WebDriver driver){
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
//		//view Todo's list
//		public void viewTodo(){
//			todo.click();
//		}

	
}
