package com.LCWprotech.hairgardenapplication.Staff;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.LCWprotech.hairgardenapplication.Admin.AppointmentAdapter;
import com.LCWprotech.hairgardenapplication.Admin.AppointmentModel;
import com.LCWprotech.hairgardenapplication.Admin.SearchAppointment;
import com.LCWprotech.hairgardenapplication.Customer.cusAppointmentAdapter;
import com.LCWprotech.hairgardenapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class StaffAppointmentFragment extends Fragment {

    RecyclerView recyclerView;
    FirebaseFirestore db;
    Button btnSearch;
    private cusAppointmentAdapter adapter;
    ArrayList<AppointmentModel> AppointList = new ArrayList<>();;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_staff_appointment,null);
        getActivity().setTitle("Staff Appointment");
        recyclerView = v.findViewById(R.id.LvAppointment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        StaffAppointment();
        btnSearch = v.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StaffSearchAppointment.class);
                startActivity(intent);
            }
        });

        return v;
    }

    private void StaffAppointment() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("AppointmentInfo");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AppointList.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()) {
                    for (DataSnapshot snapshot2 : snapshot1.getChildren()) {
                        AppointmentModel appointmentModel = snapshot2.getValue(AppointmentModel.class);
                        AppointList.add(appointmentModel);
                    }
                }
                adapter = new cusAppointmentAdapter(getContext(),AppointList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}