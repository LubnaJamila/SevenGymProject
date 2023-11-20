package com.febri.sevengymproject;

public class exercise {
    private String exercise;
    private int gambar;

    public exercise(String exercise, int gambar) {
        this.exercise = exercise;
        this.gambar = gambar;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
