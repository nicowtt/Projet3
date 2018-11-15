package com.ocr.nicolas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.ocr.nicolas.Log4j.logger;

public class SearchNumber {

    int NbrBoxesCombinationSearchNumber;
    int nbrDigitComputeur;
    int nbrDigitUser;

    String afterCompareExport;


    public void setNbrCombinationSearchNumber(int nbrCombinationSearchNumber) {
        this.NbrBoxesCombinationSearchNumber = NbrBoxesCombinationSearchNumber;
    }

    public int getNbrBoxesCombinationSearchNumber() {
        return NbrBoxesCombinationSearchNumber;
    }

    public int getNbrDigitUser() {
        return nbrDigitUser;
    }

    public String getAfterCompareExport() {
        return afterCompareExport;
    }



    /**
     * Computer number of combination
     *
     * @param nbrDigit digit number(take on config.properties)
     */
    public int computerNbrCombination(int nbrDigit) {
        String finalRandomDigitNumberString = "";
        int randomNumber = 0;

        do {
            int counterNbrDigit = nbrDigit;
            finalRandomDigitNumberString = "";
            while (counterNbrDigit >= 1) {
                //nouvel objet
                Random rand = new Random();
                //int de 0 a 9 (base 10)
                int base10RandomDigitNumber = rand.nextInt(9);
                logger.info("base 10 random =" + base10RandomDigitNumber);
                //je converti en String pour arriver a les ajouter sans addition
                String randomDigitNumberString = Integer.toString(base10RandomDigitNumber);
                //je l'ajoute a la variable String
                finalRandomDigitNumberString = finalRandomDigitNumberString + randomDigitNumberString;
                //j'incremente négativement nbrDigit
                counterNbrDigit--;
            }
            //je reconverti le chiffre final en int
            int randomNumberInt = Integer.parseInt(finalRandomDigitNumberString);
            logger.info("randomNumber = " + finalRandomDigitNumberString);
            randomNumber = randomNumberInt;

        }while (randomNumber < 1000 );

        return randomNumber;

    }


    /**
     * find number of digit
     *
     * @param number number on config.properties
     * @return digit number
     */
    public int FindNbrDigit(int number) {
        int result = number;
        int nbrDigit = 0;

        while (result >= 1) {
            result = result / 10;
            nbrDigit++;
        }
        return nbrDigit;
    }

    /**
     * put number on ArrayList
     *
     * @param nbrRandom        number random computer
     * @param nbrUser          number user of game
     * @param nbrDigitComputer number of time this loop while will be running
     * @param nbrDigitUser     number of digit on user number
     */
    public void combinationOnBoard(int nbrRandom, int nbrUser, int nbrDigitComputer, int nbrDigitUser) {

        //je met le nombre choisi par l'utilisateur a la suite du nombre choisi par l'ordinateur

        String nbrRandomString = Integer.toString(nbrRandom);
        String nbrUserString = Integer.toString(nbrUser);
        String nbrUserAndRandomString = nbrRandomString + nbrUserString;
        Integer nbrUserAndRandomInt = Integer.parseInt(nbrUserAndRandomString);
        logger.info("ajout des nombres sans addition = " + nbrUserAndRandomInt);

        //je compte combien il y a de digit en tous (ordinateur + user)
        logger.info("nombre choisi par l'utilisateur = " + nbrUser);
        int nbrDigitTotal = FindNbrDigit(nbrUserAndRandomInt);

        //je fais un tableau global

        int i = nbrDigitTotal;
        int digit = 0;
        int k = 0;
        int l = (nbrDigitTotal - 1);

        List<Integer> combinationOnBoardInverted = new ArrayList<Integer>();
        while (i > 0) {
            digit = nbrUserAndRandomInt % 10;
            i--;
            nbrUserAndRandomInt = nbrUserAndRandomInt / 10;
            combinationOnBoardInverted.add(digit);
        }
        //creation d'une nouvelle ArrayList en inversant les chiffres
        List<Integer> combinationOnBoard = new ArrayList<Integer>();

        while (k != nbrDigitTotal) {
            combinationOnBoard.add(combinationOnBoardInverted.get(l));
            k++;
            l--;
        }
        logger.info("Tableau 1 (ordinateur + user) = " + combinationOnBoard);

        //je compare les chiffre ordinateur-utilisateur
        //prise en compte du nombre de digit
        int nbrBoxes = nbrDigitUser;
        //les limites
        int minComputerBoxesOnBoard = 0;
        int maxComputerBoxesOnBoard = nbrBoxes - 1;
        int minUserBoxesOnBoard = nbrBoxes;
        int maxUserBoxesOnBoard = (nbrBoxes * 2) - 1;

        //les compteurs
        int counterForComputeur = maxComputerBoxesOnBoard;
        int counterForUser = maxUserBoxesOnBoard;
        int counterForLoop = nbrBoxes;

        //Creation d'un tableau pour les resultats:
        List<String> resultList = new ArrayList<String>();

        //on compare:

        do {
            int nbrComputerForCompare = combinationOnBoard.get(minComputerBoxesOnBoard);
            int nbrUserForCompare = combinationOnBoard.get(minUserBoxesOnBoard);
            logger.info("dernier nombre computeur = " + nbrComputerForCompare);
            logger.info("dernier nombre user = " + nbrUserForCompare);

            if (nbrComputerForCompare > nbrUserForCompare) {
                resultList.add("+");


            } else if (nbrComputerForCompare < nbrUserForCompare) {
                resultList.add("-");

            } else {
                resultList.add("=");

            }
            counterForLoop--;
            minComputerBoxesOnBoard++;
            minUserBoxesOnBoard++;


        }while (counterForLoop != 0);

        logger.info("tableau de comparaison = " + resultList);

        // enregistrement dans la variable AfterCompare
        String afterCompare = "";
        int counterForLoop2 = 0;

        do {
           String OneString = resultList.get(counterForLoop2);
           afterCompare = afterCompare + OneString;
           counterForLoop2++;
        }while (counterForLoop2 != nbrBoxes);

        afterCompareExport = afterCompare;


        logger.info("resultat final dans la class SearchNumber = " + afterCompare);
        // je set l'info dans la class MenuDisplay


        }

        }























