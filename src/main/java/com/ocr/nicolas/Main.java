package com.ocr.nicolas;

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

                                // Je lance le jeux
                                int nbrLoopDefenderMode = nbrOfTryMysteryNumber;
                                int winDefender = 0;
                                int loopForDefenderMode = 1;
                                do {
                                    // l'ordinateur lance un nombre aleatoire -> nbrCompDefender
                                    String nbrCompDefender = mysteryNumber.computerNbrCombination(nbrBoxesCombinationMysteryNumber, 0, 9);
                                    logger.info(" 1er nombre aleatoire ordinateur = " + nbrCompDefender);

                                    // je fais la comparaison normale  et je l'affiche(attention je met en premier le user !! pour inversé le resultat + et -)
                                    String listCompareDigit = mysteryNumber.CompareTwoString(nbrUserDefender, nbrCompDefender );
                                    System.out.println(listCompareDigit);

                                    while (loopForDefenderMode > 0 && loopForDefenderMode != nbrOfTryMysteryNumber) {

                                        logger.info("");
                                        logger.info("********************   Boucle " + loopForDefenderMode + "    *********************");

                                        // !!!!!! Comparaison  digit par digit !! pour avoir mes variables pour prochain random computeur (attention je met en premier le user !! pour inversé le resultat + et -)

                                        // je converti le string computeur en arraylist de integer -> listCompDefenderInt
                                        List<Integer> listCompDefenderInt = mysteryNumber.stringToArrayList(nbrCompDefender);

                                        // je converti le string user en arraylist de integer -> listUserDefenderInt
                                        List<Integer> listUserDefenderInt = mysteryNumber.stringToArrayList(nbrUserDefender);

                                        // je compare digit par digit et je recupere les variables affiné au fur et a mesure pour le prochain random (en premier le user !! pour inversé)
                                        //boucle for pour chaques digit:
                                        for (int i = 0; i < nbrBoxesCombinationMysteryNumber ; i++) {
                                            // je crée donc une liste avec digit computeur:
                                            List<Integer> listDigitCompDefenderInt = new ArrayList<>();
                                            listDigitCompDefenderInt.add(listCompDefenderInt.get(i));

                                            // je crée une liste avec digit user:
                                            List<Integer> listDigitUserDefenderInt = new ArrayList<>();
                                            listDigitUserDefenderInt.add(listUserDefenderInt.get(i));

                                            // je peux donc comparé les deux listes de 1 digit
                                            String compareDigitWithRefine = mysteryNumber.compareTwoArrayList(listDigitUserDefenderInt, listDigitCompDefenderInt);

                                            // je peux recuperer les variables pour affiner le prochain random
                                            // je met a zero les prochaine variables:
                                            int refinedMax = 0;
                                            int refinedMin = 0;
                                            int digitCompOk = 0;

                                            // je recupere
                                            refinedMax += mysteryNumber.getRefinedMinExport();
                                            refinedMin += mysteryNumber.getRefinedMaxExport();
                                            digitCompOk = mysteryNumber.getDigitCompOkExport();

                                            // je recupere la variable computeur pour enlever la mauvaise variable
                                            int compToKeep = listCompDefenderInt.get(0);
                                            if (compToKeep == refinedMax) {
                                                refinedMin = 10;
                                            }else {
                                                refinedMax = 10;
                                            }

                                            // je les ajoute dans une liste au fur et a mesure du nombre de digit -> list donc avec indication affiné ou gardé
                                            // je crée une liste pour le nombre de digit
                                            List<Integer> nbrDigitList = new ArrayList<>();

                                            // je crée une  liste hashmap pour metre l'info de chaque digit
                                            Map<String, Integer> indicateRefineAndEqualMap = new HashMap<String, Integer>();
                                            // J'ajoute dans cette liste uniquement ce qui change pour ce digit et je l'ajoute dans la list nbrDigit (une hash map dans une list)
                                            if (refinedMin != 0) {nbrDigitList.add(indicateRefineAndEqualMap.put("refinedMin", refinedMin));}
                                            if (refinedMax != 0) {nbrDigitList.add(indicateRefineAndEqualMap.put("refinedMax", refinedMax));}
                                            if (digitCompOk != 0) {nbrDigitList.add(indicateRefineAndEqualMap.put("digitCompOk", digitCompOk));}
                                            // j'ai donc une list nbrDigitList avec une hashmap dans chaque case du tableau avec l'indication refined + ou - et ok si le chiffre est bon

                                            //je regarde si l'ordinateur à gagné pour finir
                                            if (digitCompOk == nbrBoxesCombinationMysteryNumber) {
                                                System.out.println("L'ordinateur à gagné aprés " + loopForDefenderMode + "essais");
                                            }


                                            // je consulte la liste avec la hashmap dedans et je lance du random affiné par digit au besoin
                                            // je cree une list pour affiché les resultats affiné
                                            List<Integer> nbrListRefine = new ArrayList<>();

                                            // pour chaque digit dans la liste avec le nombre de digit + hashmap
                                            for (int j = 0; j < nbrDigitList.size() ; j++) {
                                                if (indicateRefineAndEqualMap.containsKey("refinedMin")) {
                                                    int refineNumberMin = 0;
                                                    refineNumberMin = indicateRefineAndEqualMap.get("refinedMin") + (int) (Math.random() * ((9 - indicateRefineAndEqualMap.get("refinedMin")) + 1));
                                                    nbrListRefine.add(refineNumberMin);
                                                }
                                                if (indicateRefineAndEqualMap.containsKey("refinedMax")) {
                                                    int refineNumberMax = 0;
                                                    refineNumberMax = (int) (Math.random() * ((indicateRefineAndEqualMap.get("refinedMax") + 1)));
                                                    nbrListRefine.add(refineNumberMax);
                                                }
                                            }// je converti la liste affiné en string
                                            String listRefineStringCompDefender = "";
                                            for (int j = 0; j < (nbrBoxesCombinationMysteryNumber - 1) ; j++) {
                                                int newInt = nbrListRefine.get(j);
                                                listRefineStringCompDefender = listRefineStringCompDefender + newInt;
                                            }

                                            // je lance la nouvelle comparaison et je l'affiche( !!! attention user en premier)
                                            String listCompareAfterRefine = mysteryNumber.CompareTwoString(nbrUserDefender, listRefineStringCompDefender);
                                            System.out.println(listCompareAfterRefine);
                                            logger.info("comparaison aprés affinage," + loopForDefenderMode + "boucle");
                                        }
                                            // j'incremente la boucle en cours
                                            loopForDefenderMode += 1;
                                        }

                                        //todo si user gagne
                                        //todo faire la boucle pour rejouer ou relancer un autre jeux.
                                }while (loopForDefenderMode > 0 && loopForDefenderMode != nbrOfTryMysteryNumber);
                                System.out.println(" l'ordinateur n'a pas trouvé ta combinaison aprés " + nbrOfTryMysteryNumber + " essais tu a donc gagné !!!");
                        }
                }
            }
        }
    }
}









