package com.cpo.dactylogame.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Reader implements Iterator {

    private File file;
    private Scanner sc;
    private String[] words;

    /**
     * Un objet permettant de lire un fichier texte mot par mot
     * @param pathname Le fichier texte à lire
     */
    public Reader(String pathname) {
        this.file = new File(pathname);
        try {
            this.sc = new Scanner(file);
            sc.useDelimiter(" ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return True si peut lire un autre mot
     */
    @Override
    public boolean hasNext() {
        return sc.hasNext();
    }

    /**
     * 
     * @return Le prochain mot du fichier
     */
    @Override
    public Object next() {
        return sc.next();
    }



}