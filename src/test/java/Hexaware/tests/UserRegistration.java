package Hexaware.tests;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Hexaware.TestComponents.BaseTest;
import Hexaware.data.DataReader;
import Hexaware.data.DataWriter;
import Hexaware.pageobjects.OpenAccountPage;
import Hexaware.pageobjects.OverviewPage;
import Hexaware.pageobjects.RegisterAccount;

public class UserRegistration extends BaseTest {
	@Test(dataProvider= "RegisterTestData")
	public void registerAccount(
			String firstName, String lastName, String addressStreet, 
			String addressCity, String addressState, String addressZipCode,
			String phoneNumber, String customerSnn, String customerUsername,
			String customerPassword, String repeatedPassword, String username, String password, String createdMessage) {
		// TODO Auto-generated method stub
		HashMap<String,String> list = 
				MapExcelValues(firstName, lastName, addressStreet, 
		 addressCity, addressState, addressZipCode,
		 phoneNumber, customerSnn, customerUsername,
		 customerPassword, repeatedPassword, username, password, createdMessage);
		
		RegisterAccount registerAccount = loginPage.goToRegisterPage();
		registerAccount.registerUserAccount(list);
		Assert.assertEquals(createdMessage, registerAccount.getConfirmationMessage());
//		loginPage.loginApp(list);
		
	}
	
	@Test(dataProvider= "loginTestData")
	public void loginParaBank(String username, String password) {
		loginPage.loginApp(username, password);
	}
	
	@Test(dataProvider= "loginTestData")
	public void userCreateOpenAccount(String username, String password) {
		OverviewPage ovp = loginPage.loginApp(username, password);
		OpenAccountPage oap = ovp.goToOpenNewAccountPage();
		oap.openNewAccount();
		Assert.assertEquals("Congratulations, your account is now open.", oap.getOpenedAccountMessage());
		DataWriter dw = new DataWriter();
		Assert.assertTrue(dw.DataWriterToExcel(username, oap.getCreatedAccountId()));
	}

	
	private HashMap<String, String> MapExcelValues(String firstName, String lastName, String addressStreet, String addressCity,
			String addressState, String addressZipCode, String phoneNumber, String customerSnn, String customerUsername,
			String customerPassword, String repeatedPassword, String username, String password, String createdMessage) {
		// TODO Auto-generated method stub
		HashMap<String,String> list = new HashMap<String,String>();
		list.put("firstName", firstName);
		list.put("lastName", lastName);
		list.put("addressStreet", addressStreet);
		list.put("addressCity", addressCity);
		list.put("addressState", addressState);
		list.put("addressZipCode", addressZipCode);
		list.put("phoneNumber", phoneNumber);
		list.put("customerSnn", customerSnn);
		list.put("customerUsername", customerUsername);
		list.put("customerPassword", customerPassword);
		list.put("repeatedPassword", repeatedPassword);
		list.put("username", username);
		list.put("password", password);
		list.put("createdMessage", createdMessage);
		return list;
	}
	
	@DataProvider(name="RegisterTestData")
	public Object[][] getCustomerData() throws IOException {
		DataReader dr = new DataReader();
		return dr.DataRetrieveFromExcel(System.getProperty("user.dir") + "\\src\\test\\java\\Hexaware\\data\\datacucumber.xlsx");
	}
	
	@DataProvider(name="loginTestData")
	public Object[][] getLoginData() throws IOException {
		DataReader dr = new DataReader();
		return dr.DataRetrieveFromExcel(System.getProperty("user.dir") + "\\src\\test\\java\\Hexaware\\data\\logincredentials.xlsx");
	}

}
