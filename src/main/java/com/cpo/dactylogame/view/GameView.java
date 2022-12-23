package com.cpo.dactylogame.view;

import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.cpo.dactylogame.model.game.Game;
import com.cpo.dactylogame.model.text.Text;

public abstract class GameView extends JPanel{
    
    protected Game game;

    protected Font nicoPaintReg;

    public GameView(Game game) {
        this.game = game;

        try{
            InputStream is = new FileInputStream(new File("resources/font/m6x11.ttf"));
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

    public int drawWord(Graphics g, String word, int x, int y, boolean current) {
        //g.setFont(nicoPaintReg);
        g.setColor(Color.YELLOW);
        g.setFont(g.getFont().deriveFont(25f));

        char[] c = word.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if(current){
                if(game.getListener().getGoodOrBadChar()[i] == 1){
                    g.setColor(Color.GREEN);
                }
                else if(game.getListener().getGoodOrBadChar()[i] == -1){
                    g.setColor(Color.RED);
                }
                else {
                    g.setColor(Color.YELLOW);
                }
            }

            g.drawString(String.valueOf(c[i]), x, y);
            if(c[i] == 'i' || c[i] == 'l') x += 12;
            else if(c[i] == 'm') x += 27;
            else x += 20;
        }
        if (!current)
            return x;

        for(int k = 0; k < game.getListener().getErrorWord().length(); k++){
            g.setColor(Color.RED);
            g.drawString(game.getListener().getErrorWord().charAt(k) + "", x, y);
            if(game.getListener().getErrorWord().charAt(k) == 'i' || game.getListener().getErrorWord().charAt(k) == 'l') x += 14;
            else if(game.getListener().getErrorWord().charAt(k) == 'm') x += 27;                        
            else x += 22;
        }
        return x;
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    static class NormalView extends GameView {

        private Rectangle rect;

        public NormalView(Game game) {
            super(game);
            rect = new Rectangle(110, 200, 500, 500);
        }

        @Override
        public void draw(java.awt.Graphics g) {
            int x = rect.x;
            int y = rect.y;

            x = drawWord(g, game.getListener().getCurrentWord(), x, y, true);
            
            x += 20;
            for (int i = 1; i < game.getListener().getText().getNbWords(); i++) {
                String s = game.getListener().getText().get(i);
                if(x + s.length() > rect.x + rect.width){
                    x = rect.x;
                    y += 30;
                }
                x = drawWord(g, s, x, y, false);
                x += 20;
            }

        }
        
    }

    static class SoloView extends GameView {

        public SoloView(Game game) {
            super(game);
        }

        @Override
        public void draw(java.awt.Graphics g) {
            Text t = game.getListener().getText();
            for (int i = 0; i < t.getNbWords(); i++) {
                int x = drawWord(g, t.get(i), game.getX(i), game.getY(i), i == 0);
                if (i == 0)
                    g.drawRect(game.getX(i) - 5, game.getY(i) - 25, x - game.getX(i) + 5, 35);
            }
            g.drawRect(150, 500, 10, 10);
            g.drawRect(850, 500, 10, 10);
        }

    }

}
