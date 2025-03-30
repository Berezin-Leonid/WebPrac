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

import web.models.*;
import web.DAO.*;

import java.time.LocalDate;

class EmployeePostDivisionDAOTest {

    @Mock
    private EmployeePostDivisionDAO employeePostDivisionDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByPostDivisionId() {
        Long postDivisionId = 1L;

        Post post1 = new Post(1L, "Manager", "Manage department");
        Division division1 = new Division(1L, "HR", null);
        PostDivision postDivision1 = new PostDivision(1L, post1, division1);

        Employee employee1 = new Employee(1L, "John Doe", "123 Main St", "Bachelor", LocalDate.of(2020, 1, 1));
        Employee employee2 = new Employee(2L, "Jane Smith", "456 Elm St", "Master", LocalDate.of(2019, 1, 1));

        EmployeePostDivision employeePostDivision1 = new EmployeePostDivision(1L, postDivision1, employee1, LocalDate.of(2020, 1, 1), null);
        EmployeePostDivision employeePostDivision2 = new EmployeePostDivision(2L, postDivision1, employee2, LocalDate.of(2019, 1, 1), null);

        List<EmployeePostDivision> expectedEmployeePostDivisions = Arrays.asList(employeePostDivision1, employeePostDivision2);

        when(employeePostDivisionDAO.getByPostDivisionId(postDivisionId)).thenReturn(expectedEmployeePostDivisions);

        List<EmployeePostDivision> actualEmployeePostDivisions = employeePostDivisionDAO.getByPostDivisionId(postDivisionId);
        assertNotNull(actualEmployeePostDivisions);
        assertEquals(2, actualEmployeePostDivisions.size());
        assertEquals(postDivision1, actualEmployeePostDivisions.get(0).getPostDivision());
        assertEquals(postDivision1, actualEmployeePostDivisions.get(1).getPostDivision());
    }


    @Test
    void testGetByEmployeeId() {
        Long employeeId = 1L;

        Post post1 = new Post(1L, "Manager", "Manage department");
        Division division1 = new Division(1L, "HR", null);
        PostDivision postDivision1 = new PostDivision(1L, post1, division1);

        Employee employee1 = new Employee(1L, "John Doe", "123 Main St", "Bachelor", LocalDate.of(2020, 1, 1));
        Employee employee2 = new Employee(2L, "Jane Smith", "456 Elm St", "Master", LocalDate.of(2019, 1, 1));

        EmployeePostDivision employeePostDivision1 = new EmployeePostDivision(1L, postDivision1, employee1, LocalDate.of(2020, 1, 1), null);
        EmployeePostDivision employeePostDivision2 = new EmployeePostDivision(2L, postDivision1, employee2, LocalDate.of(2019, 1, 1), null);

        List<EmployeePostDivision> expectedEmployeePostDivisions = Arrays.asList(employeePostDivision1);

        when(employeePostDivisionDAO.getByEmployeeId(employeeId)).thenReturn(expectedEmployeePostDivisions);

        List<EmployeePostDivision> actualEmployeePostDivisions = employeePostDivisionDAO.getByEmployeeId(employeeId);
        assertNotNull(actualEmployeePostDivisions);
        assertEquals(1, actualEmployeePostDivisions.size());
        assertEquals(employee1, actualEmployeePostDivisions.get(0).getEmployee());
    }
/*
    @Test
    void testGetByFilter() {
        EmployeePostDivisionDAO.Filter filter = EmployeePostDivisionDAO.getFilterBuilder()
                .postdivisionid(1L)
                .employeeid(1L)
                .build();

        Post post1 = new Post(1L, "Manager", "Manage department");
        Division division1 = new Division(1L, "HR", null);
        PostDivision postDivision1 = new PostDivision(1L, post1, division1);

        Employee employee1 = new Employee(1L, "John Doe", "123 Main St", "Bachelor", LocalDate.of(2020, 1, 1));
        EmployeePostDivision employeePostDivision1 = new EmployeePostDivision(1L, postDivision1, employee1, LocalDate.of(2020, 1, 1), null);

        List<EmployeePostDivision> expectedEmployeePostDivisions = Arrays.asList(employeePostDivision1);

        when(employeePostDivisionDAO.getByFilter(filter)).thenReturn(expectedEmployeePostDivisions);

        List<EmployeePostDivision> actualEmployeePostDivisions = employeePostDivisionDAO.getByFilter(filter);
        assertNotNull(actualEmployeePostDivisions);
        assertFalse(actualEmployeePostDivisions.isEmpty());
        assertEquals(employee1, actualEmployeePostDivisions.get(0).getEmployee());
    }

 */

}