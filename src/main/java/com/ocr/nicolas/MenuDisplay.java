package com.ocr.nicolas;

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
        System.out.println("A quel jeux veux-tu jouer:");
        System.out.println("1-> Recherche +/-");
        System.out.println("2-> Mastermind");
        System.out.println("3-> quitter");
    }

    /**
     * Display and confirmation of games choice.
     */
    public int displayGamesMenuChoice() {

        boolean responseIsGood;
        //Combien de choix maximum pour limité l'entree utilisateur
        int nbrChoiceMax = 3;
        int gameChoice = forceProposedChoice(nbrChoiceMax);

        do {
            switch (gameChoice) {
                case 1:
                    System.out.println("Tu as choisi le jeux Recherche +/- ");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 2:
                    System.out.println("Tu as choisi le Mastermind");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 3:
                    System.out.println("arrêt des jeux");
                    System.exit(0);
                    responseIsGood = true;
                    break;
                default:
                    responseIsGood = false;
                    break;
            }
        } while (!responseIsGood);

        logger.info("Choix du jeux (1- Recherche+/- ; 2- Mastermind; 3 -arret) -> " + gameChoice);
        return gameChoice;
    }

    /**
     * Display Type of Games.
     */
    public void displayAskTypeOfGame() {

        System.out.println("");
        System.out.println("****** Choisi le mode de jeux: *******");
        System.out.println("1-Challenger"); //-> Trouve la combinaison mystère de l'ordinateur");
        System.out.println("2-Défenseur"); //-> L'ordinateur doit trouver ta combinaison");
        System.out.println("3-Duel");
    }

    /**
     * Display and confirmation of Type games choice.
     */
    public int displayGameTypeChoice() {

        boolean responseIsGood;

        // je demande a l'utilisateur de choisir
        int nbrChoiceMax = 3;
        int gameTypeChoice = forceProposedChoice(nbrChoiceMax);

        do {
            switch (gameTypeChoice) {
                case 1:
                    System.out.println("Tu as choisi le mode Challenger (tu doit trouver la combinaison)");
                    System.out.println("************************************");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 2:
                    System.out.println("Tu as choisi le mode Défenseur (l'ordinateur doit trouver ta combinaison)");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 3:
                    System.out.println("Tu as choisi le mode Duel");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                default:
                    responseIsGood = false;
                    break;
            }
        } while (!responseIsGood);
        logger.info("Mode de jeux (1- Challenger ; 2- défenseur ; 3- Duel) -> " + gameTypeChoice);
        return gameTypeChoice;
    }

    /**
     * display first choice of number on String
     *
     * @param nbr number of boxes (digit)
     */
    public void displayAskNumber(int nbr) {

        // demande et verification nombre de digit entrée utilisateur (re-demande si pas assez ou trop de nombre ou lettre(s))

        boolean responseIsGood;
        responseIsGood = false;
        int nbrDigit = nbr;
        int counterTotalLetter = 0;
        int tooManyNbr = 0;

        System.out.println("Choisi une combinaison de " + nbr + " chiffre(s):");

        do {
            try {
                String userChoiceString = sc.next();

                // Verification si y a trop de caractères en entrée
                if (userChoiceString.length() > nbrDigit) {
                    tooManyNbr++;
                }

                if (tooManyNbr != 1) {
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
                } else {
                    System.out.println("oupss ! trop de caractères, choisi une suite de " + nbrDigit + " chiffres: ");
                    logger.info("- INFO - trop de caractère entrée par l'utilisateur");
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Tu dois rentrer " + nbr + " chiffres:");
                logger.info("- INFO - pas assez de caractère entrée par l'utilisateur");
            } catch (NumberFormatException e) {
                System.out.println("Lettre(s) non acceptée(s), choisi une suite de " + nbrDigit + " chiffres: ");
                logger.info("- INFO - Lettre(s) entrée par l'utilisateur");
            }
            tooManyNbr = 0;

        } while (responseIsGood != true);
    }

    /**
     * display end of game menu
     */
    public void displayAskIfReplay() {
        logger.info("");
        System.out.println("****** Veux-tu rejouer ? *******");
        System.out.println(" 1 -> oui je veux rejouer au meme jeux");
        System.out.println(" 2 -> retour à l'ecran du choix des jeux");
        System.out.println(" 3 -> quitter");
    }

    /**
     * for display replay choice
     *
     * @return replay choice (int)
     */
    public Replay displayReplayChoice() {

        //variables
        boolean responseIsGood;
        int nbrChoiceMax = 3;
        int replayChoice = forceProposedChoice(nbrChoiceMax);
        Replay replayEnum = Replay.EXIT;

        do {
            switch (replayChoice) {
                case 1:
                    System.out.println("Tu as choisi de rejouer au même jeux ");
                    System.out.println("");
                    responseIsGood = true;
                    replayEnum = Replay.REPLAY;
                    break;
                case 2:
                    System.out.println("Tu as choisi le retour a l'ecran principal du choix des jeux");
                    System.out.println("");
                    responseIsGood = true;
                    replayEnum = Replay.GAMESCHOICE;
                    break;
                case 3:
                    System.out.println("tu as choisi de quitter");
                    System.exit(0);
                    System.out.println("");
                    responseIsGood = true;
                    replayEnum = Replay.EXIT;
                    break;
                default:
                    responseIsGood = false;
                    break;
            }
        } while (!responseIsGood);
        logger.info("Choix du replay-> " + replayChoice + " (1- Recherche+/- ; 2- choix d'un autre jeux; 3- quitter)");
        return replayEnum;
    }

    /**
     * for force user to input on choice and int only
     *
     * @param nbrMax choice max
     * @return choice
     */
    private int forceProposedChoice(int nbrMax) {

        // varibles
        boolean responseIsGood;
        int forcedChoiceInt = 0;
        String forcedChoiceStr = "";
        int count = 0;


        do {
            count = 0;
            try {
                System.out.println("-> Choisi parmi les choix proposés:");
                forcedChoiceStr = sc.next();
                for (int i = 0; i < forcedChoiceStr.length(); i++) { // si trop de caractères
                    char letter = forcedChoiceStr.charAt(i);
                    String letterStr = String.valueOf(letter);
                    Integer letterInt = Integer.valueOf(letterStr);
                    forcedChoiceInt = letterInt;
                    count++;
                }
                if (count > 1) {
                    System.out.println("Trop de caractères");
                    responseIsGood = false;
                } else if (forcedChoiceInt <= 0 || forcedChoiceInt > nbrMax) { // si pas dans les possibles
                    forcedChoiceInt = 0;
                    responseIsGood = false;
                } else {
                    responseIsGood = true;
                }

            } catch (NumberFormatException e) { // si il y a une lettre
                System.out.println("erreur chiffre uniquement");
                responseIsGood = false;
            }
        } while (!responseIsGood);

        return forcedChoiceInt;

    }

    /**
     * For display to user to input values
     */
    public void displayForValueToUser() {

        System.out.println("peux-tu donner les valeurs (+,- ou =) pour chaque chiffre par rapport au tien:");


    }
}





