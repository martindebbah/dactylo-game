package com.cpo.dactylogame.view;

import javax.swing.JFrame;

public class Window extends JFrame {

    private static int WIDTH = 1000;
    private static int HEIGHT = 1000;

    public Window() {
        // Configuration de la fenêtre
        setTitle("Dactylo-game");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
