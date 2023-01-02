package com.cpo.dactylogame.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cpo.dactylogame.model.GameState;
import com.cpo.dactylogame.model.Parametres;
import com.cpo.dactylogame.model.game.Game;
import com.cpo.dactylogame.model.game.Normal;
import com.cpo.dactylogame.model.game.Jeu;

public class Window extends JFrame {

    private final static int WIDTH = 1000;
    private final static int HEIGHT = 700;

    private Game game;
    private GameView gameView;

    public Window() {
        // Configuration de la fenêtre
        setTitle("Dactylo-game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);

        setMenu();
    }

    public void setMenu() {
        addPanel(new Menu(this));
        refresh();
    }

    public void setGame(GameState gameState){
        Parametres p = new Parametres();
        setGame(gameState, p);
    }

    public void setGame(GameState gameState, Parametres param) {
        if (gameState == GameState.NORMAL) {
            game = new Normal(this, param);
            gameView = new GameView.NormalView(game);
        } 
        else if(gameState == GameState.JEU){
            game = new Jeu(this, param, gameState);
            gameView = new GameView.JeuView(game);
        }
        else {
            game = new Jeu(this, param, gameState);
            gameView = new GameView.JeuView(game);

            
        }
        addPanel(gameView);
        addKeyListener(game.getListener());
        refresh();
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

    public Game getGame() {
        return game;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void gameOver() {
        gameView.gameOver();
        refresh();
    }

    public void quit() {
        System.exit(0);
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
    
}
