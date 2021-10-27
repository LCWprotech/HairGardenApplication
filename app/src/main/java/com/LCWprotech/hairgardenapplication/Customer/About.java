package com.LCWprotech.hairgardenapplication.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.LCWprotech.hairgardenapplication.ForgotPassword;
import com.LCWprotech.hairgardenapplication.Login;
import com.LCWprotech.hairgardenapplication.MainMenu;
import com.LCWprotech.hairgardenapplication.R;
import com.LCWprotech.hairgardenapplication.Registration;

public class About extends AppCompatActivity {
    TextView txtPrivacy;
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycler_view);
        txtPrivacy = (TextView) findViewById(R.id.text2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new MainAdapter(this, Customer.arrayList));

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
/*
        txtPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, PrivacyPolicy.class));
                finish();
            }
        });*/

    }
    protected void onPause(){
        super.onPause();
        Customer.closeDrawer(drawerLayout);
    }
/*
    public void Privacy(View v){
        Intent z = new Intent(activity, Appointment.class);
        activity.startActivity(z);
        finish();
    }*/
}