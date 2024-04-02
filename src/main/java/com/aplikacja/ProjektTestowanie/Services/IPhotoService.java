package com.aplikacja.ProjektTestowanie.Services;

import com.aplikacja.ProjektTestowanie.Entities.Photo;

import java.util.List;

public interface IPhotoService {

    public List<Photo> getPhotosByAlbumId(int albumId);

    public Photo createPhoto(Photo photoRequest);
}
