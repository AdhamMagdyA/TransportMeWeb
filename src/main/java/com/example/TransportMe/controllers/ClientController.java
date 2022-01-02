package com.example.TransportMe.controllers;

import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.example.TransportMe.users_pack.Admin;
import com.example.TransportMe.users_pack.Client;
import com.example.TransportMe.users_pack.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    UserStorage userStorage = new UserListStorage();

    @PostMapping("/register/client")
    public boolean registerClient(
            @RequestParam("username") String userName,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("birthdate") String birthdate,
            @RequestParam(value = "email",required = false) String email
    ){
        User user;
        user = new Client(userName,phone,password,email,birthdate);
        userStorage.addRegisteredUser(user);
        return true;
    }
}
