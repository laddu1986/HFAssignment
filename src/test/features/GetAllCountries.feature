Feature: Get all countries
  Scenario: User calls api to get all the countries
	Given a api exists to get countries
	When a user retrieves all
	Then the status code is 200
  And response includes the following in any order
	| RestResponse.result.alpha2_code 				| US	|
    | RestResponse.result.alpha2_code 				| DE	| 
    | RestResponse.result.alpha2_code 				| GB	|