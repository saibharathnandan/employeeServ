# employeeserv
### DB Details
This application uses H2 in-memory DB. During the Boot-up of application tables schema will be created using the file  [schema.sql](employeeservImplementation/src/main/resources/schema.sql)
### Api's
    Get Api
    This endpoint retrieves the employee details based on id
    Below is an example:
	Url: http://localhost:8080/v1/bfs/employees/1
	Method: GET
	Response: {
			"id": 1,
			"first_name": "first-name",
			"last_name": "last-name",
			"date_of_birth": "27-04-1993",
			"address": {
				"line1": "line1",
				"line2": "line2",
				"city": "Hyderabad",
				"state": "Telangana",
				"country": "india",
				"zipcode": "500085"
			}
	}
---------------
    POST Api
    This Endpoint is used to create new Employee
	Create Employee
	Url: http://localhost:8080/v1/bfs/employees/create
	Method: POST
	Request: {
		"first_name":"first-name",
		"last_name": "last-name",
		"date_of_birth":"20-04-1993",
		"address":{
			"line1":"line1",
			"line2":"line2",
			"city":"Hyderabad",
			"state":"Telangana",
			"country":"india",
			"zipcode":"500085"
			}
		}
	Response: {
    		"id": 1,
    		"first_name": "first-name",
    		"last_name": "last-name",
    		"date_of_birth": "27-04-1993",
    		"address": {
    			"line1": "line1",
    			"line2": "line2",
    			"city": "Hyderabad",
    			"state": "Telangana",
    			"country": "india",
    			"zipcode": "500085"
    		}
    	}
### Test Case
All the test cases are included in the module `employeeservImplementation` even integration test cases.

### Idempotency logic
Employees will be considered same if first_name + last_name matches any record in the database
### Constraints

All the files are mandatory except line2 in address object, date_of_birth field should be of format dd-MM-yyyy

###Responses
incase of any validation failure or exceptions below is the format of response, with appropriate response codes
    
    Http Status Code: 400 Bad Request
    Response:{
            "error": "FirstName must not be null or empty",
            "status": "BAD_REQUEST"
            }
### Validations
For any validation failures returning `400 Bad request`.
`200` in case employee created successfully or employee object is returned successfully.
`404` in case employee requested is not found in the database
`500` in case of any internal server errors


## Application Overview
employeeserv is a spring boot rest application which would provide the CRUD operations for `Employee` resource.

There are three modules in this application
- employeeservApi - This module contains the interface.
	- `v1/schema/employee.json` defines the employee resource.
	- `jsonschema2pojo-maven-plugin` is being used to create `Employee POJO` from json file.
	- `EmployeeResource.java` is the interface for CRUD operations on `Employee` resource.
		- GET `/v1/bfs/employees/{id}` endpoint is defined to fetch the resource.
- employeeservImplementation - This module contains the implementation for the rest endpoints.
	- `EmployeeResourceImpl.java` implements the `EmployeeResource` interface.
- employeeservFunctionalTests - This module would have the functional tests.

## How to run the application
- Please have Maven version `3.3.3` & Java 8 on your system.
- Use command `mvn clean install` to build the project.
- Use command `mvn spring-boot:run` from `employeeservImplementation` folder to run the project.
- Use postman or curl to access `http://localhost:8080/v1/bfs/employees/1` GET endpoint. It will return an Employee resource.

## Assignment
We would like you to enhance the existing project and see you complete the following requirements:

- `employee.json` has only `name`, and `id` elements. Please add `date of birth` and `address` elements to the `Employee` resource. Address will have `line1`, `line2`, `city`, `state`, `country` and `zip_code` elements. `line2` is an optional element.
- Add one more operation in `EmployeeResource` to create an employee. `EmployeeResource` will have two operations, one to create, and another to retrieve the employee resource.
- Implement create and retrieve operations in `EmployeeResourceImpl.java`.
- Resource created using create endpoint should be retrieved using retrieve/get endpoint.
- Please use h2 in-memory database or any other in-memory database to persist the `Employee` resource. Dependency for h2 in-memory database is already added to the parent pom.
- Please make sure the validations are done for the requests.
- Response codes are as per rest guidelines.
- Error handling in case of failures.

## Assignment submission
Thank you very much for your time to take this test. Please upload this complete solution in Github and send us the link to `bfs-sor-interview@paypal.com`.
