package com.paypal.bfs.test.employeeserv.impl.test;

import com.paypal.bfs.test.employeeserv.api.constants.ErrorMessages;
import com.paypal.bfs.test.employeeserv.api.exceptions.InvalidInputException;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.validatiors.ValidatorService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Bharath
 * @created 14/07/2021-18:51
 */
@RunWith(SpringRunner.class)
public class ValidatorServiceTest {

    @InjectMocks
    ValidatorService validatorService;

    @Rule
    public ExpectedException expectedExpection = ExpectedException.none();

    @Test
    public void testValidateWithNull(){
        expectedExpection.expect(InvalidInputException.class);
        expectedExpection.expectMessage(ErrorMessages.EMPLOYEE_OBJECT_EMPTY_OR_NULL);
        validatorService.validate(null);
    }

    @Test
    public void testValidateWithAddressNull(){
        expectedExpection.expect(InvalidInputException.class);
        expectedExpection.expectMessage(ErrorMessages.ADDRESS_OBJECT_EMPTY_OR_NULL);
        validatorService.validate(new Employee());
    }

    @Test
    public void testValidateWithPositive() {
        Address address = new Address();
        address.setLine1("Line1");
        address.setCity("Hyderabad");
        address.setState("Telangana");
        address.setCountry("India");
        address.setZipcode("500085");
        Employee employee = new Employee();
        employee.setFirstName("first-name");
        employee.setLastName("last-name");
        employee.setDateOfBirth("20-04-1994");
        employee.setAddress(address);
        validatorService.validate(employee);
    }
}
