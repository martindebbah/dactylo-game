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
        refresh();
    }

    public void setGame(GameState gameState){
        switch (gameState) {
            case NORMAL:
                game = new Normal(this, "lotr");
                gameView = new GameView.NormalView(game);
                addPanel(gameView);
                addKeyListener(game.getListener());
                refresh();
                break;
            case JEU:
                game = new Solo(this);
                addKeyListener(game.getListener());
                gameView = new GameView.SoloView(game);
                refresh();
                break;
            default:
                break;
        }
    }

    private void addPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
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
    
}
