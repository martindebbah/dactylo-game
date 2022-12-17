package com.cpo.dactylogame.model.game;

import com.cpo.dactylogame.model.Player;
import com.cpo.dactylogame.model.text.Text;
import com.cpo.dactylogame.view.Window;

public class Solo extends Game {

    private Player player;

    public Solo(Window window) {
        super(window);
        setText(new Text(""));
    }

    @Override
    public boolean isGameOver() {
        return player.getHp() <= 0;
    }
    
}
