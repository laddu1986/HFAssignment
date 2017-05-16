Feature: Get specific country
  Scenario Outline: User calls api to get specific country
	Given there is api to get countries
	When a user try to retireve specific country with "<alpha2code>"
	Then the status code is 200
    And response should includes "<correct alpha code>" and "<country name>"
    
    Examples:
    |alpha2code | correct alpha code | country name |
	| US        | US                 | United States of America |
	| DE        | DE                 | Germany |
	| GB        | GB                 | United Kingdom of Great Britain and Northern Ireland |
	
	
 Scenario Outline: User calls api to get inexistent country
	Given there is api to get countries
	When a user try to retireve specific country with "<alpha2code>"
	Then the status code is 200
    And response for in existent country should includes "<error message>"
    
    Examples:
    |alpha2code | error message |
    | AA        | No matching country found for requested code [AA]. |
    | ZZ        | No matching country found for requested code [ZZ]. |
    
  Scenario Outline: User calls api to get all countries
	Given there is api to get countries
	When a user try to retireve all countries
	Then the status code is 200
    And response should include all these "<countries>" and "<country name>"
    
    Examples:
    | countries | country name |
    | US                 | United States of America |
    | DE                 | Germany |
    | GB                 | United Kingdom of Great Britain and Northern Ireland |