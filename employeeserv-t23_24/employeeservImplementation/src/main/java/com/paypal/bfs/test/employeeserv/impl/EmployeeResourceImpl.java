package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.exceptions.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.api.exceptions.InvalidInputException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.converters.DaoToModelConverter;
import com.paypal.bfs.test.employeeserv.converters.ModelToDaoConverter;
import com.paypal.bfs.test.employeeserv.jpa.entity.EmployeesEntity;
import com.paypal.bfs.test.employeeserv.jpa.repo.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.validatiors.ValidatorService;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    private final ValidatorService validatorService;
    private final EmployeeRepository empRepo;
    public EmployeeResourceImpl(ValidatorService validatorService, EmployeeRepository employeeRepository){
        this.validatorService = validatorService;
        this.empRepo = employeeRepository;
    }

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {
        if (StringUtils.isEmpty(id) || id.equals("null")){
            throw new InvalidInputException("Invalid Id provided");
        }
        Optional<EmployeesEntity> employeesEntityOptional = empRepo.findById(Integer.valueOf(id));
        if (employeesEntityOptional.isPresent()){
            Employee employee = DaoToModelConverter.convertDaoToEmployee(employeesEntityOptional.get());
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }else{
            throw new EmployeeNotFoundException("No Employee Found with Id :"+id);
        }
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        validatorService.validate(employee);
        validatorService.checkIfEmployeeExists(employee.getFirstName(),employee.getLastName());
        EmployeesEntity employeesEntity = ModelToDaoConverter.convertModelToEmployeeEntity(employee);
        employeesEntity = empRepo.save(employeesEntity);
        Employee employeeModel = DaoToModelConverter.convertDaoToEmployee(employeesEntity);
        return new ResponseEntity<>(employeeModel,HttpStatus.OK);
    }

    @ExceptionHandler({EmployeeNotFoundException.class})
    public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        Map<Object, Object> headers = new HashMap<>();
        headers.put("error", exception.getMessage());
        headers.put("status", NOT_FOUND);
        return new ResponseEntity<>(headers, NOT_FOUND);
    }

    @ExceptionHandler({InvalidInputException.class})
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException exception) {
        Map<Object, Object> headers = new HashMap<>();
        headers.put("error", exception.getMessage());
        headers.put("status", BAD_REQUEST);
        return new ResponseEntity<>(headers, BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception exception) {
        Map<Object, Object> headers = new HashMap<>();
        headers.put("error", exception.getMessage());
        headers.put("status", INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(headers, INTERNAL_SERVER_ERROR);
    }
}
