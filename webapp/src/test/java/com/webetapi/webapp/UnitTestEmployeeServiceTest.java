package com.webetapi.webapp;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.webetapi.webapp.model.Employee;
import com.webetapi.webapp.repository.EmployeeProxy;
import com.webetapi.webapp.service.EmployeeService;

import static org.assertj.core.api.Assertions.*;



@RunWith(MockitoJUnitRunner.class)
public class UnitTestEmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeProxy EmployeeProxyMock;


    @Test
    public void testGetEmployeesReturnsAllEmployeeItemFromProxy() {

        //given
        Employee item1 = new Employee(1, "Star Wars", "7.7", "M", "dd");
        Employee item2 = new Employee(2, "Star Trek", "7.7", "L", "dd");
        List<Employee> mockItems = Arrays.asList(item1, item2);


        //when
        when(EmployeeProxyMock.getEmployees()).thenReturn(mockItems);

        //Act
        List<Employee> result = employeeService.getEmployees();

        //Assert
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getPassword()).isEqualTo("dd");
        assertThat(result.get(1).getFirstName()).isEqualTo("Star Trek");
    }

    @Test
    public void testGetEmployeeReturnsEmployeeItemFromProxy() {

        //Arrange
        Employee item1 = new Employee(1, "Star Wars", "7.7", "M", "dd");
        when(EmployeeProxyMock.getEmployee(1)).thenReturn(item1);
        //Act
        Employee result = employeeService.getEmployee(1);
        //Assert
        assertThat(result.getPassword()).isEqualTo("dd");
    }
}
