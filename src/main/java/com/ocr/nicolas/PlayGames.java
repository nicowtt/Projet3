package com.ocr.nicolas;

import com.ocr.nicolas.Mastermind.MastermindChallenger;
import com.ocr.nicolas.Mastermind.MastermindDefender;
import com.ocr.nicolas.Mastermind.MastermindDuel;
import com.ocr.nicolas.SearchNumber.SearchNumberChallenger;
import com.ocr.nicolas.SearchNumber.SearchNumberDefender;
import com.ocr.nicolas.SearchNumber.SearchNumberDuel;

public class PlayGames {

    private static int nbrDigit;

    public static void playGames() {

        // Variable
        int replay = 3;
        int gamesMenuChoice;

        // objets
        MenuDisplay display = new MenuDisplay();
        Config config = new Config();

        // lecture dans fichier readProperties
        nbrDigit = config.getNbrDigit();

        // boucle si replay
        do {
            // Affichage du menu du choix des jeux.
            display.displayAskGamesMenu();

            // Recuperation variable du choix des jeux
            gamesMenuChoice = display.displayGamesMenuChoice();
            if (gamesMenuChoice == 3) {
                System.exit(0);
            }
            else { replay = playGamesMode(config, gamesMenuChoice); }

        } while (replay >= 2);
    }

    /**
     * For playing game mode
     *
     * @return playing
     */
    public static int playGamesMode(Config config, int pGame) {

        // Variables
        int replay = 3;
        int gameTypeChoice;

        // objets
        PlayChallenger searchNumberChallenger = new SearchNumberChallenger(config);
        PlayDefender searchNumberDefender = new SearchNumberDefender(config);
        PlayDuel searchNumberDuel = new SearchNumberDuel(config);
        PlayChallenger mastermindChallenger = new MastermindChallenger(config);
        PlayDefender mastermindDefender = new MastermindDefender(config);
        PlayDuel mastermindDuel = new MastermindDuel(config);

        do {
            // Affichage du menu du type de jeux.
            MenuDisplay display = new MenuDisplay();
            display.displayAskTypeOfGame();

            // Recuperation variable du type de jeux
            gameTypeChoice = display.displayGameTypeChoice();

            switch (gameTypeChoice) {
                case 1:
                    while (gameTypeChoice == 1) {
                        if (pGame == 1) {
                            replay = searchNumberChallenger.playChallengerMode();
                        }
                        else if (pGame == 2) {
                            replay = mastermindChallenger.playChallengerMode();
                        }
                        break;
                    }
                case 2:
                    while (gameTypeChoice == 2) {
                        if (pGame == 1) {
                            replay = searchNumberDefender.playDefenderMode();
                        }
                        else if (pGame == 2) {
                            replay = mastermindDefender.playDefenderMode();
                        }
                        break;
                    }
                case 3:
                    while (gameTypeChoice == 3) {
                        if (pGame == 1 ) {
                            replay = searchNumberDuel.playDuelMode();
                        }
                        else if (pGame == 2 ) {
                            replay = mastermindDuel.playDuelMode();
                        }
                        break;
                    }
                default:
                    break;
            }
        } while (replay == 1);
        return replay;
    }

    /**
     * for make random computer number(s) combination
     *
     * @return random digit number (type of character string)
     */
    public static String computerNbrCombination(int min, int max) {

        // variables
        String finalRandomDigitNumberString = "";
        int counterNbrDigit = nbrDigit;

        while (counterNbrDigit >= 1) {

            //int de 0 a 9 (base 10) avec min et max
            int base10RandomDigitNumber = min + (int) (Math.random() * ((max - min) + 1));
            //logger.info("base 10 random =" + base10RandomDigitNumber);

            // je converti le chiffre en string
            String base10RandomDigitNumberString = String.valueOf(base10RandomDigitNumber);

            // Je l'ajoute au string computer final
            finalRandomDigitNumberString = finalRandomDigitNumberString + base10RandomDigitNumberString;

            // j'incremente le compteur
            counterNbrDigit--;
        }
        return finalRandomDigitNumberString;
    }

    /**
     * For redirection of replay or leave
     * @return replay (1 = replay, 2 = gameschoice, 3 = leave)
     */
    public static int replay() {

        //variables
        int replay = 3;
        Replay replayEnum;

        // objets
        MenuDisplay display = new MenuDisplay();

        // affichage console for replay et redirection
        display.displayAskIfReplay();
        replayEnum = display.displayReplayChoice();
        if (replayEnum == Replay.REPLAY) {replay = 1;}
        if (replayEnum == Replay.GAMESCHOICE) {replay = 2;}
        if (replayEnum == Replay.EXIT) {System.exit(0);}

        return replay;
    }

    /**
     * For redirection of replay or leave
     * @return replayEnum
     */
    public static Replay replayEnum() {

        // variable
        Replay replayEnum;

        // objets
        MenuDisplay display = new MenuDisplay();

        // affichage console for replay et redirection
        display.displayAskIfReplay();
        replayEnum = display.displayReplayChoice();

        return replayEnum;
    }

}
