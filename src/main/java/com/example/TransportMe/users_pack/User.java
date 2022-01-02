package com.example.TransportMe.users_pack;


public class User {
    String userName,mobileNumber,password,email,birthDate;
    public static int count=0;


    public User(String userName, String mobileNumber, String password, String email, String birthDate){
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.email = email;
        this.birthDate=birthDate;
        count++;
    }
    public User(){
        this.userName = "";
        this.mobileNumber = "";
        this.password = "";
        this.email = "email";
        this.birthDate="birthDate";
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
