package com.ocr.nicolas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ocr.nicolas.Log4j.logger;

public class MysteryNumber {

    private int nbrDigit;
    private int counterForWinExport;
    private int refinedMinExport;
    private int refinedMaxExport;
    private int digitCompOkExport;
    private String digitOkExport;


    public void setNbrDigit(int nbrDigit) {this.nbrDigit = nbrDigit;}

    public int getCounterForWinExport() {return counterForWinExport;}
    public int getRefinedMinExport() {return refinedMinExport;}
    public int getRefinedMaxExport() {return refinedMaxExport;}
    public int getDigitCompOkExport() {return digitCompOkExport;}
    public String getDigitOkExport() {return digitOkExport;}

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
            logger.info("base 10 random =" + base10RandomDigitNumber);

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
     * @param user User string (with number(s) inside)
     */
    public String CompareTwoString (String computer, String user) {


        // Je converti la chaine de caractere computeur en arraylist de integer -> computerArrayListInt
        List<Integer> computerArrayListInt = stringToArrayList(computer);

        // Je converti la chaine de caractere utilisateur en arraylist de integer -> userArrayListInt
        List<Integer> userArrayListInt = stringToArrayList(user);

        // Je compare les deux arrayLists
        String compareListString = compareTwoArrayList(userArrayListInt, computerArrayListInt); //-> compareListString

        logger.info("resultat de la comparaison = " + compareListString);

        return compareListString;
    }




    /**
     * For compare Two Array List (with number(s) inside)
     *
     * @param userArrayListInt User Array List (number inside )
     * @param computerArrayListInt Computer Array List (number inside)
     * @return String with + , - or =
     */
    public String compareTwoArrayList(List<Integer> userArrayListInt, List<Integer> computerArrayListInt) {

        // mise en place d'un compteur de String "=" (indication si gagnant) et d'une variable (0 = perdant, 1= gagnant)
        int counterForSeeEgal = 0;
        int counterForWin = 0;

        // creation d'une nouvelle list pour les resultat (+--+)
        List<String>resultWithIndicationList = new ArrayList<>();

        for (int i = 0; i < computerArrayListInt.size(); i++) {
            int nbrComputerForCompare = computerArrayListInt.get(i);
            int nbrUserForCompare = userArrayListInt.get(i);

            if (nbrComputerForCompare > nbrUserForCompare) {
                resultWithIndicationList.add("+");
                // je prend l'information pour ajusté le min du prochain random computeur (Search number - mode defender)

                int refinedMin = userArrayListInt.get(i);
                // j'exporte la variable
                refinedMinExport = refinedMin;
                logger.info("passage par reajustement du minimum (mysteryNumber)= " + refinedMin);


            } else if (nbrComputerForCompare < nbrUserForCompare) {
                resultWithIndicationList.add("-");
                // je prend l'information pour ajusté le max du prochain random computeur (Search number - mode defender)
                int refinedMax = userArrayListInt.get(i);
                // j'exporte la variable
                refinedMaxExport = refinedMax;
                logger.info("passage par reajustement du maximum (mysteryNumber) = " + refinedMax);

            } else {
                resultWithIndicationList.add("=");
                counterForSeeEgal += 1; // pour savoir combien il y a d'egal lorsque la methode est lancé.
                int digitCompOk = computerArrayListInt.get(i);
                // j'exporte la variable
                digitCompOkExport = digitCompOk;

            }
        }

        // Si il a autant d'egal que de digit je signale qu'on a un gagnant
        if ( counterForSeeEgal == computerArrayListInt.size()) {counterForWin = 1;}

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
     * @param str String "54"
     * @param value String "+--="
     */
    public Map<String, Integer> infoDigitForRefined (String str, String value) {

        String refinedMin = "refinedMin";
        String refinedMax = "refinedMax";
        String digitOk = "digitOk";
        Integer strDigit = Integer.valueOf(str);


        // creation d'un hashMap
        Map<String, Integer> digitHashMap = new HashMap<String, Integer>();


        // comparaison et entré dans la hashMap
        if (value.contains("+") ) {
            digitHashMap.put(refinedMin,strDigit);
        }

        if (value.contains("-")) {
            digitHashMap.put(refinedMax,strDigit);
        }

        if (value.contains("=")) {
            digitHashMap.put(digitOk,strDigit);
        }
        logger.info("digit hashMap = " + digitHashMap);
        return digitHashMap;
    }


    /**
     * for create a random digit with refined
     *
     * @param list hashMap with information on digit
     * @param digit one int
     * @return int refined
     */
    public String digitWithRefined (Map<String, Integer> list, String digit) {

        int digitRefined = Integer.valueOf(digit);

        int refineNumberMin;
        int refineNumberMax;
        int digitOk = 0;
        int refineNumberMinAndMax;



        if (list.containsKey("refinedMin") && list.containsKey("refinedMax")) {
            refineNumberMinAndMax = list.get("refinedMin") + (int) (Math.random() * (((list.get("refinedMax")) - (list.get("refinedMin"))) + 1));
            digitRefined = refineNumberMinAndMax;
            logger.info("passage par refineNumber Min et Max, nouveau nombre aleatoire = " + digitRefined);
        } else {
            if (list.containsKey("refinedMin")) {
                refineNumberMin = list.get("refinedMin") + (int) (Math.random() * ((9 - list.get("refinedMin")) + 1));
                digitRefined = refineNumberMin;
                logger.info("passage par refineNumber Min, nouveau nombre aleatoire = " + digitRefined);
            }
            if (list.containsKey("refinedMax")) {
                refineNumberMax = (int) (Math.random() * ((list.get("refinedMax") + 1)));
                digitRefined = refineNumberMax;
                logger.info("passage par refineNumber Max, nouveau nombre aleatoire = " + digitRefined);
            }
            if (list.containsKey("digitOk")) {
                digitOk = list.get("digitOk");
                logger.info("passage par digit ok = " );
            }
        }


        // je met le digit en string
        String digitRefinedString = String.valueOf(digitRefined);

        // je converti en string
        String digitOkString = String.valueOf(digitOk);
        digitOkExport = digitOkString;

        return digitRefinedString;
    }


}

