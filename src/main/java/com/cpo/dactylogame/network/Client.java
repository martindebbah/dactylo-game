package com.cpo.dactylogame.network;

import java.io.IOException;
import java.net.Socket;

public class Client{
    
    private Socket s;

    public Client(String host) {
        try {
            this.s = new Socket(host, 4242);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            s.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
