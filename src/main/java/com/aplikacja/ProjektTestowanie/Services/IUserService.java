package com.aplikacja.ProjektTestowanie.Services;

import com.aplikacja.ProjektTestowanie.Entities.User;

import java.util.List;

public interface IUserService {

    public List<User> getUsers();

    public User createUser(User userRequest);
}
