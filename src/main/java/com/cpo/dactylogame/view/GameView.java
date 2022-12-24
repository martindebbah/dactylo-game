package com.cpo.dactylogame.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.cpo.dactylogame.model.game.Game;
import com.cpo.dactylogame.model.game.Solo;
import com.cpo.dactylogame.model.text.Text;

public abstract class GameView extends JPanel {
    
    protected Game game;

    protected Font nicoPaintReg;
    private boolean gameOver = false;

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
        setLayout(new GridBagLayout());

        game.start();
    }

    public void gameOver() {
        gameOver = true;

        JButton back = new JButton("Retour au menu principal");
        back.addActionListener(e -> {
            game.getWindow().setMenu();
        });

        GridBagConstraints gbc = new GridBagConstraints();
        add(back, gbc);
    }

    /**
     * Affiche le jeu en fonction du mode de jeu
     * @param g the Graphics object to protect
     */
    public abstract void draw(Graphics g);

    /**
     * Affiche la partie finie
     * @param g the Graphics object to protect
     */
    public abstract void drawGameOver(Graphics g);

    /**
     * Dessine un mot sur l'écran
     * @param g the Graphics object to protect
     * @param word Le mot à dessiner
     * @param x La coordonnée x en pixels
     * @param y La coordonnée y en pixels
     * @param current True si le mot est celui courant
     * @return La coordonnée x de la fin du mot
     */
    public int drawWord(Graphics g, String word, int x, int y, boolean current) {
        char[] c = word.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (current) {
                switch (game.getListener().getGoodOrBadChar()[i]) {
                    case -1:
                        g.setColor(Color.RED);
                        break;
                    case 1:
                        g.setColor(Color.GREEN);
                        break;
                    default:
                        g.setColor(Color.YELLOW);
                }
            }

            g.drawString(String.valueOf(c[i]), x, y);
            if (c[i] == 'i' || c[i] == 'l')
                x += 12;
            else if (c[i] == 'm')
                x += 27;
            else
                x += 20;
        }
        if (!current)
            return x;

        for(int k = 0; k < game.getListener().getErrorWord().length(); k++){
            g.setColor(Color.RED);
            g.drawString(game.getListener().getErrorWord().charAt(k) + "", x, y);
            if (game.getListener().getErrorWord().charAt(k) == 'i' || game.getListener().getErrorWord().charAt(k) == 'l')
                x += 14;
            else if (game.getListener().getErrorWord().charAt(k) == 'm')
                x += 27;                        
            else
                x += 22;
        }

        return x;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameOver)
            draw(g);
        else
            drawGameOver(g);
    }

    static class NormalView extends GameView {

        private Rectangle rect;

        public NormalView(Game game) {
            super(game);
            rect = new Rectangle(110, 200, 500, 500);
        }

        @Override
        public void draw(Graphics g) {
            g.setFont(g.getFont().deriveFont(25f));
            
            int x = rect.x;
            int y = rect.y;
            
            g.setColor(Color.YELLOW);
            x = drawWord(g, game.getListener().getCurrentWord(), x, y, true);
            g.setColor(Color.YELLOW);
            
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

        @Override
        public void drawGameOver(Graphics g) {
            drawWord(g, "Partie finie", 500, 500, false);
        }
        
    }

    static class SoloView extends GameView {

        public SoloView(Game game) {
            super(game);
        }

        @Override
        public void draw(Graphics g) {
            g.setFont(g.getFont().deriveFont(25f));
            
            Text t = game.getListener().getText();
            for (int i = 0; i < t.getNbWords(); i++) {
                g.setColor(Color.YELLOW);
                int x = drawWord(g, t.get(i), game.getX(i), game.getY(i), i == 0);
                if (i == 0)
                    g.drawRect(game.getX(i) - 5, game.getY(i) - 25, x - game.getX(i) + 5, 35);

                g.setColor(new Color(14, 204, 216));
                if (((Solo) game).isBonus(i))
                    g.drawLine(game.getX(i) - 5, game.getY(i) + 10, x, game.getY(i) + 10);
            }
            // g.drawRect(150, 500, 10, 10);
            // g.drawRect(850, 500, 10, 10);

            g.setColor(Color.YELLOW);
            drawWord(g, "Points de vie restants : " + ((Solo) game).getHp() + " | Niveau " + ((Solo) game).getLevel(),
            100, 650, false);
        }

        @Override
        public void drawGameOver(Graphics g) {
            int nWritten = ((Solo) game).getNbWords();
            int level = ((Solo) game).getLevel();

            g.setFont(g.getFont().deriveFont(25f));
            g.setColor(Color.YELLOW);
            
            drawWord(g, "Vous avez atteint le niveau " + level, 150, 150, false);
            drawWord(g, "Vous avez écrit " + nWritten + " mots correctement", 170, 170, false);
        }

    }

}
