Feature: Customers


 Background: Below are the common steps for each scenario
    Given  User launch Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login?"
    And User enters Emails as "admin@yourstore.com" and Password as "admin"
    And Click on login
    Then User can view Dashboard
    
  

  
@Sanity
Scenario: Add a new customer

 When User clicks on customers Menu
 And User clicks on customer Menu Items
 And click on Add new button
 Then User can view Add new customer page
 When  User enter customer info
 And Click on save button
 Then User can view confirmation message "The new customer has been added successfully."
 And Close browser
 
 
 @Sanity
 Scenario: Search customer by EmailID
 
 
 When User clicks on customers Menu
 Then User clicks on customer Menu Items
 And Enter customer email
 Then User clicks on search button
 And User should find the email in search table
 And Close browser
 
 @Regression
 Scenario: Search customer by Name
 
 
 When User clicks on customers Menu
 Then User clicks on customer Menu Items
 And Enter customer FirstName
 And Enter customer LastName
 Then User clicks on search button
 And User should find the name in search table
 And Close browser
 