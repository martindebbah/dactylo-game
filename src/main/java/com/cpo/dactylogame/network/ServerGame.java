package com.cpo.dactylogame.network;

import java.net.*;

import java.io.IOException;

public class ServerGame extends Thread {

    private String ip;
    private boolean running;

    private final int PORT = 8080;

    @Override
    public void run() {
        // this.ready = false;
        try {
            InetAddress address = InetAddress.getLocalHost();
            this.ip = address.getHostAddress();
            ServerSocket serverSocket = new ServerSocket(PORT, 5, address);

            running = true;
            while (running) {
                Socket s = serverSocket.accept();
                System.out.println("accepté");
                new ClientHandler(s).start();
            }

            System.out.println("fermé");

            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

    public void close() {
        running = false;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return PORT;
    }
    
}
