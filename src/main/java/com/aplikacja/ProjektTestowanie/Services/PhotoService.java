package com.aplikacja.ProjektTestowanie.Services;

import com.aplikacja.ProjektTestowanie.Entities.Photo;
import com.aplikacja.ProjektTestowanie.JsonPlaceholderService.JsonPlaceHolderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PhotoService {

    private final JsonPlaceHolderService jsonPlaceHolderService;

    public List<Photo> getPhotosByAlbumId(int albumId){

        List<Photo> photos = jsonPlaceHolderService.getPhotos();

        return photos.stream()
                .filter(photo -> photo.getAlbumId() == albumId)
                .collect(Collectors.toList());
    }

    public Photo createPhoto(Photo photoRequest){
        return jsonPlaceHolderService.createPhoto(photoRequest);
    }
}
