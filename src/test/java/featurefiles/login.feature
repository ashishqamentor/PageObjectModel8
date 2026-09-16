Feature: Login sause Labs.

Scenario Outline: Login validation
  Given I am on the sause login page
  When I enter <username> and <password>
  And click on login button
  Then I should see dashboard page with url "https://www.saucedemo.com/inventory.html"

Examples:
  | username 				| password 		 | 
  | standard_user  			|	secret_sauce |
  | standard_user			|	secret_sauce |
  | locked_out_user			|	secret_sauce |
  | problem_user			|	secret_sauce |
  | performance_glitch_user	|	secret_sauce |
  | error_user				|	secret_sauce |
  | visual_user				|	secret_sauce |

  
 