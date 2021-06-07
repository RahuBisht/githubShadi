package com.example.networking.apicallback;


import com.example.networking.errorNodel.ApiErrorModel;

public interface ApiResponseModel<T> {
    void onStart();

    default void onSuccess(T response){

    }
    default void onSuccess(){

    }
    default void onError(ApiErrorModel apiError){

    }

    default void onFailure(ApiErrorModel httpError){

    }
}