package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MastermindChallenger extends Mastermind {

    static final Logger logger = LogManager.getLogger();

    public MastermindChallenger(int nbrDigit, int nbrOfTry, String developerMode) {
        super(nbrDigit, nbrOfTry, developerMode);
    }

    protected void playChallengerModeMastermind () {

        System.out.println("Mastermind is begin");
        this.replay();

    }




}
