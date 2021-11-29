package com.LCWprotech.hairgardenapplication.Customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.LCWprotech.hairgardenapplication.R;
public class CustomerHairstyleFragment extends Fragment {
    CardView cvMale, cvFemale;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_customer_hairstyle,null);
        getActivity().setTitle("Hairstyle");

        cvMale = v.findViewById(R.id.cardMaleHair);
        cvFemale = v.findViewById(R.id.cardFemaleHair);

        cvMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MaleHairstyle.class);
                startActivity(intent);
            }
        });

        cvFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FemaleHairstyle.class);
                startActivity(intent);
            }
        });


        return v;
    }
}