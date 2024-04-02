package com.aplikacja.ProjektTestowanie.api.Services;

import com.aplikacja.ProjektTestowanie.Entities.Photo;
import com.aplikacja.ProjektTestowanie.JsonPlaceholderService.JsonPlaceHolderService;
import com.aplikacja.ProjektTestowanie.Services.PhotoService;
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
public class PhotoServiceTests {

    @Mock
    private JsonPlaceHolderService jsonPlaceHolderService;

    @InjectMocks
    private PhotoService photoService;

    private static Photo photo;

    private static List<Photo> photos;

    @BeforeAll
    public static void init(){

        photos = new ArrayList<>();
        photos.add(new Photo(1, 1, "test1", "//test1", "test"));
        photos.add(new Photo(1, 2, "test2", "//test2", "test"));
        photos.add(new Photo(1, 3, "test3", "//test3", "test"));
        photos.add(new Photo(2, 4, "test4", "//test4", "test"));

        photo = photos.get(0);
    }

    @Test
    public void getPhotosByAlbumIdTest(){

        when(jsonPlaceHolderService.getPhotos()).thenReturn(photos);

        List<Photo> result = photoService.getPhotosByAlbumId(1);

        Assertions.assertThat(result.size()).isEqualTo(3);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(result.get(1).getId()).isEqualTo(2);
        Assertions.assertThat(result.get(2).getId()).isEqualTo(3);
    }

    @Test
    public void createPhotoTest(){

         when(jsonPlaceHolderService.createPhoto(any())).thenReturn(photo);

         photoService.createPhoto(photo);

        ArgumentCaptor<Photo> photoArgumentCaptor = ArgumentCaptor.forClass(Photo.class);
        verify(jsonPlaceHolderService, times(1)).createPhoto(photoArgumentCaptor.capture());

        Assertions.assertThat(photoArgumentCaptor.getValue().getUrl()).isEqualTo(photo.getUrl());
        Assertions.assertThat(photoArgumentCaptor.getValue().getTitle()).isEqualTo(photo.getTitle());
        Assertions.assertThat(photoArgumentCaptor.getValue().getAlbumId()).isEqualTo(photo.getAlbumId());
        Assertions.assertThat(photoArgumentCaptor.getValue().getId()).isEqualTo(photo.getId());
        Assertions.assertThat(photoArgumentCaptor.getValue().getThumbnailUrl()).isEqualTo(photo.getThumbnailUrl());
    }
}
