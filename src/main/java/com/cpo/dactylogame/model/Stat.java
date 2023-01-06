package com.cpo.dactylogame.model;

import java.util.LinkedList;
import java.util.List;

public class Stat {

    private double vit; // Vitesse (= mots/minute)
    private double prec; // Précision (= % de caractères utiles tapés)
    private double reg; // Régularité (= moyenne de temps en deux frappes de caractères utiles)

    private int nChar; // Nombre de caractères utiles
    private int nTyped; // Nombre total d'appuis de touches

    // Nombres de caractères tapés temporaires
    private int tmpChar;
    private int tmpTyped;

    private long startTime; // Le temps à partir duquel on commence à calculer, en milli-secondes

    // Temps temporaires en milli-secondes pour calcul de la régularité
    private List<Long> regList = new LinkedList<>();
    private List<Long> tmpRegList = new LinkedList<>();
    private long lastTime;

    /**
     * Initialise le temps pour le calcul des statistiques
     * @param time Le temps en milli-secondes
     */
    public void initTime(long time) {
        this.startTime = time;
    }

    /**
     * Calcule les statistiques de la partie
     * @param time Le temps actuel en milli-secondes
     */
    public void calcData(long time) {
        double totalTime = (double) (time - startTime) / 60_000; // Conversion de milli-secondes vers minutes
        calcVit(totalTime);
        calcPrec();
        calcReg();
    }

    /**
     * Calcule la vitesse
     * @param time Le temps total en minutes
     */
    public void calcVit(double time) {
        vit = nChar / time / 5;
    }

    /**
     * Calcule la précision
     */
    public void calcPrec() {
        prec = (double) nChar / nTyped * 100;
    }

    /**
     * Calcule la régularité
     */
    public void calcReg() {
        // Calcul de la moyenne des valeurs
        double moyenne = regList.stream().mapToLong(l -> l).average().getAsDouble();
        // Calcul de la moyenne des écarts
        double ecartMoyen = regList.stream().mapToLong(l -> (long) Math.pow(moyenne - l, 2)).sum() / (regList.size() - 1);
        // Calcul de l'écart type
        reg = Math.sqrt(ecartMoyen) / 1000; // Divisé par 1000 car 'ecartMoyen' est en millisecondes
        System.out.println(moyenne);
        System.out.println(ecartMoyen);
        System.out.println(reg);
    }

    /**
     * Ajoute un caractère temporaire
     * @param time Le temps actuel en milli-secondes
     */
    public void add(long time) {
        tmpChar++;
        tmpTyped++;

        // Régularité
        time = time - startTime;
        tmpRegList.add(time - lastTime);
        lastTime = time;
    }

    /**
     * Supprime un caractère utile temporaire et ajoute un appui de touche
     */
    public void supp() {
        if (tmpChar > 0)
            tmpChar--;
        tmpTyped++;
    }

    /**
     * Valide le mot courant.
     * Si correct -> compte tmpChar comme des caractères utiles
     * @param correct True si le mot a été correctement tapé
     */
    public void validate(boolean correct) {
        if (correct) {
            nChar += tmpChar;
            regList.addAll(tmpRegList);
        }
        nTyped += tmpTyped;

        tmpTyped = 0;
        tmpChar = 0;
        tmpRegList.clear();
    }

    /**
     * 
     * @return Le nombre de mots par minute
     */
    public double getVit() {
        return vit;
    }

    /**
     * 
     * @return Le nombre de caracères utiles pour cent appuis de touche
     */
    public double getPrec() {
        return prec;
    }

    /**
     * 
     * @return La moyenne du temps entre deux caractères utiles
     */
    public double getReg() {
        return reg;
    }
    
}
