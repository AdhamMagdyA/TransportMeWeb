package com.example.TransportMe.storage;
import com.example.TransportMe.rides.Ride;
import com.example.TransportMe.rides.Offer;
import com.example.TransportMe.users_pack.Client;

import java.util.ArrayList;


public class RideListStorage extends RideStorage{
    public static ArrayList<Ride> rides=new ArrayList<>() ;
    public static ArrayList<Client> registeredClients=new ArrayList<>();
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


        return true;
    }

    @Override
    public ArrayList<Ride> getRide() {
        return null;
    }
    @Override
    public ArrayList<Ride> getRidesEvents() {

        return ridesEvents;
    }


}
