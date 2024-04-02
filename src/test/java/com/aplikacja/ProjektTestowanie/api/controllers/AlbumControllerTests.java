package com.aplikacja.ProjektTestowanie.api.controllers;

import com.aplikacja.ProjektTestowanie.Controllers.AlbumController;
import com.aplikacja.ProjektTestowanie.Controllers.PhotoController;
import com.aplikacja.ProjektTestowanie.Entities.Album;
import com.aplikacja.ProjektTestowanie.Services.AlbumService;
import com.aplikacja.ProjektTestowanie.Services.PhotoService;
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

@WebMvcTest(controllers = AlbumController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AlbumControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private AlbumService albumService;

    private static Album album;

    private static List<Album> albums;

    @BeforeAll
    public static void init(){

        albums = new ArrayList<>();
        albums.add(new Album(1, 1, "test1"));
        albums.add(new Album(1, 2, "test2"));
        albums.add(new Album(1, 3, "test3"));
        albums.add(new Album(1, 4, "InnyTtytul"));
        albums.add(new Album(1, 5, "test4"));

        album = albums.get(0);
    }

    @Test
    public void getAlbumsTest() throws Exception {

        when(albumService.getAlbums()).thenReturn(albums);

        mockMvc.perform(get("/albums"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(albums)));
    }

    @Test
    public void getAlbumsByTitleLikeTest() throws Exception {

        when(albumService.getAlbumsByTitleLike("t")).thenReturn(albums);

        mockMvc.perform(get("/albums/ByTitleLike")
                        .param("key", "t"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(albums)));
    }

    @Test
    public void getAlbumsByAuthorIdTest() throws Exception {

        when(albumService.getAlbumsByAuthorId(1)).thenReturn(albums);

        mockMvc.perform(get("/albums/ByAuthorId")
                        .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(albums)));
    }

    @Test
    public void createAlbumTest() throws Exception {

        when(albumService.createAlbum(any())).thenReturn(album);

        mockMvc.perform(post("/albums").
                        contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(album)))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(album)));
    }
}
