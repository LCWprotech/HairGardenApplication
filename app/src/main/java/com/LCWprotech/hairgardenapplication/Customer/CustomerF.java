package com.LCWprotech.hairgardenapplication.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.LCWprotech.hairgardenapplication.Admin.AdminAppointmentFragment;
import com.LCWprotech.hairgardenapplication.Admin.AdminHairstyleFragment;
import com.LCWprotech.hairgardenapplication.Admin.AdminHomeFragment;
import com.LCWprotech.hairgardenapplication.Admin.AdminProductFragment;
import com.LCWprotech.hairgardenapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerF extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_f);
        BottomNavigationView navigationView = findViewById(R.id.customer_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name = getIntent().getStringExtra("PAGE");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        loadcustomerfragment(new CustomerHome());
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.customerHome:
                fragment=new CustomerHome();
                break;
            case R.id.customerAppointment:
                fragment=new CustomerAppointment();
                break;
            case R.id.customerHair:
                fragment=new CustomerHairStyle();
                break;
            case R.id.customerProduct:
                fragment=new CustomerProduct();
                break;
            case R.id.customerAbout:
                fragment=new CustomerAbout();
                break;
        }
        return loadcustomerfragment(fragment);
    }

    private boolean loadcustomerfragment(Fragment fragment) {

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
        return false;
    }
}