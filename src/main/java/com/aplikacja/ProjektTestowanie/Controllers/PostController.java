package com.aplikacja.ProjektTestowanie.Controllers;

import com.aplikacja.ProjektTestowanie.Entities.Post;
import com.aplikacja.ProjektTestowanie.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private final PostService postService;
    @GetMapping()
    public List<Post> getPosts(
            @RequestParam(required = false, defaultValue = Integer.MAX_VALUE + "") int limit
    ){
        return postService.getPosts(limit);
    }

    @GetMapping("/byLength")
    public List<Post> getPostsByBodyLength(
            @RequestParam(required = false, defaultValue = "0") int minBodyLength,
            @RequestParam(required = false, defaultValue = Integer.MAX_VALUE + "") int maxBodyLength,
            @RequestParam(required = false, defaultValue = Integer.MAX_VALUE + "") int limit){
        return postService.getPostByCharLength(minBodyLength, maxBodyLength, limit);
    }

    @GetMapping("/byBodyLike")
    public List<Post> getPostsByBodyLike(
            @RequestParam(required = false, defaultValue = "") String key,
            @RequestParam(required = false, defaultValue = Integer.MAX_VALUE + "") int limit){
        return postService.getPostByBodyLike(key, limit);
    }

    @GetMapping("/byTitleLike")
    public List<Post> getPostsByTitleLike(
            @RequestParam(required = false, defaultValue = "") String key,
            @RequestParam(required = false, defaultValue = Integer.MAX_VALUE + "") int limit){
        return postService.getPostByTitleLike(key, limit);
    }

    @GetMapping("/byUserName")
    public List<Post> getPostsByUserName(
            @RequestParam(required = false, defaultValue = "") String username,
            @RequestParam(required = false, defaultValue = Integer.MAX_VALUE + "") int limit){
        return null;//postService.getPostByTitleLike(username, limit);
    }

}
