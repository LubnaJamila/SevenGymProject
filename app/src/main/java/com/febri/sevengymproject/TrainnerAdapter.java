package com.febri.sevengymproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TrainnerAdapter extends RecyclerView.Adapter<TrainnerAdapter.TrainnerViewHolder> {
    private static ArrayList<trainner>trainners;
    private static Context context;

    public TrainnerAdapter(Context context, ArrayList<trainner>trainners) {
        this.trainners = trainners;
        this.context = context;
    }

    @NonNull
    @Override
    public TrainnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.from(context).inflate(R.layout.trainner,parent,false);
        return new TrainnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainnerAdapter.TrainnerViewHolder holder, int position) {
        trainner trainner = trainners.get(position);

        //holder.profil_train.setImageResource(Integer.parseInt(trainner.getProfil_trainner()));
        holder.nama.setText(trainner.getNama_lengkap());
        holder.harga.setText(trainner.getHarga_trainner());

    }

    @Override
    public int getItemCount() {
        return trainners.size();
    }

    public static class TrainnerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //ImageView profil_train;
        TextView nama, harga;

        public TrainnerViewHolder(@NonNull View itemView) {
            super(itemView);

            //profil_train = itemView.findViewById(R.id.fototrain);
            nama = itemView.findViewById(R.id.namatrain);
            harga = itemView.findViewById(R.id.hargatrain);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, DetailTrainnerActivity.class);
            intent.putExtra("profil", trainners.get(position).getProfil_trainner());
            intent.putExtra("harga", trainners.get(position).getHarga_trainner());
            intent.putExtra("nama_lengkap", trainners.get(position).getNama_lengkap());
            intent.putExtra("tanggal", trainners.get(position).getTanggal_lahir());
            intent.putExtra("jenis_kelamin", trainners.get(position).getTanggal_lahir());
            intent.putExtra("nohp", trainners.get(position).getNohp());
            intent.putExtra("deskripsi", trainners.get(position).getDeskripsi_pelatih());
            intent.putExtra("tb", trainners.get(position).getTb());
            intent.putExtra("bb", trainners.get(position).getBb());
            context.startActivity(intent);

        }
    }


    }
