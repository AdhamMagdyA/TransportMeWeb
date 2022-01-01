package com.example.TransportMe.users_pack;

public class Rating {
    public Client client;
    public int rating;


    public Rating(Client c ,int r)
    {
        client = c;
        rating = r;
    }

    public String tostring()
    {
        return "rate " + rating +"/n";

    }

}