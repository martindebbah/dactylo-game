package com.cpo.dactylogame.model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

public class Listener extends KeyAdapter {

    private Text text;
    private String currentWord;
    private String typedWord;

    public Listener() {
        try {
            this.text = new Text("lotr");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.currentWord = "";
        this.typedWord = "";
    }

    @Override
    public void keyTyped(KeyEvent key) {
        if (currentWord.equals(""))
            return;

        typedWord += key.getKeyChar();
    }

    public void refresh() {
        if (text.isEmpty())
            gameOver();
        typedWord = "";
        currentWord = text.removeFirst();
    }

    public void gameOver() {
        System.out.println("Partie terminée");
        System.exit(0);
    }
    
}
