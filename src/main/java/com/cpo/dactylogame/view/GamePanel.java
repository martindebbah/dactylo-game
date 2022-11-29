package com.cpo.dactylogame.view;

import javax.swing.JPanel;

import com.cpo.dactylogame.model.AncienReader;
import com.cpo.dactylogame.model.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable, KeyListener{

    private Thread gameThread;
    private static final double FPS = 60;

    private GameState gameState = GameState.MENU;
    private Font nicoPaintReg;
    private boolean button1, button2;

    private AncienReader reader = new AncienReader(this);

    public GamePanel() {
        gameThread = new Thread(this);
        gameThread.start();

        try{
            InputStream is = getClass().getResourceAsStream("/com/cpo/dactylogame/resources/font/NicoPaint-Regular.ttf");
            nicoPaintReg = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){ // boucle de jeu à 60 fps
            
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){ // on update et repaint 60 fois par seconde
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update(){

    }

    public void menuDraw(Graphics g){
        int x = 450;
        int y = 100;
        String str = "Play";
        g.setFont(g.getFont().deriveFont(48f));

        g.drawString(str, x, y);
        if(button1) g.drawString(">", x-40, y);

        y += 100;

        str = "Exit";
        g.drawString(str, x, y);
        if(button2) g.drawString(">", x-40, y);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(nicoPaintReg);

        switch(gameState){
            case MENU:
                menuDraw(g);
                break;
            case NORMAL:
                reader.draw(g);
                break;
            case JEU:
                break;
            case MULTIJOUEUR:
                break;
            case GAMEOVER:
                int x = 380;
                int y = 100;
                String str = "Fin du jeu";
                g.setFont(g.getFont().deriveFont(48f));

                g.drawString(str, x, y);
                break;
        }
            
    }

    @Override
    public void keyPressed(KeyEvent arg0) {

        switch(gameState){
            case GAMEOVER:
                break;
            case JEU:
                break;
            case MENU:
                if(arg0.getKeyCode() == KeyEvent.VK_Z || arg0.getKeyCode() == KeyEvent.VK_S){
                    if(button1){
                        button1 = false;
                        button2 = true;
                    }
                    else if(button2){
                        button1 = true;
                        button2 = false;
                    }
                    else button1 = true;
                }
                if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
                    if(button1) gameState = GameState.NORMAL;
                    else if(button2) System.exit(0);
                }
                break;
            case MULTIJOUEUR:
                break;
            case NORMAL:
                reader.checkChar(arg0.getKeyChar());
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    
}
