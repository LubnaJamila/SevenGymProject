package com.febri.sevengymproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    public TextInputEditText etusername, etpassword;
    CheckBox checkBox;
    SharedPreferences preferences;
    private static final String PREFS_NAME = "PrefsFile";
    private String KEYNAME = "NAMA_PELANGGAN";

    public Button bmasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        etusername = findViewById(R.id.username);
        etpassword = findViewById(R.id.password);
        bmasuk = findViewById(R.id.masuk);
        checkBox = findViewById(R.id.ingat);

        getPreferencesData();

        bmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = etusername.getText().toString();
                String pass = etpassword.getText().toString();

                //login(user, pass);

                if(!(user.isEmpty() || pass.isEmpty())){
                    login(user,pass);
                }else{
                    Toast.makeText(LoginActivity.this, "Isikan Username dan Password", Toast.LENGTH_SHORT).show();
                }



//                Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
//                startActivity(intent);

                if (checkBox.isChecked()){
                    Boolean boolIsChecked = checkBox.isChecked();
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("pref_name", user);
                    editor.putString("pref_pass", pass);
                    editor.putBoolean("pref_check", boolIsChecked);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Akun Telah Disimpan", Toast.LENGTH_SHORT).show();
                }else{
                    preferences.edit().clear().apply();
                }
                etusername.getText().clear();
                etpassword.getText().clear();
            }

        });

    }

    private void login(String username, String password){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.urlLogin + "?username=" + username + "&password=" + password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                UserRespon userRespon = gson.fromJson(response.toString(), UserRespon.class);

                if (userRespon.getCode()==200){
                    User user = userRespon.getData().get(0);
                    String usernm = user.getUsername();
                    String pss = user.getPassword();
                    String namleng = user.getNama_pelanggan();
                    String pp = user.getProfil_pelanggan();
                    Integer id = user.getId_user();

                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", usernm);
                    editor.putString("password", pss);
                    editor.putInt("id_user", id);
                    editor.apply();

                    Toast.makeText(LoginActivity.this, userRespon.getStatus(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                    intent.putExtra("nama_pelanggan", namleng);
                    intent.putExtra("profil_pelanggan", pp);
                    startActivity(intent);
                    finish();


                }else if (userRespon.getCode()==401){

                    Toast.makeText(LoginActivity.this, userRespon.getStatus(), Toast.LENGTH_SHORT).show();

                }else if (userRespon.getCode()==404){

                    Toast.makeText(LoginActivity.this, userRespon.getStatus(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
        requestQueue.add(stringRequest);
    }

//    private void check(String username, String password){
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.urlLogin + "?username=" + username + "&password=" + password, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONObject obj = new JSONObject(response);
//                    String status = obj.getString("status");
//
//                    if(status.equals("berhasil")){
//                        String namaPelanggan = obj.getString("nama_pelanggan");
//                        Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
//                        intent.putExtra(KEYNAME, namaPelanggan);
//                        startActivity(intent);
//                        Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
//                    }
//                }catch (JSONException e){
//                    Toast.makeText(LoginActivity.this, "ASD", Toast.LENGTH_SHORT).show();
//                }
//
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(stringRequest);
//    }
    private void getPreferencesData(){
        SharedPreferences sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if(sp.contains("pref_name")){
            String u = sp.getString("pref_name", "not found.");
            etusername.setText(u.toString());
        }
//        if(sp.contains("pref_pass")){
//            String p = sp.getString("pref_pass", "not found.");
//            password.setText(p.toString());
//        }
        if(sp.contains("pref_check")){
            Boolean b = sp.getBoolean("pref_check", false);
            checkBox.setChecked(b);
        }
    }
}