package com.ocr.nicolas.SearchNumber;

import com.ocr.nicolas.*;

public class SearchNumberChallenger extends SearchNumber implements PlayChallenger {

    public SearchNumberChallenger(Config config) {
        super(config);
    }

    /**
     * For play Challenger mode of Search Number
     * @return
     */
    public Replay playChallengerMode() {

        // declaration objets
        MenuDisplay display = new MenuDisplay();

        //variables
        Replay replayEnum;
        isWin = false;
        int loopForChallengerMode = 0;
        int inverseLoop = nbrOfTry;

        // Recuperation variable random ordinateur  -> randCompChallenger
        String randCompChallenger = this.computerNbrCombination(0, 9);
        logger.info("--------> aleatoire String computeur = " + randCompChallenger);

        // Je donne le nombre d'essais possible
        System.out.println("tu as " + nbrOfTry + " essai(s) pour trouver la combinaison de l'ordinateur.");

        do {
            loopForChallengerMode += 1;
            logger.info("");
            logger.info("********************   Boucle " + loopForChallengerMode + "    *********************");

            // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserChallenger
            display.displayAskNumber(nbrDigit);
            String nbrUserChallenger = display.getUserChoiceStringExport();
            logger.info("nombre entré par l'utilisateur = " + nbrUserChallenger);

            // Je lance la comparaison et j'affiche le resultat
            String afterCompareChallenger = this.CompareTwoString(randCompChallenger, nbrUserChallenger);
            System.out.println(afterCompareChallenger);

            // je verifie si le Mode developper a été demandé
            if (developerMode.contains("true") || Config.isDEV()) {System.out.println("(" + randCompChallenger + ")");}

            // je teste si gagnant ou perdant
            inverseLoop -= 1;
            this.testIfUserWinChallengerMode(loopForChallengerMode, randCompChallenger,nbrUserChallenger,inverseLoop);
            if (isWin) { inverseLoop = 0;}

        } while (inverseLoop != 0);
        if (!isWin) {
            System.out.println(" tu as perdu... :-(");
            System.out.println("");
            logger.info("l'utilisateur a perdu");
            System.out.println("-----> la combinaison mystère était: " + randCompChallenger);
            System.out.println("");
        }

        //lancement du menu pour replay
        replayEnum = PlayGames.replayEnum();
        return replayEnum;
    }
}


// ( * pour info * methode "playChallengerMode" total = 51 - 23 lignes (espace , texte et logger) ->  28 lignes



