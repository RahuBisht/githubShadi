package com.example.networking.serviceLayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.models.UserModel;
import com.example.networking.apicallback.ApiResponseModel;
import com.example.networking.apiendpoints.ApiInterface;
import com.example.networking.apiendpoints.RequestConfig;
import com.example.networking.errorNodel.ApiErrorModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryServiceLayer {
    private static CategoryServiceLayer projectRepository;
    private static ApiInterface endpoint;

    public synchronized static CategoryServiceLayer getInstance() {
        if (projectRepository == null) {
            endpoint = RequestConfig.getClient().create(ApiInterface.class);
            projectRepository = new CategoryServiceLayer();
        }
        return projectRepository;
    }



    public void getBoxData(ApiResponseModel listener) {
        CategoryServiceLayer.getInstance().getBoxSerialNumbers(listener);
    }

    public void getBoxSerialNumbers( ApiResponseModel listener) {

        endpoint.getBoxSerialNumbers(10).enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getResults() != null) {
                            UserModel baseCategory = response.body();
                            listener.onSuccess(baseCategory);
                        } else {
                            UserModel baseCategory = response.body();
                            listener.onSuccess(baseCategory);
                        }
                    } else {
                        ApiErrorModel errorModel = new ApiErrorModel(response.code(), response.message());
                        listener.onError(errorModel);
                    }

                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    ApiErrorModel errorModel = new ApiErrorModel(500, t.getMessage());
                    listener.onFailure(errorModel);
                }
            });




    }







}
