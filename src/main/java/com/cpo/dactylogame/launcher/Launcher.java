package com.cpo.dactylogame.launcher;

import com.cpo.dactylogame.model.text.Text;

public class Launcher {

    public static void main(String[] args) {
        // Window window = new Window();

        Text t = new Text("test");
        while (!t.isFull()) {
            t.addWord();
            // System.out.println(t.removeFirst());
        }
        System.out.println(t.getBuffer());
    }

}