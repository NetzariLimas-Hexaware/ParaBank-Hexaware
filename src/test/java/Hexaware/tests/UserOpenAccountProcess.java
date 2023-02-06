package Hexaware.tests;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Hexaware.TestComponents.BaseTest;
import Hexaware.TestComponents.MapExcel;
import Hexaware.data.DataReader;
import Hexaware.data.DataWriter;
import Hexaware.pageobjects.OpenAccountPage;
import Hexaware.pageobjects.OverviewPage;
import Hexaware.pageobjects.RegisterAccount;

public class UserOpenAccountProcess extends BaseTest {
	@Test(dataProvider="RegisterTestData",groups={"Regression"})
	public void RegressionUserProcess(String firstName, String lastName, String addressStreet, 
			String addressCity, String addressState, String addressZipCode,
			String phoneNumber, String customerSnn, String customerUsername,
			String customerPassword, String repeatedPassword, String username, String password, String createdMessage) {
		
		// Register Process //
		HashMap<String,String> list = 
				MapExcel.MapExcelValues(firstName, lastName, addressStreet, 
		 addressCity, addressState, addressZipCode,
		 phoneNumber, customerSnn, customerUsername,
		 customerPassword, repeatedPassword, username, password, createdMessage);
		
		RegisterAccount registerAccount = loginPage.goToRegisterPage();
		registerAccount.registerUserAccount(list);
		Assert.assertEquals(createdMessage, registerAccount.getConfirmationMessage());
		
		//Then AutoLogin attemped.
		
		OverviewPage ovp = loginPage.loginApp(username, password, true); // tercer parametro es Regression
		// Si es regression entonces solo devolvemos el objeto con el driver para seguir usandolo.
		
		OpenAccountPage oap = ovp.goToOpenNewAccountPage();
		oap.openNewAccount();
		Assert.assertEquals("Congratulations, your account is now open.", oap.getOpenedAccountMessage());
		DataWriter dw = new DataWriter();
		Assert.assertTrue(dw.DataWriterToExcel(username, oap.getCreatedAccountId()));
	}
	
	// User Registration only//
	@Test(dataProvider= "RegisterTestData")
	public void registerAccount(
			String firstName, String lastName, String addressStreet, 
			String addressCity, String addressState, String addressZipCode,
			String phoneNumber, String customerSnn, String customerUsername,
			String customerPassword, String repeatedPassword, String username, String password, String createdMessage) {
		// TODO Auto-generated method stub
		HashMap<String,String> list = 
				MapExcel.MapExcelValues(firstName, lastName, addressStreet, 
		 addressCity, addressState, addressZipCode,
		 phoneNumber, customerSnn, customerUsername,
		 customerPassword, repeatedPassword, username, password, createdMessage);
		
		RegisterAccount registerAccount = loginPage.goToRegisterPage();
		registerAccount.registerUserAccount(list);
		Assert.assertEquals(createdMessage, registerAccount.getConfirmationMessage());		
	}
	
	@Test(dataProvider= "loginTestData")
	public void loginParaBank(String username, String password) {
		loginPage.loginApp(username, password, false);
	}
	
	@Test(dataProvider= "loginTestData", dependsOnMethods={"RegressionUserProcess","loginParaBank"})
	public void userCreateOpenAccount(String username, String password) {
		OverviewPage ovp = loginPage.loginApp(username, password, false);
		OpenAccountPage oap = ovp.goToOpenNewAccountPage();
		oap.openNewAccount();
		Assert.assertEquals("Congratulations, your account is now open.", oap.getOpenedAccountMessage());
		DataWriter dw = new DataWriter();
		Assert.assertTrue(dw.DataWriterToExcel(username, oap.getCreatedAccountId()));
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
