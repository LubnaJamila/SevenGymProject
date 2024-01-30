package com.febri.sevengymproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity {
    private long backPress;
    private Toast backToast;

//    private RecyclerView recyclerView;
//    private exerciseAdapter adapter;
//    private ArrayList<exercise> modelExerciseArrayList;
//    LinearLayoutManager layoutManager;

    private ShapeableImageView fprofil;
    BottomNavigationView  bottomNavigationView;
    private TextView wel, nm, jd;
    SharedPreferences preferences;
    private ImageView set;
    String usernma, passwr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new HomeFragment()).commit();
        }

        fprofil = findViewById(R.id.profil);
        wel = findViewById(R.id.welcome);
        nm = findViewById(R.id.nama);

        preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        usernma = preferences.getString("username", "");
        passwr = preferences.getString("password", "");

        getData();

        //Bundle extra = getIntent().getExtras();

//        namaPelanggan = extra.getString("nama_pelanggan");
//        nm.setText(namaPelanggan);
//        String nama = getIntent().getStringExtra("nama_pelanggan");
//        nm.setText(nama);
//        String pp = getIntent().getStringExtra("profil_pelanggan");
//        if (pp != null){
//            String urlPP = "http://"+Api.ip+"/SevenGym/img/" + pp;
//            Glide.with(this).load(urlPP).into(fprofil);
//
//        }
        bottomNavigationView = findViewById(R.id.nav);
        set = findViewById(R.id.pengaturan);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(NavigationActivity.this, SettingActivity.class);
//                startActivity(intent);
                LogoutMenu(NavigationActivity.this);
            }
        });

        fprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NavigationActivity.this, SettingActivity.class);
                startActivity(intent);
//                finish();

            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    getFragment(new HomeFragment());
                    return true;
//                } else if (item.getItemId() == R.id.custom) {
//                    getFragment(new CustomExerciseFragment());
//                    return true;
                }else if(item.getItemId() == R.id.trainner){
                    getFragment(new TrainnerFragment());
                    return true;
                }
                return false;
            }
        });
    }

    private void LogoutMenu(NavigationActivity navigationActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(navigationActivity);
        builder.setTitle("Logout");
        builder.setMessage("Apakah anda yakin ingin keluar?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        if(backPress + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
        }else {
            Toast.makeText(getBaseContext(), "Klik dua kali untuk keluar", Toast.LENGTH_LONG).show();
        }
        backPress = System.currentTimeMillis();
    }

    private void getFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
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
                    nm.setText(user.getNama_pelanggan());
                    String profil = user.getProfil_pelanggan();
                    if (profil != null) {
                        String urlPP = "http://" + Api.ip + "/SevenGym/img/" + profil;
                        Glide.with(getApplicationContext()).load(urlPP).into(fprofil);
                    }


                } else if (userRespon.getCode() == 401) {

                    Toast.makeText(NavigationActivity.this, userRespon.getStatus(), Toast.LENGTH_SHORT).show();

                } else if (userRespon.getCode() == 404) {

                    Toast.makeText(NavigationActivity.this, userRespon.getStatus(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(NavigationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
        requestQueue.add(stringRequest);
    }

//    private void addData() {
//        modelExerciseArrayList = new ArrayList<>();
//        modelExerciseArrayList.add(new exercise("Exercise 1", R.drawable.ic_done));
//        modelExerciseArrayList.add(new exercise("Exercise 2", 0));
//        modelExerciseArrayList.add(new exercise("Exercise 3", R.drawable.ic_done));
//        modelExerciseArrayList.add(new exercise("Exercise 4", R.drawable.ic_done));
//
//    }
//    private void setRecyclerView(){
//        addData();
//
//        //recyclerView = findViewById(R.id.recycle_exercise);
//        layoutManager = new LinearLayoutManager(NavigationActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        adapter = new exerciseAdapter(modelExerciseArrayList);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//
//
////        adapter = new exerciseAdapter(modelExerciseArrayList);
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
////
////        recyclerView.setLayoutManager(layoutManager);
////        recyclerView.setAdapter(adapter);
//    }
}