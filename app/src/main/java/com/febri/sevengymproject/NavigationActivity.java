package com.febri.sevengymproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity {
    private String KEYNAME = "NAMA_PELANGGAN";
//    private RecyclerView recyclerView;
//    private exerciseAdapter adapter;
//    private ArrayList<exercise> modelExerciseArrayList;
//    LinearLayoutManager layoutManager;

    private ShapeableImageView fprofil;
    BottomNavigationView  bottomNavigationView;
    private TextView wel, nm, jd;

    private ImageView set;
    String namaPelanggan;

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
        Bundle extra = getIntent().getExtras();

        namaPelanggan = extra.getString(KEYNAME);
        nm.setText(namaPelanggan);
        bottomNavigationView = findViewById(R.id.nav);
        set = findViewById(R.id.pengaturan);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    getFragment(new HomeFragment());
                    return true;
                } else if (item.getItemId() == R.id.custom) {
                    getFragment(new CustomExerciseFragment());
                    return true;
                }else if(item.getItemId() == R.id.trainner){
                    getFragment(new TrainnerFragment());
                    return true;
                }
                return false;
            }
        });
    }

    private void getFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
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