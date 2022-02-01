package com.example.TransportMe.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.TransportMe.Events.Event;
import com.example.TransportMe.rides.Ride;
import com.example.TransportMe.storage.RideListStorage;
import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.example.TransportMe.users_pack.Admin;
import com.example.TransportMe.users_pack.Driver;
import com.example.TransportMe.users_pack.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    UserStorage userStorage = new UserListStorage();

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(
            @RequestParam("username") String userName,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone
    ){
        try {
            User user;
            user = new Admin(userName, phone, password);
            // if user is already registered
            if (userStorage.getRegisteredUsers().contains(user))
                return ResponseEntity.internalServerError().body("User is already registered");
            // no problem
            userStorage.addRegisteredUser(user);
            return ResponseEntity.ok().body("Registration requested successfully");
        }catch ( Exception e ){
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

    @GetMapping("/admin/listPending")
    public ResponseEntity<List<Driver>> listPending() {
        try {
            return ResponseEntity.ok().body(userStorage.getPendingRegistrations());
        }catch ( Exception e ){
            return ResponseEntity.internalServerError().body(null);
        }
    }


    // we may remove this functionality

//    @PostMapping("/admin/addPending")
//    public void addPending(
//            @RequestParam("driverUserName") String driverUserName
//
//    ) {
//        ArrayList<Driver> drivers = userStorage.getRegisteredDrivers();
//        Driver d = null;
//        for (Driver driver : drivers) {
//            if (driver.getUsername().equals(driverUserName)) {
//                d = driver;
//
//                userStorage.addPendingRegistration(d);
//            }
//        }
//
//    }

    @PostMapping("/admin/suspendUser")
    public boolean suspendUser(
            @RequestParam("userName") String userName
    ) {

        for( User user : userStorage.getRegisteredUsers() ) {
            if(user.getUserName().equals(userName) ){
               userStorage.removeRegisteredUser(user);
               userStorage.addSuspendedUser(user);
               return true;
            }
        }
        return false;

    }

    @PostMapping("/admin/unsuspendUser")
    public boolean unsuspendUser(
            @RequestParam("userName") String userName
    ) {
       
        for( User user : userStorage.getSuspendedUsers() ) {
            if(user.getUserName().equals(userName) ){
                UserListStorage.registeredUsers.add(user);
                UserListStorage.suspendedUsers.remove(user);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/admin/acceptRegisteration")
    public ResponseEntity<String> acceptRegisteration(
            @RequestParam("userName") String userName
    ) {
       try {
           for (User user : userStorage.getPendingRegistrations()) {
               if (user.getUserName().equals(userName)) {
                   userStorage.removePendingRegistration((Driver) user);
                   userStorage.addRegisteredUser(user);
                   return ResponseEntity.ok().body(user.getUserName() + " Registration accepted");
               }
           }

           return ResponseEntity.internalServerError().body("Registration not found");
       }catch ( Exception e ){
           return ResponseEntity.internalServerError().body(e.toString());
       }
    }
    @PostMapping("/admin/showEvents")
    public ArrayList<Event> showEvents(
            @RequestParam("rideID") int rideID

    ) {
       
        Ride r=null;
        for( Ride ride : RideListStorage.rides) {
            if(ride.getId()== rideID ){
                r=ride;
                
        }
        }
        return r.events;

    }
}
