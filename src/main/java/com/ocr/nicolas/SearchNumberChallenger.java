package com.ocr.nicolas;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SearchNumberChallenger {

    static final Logger logger = LogManager.getLogger();

    /**
     * For play Challenger mode of Search Number
     * @return
     */
    public int playChallengerMode () {

        int playChallengerMode = 1;

        //je  recupère les infos importantes
        ReadPropertiesFile read = new ReadPropertiesFile();
        read.readConfigProperties();
        int nbrDigit = read.getNbrDigit();
        String developerMode = read.getDeveloperMode(); // developer mode?
        int nbrOfTry = read.getNbrOfTry();

        // declaration objet
        SearchNumber searchNumber = new SearchNumber();
        MenuDisplay display = new MenuDisplay();
        Games games = new Games();

        // Recuperation variable random ordinateur  -> randCompChallenger
        String randCompChallenger = searchNumber.computerNbrCombination(nbrDigit, 0, 9);
        logger.info("--------> aleatoire String computeur = " + randCompChallenger);

        // Je donne le nombre d'essai possible
        System.out.println("vous avez " + nbrOfTry + " essai");

        // Je lance le jeux
        int nbrLoopChallengerMode = nbrOfTry;
        int winChallenger = 0;
        int loopForChallengerMode = 0;

        do {
            while (nbrLoopChallengerMode != 0) {
                loopForChallengerMode += 1;
                logger.info("");
                logger.info("********************   Boucle " + loopForChallengerMode + "    *********************");

                // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserChallenger
                display.displayAskNumber(nbrDigit);
                String nbrUserChallenger = display.getUserChoiceStringExport();

                logger.info("nombre entré par l'utilisateur = " + nbrUserChallenger);

                // Je lance la comparaison et j'affiche le resultat
                String afterCompareChallenger = searchNumber.CompareTwoString(randCompChallenger, nbrUserChallenger);
                System.out.println(afterCompareChallenger);

                // je verifie si le Mode developper a été demandé
                if (developerMode.contains("true")) {System.out.println("(" + randCompChallenger + ")");}
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
        if (replayIntern == 2) {games.playTwoGames();}

        return playChallengerMode;
    }

}






