package com.cpo.dactylogame.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Optional;

import com.cpo.dactylogame.model.text.Text;

public class Listener extends KeyAdapter {

    Optional<Stat> stat;
    private Text text; // différent en fonction du mode de jeu
    private String currentWord = "";
    private int currentWordIndex = 0;
    private int index = 0;
    private int[][] goodOrBadChar;
    private int cptError = 0;
    private String errorWord = "";
    //private LinkedList<String> errorWord = new LinkedList<String>();

    public Listener(Text text) {
        this.stat = Optional.empty();
        this.text = text;
    }

    // Si char == " " -> validation du mot
    // Créer une fonction validateWord() pour forcer la validation du mot courant
    // " " -> validateWord()
    // Et en mode jeu, ajouter un mot à la file pleine appellera aussi cette fonction
    @Override
    public void keyPressed(KeyEvent key) {
        if (currentWord.equals(""))
            return;
        long time = System.currentTimeMillis();
        if(currentWord.charAt(index) == ' '){
            if(key.getKeyCode() == KeyEvent.VK_SPACE){
                goodChar();
            }
            else if(key.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                if(errorWord.length() > 0){
                    errorWord = errorWord.substring(0, errorWord.length() - 1);
                    goodOrBadChar[currentWordIndex][index] = 0;
                } else {
                    if(index > 0) index--;
                    goodOrBadChar[currentWordIndex][index] = 0;
                }
            }
            else{
                badChar();
                errorWord += key.getKeyChar();
            }
        }
        else if(key.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            if(index > 0) index--;
            goodOrBadChar[currentWordIndex][index] = 0;
        }
        else if (key.getKeyChar() == currentWord.charAt(index)){
            goodChar();
        }
        else {
            badChar();
            index++;
            if (index == currentWord.length()) 
                refresh();
        }
        add(time); // Pour tout char != del
        //System.out.println("index: " + index);
        /*
         * ajouter une condition pour espace -> valider le mot
         * et pour backspace -> supprimer le dernier char + index--
         */
    }

    public void initGame(){
        String word = text.getWords().get(currentWordIndex);
        currentWord = word;
        goodOrBadChar = new int[text.getWords().size()][];
        for(int i = 0; i < goodOrBadChar.length; i++)
            goodOrBadChar[i] = new int[text.getWords().get(i).length()];
        index = 0;
    }

    public void goodChar(){
        goodOrBadChar[currentWordIndex][index] = 1;
        index++;
        if (index == currentWord.length()) 
            refresh();
    }

    public void badChar(){
        goodOrBadChar[currentWordIndex][index] = -1;
        cptError++;
    }

    public void refresh() {
        currentWordIndex++;
        if (currentWordIndex == text.getWords().size()) {
            currentWord = "";
            return;
        }
        String word = text.getWords().get(currentWordIndex);
        currentWord = word;
        index = 0;
        System.out.println("refresh");
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

    public int[][] getGoodOrBadChar() {
        return goodOrBadChar;
    }

    public int getCptError() {
        // remettre le nombre à 0 avant de le retourner ?
        return cptError;
    }

    public int getCurrentWordIndex() {
        return currentWordIndex;
    }

    public String getErrorWord() {
        return errorWord;
    }
    
}
