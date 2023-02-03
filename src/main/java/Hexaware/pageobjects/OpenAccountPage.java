package Hexaware.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Hexaware.AbstractComponents.AbstractComponent;

public class OpenAccountPage extends AbstractComponent {
	WebDriver driver;
	public OpenAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="type")
	WebElement accountType;
	
	@FindBy(id="fromAccountId")
	WebElement existingAccount;
	
	@FindBy(css="form input[type='submit']")
	WebElement submitButton;
	
	@FindBy(css="div[ng-app='AddAccountApp'] div[ng-if='showResult'] p:first-of-type")
	WebElement accountCreatedMessage;
	
	@FindBy(css="div[ng-app='AddAccountApp'] div[ng-if='showResult'] p:last-of-type a[id='newAccountId']")
	WebElement newAccountId;
	
	public void openNewAccount() {
		Select dropAccountType = new Select(accountType);
		Select dropexistingAccount = new Select(existingAccount);
		waitElementToAppear(accountType);
		dropAccountType.selectByValue("1"); // SAVINGS
		waitElementToAppear(existingAccount);
		dropexistingAccount.selectByIndex(0);
		submitButton.click();
	}
	
	public String getOpenedAccountMessage() {
		return accountCreatedMessage.getText();
	}
	
	public String getCreatedAccountId() {
		return newAccountId.getText();
	}
}
