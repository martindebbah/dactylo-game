package com.cpo.dactylogame.model.game;

import java.util.Random;
import java.util.Timer;

import com.cpo.dactylogame.model.Listener;
import com.cpo.dactylogame.model.Parametres;
import com.cpo.dactylogame.model.Player;
import com.cpo.dactylogame.model.text.Text;
import com.cpo.dactylogame.view.Window;

public class Solo extends Game {

    private Player player;
    private int[][] wordsPos; // [n][pos] -> pour le n-ième mot du texte pos = [x][y]
    private int level;
    private Timer timerAdd;

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
        updateWords();
        listener.initGame();
    }

    /**
     * Met à jour les coordonnées des mots
     */
    public void update() {
        for (int i = 0; i < listener.getText().getNbWords(); i++)
            if (wordsPos[i][1] < 600)
                wordsPos[i][1] += 1;
    }

    @Override
    public void updateWords() {
        for (int i = 0; i < wordsPos.length - 1; i++) {
            wordsPos[i][0] = wordsPos[i + 1][0];
            wordsPos[i][1] = wordsPos[i + 1][1];
        }
        wordsPos[wordsPos.length - 1][0] = 0;
        wordsPos[wordsPos.length - 1][1] = 0;

        if (listener.getText().getNbWords() < listener.getText().getBufferSize() / 2)
            add();

        listener.refreshWord();
    }

    /**
     * Ajoute un mot avec de nouvelles positions
     */
    private void add() {
        if (listener.getText().isFull())
            listener.refresh(); // Force la validation du mot en cours si la file est pleine

        listener.getText().addWord();
        // Gérer ici la fréquence de bonus

        int i = listener.getText().getNbWords() - 1; // La position dans la liste du mot ajouté
        int min = 150;
        int max = 1000;
        Random random = new Random();

        // On positionne le mot 
        wordsPos[i][0] = random.nextInt(max - min) + min;
        wordsPos[i][1] = 0;
    }

    public int getX(int index) {
        return wordsPos[index][0];
    }

    public int getY(int index) {
        return wordsPos[index][1];
    }

     @Override
     public void mainLoop() {
        update();
        super.mainLoop();
     }
    
}
