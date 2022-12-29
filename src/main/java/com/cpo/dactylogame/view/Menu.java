package com.cpo.dactylogame.view;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cpo.dactylogame.model.GameState;
import com.cpo.dactylogame.model.Parametres;

import java.awt.event.ActionListener;
import java.awt.*;

public class Menu extends JPanel{

    private Window window;
    private GridBagConstraints gbc;

    public Menu(Window w) {
        this.window = w;

        // Configuration du panel
        setSize(window.getSize());
        setBackground(new Color(104, 139, 207));
        setLayout(new GridBagLayout());

        this.gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.REMAINDER;
        gbc.ipady = 10;
        gbc.insets = new Insets(20, 0, 20, 0);

        setMainMenu();
    }

    public void createButton(String name, ActionListener l) {
        JButton button = new JButton(name);
        button.addActionListener(l);
        add(button, gbc);
    }

    public void setMainMenu() {
        removeAll();

        // Boutons
        createButton("Jouer", e -> {choiceGame();});
        createButton("Paramètres", e -> {param();});
        createButton("Quitter", e -> {window.quit();});

        window.refresh();
    }

    public void choiceGame() {
        removeAll();

        // Boutons de choix de mode de jeu
        createButton("Mode Normal", e -> {window.setGame(GameState.NORMAL);});
        createButton("Mode Jeu", e -> {window.setGame(GameState.JEU);});
        // createButton("Mode Jeu en Multijoueur", e -> {window.setGame();});
        
        // Bouton de retour en arrière
        createButton("Retour", e -> {setMainMenu();});

        window.refresh();
    }

    public void param() {
        removeAll();

        Parametres param = new Parametres();

        GridBagConstraints panelGbc = new GridBagConstraints();
        panelGbc.gridx = GridBagConstraints.REMAINDER;
        GridBagConstraints buttonsGbc = new GridBagConstraints();

        // Choix du texte
        JPanel textPanel = new JPanel(new GridBagLayout());
        textPanel.setOpaque(false);

        JLabel textLabel = new JLabel("Choix du texte");
        JPanel textButtons = new JPanel(new GridBagLayout());
        textButtons.setOpaque(false);

        createParamButton("Aléatoire", e -> {param.setText("");}, textButtons, buttonsGbc, true);
        createParamButton("Le Seigneur des Anneaux", e -> {param.setText("lotr");}, textButtons, buttonsGbc, false);

        textPanel.add(textLabel, panelGbc);
        textPanel.add(textButtons, panelGbc);

        // Choix de la fréquence de bonus
        JPanel bonusPanel = new JPanel(new GridBagLayout());
        bonusPanel.setOpaque(false);

        JLabel bonusLabel = new JLabel("Fréquence des bonus");
        JPanel bonusButtons = new JPanel(new GridBagLayout());
        bonusButtons.setOpaque(false);

        createParamButton("Rare", e -> {param.setBonusFreq('r');}, bonusButtons, buttonsGbc, true);
        createParamButton("Courant", e -> {param.setBonusFreq('c');}, bonusButtons, buttonsGbc, false);
        createParamButton("Abondant", e -> {param.setBonusFreq('a');}, bonusButtons, buttonsGbc, false);

        bonusPanel.add(bonusLabel, panelGbc);
        bonusPanel.add(bonusButtons, panelGbc);

        // Choix de la fréquence de malus
        JPanel malusPanel = new JPanel(new GridBagLayout());
        malusPanel.setOpaque(false);

        JLabel malusLabel = new JLabel("Fréquence des malus");
        JPanel malusButtons = new JPanel(new GridBagLayout());
        malusButtons.setOpaque(false);

        createParamButton("Rare", e -> {param.setMalusFreq('r');}, malusButtons, buttonsGbc, true);
        createParamButton("Courant", e -> {param.setMalusFreq('c');}, malusButtons, buttonsGbc, false);
        createParamButton("Abondant", e -> {param.setMalusFreq('a');}, malusButtons, buttonsGbc, false);

        malusPanel.add(malusLabel, panelGbc);
        malusPanel.add(malusButtons, panelGbc);

        // Boutons pour jouer
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setOpaque(false);

        JLabel buttonsLabel = new JLabel("Jouer avec ces paramètres");
        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setOpaque(false);

        createParamButton("Mode normal", e -> {window.setGame(GameState.NORMAL, param);}, buttons, buttonsGbc, false);
        createParamButton("Mode jeu", e -> {window.setGame(GameState.JEU, param);}, buttons, buttonsGbc, false);

        buttonsPanel.add(buttonsLabel, panelGbc);
        buttonsPanel.add(buttons, panelGbc);

        // Ajout des composants
        add(textPanel, gbc);
        add(bonusPanel, gbc);
        add(malusPanel, gbc);
        add(buttonsPanel, gbc);
        createButton("Retour", e -> {setMainMenu();});

        window.refresh();
    }

    public void createParamButton(String name, ActionListener l, JComponent c, GridBagConstraints g, boolean selected) {
        JButton button = new JButton(name);
        button.setOpaque(false);
        Color selectedColor = new Color(27, 180, 146);
        if (selected)
            button.setForeground(selectedColor);

        button.addActionListener(e -> {
            for (Component b : c.getComponents())
                b.setForeground(Color.BLACK);
            l.actionPerformed(e);
            button.setForeground(selectedColor);
        });
        c.add(button, g);
    }
    
}
