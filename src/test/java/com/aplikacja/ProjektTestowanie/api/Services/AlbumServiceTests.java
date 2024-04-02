package com.aplikacja.ProjektTestowanie.api.Services;

import com.aplikacja.ProjektTestowanie.Entities.Album;
import com.aplikacja.ProjektTestowanie.JsonPlaceholderService.JsonPlaceHolderService;
import com.aplikacja.ProjektTestowanie.Services.AlbumService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlbumServiceTests {

    @Mock
    private JsonPlaceHolderService jsonPlaceHolderService;

    @InjectMocks
    private AlbumService albumService;

    private static Album album;

    private static List<Album> albums;

    @BeforeAll
    public static void init(){

        albums = new ArrayList<>();
        albums.add(new Album(1, 1, "test1"));
        albums.add(new Album(1, 2, "test2"));
        albums.add(new Album(2, 3, "test3"));
        albums.add(new Album(3, 4, "InnyTtytul"));
        albums.add(new Album(4, 5, "test4"));

        album = albums.get(0);
    }

    @Test
    public void getAlbumsTest(){

        when(jsonPlaceHolderService.getAlbums()).thenReturn(albums);

        List<Album> result = albumService.getAlbums();

        Assertions.assertThat(result.size()).isEqualTo(5);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getTitle()).isEqualTo("test1");
        Assertions.assertThat(result.get(0).getUserId()).isEqualTo(1);
        Assertions.assertThat(result.get(4).getId()).isEqualTo(5);
        Assertions.assertThat(result.get(4).getTitle()).isEqualTo("test4");
        Assertions.assertThat(result.get(4).getUserId()).isEqualTo(4);
    }

    @Test
    public void createAlbumTest(){

        when(jsonPlaceHolderService.createAlbum(any())).thenReturn(album);

        albumService.createAlbum(album);

        ArgumentCaptor<Album> albumArgumentCaptor = ArgumentCaptor.forClass(Album.class);
        verify(jsonPlaceHolderService, times(1)).createAlbum(albumArgumentCaptor.capture());

        Assertions.assertThat(albumArgumentCaptor.getValue().getId()).isEqualTo(1);
        Assertions.assertThat(albumArgumentCaptor.getValue().getUserId()).isEqualTo(1);
        Assertions.assertThat(albumArgumentCaptor.getValue().getTitle()).isEqualTo("test1");
    }

    @Test
    public void getAlbumsByTitleLikeTest(){

         when(jsonPlaceHolderService.getAlbums()).thenReturn(albums);

         List<Album> result = albumService.getAlbumsByTitleLike("Inny");

         Assertions.assertThat(result.size()).isEqualTo(1);
         Assertions.assertThat(result.get(0).getId()).isEqualTo(4);
         Assertions.assertThat(result.get(0).getUserId()).isEqualTo(3);
         Assertions.assertThat(result.get(0).getTitle()).isEqualTo("InnyTtytul");
    }

    @Test
    public void getAlbumsByAuthorId(){

        when(jsonPlaceHolderService.getAlbums()).thenReturn(albums);

        List<Album> result = albumService.getAlbumsByAuthorId(1);

        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0).getUserId()).isEqualTo(1);
        Assertions.assertThat(result.get(1).getUserId()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(result.get(1).getId()).isEqualTo(2);
    }
}
