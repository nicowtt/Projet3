package com.ocr.nicolas;

public class SearchNumberDefender {


    /*
    //**********jeux searchnumber+/-****** mode defenseur******

    //variables
    int loopForDefenderMode = 1;


    // Je donne le nombre d'essai possible
                System.out.println("l'ordinateur a " + nbrOfTry + " essai pour trouver ta combinaison");

    // Je demande la suite de chiffre a l'utilisateur -> nbrUserString
                display.displayAskNumber(nbrDigit);
    String nbrUserDefender = display.getUserChoiceStringExport(); //-> variable string utilisateur = nbrUserDefender

                logger.info("nombre entré par l'utilisateur = " + nbrUserDefender);

    //je converti en int pour voir si gagnant du premier coup (avec un digit cela peut arriver)
    Integer nbrUserIntFirst = Integer.valueOf(nbrUserDefender);


    // creation d'une arrayList pour chaque digit affiné
    List<String> listDigitDefenderModeAfterRefine = new ArrayList<>();

    // creation d'une hashMap base avec les limite Max, Min et digitok incrementé par digit -> ok
    Map<String, Integer> completeHashMapBase = mysteryNumber.createHashMapBase(nbrDigit);
                logger.info(" hashMap Base dans la class Main = " + completeHashMapBase);

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
        String compDefenderString1 = mysteryNumber.computerNbrCombination(nbrDigit, 0, 9);
        logger.info("premier jet aleatoire computeur = " + compDefenderString1);

        // je convertie en int pour voir si gagnant
        Integer compDefenderInt = Integer.valueOf(compDefenderString1);
        // gagnant?
        if (compDefenderInt == nbrUserIntFirst) {
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

        // je met les valeur + - et = dans une ArrayList
        List<String> valueListDefender = mysteryNumber.valueStringToArrayList(displayFirstResultCompare);
        logger.info("liste de valeur = " + valueListDefender);

        //initialization
        listDigitDefenderModeAfterRefine.add("0");

        // je met a jour ma hashmap avec les nouvelles données de comparaison
        Map<String, Integer> hashMapRefine = mysteryNumber.infoDigitForRefined2(completeHashMapBase, listDefenderModeCompFinal, valueListDefender);
        logger.info(" 1 HashMap pour metre a jour les valeur max et min et egual" + hashMapRefine);

        //todo je renseigne ma hashMap pour le futur random affiné. -> en cour dans mysteryNumber
        //todo while ** debut boucle (jusqu'a -> computer gagne ou fin des essais, il perd)
        //todo je lance les nouveau randomDigit avec les info de la hashmap
        //todo je recupere les nouveaux digits random computeur dans une derniere arrayList
        //todo je la compare pour affiché les + - et = a l'utilisateur et je renseigne la hasmap.


        // ensuite il va falloir affiné les reponses de l'ordinateur -> ici debut de la boucle while
        while (loopForDefenderMode != nbrOfTry) {

            logger.info("");
            logger.info("******* boucle " + loopForDefenderMode + " *************");


            // pour chaque digit de la liste finale ( je vais affiné les reponses)

            for (int i = 0; i < nbrDigit; i++) {


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
                    valueMaxRefine = hashMapDigit.get("refinedMax") - 1;

                    if (valueMaxRefine > valueMax) {
                        valueMax = valueMaxRefine;
                    }
                } else {
                    valueMax = 9;
                }

                if (hashMapDigit.containsKey("digitOk")) {
                    digitCompOk += 1;
                }

                // gagnant?
                if (digitCompOk == nbrDigit) {
                    System.out.println(" l'ordinateur a gagné");
                    System.exit(0);
                }

                logger.info("");
                logger.info("refine min = " + valueMinRefine);
                logger.info("refine max = " + valueMaxRefine);
                logger.info("");

                // j'affine le chiffre au besoin
                String digitRefined = mysteryNumber.computerNbrCombination(1, valueMinRefine, valueMaxRefine);
                logger.info("digit affiné = " + digitRefined);

                // je converti le digit affiné en Int
                Integer digitRefinedInt = Integer.parseInt(digitRefined);

                // j'increment de le compteur d'equal si ok
                if (digitRefinedInt == digitUser) {
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
            if (digitCompOk == nbrDigit) {
                System.out.println("l'ordinateur a gagné au " + (loopForDefenderMode + 1) + "eme essai !!");
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
                System.out.println("l'ordinateur a gagné au " + loopForDefenderMode + " essai !!");
            }

            //j'increment la boucle
            loopForDefenderMode++;
        }

    } while (loopForDefenderMode != nbrOfTry);

                System.out.println(" l'ordinateur n'a pas trouvé ta combinaison aprés " + nbrOfTry + " essais tu a donc gagné !!!");
                System.exit(0);
                */
}
