package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MastermindChallenger extends Mastermind {

    static final Logger logger = LogManager.getLogger();

    public MastermindChallenger(int nbrDigit, int nbrOfTry, String developerMode, int nbrMaxOnDigit) {
        super(nbrDigit, nbrOfTry, developerMode, nbrMaxOnDigit);
    }


    protected void playChallengerModeMastermind () {


        this.replay();

    }




}
