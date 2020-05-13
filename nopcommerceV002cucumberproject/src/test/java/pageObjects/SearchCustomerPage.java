package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
	public WebDriver driver;
	public WaitHelper waithelper;
	
	public SearchCustomerPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waithelper= new WaitHelper(driver);  
	}
	  
	
	//locators
	
		@FindBy(how=How.ID, using="SearchEmail")
		@CacheLookup
		WebElement txtEmail;
		
		@FindBy(how=How.ID, using="SearchFirstName")
		@CacheLookup
		WebElement txtFirstName;
		
		@FindBy(how=How.ID, using="SearchLastName")
		@CacheLookup
		WebElement txtLastName;
		
		@FindBy(how=How.ID, using="search-customers")
		@CacheLookup
		WebElement searchBtn;

		@FindBy(how=How.XPATH, using="//div[@class='dataTables_scrollBody']//table//tbody//tr")
		@CacheLookup
		List<WebElement>  tableRows;
		
		@FindBy(how=How.XPATH, using="//div[@class='dataTables_scrollBody']//table//tbody//tr[1]//td")
		@CacheLookup
		List<WebElement> tableColumns;
		
		//actions
		
		public void setEmailid(String emailid)
	    {   
			waithelper.waitForElement(txtEmail, 30);
	    	txtEmail.clear();
	    	txtEmail.sendKeys(emailid);
	    }
	    
		public void setFirstName(String fName)
		{   
			waithelper.waitForElement(txtFirstName, 30);
			txtFirstName.clear();
			txtFirstName.sendKeys(fName);
		}
		
	    public void setLastName(String lName)
	    {   
	    	waithelper.waitForElement(txtLastName, 30);
	    	txtLastName.clear();
	    	txtLastName.sendKeys(lName);
	    }
		
	    public void clickOnSearchBtn()
	    {
	    	searchBtn.click();
	    	
	    }
		
	    public int getNoOfRow()
	    {
	    	return(tableRows.size());
	    }
	    
	    public int getNoOfCol()
	    {
	    	return(tableColumns.size());
	    }
	    
	    public boolean searchByEmail(String email)
	    {  boolean flag=false;
	    
	    	for(int i=1; i<=getNoOfRow();i++)
	    	{
	    		String email_id=driver.findElement(By.xpath("//div[@class='dataTables_scrollBody']//table//tbody//tr["+i+"]//td[2]")).getText();
	    	    System.out.println(email_id);
	    		
	    		if(email_id.equals(email))
	    	    {
	    	    	flag=true;
	    	    	break;
	    	    }
	    	    
	    	  
	    	  }
	    	
			return flag;
	    	
	      }
	    
	    public boolean searchByName(String custFirstname, String custLastname)
	    {    
	    	boolean flag=false;
	    	
	    	for(int i=1; i<=getNoOfRow(); i++)
	    	{
	    		String cus_name=driver.findElement(By.xpath("//div[@class='dataTables_scrollBody']//table//tbody//tr["+i+"]/td[3]")).getText();
	    	    System.out.println(cus_name);
	    	    String[] name=cus_name.split(" ");
	    	    if((name[0].equals(custFirstname))&&(name[1].equals(custLastname)))
	    	    {
	    	    	flag=true;
	    	    	break;
	    	    }
	    	
	        }
	    
	    	return flag;
	    	
	    }

}
