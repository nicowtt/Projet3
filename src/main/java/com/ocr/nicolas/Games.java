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

    //pour le mastermind
    protected int nbrDigitUsable;

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
                    Mastermind mastermind = new Mastermind(nbrDigit,nbrOfTry,developerMode);
                    mastermind.playMastermind();
                    break;
                }
            default:
                break;
        }
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
}
