package com.ocr.nicolas;

import com.ocr.nicolas.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;


public class Games {

    Scanner sc = new Scanner(System.in);
    static final Logger logger = LogManager.getLogger();

    //commun a tous les jeux
    protected int nbrDigit;
    protected int nbrOfTry;
    protected String developerMode;

    //Pour le mastermind
    protected int nbrMaxOnDigit;

    public Games(int nbrDigit, int nbrOfTry, String developerMode) {
        this.nbrDigit = nbrDigit;
        this.nbrOfTry = nbrOfTry;
        this.developerMode = developerMode;
    }

    public Games() {
    }


    public void playGames() {
        // lecture config.propertie et variable a zero
        ReadPropertiesFile read = new ReadPropertiesFile();
        nbrDigit = read.getNbrDigit();
        developerMode = read.getDeveloperMode(); // developer mode?
        nbrOfTry = read.getNbrOfTry();
        nbrMaxOnDigit = read.getNbrMaxOnDigit();
        boolean isWin = false;

        logger.info("info dans la class mere Games (nbrDigit= "+ nbrDigit +" nbrOfTry = " + nbrOfTry +" developerMode =" + developerMode);


        // Affichage du menu du choix des jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskGamesMenu();

        // Recuperation variable du choix des jeux
        int gamesMenuChoice = display.displayGamesMenuChoice();
        if (gamesMenuChoice == 3) {System.exit(0);}

        switch (gamesMenuChoice) {
            case 1:
                while (gamesMenuChoice == 1) {
                    SearchNumber searchNumber = new SearchNumber(nbrDigit,nbrOfTry,developerMode);
                    searchNumber.playSearchNumber();
                    break;
                }
            case 2:
                while (gamesMenuChoice == 2) {
                    Mastermind mastermind = new Mastermind(nbrDigit,nbrOfTry,developerMode, nbrMaxOnDigit);
                    mastermind.playMastermind();
                    break;
                }
            default:
                break;
        }
    }

    /**
     * for make random computer number(s) combination
     *
     * @return random digit number (type of character string)
     */
    protected String computerNbrCombination(int min, int max) {

        String finalRandomDigitNumberString = "";
        int counterNbrDigit = this.nbrDigit;

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

    /**
     * for play headsOrTails (Pile ou face en fr)
     *
     * @return boolean luck (luck or no luck)
     */
    public boolean headsOrTails() {

        //variable(s)
        String user;
        Boolean comp;
        Boolean luck = true;
        Boolean responseIsGood = false;

        System.out.println("Qui commence a deviner la combinaison mystère ?");

        do {
            System.out.println("Choisi pile ou face :");
            user = sc.nextLine();
            comp = Utils.getRandomBoolean();
            if (user.contains("pile")) {
                if (comp) {
                    luck = true;
                } else {
                    luck = false;
                }
                responseIsGood = true;
            }
            if (user.contains("face")) {
                if (!comp) {
                    luck = true;
                } else {
                    luck = false;
                }
                responseIsGood = true;
            }
        } while (!responseIsGood);

        if (luck) {
            System.out.println("Tirage aléatoire = pile");
        } else {
            System.out.println("Tirage aléatoire = face");
        }
        if (luck = comp) {
            System.out.println("Tu as de la chance ! A toi de commencer. ");
        }else {
            System.out.println("Domage !, c'est donc l'ordinateur qui commence.");}

        logger.info("tirage de la chance (false: ordi qui commence, true: utilisateur commence) = " + luck );

        return luck;
    }

    /**
     * For redirection of replay or leave
     */
    protected void replay() {

        // objets
        MenuDisplay display = new MenuDisplay();
        SearchNumber searchNumber = new SearchNumber(nbrDigit, nbrOfTry, developerMode);


        // affichage console for replay et redirection
        display.displayAskIfReplay();
        int replayIntern = display.displayReplayChoice();
        if (replayIntern == 1) {searchNumber.playSearchNumber();}
        if (replayIntern == 2) {this.playGames();}
        if (replayIntern == 3) {System.exit(0);}
    }
}
