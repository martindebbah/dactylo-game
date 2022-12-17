package com.cpo.dactylogame.model.game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

import com.cpo.dactylogame.model.Listener;
import com.cpo.dactylogame.model.text.Text;
import com.cpo.dactylogame.view.Window;

public abstract class Game implements ActionListener {

    private Window window;
    protected Text text;
    // private Param param;
    private Timer timer;
    private final int FPS = 60;

    private Listener listener;

    /**
     * Crée un objet jeu
     * @param window La fenêtre du jeu
     */
    public Game(Window window) {
        this.window = window;
        this.timer = new Timer(1000 / FPS, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameOver()) { // La partie est finie, on arête le jeu
            timer.stop();
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
        window.refresh();
    }
    
    /**
     * Ajoute un texte à la partie
     * @param text Le texte à ajouter
     */
    public void setText(Text text) {
        this.text = text;
    }

    /**
     * 
     * @return True si le jeu est terminé
     */
    public abstract boolean isGameOver();

    public Listener getListener() {
        return listener;
    }
    
}
