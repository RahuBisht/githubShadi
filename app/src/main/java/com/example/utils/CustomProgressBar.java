package com.example.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.example.mysampleshadi.R;


public class CustomProgressBar extends ProgressBar {
    public CustomProgressBar(Context context) {
        super(context);
        this.setIndeterminate(true);
        this.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.MULTIPLY);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.MULTIPLY);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
