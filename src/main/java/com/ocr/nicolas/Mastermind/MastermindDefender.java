package com.ocr.nicolas.Mastermind;

import com.ocr.nicolas.Config;
import com.ocr.nicolas.PlayDefender;
import com.ocr.nicolas.PlayGames;
import com.ocr.nicolas.Replay;

public class MastermindDefender extends Mastermind implements PlayDefender {

    String compDefendMastKnuthStr; // computer combination (String)
    String userDefendMastStr; // user combination (String)

    public MastermindDefender(Config config) {
        super(config);
    }

    public Replay playDefenderMode() {

        //variable
        Replay replayEnum;
        int nbrLoopMastDefend = 1;
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
        replayEnum = PlayGames.replayEnum();
        return replayEnum;
    }
}

// ( * pour info * methode "playDefenderMode" total = 65 - 31 ligne (espace , texte et logger) -> 34 lignes


