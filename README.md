# Sauce Demo Tests 
## Objective
To create an automated test that achieves the following flow:
1. Navigate to https://www.saucedemo.com/
2. Login to the website using one of the accepted usernames & password listed in the website
3. Create data driven tests to iterate through all the usernames provided in the website
4. Add 2 products less than $10 to the cart
5. Verify that the cart has 2 items
6. Proceed through the checkout process

## Full regressions

To execute the full test suite, simply run:

    $ mvn test
    
And each scenario will execute.

## Smoke tests

Smoke tests can be run by specifying the `@smokeTests` tag:

    $ mvn clean test "-Dcucumber.options=--tags '@smokeTests'"

Tags can be combined to further filter the set of tests run.  For example, to run only the login smoke tests, run:

    $ mvn clean test "-Dcucumber.options=--tags '@login and @smokeTests'"

Similarly, to run the order smoke tags, run:

    $ mvn clean test "-Dcucumber.options=--tags '@order and @smokeTests'"
   
## Scenario breakdown

|Feature| Scenario                                                | Smoke Test |Regression Test|
|---|---------------------------------------------------------|------------|---|
|login| Verify valid users can sign in                          | Yes        |Yes|
|login| Verify locked out user gets locked out message          | No         |Yes|
|login| Verify invalid users cannot sign in                     | No         |Yes|
|orders| Place a single item in the shopping cart                | Yes        |Yes|
|orders| Place a 2 items in the shopping cart                    | Yes        |Yes|
|orders| Place order with 2 products less than $10 to the cart   | Yes        |Yes|
|orders| Tap on checkout button and verify                       | Yes        |Yes|
|orders| Place an order with multiple items in the shopping cart | No         |Yes|
|orders|Validate Order Totals|No|Yes|

## Tags

Tags are defined to allow execution of specific Features and Scenarios.

__Example__: Execute smoke tests for tests related to `orders`:

    $ mvn test "-Dcucumber.options=--tags '@orders and @smokeTests'" -f pom.xml   

__Example__: Execute regression tests for tests related to `login`:

    $ mvn test "-Dcucumber.options=--tags '@login and @regressionTests'" -f pom.xml   
