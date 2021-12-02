package com.LCWprotech.hairgardenapplication.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

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

public class UpdateDeleteAppointment extends AppCompatActivity {
    TextInputLayout date_in;
    TextInputLayout time_in;
    TextInputLayout tname;
    Spinner service;
    private Uri mCropimageuri;
    Button Update_dish,Delete_dish;
    String date,time,name,services,serv;
    String RandomUID;
    StorageReference ref;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth FAuth;
    String ID,CusID;
    private ProgressDialog progressDialog;
    DatabaseReference dataa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_appointment);
        ID = getIntent().getStringExtra("updatedeleteappointment");
        CusID = getIntent().getStringExtra("cusID");
        date_in=(TextInputLayout) findViewById(R.id.date_input);
        time_in=(TextInputLayout) findViewById(R.id.time_input);
        tname = (TextInputLayout) findViewById(R.id.name);
        service=(Spinner) findViewById(R.id.service);
        Update_dish = (Button)findViewById(R.id.Updatedish);
        Delete_dish = (Button)findViewById(R.id.Deletedish);
        AppointmentModel appointmentModel = new AppointmentModel();
        serv = appointmentModel.getService();

        final String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dataa = firebaseDatabase.getInstance().getReference("Admin").child(userid);
        dataa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Update_dish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        date = date_in.getEditText().getText().toString().trim();
                        time = time_in.getEditText().getText().toString().trim();
                        name = tname.getEditText().getText().toString().trim();
                        services = service.getSelectedItem().toString().trim();

                    }
                });
                progressDialog = new ProgressDialog(UpdateDeleteAppointment.this);
                databaseReference = FirebaseDatabase.getInstance().getReference("AppointmentInfo").child(CusID).child(ID);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        AppointmentModel updateProductModel = snapshot.getValue(AppointmentModel.class);
                        String date = updateProductModel.getDate();
                        String time = updateProductModel.getTime();
                        String name = updateProductModel.getName();
                        date_in.getEditText().setText(date);
                        time_in.getEditText().setText(time);
                        tname.getEditText().setText(name);
                        setSpinnerValue(serv);
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
    private static final String HAIR_CUT = "Hari Cut";
    private static final String HAIR_WASH= "Hair Wash";
    private static final String HAIR_DYE = "Hair Dye";
    private static final String HAIR_TREATMENT = "Hair Treatment";

    private void setSpinnerValue(String dataBaseValue)
    {
        if(dataBaseValue.equalsIgnoreCase(HAIR_CUT)) // IF Data base value is AM select position as 0
        {
            service.setSelection(0);
        }
        else if(dataBaseValue.equalsIgnoreCase(HAIR_WASH)) // IF Data base value is PM select position as 1
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
}