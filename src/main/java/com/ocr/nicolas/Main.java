package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {

        /* developer Mode recovery
        boolean developerMode;

        for (int i = 0; i < args.length; i++) {
            if ("-d".equals(args[i])) {
                developerMode = true;
                logger.info("developperMode");
            }
        }
        */


        ReadPropertiesFile read = new ReadPropertiesFile();
        read.ReadConfigProperties();

        Choices choice = new Choices();

        choice.displayAskGamesMenu();
        choice.displayGamesMenuChoice();

        choice.displayAskTypeOfGame();
        choice.displayGameTypeChoice();


    }
}

