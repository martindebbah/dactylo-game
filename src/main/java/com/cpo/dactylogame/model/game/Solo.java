package com.cpo.dactylogame.model.game;

import java.util.Random;

import javax.swing.Timer;

import com.cpo.dactylogame.model.Listener;
import com.cpo.dactylogame.model.Parametres;
import com.cpo.dactylogame.model.Player;
import com.cpo.dactylogame.model.text.Text;
import com.cpo.dactylogame.view.Window;

public class Solo extends Game {

    private Player player;
    private int[][] wordsPos; // [n][pos] -> pour le n-ième mot du texte pos = [x][y]
    private int[] bonus;
    private int level;
    private int nWritten;
    private Timer timerAdd;

    private int t = 0;

    public Solo(Window window) {
        super(window, new Listener(new Text("")));
        this.player = new Player("");
    }

    public Solo(Window window, Parametres parametres) {
        super(window, new Listener(new Text(parametres.getText())));
        this.param = parametres;
    }

    @Override
    public boolean isGameOver() {
        return player.getHp() <= 0;
    }

    @Override
    public void gameOver() {
        timerAdd.stop();
        super.gameOver();
    }

    @Override
    public void initGame() {
        this.wordsPos = new int[16][2]; // Taille du buffer + 1, car lors de l'ajout d'un mot dans une file pleine,
        this.bonus = new int[16];       // on fait la modification du tableau après l'initialisation des positions.

        level = 1; // Début du jeu au niveau 1
        this.timerAdd = new Timer(delay(), e -> {add();}); // Un timer qui ajoute un mot à la file toutes les x secondes
        add(); // Ajout du premier mot

        listener.initGame();
        timerAdd.start(); // Lancement du jeu
    }

    /**
     * Met à jour les coordonnées des mots
     */
    public void update() {
        for (int i = 0; i < listener.getText().getNbWords(); i++) {
            boolean move = true;
            for (int j = 0; j < i; j++)
                move = move ? !isAbove(i, j) : false;
            if (wordsPos[i][1] < 600 && move)
                wordsPos[i][1] += 1;
        }
    }

    private boolean isAbove(int word1, int word2) {
        int start1 = wordsPos[word1][0];
        int end1 = wordsPos[word1][0] + listener.getText().get(word1).length() * 25;
        int start2 = wordsPos[word2][0];
        int end2 = wordsPos[word2][0] + listener.getText().get(word2).length() * 25;

        return start1 < end2 && end1 > start2 && wordsPos[word1][1] + 35 >= wordsPos[word2][1];
    }

    @Override
    public void updateWords() {
        // On change toutes les positions pour qu'elles soient au même index que les mots
        for (int i = 0; i < wordsPos.length - 1; i++) {
            wordsPos[i][0] = wordsPos[i + 1][0];
            wordsPos[i][1] = wordsPos[i + 1][1];
        }

        int hpToHeal = listener.getHpToHeal();

        // Si le buffer est rempli à moins de 50%, on ajoute un mot
        if (listener.getText().getNbWords() < listener.getText().getBufferSize() / 2)
            add();

        // On change le mot courant
        listener.refreshWord();

        // On récupère le nombre d'erreurs
        int nError = listener.getCptError();
        if (nError == 0) { // Pas d'erreur
            nWritten++; // On incrémente le nombre de mots écrits sans erreur

            if (nWritten % 100 == 0) { // Tous les 100 mots sans erreur
                level++; // On monte d'un niveau
                timerAdd.setDelay(delay()); // Et on change la vitesse du jeu
            }

            if (bonus[0] == 1) // Le mot qu'on vient d'écrire est un mot bonus
                player.heal(hpToHeal);

        }else {
            player.loseHp(nError); // Le joueur perd des pdv
        }

        for (int i = 0; i < bonus.length - 1; i++) // On met à jour le tableau de bonus
            bonus[i] = bonus[i + 1];
    }

    private int delay() {
        Double r = 0.9;
        for (int i = 1; i < level; i++)
            r *= 0.9;
        r *= 1000; // Puisque Timer demande une valeur en milli-secondes
        return 3 * r.intValue();
    }

    /**
     * Ajoute un mot avec de nouvelles positions
     */
    private void add() {
        if (listener.getText().isFull())
            listener.refresh(); // Force la validation du mot en cours si la file est pleine

        if (listener.nextWord())
            updateWords();

        listener.getText().addWord();
        int i = listener.getText().getNbWords() - 1; // La position dans la liste du mot ajouté

        // Fréquence de bonus
        double freq = param.getBonusFreq();
        if (new Random().nextInt(100) < freq)
            bonus[i] = 1;

        int wordLength = (listener.getText().get(i).length()) * 25;
        // Taille du mot en pixels

        int min = 150;
        int max = 850;

        // Pour que le mot ne dépasse pas à droite de l'écran
        int x = new Random().nextInt(max - min) + min;
        if (x + wordLength > max)
            x = max - wordLength;

        // On positionne le mot 
        wordsPos[i][0] = x;
        wordsPos[i][1] = 0;
    }

    public int getX(int index) {
        return wordsPos[index][0];
    }

    public int getY(int index) {
        return wordsPos[index][1];
    }

    public int getHp() {
        return player.getHp();
    }

    public int getNbWords() {
        return nWritten;
    }

    public int getLevel() {
        return level;
    }

    public boolean isBonus(int index) {
        return bonus[index] == 1;
    }

    @Override
    public void mainLoop() {
        update();
        super.mainLoop();
    }
    
}
