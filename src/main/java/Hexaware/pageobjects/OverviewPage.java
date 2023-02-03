package Hexaware.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class OverviewPage extends AbstractComponent {
	WebDriver driver;
	public OverviewPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[id='leftPanel'] li a[href*='openaccount']")
	WebElement openAccountLink;

	public OpenAccountPage goToOpenNewAccountPage() {
		waitElementToAppear(openAccountLink);
		openAccountLink.click();
		return new OpenAccountPage(driver);
	}
}
