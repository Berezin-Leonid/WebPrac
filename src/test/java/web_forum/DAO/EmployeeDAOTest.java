package ru.msu.cmc.webprak.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import web.models.Employee;
import web.DAO.EmployeeDAO;
import java.time.LocalDate;

class EmployeeDAOTest {

    @Mock
    private EmployeeDAO employeeDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployeeByName() {
        String name = "John Doe";
        Employee employee1 = new Employee(1L, "John Doe", "123 Main St", "Bachelor", LocalDate.of(2020, 1, 1));
        Employee employee2 = new Employee(2L, "John Doe", "456 Elm St", "Master", LocalDate.of(2019, 1, 1));
        Employee employee3 = new Employee(3L, "Leonid Berezin", "Leninskiy prospect", "Master", LocalDate.of(2019, 1, 1));

        List<Employee> expectedEmployees = Arrays.asList(employee1, employee2);

        when(employeeDAO.getAllEmployeeByName(name)).thenReturn(expectedEmployees);

        List<Employee> actualEmployees = employeeDAO.getAllEmployeeByName(name);
        assertNotNull(actualEmployees);
        assertEquals(2, actualEmployees.size());
        assertEquals(name, actualEmployees.get(0).getName());
    }

    @Test
    void testGetSingleEmployeeByName() {
        String name = "Jane Doe";
        Employee employee = new Employee(3L, "Jane Doe", "789 Oak St", "PhD", LocalDate.of(2018, 1, 1));

        when(employeeDAO.getSingleEmployeeByName(name)).thenReturn(employee);

        Employee actualEmployee = employeeDAO.getSingleEmployeeByName(name);
        assertNotNull(actualEmployee);
        assertEquals(name, actualEmployee.getName());
    }

    @Test
    void testGetByFilter() {
        EmployeeDAO.Filter filter = EmployeeDAO.getFilterBuilder().name("John Doe").build();
        Employee employee = new Employee(4L, "John Doe", "321 Pine St", "Bachelor", LocalDate.of(2021, 1, 1));
        List<Employee> expectedEmployees = List.of(employee);

        when(employeeDAO.getByFilter(filter)).thenReturn(expectedEmployees);

        List<Employee> actualEmployees = employeeDAO.getByFilter(filter);
        assertNotNull(actualEmployees);
        assertFalse(actualEmployees.isEmpty());
        assertEquals("John Doe", actualEmployees.get(0).getName());
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee(5L, "Alice Smith", "999 Maple St", "MBA", LocalDate.of(2015, 6, 15));
        doNothing().when(employeeDAO).delete(employee);

        employeeDAO.delete(employee);

        verify(employeeDAO, times(1)).delete(employee);
    }

    @Test
    void testUpdateEmployee() {
        Employee employee = new Employee(6L, "Bob Brown", "777 Cedar St", "BSc", LocalDate.of(2017, 3, 10));
        doNothing().when(employeeDAO).update(employee);

        employeeDAO.update(employee);

        verify(employeeDAO, times(1)).update(employee);
    }


    @Test
    void testSaveEmployee() {
        Employee employee = new Employee(null, "Charlie White", "555 Birch St", "MSc", LocalDate.of(2022, 5, 20));
        doNothing().when(employeeDAO).save(employee);

        employeeDAO.save(employee);

        verify(employeeDAO, times(1)).save(employee);
    }

}