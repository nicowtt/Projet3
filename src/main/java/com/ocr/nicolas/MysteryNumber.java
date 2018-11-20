package com.ocr.nicolas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.ocr.nicolas.Log4j.logger;

public class MysteryNumber {

    private int nbrDigit;
    private String afterCompareExport;
    private int counterForWinExport;


    public void setNbrDigit(int nbrDigit) { this.nbrDigit = nbrDigit; }
    public String getAfterCompareExport() { return afterCompareExport; }
    public int getCounterForWinExport() { return counterForWinExport; }


    /**
     * for make random computer number(s) combination
     *
     * @param nbrDigit digit number(take on config.properties)
     * @return random digit number (type of character string)
     */
    public String computerNbrCombination(int nbrDigit) {

        String finalRandomDigitNumberString = "";
        int counterNbrDigit = nbrDigit;

        while (counterNbrDigit >= 1) {
            // Creation d'un nouvel objet pour l'aléatoire
            Random rand = new Random();

            //int de 0 a 9 (base 10)
            int base10RandomDigitNumber = rand.nextInt(9);
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
     * For Compare 2 String (with number(s) inside)
     *
     * @param computeur Computeur string (with number(s) inside)
     * @param user User string (with number(s) inside)
     * @param nbrDigit number of boxes
     */
    public void CompareTwoString (String computeur, String user, int nbrDigit ) {


        // Je converti la chaine de caractere computeur en arraylist de integer -> computerArrayListInt
        List<Integer> computerArrayListInt = stringToArrayList(computeur);

        // Je converti la chaine de caractere utilisateur en arraylist de integer -> userArrayListInt
        List<Integer> userArrayListInt = stringToArrayList(user);

        // Je compare les deux arrayLists
        String compareListString = compareTwoArrayList(userArrayListInt, computerArrayListInt, nbrDigit); //-> compareListString

        // J'exporte la variable de comparaison vers la class main
        afterCompareExport = compareListString;

        logger.info("resultat de la comparaison = " + compareListString);
        }


    /**
     * For compare Two Array List (with number(s) inside)
     *
     * @param userArrayListInt User Array List (number inside )
     * @param computerArrayListInt Computer Array List (number inside)
     * @param nbrDigit number of boxes
     * @return String with + , - or =
     */
    private String compareTwoArrayList(List<Integer> userArrayListInt, List<Integer> computerArrayListInt, Integer nbrDigit) {

        // mise en place d'un compteur de String "=" (indication si gagnant) et d'une variable (0 = perdant, 1= gagnant)
        int counterForSeeEgal = 0;
        int counterForWin = 0;

        // creation d'une nouvelle list pour les resultat (+--+)
        List<String>resultWithIndicationList = new ArrayList<>();

        for (int i = 0; i < nbrDigit; i++) {
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
        if ( counterForSeeEgal == nbrDigit) {counterForWin = 1;}

        //j'exporte l'information du counterForWin
        counterForWinExport = counterForWin;

        // je converti cette liste en string
        String compareListString = "";

        for (int i = 0; i < nbrDigit; i++) {
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
    private List<Integer> stringToArrayList(String inputString) {

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < inputString.length(); i++) {
            char letter = inputString.charAt(i);
            String letterString = String.valueOf(letter);
            int letterInt = Integer.parseInt(letterString);
            result.add(letterInt);
        }
        return result;

    }
}

