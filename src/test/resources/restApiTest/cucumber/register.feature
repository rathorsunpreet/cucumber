Feature: Test all operations on /api/register endpoint

	Background:
		Given the correct api endpoint as "/api/register"
		
	Scenario Outline: Register a new user
		When the payload consist of "<email>" and "<psswd>"
		And endpoint is pinged
		Then response status is <status>
		And response body contains "<description>" and "<val>"
		
		Examples:
			| email | psswd | status | description | val |
			| eve.holt@reqres.in | pistol | 200 | id | 4 |
			| sydney@fife |  | 400 | error | Missing password |