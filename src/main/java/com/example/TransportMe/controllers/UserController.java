package com.example.TransportMe.controllers;

import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.example.TransportMe.users_pack.Admin;
import com.example.TransportMe.users_pack.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/users")
    public List<User> getRegisteredUsers(){
        return userStorage.getRegisteredUsers();
    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable("username") String userName ){
        for (User user : userStorage.getRegisteredUsers()){
            if(user.getUsername().equals(userName))
                return user;
        }
        return null;
    }

}
