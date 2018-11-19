package com.ocr.nicolas;

import jdk.nashorn.internal.ir.IfNode;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.ocr.nicolas.Log4j.logger;

public class MenuDisplay {

    Scanner sc = new Scanner(System.in);

    private String userChoiceStringExport;

    public String getUserChoiceStringExport() {
        return userChoiceStringExport;
    }

    /**
     * Display ask games menu.
     */
    public void displayAskGamesMenu() {
        System.out.println("Voulez-vous jouer:");
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
                    System.out.println("Vous avez choisi le jeux Recherche +/- ");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 2:
                    System.out.println("Vous avez choisi le Mastermind");
                    System.out.println("");
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
        logger.info("Choix du jeux-> " + gameChoice + " (1- Recherche+/- ; 2- Mastermind)");
        return gameChoice;
    }

    /**
     * Display Type of Games.
     */
    public void displayAskTypeOfGame() {

        System.out.println("******Choisissez le mode de jeux:*******");
        System.out.println("1-Challenger -> trouve le nombre mystère de l'ordinateur");
        System.out.println("2-Défenseur -> L'ordinateur doit trouver ton nombre");
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
                System.out.println("-> Quel est votre choix: ");
                gameTypeChoice = sc.nextInt();
                responseIsGood = true;
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Erreur chiffre uniquement (1, 2 ou 3)");
                responseIsGood = false;
            }
        } while (!responseIsGood);

        do {
            switch (gameTypeChoice) {
                case 1:
                    System.out.println("Vous avez choisi le mode Challenger");
                    System.out.println("************************************");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 2:
                    System.out.println("Vous avez choisi le mode Défenseur");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 3:
                    System.out.println("Vous avez choisi le mode Duel");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                default:
                    try {
                        System.out.println("Il faut choisir parmis les choix proposés (1,2 ou 3)");
                        responseIsGood = false;
                        gameTypeChoice = sc.nextInt();
                    } catch (InputMismatchException e) {
                        sc.next();
                        break;
                    }
            }
        } while (!responseIsGood);
        logger.info("Mode de jeux-> " + gameTypeChoice + " (1- Challenger ; 2- défenseur ; 3- Duel)");
        return gameTypeChoice;
    }

    /**
     * display first choice of number on String
     *
     * @param nbr number of boxes (digit)
     */
    public void displayAskNumber(int nbr) {

        // demande et verification nombre de digit entrée utilisateur
        boolean responseIsGood;
        responseIsGood = false;
        int nbrDigit = nbr;
        String digitString = "";
        int counterTotalLetter = 0;


        System.out.println("Choisissez une suite de " + nbr + " chiffre :");

        // je compte le nombre de chiffre rentré (et je redemande si il ya des lettres ou peu de chiffres)

        do {
            try {
                String userChoiceString = sc.next();
                userChoiceStringExport = userChoiceString;
                String userChoiceForCountString = userChoiceString;
                while (responseIsGood != true) {
                    // je prend le premier caractère
                    char letter = userChoiceForCountString.charAt(counterTotalLetter);
                    // je le converti en string
                    String letterString = String.valueOf(letter);
                    // je le converti en Int
                    int letterInt = Integer.parseInt(letterString);
                    // j'incrémente mon compteur de nombre de chiffre
                    counterTotalLetter++;
                    // je compare par rapport au nombre de digit
                    if (counterTotalLetter == nbrDigit) {
                        responseIsGood = true;
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Vous devez rentrer " + nbr + " chiffres");
            } catch (NumberFormatException e ) {
                System.out.println("Lettre non accepté");
            }
            }while (responseIsGood != true) ;
        counterTotalLetter++;
    }
}





