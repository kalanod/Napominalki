package com.kalanco.napominalki;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.kalanco.napominalki.adapters.UsersListAdapter;
import com.kalanco.napominalki.adapters.napominalkiAdapter;
import com.kalanco.napominalki.moduls.InputDialog;
import com.kalanco.napominalki.moduls.Napominalka;
import com.kalanco.napominalki.services.AuthServices;
import com.kalanco.napominalki.services.CurrentUser;
import com.kalanco.napominalki.services.DatabaseServices;
import com.kalanco.napominalki.services.UserServices;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button logOut_button;
    RecyclerView list;
    Button btn_console;
    TextView textView;
    napominalkiAdapter adapter;
    public static FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    FloatingActionButton fButtin;
    EditText editText;
    //Button reg_button;
    //EditText email;
    //EditText pass;
    void showMess(String text){
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linker();

        //Bundle arguments = getIntent().getExtras();
        //if(arguments!=null) {
        //    currentUser = (CurrentUser)  arguments.getSerializable(CurrentUser.class.getSimpleName());
        //}

        logOut_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthServices.logOut();
                startActivity(new Intent(getApplicationContext(), splash.class));
            }
        });

        btn_console.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Console.class));
            }
        });
        adapter = new napominalkiAdapter(DatabaseServices.getNapominalkaOptions(currentUser.getUid()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setAdapter(adapter);
        list.setLayoutManager(layoutManager);
        fButtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                InputDialog myDialogFragment = new InputDialog();
                myDialogFragment.show(manager, "myDialog");
            }
        });

    }
    public void addNapom(){
        Napominalka test = new Napominalka("test");
        DatabaseServices.addNapom(currentUser, test);
        //DatabaseServices.addNapom("test");
        showMess("test");
    };
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    protected void linker() {
        logOut_button = findViewById(R.id.button2);
        list = findViewById(R.id.list);
        btn_console = findViewById(R.id.btn_console);
        textView = findViewById(R.id.textView);
        fButtin = findViewById(R.id.floatingActionButton2);
        editText = findViewById(R.id.editTextTextPersonName4);
        //reg_button = findViewById(R.id.button_reg);
        //email = findViewById(R.id.fild_email);
        //pass = findViewById(R.id.fild_pass);
    }

}