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

import org.springframework.web.bind.annotation.GetMapping;
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
            @RequestParam("phone") String phone) {
        User user;
        user = new Admin(userName, phone, password);
        userStorage.addRegisteredUser(user);
        return true;
    }

    @GetMapping("/admin/listPending")
    public List<Driver> listPending() {
        return userStorage.getPendingRegistrations();
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
            if(user.getUsername().equals(userName) ){
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
            if(user.getUsername().equals(userName) ){
                UserListStorage.registeredUsers.add(user);
                UserListStorage.suspendedUsers.remove(user);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/admin/acceptRegisteration")
    public boolean acceptRegisteration(
            @RequestParam("userName") String userName

    ) {
       
        for( User user : userStorage.getPendingRegistrations() ){
            if( user.getUsername().equals(userName) ){
                userStorage.removePendingRegistration( (Driver) user );

                userStorage.addRegisteredUser(user);
                return true;
            }
        }

        return false;

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
