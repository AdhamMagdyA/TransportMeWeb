package com.example.TransportMe.controllers;

import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.example.TransportMe.users_pack.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserStorage userStorage = new UserListStorage();

    @PostMapping("/login")
    public boolean login(
            @RequestParam("username") String userName,
            @RequestParam("password") String password
    ){
        if (userStorage.searchRegisteredUsers(userName)){
            for(User u : userStorage.getRegisteredUsers()){
                if(u.getUsername().equals(userName) && u.getPassword().equals(password) ){
                    UserStorage.loggedIn = u;
                    return true;
                }
            }
        }
        return false;
    }

    @GetMapping("/user")
    public User getLoggedIn(){
        return userStorage.loggedIn;
    }

}
