package com.cpo.dactylogame.model.text;

import java.util.Iterator;

public abstract class WordIterator implements Iterator<String> {

    protected final int NCIT = 10; // Nombre de fichier de citations

    /**
     * 
     * @return True si peut lire un autre mot
     */
    public abstract boolean hasNext();

    /**
     * 
     * @return Le prochain mot à écrire
     */
    public abstract String next();
    
}
