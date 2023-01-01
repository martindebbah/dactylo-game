package com.cpo.dactylogame.network;

import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

import com.cpo.dactylogame.model.Parametres;
import com.cpo.dactylogame.view.Window;

import java.io.IOException;

public class ServerGame {

    private ServerSocket serverSocket;
    private ForkJoinPool pool;
    private String ip;
    private Parametres param;
    private boolean ready;

    public ServerGame() {
        this.param = new Parametres();
        this.ready = false;
        try {
            InetAddress address = InetAddress.getLocalHost();
            this.ip = address.getHostAddress();
            this.serverSocket = new ServerSocket(4242, 2); // address

            boolean running = true;
            while (running) {
                Socket s = serverSocket.accept();
                new ClientHandler(s).start();

                if (s.isClosed())
                    running = false;
            }

            serverSocket.close();

            //start();

        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

    public void start() {
        // Lancer toutes les parties avec param
    }

    public void isReady(Parametres p) {
        ready = true;
        param = p;
    }

    public void stop() throws IOException{
        ready = false;
        serverSocket.close();
    }

    public String getIp() {
        return ip;
    }
    
}
