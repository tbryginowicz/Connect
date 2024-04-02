package com.aplikacja.ProjektTestowanie.api.controllers;

import com.aplikacja.ProjektTestowanie.Controllers.PhotoController;
import com.aplikacja.ProjektTestowanie.Controllers.UserController;
import com.aplikacja.ProjektTestowanie.Entities.Photo;
import com.aplikacja.ProjektTestowanie.Services.PhotoService;
import com.aplikacja.ProjektTestowanie.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PhotoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PhotoControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private PhotoService photoService;

    private static Photo photo;

    private static List<Photo> photos;

    @BeforeAll
    public static void init(){

        photos = new ArrayList<>();
        photos.add(new Photo(1, 1, "test1", "//test1", "test"));
        photos.add(new Photo(1, 2, "test2", "//test2", "test"));
        photos.add(new Photo(1, 3, "test3", "//test3", "test"));

        photo = photos.get(0);
    }

    @Test
    public void getByAlbumId() throws Exception {

        when(photoService.getPhotosByAlbumId(anyInt())).thenReturn(photos);

        mockMvc.perform(get("/photos/byAlbumId")
                        .param("albumId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(photos)));
    }

    @Test
    public void createPhotoTest() throws Exception {

        when(photoService.createPhoto(any())).thenReturn(photo);

        mockMvc.perform(post("/photos").
                        contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(photo)))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(photo)));
    }
}
