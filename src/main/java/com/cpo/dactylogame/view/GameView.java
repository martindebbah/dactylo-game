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

    public void drawWord(Graphics g, String word, int x, int y) {
        g.setFont(nicoPaintReg);
        g.setColor(Color.YELLOW);
        g.setFont(g.getFont().deriveFont(30f));

        char[] c = word.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if(game.getListener().getGoodOrBadChar()[game.getListener().getCurrentWordIndex()][i] == 1){
                g.setColor(Color.GREEN);
            }
            else if(game.getListener().getGoodOrBadChar()[game.getListener().getCurrentWordIndex()][i] == -1){
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.YELLOW);
            }

            g.drawString(String.valueOf(c[i]), x, y);
            x += 25;
        }
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
            //g.setFont(nicoPaintReg);
            g.setColor(Color.YELLOW);
            g.setFont(g.getFont().deriveFont(25f));

            for(int i = 0; i < game.getListener().getGoodOrBadChar().length; i++){
                for(int j = 0; j < game.getListener().getGoodOrBadChar()[i].length; j++){
                    if(game.getListener().getGoodOrBadChar()[i][j] == 1){
                        g.setColor(Color.GREEN);
                    }
                    else if(game.getListener().getGoodOrBadChar()[i][j] == -1){
                        g.setColor(Color.RED);
                    }
                    else {
                        g.setColor(Color.YELLOW);
                    }
                    g.drawString(game.getListener().getText().get(i).charAt(j) + "", x, y);
                    if(game.getListener().getText().get(i).charAt(j) == 'i' || game.getListener().getText().getWords().get(i).charAt(j) == 'l') x += 12;
                    else if(game.getListener().getText().get(i).charAt(j) == 'm') x += 27;
                    else x += 20;
                    if(game.getListener().getGoodOrBadChar()[i].length-1 == j && i == game.getListener().getCurrentWordIndex()){
                        for(int k = 0; k < game.getListener().getErrorWord().length(); k++){
                            g.setColor(Color.RED);
                            g.drawString(game.getListener().getErrorWord().charAt(k) + "", x, y);
                            if(game.getListener().getErrorWord().charAt(k) == 'i' || game.getListener().getErrorWord().charAt(k) == 'l') x += 14;
                            else if(game.getListener().getErrorWord().charAt(k) == 'm') x += 27;
                            else x += 22;
                        }
                    }
                }
                x += 30;
                if(i != 0 && i % 4 == 0){
                    x = rect.x;
                    y += 30;
                }
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
                drawWord(g, t.get(i), game.getX(i), game.getY(i));
            }
        }

    }

}
