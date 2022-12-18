package com.cpo.dactylogame.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import com.cpo.dactylogame.model.text.Text;

public class ListenerTest {

    private Listener listener;

    @BeforeEach
    public void initListener() {
        this.listener = new Listener(new Text(""));
    }

    @AfterEach
    public void deleteListener() {
        this.listener = null;
    }

    @Test
    public void testKeyTyped() {
        
    }

    @Test
    public void testRefresh() {
        
    }

    @Test
    public void testGemeOver() {

    }
    
}
