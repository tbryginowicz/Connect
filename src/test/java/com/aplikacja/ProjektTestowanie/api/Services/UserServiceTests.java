package com.aplikacja.ProjektTestowanie.api.Services;

import com.aplikacja.ProjektTestowanie.Entities.Adress;
import com.aplikacja.ProjektTestowanie.Entities.Company;
import com.aplikacja.ProjektTestowanie.Entities.Geo;
import com.aplikacja.ProjektTestowanie.Entities.User;
import com.aplikacja.ProjektTestowanie.JsonPlaceholderService.JsonPlaceHolderService;
import com.aplikacja.ProjektTestowanie.Services.UserService;
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
public class UserServiceTests {

    @Mock
    private JsonPlaceHolderService jsonPlaceHolderService;

    @InjectMocks
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
    public void getUsersTest(){

        when(jsonPlaceHolderService.getUsers()).thenReturn(users);

        List<User> result = userService.getUsers();

        Assertions.assertThat(result.size()).isEqualTo(3);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getName()).isEqualTo("test1");
        Assertions.assertThat(result.get(2).getId()).isEqualTo(3);
        Assertions.assertThat(result.get(2).getName()).isEqualTo("test3");
    }

    @Test
    public void createUserTest(){

        when(jsonPlaceHolderService.createUser(any())).thenReturn(user);

        userService.createUser(user);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(jsonPlaceHolderService, times(1)).createUser(userArgumentCaptor.capture());

        Assertions.assertThat(userArgumentCaptor.getValue().getId()).isEqualTo(1);
        Assertions.assertThat(userArgumentCaptor.getValue().getPhone()).isEqualTo(user.getPhone());
        Assertions.assertThat(userArgumentCaptor.getValue().getEmail()).isEqualTo(user.getEmail());
        Assertions.assertThat(userArgumentCaptor.getValue().getWebsite()).isEqualTo(user.getWebsite());
        Assertions.assertThat(userArgumentCaptor.getValue().getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(userArgumentCaptor.getValue().getName()).isEqualTo(user.getName());
        Assertions.assertThat(userArgumentCaptor.getValue().getAddress()).isEqualTo(user.getAddress());
        Assertions.assertThat(userArgumentCaptor.getValue().getCompany()).isEqualTo(user.getCompany());
    }
}
