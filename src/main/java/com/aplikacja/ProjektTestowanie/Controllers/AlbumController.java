package com.aplikacja.ProjektTestowanie.Controllers;

import com.aplikacja.ProjektTestowanie.Entities.Album;
import com.aplikacja.ProjektTestowanie.Services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping
    public List<Album> getAlbums(){
        return albumService.getAlbums();
    }

    @GetMapping("/ByTitleLike")
    public List<Album> getAlbumsByTitleLike(
            @RequestParam(required = true) String key
    ){
        return albumService.getAlbumsByTitleLike(key);
    }

    @GetMapping("/ByAuthorId")
    public List<Album> getAlbumsByAuthorId(
            @RequestParam(required = true) int userId
    ){
        return albumService.getAlbumsByAuthorId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody Album albumRequest){
        return albumService.createAlbum(albumRequest);
    }
}
