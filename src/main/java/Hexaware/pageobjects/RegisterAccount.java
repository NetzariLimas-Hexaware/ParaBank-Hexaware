package Hexaware.pageobjects;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class RegisterAccount extends AbstractComponent {
	public RegisterAccount(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="form[id='customerForm'] input[id='customer.firstName']")
	WebElement firstNameInput;
	
	@FindBy(css="form[id='customerForm'] input[id='customer.lastName']")
	WebElement lastNameInput;

	@FindBy(css="form[id='customerForm'] input[id='customer.address.street']")
	WebElement customerAddressStreet;
	
	@FindBy(css="form[id='customerForm'] input[id='customer.address.city']")
	WebElement customerAddressCity;
	
	@FindBy(css="form[id='customerForm'] input[id='customer.address.state']")
	WebElement customerAddressState;
	
	@FindBy(css="form[id='customerForm'] input[id='customer.address.zipCode']")
	WebElement customerAddressZipCode;
	
	@FindBy(css="form[id='customerForm'] input[id='customer.phoneNumber']")
	WebElement customerPhoneNumber;
	
	@FindBy(css="form[id='customerForm'] input[id='customer.ssn']")
	WebElement customerSSN;
	
	@FindBy(css="form[id='customerForm'] input[id='customer.username']")
	WebElement customerUsername;
	
	@FindBy(css="form[id='customerForm'] input[id='customer.password']")
	WebElement customerPassword;
	
	@FindBy(css="form[id='customerForm'] input[id='repeatedPassword']")
	WebElement customerRepeatedPassword;
	
	@FindBy(css="form[id='customerForm'] input[type='submit']")
	WebElement customerSubmitRegister;
	
	@FindBy(css="div[id='rightPanel'] p")
	WebElement confirmAccountCreationMessage;
	
	@FindBy(css="div[id='rightPanel'] title")
	WebElement confirmAccountCreationTitle;
	
	public void registerUserAccount(HashMap<String,String> input) {
		firstNameInput.sendKeys(input.get("firstName"));
		lastNameInput.sendKeys(input.get("lastName"));
		customerAddressStreet.sendKeys(input.get("addressStreet"));
		customerAddressCity.sendKeys(input.get("addressCity"));
		customerAddressState.sendKeys(input.get("addressState"));
		customerAddressZipCode.sendKeys(input.get("addressZipCode"));
		customerPhoneNumber.sendKeys(input.get("phoneNumber"));
		customerSSN.sendKeys(input.get("customerSnn"));
		customerUsername.sendKeys(input.get("customerUsername"));
		customerPassword.sendKeys(input.get("customerPassword"));
		customerRepeatedPassword.sendKeys(input.get("repeatedPassword"));
		customerSubmitRegister.click();
	}
	
	public String getConfirmationMessage() {
		waitElementToAppear(confirmAccountCreationMessage);
		return confirmAccountCreationMessage.getText();
	}
}
