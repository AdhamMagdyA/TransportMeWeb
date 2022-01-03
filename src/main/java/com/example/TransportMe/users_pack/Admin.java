package com.example.TransportMe.users_pack;
import java.util.ArrayList;
import java.util.List;

import com.example.TransportMe.Events.Event;
import com.example.TransportMe.rides.Area;
import com.example.TransportMe.rides.Ride;
import com.example.TransportMe.storage.RideListStorage;
import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;

public class Admin extends User{

    UserStorage userStorage = new UserListStorage();

    public Admin(String userName, String mobileNumber,String password){
        super(userName, mobileNumber,password);
    }
    //transformed to use UserStorage instead of TransportMe
    public void addPendingRegistrations(Driver driver){
        userStorage.addPendingRegistration(driver);
    }

    /* commented until transformed to use database abstraction*/
    public static void suspendUser (String username){
         for( User user : UserListStorage.registeredUsers ) {
             if(user.getUsername().equals(username) ){
                UserListStorage.registeredUsers.remove(user);
                UserListStorage.suspendedUsers.add(user);
         }
         }
    }
    public static void unSuspendUser(String username) {
        for (User user : UserListStorage.registeredUsers) {
            if (user.getUsername().equals(username)) {
                UserListStorage.registeredUsers.add(user);
                UserListStorage.suspendedUsers.remove(user);
            }

        }
    }

    // transformed to use UserStorage instead of TransportMe
    public void listPendingRegistrations(){
        for (Driver driver : userStorage.getPendingRegistrations()){
            System.out.println("driver name is :" +driver.getUsername());
            System.out.println("driver national id is :" +driver.getNationalID());
            System.out.println("driver driving licence is :" +driver.getDrivingLicense());
            System.out.println("=================================");
        }
    }
    // transformed to use UserStorage instead of TransportMe
    public boolean acceptRegistration(String userName){
        for( User user : userStorage.getPendingRegistrations() ){
            if( user.getUsername().equals(userName) ){
                // remove from pending registration
                userStorage.removePendingRegistration( (Driver) user );
                // add registration to database
                userStorage.addRegisteredUser(user);
                return true;
            }
        }
//        System.out.println("User not found");
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

    // no need for this function for now
    /* public boolean rejectRegistration(String userName){
        for( User user : TransportMe.pendingRegistrations ){
            if( user.getUsername().equals(userName) ){
                TransportMe.pendingRegistrations.remove(user);
                System.out.println("Registration rejected successfully");
                return true;
            }
        }
        System.out.println("User not found");
        return false;
    }*/
}
