package com.example.TransportMe.storage;

import com.example.TransportMe.users_pack.Client;
import com.example.TransportMe.users_pack.Driver;
import com.example.TransportMe.users_pack.User;

import java.util.ArrayList;
import java.util.List;

import com.example.TransportMe.rides.Ride;

public class UserListStorage extends UserStorage{
    public static List<Driver> pendingRegistrations=new ArrayList<>();
    public static ArrayList<User> registeredUsers=new ArrayList<>() ;
    public static ArrayList<Driver> registeredDrivers =new ArrayList<>();
    public static ArrayList<Client> registeredClients=new ArrayList<>();
    
    public static ArrayList<Ride> ridesEvents=new ArrayList<>();

    @Override
    public boolean addRegisteredUser(User user) {
        registeredUsers.add( user );
        if(user instanceof Driver) registeredDrivers.add( (Driver) user );
        if(user instanceof Client) registeredClients.add( (Client) user );
        return false;
    }

    @Override
    public boolean removeRegisteredUser(User user) {
        return false;
    }

    @Override
    public boolean addPendingRegistration(Driver driver) {
        pendingRegistrations.add(driver);
        return true;
    }

    @Override
    public boolean removePendingRegistration(Driver driver) {
        pendingRegistrations.remove(driver);
        return true;
    }

    @Override
    public ArrayList<Driver> getRegisteredDrivers() {
        return registeredDrivers;
    }

    @Override
    public List<Driver> getPendingRegistrations() {
        return pendingRegistrations;
    }

    @Override
    public ArrayList<Ride> getRidesEvents() {
       
        return ridesEvents;
    }

}
