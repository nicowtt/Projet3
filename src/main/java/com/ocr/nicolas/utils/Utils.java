package com.ocr.nicolas.utils;

import com.ocr.nicolas.SearchNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Utils {

    static final Logger logger = LogManager.getLogger();


    /**
     * For convert String to Array List (integer inside)
     *
     * @param inputString String for convert
     * @return List of integer
     */
    public static List<Integer> stringToArrayList(String inputString) {

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
    public static List<String> stringToArrayListString(String inputStr) {

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
    public static Map<String, Integer> createHashMapBase(int pNbrDigit) {

        int countDigit = 0;
        int valueMin = 0;
        int valueMax = 10;

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
     * for dichotomous number
     *
     * @param pNbrMin min value
     * @param pNbrMax max value
     * @return
     */
    public static int dichotomousResearch(int pNbrMin, int pNbrMax) {

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
        logger.info("valeur min" + vMin);
        logger.info("valeur max" + vMax);

        return vMiddle;
    }
}
