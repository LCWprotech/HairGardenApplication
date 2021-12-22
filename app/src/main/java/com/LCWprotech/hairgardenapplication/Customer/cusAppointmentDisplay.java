package com.LCWprotech.hairgardenapplication.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.Spinner;

import com.LCWprotech.hairgardenapplication.Admin.AppointmentModel;
import com.LCWprotech.hairgardenapplication.Admin.UpdateDeleteAppointment;
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

import java.util.List;

public class cusAppointmentDisplay extends AppCompatActivity {
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
        setContentView(R.layout.activity_cus_appointment_display);
        ID = getIntent().getStringExtra("updatedeleteappointment");
        CusID = getIntent().getStringExtra("cusID");
        date_in=(TextInputEditText) findViewById(R.id.dateinput);
        time_in=(TextInputEditText) findViewById(R.id.timeinput);
        tname = (TextInputEditText) findViewById(R.id.cusname);
        service=(Spinner) findViewById(R.id.service);
        date_in.setInputType(InputType.TYPE_NULL);
        time_in.setInputType(InputType.TYPE_NULL);
        AppointmentModel appointmentModel = new AppointmentModel();
        serv = appointmentModel.getService();
        final String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dataa = firebaseDatabase.getInstance().getReference("Customer").child(userid);
        dataa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog = new ProgressDialog(cusAppointmentDisplay.this);
                databaseReference = FirebaseDatabase.getInstance().getReference("AppointmentInfo").child(CusID).child(ID);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        AppointmentModel appointmentModel = snapshot.getValue(AppointmentModel.class);
                        String date = appointmentModel.getDate();
                        String time = appointmentModel.getTime();
                        String name = appointmentModel.getName();
                        date_in.setText(date);
                        time_in.setText(time);
                        tname.setText(name);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}