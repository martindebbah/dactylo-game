package com.cpo.dactylogame.view;

import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.cpo.dactylogame.model.game.Game;

public abstract class GameView extends JPanel{
    
    protected Game game;

    protected Font nicoPaintReg;

    public GameView(Game game) {
        this.game = game;

        try{
            InputStream is = new FileInputStream(new File("resources/font/NicoPaint-Regular.ttf"));
            nicoPaintReg = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        setSize(game.getWindow().getSize());
        setBackground(new Color(50, 50, 50));

        game.start();
    }

    /*
     * Méthode qui affiche le jeu en fonction du mode de jeu
     */
    public abstract void draw(java.awt.Graphics g);

    static class NormalView extends GameView {

        public NormalView(Game game) {
            super(game);
        }

        @Override
        public void draw(java.awt.Graphics g) {
            int x = 150;
            int y = 200;
            g.setFont(nicoPaintReg);
            g.setColor(Color.YELLOW);
            g.setFont(g.getFont().deriveFont(30f));

            String words = game.getListener().getCurrentWord();
            char[] chars = words.toCharArray();

            for(int i = 0; i < chars.length; i++){
                if(chars[i] == '\n'){
                    y += 40;
                    x = 150;
                }
                else if(game.getListener().getGoodChar()[i]){
                    g.setColor(Color.GREEN);
                }
                else if(game.getListener().getBadChar()[i]){
                    g.setColor(Color.RED);
                }
                else {
                    g.setColor(Color.YELLOW);
                }
                if(chars[i] != '\n'){
                    g.drawString(String.valueOf(chars[i]), x, y);
                    x += 25;
                }
            }
        }

        @Override
        public void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            draw(g);
        }
        
    }

    static class SoloView extends GameView {

        public SoloView(Game game) {
            super(game);
        }

        @Override
        public void draw(java.awt.Graphics g) {
            
        }
        
    }

}
