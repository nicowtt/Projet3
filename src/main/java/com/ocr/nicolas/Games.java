package com.ocr.nicolas;

public abstract class Games {

    //commun a tous les jeux
    protected int nbrDigit;
    protected int nbrOfTry;
    protected String developerMode;

    //Pour le mastermind
    protected int nbrMaxOnDigit;

    public Games(Config config) {
        this.nbrDigit = config.getNbrDigit();
        this.nbrOfTry = config.getNbrOfTry();
        this.developerMode = config.getDeveloperMode();
        this.nbrMaxOnDigit = config.getNbrMaxOnDigit();
    }

    /**
     * for make random computer number(s) combination
     *
     * @return random digit number (type of character string)
     */
    public String computerNbrCombination(int min, int max) {

        // variables
        String finalRandomDigitNumberString = "";
        int counterNbrDigit = nbrDigit;

        while (counterNbrDigit >= 1) {

            //int de 0 a 9 (base 10) avec min et max
            int base10RandomDigitNumber = min + (int) (Math.random() * ((max - min) + 1));
            //logger.info("base 10 random =" + base10RandomDigitNumber);

            // je converti le chiffre en string
            String base10RandomDigitNumberString = String.valueOf(base10RandomDigitNumber);

            // Je l'ajoute au string computer final
            finalRandomDigitNumberString = finalRandomDigitNumberString + base10RandomDigitNumberString;

            // j'incremente le compteur
            counterNbrDigit--;
        }
        return finalRandomDigitNumberString;
    }
}
