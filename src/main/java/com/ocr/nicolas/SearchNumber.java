package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class SearchNumber extends Common {

    Scanner sc = new Scanner(System.in);

    private boolean isWin; // Grâce aux methode ci-dessous je peux voir si il y a gagnant, donc je rajoute ce parametre

    public SearchNumber(int nbrDigit, int nbrOfTry, String developerMode, boolean isWin) {
        super(nbrDigit, nbrOfTry, developerMode);
        this.isWin = isWin;
    }

    public boolean getIsWin() {
        return isWin;
    }

    static final Logger logger = LogManager.getLogger();


    /**
     * For playing SearchNumber game
     *
     * @return playing
     */
    public void playSearchNumber() {

        // Affichage du menu du type de jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskTypeOfGame();

        // Recuperation variable du type de jeux
        int gameTypeChoice = display.displayGameTypeChoice();

        // objet searchNumber Challenger Mode
        SearchNumberChallenger searchNumberChallenger = new SearchNumberChallenger(getNbrDigit(), getNbrOfTry(), getDeveloperMode(), getIsWin());
        SearchNumberDefender searchNumberDefender = new SearchNumberDefender(getNbrDigit(), getNbrOfTry(), getDeveloperMode(), getIsWin());


        do
            switch (gameTypeChoice) {
                case 1:
                    while (gameTypeChoice == 1) {
                        searchNumberChallenger.playChallengerMode();
                        display.displayAskTypeOfGame();
                        gameTypeChoice = display.displayGameTypeChoice();
                        break;
                    }
                case 2:
                    while (gameTypeChoice == 2) {
                        searchNumberDefender.playDefenderMode();
                        display.displayAskTypeOfGame();
                        gameTypeChoice = display.displayGameTypeChoice();
                        break;
                    }
                case 3:
                    while (gameTypeChoice == 3) {
                        //searchNumberChallenger.playDuelMode();
                        break;
                    }
                default:
                    break;
            } while (gameTypeChoice == 1 || gameTypeChoice == 2 || gameTypeChoice == 3);
    }


    /**
     * for make random computer number(s) combination
     *
     * @param nbrDigit digit number(take on config.properties)
     * @return random digit number (type of character string)
     */
    public String computerNbrCombination(int nbrDigit, int min, int max) {

        String finalRandomDigitNumberString = "";
        int counterNbrDigit = nbrDigit;

        while (counterNbrDigit >= 1) {

            //int de 0 a 9 (base 10) avec min et max
            int base10RandomDigitNumber = min + (int) (Math.random() * ((max - min) + 1));
            //logger.info("base 10 random =" + base10RandomDigitNumber);

            // je converti le chiffre en string
            String base10RandomDigitNumberString = String.valueOf(base10RandomDigitNumber);

            // Je l'ajoute au string computer final
            finalRandomDigitNumberString = finalRandomDigitNumberString + base10RandomDigitNumberString;

            // j'incremente le compteur
            counterNbrDigit--;
        }
        return finalRandomDigitNumberString;
    }


    /**
     * For Compare 2 String (with number(s) inside) result -> (+--=) for exemple
     *
     * @param computer Computeur string (with number(s) inside)
     * @param user     User string (with number(s) inside)
     */
    public String CompareTwoString(String computer, String user) {


        // Je converti la chaine de caractere computeur en arraylist de integer -> computerArrayListInt
        List<Integer> computerArrayListInt = stringToArrayList(computer);

        // Je converti la chaine de caractere utilisateur en arraylist de integer -> userArrayListInt
        List<Integer> userArrayListInt = stringToArrayList(user);

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
    public String compareTwoArrayList(List<Integer> userArrayListInt, List<Integer> computerArrayListInt) {

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
     * For convert String to Array List (integer inside)
     *
     * @param inputString String for convert
     * @return List of integer
     */
    public List<Integer> stringToArrayList(String inputString) {

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < inputString.length(); i++) {
            char letter = inputString.charAt(i);
            String letterString = String.valueOf(letter);
            int letterInt = Integer.parseInt(letterString);
            result.add(letterInt);
        }
        return result;

    }

    /**
     * For convert String to Array List (String inside)
     *
     * @param inputStr String for convert
     * @return List of integer
     */
    public List<String> stringToArrayListString(String inputStr) {

        //creation nouvelle ArrayList
        List<String> result = new ArrayList<>();

        for (int i = 0; i < inputStr.length(); i++) {
            char letter = inputStr.charAt(i);
            String letterString = String.valueOf(letter);
            result.add(letterString);
        }
        return result;

    }


    /**
     * For create a hashMap with increment key (nbrdigit)
     *
     * @param pNbrDigit
     * @return hashMap with base infomation for each digit
     */
    public Map<String, Integer> createHashMapBase(int pNbrDigit) {

        int countDigit = 0;

        int valueMin = 0;
        int valueMax = 9;

        // creation d'une arrayList avec le nombre de digit en String
        List<String> nbrDigitList = new ArrayList<>();


        // creation d'un hashMap
        Map<String, Integer> completeHashMapBase = new HashMap<String, Integer>();

        //j'incremente le String refinedMin, max et digit ok (grâce à l'ArrayList nbr DigitList) et je le rajoute a la hashMap
        for (int j = 0; j < pNbrDigit; j++) {
            nbrDigitList.add(String.valueOf(countDigit));

            String refinedMin = "refinedMin";
            refinedMin = refinedMin + nbrDigitList.get(j);
            completeHashMapBase.put(refinedMin, valueMin);

            String refinedMax = "refinedMax";
            refinedMax = refinedMax + nbrDigitList.get(j);
            completeHashMapBase.put(refinedMax, valueMax);

            String digitOk = "digitOk";
            digitOk = digitOk + nbrDigitList.get(j);
            completeHashMapBase.put(digitOk, 10);


            countDigit++;
        }
        return completeHashMapBase;

    }

    /**
     * for put information of each digit on Hashmap
     *
     * @param pHashMap old hashMap
     * @param pFirst
     * @param pValue
     * @return hashmap updated with new limit information
     */
    public Map<String, Integer> infoDigitForRefinedToHahMap(Map<String, Integer> pHashMap, String pFirst, String pValue) {

        //variable locales
        int digitInt = 0;
        int digitListNewInt = 0;
        String digitString = "";
        String digitStringHashMap = "";
        String valueString = "";
        isWin = false;

        int digitMin = 0;
        int digitMax = 9;
        int digitMinRefine = 0;
        int digitMaxRefine = 9;
        int digitCompOk = 0;

        //je converti le string computeur en array list
        List<Integer> listCompInt = stringToArrayList(pFirst);

        //je converti le string user en array list
        List<String> listUserStringValues = stringToArrayListString(pValue);

        //creation d'une nouvelle hasMap refined
        Map<String, Integer> hashMapRefine = new HashMap<>();

        for (int i = 0; i < listCompInt.size(); i++) {
            digitListNewInt = listCompInt.get(i);
            valueString = listUserStringValues.get(i);

            if (valueString.contains("+")) {
                if (pHashMap.containsKey("refinedMin" + i)) {
                    digitInt = pHashMap.get("refinedMin" + i);
                    if (digitInt > digitMin) {
                        digitMinRefine = digitInt;
                        hashMapRefine.put("refinedMin" + i, digitMinRefine);
                    }
                    if (digitListNewInt > digitMinRefine) {
                        hashMapRefine.put("refinedMin" + i, digitListNewInt);
                    }
                    digitInt = 0;
                    digitListNewInt = 0;
                } else {
                    hashMapRefine.put("refinedMin" + i, 0);
                    digitInt = 0;
                }
            }
            if (valueString.contains("-")) {
                if (pHashMap.containsKey("refinedMax" + i)) {
                    digitInt = pHashMap.get("refinedMax" + i);
                    if (digitInt < digitMax) {
                        digitMinRefine = digitInt;
                        hashMapRefine.put("refinedMax" + i, digitMaxRefine);
                    }
                    if (digitListNewInt < digitMaxRefine) {
                        hashMapRefine.put("refinedMax" + i, digitListNewInt);
                    }
                    digitInt = 0;
                    digitListNewInt = 0;
                } else {
                    hashMapRefine.put("refinedMin" + i, 0);
                    digitInt = 0;
                }
            }
            if (valueString.contains("=")) {
                hashMapRefine.put("digitOk" + i, digitListNewInt);
                digitCompOk++;
            }
        }
        if (digitCompOk == getNbrDigit()) {
            isWin = true;
        }
        return hashMapRefine;
    }


    /**
     * For create a good string of value ("+-=")
     *
     * @return String "+-="
     */
    public String inputValueUserToString() {

        //creation compteur pour comparer au nombre de digit
        int count = 0;

        boolean responseIsGood;

        String resultString = "";

        do {
            // creation nouvelle ArrayList
            List<String> result = new ArrayList<>();

            //je recupere la valeur user
            String userValueString = sc.next();

            for (int i = 0; i < userValueString.length(); i++) {
                char letter = userValueString.charAt(i);
                String letterString = String.valueOf(letter);
                result.add(letterString);
                count++;
            }
            if (count < getNbrDigit() || count > getNbrDigit()) {
                System.out.println("vous avez rentré trop ou pas assez de valeur, veuillez re-essayer:");
                count = 0;
                responseIsGood = false;
            } else {
                responseIsGood = true;

                // je converti la array list en string
                for (int j = 0; j < userValueString.length(); j++) {
                    resultString = resultString + result.get(j);
                }
            }
        } while (responseIsGood == false);

        return resultString;
    }


    /**
     * for dichotomous Research
     * fonction de recherche dichotomique qui renvoie un indice où se trouve la valeur "val" si elle est dans le tableau "tab[]" et -1 si cette valeur n'y est pas
     *
     * @param pNbrMin min value
     * @param pNbrMax max value
     * @return
     */
    public int dichotomousResearch(int pNbrMin, int pNbrMax) {

        // declaration des variables locale à la methode
        int vMin;  // Min value
        int vMax;  // Max value
        int vMiddle;  // Middle value

        //initialisation de variables

        vMin = pNbrMin;  //intervale entre pNbrMin...
        vMax = pNbrMax;  //...et pNbrMax

        // recherche
        vMiddle = (vMin + vMax) / 2;  //on détermine le nombre entier au milieu

        logger.info("Valeur mediane trouvé dans la methode = " + vMiddle);

        return vMiddle;
    }


    /**
     * for create new HashMap with digit refined (dichotomous method)
     *
     * @param pHashMap
     * @param pValuesString
     * @return
     */
    public String hashMapRefined(Map<String, Integer> pHashMap, String pValuesString) {

        //variables
        int countOfWin = 0;
        int digitRefined = 0;
        String digitRefinedStr = "";
        isWin = false;

        //objet
        SearchNumber searchNumber = new SearchNumber(getNbrDigit(), getNbrOfTry(), getDeveloperMode(), getIsWin());
        SearchNumberDefender defender = new SearchNumberDefender(getNbrDigit(), getNbrOfTry(), getDeveloperMode(), getIsWin());

        // je converti le pString en ArrayList (creation)
        List<String> listStringValues = new ArrayList<>();
        for (int i = 0; i < getNbrDigit(); i++) {
            char letter = pValuesString.charAt(i);
            String letterStr = String.valueOf(letter);
            listStringValues.add(letterStr);
        }

        // pour chaque digit de l'Arraylist je compare avec la Valeur +- ou = et je renseigne la hashmapRefined
        for (int j = 0; j < listStringValues.size(); j++) {

            String value = listStringValues.get(j);

            if (value.contains("+")) {
                if (pHashMap.containsKey("refinedMin" + j)) {
                    int digitMin = pHashMap.get("refinedMin" + j);
                    // je lance une demande de valeur dicotomique avec digitMin
                    digitRefined = searchNumber.dichotomousResearch(digitMin, 9);
                    digitRefinedStr = digitRefinedStr + digitRefined;
                } else {
                    digitRefined = searchNumber.dichotomousResearch(0, 9);
                    digitRefinedStr = digitRefinedStr + digitRefined;
                }
            }

            if (value.contains("-")) {
                if (pHashMap.containsKey("refinedMax" + j)) {
                    int digitMax = pHashMap.get("refinedMax" + j);
                    // je lance une demande de valeur dicotomique avec digitMax
                    digitRefined = searchNumber.dichotomousResearch(0, digitMax);
                    digitRefinedStr = digitRefinedStr + digitRefined;
                } else {
                    digitRefined = searchNumber.dichotomousResearch(0, 9);
                    digitRefinedStr = digitRefinedStr + digitRefined;
                }
            }
            if (value.contains("=")) {
                countOfWin++;
                int digitOk = pHashMap.get("digitOk" + j);
                String digitOkStr = String.valueOf(digitOk);
                digitRefinedStr = digitRefinedStr + digitOkStr;
            }
        }
        if (countOfWin == getNbrDigit()) {
            isWin = true;
        }
        return digitRefinedStr;
    }

    /**
     * for make "5555" following nbrDigit on config.properties
     *
     * @return String with only "55..."
     */
    public String fiveOnlyDigit() {
        String str = "";
        for (int i = 0; i < getNbrDigit(); i++) {
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
    public boolean checkCheat(String pbase, String pcheck, String pvalue) {

        // variables
        boolean cheat = false;
        int countCheat = 0;

        // je converti chaque string en ArraList
        List<Integer> pbaseList = new ArrayList<>();
        List<Integer> pcheckList = new ArrayList<>();
        List<String> pvalueList = new ArrayList<>();

        // je met les string dans les Array List
        for (int i = 0; i < getNbrDigit(); i++) {
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
                    countCheat++;}}

            if (pvalueList.get(j).contains("+")) {
                if (pbaseList.get(j) <= pcheckList.get(j)) {
                    System.out.println(" Ooups le digit " + (j + 1) + " n'a pas été evalué correctement, triche ? ou erreur humaine ;o)? veuillez recommencer");
                    countCheat++;}}

            if (pvalueList.get(j).contains("-")) {
                if (pbaseList.get(j) >= pcheckList.get(j)) {
                    System.out.println(" Ooups le digit " + (j + 1) + " n'a pas été evalué correctement, triche ? ou erreur humaine ;o)? veuillez recommencer");
                    countCheat++;}}
        }
        if (countCheat > 0 ) {cheat = true;}
        else { cheat = false;}
        return cheat;
    }


    /**
     * for correct input value + no cheat
     */
    public String inputValuesUserAndCheckIfCheat (String puser,String pcomp) {
        // variable
        boolean cheakCheatInput;
        boolean cheakCheat;
        String valueUserInString;

        // objets
        SearchNumber searchNumber = new SearchNumber(getNbrDigit(),getNbrOfTry(),getDeveloperMode(),getIsWin());

        // boucle entré utilisateur valeur correcte
        do {
            valueUserInString = searchNumber.inputValueUserToString();
            logger.info("Valeur entré par l'utilisateur = " + valueUserInString);

            // check des valeurs (erreur ou tricherie)
            cheakCheatInput = searchNumber.checkCheat(puser,pcomp, valueUserInString);
            logger.info("utilisateur erreur ou triche ---> " + cheakCheatInput);

            // boucle si un digit mal evalué
            if (cheakCheatInput) {cheakCheat = true;}
                else {cheakCheat = false;}


        }while (cheakCheat);

        return valueUserInString;
    }


    /**
     * For redirection of replay or leave
     */
    public void replay() {

        // objets
        MenuDisplay display = new MenuDisplay();
        Games games = new Games();
        SearchNumber searchNumber = new SearchNumber(getNbrDigit(),getNbrOfTry(),getDeveloperMode(), getIsWin());

        // affichage console for replay et redirection
        display.displayAskIfReplay();
        int replayIntern = display.displayReplayChoice();
        if (replayIntern == 1) {searchNumber.playSearchNumber();}
        if (replayIntern == 2) {games.playGames();}
        if (replayIntern == 3) {System.exit(0);}
    }

}







