package com.paypal.bfs.test.employeeserv.converters;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.jpa.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.jpa.entity.EmployeesEntity;
import com.paypal.bfs.test.employeeserv.utils.Utils;

/**
 * @author Bharath
 * @created 14/07/2021-13:06
 */
public final class DaoToModelConverter {

    private DaoToModelConverter(){

    }

    public static Employee convertDaoToEmployee(EmployeesEntity employeesEntity){
            Employee employee = new Employee();
            employee.setId(employeesEntity.getId());
            employee.setFirstName(employeesEntity.getFirstName());
            employee.setLastName(employeesEntity.getLastName());
            employee.setDateOfBirth(Utils.convertDateToString(employeesEntity.getDateOfBirth()));
            employee.setAddress(convertDaoToAddress(employeesEntity.getAddressEntity()));
            return employee;
    }

    public static Address convertDaoToAddress(AddressEntity addressEntity){
            Address address = new Address();
            address.setLine1(addressEntity.getLine1());
            address.setLine2(addressEntity.getLine2());
            address.setCity(addressEntity.getCity());
            address.setState(addressEntity.getState());
            address.setCountry(addressEntity.getCountry());
            address.setZipcode(addressEntity.getZipCode());
            return address;
    }
}
