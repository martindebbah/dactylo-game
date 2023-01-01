package com.cpo.dactylogame.network;

import java.net.*;
import java.util.concurrent.ForkJoinPool;
import java.io.IOException;

public class ServerGame {

    ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port, 5);
            // threadPool
            ForkJoinPool pool = new ForkJoinPool(5);

        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

    public void stop(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
