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
     * Computer number of combination on String
     *
     * @param nbrDigit digit number(take on config.properties)
     */
    public String computerNbrCombination(int nbrDigit) {

        // random number computer
        String finalRandomDigitNumberString = "";
        int counterNbrDigit = nbrDigit;


        while (counterNbrDigit >= 1) {
            //nouvel objet
            Random rand = new Random();
            //int de 0 a 9 (base 10)
            int base10RandomDigitNumber = rand.nextInt(9);
            logger.info("base 10 random =" + base10RandomDigitNumber);
            //je converti le chiffre en string
            String base10RandomDigitNumberString = String.valueOf(base10RandomDigitNumber);
            //je l'ajoute au string computer final
            finalRandomDigitNumberString = finalRandomDigitNumberString + base10RandomDigitNumberString;
            //j'incremente le compteur
            counterNbrDigit--;
        }
        return finalRandomDigitNumberString;
    }


    public void CompareTwoString (String computeur, String user, int nbrDigit ) {
        //les compteurs
        int counterTotalLetter = (nbrDigit * 2) -1;
        int counterForMax = counterTotalLetter;
        int counterTotalLetterForLoop = counterTotalLetter + 1;
        int counterReverseLoop = nbrDigit * 2;
        int counterAddCompareList = (nbrDigit * 2) -1;
        int counterForResultList = nbrDigit;

        //les limites
        int minComputerOnBoxes = 0;
        int maxComputerOnBoxes = nbrDigit -1;
        int minUserOnBoxes = nbrDigit;
        int maxUserOnBoxes = (nbrDigit * 2) -1;

        //je joint les mots computeur et user
        String computerUserString = computeur + user;
        String computerUserStringLoop = computerUserString;
        // je crée une ArrayList pour comparer
        List<Integer> compareListinverted = new ArrayList<>();

        logger.info("nombre total = " + computerUserString);

        //Total des lettres en int -> dans un tableau ArrayList 0 = Computer, 1 = User etc:
        while (counterTotalLetterForLoop > 0 ) {
            char letter = computerUserString.charAt(counterTotalLetter);
            //je converti en String
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
        logger.info("tableau des chiffre (computeur en premier et user aprés) a l'endroit = " + compareList);

        //creation d'une liste pour le resultat final
        List<String> resultList = new ArrayList<String>();

        // je compare les chiffres dans cette liste
        do {
            int nbrComputeurForCompare = compareList.get(minComputerOnBoxes);
            int nbrUserForCompare = compareList.get(minUserOnBoxes);
            logger.info("Premier nombre computeur = " + minComputerOnBoxes);
            logger.info("Premier nombre user = " + minUserOnBoxes);

            if (nbrComputeurForCompare > nbrUserForCompare) {
                resultList.add("+");


            } else if (nbrComputeurForCompare < nbrUserForCompare) {
                resultList.add("-");

            } else {
                resultList.add("=");

            }
            counterForResultList--;
            minComputerOnBoxes++;
            minUserOnBoxes++;
        }while (counterForResultList != 0);

        logger.info("tableau de comparaison =" + resultList);

        //mise en place d'un counter de String "="
        int counterForWin = 0;

        //enregistrement dans la variabla afterCompare
        String afterCompare = "";

        int counterForLoopAfterCompare = 0;

        do {
            String OneString = resultList.get(counterForLoopAfterCompare);
            afterCompare = afterCompare + OneString;
            if (afterCompare.contains("=")) {
                counterForWin += 1;
            }
            counterForLoopAfterCompare++;
        } while (counterForLoopAfterCompare != nbrDigit);

        //j'exporte la variable finale
        afterCompareExport = afterCompare;
        logger.info("resultat final dans la class MysteryNumber = " + afterCompare);
        //j'exporte la variable du compteur pour voir si l'utilisateur a gagné
        counterForWinExport = counterForWin;
        logger.info("compteur pour voir si gagnant " + counterForWin + " doit etre egal au nombre de case: " + nbrDigit);













        }



    }

