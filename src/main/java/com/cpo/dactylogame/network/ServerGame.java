package com.cpo.dactylogame.network;

import java.net.*;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServerGame extends Thread {

    private String ip;
    private boolean running = true;
    private static List<ClientHandler> clients = new LinkedList<>();
    private static int nbClients = 0; // A implémenter pour ne pas pouvoir lancer à un joueur

    private final int PORT = 8080;

    @Override
    public void run() {
        try {
            InetAddress address = computeIp();
            if (address == null) {
                running = false;
                return;
            }

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

    private InetAddress computeIp() throws UnknownHostException, SocketException {
        InetAddress localhost = InetAddress.getLocalHost();
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements()) {
            NetworkInterface currentInterface = interfaces.nextElement();
            Enumeration<InetAddress> addresses = currentInterface.getInetAddresses();

            while (addresses.hasMoreElements()) {
                InetAddress currentAddress = addresses.nextElement();
                if (!currentAddress.getHostAddress().equals("127.0.0.1") && isIpv4(currentAddress))
                    return currentAddress;
            }
        }
        
        return null;
    }

    public static boolean isIpv4(InetAddress a) {
        byte[] address = a.getAddress();
        if (address.length == 4)
            return true;
        return false;
    }

    public static int getNbClients() {
        return nbClients;
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

    public boolean isRunning() {
        return running;
    }

    public void remove(ClientHandler c) {
        clients.remove(c);
        nbClients--;
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

                    // if (data.equals("QuelRang?")) {
                    //     sendData(Integer.toString(nbClients), this);
                    //     ServerGame.this.remove(this);
                    //     running = false;
                    // }else
                    if (data == null) {
                        ServerGame.this.remove(this);
                        running = false;
                    }else
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
