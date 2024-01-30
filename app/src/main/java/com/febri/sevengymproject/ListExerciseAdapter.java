package com.febri.sevengymproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.List;

public class ListExerciseAdapter extends BaseAdapter {

    Activity activity;
    List<exlist> items;
    private LayoutInflater inflater;

    public ListExerciseAdapter(Activity activity, List<exlist> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) convertView = inflater.inflate(R.layout.list_exercise, null);
        ImageView gambar = (ImageView) convertView.findViewById(R.id.gbr);
        TextView id = (TextView) convertView.findViewById(R.id.idex);
        TextView latih = (TextView) convertView.findViewById(R.id.lth);
        TextView waktu = (TextView) convertView.findViewById(R.id.wkt);
        TextView repetisi = (TextView) convertView.findViewById(R.id.rp);

        exlist Exl = items.get(position);
        //gambar.setImageResource(Integer.parseInt(Exl.getGambar()));
        id.setText(Exl.getId_exercise());
        latih.setText(Exl.getNama_exercise());
//        waktu.setText(Exl.getWaktu_set());
//        repetisi.setText(Exl.getRepetisi());
        return convertView;
    }
}
