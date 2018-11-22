package com.ocr.nicolas;

import java.util.ArrayList;
import java.util.List;

import static com.ocr.nicolas.Log4j.logger;

public class MysteryNumber {

    private int nbrDigit;
    private int counterForWinExport;
    private int refinedMinExport;
    private int refinedMaxExport;
    private int digitCompOkExport;



    public void setNbrDigit(int nbrDigit) {this.nbrDigit = nbrDigit;}

    public int getCounterForWinExport() {return counterForWinExport;}
    public int getRefinedMinExport() {return refinedMinExport;}
    public int getRefinedMaxExport() {return refinedMaxExport;}
    public int getDigitCompOkExport() {return digitCompOkExport;}

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

    public void addInfoHashMapDigit () {

    }

}

