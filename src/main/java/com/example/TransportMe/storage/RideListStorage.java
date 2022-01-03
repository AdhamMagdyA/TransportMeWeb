
package com.example.TransportMe.storage;
import com.example.TransportMe.rides.Ride;
import com.example.TransportMe.rides.Offer;
import com.example.TransportMe.users_pack.Client;

import java.util.ArrayList;


public class RideListStorage extends RideStorage{
    public static ArrayList<Ride> rides=new ArrayList<>() ;
    public static ArrayList<Ride> ridesEvents=new ArrayList<>();
    @Override
    public boolean addRideRequest(Ride ride) {
        rides.add( ride );
        return true;
    }

    @Override
    public boolean removeRideRequest(Ride ride) {
        rides.remove(ride.getId());
        return true;
    }

    @Override
    public boolean addRideOffer(Offer offer,int rideId) {
        for (Ride ride : rides){
            if(ride.getId() == rideId) {
                ride.addOffer(offer);
                return true;
            }
        }
        return false;
    }

    @Override
    public  ArrayList<Ride> getRides() {
        return rides;
    }

    @Override
    public ArrayList<Ride> getRidesEvents() {
        return null;
    }


}
