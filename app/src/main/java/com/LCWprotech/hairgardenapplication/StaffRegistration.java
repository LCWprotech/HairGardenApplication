package com.LCWprotech.hairgardenapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;



public class StaffRegistration extends AppCompatActivity {


    TextInputLayout Staffname,Staffemail,Staffpassword,StaffCpswd,Staffmobileno,Staffaddress,Staffpincode;
    Spinner Staffgenderspin;
    Button signup, Emaill;
    CountryCodePicker Cpp;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String staffname,staffemailid,staffpassword,staffconfpassword,staffmobile,staffaddress,staffpincode,staffgender;
    String role="Staff";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);

        Staffname = (TextInputLayout)findViewById(R.id.staffName);
        Staffemail = (TextInputLayout)findViewById(R.id.staffEmail);
        Staffpassword = (TextInputLayout)findViewById(R.id.staffPassword);
        StaffCpswd = (TextInputLayout)findViewById(R.id.staffCpswd);
        Staffmobileno = (TextInputLayout)findViewById(R.id.staffMobileno);
        Staffaddress = (TextInputLayout)findViewById(R.id.staffAddress);
        Staffpincode = (TextInputLayout)findViewById(R.id.staffPincode);
        Staffgenderspin = (Spinner) findViewById(R.id.staffGender);

        signup = (Button)findViewById(R.id.Signup);
        Emaill = (Button)findViewById(R.id.email);

        Cpp = (CountryCodePicker)findViewById(R.id.CountryCode);

        Staffgenderspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Object value = parent.getItemAtPosition(position);
                staffgender = value.toString().trim();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        databaseReference = firebaseDatabase.getInstance().getReference("Admin");
        FAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                staffname = Staffname.getEditText().getText().toString().trim();
                staffemailid = Staffemail.getEditText().getText().toString().trim();
                staffmobile = Staffmobileno.getEditText().getText().toString().trim();
                staffpassword = Staffpassword.getEditText().getText().toString().trim();
                staffconfpassword = StaffCpswd.getEditText().getText().toString().trim();
                staffaddress = Staffaddress.getEditText().getText().toString().trim();
                staffpincode = Staffpincode.getEditText().getText().toString().trim();

                if (isValid()){
                    final ProgressDialog mDialog = new ProgressDialog(StaffRegistration.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registration in progress please wait......");
                    mDialog.show();

                    FAuth.createUserWithEmailAndPassword(staffemailid,staffpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                final HashMap<String , String> hashMap = new HashMap<>();
                                hashMap.put("Role",role);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        HashMap<String , String> hashMap1 = new HashMap<>();
                                        hashMap1.put("Mobile No",staffmobile);
                                        hashMap1.put("Full Name",staffname);
                                        hashMap1.put("EmailId",staffemailid);
                                        hashMap1.put("Password",staffpassword);
                                        hashMap1.put("Postcode",staffpincode);
                                        hashMap1.put("Gender",staffgender);
                                        hashMap1.put("Confirm Password",staffconfpassword);
                                        hashMap1.put("Address",staffaddress);

                                        firebaseDatabase.getInstance().getReference("Staff")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                mDialog.dismiss();

                                                FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if(task.isSuccessful()){
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(StaffRegistration.this);
                                                            builder.setMessage("You Have Registered! Make Sure To Verify Your Email");
                                                            builder.setCancelable(false);
                                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {

                                                                    dialog.dismiss();

                                                                    //String phonenumber = Cpp.getSelectedCountryCodeWithPlus() + mobile;
                                                                    Intent b = new Intent(StaffRegistration.this,MainMenu.class);
                                                                    //b.putExtra("phonenumber",phonenumber);
                                                                    startActivity(b);

                                                                }
                                                            });
                                                            AlertDialog Alert = builder.create();
                                                            Alert.show();
                                                        }else{
                                                            mDialog.dismiss();
                                                            Reusable.ShowAlert(StaffRegistration.this,"Error",task.getException().getMessage());
                                                        }
                                                    }
                                                });

                                            }
                                        });

                                    }
                                });
                            }else {
                                mDialog.dismiss();
                                Reusable.ShowAlert(StaffRegistration.this, "Error", task.getException().getMessage());
                            }
                        }
                    });
                }

            }
        });
        Emaill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StaffRegistration.this,StaffLogin.class));
                finish();
            }
        });

    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public boolean isValid(){
        Staffemail.setErrorEnabled(false);
        Staffemail.setError("");
        Staffname.setErrorEnabled(false);
        Staffname.setError("");
        Staffpassword.setErrorEnabled(false);
        Staffpassword.setError("");
        Staffmobileno.setErrorEnabled(false);
        Staffmobileno.setError("");
        StaffCpswd.setErrorEnabled(false);
        StaffCpswd.setError("");
        Staffaddress.setErrorEnabled(false);
        Staffaddress.setError("");
        Staffpincode.setErrorEnabled(false);
        Staffpincode.setError("");

        boolean isValid=false,isValidstaffaddress=false,isValidname=false,isValidemail=false,isValidpassword=false,isValidconfpassword=false,isValidmobilenum=false,isValidpincode=false;
        if(TextUtils.isEmpty(staffname)){
            Staffname.setErrorEnabled(true);
            Staffname.setError("Enter Full Name");
        }else{
            isValidname = true;
        }

        if(TextUtils.isEmpty(staffemailid)){
            Staffemail.setErrorEnabled(true);
            Staffemail.setError("Email Is Required");
        }else{
            if(staffemailid.matches(emailpattern)){
                isValidemail = true;
            }else{
                Staffemail.setErrorEnabled(true);
                Staffemail.setError("Enter a Valid Email Id");
            }
        }
        if(TextUtils.isEmpty(staffpassword)){
            Staffpassword.setErrorEnabled(true);
            Staffpassword.setError("Enter Password");
        }else{
            if(staffpassword.length()<8){
                Staffpassword.setErrorEnabled(true);
                Staffpassword.setError("Password is Weak");
            }else{
                isValidpassword = true;
            }
        }
        if(TextUtils.isEmpty(staffconfpassword)){
            StaffCpswd.setErrorEnabled(true);
            StaffCpswd.setError("Enter Password Again");
        }else{
            if(!staffpassword.equals(staffconfpassword)){
                StaffCpswd.setErrorEnabled(true);
                StaffCpswd.setError("Password Dosen't Match");
            }else{
                isValidconfpassword = true;
            }
        }
        if(TextUtils.isEmpty(staffmobile)){
            Staffmobileno.setErrorEnabled(true);
            Staffmobileno.setError("Mobile Number Is Required");
        }else{
            if(staffmobile.length()<10){
                Staffmobileno.setErrorEnabled(true);
                Staffmobileno.setError("Invalid Mobile Number");
            }else{
                isValidmobilenum = true;
            }
        }
        if(TextUtils.isEmpty(staffpincode)){
            Staffpincode.setErrorEnabled(true);
            Staffpincode.setError("Please Enter Postcode");
        }else{
            isValidpincode = true;
        }
        if(TextUtils.isEmpty(staffaddress)){
            Staffaddress.setErrorEnabled(true);
            Staffaddress.setError("Please Enter Address");
        }else{
            isValidstaffaddress = true;
        }

        isValid = (isValidconfpassword && isValidpassword && isValidpincode && isValidemail && isValidmobilenum && isValidname && isValidstaffaddress && isValidname) ? true : false;
        return isValid;


    }
}