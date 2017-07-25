package com.example.srividhya.iskillsapp;

/**
 * Created by srividhya on 7/13/2017.
 */

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("/Customer/InsertCustomer")
    public void insertUser(
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("address") String address,
            @Field("city") String city,
            @Field("state") String state,
            @Field("country") String country,
            @Field("postalcode") String postalcode,
            @Field("phonenumber") String phonenumber,
            Callback<Response> callback);
}