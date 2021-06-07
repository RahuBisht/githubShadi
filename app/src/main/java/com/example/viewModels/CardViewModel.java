package com.example.viewModels;

import android.app.Application;
import android.hardware.usb.UsbRequest;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.models.UserModel;
import com.example.networking.apicallback.ApiResponseModel;
import com.example.networking.apistatus.APIStatus;
import com.example.networking.errorNodel.ApiErrorModel;
import com.example.networking.responseHandler.BoxNumberBaseResponseModel;
import com.example.networking.serviceLayer.CategoryServiceLayer;

public class CardViewModel extends CustomObservableViewModel {
    /**
     * Instantiates a new Custom observable view model.
     *
     * @param application the application
     */
    public CardViewModel(@NonNull Application application) {
        super(application);
    }



    public LiveData<BoxNumberBaseResponseModel> getUserData() {
        MutableLiveData liveData = new MutableLiveData();
        CategoryServiceLayer.getInstance().getBoxData(new ApiResponseModel<UserModel>() {
            @Override
            public void onStart() {
                liveData.postValue(new BoxNumberBaseResponseModel(APIStatus.START.name(), null, null));
            }

            @Override
            public void onSuccess(UserModel response) {

                liveData.postValue(new BoxNumberBaseResponseModel(APIStatus.SUCCESS.name(), response, null));
            }


            @Override
            public void onError(ApiErrorModel apiError) {
                liveData.postValue(new BoxNumberBaseResponseModel(APIStatus.ERROR.name(), null, apiError));
            }

            @Override
            public void onFailure(ApiErrorModel httpError) {
                liveData.postValue(new BoxNumberBaseResponseModel(APIStatus.FAILURE.name(), null, httpError));
            }
        });

        return liveData;
    }

    /// https://randomuser.me/api/?results=10
}
