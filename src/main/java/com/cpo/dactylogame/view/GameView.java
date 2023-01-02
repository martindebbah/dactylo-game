package com.cpo.dactylogame.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;

import com.cpo.dactylogame.model.Stat;
import com.cpo.dactylogame.model.game.*;
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
        Color defaultColor = g.getColor();
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
                        g.setColor(defaultColor);
                }
                if(i == game.getListener().getIndex()){
                    g.setColor(Color.WHITE);
                    g.drawLine(x, y + 5, x+10, y + 5);
                    g.setColor(defaultColor);
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
            g.setFont(g.getFont().deriveFont(25f));
            g.setColor(Color.YELLOW);
            drawWord(g, "Partie terminée", (game.getWindow().getWidth() - 15 * 20) / 2, 100, false);

            DecimalFormat df = new DecimalFormat("0.00");

            Stat stat = ((Normal) game).getStat();
            String vit = "Nombre de mots par minutes : " + df.format(stat.getVit());
            String prec = "Précision : " + df.format(stat.getPrec()) + "%";
            String reg = "Régularité : " + df.format(stat.getReg());

            drawWord(g, vit, (game.getWindow().getWidth() - vit.length() * 20) / 2, 200, false);
            drawWord(g, prec, (game.getWindow().getWidth() - prec.length() * 20) / 2, 250, false);
            drawWord(g, reg, (game.getWindow().getWidth() - reg.length() * 20) / 2, 300, false);
        }
        
    }

    static class JeuView extends GameView {

        public JeuView(Game game) {
            super(game);
        }

        @Override
        public void draw(Graphics g) {
            g.setFont(g.getFont().deriveFont(25f));
            
            Text t = game.getListener().getText();
            for (int i = 0; i < t.getNbWords(); i++) {
                g.setColor(Color.YELLOW);
                if (((Jeu) game).isBonus(i))
                    g.setColor(new Color(14, 204, 216));
                if (((Jeu) game).isMalus(i))
                    g.setColor(new Color(234, 130, 5));
                int x = drawWord(g, t.get(i), game.getX(i), game.getY(i), i == 0);
                if (i == 0)
                    g.drawRect(game.getX(i) - 5, game.getY(i) - 25, x - game.getX(i) + 5, 35);
            }

            g.setColor(Color.YELLOW);
            drawWord(g, "Points de vie restants : " + ((Jeu) game).getHp() + " | Niveau " + ((Jeu) game).getLevel() +
            "(" + (100 - ((Jeu) game).getNbWords() % 100) + ")",
            100, 650, false);
        }

        @Override
        public void drawGameOver(Graphics g) {
            String level = "Vous avez atteint le niveau " + ((Jeu) game).getLevel();
            String nWritten = "Vous avez écrit " + ((Jeu) game).getNbWords() + " mots correctement";

            g.setFont(g.getFont().deriveFont(25f));
            g.setColor(Color.YELLOW);
            
            drawWord(g, level, (game.getWindow().getWidth() - level.length() * 20) / 2, 200, false);
            drawWord(g, nWritten, (game.getWindow().getWidth() - nWritten.length() * 20) / 2, 275, false);
        }

    }

}
