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
    private ServerSocket serverSocket;
    private static boolean beforeGame = true;

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

            this.serverSocket = new ServerSocket(PORT, 5, address);

            while (true) {
                Socket s = serverSocket.accept();

                ClientHandler clienthandler = new ClientHandler(s);
                clients.add(clienthandler);
                clienthandler.start();
            }

        } catch (IOException e) {
            running = false;
        }   
    }

    private InetAddress computeIp() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements()) {
            NetworkInterface currentInterface = interfaces.nextElement();
            Enumeration<InetAddress> addresses = currentInterface.getInetAddresses();

            while (addresses.hasMoreElements()) {
                InetAddress currentAddress = addresses.nextElement();
                if (!currentAddress.getHostAddress().equals("127.0.0.1") && isIPv4(currentAddress))
                    return currentAddress;
            }
        }
        
        return null;
    }

    /**
     * 
     * @param a L'adresse IP à tester
     * @return True si c'est une adresse IPv4
     */
    public static boolean isIPv4(InetAddress a) {
        byte[] address = a.getAddress();
        if (address.length == 4)
            return true;
        return false;
    }

    public static int getNbClients() {
        return clients.size();
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

    public void remove(ClientHandler c) throws IOException {
        clients.remove(c);
        if (clients.size() == 0)
            serverSocket.close();
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

                    if (data == null) {
                        remove(this);
                        if (clients.size() == 1)
                            for (ClientHandler client : clients)
                                sendData("C'est gagné", client);
                        break;
                    }

                    if (beforeGame) {
                        if (clients.size() < 2)
                            continue;
                        for (ClientHandler client : clients)
                            sendData(data, client);
                        beforeGame = false;
                        continue;
                    }

                    if (data.equals("Quel rang ?")) {
                        sendData("" + clients.size(), this);
                        continue;
                    }

                    for (ClientHandler client : clients)
                        if (client != this)
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
