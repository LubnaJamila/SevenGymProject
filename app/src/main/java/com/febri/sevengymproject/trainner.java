package com.febri.sevengymproject;

public class trainner {
    String id_trainner;
    String tanggal_lahir;
    String deskripsi_pelatih;
    String tb;
    String bb;
    String jenis_kelamin;
    String profil_trainner;
    String nohp;
    String id_user;
    String id_level;
    String nama_lengkap;
    String harga_trainner;

    public trainner(String tanggal_lahir, String deskripsi_pelatih, String jenis_kelamin, String profil_trainner,  String nama_lengkap, String nohp, String harga_trainner) {
        this.tanggal_lahir = tanggal_lahir;
        this.deskripsi_pelatih = deskripsi_pelatih;
//        this.tb = tb;
//        this.bb = bb;
        this.jenis_kelamin = jenis_kelamin;
        this.profil_trainner = profil_trainner;
        this.nohp = nohp;
        this.nama_lengkap = nama_lengkap;
        this.harga_trainner = harga_trainner;
    }

    public String getId_trainner() {
        return id_trainner;
    }

    public void setId_trainner(String id_trainner) {
        this.id_trainner = id_trainner;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getDeskripsi_pelatih() {
        return deskripsi_pelatih;
    }

    public void setDeskripsi_pelatih(String deskripsi_pelatih) {
        this.deskripsi_pelatih = deskripsi_pelatih;
    }

    public String getTb() {
        return tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getProfil_trainner() {
        return profil_trainner;
    }

    public void setProfil_trainner(String profil_trainner) {
        this.profil_trainner = profil_trainner;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_level() {
        return id_level;
    }

    public void setId_level(String id_level) {
        this.id_level = id_level;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getHarga_trainner() {
        return harga_trainner;
    }

    public void setHarga_trainner(String harga_trainner) {
        this.harga_trainner = harga_trainner;
    }
}
