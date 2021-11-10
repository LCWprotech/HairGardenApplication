package com.LCWprotech.hairgardenapplication.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.LCWprotech.hairgardenapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Admin extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        BottomNavigationView navigationView = findViewById(R.id.admin_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name = getIntent().getStringExtra("PAGE");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        loadadminfragment(new AdminHomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.adminHome:
                fragment=new AdminHomeFragment();
                break;
            case R.id.adminAppointment:
                fragment=new AdminAppointmentFragment();
                break;
            case R.id.adminHair:
                fragment=new AdminHairstyleFragment();
                break;
            case R.id.adminProduct:
                fragment=new AdminProductFragment();
                break;
        }
        return loadadminfragment(fragment);
    }

    private boolean loadadminfragment(Fragment fragment) {

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
        return false;
    }
}