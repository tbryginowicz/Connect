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
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@AllArgsConstructor
public class PostService {

    private static final String JSON_PLACEHOLDER_URL = "https://jsonplaceholder.typicode.com/posts";

    public List<Post> getPosts(){
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
            return Arrays.asList(posts);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
