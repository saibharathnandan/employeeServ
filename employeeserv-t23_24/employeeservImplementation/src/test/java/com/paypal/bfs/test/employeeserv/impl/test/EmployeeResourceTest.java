package com.paypal.bfs.test.employeeserv.impl.test;

import com.paypal.bfs.test.employeeserv.EmployeeservApplication;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Bharath
 * @created 14/07/2021-14:20
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EmployeeservApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeResourceTest {

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();

    Employee employee ;

    @Before
    public void init(){
        Address address = new Address();
        address.setLine1("Line1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipcode("560037");
        employee = new Employee();
        employee.setFirstName("first-name");
        employee.setLastName("last-name");
        employee.setDateOfBirth("20-04-1994");
        employee.setAddress(address);
    }

    @Test
    public void testCreateEmployee(){

        HttpEntity<Employee> httpEntity = new HttpEntity<>(employee);
        ResponseEntity<Employee> responseEntity = restTemplate.exchange(createUrl("/v1/bfs/employees/create"), HttpMethod.POST, httpEntity, Employee.class);
        Employee employee = responseEntity.getBody();
        Assert.assertNotNull(employee);
        Assert.assertNotNull(employee.getId());
        Assert.assertEquals("first-name",employee.getFirstName());
    }


    @Test
    public void testFetchValidEmployee(){
        HttpEntity<Employee> httpEntity = new HttpEntity<>(employee);
        ResponseEntity<Employee> createResponse = restTemplate.exchange(createUrl("/v1/bfs/employees/create"), HttpMethod.POST, httpEntity, Employee.class);
        Employee employee = createResponse.getBody();
        ResponseEntity<Employee> fetchEntity = restTemplate.exchange(createUrl("/v1/bfs/employees/" + createResponse.getBody().getId()), HttpMethod.GET, httpEntity, Employee.class);
        Employee fetchEntityBody = fetchEntity.getBody();
        Assert.assertNotNull(fetchEntityBody);
        Assert.assertNotNull(fetchEntityBody.getId());
        Assert.assertEquals("first-name",fetchEntityBody.getFirstName());
    }


    @Test
    public void testFetchInValidEmployee(){
        HttpEntity<Employee> httpEntity = new HttpEntity<>(employee);

        ResponseEntity<Employee> fetchEntity = restTemplate.exchange(createUrl("/v1/bfs/employees/1"), HttpMethod.GET, httpEntity, Employee.class);
        Assert.assertEquals(404,fetchEntity.getStatusCodeValue());
    }

    private String createUrl(String path){
        return "http://localhost:"+port+path;
    }
}
