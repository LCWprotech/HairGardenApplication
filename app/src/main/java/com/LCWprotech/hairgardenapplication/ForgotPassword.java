package com.LCWprotech.hairgardenapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    TextInputLayout forgetpassword;
    Button Reset;
    FirebaseAuth FAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        forgetpassword = (TextInputLayout) findViewById(R.id.emailIdReset);
        Reset = (Button) findViewById(R.id.reset);

        FAuth = FirebaseAuth.getInstance();
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(ForgotPassword.this);
                mDialog.setCancelable(false);
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.setMessage("Logging in Reset Password...");
                mDialog.show();

                FAuth.sendPasswordResetEmail(forgetpassword.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mDialog.dismiss();
                            Reusable.ShowAlert(ForgotPassword.this, "", "Password has been sent to your Email");
                            Intent Z = new Intent(ForgotPassword.this, Login.class);
                            startActivity(Z);
                            finish();
                        } else {
                            mDialog.dismiss();
                            Reusable.ShowAlert(ForgotPassword.this, "Error", task.getException().getMessage());
                        }
                    }
                });
            }
        });

    }
}