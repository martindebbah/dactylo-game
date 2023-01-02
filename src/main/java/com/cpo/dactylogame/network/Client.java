package com.cpo.dactylogame.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.cpo.dactylogame.model.Parametres;
import com.cpo.dactylogame.model.game.Game;
import com.cpo.dactylogame.view.Window;
import com.google.gson.Gson;

public class Client extends Thread {
    
    private Socket s;
    private Gson gson = new Gson();
    private BufferedReader in;
    private PrintWriter out;
    private Window window;
    private String name;
    private Game game;

    public Client(String host, int port, String name, Window w) {
        this.window = w;
        this.name = name;
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
        String p = null;
        while (p == null) {
            p = receiveWord();
        }
        startGame(p);
    }

    public void initGame(Parametres p) {
        String paramString = gson.toJson(p);
        out.println(paramString);
    }

    public void startGame(String paramString) {
        Parametres param = gson.fromJson(paramString, Parametres.class);
        window.setGame(this, param);
        game = window.getGame();
    }

    public void sendWord(String word) {
        out.println(word);
    }

    public String receiveWord() {
        try {
            return in.readLine();
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void disconnect() {
        try {
            s.close();
            System.out.println("Déconnexion réussie");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPlayerName() {
        return name;
    }

}
