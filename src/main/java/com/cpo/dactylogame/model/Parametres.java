package com.cpo.dactylogame.model;

public class Parametres {

    private String text;
    private double speed;
    private int bonusFreq;
    private int malusFreq;
    
    public Parametres() {
        this.text = "";
        this.speed = 1;
        this.bonusFreq = 0;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    public int getBonusFreq() {
        return bonusFreq;
    }

    public void setBonusFreq(int bonusFreq) {
        this.bonusFreq = bonusFreq;
    }
    
    public int getMalusFreq() {
        return malusFreq;
    }

    public void setMalusFreq(int malusFreq) {
        this.malusFreq = malusFreq;
    }
    
}
