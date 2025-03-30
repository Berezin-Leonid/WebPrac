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

import web.models.Division;
import web.DAO.DivisionDAO;
import java.time.LocalDate;

class DivisionDAOTest {

    @Mock
    private DivisionDAO divisionDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSingleDivisionByName() {
        String name = "HR";
        Division division = new Division(3L, "HR", null);

        when(divisionDAO.getSingleDivisionByName(name)).thenReturn(division);

        Division actualDivision = divisionDAO.getSingleDivisionByName(name);
        assertNotNull(actualDivision);
        assertEquals(name, actualDivision.getName());
    }


    @Test
    void testGetAllDivisionByParentId() {
        Long parentId = 1L;
        Division parentDivision = new Division(parentId, "Head Office", null);
        Division childDivision1 = new Division(4L, "IT", parentDivision);
        Division childDivision2 = new Division(5L, "Support", parentDivision);
        Division unrelatedDivision = new Division(6L, "Logistics", new Division(2L, "Another Office", null));

        List<Division> expectedDivisions = Arrays.asList(childDivision1, childDivision2);

        when(divisionDAO.getAllDivisionByParentId(parentId)).thenReturn(expectedDivisions);

        List<Division> actualDivisions = divisionDAO.getAllDivisionByParentId(parentId);
        assertNotNull(actualDivisions);
        assertFalse(actualDivisions.isEmpty());
        assertEquals(2, actualDivisions.size());
        assertTrue(actualDivisions.stream().allMatch(d -> d.getParent().equals(parentDivision)));
    }

    @Test
    void testDeleteDivision() {
        Division division = new Division(5L, "Marketing", null);
        doNothing().when(divisionDAO).delete(division);

        divisionDAO.delete(division);

        verify(divisionDAO, times(1)).delete(division);
    }

    @Test
    void testUpdateDivision() {
        Division division = new Division(6L, "Sales", null);
        doNothing().when(divisionDAO).update(division);

        divisionDAO.update(division);

        verify(divisionDAO, times(1)).update(division);
    }

    @Test
    void testSaveDivision() {
        Division division = new Division(null, "Logistics", null);
        doNothing().when(divisionDAO).save(division);

        divisionDAO.save(division);

        verify(divisionDAO, times(1)).save(division);
    }


}