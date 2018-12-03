package com.ocr.nicolas;

public class SearchNumberDuel extends SearchNumber{

    public SearchNumberDuel(int nbrDigit, int nbrOfTry, String developerMode, boolean isWin) {
        super(nbrDigit, nbrOfTry, developerMode, isWin);
    }


    public void playDuelMode() {
        //objets
        SearchNumber searchNumber = new SearchNumber(getNbrDigit(),getNbrOfTry(),getDeveloperMode(),getIsWin());

        System.out.println("duel mode OK");
        searchNumber.replay();
    }
}
