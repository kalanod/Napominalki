package com.kalanco.napominalki.services;

import com.firebase.ui.database.ClassSnapshotParser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.kalanco.napominalki.MainActivity;
import com.kalanco.napominalki.moduls.EmptyModel;
import com.kalanco.napominalki.moduls.Napominalka;
import com.kalanco.napominalki.moduls.User;

import java.lang.reflect.Field;

public class DatabaseServices {

    public static FirebaseDatabase getDatabase(){
        return FirebaseDatabase.getInstance("https://napominalki-29cff-default-rt" +
                "db.europe-west1.firebasedatabase.app/");
    }
    @Deprecated
    public static Task<Void> setUser(String ref, User user){
        return getDatabase().getReference(ref).setValue(user);
    }
    public static Task<Void> setString(String ref, String st){
        return getDatabase().getReference(ref).setValue(st);
    }
    public static Task<Void> add(String ref, EmptyModel model, String id){
        return getDatabase().getReference(ref).push().setValue(model);
    }
    public static Task<Void> addUser(User user, String id){
        return getDatabase().getReference("users").child(id).setValue(user);
    }
    public static Task<Void> remove(String ref){
        return getDatabase().getReference(ref).removeValue();
    }
    public static void get(String ref, ValueEventListener listener){
        getDatabase().getReference(ref).addListenerForSingleValueEvent(listener);
    }
    public static void  getKeys(String ref, ChildEventListener listener){
        getDatabase().getReference(ref).startAt(5).endAt(7).limitToFirst(10).addChildEventListener(listener);
    }

    public static FirebaseRecyclerOptions<User> getUserOptions() {
        Query quere = getDatabase().getReference("users");
        ClassSnapshotParser<User> parser = new ClassSnapshotParser<>(User.class);
        return new FirebaseRecyclerOptions.Builder<User>().setQuery(quere, parser).build();
    }

    public static FirebaseRecyclerOptions<Napominalka> getNapominalkaOptions(String id) {
        Query quere = getDatabase().getReference("users/" + id + "/napominalkas");
        ClassSnapshotParser<Napominalka> parser = new ClassSnapshotParser<>(Napominalka.class);
        return new FirebaseRecyclerOptions.Builder<Napominalka>().setQuery(quere, parser).build();
    }

    public static Task<Void> delUser(String id) {
        return getDatabase().getReference("users").child(id).removeValue();
    }

    public static Task<Void> addNapom(
            FirebaseUser currentUser, Napominalka napominalka) {
        return getDatabase().getReference("users").child(currentUser.getUid()).child("napominalkas").child(napominalka.getId()).setValue(napominalka);
    }

    public static Task<Void> updateNapom(Napominalka napominalka) {
        FirebaseUser currentUser = MainActivity.currentUser;
        return getDatabase().getReference("users").child(currentUser.getUid()).child("napominalkas").child(napominalka.getId()).setValue(napominalka);
    }
}
