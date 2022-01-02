package com.example.TransportMe.controllers;

import com.example.TransportMe.rides.Area;
import com.example.TransportMe.rides.Ride;
import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.example.TransportMe.users_pack.Client;
import com.example.TransportMe.users_pack.Driver;
import com.example.TransportMe.users_pack.Rating;
import com.example.TransportMe.users_pack.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DriverController {

    UserStorage userStorage = new UserListStorage();
    Driver driver = (Driver) userStorage.loggedIn;
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
    @PostMapping("/driver/addFavArea")
    public boolean addFavArea(
            @RequestParam("source") String source
    ){
        for (Area area:driver.favAreas){
            if (area.name.equals(source))
                return false;
        }
        Area area = new Area(source);
        driver.favAreas.add(area);
        return true;
    }
    @PostMapping("/driver/listRides")
    public ArrayList<Ride> listRides(
            @RequestParam("area") String area
    ){
        return driver.listRides(area);
    }

    @PostMapping("/driver/suggestOffer")
    public boolean suggestOffer(
            @RequestParam("price") double price,
            @RequestParam("rideId") int rideId
            ){
        return driver.suggestPrice(price,rideId);
    }
    @GetMapping("/driver/viewRatings")
    public ArrayList<Rating> viewRatings(

    ){
        return driver.showRatingList();
    }

}
