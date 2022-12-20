package com.cpo.dactylogame.model.game;

import com.cpo.dactylogame.model.Listener;
import com.cpo.dactylogame.model.Player;
import com.cpo.dactylogame.model.text.Text;
import com.cpo.dactylogame.view.Window;

public class Solo extends Game {

    private Player player;

    public Solo(Window window) {
        super(window, new Listener(new Text("")));
    }

    @Override
    public boolean isGameOver() {
        return player.getHp() <= 0;
    }

    @Override
    public void initGame() {
        
    }

    /*
     * override mainloop()
     * player.loseHP(listener.getCptError());
     * super.mainloop()
     */
    
}
