package com.LCWprotech.hairgardenapplication.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.LCWprotech.hairgardenapplication.R;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}