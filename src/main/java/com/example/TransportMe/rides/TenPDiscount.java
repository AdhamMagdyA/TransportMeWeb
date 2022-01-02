package com.example.TransportMe.rides;

import com.example.TransportMe.users_pack.Driver;

public class TenPDiscount extends Discount {

    RidePrice ridePrice;
    public TenPDiscount(RidePrice ridePrice){
        this.ridePrice=ridePrice;
    }
    @Override
    public void setPrice(double price) {
        ridePrice.price=ridePrice.getPrice()*0.1;
    }
}
