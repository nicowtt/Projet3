package com.ocr.nicolas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchNumberDefender extends SearchNumber {

    private int loopForDefenderMode = 1;

    public SearchNumberDefender(int nbrDigit, int nbrOfTry, String developerMode, boolean isWin) {
        super(nbrDigit, nbrOfTry, developerMode, isWin);
    }

    /**
     * For playing DefenderMode of SearchNumber
     */
    public void playDefenderMode() {

        //objets
        MenuDisplay display = new MenuDisplay();
        SearchNumber searchNumber = new SearchNumber(getNbrDigit(), getNbrOfTry(), getDeveloperMode(), getIsWin());

        // Je donne le nombre d'essai possible
        System.out.println("l'ordinateur a " + getNbrOfTry() + " essai pour trouver ta combinaison");

        // Je demande la suite de chiffre a l'utilisateur -> nbrUserString
        display.displayAskNumber(getNbrDigit());
        String nbrUserDefender = display.getUserChoiceStringExport(); //-> variable string utilisateur = nbrUserDefender

        logger.info("nombre entré par l'utilisateur = " + nbrUserDefender);

        // creation d'une hashMap base avec les limite Max, Min et digitok incrementé par digit
        Map<String, Integer> completeHashMapBase = searchNumber.createHashMapBase(getNbrDigit());
        logger.info(" hashMap Base dans la class Main = " + completeHashMapBase);

        //do {
            // premier jet computeur  "55...."-> compDefenderString1 et affichage sur la console
            String compDefenderString1 = searchNumber.fiveOnlyDigit(); // ou si tu veux aleatoire -> searchNumber.computerNbrCombination(getNbrDigit(), 0, 9);
            logger.info("premier jet aleatoire computeur = " + compDefenderString1);
            System.out.println(compDefenderString1);

            // j'affiche la demande de valeur
            display.displayForValueToUser();

            // je recupères les valeurs (+--+) a l'utilisateur en String
            String valueUserInString = searchNumber.inputValueUserToString();
            logger.info("Valeur entré par l'utilisateur = " + valueUserInString);

            // je renseigne la hashmap
            Map<String, Integer> hashmapRefined = searchNumber.infoDigitForRefinedToHahMap(completeHashMapBase, compDefenderString1, valueUserInString);
            logger.info("nouvelle hasmap refined = " + hashmapRefined);

            // je regarde si l'ordi gagne (ça peut arriver au premier coup (si l'utilisateur rentre "55")
            boolean winTestDefender = searchNumber.getIsWin();
            logger.info("ordi gagnant ? = " + winTestDefender);

            if ( winTestDefender == true) {
                System.out.println(" l'ordinateur a gagné au 1er coup !!");
                System.out.println("");
                // je lance la methode replay
                searchNumber.replay();
            }

        //} while (loopForDefenderMode != getNbrDigit());
        // debut de la boucle (jusqu'a -> computer gagne ou fin des essais, il perd)
        logger.info("");
        logger.info("************* Debut de la boucle ********* " + loopForDefenderMode);

        // lancement des nouveaux DichotomousDigits computeur avec les info de la hashmapRefined
        String compDefenderRefined = searchNumber.hashMapRefined(hashmapRefined,valueUserInString);
        System.out.println(compDefenderRefined);
        logger.info(" nouveaux digit avec la methode dichotomique = " + compDefenderRefined);

        //todo il faut que l'ordinateur dise si tu triche

        //todo je recupere les nouveaux digits random computeur dans une derniere arrayList

        //todo je la compare pour affiché les + - et = a l'utilisateur et je renseigne la hasmap.
        System.exit(0);


        }
    }


