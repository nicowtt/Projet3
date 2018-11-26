package com.ocr.nicolas;


public class Games {


    int playTwoGames = 1;
    int replay;


    /**
     * For choice game
     * @return playing
     */
    public int playTwoGames() {

        // Affichage du menu du choix des jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskGamesMenu();

        // Recuperation variable du choix des jeux
        int gamesMenuChoice = display.displayGamesMenuChoice();
        if (gamesMenuChoice == 3) { System.exit(0); }

        switch (gamesMenuChoice) {
            case 1:
                while (gamesMenuChoice == 1) {
                    SearchNumber searchNumber = new SearchNumber();
                    searchNumber.playSearchNumber();
                }
            case 2:
                while (gamesMenuChoice == 2) {
                    // mastermind
                }
            default:
                break;
        }

        playTwoGames = 0;
        return playTwoGames;
    }
    }



