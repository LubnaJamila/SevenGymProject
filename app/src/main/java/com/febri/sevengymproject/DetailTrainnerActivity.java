package com.febri.sevengymproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

public class DetailTrainnerActivity extends AppCompatActivity {

    TextView nama, tanggal, jenis_kel, harga, desk, noHp, tinggi, berat;
    ShapeableImageView profil_trainner;
    Button hubungi;
    String whatsappUser = "+6285204967688";

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
//        tinggi = findViewById(R.id.tingban);
//        berat = findViewById(R.id.berban);
        profil_trainner = findViewById(R.id.pptrain);
        hubungi = findViewById(R.id.wa_trainner);

        String Nama = getIntent().getStringExtra("nama_lengkap");
        String Tanggal = getIntent().getStringExtra("tanggal");
        String Jenis = getIntent().getStringExtra("jenis_kelamin");
        String Harga = getIntent().getStringExtra("harga");
        String Deskripsi = getIntent().getStringExtra("deskripsi");
        String NoHp = getIntent().getStringExtra("nohp");
//        String Tinggi = getIntent().getStringExtra("tb");
//        String Berat = getIntent().getStringExtra("bb");
        String Profil = getIntent().getStringExtra("profil");
        if(Profil != null){
            String urlprofil = "http://"+Api.ip+"/SevenGym/img/" + Profil;
            Glide.with(this).load(urlprofil).into(profil_trainner);
        }

        nama.setText("Nama : " + Nama);
        tanggal.setText("Tanggal Lahir : " + Tanggal);
        jenis_kel.setText("Jenis Kelamin : " + Jenis);
        harga.setText("Harga : " + Harga);
        desk.setText("Deskripsi : " + Deskripsi);
        noHp.setText("No Hp : " + NoHp);
//        tinggi.setText(Tinggi);
//        berat.setText(Berat);

        hubungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone=" + whatsappUser;
                try {
                    PackageManager pm = getApplicationContext().getPackageManager();
                    pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                } catch (PackageManager.NameNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                }

            }
        });

    }
}