package com.LCWprotech.hairgardenapplication.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.LCWprotech.hairgardenapplication.Customer.AppointmentInfo;
import com.LCWprotech.hairgardenapplication.R;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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
import java.util.List;

public class UpdateDeleteAppointment extends AppCompatActivity {
    TextInputEditText date_in;
    TextInputEditText time_in;
    TextInputEditText tname;
    Spinner service;
    private Uri mCropimageuri;
    Button Update_dish,Delete_dish;
    String date,time,name,services,serv;
    StorageReference ref;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth FAuth;
    String ID,CusID;
    private ProgressDialog progressDialog;
    DatabaseReference dataa;
    AppointmentModel appointmentModel = new AppointmentModel();
    private List<AppointmentModel> displayedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_appointment);
        ID = getIntent().getStringExtra("updatedeleteappointment");
        CusID = getIntent().getStringExtra("cusID");
        date_in=(TextInputEditText) findViewById(R.id.dateinput);
        time_in=(TextInputEditText) findViewById(R.id.timeinput);
        tname = (TextInputEditText) findViewById(R.id.cusname);
        service=(Spinner) findViewById(R.id.service);
        Update_dish = (Button)findViewById(R.id.Updatedish);
        Delete_dish = (Button)findViewById(R.id.Deletedish);
        date_in.setInputType(InputType.TYPE_NULL);
        time_in.setInputType(InputType.TYPE_NULL);
        AppointmentModel appointmentModel = new AppointmentModel();
        serv = appointmentModel.getService();

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

        final String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dataa = firebaseDatabase.getInstance().getReference("Admin").child(userid);
        dataa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                progressDialog = new ProgressDialog(UpdateDeleteAppointment.this);
                databaseReference = FirebaseDatabase.getInstance().getReference("AppointmentInfo");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshot1:snapshot.getChildren()) {
                            for (DataSnapshot snapshot2 : snapshot1.getChildren()) {
                                AppointmentModel appointmentModel = snapshot2.getValue(AppointmentModel.class);
                                String date = appointmentModel.getDate();
                                String time = appointmentModel.getTime();
                                String name = appointmentModel.getName();
                                date_in.setText(date);
                                time_in.setText(time);
                                tname.setText(name);
                                //setSpinnerValue(serv);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Update_dish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{

                            date = date_in.getText().toString().trim();
                            time = time_in.getText().toString().trim();
                            name = tname.getText().toString().trim();
                            services = service.getSelectedItem().toString().trim();
                            if (TextUtils.isEmpty(date) || TextUtils.isEmpty(time) || TextUtils.isEmpty(name) || TextUtils.isEmpty(services)) {
                                Toast.makeText(UpdateDeleteAppointment.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                            } else {
                                update();
                            }
                        }
                         catch(Exception e) {
                            // Printing the wrapper exception:
                            System.out.println("Wrapper exception: " + e);

                            // Printing the 'actual' exception:
                            System.out.println("Underlying exception: " + e.getCause());
                        }
                    }
                });
                Delete_dish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDeleteAppointment.this);
                        builder.setMessage("Are you sure you want to Delete Appointment");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                firebaseDatabase.getInstance().getReference("AppointmentInfo")
                                        .child(CusID).child(ID).removeValue();
                                AlertDialog.Builder appointment = new AlertDialog.Builder(UpdateDeleteAppointment.this);
                                appointment.setMessage("Product Has Been Deleted!");
                                appointment.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(UpdateDeleteAppointment.this, AdminAppointmentFragment.class));
                                    }
                                });
                                AlertDialog alert = appointment.create();
                                alert.show();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private static final String HAIR_CUT = "Hari Cut";
    private static final String HAIR_WASH= "Hair Wash";
    private static final String HAIR_DYE = "Hair Dye";
    private static final String HAIR_TREATMENT = "Hair Treatment";

    private void setSpinnerValue(String dataBaseValue)
    {
        if(dataBaseValue.equalsIgnoreCase(HAIR_CUT))
        {
            service.setSelection(0);
        }
        else if(dataBaseValue.equalsIgnoreCase(HAIR_WASH))
        {
            service.setSelection(1);
        }
        else if(dataBaseValue.equalsIgnoreCase(HAIR_DYE))
        {
            service.setSelection(2);
        }
        else if(dataBaseValue.equalsIgnoreCase(HAIR_TREATMENT))
        {
            service.setSelection(3);
        }
    }
    private void update(){
        appointmentModel.setDate(date);
        appointmentModel.setTime(time);
        appointmentModel.setName(name);
        appointmentModel.setService(services);
        appointmentModel.setRandomUID(ID);
        appointmentModel.setCusId(CusID);
        databaseReference = firebaseDatabase.getInstance().getReference("AppointmentInfo").child(CusID).child(ID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                databaseReference.setValue(appointmentModel);

                Toast.makeText(UpdateDeleteAppointment.this,"data updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateDeleteAppointment.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showDateDialog(final TextInputEditText date_in) {
        final Calendar mcurrentDate = Calendar.getInstance();
        mcurrentDate.add(Calendar.DAY_OF_MONTH, 1);
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDatePicker = new DatePickerDialog(
                UpdateDeleteAppointment.this, new DatePickerDialog.OnDateSetListener() {
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
    private void showTimeDialog(final TextInputEditText time_in) {
        final Calendar calendar=Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int Minute = calendar.get(Calendar.MINUTE);;
        TimePickerDialog mTimePicker = new TimePickerDialog(
                UpdateDeleteAppointment.this, new TimePickerDialog.OnTimeSetListener() {
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


}