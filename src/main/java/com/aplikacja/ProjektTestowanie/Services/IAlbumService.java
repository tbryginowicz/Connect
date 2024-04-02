package com.aplikacja.ProjektTestowanie.Services;

import com.aplikacja.ProjektTestowanie.Entities.Album;

import java.util.List;

public interface IAlbumService {

    public List<Album> getAlbums();

    public Album createAlbum(Album albumRequest);

    public List<Album> getAlbumsByTitleLike(String key);

    public List<Album> getAlbumsByAuthorId(int userId);
}
