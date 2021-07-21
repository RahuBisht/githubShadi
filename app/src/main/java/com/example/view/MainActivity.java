package com.example.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.adapter.CardAdapter;
import com.example.baseModels.BaseBindingActivity;

import com.example.dataBase.RoomDB;
import com.example.dataBase.UserMainData;
import com.example.models.Result;


import com.example.mysampleshadi.BR;
import com.example.mysampleshadi.R;
import com.example.mysampleshadi.databinding.ActivityMain2Binding;
import com.example.networking.apistatus.APIStatus;
import com.example.networking.responseHandler.BoxNumberBaseResponseModel;
import com.example.utils.NetworkConnectivity;
import com.example.viewModels.CardViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseBindingActivity<ActivityMain2Binding> implements CardAdapter.GenreListener {
    private CardViewModel cardViewModel;
    private CardAdapter cardAdapter;
    private RoomDB roomDB;
    @Override
    public ActivityMain2Binding inflateBindingLayout(@NonNull LayoutInflater inflater) {
        return ActivityMain2Binding.inflate(inflater);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardViewModel = ViewModelProviders.of(MainActivity.this).get(CardViewModel.class);
        getBinding().setVariable(BR.cardViewModel, cardViewModel);
        roomDB = RoomDB.getInstance(MainActivity.this);
        connectionObserver();
        Log.e("MainonCreate","MainonCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainonCreate","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainonCreate","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainonCreate","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainonCreate","onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainonCreate","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MainonCreate","onRestart");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e("MainonCreate","onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("MainonCreate","onRestoreInstanceState");
    }

    private void connectionObserver() {
        if (NetworkConnectivity.isOnline(this)) {
            connectionValidation(true);
        } else {
            connectionValidation(false);
        }
    }
    private void connectionValidation(Boolean aBoolean) {
        if (aBoolean) {
            UIinitialization();
        } else {
            noConnectionLayout();
        }
    }
    private void UIinitialization() {
        getCardData();
    }
    private void noConnectionLayout() {
        if (roomDB.roomDao().getAllData() != null && !roomDB.roomDao().getAllData().isEmpty()) {
            setRecyclerProperties(roomDB.roomDao().getAllData());
        } else {
            showToast("No Internet Connection");
        }
    }

    private void getCardData() {
        if (NetworkConnectivity.isOnline(this)) {
            showLoading(getBinding().progressBar, true);
            cardViewModel.getUserData().observe(this, new Observer<BoxNumberBaseResponseModel>() {
                        @Override
                        public void onChanged(BoxNumberBaseResponseModel response) {
                            if (response != null) {
                                if (response.getStatus().equalsIgnoreCase(APIStatus.START.name())) {

                                } else if (response.getStatus().equalsIgnoreCase(APIStatus.SUCCESS.name())) {
                                    if (response.getBaseCategoriesList().getResults() != null) {
                                        List<Result> userModel = (response.getBaseCategoriesList().getResults());
                                        List<UserMainData> userMainData = new ArrayList<>();
                                        for (int i = 0; i < userModel.size(); i++) {
                                            UserMainData userMainDataModel = new UserMainData();
                                            String name = userModel.get(i).getName().getFirst() + " " + userModel.get(i).getName().getLast();
                                            String uuid = userModel.get(i).getLogin().getUuid();
                                            String image = userModel.get(i).getPicture().getMedium();
                                            String email = userModel.get(i).getEmail();
                                            String phone = userModel.get(i).getPhone();
                                            userMainDataModel.setName(name);
                                            userMainDataModel.setUid(uuid);
                                            userMainDataModel.setImage(image);
                                            userMainDataModel.setEmail(email);
                                            userMainDataModel.setPhone(phone);
                                            userMainData.add(userMainDataModel);
                                        }
                                        roomDB.roomDao().insert(userMainData);
                                        setRecyclerProperties(roomDB.roomDao().getAllData());
                                        dismissLoading(getBinding().progressBar);

                                    } else {
                                        dismissLoading(getBinding().progressBar);
                                        showToast(getResources().getString(R.string.some_thing_wrong));
                                    }


                                } else if (response.getStatus().equalsIgnoreCase(APIStatus.FAILURE.name())) {
                                    dismissLoading(getBinding().progressBar);
                                    // in this line i want to show Internal server error but api is giving response some time ,& some time not &
                                    // i done  work on first actvity like splash, so for getting data iforcefyuuly call this api, but  in real time it sgoud not be done
                                    getCardData();


                                } else if (response.getStatus().equalsIgnoreCase(APIStatus.ERROR.name())) {
                                    dismissLoading(getBinding().progressBar);
                                    // in this line i want to show Internal server error but api is giving response some time ,& some time not &
                                    // i done  work on first actvity like splash, so for getting data iforcefyuuly call this api, but  in real time it sgoud not be done
                                    getCardData();
                                }

                            }
                        }
                    }
            );
        } else {
            connectionObserver();
        }

    }

    public void showToast(String message) {
        Snackbar snackbar = Snackbar.make(getBinding().rootView, message, 4000);
        snackbar.setBackgroundTint(Color.BLACK);
        snackbar.setTextColor(Color.WHITE);
        View snackbarView = snackbar.getView();
        TextView snackTextView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        snackTextView.setMaxLines(3);
        snackbar.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private void setRecyclerProperties(List<UserMainData> userMainData1) {
        cardAdapter = new CardAdapter(MainActivity.this, userMainData1, (CardAdapter.GenreListener) this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        getBinding().genreRecyclerView.setLayoutManager(mLayoutManager);
        getBinding().genreRecyclerView.setItemAnimator(new DefaultItemAnimator());
        getBinding().genreRecyclerView.setAdapter(cardAdapter);

    }

    @Override
    public void setAccept( String Acceptname) {
        showToast(Acceptname+" Request is Accepted");
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        this.startActivity(intent);


    }

    @Override
    public void setDecline(String declineName) {
        showToast(declineName+" Request is Declined");

    }

   /* public void configureThumbnailScrubber(BaseVideoView brightcoveVideoView) {
        Log.v(TAG, "Thumbnail Scrubbing is enabled, setting up the PreviewThumbnailController");
        ThumbnailComponent thumbnailComponent = new ThumbnailComponent(brightcoveVideoView);
        thumbnailComponent.setupPreviewThumbnailController();
    }*/
}