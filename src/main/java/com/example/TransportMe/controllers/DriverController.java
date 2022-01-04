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
    //Driver driver = (Driver) userStorage.loggedIn;
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
        if(userStorage.loggedIn instanceof Driver)
        {
            for (Area area:((Driver)userStorage.loggedIn).favAreas){
            if (area.name.equals(source))
                return false;
            }
            Area area = new Area(source);
            ((Driver)userStorage.loggedIn).favAreas.add(area);
            return true;
        }else{
            return false;
        }
    }
    @PostMapping("/driver/listRides")
    public ArrayList<Ride> listRides(
            @RequestParam("area") String area
    ){
        if (userStorage.loggedIn instanceof Driver)
            return ((Driver)userStorage.loggedIn).listRides(area);
        return new ArrayList<>();
    }

    @PostMapping("/driver/suggestOffer")
    public boolean suggestOffer(
            @RequestParam("price") double price,
            @RequestParam("rideId") int rideId
            ){
        if (userStorage.loggedIn instanceof Driver)
            return ((Driver)userStorage.loggedIn).suggestPrice(price,rideId);
        return false;
    }
    @GetMapping("/driver/viewRatings")
    public ArrayList<Rating> viewRatings(

    ){
        if (userStorage.loggedIn instanceof Driver)
            return ((Driver)userStorage.loggedIn).showRatingList();
        return new ArrayList<>();
    }


}
