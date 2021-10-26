package com.LCWprotech.hairgardenapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button signinemail,signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        signinemail=(Button)findViewById(R.id.SignwithEmail);
        signup=(Button)findViewById(R.id.Signup);

        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signemail = new Intent(MainMenu.this,Login.class);
                signemail.putExtra("Home","Email");
                startActivity(signemail);
                finish();
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(MainMenu.this,Registration.class);
                signup.putExtra("Home","SignUp");
                startActivity(signup);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}