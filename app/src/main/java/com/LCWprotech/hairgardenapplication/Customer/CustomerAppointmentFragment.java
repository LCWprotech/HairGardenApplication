package com.LCWprotech.hairgardenapplication.Customer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.LCWprotech.hairgardenapplication.R;

public class CustomerAppointmentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_customer_appointment,null);
        getActivity().setTitle("Appointment");
        return v;
    }
}