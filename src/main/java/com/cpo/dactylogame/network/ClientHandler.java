// package com.cpo.dactylogame.network;

// import java.net.Socket;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.PrintWriter;

// public class ClientHandler extends Thread {

//     private Socket s;

//     public ClientHandler(Socket s) {
//         this.s = s;
//     }

//     public void run() {
//         try{
//             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//             PrintWriter out = new PrintWriter(s.getOutputStream(), true);

//             // Initiation de la partie


//             // Boucle de jeu
//             boolean running = true;
//             while (running) {

//             }

//             // Envoyez un message de bienvenue au client
//             // System.out.println("Bienvenue sur le serveur ! Envoyez votre nom et votre âge pour commencer.");

//             // // Lit les données envoyées par le client et les affiche sur la console
//             // String name = in.readLine();
//             // System.out.println("Nom du client : " + name);
//             // int age = Integer.parseInt(in.readLine());
//             // System.out.println("Age du client : " + age);

//             // // Envoyez un message au client pour lui dire au revoir
//             // out.println("Au revoir, " + name + " ! Merci de nous avoir rendu visite.");
//         }
//         catch(Exception e){
//             e.printStackTrace();
//         }
//     }
    
// }
