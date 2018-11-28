package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchNumberChallenger extends SearchNumber {

    static final Logger logger = LogManager.getLogger();

    public SearchNumberChallenger(int nbrDigit, int nbrOfTry, String developerMode, int countWin) {
        super(nbrDigit, nbrOfTry, developerMode, countWin);
    }

    /**
     * For play Challenger mode of Search Number
     * @return
     */
    public int playChallengerMode () {

        int playChallengerMode = 1;

        // declaration objets
        SearchNumber searchNumber = new SearchNumber(getNbrDigit(),getNbrOfTry(),getDeveloperMode(),getCountWin());
        MenuDisplay display = new MenuDisplay();
        Games games = new Games();

        // Recuperation variable random ordinateur  -> randCompChallenger
        String randCompChallenger = searchNumber.computerNbrCombination(getNbrDigit(), 0, 9);
        logger.info("--------> aleatoire String computeur = " + randCompChallenger);

        // Je donne le nombre d'essai possible
        System.out.println("vous avez " + getNbrOfTry() + " essai");

        // Je lance le jeux
        int nbrLoopChallengerMode = getNbrOfTry();
        int winChallenger = 0;
        int loopForChallengerMode = 0;

        do {
            while (nbrLoopChallengerMode != 0) {
                loopForChallengerMode += 1;
                logger.info("");
                logger.info("********************   Boucle " + loopForChallengerMode + "    *********************");

                // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserChallenger
                display.displayAskNumber(getNbrDigit());
                String nbrUserChallenger = display.getUserChoiceStringExport();

                logger.info("nombre entré par l'utilisateur = " + nbrUserChallenger);

                // Je lance la comparaison et j'affiche le resultat
                String afterCompareChallenger = searchNumber.CompareTwoString(randCompChallenger, nbrUserChallenger);
                System.out.println(afterCompareChallenger);

                // je verifie si le Mode developper a été demandé
                if (getDeveloperMode().contains("true")) {System.out.println("(" + randCompChallenger + ")");}
                else {System.out.println("");}

                // je teste si gagnant ou perdant
                int winTestChallenger = searchNumber.getCountWin();
                if (winTestChallenger == 1) {
                    System.out.println(" Exellent Vous avez gagné !!!");
                    winChallenger = 1;
                    nbrLoopChallengerMode = 0;
                    logger.info("l'utilisateur a gagné contre l'ordinateur aprés " + loopForChallengerMode + " essais");
                    System.out.println("");
                } else {
                    nbrLoopChallengerMode--;
                    System.out.println(" il vous reste " + nbrLoopChallengerMode + " essai.");
                }
            }
        } while (nbrLoopChallengerMode != 0);
        if (winChallenger == 0) {
            System.out.println(" vous avez perdu...");
            System.out.println("-----> le nombre mystere etait: " + randCompChallenger);
            System.out.println("");
            logger.info("l'utilisateur a perdu");

        }
        //lancement du menu pour nouveau choix (1- rejouer; 2- Retour choix jeux; 3- quitter
        display.displayAskIfReplay();
        int replayIntern = display.displayReplayChoice();
        if (replayIntern == 2) {games.playGames();}
        if (replayIntern == 3) {System.exit(0);}


        return playChallengerMode;
    }

}






