package com.ocr.nicolas;

import com.ocr.nicolas.utils.Utils;

import java.util.Map;

public class SearchNumberDefender extends SearchNumber implements SearchNumberGame{

    private int loopForDefenderMode = 1; //nbr of loop
    private String nbrUserDefender = ""; //String user input
    private String valueUserInString = ""; //String values input (+-=+)
    private String compDefenderString1 = ""; // First computeur nbr
    private String getCompDefenderString = ""; // computeur nbr (in loop)

    /**
     * For playing DefenderMode of SearchNumber
     */
    public int playDefenderModeSearchNumber() {

        //objets
        MenuDisplay display = new MenuDisplay();

        //variable
        int inverseLoop = nbrOfTry;
        loopForDefenderMode = 1;
        int replay = 3;
        isWin = false;
        int nbrOneTry = 2;

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
        logger.info("premier jet computeur (uniquement des 5) = " + compDefenderString1);
        System.out.println(compDefenderString1);

        // j'affiche la demande de valeur
        display.displayForValueToUser();
        inverseLoop--;

        // je check si erreur ou tricherie et si ordi gagne
        valueUserInString = this.inputValuesUserAndCheckIfCheat(nbrUserDefender, compDefenderString1, loopForDefenderMode, nbrOneTry);
        if (!isWin) {

            // je renseigne la hashmap
            Map<String, Integer> hashmapRefined = this.infoDigitForRefinedToHahMap(completeHashMapBase, compDefenderString1, valueUserInString);
            logger.info("nouvelle hasmap refined = " + hashmapRefined);

            // lancement des nouveaux DichotomousDigits computeur avec les infos de la hashmapRefined
            String compDefenderRefined = this.hasmapToDicotomousString(hashmapRefined, compDefenderString1, valueUserInString, nbrUserDefender);
            logger.info(" nouveaux digit avec la methode dichotomique = " + compDefenderRefined);

            // j'affiche le nouvel essai computeur
            System.out.println(compDefenderRefined);

            do {
                // debut de la boucle (jusqu'a -> computer gagne ou fin des essais, il perd)
                logger.info("************* Debut de la boucle" + loopForDefenderMode + "********* ");
                loopForDefenderMode++;

                // j'affiche la demande de valeur
                display.displayForValueToUser();

                // je check si erreur ou tricherie et si ordi gagne
                valueUserInString = this.inputValuesUserAndCheckIfCheat(nbrUserDefender, compDefenderRefined, loopForDefenderMode, inverseLoop);
                if (!isWin && nbrOfTry >= 3 && loopForDefenderMode != nbrOfTry) {
                    inverseLoop--;

                    // je fais des nouveaux chiffres computer avec les  nouvelles valeurs
                    compDefenderRefined = this.hasmapToDicotomousString(hashmapRefined, compDefenderRefined, valueUserInString, nbrUserDefender);
                    logger.info("nouveau numero computeur avec methode dicotomous = " + compDefenderRefined);

                    // j'affiche le nouvel essai computeur
                    System.out.println(compDefenderRefined);
                } else { loopForDefenderMode = nbrOfTry;}

            } while (loopForDefenderMode != nbrOfTry) ;
            if (!isWin) {
                System.out.println("l'ordinateur n'as plus d'essai,tu gagne !");
                System.out.println("");
            }
        }
        //lancement du menu pour replay
        replay = PlayGames.replay();
        return replay;
    }
}

// ( * pour info * methode "playDefenderModeSearchNumber" total = 76 - 39 ligne (espace , texte et logger) -> 37 lignes


