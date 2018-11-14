package com.ocr.nicolas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.ocr.nicolas.Log4j.logger;

public class SearchNumber {

    int nbrCombinationSearchNumber;
    int nbrDigitComputeur;
    int nbrDigitUser;

    public void setNbrCombinationSearchNumber(int nbrCombinationSearchNumber) {
        this.nbrCombinationSearchNumber = nbrCombinationSearchNumber;
    }

    public int getNbrDigitComputeur() {
        return nbrDigitComputeur;
    }
    public int getNbrDigitUser() { return nbrDigitUser; }

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
        return nbrDigit;
    }

    /**
     * put number on ArrayList
     *
     * @param nbrRandom number random computer
     * @param nbrUser number user of game
     * @param nbrDigitComputer number of time this loop while will be running
     * @param nbrDigitUser number of digit on user number
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

        //les limites
        int debutComputeurInList = 0;
        int arretComputerInList = nbrDigitComputer -1;
        int debutUserInList = nbrDigitComputer;
        int finUserInList = nbrDigitTotal -1;

        //les compteurs
        int counterComputer = arretComputerInList;
        int counterUser = finUserInList;

        //les resultats
        List<String> result = new ArrayList<String>();
        //todo faire la comparaison et mettre dans la list result

        //les comparaisons








        }



    }

}







