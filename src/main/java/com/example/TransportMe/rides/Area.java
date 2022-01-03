// if area = true then it has discount
package com.example.TransportMe.rides;

public class Area {
    public boolean hasDiscount = false;
    public String name;
    public Area(String n)
    {
        name = n;
    }

    public void setHasDiscount(boolean x){
        this.hasDiscount = x;
    }

}
