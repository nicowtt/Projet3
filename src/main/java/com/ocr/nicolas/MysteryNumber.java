package com.ocr.nicolas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ocr.nicolas.Log4j.logger;

public class MysteryNumber {

    private int nbrDigit;
    private int counterForWinExport;

    //private valueMinRefine = 0;
    //private valueMaxRefine = 9;
    //private digitCompOk = 0;


    public void setNbrDigit(int nbrDigit) {
        this.nbrDigit = nbrDigit;
    }

    public int getCounterForWinExport() {
        return counterForWinExport;
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
            counterForWin = 1;
        }

        //j'exporte l'information pour savoir si il y a gagnant
        counterForWinExport = counterForWin;


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
     * for put information of digit on Hashmap
     *
     * @param str   String "5"
     * @param value String "+" or "-" or "="
     * @return hashmap updated with limit information
     */
    public Map<String, Integer> infoDigitForRefined(String str, String value) {


        String refinedMin = "refinedMin";
        String refinedMax = "refinedMax";
        String digitOk = "digitOk";


        Integer strDigit = Integer.valueOf(str);


        // creation d'un hashMap
        Map<String, Integer> digitHashMap = new HashMap<String, Integer>();


        // comparaison et entré dans la hashMap
        if (value.contains("+")) {
            digitHashMap.put(refinedMin, strDigit);
        }

        if (value.contains("-")) {
            digitHashMap.put(refinedMax, strDigit);
        }

        if (value.contains("=")) {
            digitHashMap.put(digitOk, strDigit);
        }
        logger.info("digit hashMap = " + digitHashMap);
        return digitHashMap;
    }

    /**
     * For create a hashMap with increment key (nbrdigit)
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
            completeHashMapBase.put(refinedMin,valueMin);

            String refinedMax = "refinedMax";
            refinedMax = refinedMax + nbrDigitList.get(j);
            completeHashMapBase.put(refinedMax,valueMax);

            String digitOk = "digitOk";
            digitOk = digitOk +  nbrDigitList.get(j);
            completeHashMapBase.put(digitOk,10);


            countDigit++;
        }
        return completeHashMapBase;

    }

    /**
     * for put information of each digit on Hashmap
     *
     * @param pHashMap  old hashMap
     *
     * @param pFirst
     * @param pValue
     * @return hashmap updated with new limit information
     */
    public Map<String, Integer> infoDigitForRefined2(Map<String, Integer> pHashMap, List<Integer> pFirst, List<String> pValue) {

        Map<String, Integer> hashMapRefine = new HashMap<>();

        for (int i = 0; i < pFirst.size(); i++) {
            if (pValue.contains("+")) {
                //hashMapRefine.
            }

        }
        return hashMapRefine;
    }

    /**
     * For convert String to Array List (value inside)
     *
     * @param pInputString String for convert (+--+)
     * @return List of String ( [+] [-] [=]
     */
    public List<String> valueStringToArrayList(String pInputString) {

        List<String> result = new ArrayList<>();

        for (int i = 0; i < pInputString.length(); i++) {
            char letter = pInputString.charAt(i);
            String letterString = String.valueOf(letter);
            result.add(letterString);
        }
        return result;

    }


}



