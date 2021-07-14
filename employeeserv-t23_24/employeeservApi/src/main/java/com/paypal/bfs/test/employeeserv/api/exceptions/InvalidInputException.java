package com.paypal.bfs.test.employeeserv.api.exceptions;

/**
 * @author Bharath
 * @created 14/07/2021-10:23
 */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message){
            super(message);
    }
}
