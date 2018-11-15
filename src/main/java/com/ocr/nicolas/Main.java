package com.ocr.nicolas;

import jdk.nashorn.internal.ir.IfNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {

        /* developer Mode recovery
        boolean developerMode;

        for (int i = 0; i < args.length; i++) {
            if ("-d".equals(args[i])) {
                developerMode = true;
                logger.info("developperMode");
            }
        }
        */

        // lecture du fichier config.properties.
        ReadPropertiesFile read = new ReadPropertiesFile();
        read.readConfigProperties();
        int NbrBoxesCombinationSearchNumber = read.getNbrBoxesCombinationSearchNumber(); //nbr de digit
        String developerMode = read.getDeveloperMode(); // developer mode?
        int nbrOfTrySearchNumber = read.getNbrOfTrySearchNumber(); // nomber of try

        //affichage du menu du choix des jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskGamesMenu();

        //recuperation variable du choix des jeux
        int gamesMenuChoice = display.displayGamesMenuChoice();

        //affichage du menu du type de jeux  .
        display.displayAskTypeOfGame();

        //recuperation variable du type de jeux
        int gameTypeChoice = display.displayGameTypeChoice();

        //**set du jeu Recherche nombre***
        SearchNumber searchNumber = new SearchNumber();
        searchNumber.setNbrCombinationSearchNumber(NbrBoxesCombinationSearchNumber);


        //lancement des jeux:
        switch (gamesMenuChoice) {
            case 1:
                switch (gameTypeChoice) {
                    case 1:
                        //recuperation variable random ordinateur
                        int randomNumber = searchNumber.computerNbrCombination(NbrBoxesCombinationSearchNumber);

                        //je trouve le nombre de digit de l'ordinateur
                        int nbrDigitComputer = searchNumber.FindNbrDigit(randomNumber);

                        //Je donne le nombre d'essai possible
                        System.out.println("vous avez " + nbrOfTrySearchNumber + " essai");

                        //je lance le mode
                        int nbrLoop = nbrOfTrySearchNumber;
                        int win = 0;

                            do {
                                while (nbrLoop != 0) {
                                    //je lance la demande de nombre utilisateur et recupere la valeur
                                    int nbrUser = display.displayAskNumber(NbrBoxesCombinationSearchNumber);

                                    //je trouve le nombre de digit utilisateur
                                    int nbrDigitUser = searchNumber.FindNbrDigit(nbrUser);

                                    //je fait une autre ArrayList utilisateur et je compare avec l'ordi.
                                    searchNumber.combinationOnBoard(randomNumber, nbrUser, nbrDigitComputer, nbrDigitUser);

                                    //J'affiche la reponse de la comparaison
                                    String afterCompareImport = searchNumber.getAfterCompareExport();
                                    System.out.println("" + afterCompareImport);

                                    //mode developper (oui/non)
                                    if (developerMode.contains("true")) {
                                        System.out.println("(" + randomNumber + ")");
                                    } else {
                                        System.out.println("");
                                    }
                                    //je teste gagnat ou perdant
                                    if (randomNumber == nbrUser) {
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
                            }
                            else {

                            }
            default:
                //System.out.println("aucun type de jeux
        }
    }}}



