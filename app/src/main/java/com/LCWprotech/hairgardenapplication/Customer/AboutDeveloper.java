package com.LCWprotech.hairgardenapplication.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.LCWprotech.hairgardenapplication.R;

public class AboutDeveloper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);
        setTitle("About Developer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}