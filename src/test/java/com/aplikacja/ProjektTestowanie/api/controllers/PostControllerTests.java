package com.aplikacja.ProjektTestowanie.api.controllers;

import com.aplikacja.ProjektTestowanie.Controllers.PostController;
import com.aplikacja.ProjektTestowanie.Controllers.UserController;
import com.aplikacja.ProjektTestowanie.Entities.Post;
import com.aplikacja.ProjektTestowanie.Services.PostService;
import com.aplikacja.ProjektTestowanie.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PostController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PostControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    private static List<Post> posts;

    private static Post post;

    @BeforeAll
    public static void init(){

        posts = new ArrayList<>();
        posts.add(new Post(1, 1, "testPost1", "TestPostBody1"));
        posts.add(new Post(1, 2, "testPost2", "TestPostBody2"));
        posts.add(new Post(1, 3, "testPost3", "TestPostBody3"));
        posts.add(new Post(2, 4, "testPost4", "TestPostBody4"));
        posts.add(new Post(3, 5, "testPost5", "Te"));
        posts.add(new Post(4, 6, "singlePost", "TestPostBody1LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG"));
        posts.add(new Post(5, 7, "testowyInnyTytul", "testowyInnyBody"));
        posts.add(new Post(6, 8, "testPost7", "TestPostBody5"));
        posts.add(new Post(7, 9, "testPost8", "TestPostBody6"));

        post = posts.get(5);
    }

    @Test
    public void getPostsTest() throws Exception {

        when(postService.getPosts(anyInt())).thenReturn(posts);

        mockMvc.perform(get("/posts").param("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(posts)));
    }

    @Test
    public void getPostsByBodyLengthTest() throws Exception {

        when(postService.getPostByCharLength(10, 100, 10)).thenReturn(posts);

        mockMvc.perform(get("/posts/byLength")
                        .param("limit", "10").param("minBodyLength", "10").param("maxBodyLength", "100"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(posts)));
    }

    @Test
    public void getPostsByBodyLikeTest() throws Exception {

        when(postService.getPostByBodyLike("es", 10)).thenReturn(posts);

        mockMvc.perform(get("/posts/byBodyLike")
                        .param("limit", "10").param("key", "es"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(posts)));
    }

    @Test
    public void getPostsByTitleLikeTest() throws Exception {

        when(postService.getPostByTitleLike("e", 10)).thenReturn(posts);

        mockMvc.perform(get("/posts/byTitleLike")
                        .param("limit", "10").param("key", "e"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(posts)));
    }

    @Test
    public void createPostTest() throws Exception {

        when(postService.createPost(any())).thenReturn(post);

        mockMvc.perform(post("/posts").
                        contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(post)));
    }

}
