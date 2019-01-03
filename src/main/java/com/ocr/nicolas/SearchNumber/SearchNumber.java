package com.ocr.nicolas.SearchNumber;

import com.ocr.nicolas.Config;
import com.ocr.nicolas.Games;
import com.ocr.nicolas.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public abstract class SearchNumber extends Games {

    protected boolean isWin; // Grâce aux methode ci-dessous je peux voir si il y a gagnant, donc je rajoute ce parametre

    Scanner sc = new Scanner(System.in);

    static final Logger logger = LogManager.getLogger();

    public SearchNumber(Config config) {
        super(config);
    }

    /**
     * For Compare 2 String (with number(s) inside) result -> (+--=) for exemple
     *
     * @param computer Computeur string (with number(s) inside)
     * @param user     User string (with number(s) inside)
     */
    protected String CompareTwoString(String computer, String user) {


        // Je converti la chaine de caractere computeur en arraylist de integer -> computerArrayListInt
        List<Integer> computerArrayListInt = Utils.stringToArrayList(computer);

        // Je converti la chaine de caractere utilisateur en arraylist de integer -> userArrayListInt
        List<Integer> userArrayListInt = Utils.stringToArrayList(user);

        // Je compare les deux arrayLists
        String compareListString = compareTwoArrayList(userArrayListInt, computerArrayListInt); //-> compareListString

        //logger.info("resultat de la comparaison = " + compareListString);

        return compareListString;
    }


    /**
     * For compare Two Array List (with number(s) inside)
     *
     * @param userArrayListInt     User Array List (number inside )
     * @param computerArrayListInt Computer Array List (number inside)
     * @return String with + , - or =
     */
    protected String compareTwoArrayList(List<Integer> userArrayListInt, List<Integer> computerArrayListInt) {

        //variable gagnante a zero
        isWin = false;

        // mise en place d'un compteur de String "=" (indication si gagnant) et d'une variable (0 = perdant, 1= gagnant)
        int counterForSeeEgal = 0;
        int counterForWin = 0;

        // creation d'une nouvelle list pour les resultat (+--+)
        List<String> resultWithIndicationList = new ArrayList<>();

        for (int i = 0; i < computerArrayListInt.size(); i++) {
            int nbrComputerForCompare = computerArrayListInt.get(i);
            int nbrUserForCompare = userArrayListInt.get(i);

            if (nbrComputerForCompare > nbrUserForCompare) {
                resultWithIndicationList.add("+");
            } else if (nbrComputerForCompare < nbrUserForCompare) {
                resultWithIndicationList.add("-");
            } else {
                resultWithIndicationList.add("=");
                counterForSeeEgal += 1; // pour savoir combien il y a d'egal lorsque la methode est lancé.
            }
        }

        // Si il a autant d'egal que de digit je signale qu'on a un gagnant
        if (counterForSeeEgal == computerArrayListInt.size()) {
            isWin = true;
        }

        // je converti cette liste en string
        String compareListString = "";

        for (int i = 0; i < computerArrayListInt.size(); i++) {
            String oneString = resultWithIndicationList.get(i);
            compareListString = compareListString + oneString;
        }
        return compareListString;
    }


    /**
     * for put information of each digit on Hashmap
     *
     * @param pHashMap old hashMap
     * @param pFirst
     * @param pValue
     * @return hashmap updated with new limit information
     */
    protected Map<String, Integer> infoDigitForRefinedToHahMap(Map<String, Integer> pHashMap, String pFirst, String pValue) {

        //variable locales
        int digitInt;
        int digitListNewInt;
        String valueString;
        isWin = false;

        int digitMin = 0;
        int digitMax = 10;
        int digitMinRefine;
        int digitMaxRefine;
        int digitCompOk = 0;

        //je converti le string computeur en array list
        List<Integer> listCompInt = Utils.stringToArrayList(pFirst);
        logger.info("list computeur = " + listCompInt);

        //je converti le string user en array list
        List<String> listUserStringValues = Utils.stringToArrayListString(pValue);
        logger.info("list user = " + listUserStringValues);

        for (int i = 0; i < listCompInt.size(); i++) {
            digitListNewInt = listCompInt.get(i);
            valueString = listUserStringValues.get(i);
            digitMinRefine = 0;
            digitMaxRefine = 10;

            if (valueString.contains("+")) {
                if (pHashMap.containsKey("refinedMin" + i)) {
                    digitInt = pHashMap.get("refinedMin" + i);
                    if (digitInt > digitMin) {
                        digitMinRefine = digitInt;
                        pHashMap.put("refinedMin" + i, digitMinRefine);
                    }
                    if (digitListNewInt > digitMinRefine) {
                        pHashMap.put("refinedMin" + i, digitListNewInt);
                    }
                }
            }
            if (valueString.contains("-")) {
                if (pHashMap.containsKey("refinedMax" + i)) {
                    digitInt = pHashMap.get("refinedMax" + i);
                    if (digitInt < digitMax) {
                        digitMaxRefine = digitInt;
                        pHashMap.put("refinedMax" + i, digitMaxRefine);
                    }
                    if (digitListNewInt < digitMaxRefine) {
                        pHashMap.put("refinedMax" + i, digitListNewInt);
                    }
                }
            }
            if (valueString.contains("=")) {
                pHashMap.put("digitOk" + i, listCompInt.get(i));
                digitCompOk++;
            }
        }
        if (digitCompOk == nbrDigit) {
            isWin = true;
        }
        return pHashMap;
    }

    /**
     * For create a good string of value ("+-=")
     *
     * @return String "+-="
     */
    protected String inputValueUserToString() {
        int count = 0;
        String resultString = "";

        boolean responseIsGood;
        do {
            boolean testIfInt = false;
            responseIsGood = false;
            boolean nbrValue = true;

            // creation nouvelle ArrayList
            List<String> result = new ArrayList<>();

            do {
                //je recupere la valeur user
                String userValueString = sc.next();

                //creation compteur pour comparer au nombre de digit
                count = 0;

                for (int i = 0; i < userValueString.length(); i++) {
                    char letter = userValueString.charAt(i);
                    // je verifie si il y a pas un chiffre dans cette valeur
                    testIfInt = Character.isDigit(letter);
                    if (testIfInt) {
                        System.out.println(" Ooups chiffres non autorisé, re-essaye");
                        count = nbrDigit;
                        responseIsGood = false;
                        break;
                    }
                    String letterString = String.valueOf(letter);
                    // je verifie si la lettre correspond a "+" "-" ou "="
                    if (letterString.contains("+") || letterString.contains("-") || letterString.contains("=")) {
                        result.add(letterString);
                        count++;
                    } else {
                        System.out.println("Valeur(s) +,- ou = seulement");
                        responseIsGood = false;
                        testIfInt = true;
                    }
                }
            } while (testIfInt);

            if (!testIfInt) {
                if (count < nbrDigit || count > nbrDigit) {
                    System.out.println("vous avez rentré trop ou pas assez de valeur, re-essaye:");
                    nbrValue = true;
                    responseIsGood = false;
                } else {
                    nbrValue = false;
                }
            }
            // je converti la array list en string
            if (!nbrValue)
                for (int j = 0; j < nbrDigit; j++) {
                    resultString = resultString + result.get(j);
                    responseIsGood = true;
                }
        } while (!responseIsGood);
        return resultString;
    }


    /**
     * for create new HashMap with digit refined (dichotomous method)
     *
     * @param pHashMap
     * @param pValuesString
     * @return
     */
    protected String hasmapToDicotomousString(Map<String, Integer> pHashMap, String pfirst, String pValuesString, String pStrToComparForWin) {

        //variables
        isWin = false;
        int digit;
        int digitMin;
        int digitMax;
        String digitStr;
        String digitFinal = "";


        // je lance la methode pour renseigner la hashMap avec les nouvelles valeurs
        Map<String, Integer> hashMapUpdated = this.infoDigitForRefinedToHahMap(pHashMap, pfirst, pValuesString);
        logger.info(" 2 eme renseignement hashmap = " + hashMapUpdated);

        // pour chaque digit je crée un nouveau digit avec la methode dicotomous
        for (int i = 0; i < nbrDigit; i++) {
            digitMin = hashMapUpdated.get("refinedMin" + i);
            digitMax = hashMapUpdated.get("refinedMax" + i);
            digit = Utils.dichotomousResearch(digitMin, digitMax);
            digitStr = String.valueOf(digit);
            digitFinal = digitFinal + digitStr;
        }
        return digitFinal;
    }

    /**
     * for make "5555" following nbrDigit on config.properties
     *
     * @return String with only "55..."
     */
    protected String fiveOnlyDigit() {
        String str = "";
        for (int i = 0; i < nbrDigit; i++) {
            str = str + 5;
        }
        return str;
    }

    /**
     * For check if user Cheat
     *
     * @param pbase  nbr user
     * @param pcheck nbr comp
     * @param pvalue value "+-=" user in
     */
    protected boolean checkCheat(String pbase, String pcheck, String pvalue) {

        // variables
        boolean cheat = false;
        int countCheat = 0;

        // je converti chaque string en ArraList
        List<Integer> pbaseList = new ArrayList<>();
        List<Integer> pcheckList = new ArrayList<>();
        List<String> pvalueList = new ArrayList<>();

        // je met les string dans les Array List
        for (int i = 0; i < nbrDigit; i++) {
            // pour pbase
            char letter = pbase.charAt(i);
            String letterString = String.valueOf(letter);
            Integer letterInt = Integer.parseInt(letterString);
            pbaseList.add(letterInt);
            // pour pcheck
            char letter2 = pcheck.charAt(i);
            String letterString2 = String.valueOf(letter2);
            Integer letterInt2 = Integer.parseInt(letterString2);
            pcheckList.add(letterInt2);
            // pour pvalue
            char letter3 = pvalue.charAt(i);
            String letterString3 = String.valueOf(letter3);
            pvalueList.add(letterString3);
        }

        // je peux comparer chaque digit
        for (int j = 0; j < pbase.length(); j++) {
            if (pvalueList.get(j).contains("=")) {
                if (pbaseList.get(j) != pcheckList.get(j)) {
                    System.out.println(" Ooups le digit " + (j + 1) + " n'a pas été evalué correctement, triche ? ou erreur ;o)? veuillez recommencer");
                    countCheat++;
                }
            }

            if (pvalueList.get(j).contains("+")) {
                if (pbaseList.get(j) <= pcheckList.get(j)) {
                    System.out.println(" Ooups le digit " + (j + 1) + " n'a pas été evalué correctement, triche ? ou erreur humaine ;o)? veuillez recommencer");
                    countCheat++;
                }
            }

            if (pvalueList.get(j).contains("-")) {
                if (pbaseList.get(j) >= pcheckList.get(j)) {
                    System.out.println(" Ooups le digit " + (j + 1) + " n'a pas été evalué correctement, triche ? ou erreur humaine ;o)? veuillez recommencer");
                    countCheat++;
                }
            }
        }
        if (countCheat > 0) {
            cheat = true;
        } else {
            cheat = false;
        }
        return cheat;
    }


    /**
     * for correct input value + no cheat
     */
    protected String inputValuesUserAndCheckIfCheat(String puser, String pcomp, int ploop, int pinverseLoop) {

        // variable
        boolean cheakCheatInput;
        boolean cheakCheat;
        String valueUserInString;
        isWin = false;

        // boucle entré utilisateur valeur correcte
        do {
            valueUserInString = this.inputValueUserToString();
            logger.info("Valeur entré par l'utilisateur = " + valueUserInString);

            // check des valeurs (erreur ou tricherie)
            cheakCheatInput = this.checkCheat(puser, pcomp, valueUserInString);
            logger.info("utilisateur erreur ou triche ---> " + cheakCheatInput);

            // boucle si un digit mal evalué
            if (cheakCheatInput) {
                cheakCheat = true;
            } else {
                cheakCheat = false;
            }

        } while (cheakCheat);

        // comparaison pour voir si computeur gagne
        if (pcomp.contains(puser)) {
            System.out.println(" l'ordinateur a trouvé ta combinaison en " + ploop + " coup(s)");
            System.out.println("");
            logger.info("L'ordinateur a gagné en " + ploop + " coup(s)");
            // j'annonce le gagnant
            isWin = true;
        }

        if (nbrOfTry >= 1 && pinverseLoop == 0 && !isWin) {
            System.out.println(" l'ordinateur n'a plus d'essai non plus, personne ne gagne :-( !");
            System.out.println("");
            isWin = true;
        }
        // arrêt si plus de nombre d'essai
        if (nbrOfTry == 1 && !isWin) {
            System.out.println(" l'ordinateur n'a plus d'essai, tu gagne !");
            System.out.println("");
            isWin = true;

        }
        return valueUserInString;
    }


    protected int testIfUserWinChallengerMode(int ploop, String pcomp, String puser, int pinverseLoop) {

        //variables
        int replay = 3;
        isWin = false;

        if (pcomp.contains(puser)) {
            System.out.println(" Exellent tu as gagné !!! en " + ploop + " coup(s).");
            isWin = true;
            logger.info("l'utilisateur a gagné contre l'ordinateur aprés " + ploop + " essais");
            System.out.println("");

            // je renvoi la valeur pour remonter (et pour rejouer?)
            isWin = true;

        } else {
            if (nbrOfTry == 1 || pinverseLoop == 0) {
                System.out.println("");
            }
            if (nbrOfTry > 1 && pinverseLoop > 0) {
                System.out.println(" -> ce n'est pas la bonne combinaison !");
            }
        }
        return replay;
    }

}







