package com.cpo.dactylogame.view;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.Color;
import java.awt.Graphics;

public class GamePanel extends JPanel implements Runnable, KeyListener{

    private Thread gameThread;

    private static final double FPS = 60;

    private String test = "test";
    private boolean[] goodChar = new boolean[test.length()];
    private boolean[] badChar = new boolean[test.length()];
    private boolean testColor;
    private int indexChar = 0;

    public GamePanel() {
        gameThread = new Thread(this);
        gameThread.start();
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

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!testColor) g.setColor(Color.RED);
        else g.setColor(Color.BLUE);
        g.fillRect(0, 0, 48, 48);

        int x = 400;
        int y = 200;
        g.setFont(g.getFont().deriveFont(48f));
        char[] chars = test.toCharArray();
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

    @Override
    public void keyPressed(KeyEvent arg0) {
        if(arg0.getKeyChar() == test.charAt(indexChar)){
            goodChar[indexChar] = true;
            indexChar++;
            if(indexChar >= test.length()){
                indexChar = 0;
                test = "bravo";
                goodChar = new boolean[test.length()];
                badChar = new boolean[test.length()];
            }
        }
        else{
            badChar[indexChar] = true;
        }

        if(arg0.getKeyCode() == KeyEvent.VK_X){
            testColor = !testColor;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }
    
}
