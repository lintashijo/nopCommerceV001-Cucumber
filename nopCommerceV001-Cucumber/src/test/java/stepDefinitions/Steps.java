package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	
	
	@Before
	public void setup() throws IOException, InterruptedException {
		
		configProp=new Properties();
		FileInputStream configPropfile= new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		
		String br=configProp.getProperty("browser");
		
		if(br.equals("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver=new ChromeDriver();
		 	    
		  	 	  
		 }
		
		else if(br.equals("ie")){
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
			
			
		}
		
		
		
		
	}
	
	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {
		
		
		lp=new LoginPage(driver);
	   
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		driver.get(url);
	  
	}

	@When("User enters Email as {string} and password as {string}")
	public void user_enters_Email_as_and_password_as(String email, String password) {
		lp.setUserName(email);
		lp.setPassword(password);
	    
	}

	@When("Click on Login")
	public void click_on_Login() {
		lp.ClickLogin();
	   
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title) {
		
	
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			Assert.assertTrue(false);
		}else {
	  Assert.assertEquals(title, driver.getTitle());  
	 }
	}

	@When("User click on Log  out link")
	public void user_click_on_Log_out_link() throws InterruptedException {
		lp.ClickLogout();
		Thread.sleep(3000);
	   
	}

	

	@Then("User can view Dashboard")
	public void user_can_view_Dashboard() {
		
		addCust= new AddcustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	   
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_Menu() throws InterruptedException 
	{
		Thread.sleep(2000);
	    addCust.clickOnCustomerMenu();
	}

	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException {
		
		Thread.sleep(2000);
		addCust.clickOnCustomersMenuItem();
	   
	}

	@When("click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException {
		addCust.clickOnAddnew();
		Thread.sleep(2000);
		
	    
	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration",  addCust.getPageTitle());
	    
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		
		String email=randomstring()+"@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		addCust.setFirstName("pavan");
		addCust.setLastName("Kumar");
		addCust.setGender("Male");
		addCust.setDob("7/05/1985");
		addCust.setCompanyName("busyQA");
		addCust.setAdminContent("This is for testing.......");
		
		addCust.setManagerofVendor("Vendor 2");
		addCust.setCustomerRoles("Vendors");
		Thread.sleep(2000);
		
		
		
		
		
		
		
	    
	}

	@When("click on Save button")
	public void click_on_Save_button() throws InterruptedException {
		addCust.clickOnSave();
		Thread.sleep(2000);
	    
	}

	@Then("User can view confirmation message {string}")
	
	public void user_can_view_confirmation_message(String msg) {
	Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
			. contains("The new customer has been added successfully"));	
	    
	}


	
	   
	//step for searching t customer using the emsil id 
	
	@When("Enter customer EMail")
	public void enter_customer_EMail() {
	    searchCust=new SearchCustomerPage(driver);
	    searchCust.setEmail("victoria_victoria@nopCommerce.com");
	    
	   
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);
	  
	}

	@Then("user should found Email in the Search table")
	public void user_should_found_Email_in_the_Search_table() {
	   
		boolean status= searchCust.searchcustomerbyEmail("victoria_victoria@nopCommerce.com");
		
		Assert.assertEquals(true, status);
	}
	
	@Then("close the browser")
	public void close_the_browser() {
		driver.quit();
	}
	
	
	//Steps for searching a customer using firstname and lastname
	
	
	@When("Enter customer FirstName")
	public void enter_customer_FirstName() {
		
		 searchCust=new SearchCustomerPage(driver);
		 searchCust.setFirstName("Victoria");
		 
		 
	}
	

	@When("Enter customer LastName")
	public void enter_customer_LastName() {
		 searchCust.setLastName("Terces");
	    
	}

	@Then("user should found Name in the Search table")
	public void user_should_found_Name_in_the_Search_table() {
		
		boolean status= searchCust.searchcustomerbyName("Victoria Terces");
		Assert.assertEquals(true, status);
	    
	}



}
