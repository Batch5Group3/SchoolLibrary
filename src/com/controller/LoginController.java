package com.controller;

import com.model.User;
import com.services.UserService;

public class LoginController {
    private final UserService userService = new UserService();

    public boolean login(String username, String password) {
        return userService.getUserByCredentials(username, password) != null;
    }
}
