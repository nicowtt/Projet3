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
        System.out.println("Mode Developpeur ? ");
        System.out.println("1-oui");
        System.out.println("2-non");

    }

    /**
     * Display developer choice
     *
     */
    public void displayDeveloperChoice() {

        int developerChoice = -1;
        boolean responseIsGood;
        do {
            System.out.println("choix: ");
            developerChoice = sc.nextInt();
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
                    System.out.println("Vous n'avez pas choisi parmi les choix proposés");
                    responseIsGood = false;
                    break;
            }
        }while (!responseIsGood);
    }
}


    /**
     * Run asking developerMode
     *
     */



