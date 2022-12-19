package com.cpo.dactylogame.model.game;

import com.cpo.dactylogame.model.Listener;
import com.cpo.dactylogame.model.text.Text;
import com.cpo.dactylogame.view.Window;

public class Normal extends Game{

    public Normal(Window window, String path) {
        super(window, new Listener(new Text(path)));
    }

    @Override
    public boolean isGameOver() {
        return listener.getText().isEmpty();
    }

    @Override
    public void initGame() {
        while (!listener.getText().isFull())
            listener.getText().addWord();
    }
    
}
