package com.ocr.nicolas;

import java.util.Map;

public class SearchNumberDefender extends SearchNumber {

    private int loopForDefenderMode = 1; //nbr of loop
    private String nbrUserDefender = ""; //String user input
    private String valueUserInString = ""; //String values input (+-=+)
    private String compDefenderString1 = ""; // First computeur nbr
    private String getCompDefenderString = ""; // computeur nbr (in loop)

    public SearchNumberDefender(int nbrDigit, int nbrOfTry, String developerMode, boolean isWin) {
        super(nbrDigit, nbrOfTry, developerMode, isWin);
    }

    public int getLoopForDefenderMode() {return loopForDefenderMode;}

    /**
     * For playing DefenderMode of SearchNumber
     */
    public void playDefenderMode() {

        //objets
        MenuDisplay display = new MenuDisplay();
        SearchNumber searchNumber = new SearchNumber(getNbrDigit(), getNbrOfTry(), getDeveloperMode(), getIsWin());

        // Je donne le nombre d'essai possible
        System.out.println("l'ordinateur a " + getNbrOfTry() + " essai pour trouver ta combinaison");

        // Je demande la suite de chiffre a l'utilisateur
        display.displayAskNumber(getNbrDigit());
        nbrUserDefender = display.getUserChoiceStringExport(); //-> variable string utilisateur = nbrUserDefender
        logger.info("nombre entré par l'utilisateur = " + nbrUserDefender);

        // creation d'une hashMap base avec les limite Max, Min et digitok incrementé par digit
        Map<String, Integer> completeHashMapBase = searchNumber.createHashMapBase(getNbrDigit());
        logger.info(" hashMap Base dans la class Main = " + completeHashMapBase);

        // premier jet computeur  uniquement des "5" -> compDefenderString1 et affichage sur la console
        String compDefenderString1 = searchNumber.fiveOnlyDigit();
        logger.info("premier jet aleatoire computeur = " + compDefenderString1);
        System.out.println(compDefenderString1);

        // j'affiche la demande de valeur
        display.displayForValueToUser();

        // je check si erreur ou tricherie
        valueUserInString = searchNumber.inputValuesUserAndCheckIfCheat(nbrUserDefender, compDefenderString1);

        // je renseigne la hashmap
        Map<String, Integer> hashmapRefined = searchNumber.infoDigitForRefinedToHahMap(completeHashMapBase, compDefenderString1, valueUserInString);
        logger.info("nouvelle hasmap refined = " + hashmapRefined);

        // check si gagnant au premier coup
        searchNumber.testIfComputerWin(loopForDefenderMode);
        logger.info("ordi gagnant ? = " + getIsWin());

        // lancement des nouveaux DichotomousDigits computeur avec les infos de la hashmapRefined
        String compDefenderRefined = searchNumber.hasmapToDicotomousString(hashmapRefined, compDefenderString1,valueUserInString,nbrUserDefender);
        logger.info(" nouveaux digit avec la methode dichotomique = " + compDefenderRefined);

        // j'affiche le nouvel essai computeur
        System.out.println(compDefenderRefined);

        // je check si gagnant
        loopForDefenderMode++;
        searchNumber.testIfComputerWin(loopForDefenderMode);

        do {
            // debut de la boucle (jusqu'a -> computer gagne ou fin des essais, il perd)
            logger.info("************* Debut de la boucle" + loopForDefenderMode + "********* ");

            // j'affiche la demande de valeur
            display.displayForValueToUser();

            // je check si erreur ou tricherie
            valueUserInString = searchNumber.inputValuesUserAndCheckIfCheat(nbrUserDefender, compDefenderRefined);

            // je fait des nouveau numero computer avec les  nouvelles valeurs
            compDefenderRefined = searchNumber.hasmapToDicotomousString(hashmapRefined,compDefenderRefined,valueUserInString,nbrUserDefender);
            logger.info("nouveau numero computeur avec methode dicotomous = " + compDefenderRefined);

            // j'affiche le nouvel essai computeur
            System.out.println(compDefenderRefined);
            loopForDefenderMode++;

            // je check si gagnant
            searchNumber.testIfComputerWin(loopForDefenderMode);

        } while (loopForDefenderMode != getNbrOfTry());
        searchNumber.testIfComputerWin(loopForDefenderMode);
    }
}

// ( * pour info * methode "playDefenderMode" total = 70 - 44 ligne (espace , teste et logger) -> 26 lignes


