package com.cpo.dactylogame.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;

public class ReaderTest {

    private Reader reader;

    @BeforeEach
    public void initReader() {
        try {
            this.reader = new Reader("resources/textes/test.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }

    @AfterEach
    public void deleteReader() {
        this.reader = null;
    }

    @Test
    public void testHasNext() {
        assertTrue(reader.hasNext());
        for (int i = 0; i < 15; i++)
            reader.next();
        
        assertFalse(reader.hasNext());
    }

    @Test
    public void testNext() {

    }

}
