@orders
  Feature: Validate Web Ordering

    ##############################################################################################
    @smokeTests
    @regressionTests
    Scenario Outline: Place a single item in the shopping cart
      Given The user is on the Home Page
      And The user provides the username as "<username>" and password as "<password>"
      And The user clicks the 'Login' button
      And The user chooses a "<item>" by clicking 'Add To Cart'
      And The user clicks on the shopping cart
      Then There should be "1" items in the shopping cart
    Examples:
      |username|password|item|
      |standard_user  |secret_sauce |Sauce Labs Backpack|


    ###############################################################################################
    @smokeTests
    Scenario Outline: Place order with 2 products less than $10 to the cart

      Given The user is on the Home Page
      And The user provides the username as "<username>" and password as "<password>"
      And The user clicks the 'Login' button
      And The user selects
        |Sauce Labs Onesie      |
        |Sauce Labs Bike Light  |
      And The user clicks on the shopping cart
      Then There should be "2" items in the shopping cart
      And The user clicks on the shopping cart
      And The user clicks 'Checkout'
      And The user provides the first name as "Venu" and last name as "Chenchu" and zip code as "12345"
      And The user clicks 'Continue'
      Then The item total should be "Item total: $17.98"
      And The tax should be "Tax: $1.44"
      And The total should be "Total: $19.42"
      Examples:
        |username|password|
        |standard_user  |secret_sauce |

    ###############################################################################################
    @regressionTests
    Scenario Outline: Validate Order Totals
      Given The user is on the Home Page
      And The user provides the username as "<username>" and password as "<password>"
      And The user clicks the 'Login' button
      And The user selects
        |Sauce Labs Backpack    |
        |Sauce Labs Bolt T-Shirt|
        |Sauce Labs Onesie      |
        |Test.allTheThings() T-Shirt (Red)|
        |Sauce Labs Fleece Jacket         |
        |Sauce Labs Bike Light            |
      And The user clicks on the shopping cart
      And The user clicks 'Checkout'
      And The user provides the first name as "Test" and last name as "User" and zip code as "12345"
      And The user clicks 'Continue'
      Then The item total should be "Item total: $129.94"
      And The tax should be "Tax: $10.40"
      And The total should be "Total: $140.34"
      Examples:
        |username|password|
        |standard_user  |secret_sauce |