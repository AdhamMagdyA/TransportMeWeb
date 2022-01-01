package com.example.TransportMe.users_pack;


public class User {
    String userName,mobileNumber,password,email;
    public static int count=0;
    public User(String userName, String mobileNumber,String password,String email){
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.email = email;
        count++;
    }
    
    public User(String userName, String mobileNumber,String password){
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.email = null;
    }

    public String getUsername(){
        return this.userName;
    }
    public String getPassword(){
        return this.password;
    }
}
