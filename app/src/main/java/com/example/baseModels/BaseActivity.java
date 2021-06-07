package com.example.baseModels;



import android.os.Bundle;

import android.view.View;

import android.view.WindowManager;



import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseActivity extends AppCompatActivity  {

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void showLoading(ConstraintLayout progressBar, boolean val) {
        if (val) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.bringToFront();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }


    public void dismissLoading(ConstraintLayout progressBar) {
        if (progressBar != null) {
            progressBar.setVisibility(View.INVISIBLE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onStart() {
        super.onStart();

    }



}
