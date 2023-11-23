package com.febri.sevengymproject;

public class User {
    Integer id_user;
    String username;
    String password;
    Integer id_level;
    Integer id_pelaggan;
    String nama_pelanggan;
    String tb;
    String bb;
    String profil_pelanggan;
    String nohp;
    String tanggal_lahir;
    String jenis_kelamin;
    Integer id_trainner;

    public User(Integer id_user, String username, String password, Integer id_level, Integer id_pelaggan, String nama_pelanggan, String tb, String bb, String profil_pelanggan, String nohp, String tanggal_lahir, String jenis_kelamin, Integer id_trainner) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.id_level = id_level;
        this.id_pelaggan = id_pelaggan;
        this.nama_pelanggan = nama_pelanggan;
        this.tb = tb;
        this.bb = bb;
        this.profil_pelanggan = profil_pelanggan;
        this.nohp = nohp;
        this.tanggal_lahir = tanggal_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.id_trainner = id_trainner;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId_level() {
        return id_level;
    }

    public void setId_level(Integer id_level) {
        this.id_level = id_level;
    }

    public Integer getId_pelaggan() {
        return id_pelaggan;
    }

    public void setId_pelaggan(Integer id_pelaggan) {
        this.id_pelaggan = id_pelaggan;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
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

    public String getProfil_pelanggan() {
        return profil_pelanggan;
    }

    public void setProfil_pelanggan(String profil_pelanggan) {
        this.profil_pelanggan = profil_pelanggan;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public Integer getId_trainner() {
        return id_trainner;
    }

    public void setId_trainner(Integer id_trainner) {
        this.id_trainner = id_trainner;
    }
}
