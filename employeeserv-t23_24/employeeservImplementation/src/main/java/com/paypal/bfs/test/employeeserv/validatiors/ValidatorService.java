package com.paypal.bfs.test.employeeserv.validatiors;

import com.paypal.bfs.test.employeeserv.api.constants.ErrorMessages;
import com.paypal.bfs.test.employeeserv.api.exceptions.InvalidInputException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.jpa.entity.EmployeesEntity;
import com.paypal.bfs.test.employeeserv.jpa.repo.EmployeeRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Bharath
 * @created 14/07/2021-10:29
 */
@Service
public class ValidatorService {

    @Autowired
    EmployeeRepository empRepo;

    private final String dateFormat = "dd-MM-yyyy";
    private final Consumer<String> VALIDATE_DATE_FORMAT = date->{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);
        try {
             simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new InvalidInputException(ErrorMessages.INVALID_DATE_PATTERN);
        }

    };

    public void validate(Employee employee){
        if (employee==null)
            throw new InvalidInputException(ErrorMessages.EMPLOYEE_OBJECT_EMPTY_OR_NULL);
        if(employee.getAddress()==null){
            throw new InvalidInputException(ErrorMessages.ADDRESS_OBJECT_EMPTY_OR_NULL);
        }
        validateFields(employee);
    }

    private void validateFields(Employee employee){
        if (StringUtils.isEmpty(employee.getFirstName()))
            throw new InvalidInputException(ErrorMessages.FIRST_NAME_EMPTY_OR_NULL);
        if (StringUtils.isEmpty(employee.getLastName()))
            throw  new InvalidInputException(ErrorMessages.LAST_NAME_EMPTY_OR_NULL);
        if (StringUtils.isEmpty(employee.getDateOfBirth()))
            throw new InvalidInputException(ErrorMessages.DATE_OF_BIRTH_EMPTY_OR_NULL);
        VALIDATE_DATE_FORMAT.accept(employee.getDateOfBirth());
        if (StringUtils.isEmpty(employee.getAddress().getLine1()))
            throw new InvalidInputException(ErrorMessages.LINE1_NAME_EMPTY_OR_NULL);
        if (StringUtils.isEmpty(employee.getAddress().getCity()))
            throw new InvalidInputException(ErrorMessages.CITY_EMPTY_OR_NULL);
        if (StringUtils.isEmpty(employee.getAddress().getState()))
            throw new InvalidInputException(ErrorMessages.STATE_EMPTY_OR_NULL);
        if (StringUtils.isEmpty(employee.getAddress().getCountry()))
            throw new InvalidInputException(ErrorMessages.COUNTRY_EMPTY_OR_NULL);
        if (StringUtils.isEmpty(employee.getAddress().getZipcode()))
            throw new InvalidInputException(ErrorMessages.ZIPCODE_EMPTY_OR_NULL);
    }

    public void checkIfEmployeeExists(String firstName,String lastName){
        Optional<EmployeesEntity> employeesEntityOptional = empRepo.findByFirstNameAndLastName(firstName, lastName);
        if (employeesEntityOptional.isPresent())
            throw  new InvalidInputException("Employee Already Exists with firstName :"+firstName+", lastName :"+lastName);
    }
}
