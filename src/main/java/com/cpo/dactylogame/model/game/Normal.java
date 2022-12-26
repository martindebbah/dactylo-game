package com.cpo.dactylogame.model.game;

import java.util.Optional;

import com.cpo.dactylogame.model.Parametres;
import com.cpo.dactylogame.model.Stat;
import com.cpo.dactylogame.view.Window;

public class Normal extends Game{

    private Stat stat;

    public Normal(Window window, Parametres param) {
        super(window, param);
        this.stat = new Stat();
        listener.setStat(Optional.of(stat));
    }
    
    @Override
    public void start() {
        stat.initTime(System.currentTimeMillis());
        super.start();
    }
    
    @Override
    public void initGame() {
        while (!listener.getText().isFull())
            listener.getText().addWord();
        listener.initGame();
    }
    
    @Override
    public boolean isGameOver() {
        return listener.getText().isEmpty();
    }

    @Override
    public void gameOver() {
        stat.calcData(System.currentTimeMillis());
        super.gameOver();
    }

    public int getX(int index) {
        return 0;
    }

    public int getY(int index) {
        return 0;
    }

    @Override
    public void updateWords() {
        listener.getText().addWord();
        stat.validate(listener.getCptError() == 0);
    }
    
}
