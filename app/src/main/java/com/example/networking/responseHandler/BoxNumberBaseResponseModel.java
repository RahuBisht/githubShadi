package com.example.networking.responseHandler;

import com.example.models.UserModel;
import com.example.networking.errorNodel.ApiErrorModel;


public class BoxNumberBaseResponseModel {

    String status;

    UserModel baseCategoriesList;
    ApiErrorModel errorModel;

    public BoxNumberBaseResponseModel(String status, UserModel list, ApiErrorModel errorModel){
        this.status=status;
        this.baseCategoriesList=list;
        this.errorModel=errorModel;
    }



    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }



    public ApiErrorModel getErrorModel() {
        return errorModel;
    }

    public void setErrorModel(ApiErrorModel errorModel) {
        this.errorModel = errorModel;
    }

    public void setBaseCategoriesList(UserModel baseCategoriesList) {
        this.baseCategoriesList = baseCategoriesList;
    }

    public UserModel getBaseCategoriesList() {
        return baseCategoriesList;
    }
}
