package com.cpo.dactylogame.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Optional;

import com.cpo.dactylogame.model.text.Text;

public class Listener extends KeyAdapter {

    Optional<Stat> stat; // Pour calculer les statistiques
    private Text text; // Le texte à écrire
    private String currentWord = ""; // Le mot en cours d'écriture
    private String lastWord; // Le dernier mot écrit
    private int index = 0; // L'index du prochain caractère à taper
    private int[] goodOrBadChar; // Le tableau représenant les caractères bien ou mal tapés
    private int cptError = 0; // Le nombre d'erreurs
    private int hpToHeal = 0; // Le nombre de points de vie à soigner (mode jeu)
    private boolean firstTry = true; // Si le mot a été tapé du premier coup (pour soigner en mode jeu)
    private String errorWord = ""; // Pour permettre de taper des erreurs si on appuie pas sur espace et qu'on dépasse
                                // la longueur du mot courant

    private boolean next = false; // Si on passe au prochain mot

    public Listener(Text text) {
        this.stat = Optional.empty();
        this.text = text;
    }
    
    @Override
    public void keyPressed(KeyEvent key) {
        if (currentWord.equals(""))
            return;
        long time = System.currentTimeMillis();
        if(key.getKeyCode() == KeyEvent.VK_SPACE){
            refresh();
        }
        else if(index >= currentWord.length()){
            if(key.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                if(errorWord.length() > 0){
                    errorWord = errorWord.substring(0, errorWord.length() - 1);
                } else {
                    if(index > 0) index--;
                    goodOrBadChar[index] = 0;
                }
                supp();
                // return;
            }
            else if(errorWord.length() <= 8){
                errorWord += key.getKeyChar();
            }
        }else if(key.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            if(index > 0) index--;
            goodOrBadChar[index] = 0;
            supp();
            // return;
        }
        else if (key.getKeyChar() == currentWord.charAt(index)){
            goodChar();
        }
        else {
            badChar();
        }

        add(time);
    }

    /**
     * Initialise le listener au début de la partie
     */
    public void initGame(){
        currentWord = text.currentWord();
        goodOrBadChar = new int[currentWord.length()];
        index = 0;
    }

    /**
     * Définit le caractère courant comme un bon
     * et incrémente l'index
     */
    public void goodChar(){
        goodOrBadChar[index] = 1;
        index++;
    }

    /**
     * Définit le caractère courant comme un mauvais
     * et incrémente l'index
     */
    public void badChar(){
        goodOrBadChar[index] = -1;
        index++;
    }

    /**
     * Met à jour le texte, calcule le nombre d'erreur et notifie qu'on passe au mot suivant
     */
    public void refresh() {
        text.removeFirst();
        next = true;
        lastWord = currentWord;
        calcError();
        if (text.getWords().size() != 0)
            refreshWord();
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

    /**
     * Calcule le nombre d'erreurs et le nombre de points de vie à soigner
     */
    private void calcError() {
        for (int i = 0; i < goodOrBadChar.length; i++)
            if (goodOrBadChar[i] == 1)
                hpToHeal++;
            else
                cptError++;
        cptError += errorWord.length();
    }

    /**
     * Ajoute des données aux potentielles statistiques
     * @param time Le temps, en milli-secondes, auquel survient l'action
     */
    public void add(long time) {
        if (!stat.isPresent())
            return;
        stat.get().add(time);
    }

    /**
     * Définis le mot courant comme "non-écrit du premier coup" et ajoute des données aux 
     * potentielles statistiques
     */
    public void supp() {
        firstTry = false;
        if (!stat.isPresent())
            return;
        stat.get().supp();
    }

    /**
     * Applique un objet Stat au listener
     * @param stat L'objet Stat à appliquer au listener
     */
    public void setStat(Optional<Stat> stat) {
        this.stat = stat;
    }

    /**
     * 
     * @return Le texte utilisé
     */
    public Text getText() {
        return text;
    }

    /**
     * 
     * @return Le mot en cours d'écriture
     */
    public String getCurrentWord() {
        return currentWord;
    }

    /**
     * 
     * @return Le mot qui vient d'être écrit
     */
    public String getLastWord() {
        return lastWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public int[] getGoodOrBadChar() {
        return goodOrBadChar;
    }

    /**
     * Retourne le nombre d'erreurs tapées dans le mot et remet la valeur à 0.
     * @return Le nombre d'erreurs
     */
    public int getCptError() {
        int x = cptError;
        cptError = 0;
        return x;
    }

    /**
     * Retourne le nombre de points de vie à soigner si le mot est écrit correctement du premier coup
     * et remet les valeurs de calcul d'origine.
     * @return Le nombre de points de vie à soigner
     */
    public int getHpToHeal() {
        int toHeal = hpToHeal;
        hpToHeal = 0;
        return toHeal;
    }

    /**
     * 
     * @return True si on a écrit le mot correctement du premier coup
     */
    public boolean isFirstTry() {
        boolean ft = firstTry;
        firstTry = true;
        return ft;
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
