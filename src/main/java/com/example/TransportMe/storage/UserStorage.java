//class storage
package com.example.TransportMe.storage;

import com.example.TransportMe.users_pack.Driver;
import com.example.TransportMe.users_pack.User;

import java.util.ArrayList;
import java.util.List;

import com.example.TransportMe.rides.Ride;

public abstract class UserStorage {
    public static User loggedIn;

    public abstract boolean addRegisteredUser( User user );
    public abstract boolean removeRegisteredUser( User user );
    public abstract boolean addPendingRegistration( Driver driver );
    public abstract boolean removePendingRegistration( Driver driver );
    public abstract ArrayList<Driver> getRegisteredDrivers();
    public abstract List<Driver> getPendingRegistrations();
    public abstract List<User> getRegisteredUsers();
    public abstract boolean searchRegisteredUsers( String userName );

    public abstract ArrayList<Ride> getRidesEvents();
}
