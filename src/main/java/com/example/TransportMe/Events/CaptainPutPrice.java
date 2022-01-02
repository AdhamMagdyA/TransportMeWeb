package com.example.TransportMe.Events;

import java.util.Date;

public class CaptainPutPrice extends Event {
    public String captainName;
    double price;
    Date date = new Date();
    public void setRidePrice(String captainName,double price) {
        this.eventName="Captain put a price to the ride";
        this.eventTime=date.toString();
        this.captainName=captainName;
        this.price=price;
       
      }
}
