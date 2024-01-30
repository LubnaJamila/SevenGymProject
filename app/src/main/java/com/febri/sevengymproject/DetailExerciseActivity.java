package com.febri.sevengymproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailExerciseActivity extends AppCompatActivity {

    ImageView gambar;
    TextView nm, wkt, rep, pw, pb, grk;
    String nama, waktu, repet, poswal, posbuh, ger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_exercise);

        gambar = findViewById(R.id.gmbr);
        nm = findViewById(R.id.nm_ex);
        wkt = findViewById(R.id.wktu);
        rep = findViewById(R.id.rep);
        pw = findViewById(R.id.posisi_awal);
        pb = findViewById(R.id.posisi_tubuh);
        grk = findViewById(R.id.gerakan);

        nama = getIntent().getStringExtra("nama_exercise");
        poswal = getIntent().getStringExtra("posisi_awal");
        posbuh = getIntent().getStringExtra("posisi_badan");
        ger = getIntent().getStringExtra("gerakan");

        nm.setText(nama);
        pw.setText(poswal);
        pb.setText(posbuh);
        grk.setText(ger);
    }
}