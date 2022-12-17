package com.cpo.dactylogame.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

public class Menu extends JPanel{

    private Window window;
    private GridBagConstraints gdb;

    public Menu(Window w) {
        this.window = w;

        // Configuration du panel
        setSize(window.getSize());
        setBackground(new Color(104, 139, 207));
        setLayout(new GridBagLayout());

        this.gdb = new GridBagConstraints();
        gdb.gridx = GridBagConstraints.REMAINDER;
        gdb.ipady = 10;
        gdb.insets = new Insets(20, 0, 20, 0);

        setMainMenu();
    }

    public void createButton(String name, ActionListener l) {
        JButton button = new JButton(name);
        button.addActionListener(l);
        add(button, gdb);
    }

    public void setMainMenu() {
        removeAll();

        // Boutons
        createButton("Jouer", e -> {choiceGame();});
        createButton("Quitter", e -> {window.dispose();});

        window.refresh();
    }

    public void choiceGame() {
        removeAll();

        // Boutons de choix de mode de jeu
        createButton("Mode Normal", e -> {window.setGame();});
        createButton("Mode Jeu", e -> {window.setGame();});
        // createButton("Mode Jeu en Multijoueur", e -> {window.setGame();});
        
        // Bouton de retour en arrière
        createButton("Retour", e -> {setMainMenu();});

        window.refresh();
    }
    
}
