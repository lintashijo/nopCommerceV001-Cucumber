package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {
	
	public WebDriver driver;
	public LoginPage lp;
	
	public AddcustomerPage addCust;
	public SearchCustomerPage searchCust;
	public Properties configProp;
	
	
	public static String randomstring()
	{
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
			
		return (generatedString1);
		
	}

}
