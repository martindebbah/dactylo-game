package com.cpo.dactylogame.model;

public class Player {

    private String name;
    private int hp;

    public Player(String name) {
        this.name = name;
        this.hp = 100;
    }

    /**
     * Le joueur perd dmg points de vie
     * @param dmg Le nombre de pv à perdre
     */
    public void loseHp(int dmg) {
        hp -= dmg;
    }

    /**
     * Soigne le joueur de h points de vie. Ne peut pas dépasser 100.
     * @param h Le nombre de pv à soigner
     */
    public void heal(int h) {
        hp += h;
        if (hp > 100)
            hp -= hp - 100;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }
    
}
