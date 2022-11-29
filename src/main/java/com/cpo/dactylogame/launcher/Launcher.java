package com.cpo.dactylogame.launcher;

import com.cpo.dactylogame.model.Text;
import com.cpo.dactylogame.view.Window;

public class Launcher {

    public static void main(String[] args) {
        // Window window = new Window();

        Text t = new Text("test");
        t.addWordToBuffer();
        while (!t.isEmpty()) {
            System.out.println(t.next());
            t.addWordToBuffer();
        }
    }

}