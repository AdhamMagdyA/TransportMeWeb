package com.example.TransportMe.controllers;

import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.example.TransportMe.users_pack.Admin;
import com.example.TransportMe.users_pack.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    UserStorage userStorage = new UserListStorage();

    @PostMapping("/register/admin")
    public boolean registerAdmin(
            @RequestParam("username") String userName,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone
    ){
        User user;
        user = new Admin(userName,phone,password);
        userStorage.addRegisteredUser(user);
        return true;
    }
}
