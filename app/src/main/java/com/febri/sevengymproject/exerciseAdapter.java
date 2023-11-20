package com.febri.sevengymproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class exerciseAdapter extends RecyclerView.Adapter<exerciseAdapter.exerciceViewHolder> {

    ArrayList<exercise> listdata;

    public exerciseAdapter(ArrayList<exercise> listdata) {

        this.listdata = listdata;
    }

    @NonNull
    @Override
    public exerciseAdapter.exerciceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycle,parent,false);
        return new exerciseAdapter.exerciceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull exerciseAdapter.exerciceViewHolder holder, int position) {

        holder.txtNama.setText(listdata.get(position).getExercise());
        holder.gambar.setImageResource(listdata.get(position).getGambar());


    }

    @Override
    public int getItemCount() {

        return  (listdata != null) ? listdata.size() : 0 ;
    }

    public class exerciceViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNama;
        private ImageView gambar;

        public exerciceViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama =  itemView.findViewById(R.id.exercise);
            gambar = itemView.findViewById(R.id.logo);

        }
    }
}
