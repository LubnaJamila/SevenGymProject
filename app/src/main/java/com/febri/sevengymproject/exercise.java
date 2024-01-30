package com.febri.sevengymproject;

import java.io.Serializable;

public class exercise implements Serializable {
    String id_paket;
    String nama_paket;
//    String gambar;

    public exercise(String id_paket, String nama_paket) {
        this.id_paket = id_paket;
        this.nama_paket = nama_paket;
//        this.gambar = gambar;
    }

    public String getId_paket() {
        return id_paket;
    }

    public void setId_paket(String id_paket) {
        this.id_paket = id_paket;
    }

    public String getNama_paket() {
        return nama_paket;
    }

    public void setExercise(String nama_paket) {
        this.nama_paket = nama_paket;
    }

//    public String getGambar() {
//        return gambar;
//    }
//
//    public void setGambar(String gambar) {
//        this.gambar = gambar;
//    }
}
