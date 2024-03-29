package com.example.TransportMe.controllers;

import com.example.TransportMe.rides.Area;
import com.example.TransportMe.rides.Offer;
import com.example.TransportMe.rides.Ride;
import com.example.TransportMe.storage.RideListStorage;
import com.example.TransportMe.storage.RideStorage;
import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.example.TransportMe.users_pack.Client;
import com.example.TransportMe.users_pack.Driver;
import com.example.TransportMe.users_pack.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {
    UserStorage userStorage = new UserListStorage();
    RideStorage rideStorage = new RideListStorage();

    @PostMapping("/register/client")
    public ResponseEntity<String> registerClient(
            @RequestParam("username") String userName,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("birthdate") String birthdate,
            @RequestParam(value = "email",required = false) String email
    ){
        try{
            User user;
            user = new Client(userName,phone,password,email,birthdate);
            // if user is already registered
            if(userStorage.getRegisteredUsers().contains(user))
                return ResponseEntity.internalServerError().body("User is already registered");
            // no problem
            userStorage.addRegisteredUser(user);
            return ResponseEntity.ok().body("Registered successfully!");
        } catch (Exception e){
            System.out.println(e.toString());
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
    @PostMapping("/ride/request")
    public boolean requestRide(
            @RequestParam("source") String source,
            @RequestParam("destination") String destination,
            @RequestParam("rideDate") String rideDate,
            @RequestParam("numberOfPassengers") int numberOfPassengers

    ){
        Area sourceArea = new Area(source);
        Area destinationArea = new Area(destination);
        Ride ride=new Ride((Client) userStorage.loggedIn,sourceArea,destinationArea,rideDate,numberOfPassengers);
        rideStorage.addRideRequest  (ride);
        return true;
    }
    @GetMapping("/ride/viewOffers")
    public List<Offer> viewOffers(){
        if(userStorage.loggedIn instanceof Client){
            Client client = (Client)userStorage.loggedIn;
            List<Offer> offers = client.viewOffers();
            return offers;
        }
        return new ArrayList<>();
    }
    @PostMapping("/ride/acceptOffer")
    public boolean acceptOffer(
            @RequestParam("id") int id
    ){
        if(userStorage.loggedIn instanceof Client)
        {
            Client client = (Client)userStorage.loggedIn;
           return client.acceptOffer(id);
        }
        else{
            return false;
        }
    }
    @PostMapping("/user/addRating")
    public boolean addRating(
            @RequestParam("driverUserName") String driverUserName,
            @RequestParam("rating") int rating
    ){
        if(userStorage.loggedIn instanceof Client) {
            Client client = (Client) userStorage.loggedIn;
            ArrayList<Driver> drivers = userStorage.getRegisteredDrivers();
            Driver d = null;
            for (Driver driver : drivers) {
                if (driver.getUserName().equals(driverUserName)) {
                    d = driver;
                }
            }

            return client.addRating(d, rating);
        }else{
            return true;
        }
    }

}

