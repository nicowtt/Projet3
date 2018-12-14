package com.ocr.nicolas;

import java.util.List;

public class MastermindDefender extends Mastermind {


    String compDefendMastKnuthStr; // computer combination (String)
    String userDefendMastStr; // user combination (String)

    protected int playDefenderModeMastermind() {

        //variable
        int nbrLoopMastDefend = 1;
        int replay = 3;
        iswin = false;
        String compValue = "";
        String compCombination = "";
        String compareValue = "";

        //variable 2
        String optimalCombination = "";
        List<String> combinationRemaining;
        String valueInLoop = "";
        List<String> afterUserResponse;
        List<String> afterFirstUserResponse;
        String firstCompareValue = "";

        // affichage de la demande
        System.out.println("Choisi une combinaison de " + nbrDigit + " chiffre(s) ( chaque chiffre doit être compris entre 0 et " + nbrMaxOnDigit + ")");

        // recuperation de la combinaison utilisateur
        userDefendMastStr = this.inputUserStringMast();

        // je fais un nombre aléatoire ordinateur et je l'affiche
        compDefendMastKnuthStr = this.computerNbrCombination(0, nbrMaxOnDigit);
        System.out.println(compDefendMastKnuthStr);
        logger.info("combinaison aléatoire ordinateur = " + compDefendMastKnuthStr);

        // je check si l'ordinateur gagne au premier coup (ça peut arrivé ...)
        if (userDefendMastStr.equals(compDefendMastKnuthStr)) {
            System.out.println("l'ordinateur a gagné au 1er coups !!");
            logger.info("l'ordinateur a gagné 2 coups");
            nbrLoopMastDefend = nbrOfTry;
            iswin = true;
        }
        // Si 1 seul essai dans fichier.config
        if (nbrOfTry == 1 ) {
            System.out.println(" l'ordinateur n'a plus d'essai possible, tu as gagné !");
            iswin = true;
        }
        logger.info("");
        logger.info("****** boucle " + nbrLoopMastDefend + " **************************");
        nbrLoopMastDefend++;

        if (!iswin) {
            //je crée toutes les combinaisons possible
            List<String> listCombinationTotale = this.makeAllCombinationMastermind();

            // je compare les deux combinaisons (user avec ordinateur) -> value
            firstCompareValue = this.compareTwoStringMast(userDefendMastStr, compDefendMastKnuthStr);
            logger.info("valeur comparaison = " + firstCompareValue);

            //je récupére les bien placé(s) ( en fesant attention a la triche) et je regarde si ordinateur gagne.
            System.out.println("Peux-tu donner le nombre de chiffre(s) bien placé(s):");
            this.goodPlacedNoCheat(userDefendMastStr, compDefendMastKnuthStr);

            //je récupére les presents (attention a la triche)
            System.out.println("Peux-tu donner le nombre de chiffre(s) présent(s):");
            this.presentNoCheat(userDefendMastStr, compDefendMastKnuthStr);

            //je fais une liste affiné avec la nouvelle liste des combinaisons + value
            afterFirstUserResponse = this.sortCombinationPossible(listCombinationTotale, compDefendMastKnuthStr, firstCompareValue);

            // je crée la combinaison optimale (knuth)
            compDefendMastKnuthStr = this.optimalCombination(afterFirstUserResponse);

            // je l'affiche
            System.out.println("********** " + nbrLoopMastDefend + "ème essais de l'ordinateur *********");
            System.out.println(userDefendMastStr + "(-> rappel de ta combinaison)");
            System.out.println(compDefendMastKnuthStr);

            // je check si l'ordinateur gagne au 2eme coups
            if (userDefendMastStr.equals(compDefendMastKnuthStr)) {
                System.out.println("l'ordinateur a gagné au 2eme coups !!");
                logger.info("l'ordinateur a gagné 2 coups");
                nbrLoopMastDefend = nbrOfTry;
                iswin = true;
            }
            if (nbrOfTry == 2 ) {
                System.out.println(" l'ordinateur n'a plus d'essai possible, tu as gagné !");
                iswin = true;
            }
            do {
                if (!iswin) {
                    logger.info("");
                    logger.info("***boucle " + nbrLoopMastDefend + " *****");
                    //je récupére les bien placé(s) ( en fesant attention a la triche) et je regarde si ordinateur gagne.
                    System.out.println("Peux-tu donner le nombre de chiffre(s) bien placé(s):");
                    this.goodPlacedNoCheat(userDefendMastStr, compDefendMastKnuthStr);

                    //je récupére les presents (attention a la triche)
                    System.out.println("Peux-tu donner le nombre de chiffre(s) présent(s):");
                    this.presentNoCheat(userDefendMastStr, compDefendMastKnuthStr);

                    //je recupere la valeur et je m'en sert pour faire une nouvelle liste de combinaison
                    valueInLoop = this.codeValue(compDefendMastKnuthStr);
                    logger.info("valeur de comparaison dans la boucle = " + valueInLoop);

                    // je recupere la liste des combinaison possible
                    combinationRemaining = getListCombinationRemainingexport();
                    //logger.info("liste des combinaison restante dans defender = " + combinationRemaining);

                    //je refais une liste avec la nouvelle liste des combinaisons + value
                    afterUserResponse = this.sortCombinationPossible(combinationRemaining, compDefendMastKnuthStr, valueInLoop);
                    logger.info("liste combinaison restante dans cette boucle = " + afterUserResponse);

                    // je crée la combinaison optimale (knuth)
                    compDefendMastKnuthStr = this.optimalCombination(afterUserResponse);
                    // je l'affiche
                    System.out.println("********** " + (nbrLoopMastDefend + 1) + "ème essais de l'ordinateur *********");
                    System.out.println(userDefendMastStr + "(-> rappel de ta combinaison)");
                    System.out.println(compDefendMastKnuthStr + " -> est la nouvelle proposition de l'ordinateur");

                    // je check si l'ordinateur gagne
                    if (userDefendMastStr.equals(compDefendMastKnuthStr)) {
                        System.out.println("l'ordinateur a gagné en " + (nbrLoopMastDefend + 1) + " coups");
                        logger.info("l'ordinateur a gagné en " + (nbrLoopMastDefend + 1) + " coups");
                        nbrLoopMastDefend = nbrOfTry;
                        iswin = true;
                    }
                    // j'incremente la boucle
                    nbrLoopMastDefend++;
                }
            } while (nbrLoopMastDefend < nbrOfTry);
            if (!iswin) {
                System.out.println("L'ordinateur n'as plus d'essai possible, tu as gagné !");
                System.out.println("");
            }
        }
            System.out.println("");
            replay = this.replayMaster();
            return replay;
        }
    }


