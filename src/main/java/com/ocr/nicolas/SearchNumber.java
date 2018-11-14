package com.ocr.nicolas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.ocr.nicolas.Log4j.logger;

public class SearchNumber {

    int nbrCombinationSearchNumber;
    int nbrDigit;
    int nbrList;

    public void setNbrCombinationSearchNumber(int nbrCombinationSearchNumber) {
        this.nbrCombinationSearchNumber = nbrCombinationSearchNumber;
    }

    public void setNbrList(int nbrList) {
        this.nbrList = nbrList;
    }

    public int getNbrDigit() {
        return nbrDigit;
    }

    /**
     * Computer number of combination
     *
     * @param nbr limit max of combination (take on config.properties)
     */
    public int computerNbrCombination(int nbr) {

        Random rand = new Random();
        int randomNumber = rand.nextInt(nbr + 1);
        logger.info("nombre aléatoire ordinateur = " + randomNumber);
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
        logger.info("nombre de digit de la combinaison = " + nbrDigit);
        return nbrDigit;
    }

    /**
     * put number on ArrayList
     *
     * @param number   number
     * @param nbrDigit number of time this loop while will be running
     */
    public void combinationOnBoard(int number, int nbrDigit) {
        int i = nbrDigit;
        int digit = 0;
        int nbr = number;
        int k = 0;
        int l = (nbrDigit - 1);

        List<Integer> combinationOnBoardInverted = new ArrayList<Integer>();
        while (i > 0) {
            digit = nbr % 10;
            i--;
            nbr = nbr / 10;
            combinationOnBoardInverted.add(digit);
        }

        switch (nbrList) {
            case 1:
                //creation d'une nouvelle ArrayList pour inverser les chiffres
                List<Integer> combinationOnBoard = new ArrayList<Integer>();

            while (k != nbrDigit) {
                combinationOnBoard.add(combinationOnBoardInverted.get(l));
                k++;
                l--;
            }
            logger.info("Tableau = " + combinationOnBoard);

            case 2:
                //creation d'une ArrayList ->2 en inversant les chiffres
                List<Integer> combinationOnBoard2 = new ArrayList<Integer>();
                while (k != nbrDigit) {
                    combinationOnBoard2.add(combinationOnBoardInverted.get(l));
                    k++;
                    l--;
                    }
                    logger.info("Tableau = " + combinationOnBoard2);
                }
            }
        }






