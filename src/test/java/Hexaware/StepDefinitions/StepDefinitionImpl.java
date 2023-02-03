package Hexaware.StepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Hexaware.TestComponents.BaseTest;
import Hexaware.pageobjects.RegisterAccount;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	public RegisterAccount registerAccount;
	@Given("^User navigates to registration page$")
    public void user_navigates_to_registration_page() throws Throwable {
		registerAccount = loginPage.goToRegisterPage();
    }

    @When("^User fills the registration form from given sheetname (.+) and rownumber (.+)$")
    public void user_fills_the_registration_form_from_given_sheetname_and_rownumber(String sheetname, Integer rownumber) throws Throwable {
    	FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\Hexaware\\data\\datacucumber.xlsx");
    	//Creating a workbook
    	
    }

    @Then("^Get logged and recieve message \"([^\"]*)\" with customer name$")
    public void get_logged_and_recieve_message_something_with_customer_name(String strArg1) throws Throwable {
        
    }

    @And("^Check for \"([^\"]*)\" message$")
    public void check_for_something_message(String strArg1) throws Throwable {
        
    }
}
