package com.febri.sevengymproject;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SettingActivity extends AppCompatActivity {

    TextInputEditText username, password, namaPelanggan, Nohp, Tanggal, Gender, TB, BB;
    EditText id;
    ShapeableImageView PP;
    Button sv;

    private SharedPreferences sharedPreferences;

    String ids, usernma, passwr;
    Integer tingban, berban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        id = findViewById(R.id.id_user);
        username = findViewById(R.id.usernamee);
        password = findViewById(R.id.pass);
        namaPelanggan = findViewById(R.id.namaa);
        Nohp = findViewById(R.id.hp);
        Tanggal = findViewById(R.id.tgl);
        Gender = findViewById(R.id.jk);
        TB = findViewById(R.id.tb);
        BB = findViewById(R.id.bb);
        PP = findViewById(R.id.pp);
        sv = findViewById(R.id.save);

        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        usernma = sharedPreferences.getString("username", "");
        passwr = sharedPreferences.getString("password", "");

        profile();

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update();
            }
        });
    }

    private void profile() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.urlLogin + "?username=" + usernma + "&password=" + passwr, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                UserRespon userRespon = gson.fromJson(response.toString(), UserRespon.class);

                if (userRespon.getCode() == 200) {
                    User user = userRespon.getData().get(0);
                    id.setText(String.valueOf(user.getId_user()));
                    username.setText(user.getUsername());
                    password.setText(user.getPassword());
                    namaPelanggan.setText(user.getNama_pelanggan());
                    Nohp.setText(user.getNohp());
                    Tanggal.setText(user.getTanggal_lahir());
                    Gender.setText(user.getJenis_kelamin());
                    TB.setText(user.getTb());
                    BB.setText(user.getBb());
                    String profil = user.getProfil_pelanggan();
                    if (profil != null) {
                        String urlPP = "http://" + Api.ip + "/SevenGym/img/" + profil;
                        Glide.with(getApplicationContext()).load(urlPP).into(PP);
                    }


                } else if (userRespon.getCode() == 401) {

                    Toast.makeText(SettingActivity.this, userRespon.getStatus(), Toast.LENGTH_SHORT).show();

                } else if (userRespon.getCode() == 404) {

                    Toast.makeText(SettingActivity.this, userRespon.getStatus(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SettingActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
        requestQueue.add(stringRequest);
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.urlLogin + "?username=" + usernma + "&password=" + passwr, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                UserRespon userRespon = gson.fromJson(response.toString(), UserRespon.class);

                if (userRespon.getCode() == 200) {
                    User user = userRespon.getData().get(0);
                    //id.setText(user.getId_user());
                    username.setText(user.getUsername());
                    password.setText(user.getPassword());
                    namaPelanggan.setText(user.getNama_pelanggan());
                    Nohp.setText(user.getNohp());
                    Tanggal.setText(user.getTanggal_lahir());
                    Gender.setText(user.getJenis_kelamin());
                    TB.setText(user.getTb());
                    BB.setText(user.getBb());
                    String profil = user.getProfil_pelanggan();
                    if (profil != null) {
                        String urlPP = "http://" + Api.ip + "/SevenGym/img/" + profil;
                        Glide.with(getApplicationContext()).load(urlPP).into(PP);
                    }


                } else if (userRespon.getCode() == 401) {

                    Toast.makeText(SettingActivity.this, userRespon.getStatus(), Toast.LENGTH_SHORT).show();

                } else if (userRespon.getCode() == 404) {

                    Toast.makeText(SettingActivity.this, userRespon.getStatus(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SettingActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
        requestQueue.add(stringRequest);
    }

    private void update() {
        String Id = id.getText().toString().trim();
        String Username = username.getText().toString().trim();
        String Password = password.getText().toString().toString().trim();
        String namapelanggan = namaPelanggan.getText().toString().trim();
        String nohp = Nohp.getText().toString().trim();
        String tgl = Tanggal.getText().toString().trim();
        String gender = Gender.getText().toString().trim();
        String tb = TB.getText().toString().trim();
        String bb = BB.getText().toString().trim();


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.urlUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Gson gson = new Gson();
//                UserRespon userRespon = gson.fromJson(response.toString(), UserRespon.class);

               try {
                   JSONObject jsonObj = new JSONObject(response);
                   int code = jsonObj.getInt("code");
                   String status = jsonObj.getString("status");
                   if(code==200){
                       //getData();
                       Toast.makeText(SettingActivity.this, status, Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                       startActivity(intent);
                   }else {
                       Toast.makeText(SettingActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                   }
               }catch (JSONException e){
                   e.printStackTrace();
                   Toast.makeText(SettingActivity.this, "gagal total", Toast.LENGTH_SHORT).show();
               }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d(TAG, "Error : " + error.getMessage());

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", Id);
                params.put("usernm", Username);
                params.put("pass", Password);
                params.put("namaPel", namapelanggan);
                params.put("tgl", tgl);
                params.put("hp", nohp);
                params.put("gender", gender);
                params.put("tingban", tb);
                params.put("berban", bb);
                return params;
            }


        };
        requestQueue.add(stringRequest);
    }
}