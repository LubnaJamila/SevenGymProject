package com.febri.sevengymproject;

public class customexercise {
    private String judul;
    private int foto;

    public customexercise(String judul, int foto) {
        this.judul = judul;
        this.foto = foto;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
