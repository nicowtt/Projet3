package com.ocr.nicolas;

public class MastermindDuel extends Mastermind {

    String compDefendMastKnuthStr; // computer combination (String)
    String userDefendMastStr; // user combination (String)

    protected int playDuelModeMastermind () {

        //variables
        int nbrLoopMastDuel = 0;
        int replay = 3;
        iswin = false;





        replay = this.replayMaster();
        return replay;
    }

}
