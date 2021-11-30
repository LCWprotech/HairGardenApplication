package com.LCWprotech.hairgardenapplication.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.LCWprotech.hairgardenapplication.R;

public class AboutUs extends AppCompatActivity {
    ImageView BtnMap,BtnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setTitle("About Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BtnMap = (ImageView) findViewById(R.id.btnMap);
        BtnCall = (ImageView) findViewById(R.id.btnCall);

        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://maps.app.goo.gl/v6ZRgMVwiLem8LLm9"));
                startActivity(intent);
            }
        });

        BtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:0194809847");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });
    }
}