package com.cpo.dactylogame.model;

import java.util.LinkedList;

public class Text {

    private LinkedList<String> words;
    private static int BUFFERSIZE = 15;
    private Reader reader;

    public Text(String text) {
        this.words = new LinkedList<String>();
        this.reader = new Reader("resources/textes/" + text + ".txt");
    }

    /**
     * 
     * @return Le mot en cours d'écriture
     */
    public String currentWord() {
        return words.getFirst();
    }

    /**
     * 
     * @return Le premier mot de la liste
     */
    public String next() {
        return words.removeFirst();
    }

    /**
     * 
     * @return True si tous les mots ont été écrits
     */
    public boolean isEmpty() {
        return words.isEmpty();
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
            buf += words.get(i);
        }
        return buf;
    }

    /**
     * Ajoute le prochain mot à la liste
     */
    public void addWordToBuffer() {
        if (words.size() >= BUFFERSIZE)
            return;
        if (reader.hasNext())
            words.add((String) reader.next());
    }
    
}
