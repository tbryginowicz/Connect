package com.aplikacja.ProjektTestowanie.api.Services;

import com.aplikacja.ProjektTestowanie.Entities.Post;
import com.aplikacja.ProjektTestowanie.JsonPlaceholderService.JsonPlaceHolderService;
import com.aplikacja.ProjektTestowanie.Services.PostService;
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
public class PostServiceTests {

    @Mock
    private JsonPlaceHolderService jsonPlaceHolderService;

    @InjectMocks
    private PostService postService;

    private static List<Post> posts;

    private static Post post;

    @BeforeAll
    public static void init(){

        post = new Post(1, 10, "singlePost", "TestPostBodySingle");

        posts = new ArrayList<>();
        posts.add(new Post(1, 1, "testPost1", "TestPostBody1"));
        posts.add(new Post(1, 2, "testPost2", "TestPostBody2"));
        posts.add(new Post(1, 3, "testPost3", "TestPostBody3"));
        posts.add(new Post(2, 4, "testPost4", "TestPostBody4"));
        posts.add(new Post(3, 5, "testPost5", "Te"));
        posts.add(new Post(4, 6, "testPost6", "TestPostBody1LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG"));
        posts.add(new Post(5, 7, "testowyInnyTytul", "testowyInnyBody"));
        posts.add(new Post(6, 8, "testPost7", "TestPostBody5"));
        posts.add(new Post(7, 9, "testPost8", "TestPostBody6"));
    }

    @Test
    public void getPostsWithLMaxLimitTest(){

        when(jsonPlaceHolderService.getPosts()).thenReturn(posts);

        List<Post> result = postService.getPosts(Integer.MAX_VALUE);

        Assertions.assertThat(result.size()).isEqualTo(9);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(result.get(8).getId()).isEqualTo(9);
    }

    @Test
    public void getPostsWithLimitTest(){

        when(jsonPlaceHolderService.getPosts()).thenReturn(posts);

        List<Post> result = postService.getPosts(3);

        Assertions.assertThat(result.size()).isEqualTo(3);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(result.get(2).getId()).isEqualTo(3);
    }

    @Test
    public void createPost(){

        when(jsonPlaceHolderService.createPost(any())).thenReturn(post);

        postService.createPost(post);

        ArgumentCaptor<Post> postArgumentCaptor = ArgumentCaptor.forClass(Post.class);
        verify(jsonPlaceHolderService, times(1)).createPost(postArgumentCaptor.capture());

        Assertions.assertThat(postArgumentCaptor.getValue().getId()).isEqualTo(10);
        Assertions.assertThat(postArgumentCaptor.getValue().getUserId()).isEqualTo(1);
        Assertions.assertThat(postArgumentCaptor.getValue().getTitle()).isEqualTo("singlePost");
        Assertions.assertThat(postArgumentCaptor.getValue().getBody()).isEqualTo("TestPostBodySingle");
    }


}
