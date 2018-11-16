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
     * Random computer number(s) combination (String)
     *
     * @param nbrDigit digit number(take on config.properties)
     */
    public String computerNbrCombination(int nbrDigit) {

        // random number computer
        String finalRandomDigitNumberString = "";
        int counterNbrDigit = nbrDigit;


        while (counterNbrDigit >= 1) {
            // nouvel objet
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

        // les compteurs:
        int counterTotalLetter = (nbrDigit * 2) -1;
        int counterForMax = counterTotalLetter;
        int counterTotalLetterForLoop = counterTotalLetter + 1;
        int counterReverseLoop = nbrDigit * 2;
        int counterAddCompareList = (nbrDigit * 2) -1;
        int counterForResultList = nbrDigit;

        // les limites:
        int minComputerOnBoxes = 0;
        int minUserOnBoxes = nbrDigit;


        // je joint les mots computeur et user:
        String computerUserString = computeur + user;
        String computerUserStringLoop = computerUserString;

        // creation d'une ArrayList pour comparer
        List<Integer> compareListinverted = new ArrayList<>();

        logger.info("nombre total (String) = " + computerUserString);

        // avec le mot "computer" + "user" collé je converti les lettres en nombre  une par une -> dans un tableau ArrayList
        while (counterTotalLetterForLoop > 0 ) {
            char letter = computerUserString.charAt(counterTotalLetter);

            // je converti en String
            String letterString = String.valueOf(letter);

            //je converti en Int
            int letterInt = Integer.parseInt(letterString);

            //j'ajoute le chifre dans le tableau
            compareListinverted.add(letterInt);

            //j'increment le compteur total negativement
            counterTotalLetter --;
            counterTotalLetterForLoop--;

            //j'enleve la premiere lettre du mot total
            computerUserString = computerUserStringLoop.substring(0,counterForMax);
            counterForMax--;
        }

        // je crée une autre liste pour mettre les chiffres dans le bon ordre -> compareList
        List<Integer> compareList = new ArrayList<>();
        while (counterReverseLoop != 0) {
            compareList.add(compareListinverted.get(counterAddCompareList));
            counterReverseLoop--;
            counterAddCompareList--;
        }
        logger.info("tableau des chiffres (computeur en premier et user aprés) a l'endroit = " + compareList);

        // creation d'une liste pour le resultat final (indication + et -)
        List<String> resultList = new ArrayList<String>();

        // mise en place d'un compteur de String "=" (indication si gagnant) et d'une variable (0 = perdant, 1= gagnant)
        int counterForSeeEgal = 0;
        int counterForWin = 0;

        // je compare les chiffres de la derniere liste et j'ajoute les indication + , = ou - dans une autre liste -> resultList
        do {
            int nbrComputeurForCompare = compareList.get(minComputerOnBoxes);
            int nbrUserForCompare = compareList.get(minUserOnBoxes);

            if (nbrComputeurForCompare > nbrUserForCompare) {
                resultList.add("+");


            } else if (nbrComputeurForCompare < nbrUserForCompare) {
                resultList.add("-");

            } else {
                resultList.add("=");
                counterForSeeEgal += 1; // a chaque passage de digit le compteur incremente (je vais savoir combien il y a d'egal lorsque la methode est lancé
                }

            counterForResultList--;
            minComputerOnBoxes++;
            minUserOnBoxes++;
        }while (counterForResultList != 0);
        logger.info("tableau de comparaison =" + resultList);
        logger.info("nombre d'egal dans la comparaison class MysteryNumber = " + counterForSeeEgal);

        // Si il a autant d'egal que de digit je signale qu'on a un gagnant
        if ( counterForSeeEgal == nbrDigit) {
            counterForWin = 1;
        }

        // Je met les signes (+, - ou =) dans la variable -> afterCompare
        String afterCompare = "";

        int counterForLoopAfterCompare = 0;

        do {
            String OneString = resultList.get(counterForLoopAfterCompare);
            afterCompare = afterCompare + OneString;

            counterForLoopAfterCompare++;
        } while (counterForLoopAfterCompare != nbrDigit);



        // J'exporte la variable finale
        afterCompareExport = afterCompare;
        logger.info("resultat final dans la class MysteryNumber = " + afterCompare);

        // j'exporte aussi la variable du compteur pour voir si l'utilisateur a gagné
        counterForWinExport = counterForWin;
        logger.info("compteur pour voir si gagnant (1 = gagné) -> " + counterForWin);













        }



    }

