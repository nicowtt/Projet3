package com.ocr.nicolas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.ocr.nicolas.Log4j.logger;

public class SearchNumber {

    int nbrCombinationSearchNumber;
    int nbrDigit;

    public void setNbrCombinationSearchNumber(int nbrCombinationSearchNumber) {
        this.nbrCombinationSearchNumber = nbrCombinationSearchNumber;
    }

    public int getNbrDigit() {
        return nbrDigit;
    }

    /**
     * Computer number of combination
     * @param nbr limit max of combination (take on config.properties)
     */
    public int computerNbrCombination (int nbr) {

        Random rand = new Random();
        int randomNumber = rand.nextInt(nbr + 1);
        logger.info("nombre aléatoire ordinateur = " + randomNumber);
        return randomNumber;
    }


    /**
     * find number of digit
     * @param number number on config.properties
     * @return digit number
     */
    public int FindNbrDigit(int number) {
        int result = number;
        int nbrDigit = 0;

         while (result >= 1)
         {
         result = result/10;
         nbrDigit++;
         }
         logger.info("nombre de digit de la combinaison = " + nbrDigit);
         return nbrDigit;
    }

    /**
     * put number on ArryList
     * @param number computer number
     * @param nbrDigit number of time this boucle while run
     */
    public void combinationOnBoard(int number, int nbrDigit ) {
        int i = nbrDigit;
        int digit = 0;
        int nbr = number;
        int k = 0;
        int l = (nbrDigit -1);

        List<Integer> combinationOnBoardInverted = new ArrayList<Integer>();
        while (i > 0) {
            digit = nbr % 10;
            i--;
            nbr = nbr / 10;
            combinationOnBoardInverted.add(digit);
        }

        //creation d'un nouvelle ArrayList pour inverser les chiffres
        List<Integer> combinationOnBoard = new ArrayList<Integer>();

        while (k != nbrDigit) {
            combinationOnBoard.add(combinationOnBoardInverted.get(l));
            k++;
            l--;
        }
        logger.info("Tableau = " + combinationOnBoard);
    }


    /**
     * Compare value
     * @param nbrCombinationSearchNumber
     * @param userChoice
     */
    public void compare (int nbrCombinationSearchNumber, int userChoice) {
        if (nbrCombinationSearchNumber > userChoice) {
            System.out.println(" c'est plus ");
        }


    }




}
