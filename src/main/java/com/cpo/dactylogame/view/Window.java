package com.cpo.dactylogame.view;



import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

    private final static int WIDTH = 1000;
    private final static int HEIGHT = 1000;

    public Window() {
        // Configuration de la fenêtre
        setTitle("Dactylo-game");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);

        setMenu();
    }

    public void setMenu() {
        Menu menu = new Menu(this);
        addPanel(menu);
    }

    public void setGame(){
        GamePanel gp = new GamePanel();
        addPanel(gp);
        addKeyListener(gp);
    }

    private void addPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        refresh();
    }

    public void refresh() {
        revalidate();
        repaint();
    }
    
}
