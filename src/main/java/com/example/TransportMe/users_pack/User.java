package com.example.TransportMe.users_pack;

import com.example.TransportMe.storage.UserListStorage;
import com.example.TransportMe.storage.UserStorage;
import com.fasterxml.jackson.core.JsonEncoding;

public class User {
    private static final UserStorage userStorage = new UserListStorage();

    protected int id;
    protected String userName;
    protected String mobileNumber;
    protected String password;
    protected String email;
    protected String birthDate;

    // query for number of registered users
    public static int count = userStorage.getRegisteredUsers().size();


    public User(String userName, String mobileNumber, String password, String email, String birthDate){
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.email = email;
        this.birthDate=birthDate;
        this.id = ++count;
    }
    
    public User(String userName, String mobileNumber,String password){
       new User(userName,mobileNumber,password,null,null);
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }
}
