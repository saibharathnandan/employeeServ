package com.paypal.bfs.test.employeeserv.jpa.repo;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.jpa.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bharath
 * @created 14/07/2021-10:33
 */
@Repository
public interface EmployeeRepository extends CrudRepository<EmployeesEntity,Integer> {

    Optional<EmployeesEntity> findById(Integer id);

    Optional<EmployeesEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
