package com.ocr.nicolas.Mastermind;

import com.ocr.nicolas.Config;
import com.ocr.nicolas.PlayDuel;
import com.ocr.nicolas.PlayGames;
import com.ocr.nicolas.Replay;

public class MastermindDuel extends Mastermind implements PlayDuel {

    String randCompChalMast; // challenger mode computer random (String)
    String userChalMastStr; // challenger mode user combination (String)

    String compDefendMastKnuthStr; // defender mode computer combination (String)
    String userDefendMastStr; // defender mode user combination (String)

    public MastermindDuel(Config config) {
        super(config);
    }

    public Replay playDuelMode() {

        //variables
        Replay replayEnum;
        int loopForDuelMode = 1;
        iswin = false;
        noMoreTry = false;

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
        if (developerMode.contains("true")) { System.out.println(randCompChalMast + " -> (combinaison ordinateur: mode developpeur) ");}

        // recuperation de la combinaison utilisateur
        userChalMastStr = this.inputUserStringMast(); // (challenger)
        userDefendMastStr = userChalMastStr; // (defender)

        //je compare avec l'ordinateur pour voir les presents en fonction des bien placés (challenger)
        this.compareTwoStringMast(userChalMastStr, randCompChalMast);

        // je check si l'utilisateur gagne au premier coup aussi (challenger)
        this.seeUserWinner(goodplaceExport, presentExport);

        if (!iswin) {
            logger.info("combinaison aléatoire ordinateur = " + compDefendMastKnuthStr);
            logger.info("");
            logger.info("****** boucle " + loopForDuelMode + " **************************");

            //************** Defender2************************
            logger.info("-------------------- Defender Mode 2 ----------------");
            System.out.println("** au tour de l'ordinateur **");

            // premiere methodes pour avoir une combinaison optimale en utilisant l'algo de Knuth (et je  l'affiche) (defender)
            compDefendMastKnuthStr = firstMethodCreateKnuthCombinationDuelMode(userDefendMastStr, compDefendMastKnuthStr);

            // je check si l'ordinateur gagne au 1er coups et si il lui reste des essais (defender)
            loopForDuelMode = this.checkIfComputerWin(userDefendMastStr, compDefendMastKnuthStr, loopForDuelMode);

            // je commence la boucle
            do {
                if (!iswin) {
                    // j'increment de compteur de boucle (defender)
                    loopForDuelMode++;
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
                    this.seeUserWinner(goodplaceExport, presentExport);//(rappel)

                    // Récupération de la 2eme combinaison utilisateur (challenger)
                    userChalMastStr = this.inputUserStringMast();

                    //je compare avec l'ordinateur pour voir les presents en fonction des bien placés (challenger)
                    this.compareTwoStringMast(userChalMastStr, randCompChalMast);

                    //je regarde si l'utilisateur est gagnant (challenger)
                    this.seeUserWinner(goodplaceExport, presentExport);
                    if (!iswin) {
                        //************** Defender3************************
                        System.out.println("** au tour de l'ordinateur **");
                        logger.info("-------------------- Defender Mode 3 ----------------");

                        // je l'affiche (defender)
                        System.out.println(userDefendMastStr + "(-> rappel de ta combinaison)");
                        System.out.println(compDefendMastKnuthStr + " -> est la nouvelle proposition de l'ordinateur");

                        // je check si l'ordinateur gagne et si il lui reste des essais (defender)
                        loopForDuelMode = this.checkIfComputerWin(userDefendMastStr, compDefendMastKnuthStr, loopForDuelMode);
                    }
                }
            } while (loopForDuelMode != nbrOfTry);
        }
        if (noMoreTry) {
            System.out.println("");
            System.out.println("Il ne reste plus d'essai possible, personne ne gagne :-(");
        }
        if (iswin) {
            System.out.println("La combinaison mystère de l'ordinateur était : " + randCompChalMast);
        }
        System.out.println("");
        replayEnum = PlayGames.replayEnum();
        return replayEnum;
    }
}

// ( * pour info * methode "PlayDuelMode" total = 112 - 55 lignes (espace , texte et logger) ->  57 lignes
