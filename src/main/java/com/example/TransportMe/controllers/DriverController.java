package com.example.TransportMe.controllers;

import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.example.TransportMe.users_pack.Client;
import com.example.TransportMe.users_pack.Driver;
import com.example.TransportMe.users_pack.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    UserStorage userStorage = new UserListStorage();

    @PostMapping("/register/driver")
    public boolean registerDriver(
            @RequestParam("username") String userName,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("birthdate") String birthdate,
            @RequestParam("driving_license") String drivingLicense,
            @RequestParam("national_id") String nationalID,
            @RequestParam(value = "email",required = false) String email
    ){
        User user;
        user = new Driver(userName,phone,password,email,drivingLicense,nationalID,birthdate);
        userStorage.addPendingRegistration((Driver) user);
        return true;
    }
}
