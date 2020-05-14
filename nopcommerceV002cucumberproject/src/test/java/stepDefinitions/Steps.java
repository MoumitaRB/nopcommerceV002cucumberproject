package stepDefinitions;




import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	
	
	@Before
	public void setUp() throws IOException
	{    
		
		//reading properties file
		
		configProp= new Properties();
		FileInputStream ip= new FileInputStream(System.getProperty("user.dir")+"//Configuration//config.properties");
		configProp.load(ip);
		 
		//adding log4j
		logger=LogManager.getLogger(this.getClass());
		
		String br=configProp.getProperty("browser");
		if(br.equals("chrome"))
		{
		   System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
		   driver=new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver= new FirefoxDriver();
			
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
			driver=new InternetExplorerDriver();
		}
		
		logger.info("**************Launching browser************************");
		
	}
	
	

	@Given("User launch Chrome browser")
	public void user_launch_Chrome_browser()
	{
		
		lp=new LoginPage(driver);
	   
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		logger.info("********************Opening URL************************");
		driver.get(url);
		driver.manage().window().maximize();
	    
	}

	@When("User enters Emails as {string} and Password as {string}")
	public void user_enters_Emails_as_and_Password_as(String email, String password) throws InterruptedException {
		
		logger.info("***********************Providing Login details***********************");
		lp.setUserName(email);
		lp.setPassword(password);
		
	   
	}
	
	@When("Click on login")
	public void click_on_login() throws InterruptedException {
		logger.info("**********************Started login***************************");
	    lp.clickOnLoginBtn();
	    Thread.sleep(3000);
	}

	@Then("Page title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
		
		logger.info("*********************Verifying Dashboard title page*************************");
		String act_title=driver.getTitle();
	
	    if(driver.getPageSource().contains("Login was unsuccessful"))
		{
			Assert.assertTrue(false);
		}
	    else
	    {
	    	Assert.assertEquals(title,act_title);
	    }
	}

	@When("User click on logout link")
	public void user_click_on_logout_link() throws InterruptedException {
	   
	   logger.info("***********************Logging out****************************");
	   lp.clickOnLogoutBtn();
	   Thread.sleep(3000);
	}
   
		
	@Then("Close browser")
	public void close_browser() {
		
		logger.info("**********************************Closing the browser******************************");
		driver.quit();
	    
	}

  // Customer feature steps definitions
	
	@Then("User can view Dashboard")
	public void user_can_view_Dashboard() {
		
		addcust=new AddCustomerPage(driver);
		String act_title=addcust.getPageTitle();
		Assert.assertEquals("Dashboard / nopCommerce administration", act_title);
	    
	}

	@When("User clicks on customers Menu")
	public void user_clicks_on_customers_Menu() throws InterruptedException {
	    
		addcust.clickOnCustomerMenu();
		Thread.sleep(2000);
		
	}

	@When("User clicks on customer Menu Items")
	public void user_clicks_on_customer_Menu_Items() throws InterruptedException {
		
	   addcust.clickOnCustomerMenuItems();
	   Thread.sleep(3000);
	}

	@When("click on Add new button")
	public void click_on_Add_new_button() {
	    
		addcust.clickOnAddNewBtn();
	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
	   
		String act_title=addcust.getPageTitle();
		Assert.assertEquals("Add a new customer / nopCommerce administration", act_title);
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		
		logger.info("***********************Provinding customer info******************************");
	    String email=randomString()+"@gmail.com";
	    addcust.setEmail(email);
	    addcust.setPassword("1234");
		addcust.setFirstName("Derek");
		addcust.setLastName("Flaming");
		addcust.setGender("Female");
		addcust.setDateOfBirth("November 2018","17");
		addcust.setCompany("Wipro");
		addcust.setOnTaxExempt();
		addcust.setOnNewsLetter();
		addcust.setCustomerRole("Forum Moderators");
		addcust.setManagerOfVendor("Vendor 1");
		addcust.setAdminComment("This is for testing");
        Thread.sleep(3000);
	    
	}

	@When("Click on save button")
	public void click_on_save_button() throws InterruptedException {
		addcust.clickOnSaveBtn();
		Thread.sleep(3000);
	   
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		
		logger.info("**************************Verifying customer added confirmation msg*************************");
		String body_text=driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue(body_text.contains(msg));
		
	    
	}

  //Search customer by email step definitions
	
	@Then("Enter customer email")
	public void enter_customer_email() {
	   
		logger.info("********************Providing customer email*************************");
		searchcust=new SearchCustomerPage(driver);
		searchcust.setEmailid("victoria_victoria@nopCommerce.com");
		
	}

	@Then("User clicks on search button")
	public void user_clicks_on_search_button() throws InterruptedException {
		
		searchcust.clickOnSearchBtn();
		Thread.sleep(3000);
	   
	}

	@Then("User should find the email in search table")
	public void user_should_find_the_email_in_search_table() {
	    
		logger.info("***************************Searched email is successfull***************************");
		boolean status=searchcust.searchByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
		
	}


	//Search customer by name step definitions
	
	@Then("Enter customer FirstName")
	public void enter_customer_FirstName() {
		
		logger.info("**********************Providing first name****************************");
		searchcust=new SearchCustomerPage(driver);
		searchcust.setFirstName("Brenda");
	    
	}

	@Then("Enter customer LastName")
	public void enter_customer_LastName() {
		
		logger.info("***********************Providing last name***************************");
		searchcust.setLastName("Lindgren");
	    
	}

	@Then("User should find the name in search table")
	public void user_should_find_the_name_in_search_table() {
		
		logger.info("**********************Searched name is successfull************************");
		boolean status=searchcust.searchByName("Brenda", "Lindgren");
		Assert.assertEquals(true, status);
	    
	}


}
