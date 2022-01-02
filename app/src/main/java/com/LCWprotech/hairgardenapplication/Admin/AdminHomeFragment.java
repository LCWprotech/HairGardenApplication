package com.LCWprotech.hairgardenapplication.Admin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.LCWprotech.hairgardenapplication.Customer.cusAppointmentAdapter;
import com.LCWprotech.hairgardenapplication.MainMenu;
import com.LCWprotech.hairgardenapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class AdminHomeFragment extends Fragment {
    TextInputEditText date_in;
    TextInputEditText time_in;
    TextInputEditText tname;
    private cusAppointmentAdapter adapter;
    RecyclerView recyclerView;
    Button btnBookAppointment;
    Spinner service;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth Fauth;
    Uri imageuri;
    StorageReference ref;
    AppointmentModel appointmentInfo;
    ArrayList<AppointmentModel> AppointList = new ArrayList<>();;
    String date, time, name, services, RandomUID, CusId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_admin_home,null);
        getActivity().setTitle("Admin Home");
        setHasOptionsMenu(true);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
        appointmentInfo = new AppointmentModel();
        date_in=v.findViewById(R.id.date_input);
        time_in=v.findViewById(R.id.time_input);
        tname = v.findViewById(R.id.name);
        service=v.findViewById(R.id.service);
        btnBookAppointment = v.findViewById(R.id.btnBookAppointment);

        date_in.setInputType(InputType.TYPE_NULL);
        time_in.setInputType(InputType.TYPE_NULL);
        Fauth = FirebaseAuth.getInstance();
        RandomUID = UUID.randomUUID().toString();
        ref = storageReference.child(RandomUID);
        CusId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        recyclerView = v.findViewById(R.id.LvAppointment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
        btnBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                date = date_in.getText().toString().trim();
                time = time_in.getText().toString().trim();
                name = tname.getText().toString().trim();
                services = service.getSelectedItem().toString().trim();
                RandomUID = UUID.randomUUID().toString();
                ref = storageReference.child(RandomUID);
                String CusId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if(isValid()){
                    addDatatoFirebase(date,time,name,services,RandomUID,CusId);
                }
            }
        });
        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int idd = item.getItemId();
        if(idd == R.id.LOGOUT){
            Logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Logout() {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), MainMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void showDateDialog(final EditText date_in) {
        final Calendar mcurrentDate = Calendar.getInstance();
        mcurrentDate.add(Calendar.DAY_OF_MONTH, 1);
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
        mDatePicker.getDatePicker().setMinDate(mcurrentDate.getTimeInMillis());
        mDatePicker.show();

    }
    private void showTimeDialog(final EditText time_in) {
        final Calendar calendar=Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int Minute = calendar.get(Calendar.MINUTE);;
        TimePickerDialog mTimePicker = new TimePickerDialog(
                getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay < 10) {
                    hourOfDay = 10;
                    minute = 0;
                }
                else if(hourOfDay > 18) {
                    hourOfDay = 19;
                    minute = 0;
                }
                time_in.setText(String.format("%02d:%02d",hourOfDay , minute));
            }
        },hour,Minute,true);
        mTimePicker.show();
    }

    private void addDatatoFirebase(String date,String time,String name,String services,String RandomUID,String CusId) {

        appointmentInfo.setDate(date);
        appointmentInfo.setTime(time);
        appointmentInfo.setName(name);
        appointmentInfo.setService(services);
        appointmentInfo.setRandomUID(RandomUID);
        appointmentInfo.setCusId(CusId);
        databaseReference = firebaseDatabase.getInstance().getReference("AppointmentInfo").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                databaseReference.setValue(appointmentInfo);

                Toast.makeText(getContext(), "Appointment has been saved!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Appointment cannot be saved!" + error, Toast.LENGTH_SHORT).show();
            }
        });

    }
    private boolean isValid() {

        date_in.setTextInputLayoutFocusedRectEnabled(false);
        date_in.setError("");
        time_in.setTextInputLayoutFocusedRectEnabled(false);
        time_in.setError("");
        tname.setTextInputLayoutFocusedRectEnabled(false);
        tname.setError("");

        boolean isValidDate = false,isValidTime = false,isValidName=false,isValid=false;
        if(TextUtils.isEmpty(date)){
            date_in.setTextInputLayoutFocusedRectEnabled(true);
            date_in.setError("Date is Required");
        }else{
            date_in.setError(null);
            isValidDate=true;
        }if(TextUtils.isEmpty(time)){
            time_in.setTextInputLayoutFocusedRectEnabled(true);
            time_in.setError("Time is Required");
        }else{
            time_in.setError(null);
            isValidTime=true;
        }
        if(TextUtils.isEmpty(name)){
            tname.setTextInputLayoutFocusedRectEnabled(true);
            tname.setError("Customer Name is Required");
        }else{
            tname.setError(null);
            isValidName=true;
        }
        isValid = (isValidDate  && isValidTime && isValidName)?true:false;
        return isValid;
    }
}