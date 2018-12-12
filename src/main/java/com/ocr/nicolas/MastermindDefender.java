package com.ocr.nicolas;

public class MastermindDefender extends Mastermind {


    String compDefendMastKnuthStr; // computer combination (String)
    String userDefendMastStr; // user combination (String)
    //int goodplaced = 0; // nbr of goodplaced
    //int present = 0; // nbr of present

    protected int playDefenderModeMastermind () {

        //variable
        int nbrLoopMastDefend = 0;
        int replay = 3;

        // affichage de la demande
        System.out.println("Choisi une combinaison de " + nbrDigit + " chiffre(s) ( chaque chiffre doit être compris entre 0 et " + nbrMaxOnDigit + ")");

        // recuperation de la combinaison utilisateur
        userDefendMastStr = this.inputUserStringMast();

        //j'affiche la premiere combinaison computeur (celle conseillé par knuth a savoir par serie de 2 chiffres identique ex pour mon programme 0011)
        compDefendMastKnuthStr = "0011"; //todo attention au nombre de digit
        System.out.println(compDefendMastKnuthStr);

        //do {
            //je récupére les bien placé(s) ( en fesant attention a la triche) et je regarde si ordinateur gagne.
            System.out.println("Peux-tu donner le nombre de chiffre(s) bien placé(s):");
            this.goodPlacedNoCheat(userDefendMastStr, compDefendMastKnuthStr);

            //je récupére les presents (attention a la triche)
            System.out.println("Peux-tu donner le nombre de chiffre(s) présent(s):");
            this.presentNoCheat(userDefendMastStr, compDefendMastKnuthStr);

            //todo ce servir de ces resultats pour faire une autre proposition optimale (faire l'IA)
            //creation d'une methode pour mettre une valeur au combinaison possible (une echelle de valeur afin de ce diriger vers les bonnes combinaison)
            //todo creation d'une methode pour fabriquer toutes les combinaisons (avec ces valeurs??)
            this.makeAllCombinationMastermind();

            //todo creation d'une methode pour piocher la prochaine solution dans la tranche de valeur qui va bien

            nbrLoopMastDefend++;

        //} while (nbrLoopMastDefend < nbrOfTry);
        //System.out.println("L'ordinateur n'as plus d'essai possible, tu as gagné !");
        //System.out.println("");
        replay = this.replayMaster();
        return replay;
    }
}
