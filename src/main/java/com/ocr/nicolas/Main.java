package com.ocr.nicolas;

public class Main {

    public static void main(String[] args) {

        Choices choice = new Choices();
        choice.displayAskDeveloperChoice();
        choice.displayDeveloperChoice();

        ReadPropertiesFile read = new ReadPropertiesFile();
        read.ReadIfDeveloperModeIsOn();





    }
}
