package com.aplikacja.ProjektTestowanie.Services;

import com.aplikacja.ProjektTestowanie.Entities.Post;
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
public class PostService {

    private static final String JSON_PLACEHOLDER_URL = "https://jsonplaceholder.typicode.com/posts";

    public List<Post> getPosts(int limit){
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(JSON_PLACEHOLDER_URL)
                    .header("accept", "application/json")
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 200){
                throw new RuntimeException("Could not download posts from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            Post[] posts = mapper.readValue(jsonResponse.getRawBody(),Post[].class);
            return Arrays.stream(posts).limit(limit).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Post> getPostByCharLength(int min, int max, int limit){
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(JSON_PLACEHOLDER_URL)
                    .header("accept", "application/json")
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 200){
                throw new RuntimeException("Could not download posts from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            Post[] posts = mapper.readValue(jsonResponse.getRawBody(),Post[].class);

            //filtering the posts
            return Arrays.stream(posts)
                    .filter(post -> post.getBody().length() >= min && post.getBody().length() <= max)
                    .limit(limit)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Post> getPostByBodyLike(String key ,int limit){
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(JSON_PLACEHOLDER_URL)
                    .header("accept", "application/json")
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 200){
                throw new RuntimeException("Could not download posts from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            Post[] posts = mapper.readValue(jsonResponse.getRawBody(),Post[].class);

            //filtering the posts
            return Arrays.stream(posts)
                    .filter(post -> post.getBody().contains(key))
                    .limit(limit)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Post> getPostByTitleLike(String key ,int limit){
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(JSON_PLACEHOLDER_URL)
                    .header("accept", "application/json")
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 200){
                throw new RuntimeException("Could not download posts from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            Post[] posts = mapper.readValue(jsonResponse.getRawBody(),Post[].class);

            //filtering the posts
            return Arrays.stream(posts)
                    .filter(post -> post.getTitle().contains(key))
                    .limit(limit)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
