package com.ocr.nicolas;

public class MastermindDefender extends Mastermind {

    int nbrLoopMastDefend = 0; // nbr of loop
    String compDefendMastKnuthStr; // computer combination (String)
    String userDefendMastStr; // user combination (String)
    //int goodplaced = 0; // nbr of goodplaced
    //int present = 0; // nbr of present

    public MastermindDefender(int nbrDigit, int nbrOfTry, String developerMode, int nbrMaxOnDigit) {
        super(nbrDigit, nbrOfTry, developerMode, nbrMaxOnDigit);
    }

    protected void playDefenderModeMastermind () {
        //objet
        ReadPropertiesFile read = new ReadPropertiesFile(); // presence sinon bug lors du replay
        nbrMaxOnDigit = read.getNbrMaxOnDigit();

        // affichage de la demande
        System.out.println("Choisi une combinaison de " + nbrDigit + " chiffre(s) ( chaque chiffre doit être compris entre 0 et " + nbrMaxOnDigit + ")");

        // recuperation de la combinaison utilisateur
        userDefendMastStr = this.inputUserStringMast();

        //j'affiche la premiere combinaison computeur (celle conseillé par knuth a savoir par serie de 2 chiffres identique ex pour mon programme 0011)
        compDefendMastKnuthStr = "0011";
        System.out.println(compDefendMastKnuthStr);

        do {
            //je récupére les bien placé(s) ( en fesant attention a la triche) et je regarde si ordinateur gagne.
            System.out.println("Peux-tu donner le(s) chiffres bien placé(s):");
            this.goodPlacedNoCheat(userDefendMastStr, compDefendMastKnuthStr);

            //je récupére les presents (attention a la triche)
            System.out.println("Peux-tu donner le(s) chiffres présent(s):");
            this.presentNoCheat(userDefendMastStr, compDefendMastKnuthStr);

            //todo ce servir de ces resultat pour faire une autre proposition optimale (faire l'IA)

            nbrLoopMastDefend++;

        } while (nbrLoopMastDefend < nbrOfTry);
        System.out.println("L'ordinateur n'as plus d'essai possible, tu as gagné !");
        System.out.println("");
        this.replayMaster();
    }
}
