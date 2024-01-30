package com.febri.sevengymproject;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.gun0912.tedpermission.normal.TedPermission;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingActivity extends AppCompatActivity {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    Uri selecteduri;
    Bitmap bitmap;
    String url = "";
    String encodImage;
    String profil;
    TextInputEditText username, password, namaPelanggan, Nohp, Tanggal, Gender, TB, BB;
    EditText id;
    ShapeableImageView PP, Ubah;
    Button sv;
    ImageView balek;

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
        balek = findViewById(R.id.kembali);
        Ubah = findViewById(R.id.ubah_prof);


        sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        usernma = sharedPreferences.getString("username", "");
        passwr = sharedPreferences.getString("password", "");

        profile();

        Ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askpermission();
            }
        });
        
        PP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update();
            }
        });

        balek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void chooseImage(){
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
//            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
//        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST_CODE){
            bitmap = (Bitmap) data.getExtras().get("data");
            PP.setImageBitmap(bitmap);
            imageStore(bitmap);
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(selecteduri);
//                bitmap = BitmapFactory.decodeStream(inputStream);
//                PP.setImageBitmap(bitmap);
//                imageStore(bitmap);
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }


        }

//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
//            if (data != null) {
//                Bundle extras = data.getExtras();
//                if (extras != null) {
//                    Bitmap imageBitmap = (Bitmap) extras.get("data");
//                    if (imageBitmap != null) {
//                        PP.setImageBitmap(imageBitmap);
//                    }
//                }
//            }
//        }
        
//        if(requestCode == 1 || requestCode == RESULT_OK || data != null || data.getData() != null){
//            selecteduri = data.getData();
//
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(selecteduri);
//                bitmap = BitmapFactory.decodeStream(inputStream);
//                PP.setImageBitmap(bitmap);
//                imageStore(bitmap);
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,stream);

        byte [] imagebyte = stream.toByteArray();

        encodImage = android.util.Base64.encodeToString(imagebyte, Base64.DEFAULT);
    }

    private void askpermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }else {
            openCamera();
        }

//        PermissionListener permissionListener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//
//                chooseImage();
//
//            }
//
//            @Override
//            public void onPermissionDenied(List<String> deniedPermission) {
//
//                Toast.makeText(SettingActivity.this, "Permission required", Toast.LENGTH_SHORT).show();
//
//            }
//        };
//        TedPermission.create()
//                .setPermissionListener(permissionListener)
//                .setPermissions(Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE)
//                .check();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERM_CODE) {
            if(grantResults.length < 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();
            }else {
                Toast.makeText(this, "Perlu izin untuk menggunakan kamera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
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
                    profil = user.getProfil_pelanggan();
                    if (profil != null) {
                        encodImage = profil;
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.urlUpdateProf, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Gson gson = new Gson();
//                UserRespon userRespon = gson.fromJson(response.toString(), UserRespon.class);

               try {
                   JSONObject jsonObj = new JSONObject(response);
                   int code = jsonObj.getInt("code");
                   String status = jsonObj.getString("status");
                   if(code==200){
                       SharedPreferences.Editor editor = sharedPreferences.edit();
                       editor.putString("username", Username);
                       editor.apply();
                       //getData();
                       Toast.makeText(SettingActivity.this, status, Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                       startActivity(intent);
                       finish();
                   }else if (code == 400){
                       Toast.makeText(SettingActivity.this, status, Toast.LENGTH_SHORT).show();
                   } else if (code == 201) {
                       SharedPreferences.Editor editor = sharedPreferences.edit();
                       editor.putString("username", Username);
                       editor.apply();
                       //getData();
                       Toast.makeText(SettingActivity.this, status, Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                       startActivity(intent);
                       finish();

                   } else {
                       Toast.makeText(SettingActivity.this, status, Toast.LENGTH_SHORT).show();
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
                params.put("profpel", encodImage);
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