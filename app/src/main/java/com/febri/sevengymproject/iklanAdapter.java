package com.febri.sevengymproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class iklanAdapter extends RecyclerView.Adapter<iklanAdapter.iklanViewHolder> {

    ArrayList<iklan> list;

    public iklanAdapter(ArrayList<iklan> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public iklanAdapter.iklanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.iklan,parent,false);
        return new iklanAdapter.iklanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull iklanAdapter.iklanViewHolder holder, int position) {

        holder.title.setText(list.get(position).getTrainner());
        holder.back.setImageResource((list.get(position).getPict()));

    }

    @Override
    public int getItemCount() {
        return  (list != null) ? list.size() : 0 ;
    }

    public class iklanViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView back;
        public iklanViewHolder(@NonNull View itemView) {
            super(itemView);

            title =  itemView.findViewById(R.id.title);
            back = itemView.findViewById(R.id.back);
        }
    }
}
