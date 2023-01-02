package com.cpo.dactylogame.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.cpo.dactylogame.model.Parametres;
import com.google.gson.Gson;

public class Client extends Thread {
    
    private Socket s;
    private Gson gson = new Gson();
    private BufferedReader in;
    private PrintWriter out;

    public Client(String host, int port, String name) {
        try {
            this.s = new Socket(host, port);

            this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.out = new PrintWriter(s.getOutputStream(), true);

            
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

    public void initGame(Parametres p) {
        String paramString = gson.toJson(p);
        out.println(paramString);
    }

    public void startGame() {
        
    }

    public void sendWord() {

    }

    public void receiveWord() {

    }

    public void disconnect() {
        try {
            s.close();
            System.out.println("Déconnexion réussie");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
