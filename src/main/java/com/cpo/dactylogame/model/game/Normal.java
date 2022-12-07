package com.cpo.dactylogame.model.game;

import com.cpo.dactylogame.model.Text;
import com.cpo.dactylogame.view.Window;

public class Normal extends Game {

    private Text text;

    public Normal(Window window) {
        super(window);
    }

    @Override
    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public boolean isGameOver() {
        return text.isEmpty();
    }
    
}
