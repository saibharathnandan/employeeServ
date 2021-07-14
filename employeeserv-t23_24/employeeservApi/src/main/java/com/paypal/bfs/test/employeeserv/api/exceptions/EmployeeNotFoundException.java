package com.paypal.bfs.test.employeeserv.api.exceptions;

/**
 * @author Bharath
 * @created 14/07/2021-17:04
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
