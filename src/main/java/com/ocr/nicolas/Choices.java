package com.ocr.nicolas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Choices {

    Scanner sc = new Scanner(System.in);



    /**
     * Display Ask developer choice
     */
    public void displayAskDeveloperChoice() {

        System.out.println("Bienvenu aux jeux de logique");
        System.out.println("Voulez-vous jouer en Mode Developpeur ? ");
        System.out.println("1-> oui");
        System.out.println("2-> non");

    }

    /**
     * Display developer choice
     *
     */
    public void displayDeveloperChoice() {

        boolean responseIsGood;
        int developerChoice = 1;

        do {
            try {
                System.out.println("quel est votre choix: ");
                developerChoice = sc.nextInt();
                responseIsGood = true;
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("ooups erreur chiffre uniquement (1 ou 2):");
                responseIsGood = false;
            }
        } while (!responseIsGood);

        do {
            switch (developerChoice) {
                case 1:
                    System.out.println("Vous avez choisi le mode developpeur");
                    responseIsGood = true;
                    break;
                case 2:
                    System.out.println("Vous n'avez pas choisi le mode developpeur");
                    responseIsGood = true;
                    break;
                default:
                    System.out.println("Veuillez choisir parmi les choix proposés (1 ou 2):");
                    responseIsGood = false;
                    developerChoice = sc.nextInt();
                    break;
            }
        }while (!responseIsGood);
    }
}




