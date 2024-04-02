package com.aplikacja.ProjektTestowanie.Services;

import com.aplikacja.ProjektTestowanie.Entities.Post;

import java.util.List;

public interface IPostService {

    public List<Post> getPosts(int limit);

    public Post createPost(Post postRequest);

    public List<Post> getPostByCharLength(int min, int max, int limit);

    public List<Post> getPostByBodyLike(String key ,int limit);

    public List<Post> getPostByTitleLike(String key ,int limit);
}
