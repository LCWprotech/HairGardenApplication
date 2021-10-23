package com.LCWprotech.hairgardenapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choices extends AppCompatActivity {

    Button Admin,Customer,Staff;
    Intent intent;
    String type;
    ConstraintLayout bgimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choices);



        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();

        Admin = (Button)findViewById(R.id.admin);
        Staff = (Button)findViewById(R.id.staff);
        Customer = (Button)findViewById(R.id.customer);


        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Email")){
                    Intent loginemail  = new Intent(Choices.this,AdminLogin.class);
                    startActivity(loginemail);
                    finish();
                }
                if(type.equals("SignUp")){
                    Intent Register  = new Intent(Choices.this,AdminRegistration.class);
                    startActivity(Register);
                }
            }
        });

        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type.equals("Email")){
                    Intent loginemailcust  = new Intent(Choices.this,Login.class);
                    startActivity(loginemailcust);
                    finish();
                }
                if(type.equals("SignUp")){
                    Intent Registercust  = new Intent(Choices.this,Registration.class);
                    startActivity(Registercust);
                }

            }
        });

        Staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(type.equals("Email")){
                    Intent loginemail = new Intent(Choices.this,StaffLogin.class);
                    startActivity(loginemail);
                    finish();
                }
                if(type.equals("SignUp")){
                    Intent Registerdelivery  = new Intent(Choices.this,StaffRegistration.class);
                    startActivity(Registerdelivery);
                }

            }
        });

    }
}