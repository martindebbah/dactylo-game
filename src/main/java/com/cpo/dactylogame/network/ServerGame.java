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
    private static int nbClients = 0; // A implémenter pour ne pas pouvoir lancer à un joueur

    private final int PORT = 8080;

    @Override
    public void run() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            this.ip = address.getHostAddress();
            ServerSocket serverSocket = new ServerSocket(PORT, 5, address);

            this.running = true;
            while (running) {
                Socket s = serverSocket.accept();

                ClientHandler clienthandler = new ClientHandler(s);
                clients.add(clienthandler);
                nbClients++;
                clienthandler.start();
            }

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

                boolean running = true;
                while (running) {
                    String data = in.readLine();
                    for (ClientHandler client : ServerGame.clients)
                        if (client != this || data.equals("startGame"))
                            sendData(data, client);
                }

            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendData(String data, ClientHandler client) {
            try {
                PrintWriter out = new PrintWriter(client.getSocket().getOutputStream(), true);
                out.println(data);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Socket getSocket() {
            return socket;
        }

    }
    
}
