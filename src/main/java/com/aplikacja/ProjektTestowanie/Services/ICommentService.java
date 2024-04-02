package com.aplikacja.ProjektTestowanie.Services;

import com.aplikacja.ProjektTestowanie.Entities.Comment;
import com.aplikacja.ProjektTestowanie.Entities.Post;

import java.util.List;

public interface ICommentService {

    public List<Comment> getCommentsByPost(int postId);

    public Comment createComment(Comment commentRequest);
}
