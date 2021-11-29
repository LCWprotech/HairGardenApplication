package com.LCWprotech.hairgardenapplication.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.LCWprotech.hairgardenapplication.R;

public class MaleHairstyle extends AppCompatActivity {
    CardView cvHair1, cvHair2, cvHair3, cvHair4, cvHair5, cvHair6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_hairstyle);

        cvHair1 = findViewById(R.id.cardMaleHair1);
        cvHair2 = findViewById(R.id.cardMaleHair2);
        cvHair3 = findViewById(R.id.cardMaleHair3);
        cvHair4 = findViewById(R.id.cardMaleHair4);
        cvHair5 = findViewById(R.id.cardMaleHair5);
        cvHair6 = findViewById(R.id.cardMaleHair6);

        cvHair1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaleHairstyle.this, MaleHair1.class);
                startActivity(intent);
            }
        });

    }
}