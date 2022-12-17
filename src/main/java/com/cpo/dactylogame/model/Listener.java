package com.cpo.dactylogame.model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

public class Listener extends KeyAdapter {

    private Text text; // différent en fonction du mode de jeu
    private String currentWord;
    private int index = 0;
    private boolean[] goodChar;
    private boolean[] badChar;
    private int cptError = 0;

    public Listener() {
        try {
            this.text = new Text("lotr");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.currentWord = "";
    }

    @Override
    public void keyTyped(KeyEvent key) {
        if (currentWord.equals(""))
            return;
        if (key.getKeyChar() == currentWord.charAt(index)){
            goodChar[index] = true;
            index++;
            if (index == currentWord.length()) 
                refresh();
        }
        else {
            badChar[index] = true;
            cptError++;
        }
    }

    public void refresh() {
        if (text.isEmpty())
            gameOver();
        currentWord = text.removeFirst();
        goodChar = new boolean[currentWord.length()];
        badChar = new boolean[currentWord.length()];
        index = 0;
    }

    public void gameOver() {
        System.out.println("Partie terminée");
        System.exit(0);
    }
    
}
