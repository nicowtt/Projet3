package com.ocr.nicolas;

public class Mastermind extends Games{

    protected int nbrMaxOnDigit;

    public Mastermind(int nbrDigit, int nbrOfTry, String developerMode, int nbrMaxOnDigit) {
        super(nbrDigit, nbrOfTry, developerMode);
        this.nbrMaxOnDigit = nbrMaxOnDigit;
    }


    /**
     * For playing Mastermind Game
     */
    protected void playMastermind() {

        // Affichage du menu du type de jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskTypeOfGame();

        // Recuperation variable du type de jeux
        int gameTypeChoice = display.displayGameTypeChoice();

        // objets
        MastermindChallenger mastermindChallenger = new MastermindChallenger(nbrDigit,nbrOfTry,developerMode, nbrMaxOnDigit);

        do
            switch (gameTypeChoice) {
                case 1:
                    while (gameTypeChoice == 1) {
                        mastermindChallenger.playChallengerModeMastermind();
                        display.displayAskTypeOfGame();
                        gameTypeChoice = display.displayGameTypeChoice();
                        break;
                    }
                case 2:
                    while (gameTypeChoice == 2) {
                        break;
                    }
                case 3:
                    while (gameTypeChoice == 3) {
                        break;
                    }
                default:
                    break;
            } while (gameTypeChoice == 1 || gameTypeChoice == 2 || gameTypeChoice == 3);
    }


}
