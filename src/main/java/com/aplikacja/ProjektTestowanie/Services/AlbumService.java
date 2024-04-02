package com.aplikacja.ProjektTestowanie.Services;

import com.aplikacja.ProjektTestowanie.Entities.Album;
import com.aplikacja.ProjektTestowanie.JsonPlaceholderService.JsonPlaceHolderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AlbumService implements IAlbumService{

    private final JsonPlaceHolderService jsonPlaceHolderService;

    public List<Album> getAlbums(){
        return jsonPlaceHolderService.getAlbums();
    }

    @Override
    public Album createAlbum(Album albumRequest) {
        return jsonPlaceHolderService.createAlbum(albumRequest);
    }

    @Override
    public List<Album> getAlbumsByTitleLike(String key) {

        List<Album> albums = jsonPlaceHolderService.getAlbums();

        return albums.stream()
                .filter(album -> album.getTitle().contains(key))
                .collect(Collectors.toList());
    }

    @Override
    public List<Album> getAlbumsByAuthorId(int userId) {

        List<Album> albums = jsonPlaceHolderService.getAlbums();

        return albums.stream()
                .filter(album -> album.getUserId() == userId)
                .collect(Collectors.toList());
    }


}
