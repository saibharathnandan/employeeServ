package com.paypal.bfs.test.employeeserv.converters;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.jpa.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.jpa.entity.EmployeesEntity;
import com.paypal.bfs.test.employeeserv.utils.Utils;

/**
 * @author Bharath
 * @created 14/07/2021-12:52
 */
public final class ModelToDaoConverter {
    private ModelToDaoConverter(){

    }

    public static EmployeesEntity convertModelToEmployeeEntity(Employee employee){
        EmployeesEntity employeesEntity = new EmployeesEntity();
        employeesEntity.setFirstName(employee.getFirstName());
        employeesEntity.setLastName(employee.getLastName());
        employeesEntity.setDateOfBirth(Utils.convertStringToDate(employee.getDateOfBirth()));
        employeesEntity.setAddressEntity(convertModelToAddressEntity(employee.getAddress()));
        return employeesEntity;
    }

    public static AddressEntity convertModelToAddressEntity(Address address){
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(address.getCity());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setState(address.getState());
        addressEntity.setZipCode(address.getZipcode());
        addressEntity.setLine1(address.getLine1());
        addressEntity.setLine2(address.getLine2());
        addressEntity.setZipCode(addressEntity.getZipCode());
        return addressEntity;
    }
}
