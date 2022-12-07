package com.cpo.dactylogame.model;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Text {

    private LinkedList<String> words;
    private static int BUFFERSIZE = 15;
    private Reader reader;
    
    public Text(String text) throws FileNotFoundException {
        this.words = new LinkedList<String>();
        this.reader = new Reader("resources/textes/" + text + ".txt");
    }

    /**
     * Ajoute le prochain mot à la liste
     */
    public void addWord() {
        if (words.size() >= BUFFERSIZE)
            return;
        if (reader.hasNext())
            words.add(reader.next());
    }

    /**
     * Ajoute une chaîne de caractère à la liste
     * @param str La chaîne de caractère à ajouter
     */
    public void addWord(String str) {
        if (words.size() >= BUFFERSIZE)
            return; // force();
        words.add(str);
    }

    /**
     * 
     * @return Le mot en cours d'écriture
     */
    public String currentWord() {
        if (!words.isEmpty())
            return words.getFirst();
        return null;
    }

    /**
     * Retire le premier mot de la liste
     * @return Le premier mot de la liste
     */
    public String removeFirst() {
        if (!words.isEmpty())
            return words.removeFirst();
        return null;
    }

    /**
     * 
     * @return True si tous les mots ont été écrits
     */
    public boolean isEmpty() {
        return words.isEmpty() && !reader.hasNext();
    }

    /**
     * Peut-être inutile
     * @return True si le buffer est plein ou qu'on ne peut plus lire de mot
     */
    public boolean isFull() {
        return words.size() >= BUFFERSIZE || !reader.hasNext();
    }

    /**
     * 
     * @return Le buffer contenant les 15 prochains mots à écrire
     */
    public String getBuffer() {
        String buf = "";
        for (int i = 0; i < BUFFERSIZE; i++) {
            if (i == words.size())
                break;
            buf += (i == 0 ? "" : " ") + words.get(i);
        }
        return buf;
    }

    
}
