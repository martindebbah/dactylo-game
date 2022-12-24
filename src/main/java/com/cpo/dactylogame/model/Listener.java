package com.cpo.dactylogame.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Optional;

import com.cpo.dactylogame.model.text.Text;

public class Listener extends KeyAdapter {

    Optional<Stat> stat;
    private Text text; // différent en fonction du mode de jeu
    private String currentWord = "";
    private int index = 0;
    private int[] goodOrBadChar;
    private int cptError = 0;
    private int hpToHeal = 0;
    private boolean firstTry = true;
    private String errorWord = "";
    //private LinkedList<String> errorWord = new LinkedList<String>();

    private boolean next = false;

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
        if(key.getKeyCode() == KeyEvent.VK_SPACE){
            refresh();
        }
        else if(index == currentWord.length()){
            if(key.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                if(errorWord.length() > 0){
                    errorWord = errorWord.substring(0, errorWord.length() - 1);
                } else {
                    if(index > 0) index--;
                    goodOrBadChar[index] = 0;
                }
                supp();
            }
            else if(errorWord.length() <= 8){
                cptError++;
                errorWord += key.getKeyChar();
            }
        }else if(key.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            if(index > 0) index--;
            goodOrBadChar[index] = 0;
            supp();
        }
        else if (key.getKeyChar() == currentWord.charAt(index)){
            goodChar();
        }
        else {
            badChar();
            index++;
        }

        add(time); // Pour tout char != del
        //System.out.println("index: " + index);
        /*
         * ajouter une condition pour espace -> valider le mot
         * et pour backspace -> supprimer le dernier char + index--
         */
    }

    public void initGame(){
        currentWord = text.currentWord();
        goodOrBadChar = new int[currentWord.length()];
        index = 0;
    }

    public void goodChar(){
        goodOrBadChar[index] = 1;
        index++;
    }

    public void badChar(){
        goodOrBadChar[index] = -1;
        cptError++;
    }

    public void refresh() {
        text.removeFirst();
        next = true;
        calcError();
        if (text.getWords().size() != 0)
            refreshWord();

        System.out.println("refresh");
        // mettre à jour le nombre d'erreur dans le mot
    }

    /**
     * Change le mot courant
     */
    public void refreshWord() {
        currentWord = text.currentWord();
        goodOrBadChar = new int[currentWord.length()];
        index = 0;
        errorWord = "";
    }

    private void calcError() {
        for (int i = 0; i < goodOrBadChar.length; i++)
            if (goodOrBadChar[i] == 1)
                hpToHeal++;
            else
                cptError++;
        cptError += errorWord.length();
    }

    public void add(long time) {
        if (!stat.isPresent())
            return;
        stat.get().add(time);
    }

    public void supp() {
        firstTry = false;
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

    public int[] getGoodOrBadChar() {
        return goodOrBadChar;
    }

    public int getCptError() {
        int x = cptError;
        cptError = 0;
        return x;
    }

    public int getHpToHeal() {
        int x = hpToHeal;
        hpToHeal = 0;
        firstTry = true;
        return x;
    }

    public String getErrorWord() {
        return errorWord;
    }

    public int getIndex() {
        return index;
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
