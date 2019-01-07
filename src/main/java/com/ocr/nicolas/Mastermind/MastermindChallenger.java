package com.ocr.nicolas.Mastermind;

import com.ocr.nicolas.Config;
import com.ocr.nicolas.PlayChallenger;
import com.ocr.nicolas.PlayGames;

public class MastermindChallenger extends Mastermind implements PlayChallenger {

    String randCompChalMast; // random computer combination (String)
    String userChalMastStr; // user combination (String)

    public MastermindChallenger(Config config) {
        super(config);
    }

    public int playChallengerMode() {

        //variables
        int nbrLoopMastChal = 0;
        int replay = 3;
        iswin = false;

        //creation combinaison ordinateur
        randCompChalMast = PlayGames.computerNbrCombination(0, nbrMaxOnDigit);
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
            if (developerMode.contains("true")) { System.out.println(randCompChalMast + " -> (combinaison ordinateur: mode developpeur) ");}

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
        replay = PlayGames.replay();
        return replay;
    }
}
