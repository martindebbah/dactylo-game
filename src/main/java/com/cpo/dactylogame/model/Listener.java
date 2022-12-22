package com.cpo.dactylogame.model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Optional;

import com.cpo.dactylogame.model.text.Text;

public class Listener extends KeyAdapter {

    Optional<Stat> stat;
    private Text text; // différent en fonction du mode de jeu
    private String currentWord;
    private int index = 0;
    private boolean[] goodChar;
    private boolean[] badChar;
    private int cptError = 0;

    private boolean next = false;

    public Listener(Text text) {
        this.stat = Optional.empty();
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
        long time = System.currentTimeMillis();

        if (key.getKeyChar() == ' ') {
            next = true;
            refresh();

        }else if (key.getKeyCode() == 127) {
            
        }else if (key.getKeyChar() == currentWord.charAt(index)){
            goodChar[index] = true;
            index++;
            if (index == currentWord.length()) 
                refresh();

        }else {
            badChar[index] = true;
            cptError++;
            index++;
            if (index == currentWord.length()) 
                refresh();
        }

        add(time); // Pour tout char != del
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

    public void add(long time) {
        if (!stat.isPresent())
            return;
        stat.get().add(time);
    }

    public void supp() {
        if (!stat.isPresent())
            return;
        stat.get().supp();
    }

    public void setStat(Optional<Stat> stat) {
        this.stat = stat;
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

    /**
     * 
     * @return True si on doit passer au prochain mot
     */
    public boolean nextWord() {
        if (next) {
            next = false;
            return true;
        }
        return false;
    }
    
}
