package com.LCWprotech.hairgardenapplication.Customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.LCWprotech.hairgardenapplication.R;

public class CustomerAboutFragment extends Fragment {

    Button BtnPrivacy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_customer_about,null);
        getActivity().setTitle("About Us");

        BtnPrivacy = (Button) v.findViewById(R.id.btnPrivacy);

        BtnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PrivacyPolicy.class);
                startActivity(intent);
            }
        });

        return v;
    }
}