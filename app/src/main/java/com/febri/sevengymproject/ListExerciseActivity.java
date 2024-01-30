package com.febri.sevengymproject;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListExerciseActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    TextView judul;
    ListView list;
    List<exlist> itemList = new ArrayList<exlist>();
    SwipeRefreshLayout swipe;
    ListExerciseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercise);

        judul = findViewById(R.id.jdl);
        String Judul = getIntent().getStringExtra("nama_paket");
        judul.setText(Judul);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = findViewById(R.id.lv);

        adapter = new ListExerciseAdapter(ListExerciseActivity.this, itemList);
        list.setAdapter(adapter);


//        JSONArray array;
//        try {
//            JSONObject obj = new JSONObject(getIntent().getStringExtra("data"));
//            array = obj.getJSONArray("data");
//            for(int i=0; i>array.length();i++){
//            JSONObject data = array.getJSONObject(i);
//                exlist ex = new exlist();
//                ex.setGambar(data.getString("gambar"));
//                ex.setNama_exercise(data.getString("nama_exercise"));
//                ex.setWaktu_set(data.getString("waktu_set"));
//                ex.setRepetisi(data.getString("repetisi"));
//                itemList.add(ex);
//            }
//
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }

        try {
            swipe.setOnRefreshListener(this);

            swipe.post(new Runnable() {
                @Override
                public void run() {
                    swipe.setRefreshing(true);
                    itemList.clear();
                    adapter.notifyDataSetChanged();
                    callVolley();
                }
            });

        } catch (Exception e){
            Log.d("Test", e.getMessage());
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final String idx = itemList.get(position).getId_exercise();
                detailLatihan(idx);
            }
        });
    }

    private void detailLatihan(String id_exercise) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.urlDetail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);

                    String idx = jobj.getString("id_exercise");
                    String nama_exercise = jobj.getString("nama_exercise");
                    String gambard = jobj.getString("gambar");
                    String pos_awl = jobj.getString("posisi_awal");
                    String pos_bdn = jobj.getString("posisi_badan");
                    String grkn = jobj.getString("gerakan");

                    Intent intent = new Intent(ListExerciseActivity.this, DetailExerciseActivity.class);
                    intent.putExtra("id_exercise", idx);
                    intent.putExtra("nama_exercise", nama_exercise);
                    intent.putExtra("gambar", gambard);
                    intent.putExtra("posisi_awal", pos_awl);
                    intent.putExtra("posisi_badan", pos_bdn);
                    intent.putExtra("gerakan", grkn);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListExerciseActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected HashMap<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();

                params.put("id_exercise", id_exercise);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }

    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }
    public void callVolley(){
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        JsonArrayRequest jArr = new JsonArrayRequest(Api.urlListExercise, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        exlist items = new exlist();

                        //items.setGambar(obj.getString("gambar"));
                        items.setId_exercise(obj.getString("id_exercise"));
                        items.setNama_exercise(obj.getString("nama_exercise"));
//                        items.setWaktu_set(obj.getString("waktu_set"));
//                        items.setRepetisi(obj.getString("repetisi"));

                        itemList.add(items);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                adapter.notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error : " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);
    }

}