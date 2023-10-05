Feature: Validating Place API's 
//test
@AddPlace
Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI 
	Given Add Place Payload "<Name>" "<Language>" "<Address>" 
	When User calls "AddPlaceAPI" with "POST" Http request 
	Then then API call is success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	And verify place_Id created maps to "<Name>" using "getPlaceAPI" 
	
	Examples: 
		| Name | Language | Address |
		|Testname| Test L| Test Address|
	#	|Testname 1| Test L 1| Test Address 1|
		
@DeletePlace		
Scenario: Verify if Delete Placefunctionality is worrking 
	Given DeletePlace Payload 
	When User calls "deletePlaceAPI" with "POST" Http request 
	Then then API call is success with status code 200 
	And "status" in response body is "OK" 
