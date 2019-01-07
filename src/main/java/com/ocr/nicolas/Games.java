package com.ocr.nicolas;

public abstract class Games {

    //commun a tous les jeux
    protected int nbrDigit;
    protected int nbrOfTry;
    protected String developerMode;

    //Pour le mastermind
    protected int nbrMaxOnDigit;

    public Games(Config config) {
        this.nbrDigit = config.getNbrDigit();
        this.nbrOfTry = config.getNbrOfTry();
        this.developerMode = config.getDeveloperMode();
        this.nbrMaxOnDigit = config.getNbrMaxOnDigit();
    }
}
