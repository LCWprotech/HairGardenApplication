package com.LCWprotech.hairgardenapplication.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.LCWprotech.hairgardenapplication.R;

public class MaleHairstyle extends AppCompatActivity {
    CardView cvMHair1, cvMHair2, cvMHair3, cvMHair4, cvMHair5, cvMHair6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_hairstyle);
        setTitle("Male Hairstyle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cvMHair1 = findViewById(R.id.cardMaleHair1);
        cvMHair2 = findViewById(R.id.cardMaleHair2);
        cvMHair3 = findViewById(R.id.cardMaleHair3);
        cvMHair4 = findViewById(R.id.cardMaleHair4);
        cvMHair5 = findViewById(R.id.cardMaleHair5);
        cvMHair6 = findViewById(R.id.cardMaleHair6);

        cvMHair1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaleHairstyle.this, MaleHair1.class);
                startActivity(intent);
            }
        });

        cvMHair2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaleHairstyle.this, MaleHair2.class);
                startActivity(intent);
            }
        });

        cvMHair3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaleHairstyle.this, MaleHair3.class);
                startActivity(intent);
            }
        });

        cvMHair4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaleHairstyle.this, MaleHair4.class);
                startActivity(intent);
            }
        });

        cvMHair5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaleHairstyle.this, MaleHair5.class);
                startActivity(intent);
            }
        });

        cvMHair6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaleHairstyle.this, MaleHair6.class);
                startActivity(intent);
            }
        });

    }
}