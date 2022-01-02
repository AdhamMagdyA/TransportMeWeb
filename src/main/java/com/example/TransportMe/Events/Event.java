package com.example.TransportMe.Events;

import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.example.TransportMe.users_pack.Client;
import com.example.TransportMe.users_pack.Driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

    public String eventName1;
    public String eventName2;
    public String eventName3;
    public String eventName4;
    Date date = new Date();
    public String eventTime1 ;
    public String eventTime2 ;
    public String eventTime3 ;
    public String eventTime4 ;

    public String userName;
    public String captainName;
    double price;


   public ArrayList<String> Event1 = new ArrayList<String>();
   public ArrayList<String> Event2 = new ArrayList<String>();
   public ArrayList<String> Event3 = new ArrayList<String>();
   public ArrayList<String> Event4 = new ArrayList<String>();


  //1-
    public void setRidePrice(String captainName,double price) {
      this.eventName1="Captain put a price to the ride";
      this.eventTime1=date.toString();
      this.captainName=captainName;
      this.price=price;
      Event1.add(eventName1);
      Event1.add(eventTime1);
      Event1.add(captainName);
      String priceParam=String.valueOf(price)  ;
      Event1.add(priceParam);
    }

    public ArrayList<String> getEvent1(){
        return Event1;
    }
  //2-
    public void acceptCaptainPrice(String userName) {
        this.eventName2="User accepts the captain price";
      this.eventTime2=date.toString();
      this.userName=userName;
      Event2.add(eventName2);
      Event2.add(eventTime2);
      Event2.add(userName);
    }
    public ArrayList<String> getEvent2(){
        return Event2;
    }
  //3-  
    public void captainArrivedToLocation(String captainName,String userName) {
        this.eventName3="Captain arrived to user location";
        this.eventTime3=date.toString();
        this.captainName=captainName;
        this.userName=userName;
        Event3.add(eventName3);
        Event3.add(eventTime3);
        Event3.add(captainName);
        Event1.add(userName);
    }
    public ArrayList<String> getEvent3(){
        return Event3;
    }
   //4- 
   public void captainArrivedToDestination(String captainName,String userName) {
    this.eventName4="Captain arrived to user destination";
    this.eventTime4=date.toString();
    this.captainName=captainName;
    this.userName=userName;
    Event4.add(eventName4);
    Event4.add(eventTime4);
    Event4.add(captainName);
    Event4.add(userName);
}
public ArrayList<String> getEvent4(){
    return Event4;
}
  
}
