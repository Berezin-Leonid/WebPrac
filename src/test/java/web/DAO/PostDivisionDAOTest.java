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

import web.models.Post;
import web.models.Division;
import web.models.PostDivision;
import web.DAO.PostDivisionDAO;
import java.time.LocalDate;

class PostDivisionDAOTest {

    @Mock
    private PostDivisionDAO postDivisionDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllByPostId() {
        Long postId = 1L;

        Post post1 = new Post(1L, "Manager", "Manage department");
        Post post2 = new Post(2L, "Developer", "Develop software");

        Division division1 = new Division(1L, "HR", null);
        Division division2 = new Division(2L, "IT", null);

        PostDivision postDivision1 = new PostDivision(1L, post1, division1);
        PostDivision postDivision2 = new PostDivision(2L, post1, division2);
        PostDivision postDivision3 = new PostDivision(3L, post2, division2);

        List<PostDivision> expectedPostDivisions = Arrays.asList(postDivision1, postDivision2);

        when(postDivisionDAO.getAllByPostId(postId)).thenReturn(expectedPostDivisions);

        List<PostDivision> actualPostDivisions = postDivisionDAO.getAllByPostId(postId);
        assertNotNull(actualPostDivisions);
        assertEquals(2, actualPostDivisions.size());
        assertEquals(post1, actualPostDivisions.get(0).getPost());
        assertEquals(post1, actualPostDivisions.get(1).getPost());
    }

    @Test
    void testGetAllByDivisionId() {
        Long divisionId = 2L;

        Post post1 = new Post(1L, "Manager", "Manage department");
        Post post2 = new Post(2L, "Developer", "Develop software");

        Division division1 = new Division(1L, "HR", null);
        Division division2 = new Division(2L, "IT", null);

        PostDivision postDivision1 = new PostDivision(1L, post1, division2);
        PostDivision postDivision2 = new PostDivision(2L, post2, division2);

        List<PostDivision> expectedPostDivisions = Arrays.asList(postDivision1, postDivision2);

        when(postDivisionDAO.getAllByDivisionId(divisionId)).thenReturn(expectedPostDivisions);

        List<PostDivision> actualPostDivisions = postDivisionDAO.getAllByDivisionId(divisionId);
        assertNotNull(actualPostDivisions);
        assertEquals(2, actualPostDivisions.size());
        assertEquals(division2, actualPostDivisions.get(0).getDivision());
        assertEquals(division2, actualPostDivisions.get(1).getDivision());
    }
}