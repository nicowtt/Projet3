package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Games {

    static final Logger logger = LogManager.getLogger();

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
}
