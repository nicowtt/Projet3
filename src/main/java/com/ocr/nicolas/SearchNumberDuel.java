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
        System.out.println("*****************************************************************************");
        System.out.println("**** " + loopForDuelMode + "er essai mode defender (l'ordinateur doit trouver ta combinaison) *****");
        logger.info("-------------------- Defender Mode 1 ----------------");

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

        // je check si erreur ou tricherie et si ordi gagne
        valueUserInString = this.inputValuesUserAndCheckIfCheat(nbrUserDuelDefender, compDuelFirstString, loopForDuelMode);

        // je renseigne la hashmap
        Map<String, Integer> hashmapRefined = this.infoDigitForRefinedToHahMap(completeHashMapBase, compDuelFirstString, valueUserInString);
        logger.info("nouvelle hasmap refined = " + hashmapRefined);

        System.out.println("--> L'ordinateur n'a pas trouver ta combinaison au 1er essai.");
        System.out.println("");

        //*********** Challenger1******************
        inverseLoop -= 1;
        System.out.println("*************************************************************************************");
        System.out.println("**** " + loopForDuelMode + "er essai mode Challenger (Tu dois trouver la combinaison de l'ordinateur) *****");
        logger.info("-------------------- Challenger Mode 1 ----------------");
        System.out.println(" Voici les valeurs pour trouver la combinaison de l'ordinateur:");

        // je verifie et j'affiche si le Mode developper a été demandé
        if (developerMode.contains("true")) {System.out.println("(" + randCompDuel + ") = chiffre ordinateur (mode developpeur)");}

        //challenger 1: Je lance la comparaison et j'affiche le resultat.
        String afterCompareChallenger = this.CompareTwoString(randCompDuel, nbrUserDuelDefender);
        System.out.println(afterCompareChallenger);

        // si l'utilisateur trouve la combinaison ordinateur du premier coup (ça peut arriver avec un digit par ex)
        if (nbrUserDuelDefender.contains(randCompDuel)) {
            System.out.println("Félicitation tu as trouvé la combinaison de l'ordinateur du premier coup !!");
            System.out.println("");
            this.replay();
        }
        System.out.println(nbrUserDuelDefender + " (rappel de ta combinaison)");

        // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserDuel
        System.out.println("");
        display.displayAskNumber(nbrDigit);
        nbrUserDuelChallenger = display.getUserChoiceStringExport();
        logger.info("nombre entré par l'utilisateur = " + nbrUserDuelChallenger);

        // verification si gagnant challenger
        this.testIfUserWinChallengerMode(loopForDuelMode, randCompDuel,nbrUserDuelChallenger, inverseLoop );
        System.out.println("");

        //************** Defender2************************

        System.out.println("******************************************************************************");
        System.out.println("**** " + (loopForDuelMode + 1) + "eme essai mode defender (l'ordinateur doit trouver ta combinaison) *****");
        logger.info("-------------------- Defender Mode 2 ----------------");

        //defender 2: lancement des nouveaux DichotomousDigits computeur avec les infos de la hashmapRefined
        String compDefenderRefined = this.hasmapToDicotomousString(hashmapRefined, compDuelFirstString,valueUserInString,nbrUserDuelDefender);
        logger.info(" nouveaux digit avec la methode dichotomique = " + compDefenderRefined);

        // j'affiche le nouvel essai computeur
        System.out.println(compDefenderRefined);

        do {
            logger.info("************* Debut de la boucle" + loopForDuelMode + "********* ");
            loopForDuelMode++;

            // j'affiche la demande de valeur
            display.displayForValueToUser();
            System.out.println(nbrUserDuelDefender + " (rappel de ta combinaison)");

            // je check si erreur ou tricherie et si ordi gagne
            valueUserInString = this.inputValuesUserAndCheckIfCheat(nbrUserDuelDefender, compDefenderRefined, loopForDuelMode);

            System.out.println("--> L'ordinateur n'a pas trouver ta combinaison au " + loopForDuelMode + "eme essai");
            System.out.println("");

            //*********** Challenger2******************
            inverseLoop -= 1;
            System.out.println("**************************************************************************************");
            System.out.println("**** " + loopForDuelMode + "eme essai mode Challenger (Tu dois trouver la combinaison de l'ordinateur) *****");
            logger.info("-------------------- Challenger Mode boucle " + loopForDuelMode + "----------------");
            System.out.println(" Voici les valeurs pour trouver la combinaison de l'ordinateur:");

            // je verifie et j'affiche si le Mode developper a été demandé
            if (developerMode.contains("true")) {System.out.println("(" + randCompDuel + ") = chiffre ordinateur (mode developpeur)");}

            //challenger 2 et suivant: Je lance la comparaison et j'affiche le resultat.
            afterCompareDuel = this.CompareTwoString(randCompDuel, nbrUserDuelChallenger);
            System.out.println(afterCompareDuel);
            System.out.println(nbrUserDuelChallenger + " (rappel de ta combinaison)");

            // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserDuel
            System.out.println("");
            display.displayAskNumber(nbrDigit);
            nbrUserDuelChallenger = display.getUserChoiceStringExport();
            logger.info("nombre entré par l'utilisateur = " + nbrUserDuelChallenger);

            // verification si gagnant challenger
            this.testIfUserWinChallengerMode(loopForDuelMode, randCompDuel,nbrUserDuelChallenger, inverseLoop );

            if (nbrOfTry > 2) {
                //************** Defender3************************
                System.out.println("************************************************************************************");
                System.out.println("**** " + (loopForDuelMode + 1) + "eme essai mode defender (l'ordinateur doit trouver ta combinaison) *****");
                logger.info("-------------------- Defender Mode boucle " + loopForDuelMode + "----------------");

                //defender 3 et suivant: je fais des nouveaux chiffres computer avec les  nouvelles valeurs
                compDefenderRefined = this.hasmapToDicotomousString(hashmapRefined,compDefenderRefined,valueUserInString,nbrUserDuelDefender);
                logger.info("nouveau numero computeur avec methode dicotomous = " + compDefenderRefined);

                //defender 3 j'affiche le nouvel essai computeur
                System.out.println(compDefenderRefined);
            }

        }while (loopForDuelMode != nbrOfTry);
        System.out.println("et l'ordinateur n'as plus d'essai non plus, donc personne ne gagne !");
        System.out.println("");

        //replay
        this.replay();
    }
}
