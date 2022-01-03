package com.example.TransportMe.users_pack;
import com.example.TransportMe.Events.Event;
import com.example.TransportMe.Events.UserAcceptPrice;
import com.example.TransportMe.rides.Offer;
import com.example.TransportMe.rides.Ride;

import java.util.List;

public class Client extends User {
    private int id=0;
    private boolean hadFirstRide=false;
    Ride rideRequest=null;
  
    public Client(String userName, String mobileNumber,String password,String email,String birthDate){
        super(userName, mobileNumber,password,email,birthDate);
        this.id=count;
    }
    //  attribute(Email) is optional
    public Client(String userName, String mobileNumber,String password){
        super(userName, mobileNumber,password);
        this.id=count;

    }

    /* commented until transformed to use database abstraction
    public void requestRide(String source,String destination,int numberOfPassengers){
        Ride rideRequest = new Ride(this,source,destination,numberOfPassengers);
        boolean status;
        status=rideRequest.notifyDrivers(source);
        if (status){
            TransportMe.rides.add(rideRequest);
            this.rideRequest=rideRequest;
            System.out.println("Requested successfully");
        }
        else {
            System.out.println("No drivers in Your area");
        }
    }*/
    public List<Offer> viewOffers(){
        if(this.rideRequest==null)
        {
            return null;
//            System.out.println("no Rides yet.");
        }
        else if(rideRequest.offers==null||rideRequest.offers.size()==0)
        {
            return null;
//            System.out.println("no offers yet.");
        }

        return rideRequest.offers;

    }
    public boolean addRating(Driver dr , int r) {
        if (dr==null)
            return false;
        Rating rate = new Rating(this,r);
        dr.list.add(rate);
        return false;
    }

    public boolean acceptOffer(int id){
        for (Offer offer:rideRequest.offers){
            if (offer.getId() == id)
            {
                // means that the driver is now handling a request
                offer.getDriver().setAvailableForHandlingRequests(false);
                if (!hadFirstRide)
                    setHadFirstRide(true);
                // request resolved
                rideRequest = null;
                Event e =new UserAcceptPrice();
                ((UserAcceptPrice) e).acceptCaptainPrice(this.userName);
                rideRequest.events.add(e);
                
                return true;
            }
        }
        return false;
    }

    public void setHadFirstRide(boolean hadFirstRide) {
        this.hadFirstRide = hadFirstRide;
    }

    public boolean HadFirstRide() {
        return hadFirstRide;
    }
}
