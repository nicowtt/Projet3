package com.ocr.nicolas;

import java.util.*;

public class Mastermind extends Games {


//    protected int nbrMaxOnDigit;

    private int goodplaceExport;
    private int presentExport;

    public int getGoodplaceExport() { return goodplaceExport;}

    public int getPresentExport() { return presentExport;}

    Scanner sc = new Scanner(System.in);


    /**
     * For playing Mastermind Game
     */
    protected void playMastermind() {

        // Affichage du menu du type de jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskTypeOfGame();

        // Recuperation variable du type de jeux
        int gameTypeChoice = display.displayGameTypeChoice();

        // objets
        MastermindChallenger mastermindChallenger = new MastermindChallenger();
        MastermindDefender mastermindDefender = new MastermindDefender();

        do
            switch (gameTypeChoice) {
                case 1:
                    while (gameTypeChoice == 1) {
                        mastermindChallenger.playChallengerModeMastermind();
                        display.displayAskTypeOfGame();
                        gameTypeChoice = display.displayGameTypeChoice();
                        break;
                    }
                case 2:
                    while (gameTypeChoice == 2) {
                        mastermindDefender.playDefenderModeMastermind();
                        display.displayAskTypeOfGame();
                        gameTypeChoice = display.displayGameTypeChoice();
                        break;
                    }
                case 3:
                    while (gameTypeChoice == 3) {
                        break;
                    }
                default:
                    break;
            } while (gameTypeChoice == 1 || gameTypeChoice == 2 || gameTypeChoice == 3);
    }

    /**
     * For create ArrayList with number ok in
     * @return ArrayList (Integer)
     */
    protected List<Integer> createListNbrOk() {

        //creation d'une list avec les chiffres possible
        List<Integer> listNbrOk = new ArrayList<>();

        //je met les chiffres ok a l'interieur
        for (int i = 0; i < (nbrMaxOnDigit + 1); i++) {listNbrOk.add(i);}
        logger.info("liste chiffres ok = " + listNbrOk);

        return listNbrOk;
    }


    /**
     * For force user input combination ok
     *
     * @return user input combination ok (string)
     */

    protected String inputUserStringMast() {

        boolean responseIsGood = true;
        int digitOk;
        String inputUserFinal;

        // je lance la methode pour avoir une liste des chiffres utilisable
        List<Integer> listNbrOk = this.createListNbrOk();

        do {
            // entrée finale ok
            inputUserFinal = "";
            responseIsGood = true;

            // je fais venir l'entrée utilisateur
            String inputUser = sc.nextLine();

            // je compare chaque digit avec le tableau de chiffre ok sinon boucle
            int countTooManyDigit = 0;

            try {
                for (int i = 0; i < inputUser.length(); i++) {
                    char letter = inputUser.charAt(i);
                    String letterStr = String.valueOf(letter);
                    Integer letterInt = Integer.valueOf(letterStr);
                    // comparaison si il est dans la liste
                    for (int j = 0; j < listNbrOk.size(); j++) {
                        digitOk = listNbrOk.get(j);
                        if (digitOk == letterInt) {
                            inputUserFinal = inputUserFinal + letterStr;
                            countTooManyDigit++;
                        }
                    }
                }
                if (countTooManyDigit != nbrDigit) {
                    System.out.println("choisi une combinaison de " + nbrDigit + " chiffre (chiffre entre 0 et " + nbrMaxOnDigit + ")");
                    logger.info("mauvaise entrée utilisateur");
                    responseIsGood = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("lettre non accepté, tu doit choisir une combinaison de " + nbrDigit + " chiffre (chiffre entre 0 et " + nbrMaxOnDigit + ")");
                responseIsGood = false;
            }

        } while (!responseIsGood);
        logger.info("Entrée utilisateur = " + inputUserFinal);
        return inputUserFinal;
    }

    protected void compareTwoStringMast(String pFirst, String pSecond) {

        //Je crée deux ArrayList avec des booleans
        List<Boolean> listpFirstBoolean = new ArrayList<>();
        List<Boolean> listpSecondBoolean = new ArrayList<>();

        // je crée deux compteur
        int goodplace = 0; // les bien placés
        int present = 0; // les présents


        //je compare pour trouvé les bien placés
        for (int i = 0; i < pFirst.length(); i++) {
            if (pFirst.charAt(i) != pSecond.charAt(i)) {
                listpFirstBoolean.add(false);
                listpSecondBoolean.add(false);
            }
            if (pFirst.charAt(i) == pSecond.charAt(i)) {
                goodplace++;
                listpFirstBoolean.add(true);
                listpSecondBoolean.add(true);
            }
        }
        logger.info("******************************************************");
        logger.info("list pFirst boolean bien placé = " + listpFirstBoolean);
        logger.info("list pSecond boolean bien placé = " + listpSecondBoolean);

        // je compare une seconde fois en prenant en compte les bien placés
        for (int i = 0; i < pFirst.length(); i++) {
            for (int j = 0; j < pSecond.length(); j++) {
                if (!listpFirstBoolean.get(i) && !listpSecondBoolean.get(j) && pFirst.charAt(i) == pSecond.charAt(j)) {
                    present++;
                    // je change la valeur boolean si il trouve une comparaison ok
                    listpFirstBoolean.set(i, true);
                    listpSecondBoolean.set(j, true);
                }
            }
        }
        logger.info("list pFirst boolean present = " + listpFirstBoolean);
        logger.info("list pSecond boolean present = " + listpSecondBoolean);
        logger.info("******************************************************");

        goodplaceExport = goodplace;
        presentExport = present;
    }

    /**
     * For see if user is a winner
     *
     * @param pGoodPlaced Integer with goodPlaces in.
     */
    protected void seeUserWinner(int pGoodPlaced, int pPresent) {

        // j'affiche le resultat selon les précédentes valeurs
        if (pGoodPlaced == nbrDigit) {
            System.out.println("Félicitation tu as trouvé la combinaison !");
            System.out.println("");
            this.replayMaster();
        }
        if (pGoodPlaced == 0 && pPresent == 0) {
            System.out.println(" Aucun bien placé ou present.");
        } else {
            System.out.println(pGoodPlaced + " bien placé(s), " + pPresent + " present(s) ");
        }
    }

    /**
     * For have a goodPlaced input with no cheat
     *
     * @param pFirst  string for mastermind compare
     * @param pSecond String for mastermind compare
     * @return user input (good placed) whith no cheat
     */
    protected int goodPlacedNoCheat(String pFirst, String pSecond) {

        boolean responseIsGood;
        int userInput = 0;

        do {
            try {
                userInput = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("chiffres uniquement.");
                responseIsGood = false;
            }

            // je check la bonne reponse
            logger.info("verification bien placé(s)");
            this.compareTwoStringMast(pFirst, pSecond);

            if (userInput != getGoodplaceExport()) {
                System.out.println("Chiffre bien placé non valable, erreur humaine ou tentative de tricherie? ;-)");
                responseIsGood = false;
            } else {
                responseIsGood = true;
            }

            // je regarde si l'utilisateur a gagné
            if (userInput == nbrDigit) {
                System.out.println("l'ordinateur a trouvé ta combinaison, tu as perdu :-(");
                System.out.println("");
                logger.info("l'ordinateur a trouvé la combinaison");
                this.replayMaster();
            }

        } while (!responseIsGood);
        return userInput;
    }


    /**
     * For have a present input with no cheat
     *
     * @param pFirst  string for mastermind compare
     * @param pSecond string for mastermind compare
     * @return user (present) input whith no cheat
     */
    protected int presentNoCheat(String pFirst, String pSecond) {

        boolean responseIsGood;
        int userInput = 0;

        do {
            try {
                userInput = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Chiffre uniquement");
                responseIsGood = false;
            }

            // je check la bonne réponse
            logger.info("verification présent(s)");
            this.compareTwoStringMast(pFirst, pSecond);
            if (userInput != getPresentExport()) {
                System.out.println("Chiffre présent non valable, erreur humaine ou tentative de tricherie? ;-)");
                responseIsGood = false;
            } else {
                responseIsGood = true;
            }

        } while (!responseIsGood);
        return userInput;
    }

    /**
     * for evaluate code on String of 2 digit ( highter is close of solution )
     * decade = number of goodplaced,  only one Digit = number of present;
     * (ex: for 4 digit and 4 figure possible -> 0,1,2,3,4,10,11,12,13,20,21,22,30,40
     */
    protected String codeValue() {

        String codeValueString = "";

        // pour une reponse de comparaison (0 goodplace et 0 present)
        if (goodplaceExport == 0 && presentExport == 0) { codeValueString = "0";}

        // pour une reponse de comparaison uniquement avec des presents
        if (goodplaceExport == 0 && presentExport != 0) { codeValueString = String.valueOf(presentExport);}

        // pour une reponse de comparaison avec des bien placé(s) et pas de present
        if (goodplaceExport != 0 && presentExport == 0) { codeValueString = String.valueOf(goodplaceExport) + "0";}

        // pour une reponse de comparaison avec des bien placé(s) et des present(s)
        if (goodplaceExport != 0 && presentExport != 0) { codeValueString = String.valueOf(goodplaceExport) + String.valueOf(presentExport);}

        logger.info("valeur code = " + codeValueString );

        return codeValueString;

    }

    /**
     * for make all combination possible with codeValueParameter
     */
    protected void makeAllCombinationMastermind() {

        String combination = "";

        // je lance la methode pour trouver la valeur du code
        String codeValueStr = this.codeValue();

        //je recupere la list des nombre ok
        List<Integer> listNbrOk = this.createListNbrOk();

        // je fabrique une array list avec toutes les combinaisons
        List<String> combinationTotal = new ArrayList<>();

        // je fabrique les base 00,11,22,33
        int count = 0;

        for (int i = 0; i < nbrMaxOnDigit ; i++) { // -> pour le nombre max sur un digit
            combination = "";
            for (int j = 0; j < nbrDigit; j++) { // -> pour le nombre de digit
                combination = combination + listNbrOk.get(count);}
            count++;
            combinationTotal.add(combination);
        }

        // je fabrique les autres
        for (int i = 0; i < nbrMaxOnDigit ; i++) {
            combination = "";
            for (int j = 0; j < nbrDigit ; j++) {



            }

        }



        logger.info("liste combinaison totale = " + combinationTotal);




        }

    }






