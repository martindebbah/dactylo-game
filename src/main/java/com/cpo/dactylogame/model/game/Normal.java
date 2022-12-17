package com.cpo.dactylogame.model.game;

import com.cpo.dactylogame.model.text.Text;
import com.cpo.dactylogame.view.Window;

public class Normal extends Game {

    public Normal(Window window, String path) {
        super(window);
        setText(new Text(path));
    }

    @Override
    public boolean isGameOver() {
        return text.isEmpty();
    }
    
}
