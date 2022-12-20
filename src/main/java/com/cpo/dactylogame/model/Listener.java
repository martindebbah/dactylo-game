package com.cpo.dactylogame.model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.cpo.dactylogame.model.text.Text;

public class Listener extends KeyAdapter {

    // Stat optionnel
    private Text text; // différent en fonction du mode de jeu
    private String currentWord;
    private int index = 0;
    private boolean[] goodChar;
    private boolean[] badChar;
    private int cptError = 0;

    public Listener(Text text) { // stat optionnel en arg
        this.text = text;
        currentWord = text.getBuffer(); // text.currentWord()
        goodChar = new boolean[currentWord.length()];
        badChar = new boolean[currentWord.length()];
        index = 0;
    }

    // Si char == " " -> validation du mot
    // Créer une fonction validateWord() pour forcer la validation du mot courant
    // " " -> validateWord()
    // Et en mode jeu, ajouter un mot à la file pleine appellera aussi cette fonction
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
            index++;
            if (index == currentWord.length()) 
                refresh();
        }
        System.out.println("index: " + index);
        /*
         * ajouter une condition pour espace -> valider le mot
         * et pour backspace -> supprimer le dernier char + index--
         */
    }

    public void refresh() {
        // text.removeFirst();
        currentWord = text.getBuffer(); // text.currentWord
        goodChar = new boolean[currentWord.length()];
        badChar = new boolean[currentWord.length()];
        index = 0;
        // mettre à jour le nombre d'erreur dans le mot
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
        // remettre le nombre à 0 avant de le retourner ?
        return cptError;
    }
    
}
