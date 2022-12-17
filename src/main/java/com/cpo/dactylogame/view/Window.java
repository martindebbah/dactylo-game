package com.cpo.dactylogame.view;



import javax.swing.JFrame;

import com.cpo.dactylogame.model.GameState;
import com.cpo.dactylogame.model.game.Game;
import com.cpo.dactylogame.model.game.Normal;
import com.cpo.dactylogame.model.game.Solo;

public class Window extends JFrame {

    private final static int WIDTH = 1000;
    private final static int HEIGHT = 1000;

    private Game game;
    private GameView gameView;

    public Window() {
        // Configuration de la fenêtre
        setTitle("Dactylo-game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        setGame(GameState.NORMAL);

        setLocationRelativeTo(null);
        setVisible(true);

        setFocusable(true);
    }

    public void setGame(GameState gameState){
        switch (gameState) {
            case NORMAL:
                game = new Normal(this);
                addKeyListener(game.getListener());
                gameView = new GameView.NormalView(game);
                break;
            case JEU:
                game = new Solo(this);
                addKeyListener(game.getListener());
                gameView = new GameView.SoloView(game);
                break;
            default:
                break;
        }
    }

    @Override
    public void paintComponents(java.awt.Graphics g) {
        super.paintComponents(g);
        if(game != null){
            gameView.draw(g);
        }
            
    }

    public void refresh() {
        revalidate();
        repaint();
    }
    
}
