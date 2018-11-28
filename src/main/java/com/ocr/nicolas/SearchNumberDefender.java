package com.ocr.nicolas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchNumberDefender extends SearchNumber {


    public SearchNumberDefender(int nbrDigit, int nbrOfTry, String developerMode, int countWin) {
        super(nbrDigit, nbrOfTry, developerMode, countWin);
    }

    /**
     * For playing DefenderMode of SearchNumber
     */
    public void playDefenderMode() {



        //variables
        int loopForDefenderMode = 1;

        //objets
        MenuDisplay display = new MenuDisplay();
        SearchNumber searchNumber = new SearchNumber(getNbrDigit(), getNbrOfTry(), getDeveloperMode(), getCountWin());

        // Je donne le nombre d'essai possible
        System.out.println("l'ordinateur a " + getNbrOfTry() + " essai pour trouver ta combinaison");

        // Je demande la suite de chiffre a l'utilisateur -> nbrUserString
        display.displayAskNumber(getNbrDigit());
        String nbrUserDefender = display.getUserChoiceStringExport(); //-> variable string utilisateur = nbrUserDefender

        logger.info("nombre entré par l'utilisateur = " + nbrUserDefender);

        //je converti en int pour voir si gagnant du premier coup (avec un digit cela peut arriver)
        Integer nbrUserIntFirst = Integer.valueOf(nbrUserDefender);

        // creation d'une arrayList pour chaque digit affiné
        List<String> listDigitDefenderModeAfterRefine = new ArrayList<>();

        // creation d'une hashMap base avec les limite Max, Min et digitok incrementé par digit -> ok
        Map<String, Integer> completeHashMapBase = searchNumber.createHashMapBase(getNbrDigit());
        logger.info(" hashMap Base dans la class Main = " + completeHashMapBase);

        //do {
            // premier jet computeur en recuperant chaque digit dans un string -> compDefenderString1 et affichage sur la console
            String compDefenderString1 = "55"; // ou si tu veux aleatoire -> searchNumber.computerNbrCombination(getNbrDigit(), 0, 9);
            logger.info("premier jet aleatoire computeur = " + compDefenderString1);
            System.out.println(compDefenderString1);

            // je converti en string le jet computer
            Integer compDefenderInt = Integer.valueOf(compDefenderString1);

            // je demande les valeurs (+--+) a l'utilisateur et je recupere
            display.displayForValueToUser();
            String valueUserInString = searchNumber.inputValueUserToString();
            logger.info("Valeur entré par l'utilisateur = " + valueUserInString);

            //je converti le string computeur en array list
            List<Integer> listCompInt = searchNumber.stringToArrayList(compDefenderString1);

            //je converti le string user en array list
            List<String> listUserStringValues = searchNumber.stringToArrayListString(valueUserInString);

            // je renseigne la hashmap
            Map<String, Integer> hashmapRefined = searchNumber.infoDigitForRefinedToHahMap(completeHashMapBase, listCompInt, listUserStringValues);
            logger.info("nouvelle hasmap refined = " + hashmapRefined);


            //todo while ** debut boucle (jusqu'a -> computer gagne ou fin des essais, il perd)
            //todo je lance les nouveau randomDigit avec les info de la hashmap
            //todo je recupere les nouveaux digits random computeur dans une derniere arrayList
            //todo je la compare pour affiché les + - et = a l'utilisateur et je renseigne la hasmap.


            System.exit(0);

        }
    }


