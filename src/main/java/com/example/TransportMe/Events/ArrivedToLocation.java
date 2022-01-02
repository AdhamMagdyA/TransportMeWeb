package com.example.TransportMe.Events;


import java.util.Date;

    
public class ArrivedToLocation extends Event{
    Date date = new Date();
    String captainName,userName;
    public void captainArrivedToLocation(String captainName,String userName) {
        this.eventName="Captain arrived to user location";
        this.eventTime=date.toString();
        this.captainName=captainName;
        this.userName=userName;
        
    }
}
