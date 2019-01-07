package com.ocr.nicolas;

import com.ocr.nicolas.Mastermind.MastermindChallenger;
import com.ocr.nicolas.Mastermind.MastermindDefender;
import com.ocr.nicolas.Mastermind.MastermindDuel;
import com.ocr.nicolas.SearchNumber.SearchNumberChallenger;
import com.ocr.nicolas.SearchNumber.SearchNumberDefender;
import com.ocr.nicolas.SearchNumber.SearchNumberDuel;

public class PlayGames {

    public static void playGames() {

        // Variables (Enumerate)
        Replay replayEnum = Replay.EXIT;
        WhatGames whatGameEnum;

        // objets
        MenuDisplay display = new MenuDisplay();
        Config config = new Config();

        // boucle si replay
        do {
            // Affichage du menu du choix des jeux.
            display.displayAskGamesMenu();

            // Recuperation variable du choix des jeux
            whatGameEnum = display.displayGamesMenuChoice();
            if (whatGameEnum == WhatGames.SEARCHNUMBER) {
                replayEnum = playGamesMode(config, whatGameEnum);
            }
            else if (whatGameEnum == WhatGames.MASTERMIND) {
                replayEnum = playGamesMode(config, whatGameEnum);
            }
            else if (whatGameEnum == WhatGames.EXIT) {
                System.exit(0);
            }
        } while (replayEnum != Replay.EXIT);
    }

    /**
     * For playing game mode
     *
     * @return playing
     */
    public static Replay playGamesMode(Config config, WhatGames pGame) {

        // Variables
        int gameTypeChoice;
        Replay replayEnum = Replay.EXIT;

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
                        if (pGame == WhatGames.SEARCHNUMBER) {
                            replayEnum = searchNumberChallenger.playChallengerMode();
                        }
                        else if (pGame == WhatGames.MASTERMIND) {
                            replayEnum = mastermindChallenger.playChallengerMode();
                        }
                        break;
                    }
                case 2:
                    while (gameTypeChoice == 2) {
                        if (pGame == WhatGames.SEARCHNUMBER) {
                            replayEnum = searchNumberDefender.playDefenderMode();
                        }
                        else if (pGame == WhatGames.MASTERMIND) {
                            replayEnum = mastermindDefender.playDefenderMode();
                        }
                        break;
                    }
                case 3:
                    while (gameTypeChoice == 3) {
                        if (pGame == WhatGames.SEARCHNUMBER ) {
                            replayEnum = searchNumberDuel.playDuelMode();
                        }
                        else if (pGame == WhatGames.MASTERMIND ) {
                            replayEnum = mastermindDuel.playDuelMode();
                        }
                        break;
                    }
                default:
                    break;
            }
        } while (replayEnum != Replay.GAMESCHOICE);
        return replayEnum;
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
