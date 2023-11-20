package com.febri.sevengymproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customexerciseAdapter extends RecyclerView.Adapter<customexerciseAdapter.customexerciseViewHolder> {

    ArrayList<customexercise> data;

    public customexerciseAdapter(ArrayList<customexercise> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public customexerciseAdapter.customexerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_custom,parent,false);
        return new customexerciseAdapter.customexerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull customexerciseAdapter.customexerciseViewHolder holder, int position) {
        holder.txtJudul.setText(data.get(position).getJudul());
        holder.ifoto.setImageResource(data.get(position).getFoto());

    }

    @Override
    public int getItemCount() {
        return  (data != null) ? data.size() : 0 ;
    }

    public class customexerciseViewHolder extends RecyclerView.ViewHolder {

        private TextView txtJudul;
        private ImageView ifoto;

        public customexerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul =  itemView.findViewById(R.id.judul);
            ifoto = itemView.findViewById(R.id.foto);
        }
    }
}
