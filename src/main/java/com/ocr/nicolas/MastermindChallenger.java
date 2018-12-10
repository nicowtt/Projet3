package com.ocr.nicolas;

public class MastermindChallenger extends Mastermind {

    int nbrLoopMastChal = 0; // nbr of loop
    String randCompChalMast; // random computer combination (String)
    String userChalMastStr; // user combination (String)


    public MastermindChallenger(int nbrDigit, int nbrOfTry, String developerMode, int nbrMaxOnDigit) {
        super(nbrDigit, nbrOfTry, developerMode, nbrMaxOnDigit);
    }

    protected void playChallengerModeMastermind () {

        //objet
        ReadPropertiesFile read = new ReadPropertiesFile(); // presence sinon bug lors du replay
        nbrMaxOnDigit = read.getNbrMaxOnDigit();

        //creation combinaison ordinateur
        randCompChalMast = this.computerNbrCombination(0, nbrMaxOnDigit);
        logger.info("Ordinateur combinaison = " + randCompChalMast);

        // affichage de la demande
        System.out.println("Choisi une combinaison de " + nbrDigit + " chiffre(s) ( chaque chiffre doit être compris entre 0 et " + nbrMaxOnDigit + ")");

        do {
            logger.info("");
            logger.info("****** boucle " + nbrLoopMastChal + " ********");
            // affichage du nombre d'essai restant
            System.out.println("tu as " + (nbrOfTry - nbrLoopMastChal) + " essai(s) pour trouver la combinaison de l'ordinateur.");
            nbrLoopMastChal++;

            // Récupération de la 1er combinaison utilisateur
            userChalMastStr = this.inputUserStringMast();

            // j'affiche le mode developpeur au besoin
            if (developerMode.contains("true")) { System.out.println(randCompChalMast + " -> (combinaison ordinateur: mode developpeur) ");}

            //je compare avec l'ordinateur pour voir les presents en fonction des bien placés
            this.compareTwoStringMast(userChalMastStr, randCompChalMast);

            //je regarde si l'utilisateur est gagnant
            this.seeUserWinner(getGoodplaceExport(), getPresentExport());

        }while (nbrLoopMastChal < nbrOfTry);
        System.out.println("Tu n'as plus d'essai possible, tu as perdu :-(");
        System.out.println("");
        this.replayMaster();
    }
}
