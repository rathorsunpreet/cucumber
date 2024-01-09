Feature: Test all operations on /api/users endpoint

	Background:
		Given the correct api endpoint as "/api/users"

	Scenario: Get a list of users
		When page number 2 is specified
		Then response status code is 200
		And response contains data field with length of 6
	
	Scenario Outline: Get single user info
		When <userId> is specified
		Then response status is <res>
		And response body contains "<val>"
		
		Examples:
			| userId | res | val |
			| 2		 | 200 | janet.weaver@reqres.in |
			| 23	 | 404 |  |

	Scenario: Create a new user
		When the request is sent to the API
		Then response status code is 201
		Then new user is created with name as "morpheus" and job as "leader"
	
	Scenario Outline: Update existing user
		When <pagenum>, "<name>", "<job>" are sent to the API using "<verb>"
		Then response status is 200
		And response body contains "<name>" and "<job>"
		
		Examples:
			| pagenum | name | job | verb |
			| 2		  | morpheus | zion resident | PUT |
			| 2		  | morpheus | zion resident | PATCH |
			
	Scenario: Delete an existing user
		When id of 2 is sent to the API
		Then response status code is 204
		
	Scenario: Get a list of users delayed
		When page number 3 is specified with delay parameter
		Then response status code is 200
		And response contains data field with length of 6