package com.cpo.dactylogame.model;

/**
 * Peut être supprimé
 */

import java.awt.*;
import java.util.LinkedList;

import com.cpo.dactylogame.view.GamePanel;

public class AncienReader {

    private GamePanel gp;
    private String currentWord;
    private LinkedList<String> words = new LinkedList<String>();
    private boolean[] goodChar;
    private boolean[] badChar;
    private int indexWord = 0;

    public AncienReader(GamePanel gp){
        this.gp = gp;

        words.add("test");
        words.add("test2");
        words.add("test3");
        words.add("test4");
        words.add("test5");
        words.add("test6");
        words.add("test7");
        words.add("test8");
        words.add("test9");

        currentWord = words.removeFirst();
        goodChar = new boolean[currentWord.length()];
        badChar = new boolean[currentWord.length()];
    }

    public void checkChar(char c){
        if(c == currentWord.charAt(indexWord)){
            goodChar[indexWord] = true;
            indexWord++;
        }else{
            badChar[indexWord] = true;
        }

        if(indexWord == currentWord.length()){
            indexWord = 0;
            if(words.size() > 0) currentWord = words.removeFirst();
            else gp.setGameState(GameState.GAMEOVER);
            goodChar = new boolean[currentWord.length()];
            badChar = new boolean[currentWord.length()];
        }
    }

    public void draw(Graphics g){
        int x = 400;
        int y = 200;
        g.setFont(g.getFont().deriveFont(48f));
        char[] chars = currentWord.toCharArray();
        for(int i = 0; i < goodChar.length; i++){
            if(goodChar[i]){
                g.setColor(Color.GREEN);
            }
            else if(badChar[i]){
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.BLACK);
            }
            g.drawString(String.valueOf(chars[i]), x, y);
            x += 50;
        }
    }
}
