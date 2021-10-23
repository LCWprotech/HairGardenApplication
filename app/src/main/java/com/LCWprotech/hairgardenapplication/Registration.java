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

public class Registration extends AppCompatActivity {

    TextInputLayout Custname,Custemail,Custpassword,CustCpswd,Custmobileno,Custfulladdress,Custpincode;
    Spinner CustGenderspin;
    Button btnSignup, btnEmail;
    CountryCodePicker Cpp;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String name,emailid,password,confpassword,mobile,fulladdress,pincode,gender;
    String role="Customer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Custname = (TextInputLayout)findViewById(R.id.custName);
        Custemail = (TextInputLayout)findViewById(R.id.custEmail);
        Custpassword = (TextInputLayout)findViewById(R.id.custPswd);
        CustCpswd = (TextInputLayout)findViewById(R.id.custCpswd);
        Custmobileno = (TextInputLayout)findViewById(R.id.custMobileno);
        Custfulladdress = (TextInputLayout)findViewById(R.id.custFullAddress);
        Custpincode = (TextInputLayout)findViewById(R.id.custPincode);
        CustGenderspin = (Spinner) findViewById(R.id.custGender);


        btnSignup = (Button)findViewById(R.id.custSignup);
        btnEmail = (Button)findViewById(R.id.custBtnEmail);

        Cpp = (CountryCodePicker)findViewById(R.id.CountryCode);

        CustGenderspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Object value = parent.getItemAtPosition(position);
                gender = value.toString().trim();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        databaseReference = firebaseDatabase.getInstance().getReference("Customer");
        FAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = Custname.getEditText().getText().toString().trim();
                emailid = Custemail.getEditText().getText().toString().trim();
                mobile = Custmobileno.getEditText().getText().toString().trim();
                password = Custpassword.getEditText().getText().toString().trim();
                confpassword = CustCpswd.getEditText().getText().toString().trim();
                fulladdress = Custfulladdress.getEditText().getText().toString().trim();
                pincode = Custpincode.getEditText().getText().toString().trim();

                if (isValid()){
                    final ProgressDialog mDialog = new ProgressDialog(Registration.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registration in progress please wait......");
                    mDialog.show();

                    FAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
                                        hashMap1.put("Mobile No",mobile);
                                        hashMap1.put("Full Name",name);
                                        hashMap1.put("EmailId",emailid);
                                        hashMap1.put("Password",password);
                                        hashMap1.put("Postcode",pincode);
                                        hashMap1.put("Gender",gender);
                                        hashMap1.put("Confirm Password",confpassword);
                                        hashMap1.put("Address",fulladdress);

                                        firebaseDatabase.getInstance().getReference("Customer")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                mDialog.dismiss();

                                                FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if(task.isSuccessful()){
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
                                                            builder.setMessage("You Have Registered! Make Sure To Verify Your Email");
                                                            builder.setCancelable(false);
                                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {

                                                                    dialog.dismiss();

                                                                    //String phonenumber = Cpp.getSelectedCountryCodeWithPlus() + mobile;
                                                                    Intent b = new Intent(Registration.this,MainMenu.class);
                                                                    //b.putExtra("phonenumber",phonenumber);
                                                                    startActivity(b);

                                                                }
                                                            });
                                                            AlertDialog Alert = builder.create();
                                                            Alert.show();
                                                        }else{
                                                            mDialog.dismiss();
                                                            Reusable.ShowAlert(Registration.this,"Error",task.getException().getMessage());
                                                        }
                                                    }
                                                });

                                            }
                                        });

                                    }
                                });
                            }else {
                                mDialog.dismiss();
                                Reusable.ShowAlert(Registration.this, "Error", task.getException().getMessage());
                            }
                        }
                    });
                }

            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,Login.class));
                finish();
            }
        });


    }


    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public boolean isValid(){
        Custemail.setErrorEnabled(false);
        Custemail.setError("");
        Custname.setErrorEnabled(false);
        Custname.setError("");
        Custpassword.setErrorEnabled(false);
        Custpassword.setError("");
        Custmobileno.setErrorEnabled(false);
        Custmobileno.setError("");
        CustCpswd.setErrorEnabled(false);
        CustCpswd.setError("");
        Custfulladdress.setErrorEnabled(false);
        Custfulladdress.setError("");
        Custpincode.setErrorEnabled(false);
        Custpincode.setError("");

        boolean isValid=false,isValidfulladd=false,isValidname=false,isValidemail=false,isValidpassword=false,isValidconfpassword=false,isValidmobilenum=false,isValidpincode=false;
        if(TextUtils.isEmpty(name)){
            Custname.setErrorEnabled(true);
            Custname.setError("Enter Full Name");
        }else{
            isValidname = true;
        }

        if(TextUtils.isEmpty(emailid)){
            Custemail.setErrorEnabled(true);
            Custemail.setError("Email Is Required");
        }else{
            if(emailid.matches(emailpattern)){
                isValidemail = true;
            }else{
                Custemail.setErrorEnabled(true);
                Custemail.setError("Enter a Valid Email Id");
            }
        }
        if(TextUtils.isEmpty(password)){
            Custpassword.setErrorEnabled(true);
            Custpassword.setError("Enter Password");
        }else{
            if(password.length()<8){
                Custpassword.setErrorEnabled(true);
                Custpassword.setError("Password is Weak");
            }else{
                isValidpassword = true;
            }
        }
        if(TextUtils.isEmpty(confpassword)){
            CustCpswd.setErrorEnabled(true);
            CustCpswd.setError("Enter Password Again");
        }else{
            if(!password.equals(confpassword)){
                CustCpswd.setErrorEnabled(true);
                CustCpswd.setError("Password Dosen't Match");
            }else{
                isValidconfpassword = true;
            }
        }
        if(TextUtils.isEmpty(mobile)){
            Custmobileno.setErrorEnabled(true);
            Custmobileno.setError("Mobile Number Is Required");
        }else{
            if(mobile.length()<10){
                Custmobileno.setErrorEnabled(true);
                Custmobileno.setError("Invalid Mobile Number");
            }else{
                isValidmobilenum = true;
            }
        }
        if(TextUtils.isEmpty(pincode)){
            Custpincode.setErrorEnabled(true);
            Custpincode.setError("Please Enter Postcode");
        }else{
            isValidpincode = true;
        }
        if(TextUtils.isEmpty(fulladdress)){
            Custfulladdress.setErrorEnabled(true);
            Custfulladdress.setError("Please Enter Address");
        }else{
            isValidfulladd = true;
        }

        isValid = (isValidconfpassword && isValidpassword && isValidpincode && isValidemail && isValidmobilenum && isValidname && isValidfulladd )? true : false;
        return isValid;


    }

}