package com.example.TransportMe.Events;

import java.util.Date;

public class ArrivedToDestination extends Event{
    Date date = new Date();
    String captainName,userName;
    public void captainArrivedToDestination(String captainName,String userName) {
        this.eventName="Captain arrived to user destination";
        this.eventTime=date.toString();
        this.captainName=captainName;
        this.userName=userName;
      
    }
}
