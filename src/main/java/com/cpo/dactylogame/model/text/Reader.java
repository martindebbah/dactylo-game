package com.cpo.dactylogame.model.text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader extends WordIterator {

    private File file;
    private Scanner sc;

    /**
     * Un objet permettant de lire un fichier texte mot par mot
     * @param pathname Le fichier texte à lire
     */
    public Reader(String pathname) throws FileNotFoundException {
        this.file = new File(pathname);
        this.sc = new Scanner(file);
        sc.useDelimiter(" |\n");
    }

    @Override
    public boolean hasNext() {
        return sc.hasNext();
    }

    @Override
    public String next() {
        return sc.next();
    }

}