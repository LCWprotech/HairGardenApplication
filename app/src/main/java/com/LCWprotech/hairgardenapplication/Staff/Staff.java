package com.LCWprotech.hairgardenapplication.Staff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.LCWprotech.hairgardenapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Staff extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        BottomNavigationView navigationView = findViewById(R.id.staff_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name = getIntent().getStringExtra("PAGE");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        /*if(name!=null){
            if(name.equalsIgnoreCase("Orderpage")){
                loadadminfragment(new ChefPendingOrderFragment());
            }else if(name.equalsIgnoreCase("Confirmpage")){
                loadadminfragment(new ChefOrderFragment());
            }else if(name.equalsIgnoreCase("AcceptOrderpage")){
                loadadminfragment(new ChefOrderFragment());
            }else if(name.equalsIgnoreCase("Deliveredpage")){
                loadadminfragment(new ChefOrderFragment());
            }
        }else{
            loadadminfragment(new AdminHomeFragment());
        }*/
        loadstafffragment(new StaffHomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.staffHome:
                fragment=new StaffHomeFragment();
                break;
            case R.id.staffAppointment:
                fragment=new StaffAppointmentFragment();
                break;
            case R.id.staffHair:
                fragment=new StaffHairstyleFragment();
                break;
            case R.id.staffProduct:
                fragment=new StaffProductFragment();
                break;
        }
        return loadstafffragment(fragment);
    }

    private boolean loadstafffragment(Fragment fragment) {

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
        return false;
    }
}