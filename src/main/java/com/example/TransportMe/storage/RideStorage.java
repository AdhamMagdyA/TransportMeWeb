package com.example.TransportMe.storage;
import com.example.TransportMe.rides.Ride;
import com.example.TransportMe.rides.Offer;


import java.util.ArrayList;

public abstract class RideStorage {
    public abstract boolean addRideRequest( Ride ride );
    public abstract boolean removeRideRequest( Ride ride );
    public abstract boolean addRideOffer( Offer offer , int rideId );
    public abstract  ArrayList<Ride> getRides();
    public abstract ArrayList<Ride> getRidesEvents();


}
