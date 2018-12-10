package com.ocr.nicolas;

import java.util.*;

public class Mastermind extends Games {


    protected int nbrMaxOnDigit;

    public Mastermind(int nbrDigit, int nbrOfTry, String developerMode, int nbrMaxOnDigit) {
        super(nbrDigit, nbrOfTry, developerMode);
        this.nbrMaxOnDigit = nbrMaxOnDigit;
    }

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
        MastermindChallenger mastermindChallenger = new MastermindChallenger(nbrDigit, nbrOfTry, developerMode, nbrMaxOnDigit);

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
     * For force user input combination ok
     *
     * @return user input combination ok (string)
     */

    protected String inputUserStringMast() {

        boolean responseIsGood = true;
        int digitOk;
        String inputUserFinal;

        //creation d'une list avec les chiffres possible
        List<Integer> listNbrOk = new ArrayList<>();

        //je met les chiffres ok a l'interieur
        int count = nbrMaxOnDigit;

        for (int i = 0; i < (nbrMaxOnDigit + 1); i++) {
            listNbrOk.add(count);
            count--;
        }
        logger.info("liste chiffres ok = " + listNbrOk);

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
        int goodplace = 0;
        int present = 0;

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

        // j'affiche le resultat selon les résultats
        if (goodplace == nbrDigit) {
            System.out.println("Félicitation tu as trouvé la combinaison !");
        }
        if (goodplace == 0 && present == 0) {
            System.out.println(" Aucun bien placé ou present.");
        } else {
            System.out.println(goodplace + " bien placé(s), " + present + " present(s) ");
        }
    }

    }


