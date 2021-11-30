package com.LCWprotech.hairgardenapplication.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.LCWprotech.hairgardenapplication.R;
import com.LCWprotech.hairgardenapplication.Staff.ProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Customer extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        BottomNavigationView navigationView = findViewById(R.id.customer_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name = getIntent().getStringExtra("PAGE");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        loadcustomerfragment(new CustomerHomeFragment());
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.customerHome:
                fragment=new CustomerHomeFragment();
                break;
            case R.id.customerAppointment:
                fragment=new CustomerAppointmentFragment();
                break;
            case R.id.customerHair:
                fragment=new HairstyleFragment();
                break;
            case R.id.customerProduct:
                fragment=new ProductFragment();
                break;
            case R.id.customerAbout:
                fragment=new CustomerAboutFragment();
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