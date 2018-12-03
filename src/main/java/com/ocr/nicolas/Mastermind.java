package com.ocr.nicolas;

public class Mastermind extends Games{

    public Mastermind(int nbrDigit, int nbrOfTry, String developerMode) {
        super(nbrDigit, nbrOfTry, developerMode);
    }

    public void playMastermind() {

        // in progress

        this.replay();
    }

    /**
     * For redirection of replay or leave
     */
    public void replay() {

        // objets
        MenuDisplay display = new MenuDisplay();

        // affichage console for replay et redirection
        display.displayAskIfReplay();
        int replayIntern = display.displayReplayChoice();
        if (replayIntern == 1) {this.playMastermind();}
        if (replayIntern == 2) {this.playGames();}
        if (replayIntern == 3) {System.exit(0);}
    }

}
