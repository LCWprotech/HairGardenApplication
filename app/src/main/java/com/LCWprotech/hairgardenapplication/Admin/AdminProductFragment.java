package com.LCWprotech.hairgardenapplication.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.LCWprotech.hairgardenapplication.Customer.PrivacyPolicy;
import com.LCWprotech.hairgardenapplication.R;

public class AdminProductFragment extends Fragment {

    Button BtnPrivacy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_admin_product,null);
        getActivity().setTitle("Admin Product");


        BtnPrivacy = (Button) v.findViewById(R.id.btPrivacy);

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