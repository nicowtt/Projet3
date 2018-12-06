package com.ocr.nicolas;


import java.util.Map;
import java.util.Scanner;

public class MastermindChallenger extends Mastermind {

    int nbrLoopMastChal = nbrOfTry; // loop while number of try not equal to zero
    String randCompChalMast; // random computer combination (String)
    String userChalMastStr; // user combination (String)

    Scanner sc = new Scanner(System.in);



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

            // j'affiche le mode developpeur au besoin
            if (developerMode.contains("true")) { System.out.println("( combinaison ordi = " +  randCompChalMast + ")");}

            //je compare avec l'ordinateur
            Map<String, Integer> hashMapWithCompareInfo = this.compareTwoStringMastToHashMap(userChalMastStr, randCompChalMast);
            // je traduit cette comparaison
            Map<String, Integer> hashMapReelInfo = this.displayInformationOfCompare(hashMapWithCompareInfo,userChalMastStr);
            // j'affiche le resulta
            this.displayInfoForUser(hashMapReelInfo);


            //todo while (tous les chiffres bien placés < nombre d'essai)
            //todo bug quand on rejoue au mastermin challenger

        //}while (nbrLoopMastChal != 0);
        this.replayMaster();

    }




}
