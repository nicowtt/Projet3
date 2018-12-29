package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Games {

    static final Logger logger = LogManager.getLogger();

    //commun a tous les jeux
    protected int nbrDigit = PlayGames.getNbrDigit();
    protected int nbrOfTry = PlayGames.getNbrOfTry();
    protected String developerMode = PlayGames.getDeveloperMode();


    //Pour le mastermind
    protected int nbrMaxOnDigit = PlayGames.getNbrMaxOnDigit();
}
