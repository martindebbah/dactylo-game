package com.cpo.dactylogame.model.game;

import com.cpo.dactylogame.model.Player;
import com.cpo.dactylogame.model.Text;
import com.cpo.dactylogame.view.Window;

public class Solo extends Game {

    private Player player;

    public Solo(Window window) {
        super(window);
    }

    @Override
    public void setText(Text text) {
        
    }

    @Override
    public boolean isGameOver() {
        return player.getHp() <= 0;
    }
    
}
