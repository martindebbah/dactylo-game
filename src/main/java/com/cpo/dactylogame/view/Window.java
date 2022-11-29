package com.cpo.dactylogame.view;



import javax.swing.JFrame;

public class Window extends JFrame {

    private final static int WIDTH = 1000;
    private final static int HEIGHT = 1000;

    public Window() {
        // Configuration de la fenêtre
        setTitle("Dactylo-game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        setGame();

        setLocationRelativeTo(null);
        setVisible(true);

        setFocusable(true);
    }

    public void setGame(){
        GamePanel gp = new GamePanel();
        add(gp);
        addKeyListener(gp);
    }
    
}
