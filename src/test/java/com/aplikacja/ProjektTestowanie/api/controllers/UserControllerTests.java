package com.aplikacja.ProjektTestowanie.api.controllers;

import com.aplikacja.ProjektTestowanie.Controllers.UserController;
import com.aplikacja.ProjektTestowanie.Entities.Adress;
import com.aplikacja.ProjektTestowanie.Entities.Company;
import com.aplikacja.ProjektTestowanie.Entities.Geo;
import com.aplikacja.ProjektTestowanie.Entities.User;
import com.aplikacja.ProjektTestowanie.Services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private static User user;

    private static List<User> users;

    @BeforeAll
    public static void init(){
        Geo geo = new Geo(12.50f, 15.50f);
        Adress adress = new Adress("test", "12", "test", "12-56", geo);
        Company company = new Company("test", "test", "test");

        users = new ArrayList<>();
        users.add(new User(1, "test1", "testusername1", "test@test", adress, "123456789", "web.site", company));
        users.add(new User(2, "test2", "testusername2", "test@test", adress, "123456789", "web.site", company));
        users.add(new User(3, "test3", "testusername3", "test@test", adress, "123456789", "web.site", company));

        user = users.get(0);
    }

    @Test
    public void getUsersTest() throws Exception {

        when(userService.getUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(users)));
    }

    @Test
    public void createUserTest() throws Exception {

        when(userService.createUser(any())).thenReturn(user);

        mockMvc.perform(post("/users").
                contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(user)));
    }
}
