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

        //affichage du menu du choix des jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskGamesMenu();

        //recuperation variable du choix des jeux
        int gamesMenuChoice = display.displayGamesMenuChoice();

        //affichage du menu du type de jeux  .
        display.displayAskTypeOfGame();

        //recuperation variable du type de jeux
        int gameTypeChoice = display.displayGameTypeChoice();

        //**set du jeu Recherche nombre*** !!!ici je change pour mysteryNumber!!! etape 1
        MysteryNumber mysteryNumber = new MysteryNumber();
        mysteryNumber.setNbrDigit(nbrBoxesCombinationMysteryNumber);


        //lancement des jeux:
        switch (gamesMenuChoice) {
            case 1:
                switch (gameTypeChoice) {
                    case 1:
                        //recuperation variable random ordinateur !!! etape 2!! -> randomNumberString
                        String randomNumberString = mysteryNumber.computerNbrCombination(nbrBoxesCombinationMysteryNumber);
                        logger.info("aleatoire String computeur = " + randomNumberString);

                        //Je donne le nombre d'essai possible
                        System.out.println("vous avez " + nbrOfTryMysteryNumber + " essai");

                        //je lance le mode
                        int nbrLoop = nbrOfTryMysteryNumber;
                        int win = 0;

                        do {
                            while (nbrLoop != 0) {
                                //je lance la demande de nombre utilisateur et recupere la valeur !! etape 3 !!! -> nbrUserString
                                String nbrUserString = display.displayAskNumber(nbrBoxesCombinationMysteryNumber);
                                logger.info("nombre user dans la class main = " + nbrUserString);

                                //je compare les deux nombres !!!!! etape 4 !!!!!
                                mysteryNumber.CompareTwoString(randomNumberString, nbrUserString, nbrBoxesCombinationMysteryNumber);

                                //J'affiche la reponse de la comparaison
                                String afterCompareImport = mysteryNumber.getAfterCompareExport();
                                System.out.println("" + afterCompareImport);

                                //mode developper (oui/non)
                                if (developerMode.contains("true")) {
                                    System.out.println("(" + randomNumberString + ")");
                                } else {
                                    System.out.println("");
                                }
                                //je teste gagnant ou perdant !!!! etape 5 !!!!
                                // je fait une variable pour voir si gagnant
                                int winTest = mysteryNumber.getCounterForWinExport();
                                if (winTest == 1) {
                                    System.out.println(" Exellent Vous avez gagné !!!");
                                    win = 1;
                                    nbrLoop = 0;

                                } else {
                                    nbrLoop--;

                                    System.out.println(" il vous reste " + nbrLoop + " essai.");
                                }
                            }
                        } while (nbrLoop != 0);
                        if (win == 0) {
                            System.out.println(" vous avez perdu");
                            System.out.println("le nombre mystere etait: " + randomNumberString);
                        } else {

                        }
                }
        }
    }
}







