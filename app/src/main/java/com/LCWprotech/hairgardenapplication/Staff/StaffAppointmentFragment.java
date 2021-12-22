package com.LCWprotech.hairgardenapplication.Staff;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.LCWprotech.hairgardenapplication.Admin.AppointmentAdapter;
import com.LCWprotech.hairgardenapplication.Admin.AppointmentModel;
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
    TextInputEditText date_in;
    TextInputEditText time_in;
    FirebaseFirestore db;
    private cusAppointmentAdapter adapter;
    ArrayList<AppointmentModel> AppointList = new ArrayList<>();;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_staff_appointment,null);
        getActivity().setTitle("Staff Appointment");
        date_in=v.findViewById(R.id.date_input);
        time_in=v.findViewById(R.id.time_input);
        recyclerView = v.findViewById(R.id.LvAppointment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        StaffAppointment();

        date_in.setInputType(InputType.TYPE_NULL);
        time_in.setInputType(InputType.TYPE_NULL);

        date_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_in);
            }
        });
        time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(time_in);
            }
        });
        return v;
    }
    private void showDateDialog(final EditText date_in) {
        final Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDatePicker = new DatePickerDialog(
                getContext(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker,
                                  int selectedyear, int selectedmonth,
                                  int selectedday) {

                mcurrentDate.set(Calendar.YEAR, selectedyear);
                mcurrentDate.set(Calendar.MONTH, selectedmonth);
                mcurrentDate.set(Calendar.DAY_OF_MONTH,
                        selectedday);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

                date_in.setText(simpleDateFormat.format(mcurrentDate
                        .getTime()));
            }
        }, mYear, mMonth, mDay);
        mDatePicker.show();

    }
    private void showTimeDialog(final EditText time_in) {
        final Calendar calendar=Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                time_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new TimePickerDialog(getContext(),timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
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