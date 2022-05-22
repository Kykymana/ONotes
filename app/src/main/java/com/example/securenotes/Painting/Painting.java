package com.example.securenotes.Painting;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.os.Bundle;

public class Painting extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyDraw(this));
    }
}