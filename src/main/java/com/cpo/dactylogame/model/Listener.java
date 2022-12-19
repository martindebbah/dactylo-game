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
        currentWord = text.getBuffer();
        goodChar = new boolean[currentWord.length()];
        badChar = new boolean[currentWord.length()];
        index = 0;
    }

    // Si char == " " -> validation du mot
    // Créer une fonction validateWord() pour forcer la validation du mot courant
    // " " -> validateWord()
    // Et en mode jeu la file pleine appellera aussi cette fonction
    @Override
    public void keyTyped(KeyEvent key) {
        if (currentWord.equals(""))
            return;
        if (key.getKeyChar() == currentWord.charAt(index)){
            goodChar[index] = true;
            index++;
            if (index == currentWord.length()) 
                refresh(); // Pas forcément vu que si on tape pas " ", on continue d'éditer le mot actuel
        }
        else {
            badChar[index] = true;
            cptError++;
            index++;
            if (index == currentWord.length()) 
                refresh();
        }
        System.out.println("index: " + index);
    }

    public void refresh() {
        if (text.isEmpty()) // On vérifie ça dans la boucle de jeu normalement
            gameOver();
        currentWord = text.getBuffer(); // Ca devrait pas être currentWord() ?
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
