package com.cpo.dactylogame.model.game;

import com.cpo.dactylogame.model.Listener;
import com.cpo.dactylogame.model.Parametres;
import com.cpo.dactylogame.model.Player;
import com.cpo.dactylogame.model.text.Text;
import com.cpo.dactylogame.view.Window;

public class Solo extends Game {

    private Player player;
    private int[][] wordsPos; // [n][pos] -> pour le n-ième mot du texte pos = [x][y]

    public Solo(Window window) {
        super(window, new Listener(new Text("")));
        this.player = new Player("");
    }

    public Solo(Window window, Parametres parametres) {
        super(window, new Listener(new Text(parametres.getText())));
        this.param = parametres;
    }

    @Override
    public boolean isGameOver() {
        return player.getHp() <= 0;
    }

    @Override
    public void initGame() {
        this.wordsPos = new int[15][2];
        listener.getText().addWord();
        updateWords();
        listener.initGame();
    }

    /**
     * Met à jour les coordonnées des mots
     */
    public void update() {
        for (int i = 0; i < listener.getText().getNbWords(); i++) {
            if (wordsPos[i][1] < 600)
                wordsPos[i][1] += 1;
        }
    }

    /**
     * Met à jour le tableau de coordonnées quand on écrit un mot
     */
    @Override
    public void updateWords() {
        if (listener.getText().getNbWords() > listener.getText().getBufferSize() / 2)
            listener.getText().addWord();

        for (int i = 0; i < wordsPos.length - 1; i++) {
            wordsPos[i][0] = wordsPos[i + 1][0];
            wordsPos[i][1] = wordsPos[i + 1][1];
        }
        wordsPos[wordsPos.length - 1][0] = 0;
        wordsPos[wordsPos.length - 1][1] = 0;

        wordsPos[listener.getText().getNbWords() - 1][0] = 150;
        wordsPos[listener.getText().getNbWords() - 1][1] = 0;
    }

    public int getX(int index) {
        return wordsPos[index][0];
    }

    public int getY(int index) {
        return wordsPos[index][1];
    }

    /*
     * override mainloop()
     * player.loseHP(listener.getCptError());
     * super.mainloop()
     */

     @Override
     public void mainLoop() {
        update();
        super.mainLoop();
     }
    
}
