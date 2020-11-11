package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	public WebDriver driver;
	WebElement listitem;
	
	
	public AddCustomerPage(WebDriver driver)
	{
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	By lnkCustomers_menu=By.xpath("//body[@class='skin-blue sidebar-mini']/div[@class='wrapper']/div[@class='main-sidebar']"
			+ "/div[@class='sidebar']/ul[@class='sidebar-menu tree']/li[4]/a[1]/span[1]");
    By lnkCustomers_menuitems=By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");
    By btnAddNew=By.xpath("//a[@class='btn bg-blue']");
    By txtEmail= By.id("Email");
    By txtPassword=By.id("Password");
    By txtFirstName=By.id("FirstName");
    By txtLastname= By.id("LastName");
    By rdBtnMale= By.id("Gender_Male");
    By rdBTnFemale=By.id("Gender_Female");
    By txtDob= By.xpath("//span[@class='k-select']");
    By txtCompany= By.id("Company");
    By chkBoxTax= By.id("IsTaxExempt");
    By chkNewsLetter= By.xpath("//div[@class='checkbox']//label//input[@value='2']");
    By txtCustomerRole=By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
    
    By lstitemRegister=By.xpath("//li[@class='k-button k-state-hover']//span[contains(text(),'Registered')]");
    By lstitemAdministrator= By.xpath("//ul[@class='k-list k-reset']//li[text()='Administrators']");
    By lstitemModerator=By.xpath("//ul[@class='k-list k-reset']//following::li[text()='Forum Moderators']");
    By lstitemGuests= By.xpath("//ul[@class='k-list k-reset']//following::li[2][text()='Guests']");
    By lstitemVendor=By.xpath("//ul[@class='k-list k-reset']//following::li[text()='Vendors']");
    By drpmgrOfVendor=By.id("VendorId");
    By txtAdminComment= By.id("AdminComment");
    By saveBtn= By.xpath("//button[@name='save']");
    By confirmtxt=By.xpath("//div[@class='alert alert-success alert-dismissable']");
    
    
    //actions
    
      public String getPageTitle()
      {
    	  return driver.getTitle();
      }
    
	   public void clickOnCustomerMenu()
	   {
		   driver.findElement(lnkCustomers_menu).click();
		   
	   }
	   
	   public void clickOnCustomerMenuItems()
	   {
		   driver.findElement(lnkCustomers_menuitems).click();
	   }
	   
	   public void clickOnAddNewBtn()
	   {
		   driver.findElement(btnAddNew).click();
	   }
	   
	   public void setEmail(String email)
	   {
		   driver.findElement(txtEmail).sendKeys(email);
	   }
	   
	   public void setPassword(String pwd)
	   {
		   driver.findElement(txtPassword).sendKeys(pwd);
	   }
	   
	   public void setFirstName(String fname)
	   {
		   driver.findElement(txtLastname).sendKeys(fname);
	   }
	   
	   public void setLastName(String lname)
	   {
		   driver.findElement(txtLastname).sendKeys(lname);
	   }
	   
	   public void setGender(String gender)
	   {
		   if(gender.equals("Male"))
		   {
			   driver.findElement(rdBtnMale).click();
		   }
		   else if(gender.equals("Female"))
		   {
			   driver.findElement(rdBTnFemale).click();
		   }
		   
		   else
		   {
			   driver.findElement(rdBtnMale).click();    //default
		   }
		   
		 }
	     public void setDateOfBirth(String monthyear, String date) throws InterruptedException
	     {
	    	 driver.findElement(txtDob).click();
	    	 Thread.sleep(3000);
	    	 while(true)
	 		{
	 			String act_month=driver.findElement(By.xpath("//a[@class='k-link k-nav-fast']")).getText();
	 			System.out.println(act_month);
	 			if(act_month.equals(monthyear))
	 			{
	 		          break;
	 			}
	 			
	 		   driver.findElement(By.xpath("//body/div[4]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
	 		   Thread.sleep(3000);
	 			
	 	 }
	 		
	 		List<WebElement>columns=driver.findElements(By.xpath("//table//tbody//tr//td"));
	 		System.out.println(columns.size());
	 		
	 		
	 		for(WebElement col:columns)
	 		{    
	 			String ele=col.getText();
	 			if(ele.equals(date))
	 			{  
	 				col.click();
	 			}
	 		}
	 		
	     }
	   
	   
	     public void setCompany(String company)
	     {
	    	 driver.findElement(txtCompany).sendKeys(company);
	     }
	     
	     public void setOnTaxExempt()
	     {
	    	 driver.findElement(chkBoxTax).click();
	     }
	     
	     public void setOnNewsLetter()
	     {
	    	 driver.findElement(chkNewsLetter).click();
	     }
	     public void setCustomerRole(String role) throws InterruptedException
	     {  
	    	//ldriver.findElement(By.xpath("//span[@class='k-icon k-delete']")).click();
	        driver.findElement(txtCustomerRole).click();
	        Thread.sleep(2000);
	   
	        if(role.equals("Registered"))
	        {
	    	  listitem=driver.findElement(lstitemRegister);
	        }
	        else if(role.equals("Administrators"))
	        {
	        	listitem=driver.findElement(lstitemAdministrator);
	        }
	        else if(role.equals("Guests"))
	        {    
	        	driver.findElement(By.xpath("//span[@class='k-icon k-delete']")).click();
	        	//ldriver.findElement(By.xpath("//li[@class='k-button']//span[contains(text(),'Registered')]")).click();  //delete register
	            listitem= driver.findElement(lstitemGuests);
	        }
	        else if(role.equals("Vendors"))
	        {
	        	listitem=driver.findElement(lstitemVendor);
	        }
	        else if(role.equals("Forum Moderators"))
	        {
	        	listitem=driver.findElement(lstitemModerator);
	        }
	        else
	        {
	        	listitem=driver.findElement(lstitemGuests);
	        }
	        
	       // listitem.click();
	        
	        JavascriptExecutor js=(JavascriptExecutor)driver;
	        js.executeScript("arguments[0].click();", listitem);
	        
	     }
	     
	     public void setManagerOfVendor(String value)
	     {
	    	 Select drp= new Select(driver.findElement(drpmgrOfVendor));
	    	 drp.selectByVisibleText(value);
	     }
	     
	     public void setAdminComment(String content)
	     {
	    	 driver.findElement(txtAdminComment).sendKeys(content);
	     }
          
	     public void clickOnSaveBtn()
	     {
	    	 driver.findElement(saveBtn).click();
	     }
	     
	     public boolean verifyingConfirmationMsg()
	     {
	    	String msg= driver.findElement(confirmtxt).getText();
	    	if(msg.contains("The new customer has been added successfully"))
	    	{
	    		return true;
	    	}
	    	
	    	else
	    	{
	    		return false;
	    	}
	     }
	
}



