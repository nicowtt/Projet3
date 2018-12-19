package com.ocr.nicolas;

public class MastermindDefender extends Mastermind {

    String compDefendMastKnuthStr; // computer combination (String)
    String userDefendMastStr; // user combination (String)

    protected int playDefenderModeMastermind() {

        //variable
        int nbrLoopMastDefend = 1;
        int replay = 3;
        iswin = false;
        noMoreTry = false;

        // affichage de la demande
        System.out.println("Choisi une combinaison de " + nbrDigit + " chiffre(s) (chaque chiffre doit être compris entre 0 et " + nbrMaxOnDigit + ")");

        // recuperation de la combinaison utilisateur
        userDefendMastStr = this.inputUserStringMast();

        // je fais un nombre aléatoire ordinateur et je l'affiche
        compDefendMastKnuthStr = this.computerNbrCombination(0, nbrMaxOnDigit);
        System.out.println(compDefendMastKnuthStr);
        logger.info("combinaison aléatoire ordinateur = " + compDefendMastKnuthStr);

        // je check si l'ordinateur gagne au premier coup (ça peut arrivé ...) et si il lui reste des essais
        this.checkIfComputerWin(userDefendMastStr, compDefendMastKnuthStr, nbrLoopMastDefend);

        logger.info("");
        logger.info("****** boucle " + nbrLoopMastDefend + " **************************");

        if (!iswin) {
            // j'increment de compteur de boucle
            nbrLoopMastDefend++;

            // je lance la premiere methodes pour avoir une combinaison optimale en utilisant l'algo de Knuth
            compDefendMastKnuthStr = firstMethodCreateKnuthCombination(userDefendMastStr, compDefendMastKnuthStr);

            // je l'affiche
            System.out.println("********** " + nbrLoopMastDefend + "ème essais de l'ordinateur *********");
            System.out.println(userDefendMastStr + "(-> rappel de ta combinaison)");
            System.out.println(compDefendMastKnuthStr + " -> est la nouvelle proposition de l'ordinateur");

            // je check si l'ordinateur gagne au 2eme coups et si il lui reste des essais
            nbrLoopMastDefend = this.checkIfComputerWin(userDefendMastStr, compDefendMastKnuthStr, nbrLoopMastDefend);

            do {
                if (!iswin) {
                    logger.info("");
                    logger.info("***boucle " + nbrLoopMastDefend + " *****");

                    // je lance la deuxiemes methodes pour avoir une combinaison optimale en utilisant l'algo de Knuth
                    compDefendMastKnuthStr = secondAndNextMethodCreateKnuthCombination(userDefendMastStr, compDefendMastKnuthStr);

                    // je l'affiche
                    System.out.println("********** " + (nbrLoopMastDefend + 1) + "ème essais de l'ordinateur *********");
                    System.out.println(userDefendMastStr + "(-> rappel de ta combinaison)");
                    System.out.println(compDefendMastKnuthStr + " -> est la nouvelle proposition de l'ordinateur");

                    // j'incremente la boucle
                    nbrLoopMastDefend++;

                    // je check si l'ordinateur gagne et si il lui reste des essais
                    nbrLoopMastDefend = this.checkIfComputerWin(userDefendMastStr, compDefendMastKnuthStr, nbrLoopMastDefend);
                }
            } while (nbrLoopMastDefend != nbrOfTry);
        }
        if (noMoreTry) {System.out.println("l'ordinateur n'a plus d'essai possible, tu gagne !!");}
        System.out.println("");
        replay = this.replayMaster();
        return replay;
    }
}

// ( * pour info * methode "playDefenderModeMastermind" total = 64 - 30 lignes (espace , texte et logger) ->  34 lignes


