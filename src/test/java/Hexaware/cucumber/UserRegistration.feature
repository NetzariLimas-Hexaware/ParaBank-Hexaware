@tag
Feature: User Registration in a Bank Page.
  I want to use this template for my feature file
  
  Background:
  	Given: I landed to home page

  @Registration
  Scenario Outline: Registration Page Process
    Given User navigates to registration page
  	When User fills the registration form from given sheetname <SheetName> and rownumber <rowNumber>
    And Check for "Your account was created successfully. You are now logged in." message
    Then Get logged and recieve message "Welcome" with customer name

    Examples: 
      | SheetName  		| RowNumber  |
      | registration  | 0					 |
      | registration  | 1					 |
