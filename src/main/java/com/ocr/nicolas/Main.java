package com.ocr.nicolas;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // si logiciel lancé en ligne de commande avec l'argument "dev"
        if ( ( args.length > 0 && args[0].equals( "dev" ) )) {
            logger.info("Starting Developer mode");

            // Variable DEV -> true; la variable DEV est verifier dans chaque mode au besoin.
            Config.setDEV(true);
        }

        // lancement du jeu
        PlayGames.playGames();
    }
}















