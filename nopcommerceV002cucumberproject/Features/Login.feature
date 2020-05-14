Feature: Login Test

@Sanity
Scenario: Successful login with valid credentials
   
   Given User launch Chrome browser
   When User opens URL "https://admin-demo.nopcommerce.com/login?"
   And User enters Emails as "admin@yourstore.com" and Password as "admin"
   And Click on login
   Then Page title should be "Dashboard / nopCommerce administration"
   When User click on logout link
   Then Page title should be "Your store. Login"
   Then Close browser

@Regression
 Scenario Outline: Login Data Driven 
 
   Given User launch Chrome browser
   When User opens URL "https://admin-demo.nopcommerce.com/login?"
   And User enters Emails as "<email>" and Password as "<password>"
   And Click on login
   Then Page title should be "Dashboard / nopCommerce administration"
   When User click on logout link
   Then Page title should be "Your store. Login"
   Then Close browser
   
   Examples:
   
   | email | password |
   | admin@yourstore.com | admin |
   | admin@yourstore.com | admin |
   
   