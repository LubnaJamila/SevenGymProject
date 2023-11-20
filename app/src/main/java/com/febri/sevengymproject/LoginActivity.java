package com.febri.sevengymproject;

import androidx.appcompat.app.AppCompatActivity;

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

                if(!(user.isEmpty() || pass.isEmpty())){
                    check(user,pass);
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

    private void check(String username, String password){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.urlLogin + "?username=" + username + "&password=" + password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    String status = obj.getString("status");

                    if(status.equals("berhasil")){
                        String namaPelanggan = obj.getString("nama_pelanggan");
                        Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                        intent.putExtra(KEYNAME, namaPelanggan);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    Toast.makeText(LoginActivity.this, "ASD", Toast.LENGTH_SHORT).show();
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