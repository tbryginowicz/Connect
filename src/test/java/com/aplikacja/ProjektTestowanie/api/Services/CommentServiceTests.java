package com.aplikacja.ProjektTestowanie.api.Services;

import com.aplikacja.ProjektTestowanie.Entities.Comment;
import com.aplikacja.ProjektTestowanie.JsonPlaceholderService.JsonPlaceHolderService;
import com.aplikacja.ProjektTestowanie.Services.CommentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTests {

    @Mock
    private JsonPlaceHolderService jsonPlaceHolderService;

    @InjectMocks
    private CommentService commentService;

    private static Comment comment;

    private static List<Comment> comments;

    @BeforeAll
    public static void init(){

        comments = new ArrayList<>();
        comments.add(new Comment(1, 1, "test1", "test@test", "test1"));
        comments.add(new Comment(1, 2, "test1", "test@test", "test1"));
        comments.add(new Comment(2, 3, "test2", "test@test", "test1"));

        comment = comments.get(0);
    }

    @Test
    public void  getCommentsByPostTest(){

        when(jsonPlaceHolderService.getComments()).thenReturn(comments);

        List<Comment> result = commentService.getCommentsByPost(1);

        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(result.get(1).getId()).isEqualTo(2);
    }

    @Test
    public void createComnentTest(){

        when(jsonPlaceHolderService.createComment(any())).thenReturn(comment);

        commentService.createComment(comment);

        ArgumentCaptor<Comment> commentArgumentCaptor = ArgumentCaptor.forClass(Comment.class);
        verify(jsonPlaceHolderService, times(1)).createComment(commentArgumentCaptor.capture());

        Assertions.assertThat(commentArgumentCaptor.getValue().getId()).isEqualTo(1);
        Assertions.assertThat(commentArgumentCaptor.getValue().getPostId()).isEqualTo(1);
        Assertions.assertThat(commentArgumentCaptor.getValue().getBody()).isEqualTo(comment.getBody());
        Assertions.assertThat(commentArgumentCaptor.getValue().getName()).isEqualTo(comment.getName());
        Assertions.assertThat(commentArgumentCaptor.getValue().getEmail()).isEqualTo(comment.getEmail());
    }
}
