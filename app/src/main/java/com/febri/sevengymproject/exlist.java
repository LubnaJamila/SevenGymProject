package com.febri.sevengymproject;

public class exlist {
//    String id_paket_exc;
//    String id_paket;
    String id_exercise;
//    String waktu_set;
//    String repetisi;
    String nama_paket;
    String nama_exercise;
    String gambar;
    String posisi_awal;
    String posisi_badan;
    String gerakan;

    public exlist() {
//        this.id_paket_exc = id_paket_exc;
//        this.id_paket = id_paket;
//        this.id_exercise = id_exercise;
////        this.waktu_set = waktu_set;
////        this.repetisi = repetisi;
//        this.nama_paket = nama_paket;
//        this.nama_exercise = nama_exercise;
//        this.gambar = gambar;
//        this.posisi_awal = posisi_awal;
//        this.posisi_badan = posisi_badan;
//        this.gerakan = gerakan;
    }

    public exlist(String id_exercise, String nama_paket, String nama_exercise, String gambar, String posisi_awal, String posisi_badan, String gerakan) {
        this.id_exercise = id_exercise;
        this.nama_paket = nama_paket;
        this.nama_exercise = nama_exercise;
        this.gambar = gambar;
        this.posisi_awal = posisi_awal;
        this.posisi_badan = posisi_badan;
        this.gerakan = gerakan;
    }
    //    public String getId_paket_exc() {
//        return id_paket_exc;
//    }
//
//    public void setId_paket_exc(String id_paket_exc) {
//        this.id_paket_exc = id_paket_exc;
//    }
//
//    public String getId_paket() {
//        return id_paket;
//    }
//
//    public void setId_paket(String id_paket) {
//        this.id_paket = id_paket;
//    }

    public String getId_exercise() {
        return id_exercise;
    }

    public void setId_exercise(String id_exercise) {
        this.id_exercise = id_exercise;
    }

//    public String getWaktu_set() {
//        return waktu_set;
//    }
//
//    public void setWaktu_set(String waktu_set) {
//        this.waktu_set = waktu_set;
//    }
//
//    public String getRepetisi() {
//        return repetisi;
//    }
//
//    public void setRepetisi(String repetisi) {
//        this.repetisi = repetisi;
//    }

    public String getNama_paket() {
        return nama_paket;
    }

    public void setNama_paket(String nama_paket) {
        this.nama_paket = nama_paket;
    }

    public String getNama_exercise() {
        return nama_exercise;
    }

    public void setNama_exercise(String nama_exercise) {
        this.nama_exercise = nama_exercise;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getPosisi_awal() {
        return posisi_awal;
    }

    public void setPosisi_awal(String posisi_awal) {
        this.posisi_awal = posisi_awal;
    }

    public String getPosisi_badan() {
        return posisi_badan;
    }

    public void setPosisi_badan(String posisi_badan) {
        this.posisi_badan = posisi_badan;
    }

    public String getGerakan() {
        return gerakan;
    }

    public void setGerakan(String gerakan) {
        this.gerakan = gerakan;
    }
}
