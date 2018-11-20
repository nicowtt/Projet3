package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {

        // lecture du fichier config.properties.
        ReadPropertiesFile read = new ReadPropertiesFile();
        read.readConfigProperties();
        int nbrBoxesCombinationMysteryNumber = read.getNbrBoxesCombinationSearchNumber(); // = nbr de digit
        String developerMode = read.getDeveloperMode(); // developer mode?
        int nbrOfTryMysteryNumber = read.getNbrOfTryMysteryNumber(); // nomber of try

        // Set du jeu Recherche nombre
        MysteryNumber mysteryNumber = new MysteryNumber();
        mysteryNumber.setNbrDigit(nbrBoxesCombinationMysteryNumber);

        //declaration variable pour jouer
        int playGames = 1;
        int replay = 1;

        // Lancement des jeux:

        // boucle principale "demande du jeux"
        while (playGames == 1) {

            // Affichage du menu du choix des jeux.
            MenuDisplay display = new MenuDisplay();
            display.displayAskGamesMenu();

            // Recuperation variable du choix des jeux
            int gamesMenuChoice = display.displayGamesMenuChoice();
            if (gamesMenuChoice == 3) {System.exit(0);}

            // Affichage du menu du type de jeux  .
            display.displayAskTypeOfGame();

            // Recuperation variable du type de jeux
            int gameTypeChoice = display.displayGameTypeChoice();

            //possibilite de replay
            replay = 1;

            //boucle secondaire "demande de replay"
            while (replay == 1) {
                switch (gamesMenuChoice) {
                    case 1:
                        switch (gameTypeChoice) {
                            case 1:
                                //**********jeux searchnumber+/-****** mode challenger******

                                // Recuperation variable random ordinateur  -> randomNumberString
                                String randomNumberString = mysteryNumber.computerNbrCombination(nbrBoxesCombinationMysteryNumber);
                                logger.info("--------> aleatoire String computeur = " + randomNumberString);

                                // Je donne le nombre d'essai possible
                                System.out.println("vous avez " + nbrOfTryMysteryNumber + " essai");

                                // Je lance le jeux
                                int nbrLoop = nbrOfTryMysteryNumber;
                                int win = 0;
                                int loopForInformation = 0;

                                do {
                                    while (nbrLoop != 0) {
                                        loopForInformation += 1;
                                        logger.info("");
                                        logger.info("********************   Boucle " + loopForInformation + "    *********************");

                                        // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserString
                                        display.displayAskNumber(nbrBoxesCombinationMysteryNumber);
                                        String nbrUserString = display.getUserChoiceStringExport();

                                        logger.info("nombre entré par l'utilisateur = " + nbrUserString);

                                        // Je lance la comparaison des deux nombres (class MysteryNumber)
                                        mysteryNumber.CompareTwoString(randomNumberString, nbrUserString);

                                        // J'affiche la réponse de la comparaison
                                        String afterCompareImport = mysteryNumber.getAfterCompareExport();
                                        System.out.println("" + afterCompareImport);

                                        // je verifie si le Mode developper a été demandé
                                        if (developerMode.contains("true")) {
                                            System.out.println("(" + randomNumberString + ")");
                                        } else {
                                            System.out.println("");
                                        }

                                        // je teste si gagnant ou perdant
                                        int winTest = mysteryNumber.getCounterForWinExport();
                                        if (winTest == 1) {
                                            System.out.println(" Exellent Vous avez gagné !!!");
                                            win = 1;
                                            nbrLoop = 0;
                                            logger.info("l'utilisateur a gagné contre l'ordinateur aprés " + loopForInformation + " essais");
                                            System.out.println("");
                                            //remise a zero des parametres de choix
                                            gamesMenuChoice = 0;
                                            gameTypeChoice = 0;
                                            //lancement du menu du nouveau choix (1- rejouer; 2- Retour choix jeux; 3- quitter
                                            display.displayAskIfReplay();
                                            display.displayReplayChoice();
                                        } else {
                                            nbrLoop--;
                                            System.out.println(" il vous reste " + nbrLoop + " essai.");
                                        }
                                    }
                                } while (nbrLoop != 0);
                                if (win == 0) {
                                    System.out.println(" vous avez perdu...");
                                    System.out.println("-----> le nombre mystere etait: " + randomNumberString);
                                    System.out.println("");

                                    //remise a zero des parametres de choix
                                    gamesMenuChoice = 0;
                                    gameTypeChoice = 0;

                                    //lancement du menu pour nouveau choix (1- rejouer; 2- Retour choix jeux; 3- quitter
                                    display.displayAskIfReplay();
                                    int replayIntern = display.displayReplayChoice();
                                    switch (replayIntern) {
                                        case 1:
                                            //si l'utilisateur veut rejouer
                                            display.displayAskTypeOfGame();
                                            display.displayGameTypeChoice();
                                            gamesMenuChoice = 1;
                                            gameTypeChoice = 1;
                                            break;
                                        case 2:
                                            //l'utilisateur veut rechoisir un autre jeux
                                            gamesMenuChoice = 1;
                                            gameTypeChoice = 0;
                                            replay = 0;
                                            break;
                                        case 3:
                                            // l'utilisateur veut quitter
                                            System.exit(0);

                                        default:
                                            playGames = 0;
                                            break;
                                    }
                                }
                        }
                }
            }
        }
    }
}







