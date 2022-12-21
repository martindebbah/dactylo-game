package com.cpo.dactylogame.model;

public class Stat {

    private double vit; // Vitesse (= mots/minute)
    private double prec; // Précision (= % de caractères utiles tapés)
    private double reg; // Régularité (= moyenne de temps en deux frappes de caractères utiles)

    private double nChar; // Nombre de caractères utiles
    private double nTyped; // Nombre total d'appuis de touches

    // Nombres de caractères tapés temporaires
    private double tmpChar;
    private double tmpTyped;

    private long startTime; // Le temps à partir duquel on commence à calculer, en milli-secondes

    // Temps temporaires en milli-secondes pour calcul de la régularité
    private long tmpReg;
    private long tmpTot;
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
        prec = nChar / nTyped * 100;
    }

    /**
     * Calcule la régularité
     */
    public void calcReg() { // Pas sûr
        reg = tmpTot / (nChar - 1);
    }

    /**
     * Ajoute un caractère temporaire
     * @param time Le temps actuel en milli-secondes
     */
    public void add(long time) {
        tmpChar++;
        tmpTyped++;

        // Régularité
        tmpReg += time - lastTime;
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
            tmpTot += tmpReg;
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
