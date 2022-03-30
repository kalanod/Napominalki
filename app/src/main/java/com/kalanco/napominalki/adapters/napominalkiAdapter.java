package com.kalanco.napominalki.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.kalanco.napominalki.R;
import com.kalanco.napominalki.moduls.Napominalka;
import com.kalanco.napominalki.services.CurrentUser;
import com.kalanco.napominalki.services.DatabaseServices;

public class napominalkiAdapter extends FirebaseRecyclerAdapter<Napominalka, napominalkiAdapter.ViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public napominalkiAdapter(@NonNull FirebaseRecyclerOptions<Napominalka> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Napominalka model) {
        holder.build(model);
    }

    @NonNull
    @Override
    public napominalkiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.napominalka,
                parent,
                false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        CheckBox isCheck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.textView);
            this.isCheck = itemView.findViewById(R.id.checkBox);
        }

        public void build(Napominalka napominalka){
            title.setText(napominalka.title);
            isCheck.setChecked(napominalka.isCheck);
            isCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    napominalka.isCheck = !napominalka.isCheck;
                    DatabaseServices.updateNapom(napominalka);
                }
            });
        }
    }
}
