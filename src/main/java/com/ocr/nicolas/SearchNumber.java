package com.ocr.nicolas;

import java.util.Random;

import static com.ocr.nicolas.Log4j.logger;

public class SearchNumber {

    int nbrCombinationSearchNumber;

    public void setNbrCombinationSearchNumber(int nbrCombinationSearchNumber) {
        this.nbrCombinationSearchNumber = nbrCombinationSearchNumber;
    }

    /**
     * Computer number of combination
     * @randomNumber: random number with config.properties limit
     */
    public void computerNbrCombination (int nbr) {

        Random rand = new Random();
        int randomNumber = rand.nextInt(nbr + 1);
        logger.info("nombre aléatoire ordinateur = " + randomNumber);
        logger.info("nombre dans getNbrCombinationSearchNumber " + nbr);
    }

    public void compare (int nbrCombinationSearchNumber, int userChoice) {
        if (nbrCombinationSearchNumber > userChoice) {
            System.out.println(" c'est plus ");
        }


    }




}
