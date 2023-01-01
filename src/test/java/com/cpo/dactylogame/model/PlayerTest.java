package com.cpo.dactylogame.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void init() {
        this.player = new Player("");
    }

    @AfterEach
    public void delete() {
        this.player = null;
    }

    @Test
    public void testHeal() {
        player.loseHp(10);

        player.heal(5);
        assertEquals(95, player.getHp());
        
        player.heal(10);
        assertEquals(100, player.getHp());
    }

    @Test
    public void testLoseHp() {
        assertEquals(100, player.getHp());
        
        player.loseHp(10);
        assertEquals(90, player.getHp());
    }
    
}
