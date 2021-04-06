package com.webetapi.webapp;

import com.webetapi.webapp.model.Employee;
import com.webetapi.webapp.repository.EmployeeProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeProxyTest {


    private EmployeeProxy employeeProxyUnderTest;

    @Test
    @DisplayName("Test le REST API")
    //le bug ici.....mystere
    public void testGetEmployeesReturnsAllEmployeeItem() {
        //given

        //when
        List<Employee> e =employeeProxyUnderTest.getEmployees();

        //then
        assertThat(e.get(1).getFirstName()).isNotEmpty();

    }

}
