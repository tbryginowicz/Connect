package com.aplikacja.ProjektTestowanie.Controllers;

import com.aplikacja.ProjektTestowanie.Entities.Comment;
import com.aplikacja.ProjektTestowanie.Services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/byPostId")
    public List<Comment> getCommentByPostId(
            @RequestParam(required = true) int postId
    ){
        return commentService.getCommentsByPost(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment commentRequest){
        return commentService.createComment(commentRequest);
    }
}
