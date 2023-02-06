package Hexaware.TestComponents;

import java.util.HashMap;

public class MapExcel {
	public static HashMap<String, String> MapExcelValues(String firstName, String lastName, String addressStreet, String addressCity,
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
}
