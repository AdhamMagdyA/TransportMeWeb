package com.example.TransportMe.users_pack;

import com.example.TransportMe.Events.Event;
import com.example.TransportMe.rides.Area;
import com.example.TransportMe.rides.Ride;
import com.example.TransportMe.storage.RideListStorage;
import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;

import java.util.ArrayList;

public class Admin extends User{

    final static UserStorage userStorage = new UserListStorage();

    public Admin(String userName, String mobileNumber,String password){
        super(userName, mobileNumber,password);
    }

    //transformed to use UserStorage instead of TransportMe
    public void addPendingRegistrations(Driver driver){
        userStorage.addPendingRegistration(driver);
    }


    public static void suspendUser (String username){
         for( User user : UserListStorage.registeredUsers ) {
             if(user.getUserName().equals(username) ){
                UserListStorage.registeredUsers.remove(user);
                UserListStorage.suspendedUsers.add(user);
            }
         }
    }
    public static void unSuspendUser(String username) {
        for (User user : UserListStorage.registeredUsers) {
            if (user.getUserName().equals(username)) {
                UserListStorage.registeredUsers.add(user);
                UserListStorage.suspendedUsers.remove(user);
            }

        }
    }


    // transformed to use UserStorage instead of TransportMe
    public boolean acceptRegistration(String userName){
        for( User user : userStorage.getPendingRegistrations() ){
            if( user.getUserName().equals(userName) ){
                // remove from pending registration
                userStorage.removePendingRegistration( (Driver) user );
                // add registration to database
                userStorage.addRegisteredUser(user);
                return true;
            }
        }
        return false;
    }

    // make discount for area by admin
    public void addDiscount(Area area){
        area.setHasDiscount(true);
    }

    // removing discount to area by admin
    public void removeDiscount(Area area){
        area.setHasDiscount(false);
    }


    public ArrayList<Event> showEvents(int rideID){
        
        Ride r=null;
        for( Ride ride : RideListStorage.rides) {
            if(ride.getId()== rideID ){
                r=ride;
                
        }
        }
        return r.events;
      
    }

}
