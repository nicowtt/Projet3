package com.ocr.nicolas;


public abstract class Games {

    //commun a tous les jeux
    private int nbrDigit;
    private int nbrOfTry;
    private String developerMode;

    //pour le mastermind
    private int nbrDigitUsable;

    public Games() {

    }

    public Games(int nbrDigit, int nbrOfTry, String developerMode) {
        this.nbrDigit = nbrDigit;
        this.nbrOfTry = nbrOfTry;
        this.developerMode = developerMode;
    }

    public int getNbrDigit() {return nbrDigit;}

    public int getNbrOfTry() {return nbrOfTry;}

    public String getDeveloperMode() {return developerMode;}

    }



