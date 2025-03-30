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
import web.DAO.PostDAO;
import java.time.LocalDate;


class PostDAOTest {

    @Mock
    private PostDAO postDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPostByName() {
        String name = "Manager";
        Post post1 = new Post(1L, "Manager", "Responsible for team coordination");
        Post post2 = new Post(2L, "Manager", "Handles project management");
        List<Post> expectedPosts = Arrays.asList(post1, post2);

        when(postDAO.getAllPostByName(name)).thenReturn(expectedPosts);

        List<Post> actualPosts = postDAO.getAllPostByName(name);
        assertNotNull(actualPosts);
        assertEquals(2, actualPosts.size());
        assertEquals(name, actualPosts.get(0).getName());
    }

    @Test
    void testGetSinglePostByName() {
        String name = "CEO";
        Post post = new Post(3L, "CEO", "Oversees company operations");

        when(postDAO.getSinglePostByName(name)).thenReturn(post);

        Post actualPost = postDAO.getSinglePostByName(name);
        assertNotNull(actualPost);
        assertEquals(name, actualPost.getName());
    }

    @Test
    void testGetByFilter() {
        PostDAO.Filter filter = PostDAO.getFilterBuilder().name("Engineer").build();
        Post post = new Post(4L, "Engineer", "Develops and maintains systems");
        List<Post> expectedPosts = List.of(post);

        when(postDAO.getByFilter(filter)).thenReturn(expectedPosts);

        List<Post> actualPosts = postDAO.getByFilter(filter);
        assertNotNull(actualPosts);
        assertFalse(actualPosts.isEmpty());
        assertEquals("Engineer", actualPosts.get(0).getName());
    }

    @Test
    void testDeletePost() {
        Post post = new Post(5L, "Analyst", "Analyzes business data");
        doNothing().when(postDAO).delete(post);

        postDAO.delete(post);

        verify(postDAO, times(1)).delete(post);
    }

    @Test
    void testUpdatePost() {
        Post post = new Post(6L, "Consultant", "Provides expert advice");
        doNothing().when(postDAO).update(post);

        postDAO.update(post);

        verify(postDAO, times(1)).update(post);
    }

    @Test
    void testSavePost() {
        Post post = new Post(null, "Intern", "Assists in various tasks");
        doNothing().when(postDAO).save(post);

        postDAO.save(post);

        verify(postDAO, times(1)).save(post);
    }

}