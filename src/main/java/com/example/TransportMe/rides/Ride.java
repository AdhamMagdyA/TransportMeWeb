package com.example.TransportMe.rides;
import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.example.TransportMe.users_pack.Client;
import com.example.TransportMe.users_pack.Driver;
import java.util.List;

import com.example.TransportMe.Events.Event;

public class Ride {
    private static int count=0;
    private Client client;
    private int id;
    private String source;
    private String destination;
    private UserStorage userStorage = new UserListStorage();
    public List<Offer> offers;
    private int numberOfPassengers;

    public Event event;

    public Ride(Client client,String source,String destination,int numberOfPassengers) {
        this.client =client;
        this.source=source;
        this.destination=destination;
        this.numberOfPassengers = numberOfPassengers;
        this.numberOfPassengers = numberOfPassengers;
        count++;
        this.id=count;
    }
    public int getId() {
        return id;
    }
    public String getSource() {
        return source;
    }
    public void getRideInfo(){
        System.out.println("Ride id is"+id);
        System.out.println("Ride source area's name is"+source);
        System.out.println("Ride destination area's name is"+destination);
        System.out.println("Client's name is"+client.getUsername());
        System.out.println("==============================");
    }
    public void addOffer(Offer offer){
        offers.add(offer);
        System.out.println("Offer added successfully");
    }

    // transformed storage system and added new requirement
    public boolean notifyDrivers(String source){
        boolean found=false;
        for (Driver driver : userStorage.getRegisteredDrivers()){
            for (String area : driver.favAreas){
                if ( area.equals(source) && driver.getAvailableForHandlingRequests() ){
                    driver.availableRides.add(this);
                    found=true;
                }
            }
        }
        return found;
    }

}