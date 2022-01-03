package com.example.TransportMe.users_pack;
import com.example.TransportMe.rides.Area;
import com.example.TransportMe.rides.Offer;
import com.example.TransportMe.rides.Ride;

import java.util.ArrayList;

import com.example.TransportMe.Events.ArrivedToDestination;
import com.example.TransportMe.Events.ArrivedToLocation;
import com.example.TransportMe.Events.CaptainPutPrice;
import com.example.TransportMe.Events.Event;
import com.example.TransportMe.storage.RideListStorage;

public class Driver extends User{
    private int id;
    private String drivingLicense, nationalID;
    public ArrayList<Area> favAreas = new ArrayList<Area>();
    Ride ride;
    // should deleted
    // public ArrayList<Ride> availableRides = new ArrayList<Ride>();
    public ArrayList<Rating> list = new ArrayList<Rating>();
    private boolean availableForHandlingRequests;
    private boolean arrivedLocation=false;
    private boolean arrivedDestination=false;
    
    
    public Driver(String userName, String mobileNumber,String password,String email,String drivingLicense, String nationalID,String birthDate){
        super(userName, mobileNumber,password,email,birthDate);
        this.drivingLicense=drivingLicense;
        this.nationalID=nationalID;
        this.id=count;
        this.availableForHandlingRequests = true;
    }

    public ArrayList<Ride> listRides(String sourceArea){
        ArrayList<Ride> rides=null;
        for (Ride ride: RideListStorage.rides){
            if(ride.getSource().equals(sourceArea)){
                rides.add(ride);
            }
        }
        return rides;
    }

    public boolean suggestPrice(double price,int rideId){
        for(Ride ride: RideListStorage.rides){
            if(ride.getId()==rideId){
                Offer offer = new Offer(ride,price,this);
                Event e=new CaptainPutPrice();
                ((CaptainPutPrice) e).setRidePrice(this.userName,price);
                ride.events.add(e);
               return  true;
            }
        }
        return  false;
    }

    public ArrayList<Rating> showRatingList() {

        return list ;
    }
    public double calculateAverageRating() {
        int sum = 0;
        for (Rating rating : list) {
            sum = sum + rating.rating;
        }
        return ((double) sum /list.size() );
    }
    public void getDriverInfo(){
        System.out.println("Driver Name is"+userName);
        System.out.println("Driver id is"+id);
        System.out.println("Driver total rating is"+calculateAverageRating());
        System.out.println("=================================");
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public String getNationalID() {
        return nationalID;
    }
    public void setFavAreas(String area){
        favAreas.add(new Area(area) );
    }

    public void arrivedToUserLocation(String userName){
        this.arrivedLocation=true;
        Event e= new ArrivedToLocation();
        ((ArrivedToLocation) e).captainArrivedToLocation(this.userName, userName) ;
        ride.events.add(e);
    }   
    public void arrivedToDestination(String userName){
        this.arrivedDestination=true;
        Event e= new ArrivedToDestination();
        ((ArrivedToDestination) e).captainArrivedToDestination(this.userName, userName);
        ride.events.add(e);
     }  

    public boolean getAvailableForHandlingRequests(){return this.availableForHandlingRequests;}

    public void setAvailableForHandlingRequests(boolean availableForHandlingRequests) {
        this.availableForHandlingRequests = availableForHandlingRequests;
    }
}
