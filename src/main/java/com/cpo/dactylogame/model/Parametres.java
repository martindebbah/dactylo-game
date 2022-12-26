package com.cpo.dactylogame.model;

public class Parametres {

    private String text;
    private int bonusFreq; // Pourcentage d'apparition
    private int malusFreq; // Pourcentage d'apparition
    
    public Parametres() {
        this.text = "";
        this.bonusFreq = 25;
        this.malusFreq = 25;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
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
