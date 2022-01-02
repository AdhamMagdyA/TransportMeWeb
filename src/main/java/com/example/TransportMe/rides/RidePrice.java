package com.example.TransportMe.rides;

public abstract class RidePrice {
    public double price;
    public abstract void setPrice(double price);

    public double getPrice() {
        return price;
    }
}

abstract class Discount extends RidePrice {

    public abstract void setPrice(double price);


}
