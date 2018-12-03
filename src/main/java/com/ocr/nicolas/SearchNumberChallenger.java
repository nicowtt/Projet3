package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchNumberChallenger extends SearchNumber {

    static final Logger logger = LogManager.getLogger();

    public SearchNumberChallenger(int nbrDigit, int nbrOfTry, String developerMode) {
        super(nbrDigit, nbrOfTry, developerMode);
    }

    /**
     * For play Challenger mode of Search Number
     * @return
     */
    public void playChallengerMode () {

        // declaration objets et mise a zero variable
        MenuDisplay display = new MenuDisplay();
        boolean isWin = false;

        // Recuperation variable random ordinateur  -> randCompChallenger
        String randCompChallenger = this.computerNbrCombination(0, 9);
        logger.info("--------> aleatoire String computeur = " + randCompChallenger);

        // Je donne le nombre d'essai possible
        System.out.println("tu as " + nbrOfTry + " essai.");

        // Je lance le jeux
        int nbrLoopChallengerMode = nbrOfTry;
        int loopForChallengerMode = 0;

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
            if (developerMode.contains("true")) {System.out.println("(" + randCompChallenger + ")");}
            else {System.out.println("");}

            // je teste si gagnant ou perdant
            boolean winTestChallenger = this.isWin;
            if (winTestChallenger) {
                System.out.println(" Exellent tu as gagné !!! au " + loopForChallengerMode + "ème essai." );
                isWin = true;
                nbrLoopChallengerMode = 0;
                logger.info("l'utilisateur a gagné contre l'ordinateur aprés " + loopForChallengerMode + " essais");
                System.out.println("");
            } else {
                nbrLoopChallengerMode--;
                System.out.println(" il te reste " + nbrLoopChallengerMode + " essai.");
            }
        } while (nbrLoopChallengerMode != 0);
        if (!isWin) {
            System.out.println(" tu as perdu... :-(");
            System.out.println("-----> la combinaison mystère etait: " + randCompChallenger);
            System.out.println("");
            logger.info("l'utilisateur a perdu");
        }
        //lancement du menu pour replay
        this.replay();
    }
}


// ( * pour info * methode "playChallengerMode" total = 57 - 24 lignes (espace , teste et logger) ->  33 lignes



