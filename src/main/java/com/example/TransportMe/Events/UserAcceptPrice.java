package com.example.TransportMe.Events;
import java.util.Date;
public class UserAcceptPrice extends Event {
    public String userName;
    Date date = new Date();
    public void acceptCaptainPrice(String userName) {
        this.eventName="User accepts the captain price";
      this.eventTime=date.toString();
      this.userName=userName;
      
    }
}
