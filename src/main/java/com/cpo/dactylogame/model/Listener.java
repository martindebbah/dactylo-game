package com.cpo.dactylogame.model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.cpo.dactylogame.model.text.Text;

public class Listener extends KeyAdapter {

    private Text text; // différent en fonction du mode de jeu
    private String currentWord;
    private int index = 0;
    private boolean[] goodChar;
    private boolean[] badChar;
    private int cptError = 0;

    public Listener(Text text) {
        this.text = text;
        currentWord = "carotte";
        goodChar = new boolean[currentWord.length()];
        badChar = new boolean[currentWord.length()];
        index = 0;
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
            System.out.print(key.getKeyChar()+" ");
        }
        else {
            badChar[index] = true;
            cptError++;
        }
    }

    public void refresh() {
        if (text.isEmpty())
            gameOver();
        currentWord = "rien";
        goodChar = new boolean[currentWord.length()];
        badChar = new boolean[currentWord.length()];
        index = 0;
    }

    public void gameOver() {
        System.out.println("Partie terminée");
        System.exit(0);
    }

    public Text getText() {
        return text;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public boolean[] getGoodChar() {
        return goodChar;
    }

    public boolean[] getBadChar() {
        return badChar;
    }

    public int getCptError() {
        return cptError;
    }
    
}
