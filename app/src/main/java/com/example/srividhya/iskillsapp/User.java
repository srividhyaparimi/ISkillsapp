package com.example.srividhya.iskillsapp;

/**
 * Created by srividhya on 7/14/2017.
 */

public class User {

    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private String state;
    private String country;
    private int postalcode;
    private int phonenumber;
    private int id;

    public User(String firstname,String lastname,String address,String city,String state,String country,int postalcode,int phonenumber){
        this.firstname=firstname;
        this.lastname=lastname;
        this.address=address;
        this.city=city;
        this.state=state;
        this.country=country;
        this.postalcode=postalcode;
        this.phonenumber=phonenumber;

    }

    public Integer getId(int id) {
        return id;
    }
}
