package com.example.networking.apiendpoints;


import com.example.models.UserModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/")
    Call<UserModel> getBoxSerialNumbers( @Query("results") Integer serialNumber);






}



