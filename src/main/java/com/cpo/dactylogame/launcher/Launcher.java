package com.cpo.dactylogame.launcher;

import java.io.FileNotFoundException;

import com.cpo.dactylogame.model.Text;
import com.cpo.dactylogame.view.Window;

public class Launcher {

    public static void main(String[] args) {
        // Window window = new Window();

        try {
            Text t = new Text("test");
            while (!t.isFull()) {
                t.addWord();
                // System.out.println(t.removeFirst());
            }
            System.out.println(t.getBuffer());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}