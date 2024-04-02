package com.aplikacja.ProjektTestowanie.Services;

import com.aplikacja.ProjektTestowanie.Entities.Comment;
import com.aplikacja.ProjektTestowanie.JsonPlaceholderService.JsonPlaceHolderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentService implements ICommentService{

    private final JsonPlaceHolderService jsonPlaceHolderService;

    @Override
    public List<Comment> getCommentsByPost(int postId) {

        List<Comment> comments = jsonPlaceHolderService.getComments();

        return comments.stream()
                .filter(comment -> comment.getPostId() == postId)
                .collect(Collectors.toList());
    }

    @Override
    public Comment createComment(Comment commentRequest) {
        return jsonPlaceHolderService.createComment(commentRequest);
    }
}
