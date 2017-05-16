Feature: Add a country
  Scenario: User calls api to add a country
	Given a api exists to add country
	| name 				| Test Country	|
    | alpha2_code 				| TC	| 
    | alpha3_code 				| TCY	|
	When a user post the request
	Then the status code is 200
  