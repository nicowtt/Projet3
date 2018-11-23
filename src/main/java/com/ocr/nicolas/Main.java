package com.ocr.nicolas;

import jdk.nashorn.internal.ir.IfNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {

        // lecture du fichier config.properties.
        ReadPropertiesFile read = new ReadPropertiesFile();
        read.readConfigProperties();
        int nbrBoxesCombinationMysteryNumber = read.getNbrBoxesCombinationSearchNumber(); // = nbr de digit
        String developerMode = read.getDeveloperMode(); // developer mode?
        int nbrOfTryMysteryNumber = read.getNbrOfTryMysteryNumber(); // nomber of try

        // Set du jeu Recherche nombre
        MysteryNumber mysteryNumber = new MysteryNumber();
        mysteryNumber.setNbrDigit(nbrBoxesCombinationMysteryNumber);

        //declaration variable pour jouer
        int playGames = 1;
        int replay = 1;

        // Lancement des jeux:

        // boucle principale "demande du jeux"
        while (playGames == 1) {

            // Affichage du menu du choix des jeux.
            MenuDisplay display = new MenuDisplay();
            display.displayAskGamesMenu();

            // Recuperation variable du choix des jeux
            int gamesMenuChoice = display.displayGamesMenuChoice();
            if (gamesMenuChoice == 3) {
                System.exit(0);
            }

            // Affichage du menu du type de jeux  .
            display.displayAskTypeOfGame();

            // Recuperation variable du type de jeux
            int gameTypeChoice = display.displayGameTypeChoice();

            //possibilite de replay
            replay = 1;

            //boucle secondaire "demande de replay"
            while (replay == 1) {
                switch (gamesMenuChoice) {
                    case 1:
                        switch (gameTypeChoice) {
                            case 1:
                                //**********jeux searchnumber+/-****** mode challenger******

                                // Recuperation variable random ordinateur  -> randCompChallenger
                                String randCompChallenger = mysteryNumber.computerNbrCombination(nbrBoxesCombinationMysteryNumber, 0, 10);
                                logger.info("--------> aleatoire String computeur = " + randCompChallenger);

                                // Je donne le nombre d'essai possible
                                System.out.println("vous avez " + nbrOfTryMysteryNumber + " essai");

                                // Je lance le jeux
                                int nbrLoopChallengerMode = nbrOfTryMysteryNumber;
                                int winChallenger = 0;
                                int loopForChallengerMode = 0;

                                do {
                                    while (nbrLoopChallengerMode != 0) {
                                        loopForChallengerMode += 1;
                                        logger.info("");
                                        logger.info("********************   Boucle " + loopForChallengerMode + "    *********************");

                                        // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserChallenger
                                        display.displayAskNumber(nbrBoxesCombinationMysteryNumber);
                                        String nbrUserChallenger = display.getUserChoiceStringExport();

                                        logger.info("nombre entré par l'utilisateur = " + nbrUserChallenger);

                                        // Je lance la comparaison et j'affiche le resultat
                                        String afterCompareChallenger = mysteryNumber.CompareTwoString(randCompChallenger, nbrUserChallenger);
                                        System.out.println(afterCompareChallenger);

                                        // je verifie si le Mode developper a été demandé
                                        if (developerMode.contains("true")) {
                                            System.out.println("(" + randCompChallenger + ")");
                                        } else {
                                            System.out.println("");
                                        }

                                        // je teste si gagnant ou perdant
                                        int winTestChallenger = mysteryNumber.getCounterForWinExport();
                                        if (winTestChallenger == 1) {
                                            System.out.println(" Exellent Vous avez gagné !!!");
                                            winChallenger = 1;
                                            nbrLoopChallengerMode = 0;
                                            logger.info("l'utilisateur a gagné contre l'ordinateur aprés " + loopForChallengerMode + " essais");
                                            System.out.println("");
                                            //remise a zero des parametres de choix
                                            gamesMenuChoice = 0;
                                            gameTypeChoice = 0;
                                            //lancement du menu du nouveau choix (1- rejouer; 2- Retour choix jeux; 3- quitter
                                            display.displayAskIfReplay();
                                            display.displayReplayChoice();
                                        } else {
                                            nbrLoopChallengerMode--;
                                            System.out.println(" il vous reste " + nbrLoopChallengerMode + " essai.");
                                        }
                                    }
                                } while (nbrLoopChallengerMode != 0);
                                if (winChallenger == 0) {
                                    System.out.println(" vous avez perdu...");
                                    System.out.println("-----> le nombre mystere etait: " + randCompChallenger);
                                    System.out.println("");
                                    logger.info("l'utilisateur a perdu");

                                    //remise a zero des parametres de choix
                                    gamesMenuChoice = 0;
                                    gameTypeChoice = 0;

                                    //lancement du menu pour nouveau choix (1- rejouer; 2- Retour choix jeux; 3- quitter
                                    display.displayAskIfReplay();
                                    int replayIntern = display.displayReplayChoice();
                                    switch (replayIntern) {
                                        case 1:
                                            //si l'utilisateur veut rejouer
                                            display.displayAskTypeOfGame();
                                            display.displayGameTypeChoice();
                                            gamesMenuChoice = 1;
                                            gameTypeChoice = 1;
                                            break;
                                        case 2:
                                            //l'utilisateur veut rechoisir un autre jeux
                                            gamesMenuChoice = 1;
                                            gameTypeChoice = 0;
                                            replay = 0;
                                            break;
                                        case 3:
                                            // l'utilisateur veut quitter
                                            System.exit(0);

                                        default:
                                            playGames = 0;
                                            break;
                                    }
                                    break;
                                }
                            case 2:
                                //**********jeux searchnumber+/-****** mode defenseur******

                                // Je donne le nombre d'essai possible
                                System.out.println("l'ordinateur a " + nbrOfTryMysteryNumber + " essai pour trouver ta combinaison");

                                // Je demande la suite de chiffre a l'utilisateur -> nbrUserString
                                display.displayAskNumber(nbrBoxesCombinationMysteryNumber);
                                String nbrUserDefender = display.getUserChoiceStringExport(); //-> variable string utilisateur = nbrUserDefender

                                logger.info("nombre entré par l'utilisateur = " + nbrUserDefender);

                                //je converti en int pour voir si gagnant du premier coup
                                Integer nbrUserIntFirst = Integer.valueOf(nbrUserDefender);


                                // Je lance le jeux
                                int nbrLoopDefenderMode = nbrOfTryMysteryNumber;
                                int winDefender = 0;
                                int loopForDefenderMode = 1;

                                // creation d'une arrayList pour chaque digit affiné
                                List<String> listDigitDefenderModeAfterRefine = new ArrayList<>();


                                do {

                                    // je met les variables (pour le random computeur) au niveau normal :
                                    int valueMin = 0;
                                    int valueMax = 9;
                                    int valueMinRefine = 0;
                                    int valueMaxRefine = 9;
                                    int digitCompOk = 0;
                                    String compDefenderStringFull = "";
                                    String refineStringCompDefender = "";


                                    // premier jet computeur en recuperant chaque digit dans un string -> compDefenderString1
                                    String compDefenderString1 = mysteryNumber.computerNbrCombination(nbrBoxesCombinationMysteryNumber, 0, 9);
                                    logger.info("premier let aleatoire computeur = " + compDefenderString1);

                                    // je convertie en int pour voir si gagnant
                                    Integer compDefenderInt = Integer.valueOf(compDefenderString1);
                                    // gagnant?
                                    if (compDefenderInt == nbrUserIntFirst ) {
                                        System.out.println("l'ordinateur a gagné du premier coup ");
                                        System.exit(0);
                                    }

                                    /// je lancer la comparaison des deux string pour affichage sur la console ( !! user en premier pour inversé)
                                    String displayFirstResultCompare = mysteryNumber.CompareTwoString(nbrUserDefender, compDefenderString1);
                                    System.out.println(displayFirstResultCompare + "( computeur = " + compDefenderString1 + ")");
                                    logger.info(" 1er comparaison " + displayFirstResultCompare);

                                    // creation d'une arrayList et je met le string computer dans cette arrayList finale (pour la prise en compte dans la boucle)
                                    List<Integer> listDefenderModeCompFinal = mysteryNumber.stringToArrayList(compDefenderString1);
                                    logger.info("1ere list computeur = " + listDefenderModeCompFinal);

                                    // creation d'une arrayList utilisateur pour pouvoir comparer digit par digit
                                    List<Integer> listDefenderModeUserFinal = mysteryNumber.stringToArrayList(nbrUserDefender);
                                    logger.info("list User = " + listDefenderModeUserFinal);

                                    if (digitCompOk == nbrBoxesCombinationMysteryNumber) {
                                        System.out.println(" l'ordinateur a gagné");
                                        System.exit(0);
                                    }
                                    //initialization
                                    listDigitDefenderModeAfterRefine.add("0");


                                    // ensuite il va falloir affiné les reponses de l'ordinateur
                                    while (loopForDefenderMode != nbrOfTryMysteryNumber) {

                                        logger.info("");
                                        logger.info("******* boucle " + loopForDefenderMode + "*************");

                                        // pour chaque digit de la liste finale ( je vais affiné les reponses)
                                        for (int i = 0; i < nbrBoxesCombinationMysteryNumber; i++) {


                                            // je fait deux array list avec un seul digit
                                            // computer
                                            List<Integer> listDigitComp = new ArrayList<>();
                                            listDigitComp.add(listDefenderModeCompFinal.get(i));
                                            logger.info("liste digit computeur (pour comparaison) = " + listDigitComp);
                                            // user
                                            List<Integer> listDigituser = new ArrayList<>();
                                            listDigituser.add(listDefenderModeUserFinal.get(i));
                                            logger.info("liste digit user (pour comparaison) = " + listDigituser);

                                            // je lance la comparaison avec les arrayList par digit pour avoir le valu (+ ou - ou =)
                                            String compareDigitForValue = mysteryNumber.compareTwoArrayList(listDigitComp, listDigituser);
                                            logger.info("valeur comparaison digit = " + compareDigitForValue);


                                            // convertion du digit en string pour lancer la comparaison pas digit (afin de renseigner la hashMap par digit)
                                            // je met la valeur int du premier digit computeur en String
                                            int digitComp = listDefenderModeCompFinal.get(i);
                                            String digitCompString = String.valueOf(digitComp);
                                            // je met la valeur int du premier digit user en string
                                            int digitUser = listDefenderModeUserFinal.get(i);
                                            String digitUserString = String.valueOf(digitUser);


                                            // je lance ma methode pour avoir le renseignement dans la hashMap par digit
                                            Map<String, Integer> hashMapDigit = mysteryNumber.infoDigitForRefined(digitCompString, compareDigitForValue);
                                            logger.info("valeur hashMap pour digit " + i + " = " + hashMapDigit);

                                            // methode sans ma derniere methode je recupere la valeur que je met dans une variable

                                            if (hashMapDigit.containsKey("refinedMin")) {
                                                valueMinRefine = hashMapDigit.get("refinedMin") + 1;

                                                if (valueMinRefine > valueMin) {
                                                    valueMin = valueMinRefine;
                                                }
                                            } else {
                                                valueMin = 0;
                                            }
                                            if (hashMapDigit.containsKey("refinedMax")) {
                                                valueMaxRefine = hashMapDigit.get("refinedMax" ) - 1;

                                                if (valueMaxRefine > valueMax) {
                                                    valueMax = valueMaxRefine;
                                                }
                                            }else {
                                                valueMax = 9;
                                            }

                                            if (hashMapDigit.containsKey("digitOk")) {
                                                digitCompOk += 1;
                                            }

                                            // gagnant?
                                            if (digitCompOk == nbrBoxesCombinationMysteryNumber) {
                                                System.out.println(" l'ordinateur a gagné");
                                                System.exit(0);
                                            }

                                            logger.info("");
                                            logger.info("refine min = " + valueMinRefine);
                                            logger.info("refine max = " + valueMaxRefine);
                                            logger.info("");

                                            // j'affine le chiffre au besoin
                                            String digitRefined = mysteryNumber.computerNbrCombination(1,valueMinRefine,valueMaxRefine);
                                            logger.info("digit affiné = " + digitRefined);

                                            // je converti le digit affiné en Int
                                            Integer digitRefinedInt = Integer.parseInt(digitRefined);

                                            // j'increment de le compteur d'equal si ok
                                            if ( digitRefinedInt == digitUser ) {
                                                digitCompOk += 1;
                                            }

                                            // j'ajoute le chiffre affiné pour afficher la valeur (developper mode)
                                            listDigitDefenderModeAfterRefine.remove(i);
                                            listDigitDefenderModeAfterRefine.add(digitRefined);
                                            logger.info("listDigitDefenderModeAfterrefine = " + listDigitDefenderModeAfterRefine);


                                            // je remplace la liste finale avec ce digit pour le prochain réafinage
                                            listDefenderModeCompFinal.remove(i);
                                            logger.info("liste affiné digitaprés remove = " + listDefenderModeCompFinal);
                                            listDefenderModeCompFinal.add(digitRefinedInt);
                                        }
                                        if (digitCompOk == nbrBoxesCombinationMysteryNumber) {
                                            System.out.println("l'ordinateur a gagné aprés " + loopForDefenderMode + " Coup !!");
                                            System.exit(0);
                                        }

                                        // je converti la liste int des chiffres affiné en string (pour la recomparé en mode normale
                                        String numberRefinedCompString = "";
                                        for (int k = 0; k < listDigitDefenderModeAfterRefine.size(); k++) {
                                            numberRefinedCompString = numberRefinedCompString + listDigitDefenderModeAfterRefine.get(k);
                                        }
                                        logger.info("String ordinateur after refine = " + numberRefinedCompString);


                                        // je compare le string affiné avec le String user et j'affiche la nouvelle comparaison
                                        String displayResultCompareOnLoop = mysteryNumber.CompareTwoString(nbrUserDefender, numberRefinedCompString);
                                        System.out.println(displayResultCompareOnLoop + "( computeur = " + numberRefinedCompString + ")");

                                        // je regarde si gagnant
                                        if (numberRefinedCompString == nbrUserDefender) {
                                            System.out.println("l'ordinateur a gagné aprés " + loopForDefenderMode + " Coup !!");
                                        }

                                        //j'increment la boucle
                                        loopForDefenderMode++;
                                    }

                                } while (loopForDefenderMode != nbrOfTryMysteryNumber);

                                System.out.println(" l'ordinateur n'a pas trouvé ta combinaison aprés " + nbrOfTryMysteryNumber + " essais tu a donc gagné !!!");
                                System.exit(0);
                        }
                }
            }
        }
    }
}











