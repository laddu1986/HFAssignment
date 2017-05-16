Feature: Get specific country
  Scenario: User calls api to get specific country
	Given a api exists to get countries
	When a user retrieves US
	Then the status code is 200
  And response includes the following in any order
	| RestResponse.result.alpha2_code 				| US	|
	| RestResponse.result.name | United States of America |
	
	Scenario: User calls api to get specific country
	Given a api exists to get countries
	When a user retrieves DE
	Then the status code is 200
  And response includes the following in any order
	| RestResponse.result.alpha2_code 				| DE	|
	| RestResponse.result.name | Germany |
	
	Scenario: User calls api to get specific country
	Given a api exists to get countries
	When a user retrieves GB
	Then the status code is 200
  And response includes the following in any order
	| RestResponse.result.alpha2_code 				| GB	|
	| RestResponse.result.name | United Kingdom of Great Britain and Northern Ireland |
	
	Scenario: User calls api to get inexistent country
	Given a api exists to get countries
	When a user retrieves AA
	Then the status code is 200
  And response includes the following in any order
	| RestResponse.messages[1] 				| No matching country found for requested code [AA].	|
	
	Scenario: User calls api to get inexistent country
	Given a api exists to get countries
	When a user retrieves ZZ
	Then the status code is 200
  And response includes the following in any order
	| RestResponse.messages[1] 				| No matching country found for requested code [ZZ].	|
	