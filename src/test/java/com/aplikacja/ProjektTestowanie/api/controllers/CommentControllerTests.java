package com.aplikacja.ProjektTestowanie.api.controllers;

import com.aplikacja.ProjektTestowanie.Controllers.CommentController;
import com.aplikacja.ProjektTestowanie.Controllers.PhotoController;
import com.aplikacja.ProjektTestowanie.Entities.Comment;
import com.aplikacja.ProjektTestowanie.Services.CommentService;
import com.aplikacja.ProjektTestowanie.Services.PhotoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CommentControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    private static Comment comment;

    private static List<Comment> comments;

    @BeforeAll
    public static void init(){

        comments = new ArrayList<>();
        comments.add(new Comment(1, 1, "test1", "test@test", "test1"));
        comments.add(new Comment(1, 2, "test1", "test@test", "test1"));
        comments.add(new Comment(1, 3, "test2", "test@test", "test1"));

        comment = comments.get(0);
    }

    @Test
    public void getCommentByPostIdTest() throws Exception {

        when(commentService.getCommentsByPost(1)).thenReturn(comments);

        mockMvc.perform(get("/comments/byPostId")
                        .param("postId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(comments)));
    }

    @Test
    public void createCommentTest() throws Exception {

        when(commentService.createComment(any())).thenReturn(comment);

        mockMvc.perform(post("/comments").
                        contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(comment)));
    }
}
