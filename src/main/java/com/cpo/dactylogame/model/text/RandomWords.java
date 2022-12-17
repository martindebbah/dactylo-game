package com.cpo.dactylogame.model.text;

import java.util.Random;

public class RandomWords extends WordIterator {

    private String[] words = {"mot", "test"};
    private Random random = new Random();

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
        return words[random.nextInt(words.length)];
    }
    
}
