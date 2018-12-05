package com.ocr.nicolas;



public class MastermindChallenger extends Mastermind {

    int nbrLoopMastChal = nbrOfTry; // loop while number of try not equal to zero
    String randCompChalMast; // random computer combination (String)
    String userChalMastStr; // user combination (String)



    public MastermindChallenger(int nbrDigit, int nbrOfTry, String developerMode, int nbrMaxOnDigit) {
        super(nbrDigit, nbrOfTry, developerMode, nbrMaxOnDigit);
    }


    protected void playChallengerModeMastermind () {

        //objet
        MenuDisplay display = new MenuDisplay();
        int userChalMast;

        //creation combinaison ordinateur
        randCompChalMast = this.computerNbrCombination(0, nbrMaxOnDigit);
        logger.info("Ordinateur combinaison = " + randCompChalMast);

        //Demande à l'utilisateur: 1/ le nombre d'essai possible et 2/ le chiffre max dans un digit (0 a 4 pour commencer)
        System.out.println(" tu as " + nbrOfTry + " essai pour trouver la combinaison.");


        //do {
            nbrLoopMastChal--;

            // affichage de la demande
            System.out.println(" Choisi une combinaison de " + nbrDigit + " chiffre (entre 0 et " + nbrMaxOnDigit + ")");

            // Récupéré la 1er combinaison utilisateur
            userChalMastStr = this.inputUserStringMast();

            //todo comparé avec l'ordinateur
            //todo dire si : 1 present, 1 bien placé (ou 2 present, ou 2 bien placé) et savoir si l'utilisateur gagne
            //todo while (tous les chiffres bien placés < nombre d'essai)

        //}while (nbrLoopMastChal != 0);
        this.replay();

    }




}
