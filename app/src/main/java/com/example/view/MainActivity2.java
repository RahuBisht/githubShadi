package com.example.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.example.mysampleshadi.R;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity2","onCreate");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity2","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity2","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity2","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity2","onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity2","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MainActivity2","onRestart");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e("MainActivity2","onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("MainActivity2","onRestoreInstanceState");
    }

}