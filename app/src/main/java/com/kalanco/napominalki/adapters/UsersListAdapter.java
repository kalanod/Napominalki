package com.kalanco.napominalki.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.kalanco.napominalki.MainActivity;
import com.kalanco.napominalki.R;
import com.kalanco.napominalki.moduls.User;
import com.kalanco.napominalki.services.DatabaseServices;

public class UsersListAdapter extends FirebaseRecyclerAdapter<User, UsersListAdapter.viewHolder> {

    public UsersListAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull User model) {
        holder.buid(model);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,
                parent,
                false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView email;
        TextView name;
        Button del;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.fild_email);
            name = itemView.findViewById(R.id.fild_name);
            del = itemView.findViewById(R.id.btn_del);

        }

        public void buid(User user) {
            name.setText(user.name);
            email.setText(user.email);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseServices.delUser(user.id).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                        }
                    });
                }
            });
        }
    }
}
