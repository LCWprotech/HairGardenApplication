package com.LCWprotech.hairgardenapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.LCWprotech.hairgardenapplication.Customer.CustomerBottomNavigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextInputLayout Email,Password;
    Button Signin;
    TextView Forgotpassword , Signup;
    FirebaseAuth Fauth;
    String emailid,pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try{

            Email = (TextInputLayout)findViewById(R.id.custEmail);
            Password = (TextInputLayout)findViewById(R.id.custPassword);
            Signin = (Button)findViewById(R.id.custBtnLogin);
            Signup = (TextView) findViewById(R.id.custRegister);
            Forgotpassword = (TextView)findViewById(R.id.custForgotpass);

            Fauth = FirebaseAuth.getInstance();

            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    emailid = Email.getEditText().getText().toString().trim();
                    pswd = Password.getEditText().getText().toString().trim();

                    if(isValid()){

                        final ProgressDialog mDialog = new ProgressDialog(Login.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Sign In Please Wait.......");
                        mDialog.show();

                        Fauth.signInWithEmailAndPassword(emailid,pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    mDialog.dismiss();

                                    if(Fauth.getCurrentUser().isEmailVerified()){
                                        mDialog.dismiss();
                                        Toast.makeText(Login.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();
                                        Intent Z = new Intent(Login.this, CustomerBottomNavigation.class);
                                        startActivity(Z);
                                        finish();

                                    }else{
                                        Reusable.ShowAlert(Login.this,"Verification Failed","You Have Not Verified Your Email");

                                    }
                                }else{
                                    mDialog.dismiss();
                                    Reusable.ShowAlert(Login.this,"Error",task.getException().getMessage());
                                }
                            }
                        });
                    }
                }
            });
            Signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Login.this,Registration.class));
                    finish();
                }
            });
            Forgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Login.this,ForgotPassword.class));
                    finish();
                }
            });

        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    String emailpattern  = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid(){

        Email.setErrorEnabled(false);
        Email.setError("");
        Password.setErrorEnabled(false);
        Password.setError("");

        boolean isvalid=false,isvalidemail=false,isvalidpassword=false;
        if(TextUtils.isEmpty(emailid)){
            Email.setErrorEnabled(true);
            Email.setError("Email is required");
        }else{
            if(emailid.matches(emailpattern)){
                isvalidemail=true;
            }else{
                Email.setErrorEnabled(true);
                Email.setError("Invalid Email Address");
            }
        }
        if(TextUtils.isEmpty(pswd)){

            Password.setErrorEnabled(true);
            Password.setError("Password is Required");
        }else{
            isvalidpassword=true;
        }
        isvalid=(isvalidemail && isvalidpassword)?true:false;
        return isvalid;
    }

}