package com.aplikacja.ProjektTestowanie.Controllers;

import com.aplikacja.ProjektTestowanie.Entities.Post;
import com.aplikacja.ProjektTestowanie.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private final PostService postService;
    @GetMapping
    public List<Post> getPosts(){
        return postService.getPosts();
    }
}
