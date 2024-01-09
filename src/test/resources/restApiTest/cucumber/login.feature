Feature: Test all operations on /api/login endpoint

	Background:
		Given the correct api endpoint as "/api/login"
		
	Scenario Outline: Login as user
		When the payload consist of "<email>" and "<psswd>"
		And endpoint is pinged
		Then response status is <status>
		And response body contains "<description>" and "<val>"
		
		Examples:
			| email | psswd | status | description | val |
			| eve.holt@reqres.in | cityslicka | 200 | token | QpwL5tke4Pnpja7X4 |
			| peter@klaven |  | 400 | error | Missing password |