package com.cpo.dactylogame.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cpo.dactylogame.model.GameState;
import com.cpo.dactylogame.model.game.Game;
import com.cpo.dactylogame.model.game.Normal;
import com.cpo.dactylogame.model.game.Solo;

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
        switch (gameState) {
            case NORMAL:
                game = new Normal(this, "lotr");
                gameView = new GameView.NormalView(game);
                break;
            case JEU:
                game = new Solo(this);
                gameView = new GameView.SoloView(game);
                break;
            default:
                break;
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
    
}
