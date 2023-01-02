package com.cpo.dactylogame.model;

public class Parametres {

    private String text;
    private int bonusFreq; // Pourcentage d'apparition
    private int malusFreq; // Pourcentage d'apparition
    
    public Parametres() {
        this.text = "";
        setBonusFreq('r');
        setMalusFreq('r');
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

    public void setBonusFreq(char freq) {
        switch (freq) {
            case 'r': // Rare
                bonusFreq = 10;
                break;
            case 'c': // Courant
                bonusFreq = 25;
                break;
            case 'a': // Abondant
                bonusFreq = 50;
                break;
        }
    }
    
    public int getMalusFreq() {
        return malusFreq;
    }

    public void setMalusFreq(char freq) {
        switch (freq) {
            case 'r': // Rare
                bonusFreq = 25;
                break;
            case 'c': // Courant
                bonusFreq = 50;
                break;
            case 'a': // Abondant
                bonusFreq = 75;
                break;
        }
    }
    
}
