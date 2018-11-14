package com.ocr.nicolas;

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
        int NbrBoxesCombinationSearchNumber = read.getNbrCombinationSearchNumber();

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
                System.out.println(" lancement du jeu Recherche +/- en mode challenger");
                switch (gameTypeChoice) {
                    case 1:
                        //recuperation variable random ordinateur
                        int randomNumber = searchNumber.computerNbrCombination(NbrBoxesCombinationSearchNumber);

                        //je trouve le nombre de digit de l'ordinateur
                        int nbrDigitComputer = searchNumber.FindNbrDigit(randomNumber);

                        //je lance la demande de nombre utilisateur et recupere la valeur
                        int nbrUser = display.displayAskNumber(NbrBoxesCombinationSearchNumber);

                        //je trouve le nombre de digit utilisateur
                        int nbrDigitUser = searchNumber.FindNbrDigit(nbrUser);

                        //je fait une autre ArrayList utilisateur
                        searchNumber.combinationOnBoard(randomNumber, nbrUser, nbrDigitComputer, nbrDigitUser);

                        //je compare les listes



                    default:
                        //System.out.println("aucun type de jeux");


                }
        }
    }
}

