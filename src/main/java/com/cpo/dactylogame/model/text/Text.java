package com.cpo.dactylogame.model.text;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Text {

    private LinkedList<String> words;
    private static int BUFFERSIZE = 15;
    private WordIterator iterator;
    
    /**
     * Crée un texte avec le fichier texte. Si on est en mode normal le fichier sera à écrire entièrement et dans l'ordre.
     * Sinon, crée un texte aléatoire à partir des mots présents dans le fichier.
     * @param text Le fichier texte
     * @param normal True si on est en partie normale
     */
    public Text(String text, boolean normal) {
        this.words = new LinkedList<String>();
        if (normal) {
            try {
                this.iterator = new Reader(text);
            }catch (FileNotFoundException e) {
                e .printStackTrace();
            }
        }else {
            this.iterator = new RandomWords(text);
        }
    }

    /**
     * Ajoute le prochain mot à la liste
     */
    public void addWord() {
        if (words.size() >= BUFFERSIZE)
            return;
        if (iterator.hasNext())
            words.add(iterator.next());
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
     * @return Le nombre de mots dans le buffer
     */
    public int getNbWords() {
        return words.size();
    }

    /**
     * 
     * @return True si tous les mots ont été écrits
     */
    public boolean isEmpty() {
        return words.isEmpty() && !iterator.hasNext();
    }

    /**
     *
     * @return True si le buffer est plein ou qu'on ne peut plus lire de mot
     */
    public boolean isFull() {
        return words.size() >= BUFFERSIZE || !iterator.hasNext();
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
            buf += (i == 0 ? "" : " ") + (i % 3 == 0 && i != 0 ? "\n" : "") + words.get(i);
        } // Pas forcément besoin des retours à la ligne
        return buf;
    }

    /**
     * 
     * @param index L'index du mot voulu
     * @return Le mot à l'index donné
     */
    public String get(int index) {
        return words.get(index);
    }

    /**
     * 
     * @return La taille maximale du buffer
     */
    public int getBufferSize() {
        return BUFFERSIZE;
    }

    public LinkedList<String> getWords() {
        return words;
    }
    
}
