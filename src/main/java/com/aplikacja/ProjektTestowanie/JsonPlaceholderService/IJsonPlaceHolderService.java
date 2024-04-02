package com.aplikacja.ProjektTestowanie.JsonPlaceholderService;

import com.aplikacja.ProjektTestowanie.Entities.*;

import java.util.List;

public interface IJsonPlaceHolderService {

    public List<Post> getPosts();

    public Post createPost(Post postRequest);

    public List<Comment> getComments();

    public Comment createComment(Comment commentRequest);

    public List<Album> getAlbums();

    public Album createAlbum(Album albumRequest);

    public List<Photo> getPhotos();

    public Photo createPhoto(Photo photoRequest);

    public List<User> getUsers();

    public User createUser(User userRequest);

}
