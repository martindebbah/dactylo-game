package com.cpo.dactylogame.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.cpo.dactylogame.model.Parametres;
import com.cpo.dactylogame.model.game.Game;
import com.cpo.dactylogame.view.Window;
// import com.google.gson.Gson;

public class Client extends Thread {
    
    private Socket s;
    // private Gson gson = new Gson();
    private BufferedReader in;
    private PrintWriter out;
    private Window window;
    private String name;
    private Game game;
    private String word = "";
    private boolean running = true;;

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
        boolean waiting = true;
        while (waiting) {
            String p = receiveWord();
            if (p.equals("startGame"))
                waiting = false;
        }
        startGame();
    }

    public void initGame(Parametres p) {
        // String paramString = gson.toJson(p);
        // out.println(paramString);
        sendWord("startGame");
    }

    public void startGame() {
        // Parametres param = gson.fromJson(paramString, Parametres.class);
        window.setGame(this, new Parametres());
        game = window.getGame();

        while (running) {
            word = receiveWord();
        }
    }

    public void sendWord(String word) {
        out.println(word);
    }

    public String getWord() {
        String r = word;
        word = "";
        return r;
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
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPlayerName() {
        return name;
    }

}
