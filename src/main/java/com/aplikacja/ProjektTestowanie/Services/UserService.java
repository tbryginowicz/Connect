package com.aplikacja.ProjektTestowanie.Services;

import com.aplikacja.ProjektTestowanie.Entities.User;
import com.aplikacja.ProjektTestowanie.JsonPlaceholderService.JsonPlaceHolderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService implements IUserService{

    private final JsonPlaceHolderService jsonPlaceHolderService;
    @Override
    public List<User> getUsers() {
        return jsonPlaceHolderService.getUsers();
    }

    @Override
    public User createUser(User userRequest) {
        return jsonPlaceHolderService.createUser(userRequest);
    }
}
