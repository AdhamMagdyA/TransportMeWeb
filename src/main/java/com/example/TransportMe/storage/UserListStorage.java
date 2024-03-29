package com.example.TransportMe.storage;

import com.example.TransportMe.users_pack.Admin;
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
    public static ArrayList<User> suspendedUsers=new ArrayList<>();

    public static ArrayList<Ride> ridesEvents=new ArrayList<>();

//    public UserListStorage(){
//        registeredUsers.add(new User("admin","011","admin","admin","12/12"));
//    }

    @Override
    public boolean addRegisteredUser(User user) {
        registeredUsers.add( user );
        if(user instanceof Driver) registeredDrivers.add( (Driver) user );
        if(user instanceof Client) registeredClients.add( (Client) user );
        return false;
    }

    @Override
    public boolean removeRegisteredUser(User user) {
        registeredUsers.remove(user);
        return true;
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
    public List<User> getRegisteredUsers() {
        return this.registeredUsers;
    }

    @Override
    public List<User> getSuspendedUsers() {
        return suspendedUsers;
    }

    @Override
    public boolean searchRegisteredUsers(String userName) {
        for (User user : registeredUsers){
            if(user.getUserName().equals(userName))
                return true;
        }
        return false;
    }

    @Override
    public ArrayList<Ride> getRidesEvents() {
       
        return ridesEvents;
    }

    @Override
    public void addSuspendedUser(User user) {
        suspendedUsers.add(user);
    }

}
