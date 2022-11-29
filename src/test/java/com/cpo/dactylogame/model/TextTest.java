package com.cpo.dactylogame.model;

import com.cpo.dactylogame.model.Text;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TextTest {

    @Test
    public void testGetFirstWord() {
        Text t = new Text("test");
        t.addWordToBuffer();
        String str = t.next();

        assertEquals("un", str);
    }

    // @Test
    // public void testAddWordToBuffer() {
    //     Text t = new Text("test");
    //     t.addWordToBuffer();
    //     t.addWordToBuffer();
    //     t.addWordToBuffer();
    //     t.next();
    //     t.next();
    //     String str = t.next();

    //     assertEquals("trois", str);
    // }
    
}
