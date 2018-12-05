package com.ocr.nicolas;

import com.ocr.nicolas.utils.Utils;

import java.util.Map;

public class SearchNumberDefender extends SearchNumber {

    private int loopForDefenderMode = 1; //nbr of loop
    private String nbrUserDefender = ""; //String user input
    private String valueUserInString = ""; //String values input (+-=+)
    private String compDefenderString1 = ""; // First computeur nbr
    private String getCompDefenderString = ""; // computeur nbr (in loop)


    public SearchNumberDefender(int nbrDigit, int nbrOfTry, String developerMode) {
        super(nbrDigit, nbrOfTry, developerMode);
    }

    public int getLoopForDefenderMode() {return loopForDefenderMode;}

    /**
     * For playing DefenderMode of SearchNumber
     */
    public void playDefenderModeSearchNumber() {

        //objets
        MenuDisplay display = new MenuDisplay();

        //variable
        int inverseLoop = nbrOfTry;

        // Je donne le nombre d'essai possible
        System.out.println("l'ordinateur a " + nbrOfTry + " essai pour trouver ta combinaison");

        // Je demande la suite de chiffre a l'utilisateur
        display.displayAskNumber(nbrDigit);
        nbrUserDefender = display.getUserChoiceStringExport(); //-> variable string utilisateur = nbrUserDefender
        logger.info("nombre entré par l'utilisateur = " + nbrUserDefender);

        // creation d'une hashMap base avec les limite Max, Min et digitok incrementé par digit
        Map<String, Integer> completeHashMapBase = Utils.createHashMapBase(nbrDigit);
        logger.info(" hashMap Base dans la class Main = " + completeHashMapBase);

        // premier jet computeur  uniquement des "5" -> compDefenderString1 et affichage sur la console
        String compDefenderString1 = this.fiveOnlyDigit();
        logger.info("premier jet aleatoire computeur = " + compDefenderString1);
        System.out.println(compDefenderString1);

        // j'affiche la demande de valeur
        display.displayForValueToUser();
        inverseLoop--;

        // je check si erreur ou tricherie et si ordi gagne
        valueUserInString = this.inputValuesUserAndCheckIfCheat(nbrUserDefender, compDefenderString1, loopForDefenderMode, inverseLoop);

        // je renseigne la hashmap
        Map<String, Integer> hashmapRefined = this.infoDigitForRefinedToHahMap(completeHashMapBase, compDefenderString1, valueUserInString);
        logger.info("nouvelle hasmap refined = " + hashmapRefined);

        // lancement des nouveaux DichotomousDigits computeur avec les infos de la hashmapRefined
        String compDefenderRefined = this.hasmapToDicotomousString(hashmapRefined, compDefenderString1,valueUserInString,nbrUserDefender);
        logger.info(" nouveaux digit avec la methode dichotomique = " + compDefenderRefined);

        // j'affiche le nouvel essai computeur
        System.out.println(compDefenderRefined);

        do {

            // debut de la boucle (jusqu'a -> computer gagne ou fin des essais, il perd)
            logger.info("************* Debut de la boucle" + loopForDefenderMode + "********* ");
            loopForDefenderMode++;

            // j'affiche la demande de valeur
            display.displayForValueToUser();
            inverseLoop--;

            // je check si erreur ou tricherie et si ordi gagne
            valueUserInString = this.inputValuesUserAndCheckIfCheat(nbrUserDefender, compDefenderRefined,loopForDefenderMode, inverseLoop );

            // je fais des nouveaux chiffres computer avec les  nouvelles valeurs
            compDefenderRefined = this.hasmapToDicotomousString(hashmapRefined,compDefenderRefined,valueUserInString,nbrUserDefender);
            logger.info("nouveau numero computeur avec methode dicotomous = " + compDefenderRefined);

            // j'affiche le nouvel essai computeur
            System.out.println(compDefenderRefined);

        } while (loopForDefenderMode != nbrOfTry);
        //System.out.println("l'ordinateur n'as plus d'essai,tu gagne !");
        //System.out.println("");

        //lancement du menu pour replay
        this.replay();
    }
}

// ( * pour info * methode "playDefenderModeSearchNumber" total = 63 - 41 ligne (espace , teste et logger) -> 22 lignes


