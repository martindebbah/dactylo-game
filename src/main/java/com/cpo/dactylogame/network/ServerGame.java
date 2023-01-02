package com.cpo.dactylogame.network;

import java.net.*;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServerGame extends Thread {

    private String ip;
    private boolean running;
    private static List<ClientHandler> clients = new LinkedList<>();

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
                ClientHandler clienthandler = new ClientHandler(s);
                clients.add(clienthandler);
                clienthandler.start();
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

    public class ClientHandler extends Thread {

        private Socket socket;

        public ClientHandler(Socket s) {
            this.socket = s;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (true) {
                    String data = in.readLine();
                    if (data == null)
                        continue;

                    for (ClientHandler client : ServerGame.clients)
                        client.sendData(data);
                }

            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendData(String data) {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(data);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
}
