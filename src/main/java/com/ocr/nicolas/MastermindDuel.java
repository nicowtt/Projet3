package com.ocr.nicolas;

import java.util.List;

public class MastermindDuel extends Mastermind {

    String randCompChalMast; // challenger mode computer random (String)
    String userChalMastStr; // challenger mode user combination (String)

    String compDefendMastKnuthStr; // defender mode computer combination (String)
    String userDefendMastStr; // defender mode user combination (String)


    protected int playDuelModeMastermind() {

        //variables
        int loopForDuelMode = 1;
        int replay = 3;
        iswin = false;
        String firstCompareValue;
        List<String> afterFirstUserResponse;

        //annonce du nombre global d'essai possible
        System.out.println("il y aura " + nbrOfTry + " essai(s) possible pour trouver les combinaisons.");
        System.out.println("");

        //creation combinaison ordinateur
        randCompChalMast = this.computerNbrCombination(0, nbrMaxOnDigit); // (challenger)
        compDefendMastKnuthStr = randCompChalMast; // (defender)
        logger.info("Ordinateur combinaison = " + randCompChalMast);

        System.out.println("**** " + loopForDuelMode + "er essai  *****");
        logger.info("-------------------- Defender Mode 1 ----------------");

        //demande une combinaison utilisateur (**** mode melangé *****)
        System.out.println("Choisi une combinaison de " + nbrDigit + " chiffre(s) ( chaque chiffre doit être compris entre 0 et " + nbrMaxOnDigit + ")");

        // j'affiche le mode developpeur au besoin (challenger)
        if (developerMode.contains("true")) {
            System.out.println(randCompChalMast + " -> (combinaison ordinateur: mode developpeur) ");
        }

        // recuperation de la combinaison utilisateur
        userChalMastStr = this.inputUserStringMast(); // (challenger)
        userDefendMastStr = userChalMastStr; // (defender)

        //je compare avec l'ordinateur pour voir les presents en fonction des bien placés (challenger)
        this.compareTwoStringMast(userChalMastStr, randCompChalMast);

        // je check si l'utilisateur gagne au premier coup aussi (challenger)
        this.seeUserWinner(getGoodplaceExport(), getPresentExport());

        if (!iswin) {
            logger.info("combinaison aléatoire ordinateur = " + compDefendMastKnuthStr);
            logger.info("");
            logger.info("****** boucle " + loopForDuelMode + " **************************");

            //************** Defender2************************
            logger.info("-------------------- Defender Mode 2 ----------------");
            System.out.println("** au tour de l'ordinateur **");

            // premiere methodes pour avoir une combinaison optimale en utilisant l'algo de Knuth (et je  l'affiche) (defender)
            // je compare les deux combinaisons (user avec ordinateur) pour obtenir la valeur ( unité = present, dizaine = bien placé)
            firstCompareValue = this.compareTwoStringMast(userDefendMastStr, compDefendMastKnuthStr);
            logger.info("valeur comparaison (dizaine = nbr de bien placé, unité = nbr de present) = " + firstCompareValue);

            //je crée toutes les combinaisons possible
            List<String> listCombinationTotale = this.makeAllCombinationMastermind();

            //je fais une liste affiné avec la nouvelle liste des combinaisons + value
            afterFirstUserResponse = this.sortCombinationPossible(listCombinationTotale, compDefendMastKnuthStr, firstCompareValue);

            // je crée la combinaison optimale (knuth)
            compDefendMastKnuthStr = this.optimalCombination(afterFirstUserResponse);

            //je l'affiche
            System.out.println(compDefendMastKnuthStr);

            // je check si l'ordinateur gagne au 1er coups et si il lui reste des essais (defender)
            loopForDuelMode = this.checkIfComputerWin(userDefendMastStr, compDefendMastKnuthStr, loopForDuelMode);
            if (iswin) {
                System.out.println("");
                System.out.println("La combinaison mystère de l'ordinateur était : " + randCompChalMast);
            }

            // je commence la boucle
            do {
                // j'increment de compteur de boucle (defender)
                loopForDuelMode++;
                if (!iswin) {
                    logger.info("");
                    logger.info("***boucle " + loopForDuelMode + " *****");

                    // je lance la deuxiemes methodes pour avoir une combinaison optimale en utilisant l'algo de Knuth (mais je ne l'affiche pas encore !!) (defender)
                    compDefendMastKnuthStr = secondAndNextMethodCreateKnuthCombination(userDefendMastStr, compDefendMastKnuthStr);

                    System.out.println("*********************");
                    System.out.println("**** " + loopForDuelMode + "eme essai *****");
                    logger.info("-------------------- Challenger Mode 2 ----------------");

                    //demande une combinaison utilisateur (**** mode melangé *****)
                    System.out.println("Choisi une combinaison de " + nbrDigit + " chiffre(s) ( chaque chiffre doit être compris entre 0 et " + nbrMaxOnDigit + ")");
                    // j'affiche le mode developpeur au besoin (challenger)
                    if (developerMode.contains("true")) {
                        System.out.println(randCompChalMast + " -> (combinaison ordinateur: mode developpeur) ");
                    }
                    System.out.println(userChalMastStr + "(-> rappel de ta combinaison)");
                    //je compare avec l'ordinateur pour voir les presents en fonction des bien placés (rappel)
                    this.compareTwoStringMast(userChalMastStr, randCompChalMast);//(rappel)

                    // je check si l'utilisateur gagne au premier coup aussi (rappel)
                    this.seeUserWinner(getGoodplaceExport(), getPresentExport());//(rappel)

                    // Récupération de la 2eme combinaison utilisateur (challenger)
                    userChalMastStr = this.inputUserStringMast();

                    //je compare avec l'ordinateur pour voir les presents en fonction des bien placés (challenger)
                    this.compareTwoStringMast(userChalMastStr, randCompChalMast);

                    //je regarde si l'utilisateur est gagnant (challenger)
                    this.seeUserWinner(getGoodplaceExport(), getPresentExport());
                    if (!iswin) {
                        //************** Defender3************************
                        System.out.println("** au tour de l'ordinateur **");
                        logger.info("-------------------- Defender Mode 3 ----------------");

                        // je l'affiche (defender)
                        System.out.println(userDefendMastStr + "(-> rappel de ta combinaison)");
                        System.out.println(compDefendMastKnuthStr + " -> est la nouvelle proposition de l'ordinateur");

                        // je check si l'ordinateur gagne et si il lui reste des essais (defender)
                        loopForDuelMode = this.checkIfComputerWin(userDefendMastStr, compDefendMastKnuthStr, loopForDuelMode);
                        if (iswin) {
                            System.out.println("");
                            System.out.println("La combinaison mystère de l'ordinateur était : " + randCompChalMast);
                        }
                    }
                }
            } while (loopForDuelMode != nbrOfTry);
        }
        System.out.println("");
        replay = this.replayMaster();
        return replay;
    }
}
