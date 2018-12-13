package com.ocr.nicolas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MastermindDefender extends Mastermind {


    String compDefendMastKnuthStr; // computer combination (String)
    String userDefendMastStr; // user combination (String)
    //int goodplaced = 0; // nbr of goodplaced
    //int present = 0; // nbr of present

    protected int playDefenderModeMastermind () {

        //variable
        int nbrLoopMastDefend = 0;
        int replay = 3;
        String compValue = "";
        String compCombination = "";
        String compareValue = "";

        //variable 2
        String optimalCombination = "";
        List<String> combinationRemaining;
        String valueInLoop = "";
        List<String> afterUserResponse;


        // affichage de la demande
        System.out.println("Choisi une combinaison de " + nbrDigit + " chiffre(s) ( chaque chiffre doit être compris entre 0 et " + nbrMaxOnDigit + ")");

        // recuperation de la combinaison utilisateur
        userDefendMastStr = this.inputUserStringMast();

        // todo j'affiche la premiere combinaison computeur (celle conseillé par knuth a savoir par serie de 2 chiffres identique ex pour mon programme 0011)
        //compDefendMastKnuthStr = "102"; // attention au nombre de digit
        //System.out.println(compDefendMastKnuthStr);

        //je crée toutes les combinaisons possible
        List<String> listCombinationTotale = this.makeAllCombinationMastermind();
        // je crée la combinaison optimale (knuth)
        compDefendMastKnuthStr = this.optimalCombination(listCombinationTotale);
        // je l'affiche
        System.out.println(compDefendMastKnuthStr);


        //do {
            //je récupére les bien placé(s) ( en fesant attention a la triche) et je regarde si ordinateur gagne.
            System.out.println("Peux-tu donner le nombre de chiffre(s) bien placé(s):");
            this.goodPlacedNoCheat(userDefendMastStr, compDefendMastKnuthStr);

            //je récupére les presents (attention a la triche)
            System.out.println("Peux-tu donner le nombre de chiffre(s) présent(s):");
            this.presentNoCheat(userDefendMastStr, compDefendMastKnuthStr);

            //todo ce servir de ces resultats pour faire une autre proposition optimale (faire l'IA - in progress)
            //je recupere la valeur et je m'en sert pour faire une nouvelle liste de combinaison
            valueInLoop = this.codeValue(compDefendMastKnuthStr);
            logger.info("valeur de comparaison dans la boucle = " + valueInLoop);

            // je recupere la liste des combinaison possible
            combinationRemaining = getListCombinationRemainingexport();
            logger.info("liste des combinaison restante dans defender = " + combinationRemaining);

            //je refais une liste avec la nouvelle liste des combinaisons + value
            afterUserResponse = this.sortCombinationPossible(combinationRemaining,userDefendMastStr, valueInLoop);
            logger.info("liste de combinaison aprés le trie dans la boucle = " + afterUserResponse);

            //todo je me sert des info precedente pour trouver une nouvelle valeur optimizé
            //compDefendMastKnuthStr = this.optimalCombination(combinationRemaining);

            // je l'affiche
            //System.out.println(compDefendMastKnuthStr);


            nbrLoopMastDefend++;

        //} while (nbrLoopMastDefend < nbrOfTry);
        //System.out.println("L'ordinateur n'as plus d'essai possible, tu as gagné !");
        //System.out.println("");
        //replay = this.replayMaster();
        return replay;
    }
}
