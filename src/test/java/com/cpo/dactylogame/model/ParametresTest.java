package com.cpo.dactylogame.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ParametresTest {

    private Parametres p;

    @BeforeEach
    public void init() {
        this.p = new Parametres();
    }

    @AfterEach
    public void delete() {
        this.p = null;
    }

    @Test
    public void testBonusFres() {
        p.setBonusFreq('r');
        assertEquals(10, p.getBonusFreq());

        p.setBonusFreq('r');
        assertEquals(10, p.getBonusFreq());
        
        p.setBonusFreq('r');
        assertEquals(10, p.getBonusFreq());
    }
    
}
