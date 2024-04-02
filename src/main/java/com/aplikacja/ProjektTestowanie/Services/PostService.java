package com.aplikacja.ProjektTestowanie.Services;

import com.aplikacja.ProjektTestowanie.Entities.Post;
import com.aplikacja.ProjektTestowanie.JsonPlaceholderService.JsonPlaceHolderService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@AllArgsConstructor
public class PostService implements IPostService{

    private final JsonPlaceHolderService jsonPlaceHolderService;

    public List<Post> getPosts(int limit){

        List<Post> posts = jsonPlaceHolderService.getPosts();

        return posts.stream().
                limit(limit).
                collect(Collectors.toList());
    }

    @Override
    public Post createPost(Post postRequest) {
        return jsonPlaceHolderService.createPost(postRequest);
    }

    public List<Post> getPostByCharLength(int min, int max, int limit){

        List<Post> posts = jsonPlaceHolderService.getPosts();

        return posts.stream()
                .filter(post -> post.getBody().length() >= min && post.getBody().length() <= max)
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Post> getPostByBodyLike(String key ,int limit){

        List<Post> posts = jsonPlaceHolderService.getPosts();

        return posts.stream()
                .filter(post -> post.getBody().contains(key))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Post> getPostByTitleLike(String key ,int limit){

        List<Post> posts = jsonPlaceHolderService.getPosts();

        return posts.stream()
                .filter(post -> post.getTitle().contains(key))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
