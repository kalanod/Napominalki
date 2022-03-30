package com.kalanco.napominalki.services;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kalanco.napominalki.moduls.User;

import java.util.Date;

public class AuthServices {
    public static Task<AuthResult> login(String email, String password){
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password);
    }
    public static void logOut(){
        FirebaseAuth.getInstance().signOut();
    }
    public static Task<AuthResult> reg(String email, String password, String name){
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                //User user = UserServices.reg(authResult, name);
                Date date = new Date();
                User user = new User(name, email, date.getDate(), authResult.getUser().getUid());
                UserServices.storeUser(user, getUserId());
            }
        });
    }

    public static void loginSilence(){
       // FirebaseAuth.getInstance().getCurrentUser().get;
    }
    public static boolean isLogin(){
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public static FirebaseUser getUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }
    public static String getUserId() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
