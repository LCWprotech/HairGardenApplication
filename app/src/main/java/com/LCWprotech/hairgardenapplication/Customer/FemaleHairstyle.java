package com.LCWprotech.hairgardenapplication.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.LCWprotech.hairgardenapplication.R;

public class FemaleHairstyle extends AppCompatActivity {
    CardView cvFHair1, cvFHair2, cvFHair3, cvFHair4, cvFHair5, cvFHair6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female_hairstyle);
        setTitle("Female Hairstyle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cvFHair1 = findViewById(R.id.cardFemaleHair1);
        cvFHair2 = findViewById(R.id.cardFemaleHair2);
        cvFHair3 = findViewById(R.id.cardFemaleHair3);
        cvFHair4 = findViewById(R.id.cardFemaleHair4);
        cvFHair5 = findViewById(R.id.cardFemaleHair5);
        cvFHair6 = findViewById(R.id.cardFemaleHair6);

        cvFHair1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FemaleHairstyle.this, FemaleHair1.class);
                startActivity(intent);
            }
        });

        cvFHair2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FemaleHairstyle.this, FemaleHair2.class);
                startActivity(intent);
            }
        });

        cvFHair3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FemaleHairstyle.this, FemaleHair3.class);
                startActivity(intent);
            }
        });

        cvFHair4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FemaleHairstyle.this, FemaleHair4.class);
                startActivity(intent);
            }
        });

        cvFHair5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FemaleHairstyle.this, FemaleHair5.class);
                startActivity(intent);
            }
        });

        cvFHair6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FemaleHairstyle.this, FemaleHair6.class);
                startActivity(intent);
            }
        });
    }
}