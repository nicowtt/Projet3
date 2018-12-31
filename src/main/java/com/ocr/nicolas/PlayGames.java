package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayGames {

    static final Logger logger = LogManager.getLogger();

    //commun a tous les jeux
    private static int nbrDigit;
    private static int nbrOfTry;
    private static String developerMode;

    //Pour le mastermind
    private static int nbrMaxOnDigit;

    public static void playGames() {

        // Variable
        int replay = 3;
        int gamesMenuChoice;

        // objets
        MenuDisplay display = new MenuDisplay();
        Config config = new Config();

        // lecture fichier readProperties
        nbrDigit = config.getNbrDigit();
        nbrOfTry = config.getNbrOfTry();
        developerMode = config.getDeveloperMode();
        nbrMaxOnDigit = config.getNbrMaxOnDigit();
        logger.info("lecture fichier config properties ");

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

        // Variable
        int replay = 3;

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
            int gameTypeChoice = display.displayGameTypeChoice();

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
    protected static String computerNbrCombination(int min, int max) {

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
    protected static int replay() {

        //variable
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
    protected static Replay replayEnum() {

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
