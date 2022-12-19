package com.cpo.dactylogame.model.game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

import com.cpo.dactylogame.model.Listener;
import com.cpo.dactylogame.model.Player;
import com.cpo.dactylogame.view.Window;

public abstract class Game implements ActionListener {

    protected Window window;
    // private Param param;
    private Timer timer;
    private final int FPS = 60;

    protected Player player; // Bouger dans Solo ?
    protected Listener listener;

    /**
     * Crée un objet jeu
     * @param window La fenêtre du jeu
     */
    public Game(Window window, Listener listener) {
        this.window = window;
        this.listener = listener;
        this.timer = new Timer(1000 / FPS, this);
        // initGame();
        window.refresh();
    }

    /**
     * Débute la partie
     */
    public void start() {
        timer.start();
    }

    /**
     * Arrête la partie
     */
    public void gameOver() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameOver()) { // La partie est finie, on arête le jeu
            gameOver();
        }
        // La partie continue
        mainLoop();
    }
    
    /**
     * Boucle de jeu principale
     */
    public void mainLoop() {
        // Récupérer les données entrées par l'utilisateur
        
        // Mettre à jour le jeu
        
        // Mettre à jour l'affichage
        // window.getGameView().repaint();
        window.refresh();
    }

    /**
     * 
     * @return True si le jeu est terminé
     */
    public abstract boolean isGameOver();

    /**
     * Initialise la partie
     */
    public abstract void initGame();

    /**
     * 
     * @return Le Listener du jeu
     */
    public Listener getListener() {
        return listener;
    }

    /**
     * 
     * @return La fenêtre du jeu
     */
    public Window getWindow() {
        return window;
    }
    
}
