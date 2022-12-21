package com.cpo.dactylogame.model;

import com.cpo.dactylogame.model.text.Text;

public class Parametres {

    private Text text;
    private double speed;
    private int bonusFreq;
    private int malusFreq;
    
    public Parametres() {
        this.text = new Text("");
        this.speed = 1;
        this.bonusFreq = 0;
    }
    
    public Text getText() {
        return text;
    }
    
    public void setText(Text text) {
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
