package com.ocr.nicolas;


public class Main {


    public static void main(String[] args) {

        // lecture config.propertie et variable a zero
        ReadPropertiesFile read = new ReadPropertiesFile();
        read.readConfigProperties();
        int nbrDigit = read.getNbrDigit();
        String developerMode = read.getDeveloperMode(); // developer mode?
        int nbrOfTry = read.getNbrOfTry();
        int countWin = 0;

        // Affichage du menu du choix des jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskGamesMenu();

        // Recuperation variable du choix des jeux
        int gamesMenuChoice = display.displayGamesMenuChoice();
        if (gamesMenuChoice == 3) {System.exit(0);}
        switch (gamesMenuChoice) {
            case 1:
                while (gamesMenuChoice == 1) {
                    Games one = new SearchNumber(nbrDigit, nbrOfTry,developerMode,countWin);
                    ((SearchNumber) one).playSearchNumber();
                }
            case 2:
                while (gamesMenuChoice == 2) {
                    // mastermind
                }
            default:
                break;
        }
    }
}














