package com.cpo.dactylogame.model;

public class Player {

    private String name;
    private int hp;

    public Player(String name) {
        this.name = name;
        this.hp = 100;
    }

    public void loseHp(int dmg) {
        hp -= dmg;
    }

    public void heal(int h) {
        hp += h;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }
    
}
