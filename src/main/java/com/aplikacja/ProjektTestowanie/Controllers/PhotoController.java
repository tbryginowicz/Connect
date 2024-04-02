package com.aplikacja.ProjektTestowanie.Controllers;

import com.aplikacja.ProjektTestowanie.Entities.Photo;
import com.aplikacja.ProjektTestowanie.Services.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/photos")
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/byAlbumId")
    public List<Photo> getByAlbumId(
            @RequestParam(required = true) int albumId
    ){
        return photoService.getPhotosByAlbumId(albumId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Photo createPhoto(@RequestBody Photo photoRequest){
        return photoService.createPhoto(photoRequest);
    }
}
