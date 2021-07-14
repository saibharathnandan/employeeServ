package com.paypal.bfs.test.employeeserv.utils;

import com.paypal.bfs.test.employeeserv.api.constants.ErrorMessages;
import com.paypal.bfs.test.employeeserv.api.exceptions.InvalidInputException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Bharath
 * @created 14/07/2021-12:56
 */
public final class Utils {

    private Utils(){

    }

    private final static String DATE_FORMAT_STRING="dd-MM-yyyy";

    public static final Date convertStringToDate(String date){
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new InvalidInputException(ErrorMessages.INVALID_DATE_PATTERN);
        }
    }

    public static final String convertDateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        dateFormat.setLenient(false);
        return dateFormat.format(date);
    }
}
