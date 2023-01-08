package com.cpo.dactylogame.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.cpo.dactylogame.model.Parametres;
import com.cpo.dactylogame.view.Window;
import com.google.gson.Gson;

public class Client extends Thread {
    
    private Socket s;
    private Gson gson = new Gson();
    private BufferedReader in;
    private PrintWriter out;
    private Window window;
    private String name;
    private String word = "";
    private boolean running = true;
    private boolean connected;
    private int rank = 0;

    public Client(String host, int port, String name, Window w) {
        this.window = w;
        this.name = name;
        try {
            this.s = new Socket(host, port);

            this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.out = new PrintWriter(s.getOutputStream(), true);

            connected = true;
        }catch (Exception e) {
            connected = false;
        }
    }

    @Override
    public void run() {
        String paramString = receiveWord();
        startGame(paramString);
    }

    /**
     * Initialise la partie
     * @param p Les paramètres utilisé pour la partie
     */
    public void initGame(Parametres p) {
        String paramString = gson.toJson(p);
        sendWord(paramString);
    }

    /**
     * Lance la partie pour chaque joueur
     * @param paramString Les paramètres au format JSon
     */
    public void startGame(String paramString) {
        Parametres param = gson.fromJson(paramString, Parametres.class);
        window.setGame(this, param);

        while (running) {
            word = receiveWord();
            try {
                rank = Integer.parseInt(word);
            }catch (NumberFormatException e) {
                // Ne fait rien
            }
        }
    }

    /**
     * Envoie une chaîne de caractères au serveur
     * @param word La chaîne de caractères à envoyer
     */
    public void sendWord(String word) {
        out.println(word);
    }

    public String getWord() {
        String r = word;
        word = "";
        return r;
    }

    /**
     * Lit les données envoyées par le serveur
     * @return La chaîne de caractères lue
     */
    public String receiveWord() {
        try {
            String s = in.readLine();
            return s == null ? "" : s;
        }catch (IOException e) {
            return "";
        }
    }

    /**
     * Déconnecte le Socket du serveur
     */
    public void disconnect() {
        try {
            connected = false;
            running = false;
            sendWord("Quel rang ?");
            synchronized (this) {
                wait(1000);
            }
            s.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public String getPlayerName() {
        return name;
    }

    public int getRank() {
        return rank;
    }
}
