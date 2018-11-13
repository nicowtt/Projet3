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
        int nbrCombinationSearchNumber = read.getNbrCombinationSearchNumber();

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
        searchNumber.setNbrCombinationSearchNumber(nbrCombinationSearchNumber);


        //recuperation variable random ordinateur
        int randomNumber = searchNumber.computerNbrCombination(nbrCombinationSearchNumber);

        //je trouve le nombre de digit de la combinaison random oridnateur et le recupère dans le main
        int nbrDigit = searchNumber.FindNbrDigit(randomNumber);

        //je met chaque digit du nombre aleatoire dans une ArrayList
        searchNumber.combinationOnBoard(randomNumber, nbrDigit);





        //lancement des jeux:
        switch (gamesMenuChoice) {
            case 1:
                System.out.println(" lancement du jeu Recherche +/-");
                switch (gameTypeChoice) {
                    case 1:
                        int userChoice = display.displayAskNumber(nbrCombinationSearchNumber);
                        searchNumber.compare(nbrCombinationSearchNumber, userChoice);
                    default:
                        //System.out.println("aucun type de jeux");


                }
            default:
                //System.out.println("aucun choix");
        }





    }
}

