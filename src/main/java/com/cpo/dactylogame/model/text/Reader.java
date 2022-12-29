package com.cpo.dactylogame.model.text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader extends WordIterator {

    private File file;
    private Scanner sc;

    /**
     * Un objet permettant de lire un fichier texte mot par mot
     * @param text Le fichier texte à lire
     */
    public Reader(String text) throws FileNotFoundException {
        if (text.equals(""))
            text = "lotr";
        this.file = new File("resources/textes/" + text + ".txt");
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