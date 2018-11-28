package com.ocr.nicolas;

public class Games {


    public void playGames() {
        // lecture config.propertie et variable a zero
        ReadPropertiesFile read = new ReadPropertiesFile();
        read.readConfigProperties();
        int nbrDigit = read.getNbrDigit();
        String developerMode = read.getDeveloperMode(); // developer mode?
        int nbrOfTry = read.getNbrOfTry();
        boolean isWin = false;


        // Affichage du menu du choix des jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskGamesMenu();

        // Recuperation variable du choix des jeux
        int gamesMenuChoice = display.displayGamesMenuChoice();
        if (gamesMenuChoice == 3) {System.exit(0);}

        switch (gamesMenuChoice) {
            case 1:
                while (gamesMenuChoice == 1) {
                    SearchNumber searchNumber = new SearchNumber(nbrDigit, nbrOfTry, developerMode, isWin);
                    searchNumber.playSearchNumber();
                    break;
                }
            case 2:
                while (gamesMenuChoice == 2) {
                    // mastermind
                    break;
                }
            default:
                break;
        }
    }
}
