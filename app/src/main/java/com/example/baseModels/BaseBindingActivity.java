package com.example.baseModels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;


public abstract class BaseBindingActivity<B extends ViewDataBinding> extends BaseActivity {

    private B mBinding;

    public abstract B inflateBindingLayout(@NonNull LayoutInflater inflater);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = setupBinding(getLayoutInflater());
        setContentView(mBinding.getRoot());



    }


    public B getBinding() {
        return mBinding;
    }


    private B setupBinding(@NonNull LayoutInflater inflater) {
        return inflateBindingLayout(inflater);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
