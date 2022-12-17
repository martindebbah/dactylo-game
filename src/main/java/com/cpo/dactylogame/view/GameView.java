package com.cpo.dactylogame.view;

import com.cpo.dactylogame.model.game.Game;

public abstract class GameView {
    
    private Game game;

    public GameView(Game game) {
        this.game = game;
    }

    /*
     * Méthode qui affiche le jeu en focntion du mode de jeu
     */
    public abstract void draw(java.awt.Graphics g);

    static class NormalView extends GameView {

        public NormalView(Game game) {
            super(game);
        }

        @Override
        public void draw(java.awt.Graphics g) {
            
        }
        
    }

    static class SoloView extends GameView {

        public SoloView(Game game) {
            super(game);
        }

        @Override
        public void draw(java.awt.Graphics g) {
            
        }
        
    }

}
