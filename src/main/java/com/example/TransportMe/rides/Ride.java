package com.example.TransportMe.rides;
import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.example.TransportMe.users_pack.Client;
import com.example.TransportMe.users_pack.Driver;
import java.util.List;

import com.example.TransportMe.Events.Event;

public class Ride {
    private static int count = 0;
    private Client client;
    private int id;
    private Area source;
    private Area destination;
    private UserStorage userStorage = new UserListStorage();
    private String rideDate;
    private RidePrice finalPrice;
    public List<Offer> offers;
    private int numberOfPassengers;

    public List<Event> events;

    public Ride(Client client, Area source, Area destination, String rideDate, int numberOfPassengers) {
        this.client = client;
        this.source = source;
        this.destination = destination;
        this.rideDate = rideDate;
        this.numberOfPassengers = numberOfPassengers;
        count++;
        this.id = count;
    }

    public int getId() {
        return id;
    }

    public Area getSource() {
        return source;
    }

    public void getRideInfo() {
        System.out.println("Ride id is" + id);
        System.out.println("Ride source area's name is" + source);
        System.out.println("Ride destination area's name is" + destination);
        System.out.println("Client's name is" + client.getUsername());
        System.out.println("==============================");
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    // transformed storage system and added new requirement
    public boolean notifyDrivers(String source) {
        boolean found = false;
        for (Driver driver : userStorage.getRegisteredDrivers()) {
            for (Area area : driver.favAreas) {
                if (area.name.equals(source) && driver.getAvailableForHandlingRequests()) {
//                    driver.availableRides.add(this);
                    found = true;
                }
            }
        }
        return found;
    }

    public void setFinalPrice(RidePrice ridePrice) {
        if (!client.HadFirstRide()) {
            finalPrice = new TenPDiscount(ridePrice);
            finalPrice.setPrice(ridePrice.getPrice());
        }
        if(destination.hasDiscount){
            finalPrice = new TenPDiscount(ridePrice);
            finalPrice.setPrice(ridePrice.getPrice());
        }
        if(client.getBirthDate().equals(rideDate)){
            finalPrice = new TenPDiscount(ridePrice);
            finalPrice.setPrice(ridePrice.getPrice());
        }
        if (numberOfPassengers==2){
            finalPrice = new FivePDiscount(ridePrice);
            finalPrice.setPrice(ridePrice.getPrice());
        }
        if (rideDate.equals("friday")||rideDate.equals("saturday")){
            finalPrice = new FivePDiscount(ridePrice);
            finalPrice.setPrice(ridePrice.getPrice());
        }

    }
    // add each condition to apply discounts
}

