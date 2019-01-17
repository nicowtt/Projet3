package com.ocr.nicolas.SearchNumber;

import com.ocr.nicolas.*;
import com.ocr.nicolas.utils.Utils;

import java.util.Map;

public class SearchNumberDuel extends SearchNumber implements PlayDuel {

    // challenger
    private String randCompDuel; //String Random Computer -> challenger
    private String nbrUserDuelChallenger; // string user -> challenger (change with loop)
    private String afterCompareDuel; //value after compare -> challenger

    // defender
    private String nbrUserDuelDefender = ""; //String user input ->  defender
    private String valueUserInString = ""; //String values input (+-=+) pour le mode defender
    private String compDuelFirstString; // 1er String comp -> "5555"

    public SearchNumberDuel(Config config) {
        super(config);
    }

    public Replay playDuelMode() {

        //objet
        MenuDisplay display = new MenuDisplay();

        //variable
        Replay replayEnum;
        int inverseLoop = nbrOfTry;
        int inverseLoopDefender = nbrOfTry;
        int loopForDuelMode = 1;
        isWin = false;

        //annonce du nombre global d'essai possible
        System.out.println("il y aura " + nbrOfTry + " essai(s) possible pour trouver les combinaisons.");
        System.out.println("");

        // random computeur
        randCompDuel = this.computerNbrCombination(0, 9);
        logger.info("--------> aleatoire String computeur = " + randCompDuel);

        //************** Defender1************************
        System.out.println("**** " + loopForDuelMode + "er essai  *****");
        logger.info("-------------------- Defender Mode 1 ----------------");

        //demande une combinaison utilisateur (mode melangé)
        display.displayAskNumber(nbrDigit);
        nbrUserDuelDefender = display.getUserChoiceStringExport(); //-> variable string utilisateur = nbrUserDefender
        logger.info("nombre entré par l'utilisateur = " + nbrUserDuelDefender);

        //challenger 1: Je lance la comparaison
        String firstCompareChallenger = this.CompareTwoString(randCompDuel, nbrUserDuelDefender);
        logger.info(" premiere comparaison challenger mode duel " + firstCompareChallenger);

        // si l'utilisateur trouve la combinaison ordinateur du premier coup (ça peut arriver avec un digit par ex)
        if (nbrUserDuelDefender.contains(randCompDuel)) {
            System.out.println("Félicitation tu as trouvé la combinaison de l'ordinateur du premier coup !!");
            System.out.println("");
            isWin = true;
        }
        if (!isWin) {
            //defender 1: creation d'une hashMap base avec les limite Max, Min et digitok incrementé par digit.
            Map<String, Integer> completeHashMapBase = Utils.createHashMapBase(nbrDigit);
            logger.info(" hashMap Base dans la class Main = " + completeHashMapBase);

            //defender 1: premier jet computeur  uniquement des "5" et affichage sur la console
            compDuelFirstString = this.fiveOnlyDigit();
            logger.info("premier jet aleatoire computeur = " + compDuelFirstString);
            System.out.println(compDuelFirstString);

            // j'affiche la demande de valeur
            display.displayForValueToUser();
            inverseLoopDefender--;

            // je check si erreur ou tricherie et si ordi gagne
            valueUserInString = this.inputValuesUserAndCheckIfCheat(nbrUserDuelDefender, compDuelFirstString, loopForDuelMode, inverseLoopDefender);
            if (!isWin) {
                // je renseigne la hashmap
                Map<String, Integer> hashmapRefined = this.infoDigitForRefinedToHahMap(completeHashMapBase, compDuelFirstString, valueUserInString);
                logger.info("nouvelle hasmap refined = " + hashmapRefined);
                System.out.println("");

                //*********** Challenger1******************
                inverseLoop--;
                loopForDuelMode++;
                System.out.println("*********************");
                System.out.println("**** " + loopForDuelMode + "eme essai *****");
                logger.info("-------------------- Challenger Mode 1 ----------------");
                System.out.println(" Voici les valeurs pour trouver la combinaison de l'ordinateur:");

                // je verifie et j'affiche si le Mode developper a été demandé
                if (developerMode.contains("true") || Config.isDEV()) {System.out.println("(" + randCompDuel + ") = chiffre ordinateur (mode developpeur)");}

                //challenger 1: Je lance la comparaison et j'affiche le resultat.
                String afterCompareChallenger = this.CompareTwoString(randCompDuel, nbrUserDuelDefender);
                System.out.println(nbrUserDuelDefender + " (rappel de ta combinaison)");
                System.out.println(afterCompareChallenger);

                // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserDuel
                System.out.println("");
                display.displayAskNumber(nbrDigit);
                nbrUserDuelChallenger = display.getUserChoiceStringExport();
                logger.info("nombre entré par l'utilisateur = " + nbrUserDuelChallenger);

                // verification si gagnant challenger
                this.testIfUserWinChallengerMode(loopForDuelMode, randCompDuel, nbrUserDuelChallenger, inverseLoop);
                System.out.println("");
                if (!isWin) {
                    //************** Defender2************************
                    System.out.println("** au tour de l'ordinateur **");
                    logger.info("-------------------- Defender Mode 2 ----------------");

                    //defender 2: lancement des nouveaux DichotomousDigits computeur avec les infos de la hashmapRefined
                    String compDefenderRefined = this.hasmapToDicotomousString(hashmapRefined, compDuelFirstString, valueUserInString, nbrUserDuelDefender);
                    logger.info(" nouveaux digit avec la methode dichotomique = " + compDefenderRefined);

                    // j'affiche le nouvel essai computeur
                    System.out.println(nbrUserDuelDefender + " (rappel de ta combinaison)");
                    System.out.println(compDefenderRefined);

                    do {
                        if (!isWin) {
                            logger.info("************* Debut de la boucle" + loopForDuelMode + "********* ");
                            // j'affiche la demande de valeur
                            display.displayForValueToUser();
                            inverseLoopDefender--;

                            // je check si erreur ou tricherie et si ordi gagne
                            valueUserInString = this.inputValuesUserAndCheckIfCheat(nbrUserDuelDefender, compDefenderRefined, loopForDuelMode, inverseLoopDefender);
                            loopForDuelMode++;
                            // si il n'y a plus d'essai possible
                            if (isWin) {loopForDuelMode = nbrOfTry;}
                            if (!isWin) {
                                //*********** Challenger2******************
                                inverseLoop -= 1;
                                System.out.println("*********************************");
                                System.out.println("**** " + loopForDuelMode + "eme essai  *****");
                                logger.info("-------------------- Challenger Mode boucle " + loopForDuelMode + "----------------");
                                System.out.println(" Voici les valeurs pour trouver la combinaison de l'ordinateur:");

                                // je verifie et j'affiche si le Mode developper a été demandé
                                if (developerMode.contains("true")) {System.out.println("(" + randCompDuel + ") = chiffre ordinateur (mode developpeur)");}

                                //challenger 2 et suivant: Je lance la comparaison et j'affiche le resultat.
                                afterCompareDuel = this.CompareTwoString(randCompDuel, nbrUserDuelChallenger);
                                System.out.println(nbrUserDuelChallenger + " (rappel de ta combinaison)");
                                System.out.println(afterCompareDuel);

                                // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserDuel
                                System.out.println("");
                                display.displayAskNumber(nbrDigit);
                                nbrUserDuelChallenger = display.getUserChoiceStringExport();
                                logger.info("nombre entré par l'utilisateur = " + nbrUserDuelChallenger);

                                // verification si gagnant challenger
                                this.testIfUserWinChallengerMode(loopForDuelMode, randCompDuel, nbrUserDuelChallenger, inverseLoop);
                                if (!isWin) {
                                    if (nbrOfTry > 2) {
                                        //************** Defender3************************
                                        logger.info("-------------------- Defender Mode boucle " + loopForDuelMode + "----------------");
                                        System.out.println("");
                                        System.out.println(" ** au tour de l'ordinateur ** ");

                                        //defender 3 et suivant: je fais des nouveaux chiffres computer avec les  nouvelles valeurs
                                        compDefenderRefined = this.hasmapToDicotomousString(hashmapRefined, compDefenderRefined, valueUserInString, nbrUserDuelDefender);
                                        logger.info("nouveau numero computeur avec methode dicotomous = " + compDefenderRefined);

                                        //defender 3 j'affiche le nouvel essai computeur
                                        System.out.println(nbrUserDuelDefender + " (rappel de ta combinaison)");
                                        System.out.println(compDefenderRefined);
                                    }
                                }
                            }
                        }
                    }while (loopForDuelMode != nbrOfTry) ;
                    if (!isWin) {
                        // j'affiche la demande de valeur
                        display.displayForValueToUser();

                        // je check si erreur ou tricherie et si ordi gagne
                        valueUserInString = this.inputValuesUserAndCheckIfCheat(nbrUserDuelDefender, compDefenderRefined, loopForDuelMode, inverseLoopDefender);
                        if (!isWin) {
                            System.out.println(" l'ordinateur n'as plus d'essai non plus, donc personne ne gagne !");
                            System.out.println("");
                        }
                    }
                }
            }
        }
        //replay
        if (isWin) {
            System.out.println("La combinaison de l'ordinateur était : " + randCompDuel);
            System.out.println("");
        }
        replayEnum = PlayGames.replayEnum();
        return replayEnum;
    }
}


// ( * pour info * methode "PlayDuelMode" total = 174 - 76 lignes (espace , texte et logger) ->  98 lignes

