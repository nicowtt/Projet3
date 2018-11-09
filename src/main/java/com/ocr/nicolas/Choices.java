package com.ocr.nicolas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Choices {

    Scanner sc = new Scanner(System.in);


    /**
     * Display ask games menu
     */
    public void displayAskGamesMenu() {
        System.out.println("Quels jeux voulez-vous lancer:");
        System.out.println("1-> Recherche +/-");
        System.out.println("2-> Mastermind");

    }

    public void displayGamesMenuChoice() {

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
                    }catch (InputMismatchException e) {
                        sc.next();
                        break;
                    }
                    }
        } while (!responseIsGood);
    }
}



