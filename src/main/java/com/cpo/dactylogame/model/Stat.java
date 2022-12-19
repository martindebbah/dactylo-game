package com.cpo.dactylogame.model;

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
    private long tmpReg;
    private long tmpTime;
    private long lastTime;

    /**
     * Initialise le temps pour le calcul des statistiques
     */
    public void initTime() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Calcule les statistiques de la partie
     */
    public void calcData() {
        long currentTime = System.currentTimeMillis();
        long totalTime = (currentTime - startTime) / 60000; // Conversion de milli-secondes vers minutes
        calcVit(totalTime);
        calcPrec();
        calcReg();
    }

    /**
     * Calcule la vitesse
     * @param time Le temps actuel en minutes
     */
    public void calcVit(long time) {
        vit = nChar / time / 5;
    }

    /**
     * Calcule la précision
     */
    public void calcPrec() {
        prec = nChar / nTyped * 100;
    }

    /**
     * Calcule la régularité
     */
    public void calcReg() { // Pas sûr
        reg = tmpReg / (nChar - 1);
    }

    /**
     * Ajoute un caractère temporaire
     */
    public void add() {
        tmpChar++;
        tmpTyped++;

        // Régularité
        tmpTime = System.currentTimeMillis();
        tmpReg += tmpTime - lastTime;
        lastTime = tmpTime;
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
            reg += tmpReg;
        }
        nTyped += tmpTyped;
        tmpChar = 0;
        tmpTyped = 0;
        tmpReg = 0;
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
