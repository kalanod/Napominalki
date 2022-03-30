package com.kalanco.napominalki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.kalanco.napominalki.services.AuthServices;
import com.kalanco.napominalki.services.CurrentUser;
import com.kalanco.napominalki.services.reg;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseApp.initializeApp(getApplicationContext());
        AuthServices.loginSilence();

        if (AuthServices.isLogin()){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), AuthServices.getUser().getUid(), Toast.LENGTH_SHORT).show();
        }
        else{
            startActivity(new Intent(getApplicationContext(), reg.class));
        }
    }
}