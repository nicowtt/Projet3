package com.ocr.nicolas;

import com.ocr.nicolas.utils.Utils;

import java.util.Map;

public class SearchNumberDuel extends SearchNumber{

    private int loopForDuelMode = 1; //nbr of loop

    // challenger
    private String randCompDuel; //String Random Computer -> challenger
    private String nbrUserDuelChallenger; // string user -> challenger (change with loop)
    private String afterCompareDuel; //value after compare -> challenger

    // defender
    private String nbrUserDuelDefender = ""; //String user input ->  defender
    private String valueUserInString = ""; //String values input (+-=+) pour le mode defender
    private String compDuelFirstString; // 1er String comp -> "5555"





    public SearchNumberDuel(int nbrDigit, int nbrOfTry, String developerMode) {
        super(nbrDigit, nbrOfTry, developerMode);
    }

    public void playDuelMode() {

        //objet
        MenuDisplay display = new MenuDisplay();

        //variable
        int inverseLoop = nbrOfTry;

        //annonce du nombre global d'essai possible
        System.out.println("il y aura " + nbrOfTry + " essai(s) possible pour trouver les combinaisons.");
        System.out.println("");

        // random computeur
        randCompDuel = this.computerNbrCombination(0, 9);
        logger.info("--------> aleatoire String computeur = " + randCompDuel);

        //************** Defender1************************
        System.out.println("**** 1er essai mode defender (l'ordinateur doit trouver ta combinaison) *****");

        //demande une combinaison utilisateur (mode Defender)
        display.displayAskNumber(nbrDigit);
        nbrUserDuelDefender = display.getUserChoiceStringExport(); //-> variable string utilisateur = nbrUserDefender
        logger.info("nombre entré par l'utilisateur = " + nbrUserDuelDefender);

        //defender 1: creation d'une hashMap base avec les limite Max, Min et digitok incrementé par digit.
        Map<String, Integer> completeHashMapBase = Utils.createHashMapBase(nbrDigit);
        logger.info(" hashMap Base dans la class Main = " + completeHashMapBase);

        //defender 1: premier jet computeur  uniquement des "5" et affichage sur la console
        compDuelFirstString = this.fiveOnlyDigit();
        logger.info("premier jet aleatoire computeur = " + compDuelFirstString);
        System.out.println(compDuelFirstString);

        // j'affiche la demande de valeur
        display.displayForValueToUser();

        // je check si erreur ou tricherie
        valueUserInString = this.inputValuesUserAndCheckIfCheat(nbrUserDuelDefender, compDuelFirstString);

        // je renseigne la hashmap
        Map<String, Integer> hashmapRefined = this.infoDigitForRefinedToHahMap(completeHashMapBase, compDuelFirstString, valueUserInString);
        logger.info("nouvelle hasmap refined = " + hashmapRefined);

        // check si gagnant au premier coup
        this.testIfComputerWinDefenderMode(loopForDuelMode);
        logger.info("ordi gagnant ? = " + isWin);

        System.out.println("L'ordinateur n'a pas trouver ta combinaison au 1er essai");
        System.out.println("");

        //*********** Challenger1******************
        System.out.println("**** 1er essai mode Challenger (Tu dois trouver la combinaison de l'ordinateur) *****");
        System.out.println(" Voici les valeurs pour trouver la combinaison de l'ordinateur:");

        // je verifie et j'affiche si le Mode developper a été demandé
        if (developerMode.contains("true")) {System.out.println("(" + randCompDuel + ") = chiffre ordinateur (mode developpeur)");}
        else {System.out.println("");}

        //challenger 1: Je lance la comparaison et j'affiche le resultat.
        String afterCompareChallenger = this.CompareTwoString(randCompDuel, nbrUserDuelDefender);
        System.out.println(afterCompareChallenger);
        System.out.println(nbrUserDuelDefender + " (rappel de ta combinaison)");

        // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserDuel
        System.out.println("");
        display.displayAskNumber(nbrDigit);
        nbrUserDuelChallenger = display.getUserChoiceStringExport();
        logger.info("nombre entré par l'utilisateur = " + nbrUserDuelChallenger);

        // verification si gagnant challenger
        this.testIfUserWinChallengerMode(loopForDuelMode, randCompDuel,nbrUserDuelChallenger, inverseLoop );

        //************** Defender2************************
        System.out.println("**** 2eme essai mode defender (l'ordinateur doit trouver ta combinaison) *****");

        //defender 2: lancement des nouveaux DichotomousDigits computeur avec les infos de la hashmapRefined
        String compDefenderRefined = this.hasmapToDicotomousString(hashmapRefined, compDuelFirstString,valueUserInString,nbrUserDuelDefender);
        logger.info(" nouveaux digit avec la methode dichotomique = " + compDefenderRefined);

        // j'affiche le nouvel essai computeur
        System.out.println("");
        System.out.println(compDefenderRefined);

        // je check si gagnant
        loopForDuelMode++;
        this.testIfComputerWinDefenderMode(loopForDuelMode);
        System.out.println("L'ordinateur n'a pas trouver ta combinaison au 2eme essai");
        System.out.println("");

        //do {
            logger.info("************* Debut de la boucle" + loopForDuelMode + "********* ");

            // j'affiche la demande de valeur
            display.displayForValueToUser();
            System.out.println(nbrUserDuelDefender + " (rappel de ta combinaison)");

            // je check si erreur ou tricherie
            valueUserInString = this.inputValuesUserAndCheckIfCheat(nbrUserDuelDefender, compDefenderRefined);

            //*********** Challenger2******************
            System.out.println("**** 2eme essai mode Challenger (Tu dois trouver la combinaison de l'ordinateur) *****");
            System.out.println("");
            System.out.println(" Voici les valeurs pour trouver la combinaison de l'ordinateur:");

            // je verifie et j'affiche si le Mode developper a été demandé
            if (developerMode.contains("true")) {System.out.println("(" + randCompDuel + ") = chiffre ordinateur (mode developpeur)");}
            else {System.out.println("");}

            //challenger 2 et suivant: Je lance la comparaison et j'affiche le resultat.
            afterCompareDuel = this.CompareTwoString(randCompDuel, nbrUserDuelChallenger);
            System.out.println(afterCompareChallenger);
            System.out.println(nbrUserDuelDefender + " (rappel de ta combinaison)");

            // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserDuel
            System.out.println("");
            display.displayAskNumber(nbrDigit);
            nbrUserDuelChallenger = display.getUserChoiceStringExport();
            logger.info("nombre entré par l'utilisateur = " + nbrUserDuelChallenger);

            // verification si gagnant challenger
            this.testIfUserWinChallengerMode(loopForDuelMode, randCompDuel,nbrUserDuelChallenger, inverseLoop );
            inverseLoop -= 1;

            //todo defender 3 et suivant: je fais des nouveaux chiffres computer avec les  nouvelles valeurs
            //todo defender 3 et suivant:  j'affiche le nouvel essai computeur
            //todo verification si gagnant
            //todo while
        //}


        //replay
        this.replay();
    }
}
