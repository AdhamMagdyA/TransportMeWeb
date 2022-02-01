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
    public ResponseEntity<String> login(
            @RequestParam("username") String userName,
            @RequestParam("password") String password
    ){
        try {
            if (userStorage.searchRegisteredUsers(userName)) {
                for (User u : userStorage.getRegisteredUsers()) {
                    if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                        UserStorage.loggedIn = u;
                        return ResponseEntity.accepted().body("Logged in successfully!");
                    }
                }
            }
            return ResponseEntity.internalServerError().body("Wrong credentials");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<User> getLoggedIn(){
        try {
            user = UserStorage.loggedIn;
            if (user == null)
                return ResponseEntity.noContent().build();
            else
                return ResponseEntity.ok().body(user);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getRegisteredUsers(){
        try {
            return ResponseEntity.ok().body(userStorage.getRegisteredUsers());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
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
