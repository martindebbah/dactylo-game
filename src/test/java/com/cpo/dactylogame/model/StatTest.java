package com.cpo.dactylogame.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class StatTest {

    private Stat stat;

    @BeforeEach
    public void initStat() {
        this.stat = new Stat();
    }

    @AfterEach
    public void deleteStat() {
        this.stat = null;
    }

    @Test
    public void testCalcVit() {
        long initTime = 0;
        stat.initTime(initTime);
        long currentTime = 0;
        long time = 1_000; // 1 frappe = 1s

        for (int i = 0; i < 60; i++) {
            stat.add(currentTime);
            currentTime += time;
        }
        stat.validate(true);
        stat.calcVit((double) (currentTime - initTime) / 60_000); // Boucle de 60 frappes = 1min
        assertEquals(60. / 1 / 5, stat.getVit()); // 60 frappes / 1 minute / 5 char

        for (int i = 1; i <= 10; i++) {
            stat.add(currentTime);
            currentTime += time;
        }
        stat.validate(false); // Le mot n'est pas juste -> pas de caractères utiles ajoutés
        stat.calcVit((double) (currentTime - initTime) / 60_000);
        assertEquals(60. / (70_000. / 60_000.) / 5, stat.getVit());
    }

    @Test
    public void testCalcPrec() {
        for (int i = 0; i < 10; i++) // +10 = 10/10
            stat.add(0);
        stat.validate(true);
        stat.calcPrec();
        assertEquals(100., stat.getPrec());

        for (int i = 0; i < 5; i++) // +5 = 10/15
            stat.add(0);
        stat.validate(false);
        stat.calcPrec();
        assertEquals(10. / 15 * 100, stat.getPrec());

        for (int i = 0; i < 5; i++)
            if (i % 3 == 0)
                stat.supp(); // +2typed et -2char = 8/16
            else
                stat.add(0); // +4 = 12/20
        stat.validate(true);
        stat.calcPrec();
        assertEquals(12. / 20 * 100, stat.getPrec());
    }

    @Test
    public void testCalcReg() {
        stat.initTime(0);
        long time = 1_000;
        long currentTime = 1_000;

        for (int i = 0; i < 5; i++) {
            stat.add(currentTime);
            currentTime += time;
        }
        stat.validate(true);
        stat.calcReg();
        assertEquals(0, stat.getReg());
        // Moyenne = 1000 -> moyenne écarts = 0 -> écart type = 0

        for (int i = 0; i < 3; i++) {
            stat.add(currentTime);
            currentTime += 2 * time;
        }
        stat.validate(true);
        stat.calcReg();
        assertEquals(Math.sqrt(187_500) / 1_000, stat.getReg());
        // Moyenne = 1250 -> moyenne écarts = 187500 -> écart type = sqrt(187500)

        for (int i = 0; i < 3; i++) {
            stat.add(currentTime);
            currentTime += time;
        }
        stat.supp();
        currentTime += time;
        stat.add(currentTime);
        stat.validate(true);
        stat.calcReg();
        assertEquals(Math.sqrt(222222) / 1_000, stat.getReg());
        // Moyenne = 1333.33 -> moyenne écarts = 222222 -> écart type = sqrt(222222)

        currentTime += time;
        for (int i = 0; i < 5; i++) {
            stat.add(currentTime);
            currentTime += time;
        }
        stat.validate(false);
        stat.calcReg();
        assertEquals(Math.sqrt(222222) / 1_000, stat.getReg());
        // Moyenne = 1333.33 -> moyenne écarts = 222222 -> écart type = sqrt(222222)
    }
    
}
