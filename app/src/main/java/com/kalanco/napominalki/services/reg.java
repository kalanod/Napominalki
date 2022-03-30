package com.kalanco.napominalki.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.kalanco.napominalki.R;
import com.kalanco.napominalki.login;
import com.kalanco.napominalki.services.AuthServices;
import com.kalanco.napominalki.splash;

public class reg extends AppCompatActivity {

    Button log_button;
    Button reg_button;
    EditText email_fild;
    EditText pass_fild;
    EditText name_fild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        linker();
        log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_fild.getText().toString();
                String pass = pass_fild.getText().toString();
                String name = name_fild.getText().toString();
                if (email.isEmpty() || !email.contains("@")) {
                    showMess("ошибка в email");
                    email_fild.setError("email is empty");
                } else {
                    AuthServices.reg(email, pass, name)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Intent intent = new Intent(getApplicationContext(), splash.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    showMess(e.getLocalizedMessage());
                                }
                            })
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                }
                            });
                }

            }

        });

    }

    void showMess(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    protected void linker() {
        log_button = findViewById(R.id.button_log);
        reg_button = findViewById(R.id.button_reg);
        email_fild = findViewById(R.id.fild_email);
        pass_fild = findViewById(R.id.fild_pass);
        name_fild = findViewById(R.id.fild_name);
    }
}