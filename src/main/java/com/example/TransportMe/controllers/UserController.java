package com.example.TransportMe.controllers;

import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.example.TransportMe.users_pack.Admin;
import com.example.TransportMe.users_pack.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    UserStorage userStorage = new UserListStorage();
    private User user;

    @PostMapping("/login")
    public boolean login(
            @RequestParam("username") String userName,
            @RequestParam("password") String password
    ){
        if (userStorage.searchRegisteredUsers(userName)){
            for(User u : userStorage.getRegisteredUsers()){
                if(u.getUserName().equals(userName) && u.getPassword().equals(password) ){
                    UserStorage.loggedIn = u;
                    return true;
                }
            }
        }
        return false;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getLoggedIn(){
        user = UserStorage.loggedIn;
        if(user == null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users")
    public List<User> getRegisteredUsers(){
        return userStorage.getRegisteredUsers();
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String userName ){
        for (User user : userStorage.getRegisteredUsers()){
            if(user.getUserName().equals(userName))
                return ResponseEntity.ok().body(user);
        }
        return null;
    }

}
