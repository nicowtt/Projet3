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
        nbrUserDefender = display.getUserChoiceStringExport(); //-> variable string utilisateur = nbrUserDefender
        logger.info("nombre entré par l'utilisateur = " + nbrUserDefender);

        // creation d'une hashMap base avec les limite Max, Min et digitok incrementé par digit
        Map<String, Integer> completeHashMapBase = searchNumber.createHashMapBase(getNbrDigit());
        logger.info(" hashMap Base dans la class Main = " + completeHashMapBase);

        // premier jet computeur  "55...."-> compDefenderString1 et affichage sur la console
        String compDefenderString1 = searchNumber.fiveOnlyDigit(); // ou si tu veux aleatoire -> searchNumber.computerNbrCombination(getNbrDigit(), 0, 9);
        logger.info("premier jet aleatoire computeur = " + compDefenderString1);
        System.out.println(compDefenderString1);

        // j'affiche la demande de valeur
        display.displayForValueToUser();

        // je check si erreur ou tricherie
        valueUserInString = searchNumber.inputValuesUserAndCheckIfCheat(nbrUserDefender, compDefenderString1);

        // je renseigne la hashmap
        Map<String, Integer> hashmapRefined = searchNumber.infoDigitForRefinedToHahMap(completeHashMapBase, compDefenderString1, valueUserInString);
        logger.info("nouvelle hasmap refined = " + hashmapRefined);

        // je regarde si l'ordi gagne (ça peut arriver au premier coup (si l'utilisateur rentre "55")
        boolean winTestDefender = searchNumber.getIsWin();
        logger.info("ordi gagnant ? = " + winTestDefender);

        if (winTestDefender == true) {
            System.out.println(" l'ordinateur a trouvé ta combinaison au 1er coup !! (je me demande si tu es au courant de ma methode de recherche hum hum...");
            System.out.println("");
            // je lance la methode replay
            searchNumber.replay();
        }
        // lancement des nouveaux DichotomousDigits computeur avec les info de la hashmapRefined
        String compDefenderRefined = searchNumber.hasmapToDicotomousString(hashmapRefined, compDefenderString1,valueUserInString,nbrUserDefender);
        logger.info(" nouveaux digit avec la methode dichotomique = " + compDefenderRefined);

        // je check si computeur gagne
        winTestDefender = searchNumber.getIsWin();
        logger.info("ordi gagnant ? = " + winTestDefender);

        if (winTestDefender == true) {
            System.out.println(" l'ordinateur a trouvé ta combinaison au " + (loopForDefenderMode + 1) + "ème essais");
            System.out.println("");
            // je lance la methode replay
            searchNumber.replay();
        }
        // j'affiche le nouvel essai computeur
        System.out.println(compDefenderRefined);

        do {
            // debut de la boucle (jusqu'a -> computer gagne ou fin des essais, il perd)
            logger.info("");
            logger.info("************* Debut de la boucle ********* " + loopForDefenderMode);

            loopForDefenderMode++;
            // j'affiche la demande de valeur
            display.displayForValueToUser();
            // je check si erreur ou tricherie
            valueUserInString = searchNumber.inputValuesUserAndCheckIfCheat(nbrUserDefender, compDefenderRefined);

            // je fait des nouveau numero computer avec les  nouvelles valeurs
            compDefenderRefined = searchNumber.hasmapToDicotomousString(hashmapRefined,compDefenderRefined,valueUserInString,nbrUserDefender);
            logger.info("nouveau numero computeur avec methode dicotomous = " + compDefenderRefined);

            // j'affiche le nouvel essai computeur
            System.out.println(compDefenderRefined);

            // je check si computeur gagne
            winTestDefender = searchNumber.getIsWin();
            logger.info("ordi gagnant ? = " + winTestDefender);

            if (winTestDefender == true) {
                System.out.println(" l'ordinateur a trouvé ta combinaison" + " au " + (loopForDefenderMode + 1) + "ème essais");
                System.out.println("");
                // je lance la methode replay
                searchNumber.replay();
            }

        } while (loopForDefenderMode != getNbrOfTry());
        System.out.println("l'ordinateur n'a pas trouvé ta combinaison de chiffre(s), tu as gagné !!!");
        System.exit(0);
    }
}


