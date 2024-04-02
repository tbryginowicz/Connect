package com.aplikacja.ProjektTestowanie.JsonPlaceholderService;

import com.aplikacja.ProjektTestowanie.Entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class JsonPlaceHolderService implements IJsonPlaceHolderService{

    private static final String JSON_PLACEHOLDER_URL = "https://jsonplaceholder.typicode.com";

    private final ObjectMapper objectMapper;

    @Override
    public List<Post> getPosts() {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(JSON_PLACEHOLDER_URL + "/posts")
                    .header("accept", "application/json")
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 200){
                throw new RuntimeException("Could not download posts from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            Post[] posts = mapper.readValue(jsonResponse.getRawBody(),Post[].class);
            return Arrays.stream(posts).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Post createPost(Post postRequest) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post(JSON_PLACEHOLDER_URL + "/posts")
                    .header("Content-type", "application/json; charset=UTF-8")
                    .body(objectMapper.writeValueAsString(postRequest))
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 201){
                throw new RuntimeException("Could not create posts from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonResponse.getRawBody(),Post.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Comment> getComments() {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(JSON_PLACEHOLDER_URL + "/comments")
                    .header("accept", "application/json")
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 200){
                throw new RuntimeException("Could not download comments from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            Comment[] comments = mapper.readValue(jsonResponse.getRawBody(),Comment[].class);
            return Arrays.stream(comments).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Comment createComment(Comment commentRequest) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post(JSON_PLACEHOLDER_URL + "/comments")
                    .header("Content-type", "application/json; charset=UTF-8")
                    .body(objectMapper.writeValueAsString(commentRequest))
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 201){
                throw new RuntimeException("Could not create comment from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonResponse.getRawBody(),Comment.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Album> getAlbums() {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(JSON_PLACEHOLDER_URL + "/albums")
                    .header("accept", "application/json")
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 200){
                throw new RuntimeException("Could not download albums from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            Album[] albums = mapper.readValue(jsonResponse.getRawBody(),Album[].class);
            return Arrays.stream(albums).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Album createAlbum(Album albumRequest) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post(JSON_PLACEHOLDER_URL + "/albums")
                    .header("Content-type", "application/json; charset=UTF-8")
                    .body(objectMapper.writeValueAsString(albumRequest))
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 201){
                throw new RuntimeException("Could not create album from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonResponse.getRawBody(),Album.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Photo> getPhotos() {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(JSON_PLACEHOLDER_URL + "/photos")
                    .header("accept", "application/json")
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 200){
                throw new RuntimeException("Could not download users from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            Photo[] photos = mapper.readValue(jsonResponse.getRawBody(),Photo[].class);
            return Arrays.stream(photos).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Photo createPhoto(Photo photoRequest) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post(JSON_PLACEHOLDER_URL + "/photos")
                    .header("Content-type", "application/json; charset=UTF-8")
                    .body(objectMapper.writeValueAsString(photoRequest))
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 201){
                throw new RuntimeException("Could not create photo from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonResponse.getRawBody(),Photo.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<User> getUsers() {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(JSON_PLACEHOLDER_URL + "/users")
                    .header("accept", "application/json")
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 200){
                throw new RuntimeException("Could not download users from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            User[] users = mapper.readValue(jsonResponse.getRawBody(),User[].class);
            return Arrays.stream(users).toList();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User createUser(User userRequest) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post(JSON_PLACEHOLDER_URL + "/users")
                    .header("Content-type", "application/json; charset=UTF-8")
                    .body(objectMapper.writeValueAsString(userRequest))
                    .asJson();

            //error handling
            if(jsonResponse.getStatus() != 201){
                throw new RuntimeException("Could not create user from JsonPlaceholder, code-" + jsonResponse.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonResponse.getRawBody(),User.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
