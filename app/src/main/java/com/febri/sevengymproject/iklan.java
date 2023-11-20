package com.febri.sevengymproject;

public class iklan {
    private String trainner;
    private int pict;

    public iklan(String trainner, int pict) {
        this.trainner = trainner;
        this.pict = pict;
    }

    public String getTrainner() {
        return trainner;
    }

    public void setTrainner(String trainner) {
        this.trainner = trainner;
    }

    public int getPict() {
        return pict;
    }

    public void setPict(int pict) {
        this.pict = pict;
    }
}
