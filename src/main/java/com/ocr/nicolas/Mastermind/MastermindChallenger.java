package com.ocr.nicolas.Mastermind;

import com.ocr.nicolas.Config;
import com.ocr.nicolas.PlayChallenger;
import com.ocr.nicolas.PlayGames;
import com.ocr.nicolas.Replay;

public class MastermindChallenger extends Mastermind implements PlayChallenger {

    String randCompChalMast; // random computer combination (String)
    String userChalMastStr; // user combination (String)

    public MastermindChallenger(Config config) {
        super(config);
    }

    public Replay playChallengerMode() {

        //variables
        Replay replayEnum;
        int nbrLoopMastChal = 0;
        iswin = false;

        //creation combinaison ordinateur
        randCompChalMast = this.computerNbrCombination(0, nbrMaxOnDigit);
        logger.info("Ordinateur combinaison = " + randCompChalMast);

        // affichage de la demande
        System.out.println("Choisi une combinaison de " + nbrDigit + " chiffre(s) (chaque chiffre doit être compris entre 0 et " + nbrMaxOnDigit + ")");

        do {
            logger.info("");
            logger.info("****** boucle " + nbrLoopMastChal + " ********");
            // affichage du nombre d'essai restant
            System.out.println((nbrOfTry - nbrLoopMastChal) + " essai(s) restant.");
            nbrLoopMastChal++;

            // Récupération de la 1er combinaison utilisateur
            userChalMastStr = this.inputUserStringMast();

            // j'affiche le mode developpeur au besoin
            if (developerMode.contains("true") || Config.isDEV()) { System.out.println(randCompChalMast + " -> (combinaison ordinateur: mode developpeur) ");}

            //je compare avec l'ordinateur pour voir les presents en fonction des bien placés
            this.compareTwoStringMast(userChalMastStr, randCompChalMast);

            //je regarde si l'utilisateur est gagnant
            this.seeUserWinner(goodplaceExport, presentExport);
            if (iswin) { nbrLoopMastChal = nbrOfTry;}

        }while (nbrLoopMastChal < nbrOfTry);
        if (!iswin) {
            System.out.println("Tu n'as plus d'essai possible, tu as perdu :-(");
            System.out.println("");
            System.out.println("-----> la combinaison de l'ordinateur était: " + randCompChalMast);
            System.out.println("");
        }
        replayEnum = PlayGames.replayEnum();
        return replayEnum;
    }
}

// ( * pour info * methode "playChallengerMode" total = 42 - 16 lignes (espace , texte et logger) ->  26 lignes
