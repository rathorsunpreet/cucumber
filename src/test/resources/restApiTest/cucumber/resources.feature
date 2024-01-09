Feature: Test all operations on /api/unknown endpoint

	Background:
		Given the correct api endpoint as "/api/unknown"

	Scenario: Get a list of resources
		When endpoint is pinged
		Then response status code is 200
		And response contains data field with length of 6
	
	Scenario Outline: Get a single resource
		When <resourceId> is specified
		Then response status code is <code>
		And response body contains "<value>"
		
		Examples:
			| resourceId | code | value |
			| 2			 | 200	| fuchsia rose |
			| 23		 | 404	|	|