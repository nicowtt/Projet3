package com.ocr.nicolas;

import java.util.*;

public class Mastermind extends Games {


    protected boolean iswin;

    private int goodplaceExport;
    private int presentExport;

    public int getGoodplaceExport() {
        return goodplaceExport;
    }

    public int getPresentExport() {
        return presentExport;
    }

    Scanner sc = new Scanner(System.in);


    /**
     * For playing Mastermind Game
     */
    protected int playMastermind() {

        int replay = 3;

        // Affichage du menu du type de jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskTypeOfGame();

        // Recuperation variable du type de jeux
        int gameTypeChoice = display.displayGameTypeChoice();

        // objets
        MastermindChallenger mastermindChallenger = new MastermindChallenger();
        MastermindDefender mastermindDefender = new MastermindDefender();

        do {
            //do
            switch (gameTypeChoice) {
                case 1:
                    while (gameTypeChoice == 1) {
                        replay = mastermindChallenger.playChallengerModeMastermind();
                        if (replay == 1) {
                            display.displayAskTypeOfGame();
                            gameTypeChoice = display.displayGameTypeChoice();
                        }
                        break;
                    }
                case 2:
                    while (gameTypeChoice == 2) {
                        replay = mastermindDefender.playDefenderModeMastermind();
                        if (replay == 1) {
                            display.displayAskTypeOfGame();
                            gameTypeChoice = display.displayGameTypeChoice();
                        }

                        break;
                    }
                case 3:
                    while (gameTypeChoice == 3) {
                        break;
                    }
                default:
                    break;
                //} while (gameTypeChoice == 1 || gameTypeChoice == 2 || gameTypeChoice == 3);
            }
        } while (replay == 1);
        return replay;
    }


    /**
     * For create ArrayList with number ok in
     *
     * @return ArrayList (Integer)
     */
    protected List<Integer> createListNbrOk() {

        //creation d'une list avec les chiffres possible
        List<Integer> listNbrOk = new ArrayList<>();

        //je met les chiffres ok a l'interieur
        for (int i = 0; i < (nbrMaxOnDigit + 1); i++) {
            listNbrOk.add(i);
        }
        logger.info("liste chiffres ok = " + listNbrOk);

        return listNbrOk;
    }


    /**
     * For force user input combination ok
     *
     * @return user input combination ok (string)
     */

    protected String inputUserStringMast() {

        boolean responseIsGood = true;
        int digitOk;
        String inputUserFinal;

        // je lance la methode pour avoir une liste des chiffres utilisable
        List<Integer> listNbrOk = this.createListNbrOk();

        do {
            // entrée finale ok
            inputUserFinal = "";
            responseIsGood = true;

            // je fais venir l'entrée utilisateur
            String inputUser = sc.nextLine();

            // je compare chaque digit avec le tableau de chiffre ok sinon boucle
            int countTooManyDigit = 0;

            try {
                for (int i = 0; i < inputUser.length(); i++) {
                    char letter = inputUser.charAt(i);
                    String letterStr = String.valueOf(letter);
                    Integer letterInt = Integer.valueOf(letterStr);
                    // comparaison si il est dans la liste
                    for (int j = 0; j < listNbrOk.size(); j++) {
                        digitOk = listNbrOk.get(j);
                        if (digitOk == letterInt) {
                            inputUserFinal = inputUserFinal + letterStr;
                            countTooManyDigit++;
                        }
                    }
                }
                if (countTooManyDigit != nbrDigit) {
                    System.out.println("choisi une combinaison de " + nbrDigit + " chiffre (chiffre entre 0 et " + nbrMaxOnDigit + ")");
                    logger.info("mauvaise entrée utilisateur");
                    responseIsGood = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("lettre non accepté, tu doit choisir une combinaison de " + nbrDigit + " chiffre (chiffre entre 0 et " + nbrMaxOnDigit + ")");
                responseIsGood = false;
            }

        } while (!responseIsGood);
        logger.info("Entrée utilisateur = " + inputUserFinal);
        return inputUserFinal;
    }

    protected void compareTwoStringMast(String pFirst, String pSecond) {

        //Je crée deux ArrayList avec des booleans
        List<Boolean> listpFirstBoolean = new ArrayList<>();
        List<Boolean> listpSecondBoolean = new ArrayList<>();

        // je crée deux compteur
        int goodplace = 0; // les bien placés
        int present = 0; // les présents


        //je compare pour trouvé les bien placés
        for (int i = 0; i < pFirst.length(); i++) {
            if (pFirst.charAt(i) != pSecond.charAt(i)) {
                listpFirstBoolean.add(false);
                listpSecondBoolean.add(false);
            }
            if (pFirst.charAt(i) == pSecond.charAt(i)) {
                goodplace++;
                listpFirstBoolean.add(true);
                listpSecondBoolean.add(true);
            }
        }
        logger.info("******************************************************");
        logger.info("list pFirst boolean bien placé = " + listpFirstBoolean);
        logger.info("list pSecond boolean bien placé = " + listpSecondBoolean);

        // je compare une seconde fois en prenant en compte les bien placés
        for (int i = 0; i < pFirst.length(); i++) {
            for (int j = 0; j < pSecond.length(); j++) {
                if (!listpFirstBoolean.get(i) && !listpSecondBoolean.get(j) && pFirst.charAt(i) == pSecond.charAt(j)) {
                    present++;
                    // je change la valeur boolean si il trouve une comparaison ok
                    listpFirstBoolean.set(i, true);
                    listpSecondBoolean.set(j, true);
                }
            }
        }
        logger.info("list pFirst boolean present = " + listpFirstBoolean);
        logger.info("list pSecond boolean present = " + listpSecondBoolean);
        logger.info("******************************************************");

        goodplaceExport = goodplace;
        presentExport = present;
    }

    /**
     * For see if user is a winner
     *
     * @param pGoodPlaced Integer with goodPlaces in.
     */
    protected void seeUserWinner(int pGoodPlaced, int pPresent) {

        // j'affiche le resultat selon les précédentes valeurs
        if (pGoodPlaced == nbrDigit) {
            System.out.println("Félicitation tu as trouvé la combinaison !");
            System.out.println("");
            iswin = true;
        }
        if (pGoodPlaced == 0 && pPresent == 0) {
            System.out.println(" Aucun bien placé ou present.");
        } else {
            if (!iswin) {
                System.out.println(pGoodPlaced + " bien placé(s), " + pPresent + " present(s) ");
            }
        }
    }

    /**
     * For have a goodPlaced input with no cheat
     *
     * @param pFirst  string for mastermind compare
     * @param pSecond String for mastermind compare
     * @return user input (good placed) whith no cheat
     */
    protected int goodPlacedNoCheat(String pFirst, String pSecond) {

        boolean responseIsGood;
        int userInput = 0;

        do {
            try {
                userInput = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("chiffres uniquement.");
                responseIsGood = false;
            }

            // je check la bonne reponse
            logger.info("verification bien placé(s)");
            this.compareTwoStringMast(pFirst, pSecond);

            if (userInput != getGoodplaceExport()) {
                System.out.println("Chiffre bien placé non valable, erreur humaine ou tentative de tricherie? ;-)");
                responseIsGood = false;
            } else {
                responseIsGood = true;
            }

            // je regarde si l'utilisateur a gagné
            if (userInput == nbrDigit) {
                System.out.println("l'ordinateur a trouvé ta combinaison, tu as perdu :-(");
                System.out.println("");
                logger.info("l'ordinateur a trouvé la combinaison");
                this.replayMaster();
            }

        } while (!responseIsGood);
        return userInput;
    }


    /**
     * For have a present input with no cheat
     *
     * @param pFirst  string for mastermind compare
     * @param pSecond string for mastermind compare
     * @return user (present) input whith no cheat
     */
    protected int presentNoCheat(String pFirst, String pSecond) {

        boolean responseIsGood;
        int userInput = 0;

        do {
            try {
                userInput = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Chiffre uniquement");
                responseIsGood = false;
            }

            // je check la bonne réponse
            logger.info("verification présent(s)");
            this.compareTwoStringMast(pFirst, pSecond);
            if (userInput != getPresentExport()) {
                System.out.println("Chiffre présent non valable, erreur humaine ou tentative de tricherie? ;-)");
                responseIsGood = false;
            } else {
                responseIsGood = true;
            }

        } while (!responseIsGood);
        return userInput;
    }

    /**
     * for evaluate code on String of 2 digit ( highter is close of solution )
     * decade = number of goodplaced,  only one Digit = number of present;
     * (ex: for 4 digit and 4 figure possible -> 0,1,2,3,4,10,11,12,13,20,21,22,30,40
     */
    protected String codeValue(String pString) {

        String codeValueString = pString;

        // pour une reponse de comparaison (0 goodplace et 0 present)
        if (goodplaceExport == 0 && presentExport == 0) {
            codeValueString = "0";
        }

        // pour une reponse de comparaison uniquement avec des presents
        if (goodplaceExport == 0 && presentExport != 0) {
            codeValueString = String.valueOf(presentExport);
        }

        // pour une reponse de comparaison avec des bien placé(s) et pas de present
        if (goodplaceExport != 0 && presentExport == 0) {
            codeValueString = String.valueOf(goodplaceExport) + "0";
        }

        // pour une reponse de comparaison avec des bien placé(s) et des present(s)
        if (goodplaceExport != 0 && presentExport != 0) {
            codeValueString = String.valueOf(goodplaceExport) + String.valueOf(presentExport);
        }

        //logger.info("valeur code = " + codeValueString);

        return codeValueString;

    }

    /**
     * for make all combination possible with codeValueParameter
     */
    protected List<String> makeAllCombinationMastermind() {

        String combination = "";

        // je fabrique une array list avec toutes les combinaisons
        List<String> listCombinationTotal = new ArrayList<>();

//        // je compte le nombre de combinaison
//        int nbrCombinationTotal = findCombinationMax();
//        logger.info("la combinaison la plus haute = " + nbrCombinationTotal);


        // nombre de conbinaison totale
        int combTotal = (int) Math.pow((nbrMaxOnDigit + 1), nbrDigit);
        logger.info("nombre de combinaison totale = " + combTotal);
        // j'enleve 1 pour evité un nombre de digit au dessus de la normale
        combTotal -= 1;

        //je converti chaque nombre de combinaison totale en base (4 si nbrmax on digit = 4)
        do {
            combination = intToBase(combTotal, (nbrMaxOnDigit + 1));
            listCombinationTotal.add(combination);
            combTotal--;

        } while (combTotal > 0);

        // je rajoute le dernier manuellement (le zero)
        listCombinationTotal.add("0");

        // je rajoute les zeros a celle qui n'ont pas assez de digit
        String nbrDigitNok;
        String nbrWithGoodNbrOfDigit;
        for (int i = 0; i < listCombinationTotal.size(); i++) {
            nbrDigitNok = listCombinationTotal.get(i);
            nbrWithGoodNbrOfDigit = completeStringFollowingNbrDigit(nbrDigitNok);
            listCombinationTotal.set(i, nbrWithGoodNbrOfDigit);
        }

        logger.info("tableau des combinaison = " + listCombinationTotal);
        return listCombinationTotal;
    }


    /**
     * For convert integer on any base
     *
     * @param pEntier Integer
     * @param pBase   integer for convert base
     * @return integer on base "number"
     */
    public String intToBase(int pEntier, int pBase) {

        int nbr = pEntier;
        int rest;
        String resultTemp = "";
        String resultFinal = "";

        do {
            rest = nbr % pBase;
            resultTemp = resultTemp + rest;
            nbr = nbr / pBase;
        } while (nbr > 0);

        //j'inverse le string resultFinal
        int decompte = resultTemp.length() - 1;
        for (int i = decompte; i >= 0; i--) {
            char digit = resultTemp.charAt(i);
            resultFinal = resultFinal + digit;
        }

        return resultFinal;
    }

//    *
//     * For find combination max
//     * @return combination max
//
//    public Integer findCombinationMax() {
//
//        String nbrMaxStr = "";
//        String nbrMaxOnDigitStr = String.valueOf(nbrMaxOnDigit);
//
//        for (int i = 0; i < nbrDigit; i++) {
//            nbrMaxStr = nbrMaxStr + nbrMaxOnDigitStr;
//        }
//        Integer nbrMax = Integer.parseInt(nbrMaxStr);
//
//        logger.info("combinaison max = " + nbrMax);
//
//        return (nbrMax);
//    }

    /**
     * for complete combination for have good nbr Of Digit
     *
     * @param pIn integer in
     * @return String with good nbr of digit
     */
    public String completeStringFollowingNbrDigit(String pIn) {

        //variables
        String nbrStr = pIn;
        String nbrMaxStr = "1";
        String nbrFinalStr = "";

        // je trouve le chiffre minimum avec le nombre de digit
        for (int i = 0; i < (nbrDigit - 1); i++) {
            nbrMaxStr = nbrMaxStr + "0";
        }
        Integer nbrMax = Integer.parseInt(nbrMaxStr);

        // je complete les String avec pas assez de digit

        // je compte le nombre de digit
        int countNbrDigit = 0;

        for (int i = 0; i < nbrStr.length(); i++) {
            countNbrDigit++;
        }
        // il faut don rajouter combien de zero
        int nbrzero = nbrDigit - countNbrDigit;

        // je crée les zero manquant
        for (int i = 0; i < nbrzero; i++) {
            nbrFinalStr = nbrFinalStr + "0";
        }

        // je rajoute mon pIn
        nbrFinalStr = nbrFinalStr + nbrStr;

        return nbrFinalStr;
    }

    /**
     * For create a list with all possible value
     * exemple -> for nbrMaxOnDigit = 4 -> 0,1,2,3,4,10,11,12,13,20,21,22,30,40
     */
    public List<String> createListOfValue () {

        int nbr = 0;
        int nbrDecade = nbrMaxOnDigit;
        int maxDecade = 0;
        String digit = "";
        int countDecade = 1;


        //creation d'une arrayList avec toutes les valeurs possible
        List<String> listPossibleValues = new ArrayList<>();

        for (int i = 0; i < (nbrMaxOnDigit + 2); i++) { // pour le nombre max sur un digit
            listPossibleValues.add(String.valueOf(nbr));
            nbr++;
        }

        maxDecade = (listPossibleValues.size() - 1);
        int count1 = 0;

        do {
            for (int i = maxDecade ; i > 0; i--) { // pour les dizaines
                digit =  String.valueOf(countDecade);
                digit = digit + listPossibleValues.get(count1);
                listPossibleValues.add(digit);
                count1++;
            }
            countDecade++;
            maxDecade--;
            digit = "";
            count1 = 0;

        } while (maxDecade != 0);

        //je dois enlever l'avant dernier (si 3 bien placé, le dernier est forcement non present !!)
        int total = listPossibleValues.size();
        listPossibleValues.remove((total - 2));

        logger.info("liste value = " + listPossibleValues);

        return listPossibleValues;
        }



    /**
     *  For find optimal computer proposal
     * @param pList All combination
     * @param pValue value of last proposition
     * @return optimal combination
     */
    public void optimalCompProposal(String pValue) {

        //variables
        String proposal = "";
        String compare = "";
        String optimalCompProposal = "";

        //objet
        List<String> combinationTotale = new ArrayList<>();

        // je lance la methode pour avoir toutes les combinaisons
        combinationTotale = this.makeAllCombinationMastermind();

        // le lance la methode pour remplir la liste de valeur
        List<String> listValueTotal = this.createListOfValue();

        // todo je compare chaque combinaisons et je choisi celle qui a une valeur au dessus de la mienne

//        for (int i = 0; i < combinationTotale.size(); i++) { // pour chaque combinaison possible
//            proposal = combinationTotale.get(i);
//            // je la compare avec la proposition utilisateur pour avoir une echelle de valeur
//            compare = this.codeValue(proposal);
//            // je classe les comparaisons dans des listes
//            if (mapWithNbrOfCombinationPerValue.containsKey(compare)) {
//                mapWithNbrOfCombinationPerValue.
//            }

        }






    }













