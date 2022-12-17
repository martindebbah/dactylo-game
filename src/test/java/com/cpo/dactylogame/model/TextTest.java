package com.cpo.dactylogame.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import com.cpo.dactylogame.model.text.Text;

public class TextTest {

    private Text text;

    @BeforeEach
    public void initText() {
        this.text = new Text("test");
    }

    @AfterEach
    public void deleteText() {
        this.text = null;
    }

    @Test
    public void testAddWord() {
        text.addWord();
        assertEquals("un", text.currentWord());
    }

    @Test
    public void testAddWordWithString() {
        text.addWord("test");
        assertEquals("test", text.currentWord());
    }

    @Test
    public void testCurrentWord() {
        String str1 = text.currentWord();
        assertEquals(null, str1);

        text.addWord();
        String str2 = text.currentWord();

        assertEquals("un", str2);
    }

    @Test
    public void testRemoveFirst() {
        text.addWord();
        String str1 = text.currentWord();
        text.removeFirst();
        String str2 = text.currentWord();

        assertEquals("un", str1);
        assertEquals(null, str2);
    }

    @Test
    public void testIsEmpty() {
        assertFalse(text.isEmpty());

        for (int i = 0; i < 15; i++) {
            text.addWord();
            text.removeFirst();
        }

        assertTrue(text.isEmpty());
    }

    @Test
    public void testIsFull() {
        assertFalse(text.isFull());

        for (int i = 0; i < 15; i++) {
            text.addWord();
        }

        assertTrue(text.isFull());
        
        for (int i = 0; i < 15; i++) {
            text.removeFirst();
        }

        assertTrue(text.isFull());
    }

    @Test
    public void testGetBuffer() {
        text.addWord();
        text.addWord();

        String str1 = text.getBuffer();

        while (!text.isFull()) {
            text.addWord();
        }

        String str2 = text.getBuffer();

        assertEquals("un deux", str1);
        assertEquals(
            "un deux trois quatre cinq six sept huit neuf dix onze douze treize quatorze quinze",
            str2);
    }
    
}
