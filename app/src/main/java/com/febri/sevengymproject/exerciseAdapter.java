package com.febri.sevengymproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class exerciseAdapter extends RecyclerView.Adapter<exerciseAdapter.exerciceViewHolder> {

    private static ArrayList<exercise> Exercise;
    private static Context context;

    public exerciseAdapter(Context context, ArrayList<exercise> Exercise) {

        this.Exercise = Exercise;
        this.context = context;
    }

    @NonNull
    @Override
    public exerciceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.from(context).inflate(R.layout.recycle, parent, false);
        return new exerciceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull exerciseAdapter.exerciceViewHolder holder, int position) {
        exercise exercise = Exercise.get(position);

        holder.txtNama.setText(exercise.getNama_paket());
        //holder.gambar.setImageResource(Integer.parseInt(exercise.getGambar()));


    }

    @Override
    public int getItemCount() {

        return Exercise.size();
    }

    public class exerciceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtNama;
        //private ImageView gambar;

        public exerciceViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama =  itemView.findViewById(R.id.exercise);
            //gambar = itemView.findViewById(R.id.logo);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
//            final String idx = Exercise.get(position).getId_paket();
//            listDetail(idx);
            Intent intent = new Intent(context, ListExerciseActivity.class);
            intent.putExtra("nama_paket", Exercise.get(position).getNama_paket());
            //intent.putExtra("gambar", Exercise.get(position).getGambar());
            context.startActivity(intent);
        }
        private void listDetail(String id){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.urlListExercise + "?id_paket=" + id, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jobj = new JSONObject(response);

//                        String id_paket_exc = jobj.getString("id_paket_exc");
//                        String id_paket = jobj.getString("id_paket");
//                        String id_exercise = jobj.getString("id_exercise");
                        String waktu_set = jobj.getString("waktu_set");
                        String repetisi = jobj.getString("repetisi");
//                        String nama_paket = jobj.getString("nama_paket");
                        String nama_exercise = jobj.getString("nama_exercise");
                        String gambar = jobj.getString("gambar");
                        String posisi_awal = jobj.getString("posisi_awal");
                        String posisi_badan = jobj.getString("posisi_baddan");
                        String gerakan = jobj.getString("gerakan");

                        Intent intent = new Intent(context, ListExerciseActivity.class);
                        intent.putExtra("data", jobj.toString());
//                        intent.putExtra("id", id_paket_exc);
//                        intent.putExtra("id_paket", id_paket);
//                        intent.putExtra("id_exercise", id_exercise);
                        intent.putExtra("waktu_set", waktu_set);
                        intent.putExtra("repetisi", repetisi);
//                        intent.putExtra("nama_paket", nama_paket);
                        intent.putExtra("nama_exercise", nama_exercise);
                        intent.putExtra("gambar", gambar);
                        intent.putExtra("posisi_awal", posisi_awal);
                        intent.putExtra("posisi_badan", posisi_badan);
                        intent.putExtra("gerakan", gerakan);
                        context.startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
            RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
            queue.add(stringRequest);
        }
    }
}
