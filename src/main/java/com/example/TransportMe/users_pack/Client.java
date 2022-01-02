package com.example.TransportMe.users_pack;
import com.example.TransportMe.Events.Event;
import com.example.TransportMe.rides.Offer;
import com.example.TransportMe.rides.Ride;

public class Client extends User {
    private int id=0;
    private boolean hadFirstRide=false;
    Ride rideRequest=null;
  Event E;
    public Client(String userName, String mobileNumber,String password,String email,String birthDate){
        super(userName, mobileNumber,password,email,birthDate);
        this.id=count;
    }
    // Email attribute is optional
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
    public void viewOffers(){
        if(this.rideRequest==null)
        {
            System.out.println("no Rides yet.");
        }
        else if(rideRequest.offers==null||rideRequest.offers.size()==0)
        {
            System.out.println("no offers yet.");
        }

        else {
            for (Offer offer:rideRequest.offers){
                offer.getOfferInfo();
            }
        }
    }
    public void addRating(Driver dr , int r) {
        Rating rate = new Rating(this,r);
        dr.list.add(rate);
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
                E.acceptCaptainPrice(this.userName);
                return true;
            }
        }
        return false;
    }

    public void setHadFirstRide(boolean hadFirstRide) {
        this.hadFirstRide = hadFirstRide;
    }

    public boolean isHadFirstRide() {
        return hadFirstRide;
    }
}
