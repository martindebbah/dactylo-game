package com.cpo.dactylogame.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cpo.dactylogame.model.GameState;
import com.cpo.dactylogame.model.Parametres;
import com.cpo.dactylogame.model.game.Game;
import com.cpo.dactylogame.model.game.Normal;
import com.cpo.dactylogame.network.Client;
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

    /**
     * Affiche les menus du jeu
     */
    public void setMenu() {
        addPanel(new Menu(this));
        refresh();
    }

    /**
     * Lance une partie avec des paramètres basiques
     * @param gameState Le type de partie
     */
    public void setGame(GameState gameState){
        Parametres p = new Parametres();
        setGame(gameState, p);
    }

    /**
     * Lance une partie
     * @param gameState Le type de partie
     * @param param Les paramètres à utiliser
     */
    public void setGame(GameState gameState, Parametres param) {
        if (gameState == GameState.NORMAL) {
            game = new Normal(this, param);
            gameView = new GameView.NormalView(game);
        } 
        else {
            game = new Jeu(this, param);
            gameView = new GameView.JeuView(game);
        }
        addPanel(gameView);
        addKeyListener(game.getListener());
        refresh();
    }

    /**
     * Lance une partie en multijoueur
     * @param c Le client
     * @param param Les paramètres à utiliser
     */
    public void setGame(Client c, Parametres param) {
        game =  new Jeu(this, param, c);
        gameView = new GameView.JeuView(game);

        addPanel(gameView);
        addKeyListener(game.getListener());
        refresh();
    }

    /**
     * Supprime tout ce qui est sur la frame et ajoute un nouveau panel
     * @param panel Le nouveau panel
     */
    private void addPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        refresh();
    }

    /**
     * Actualise la fenêtre
     */
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

    /**
     * Fin de la partie
     */
    public void gameOver() {
        gameView.gameOver();
        refresh();
    }

    /**
     * Quitte le programme
     */
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
