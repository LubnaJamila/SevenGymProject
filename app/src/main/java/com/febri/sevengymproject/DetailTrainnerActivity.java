package com.febri.sevengymproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

public class DetailTrainnerActivity extends AppCompatActivity {

    TextView nama, tanggal, jenis_kel, harga, desk, noHp, tinggi, berat;
    ShapeableImageView profil_trainner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trainner);

        nama = findViewById(R.id.nm);
        tanggal = findViewById(R.id.lahir);
        jenis_kel = findViewById(R.id.gender);
        harga = findViewById(R.id.harga);
        desk = findViewById(R.id.deskripsi);
        noHp = findViewById(R.id.nohp);
        tinggi = findViewById(R.id.tingban);
        berat = findViewById(R.id.berban);
        profil_trainner = findViewById(R.id.pptrain);

        String Nama = getIntent().getStringExtra("nama_lengkap");
        String Tanggal = getIntent().getStringExtra("tanggal");
        String Jenis = getIntent().getStringExtra("jenis_kelamin");
        String Harga = getIntent().getStringExtra("harga");
        String Deskripsi = getIntent().getStringExtra("deskripsi");
        String NoHp = getIntent().getStringExtra("nohp");
        String Tinggi = getIntent().getStringExtra("tb");
        String Berat = getIntent().getStringExtra("bb");
        String Profil = getIntent().getStringExtra("profil");
        if(Profil != null){
            String urlprofil = "http://"+Api.ip+"/SevenGym/img/" + Profil;
            Glide.with(this).load(urlprofil).into(profil_trainner);
        }

        nama.setText(Nama);
        tanggal.setText(Tanggal);
        jenis_kel.setText(Jenis);
        harga.setText(Harga);
        desk.setText(Deskripsi);
        noHp.setText(NoHp);
        tinggi.setText(Tinggi);
        berat.setText(Berat);

    }
}