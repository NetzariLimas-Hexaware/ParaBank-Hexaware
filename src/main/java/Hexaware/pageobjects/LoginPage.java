package Hexaware.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	private WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[id=\"loginPanel\"] form[name=\"login\"] input[name=\"username\"]")
	WebElement userNameInput;
	
	@FindBy(css="div[id=\"loginPanel\"] form[name=\"login\"] input[name=\"password\"]")
	WebElement passwordInput;
	
	@FindBy(css="div[id=\"loginPanel\"] form[name=\"login\"] div[class=\"login\"] input[type=\"submit\"]")
	WebElement loginButton;
	
	@FindBy(css="p a[href*=\"register\"]")
	WebElement registerLink;
	
	@FindBy(css="p a[href*=\"lookup\"]")
	WebElement forgotLink;
	
	public void goToLoginPage() {
		driver.get("https://parabank.parasoft.com/parabank/index.htm");
	}
	
	public RegisterAccount goToRegisterPage() {
		registerLink.click();
		return new RegisterAccount(driver);
	}
	
	public OverviewPage loginApp(String username, String password, boolean regression) {
		if(regression) {
			return new OverviewPage(driver);
		}
		
		userNameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
		return new OverviewPage(driver);
	}
	
}
