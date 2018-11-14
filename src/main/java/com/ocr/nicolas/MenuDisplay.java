package com.ocr.nicolas;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.ocr.nicolas.Log4j.logger;

public class MenuDisplay {

    Scanner sc = new Scanner(System.in);

    int NbrBoxesCombinationSearchNumber;

    public int getNbrBoxesCombinationSearchNumberCombinationSearchNumber() {
        return NbrBoxesCombinationSearchNumber;
    }



    /**
     * Display ask games menu.
     */
    public void displayAskGamesMenu() {
        System.out.println("Quels jeux voulez-vous lancer:");
        System.out.println("1-> Recherche +/-");
        System.out.println("2-> Mastermind");

    }

    /**
     * Display and confirmation of games choice.
     */
    public int displayGamesMenuChoice() {

        boolean responseIsGood;
        int gameChoice = -1;

        do {
            try {
                System.out.println("quel est votre choix:");
                gameChoice = sc.nextInt();
                responseIsGood = true;
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("ooups erreur chiffre uniquement (1 ou 2):");
                responseIsGood = false;

            }
        } while (!responseIsGood);

        do {
            switch (gameChoice) {
                case 1:
                    System.out.println("Vous avez choisi le jeux Recherche +/-");
                    responseIsGood = true;
                    break;
                case 2:
                    System.out.println("Vous avez choisi le Mastermind");
                    responseIsGood = true;
                    break;
                default:
                    try {
                        System.out.println("Veuillez choisir parmi les choix proposés (1 ou 2):");
                        responseIsGood = false;
                        gameChoice = sc.nextInt();
                    } catch (InputMismatchException e) {
                        sc.next();
                        break;
                    }
            }
        } while (!responseIsGood);
        logger.info("Choix du jeux-> " + gameChoice + "(1- Recherche+/- ; 2- Mastermind)" );
        return gameChoice;
    }

    /**
     * Display Type of Games.
     */
    public void displayAskTypeOfGame() {

        System.out.println("Choisissez le mode de jeux:");
        System.out.println("1-Challenger -> toi contre l'ordinateur");
        System.out.println("2-Défenseur -> ordinateur contre toi");
        System.out.println("3-Duel");
    }

    /**
     * Display and confirmation of Type games choice.
     */
    public int displayGameTypeChoice() {

        boolean responseIsGood;
        int gameTypeChoice = -1;

        do {
            try {
                System.out.println("Quel est votre choix: ");
                gameTypeChoice = sc.nextInt();
                responseIsGood = true;
            }
            catch (InputMismatchException e) {
                sc.next();
                System.out.println("Erreur chiffre uniquement (1, 2 ou 3)");
                responseIsGood = false;
            }
        }while (!responseIsGood);

        do {
            switch (gameTypeChoice) {
                case 1:
                    System.out.println("Vous avez choisi le mode Challenger");
                    responseIsGood = true;
                    break;
                case 2:
                    System.out.println("Vous avez choisi le mode Défenseur");
                    responseIsGood = true;
                    break;
                case 3:
                    System.out.println("Vous avez choisi le mode Duel");
                    responseIsGood = true;
                    break;
                default:
                    try {
                        System.out.println("Il faut choisir parmis les choix proposés (1,2 ou 3)");
                        responseIsGood = false;
                        gameTypeChoice = sc.nextInt();
                    }
                    catch (InputMismatchException e) {
                        sc.next();
                        break;
                    }
            }
        }while (!responseIsGood);
        logger.info("Choix du jeux-> " + gameTypeChoice + "(1- Challenger ; 2- défenseur ; 3- Duel)" );
        return gameTypeChoice;
    }

    /**
     * display first choice of number
     */
    public int displayAskNumber(int nbr) {

        int userChoice = -1;
        boolean responseIsGood;
        int nbrDigit = nbr;
        String digitString = "";

        while (nbrDigit > 0) {
            digitString = digitString + "9";
            nbrDigit--;
        }
        int finalNumberMaxLimit = Integer.parseInt(digitString);
        logger.info("number max utilisateur = " + finalNumberMaxLimit);

        do {
            try {
                System.out.println("Choisissez un nombre a " + nbr + " chiffre :");
                userChoice = sc.nextInt();
                if (userChoice > finalNumberMaxLimit) {
                    System.out.println("Le nombre doit etre inferieur a :" + finalNumberMaxLimit);
                    responseIsGood = false;
                    }
                    else{
                    responseIsGood = true;
                }

            }
            catch (InputMismatchException e) {
                sc.next();
                System.out.println("Ooups nombre inferieur a: " + finalNumberMaxLimit + " seulement");
                responseIsGood = false;
            }
        }while (!responseIsGood);
        return userChoice;



    }

}



