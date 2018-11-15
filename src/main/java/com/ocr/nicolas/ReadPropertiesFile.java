package com.ocr.nicolas;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ReadPropertiesFile {

    private int nbrBoxesCombinationSearchNumber;
    private boolean developperMode;

    public int getNbrBoxesCombinationSearchNumber() {
        return nbrBoxesCombinationSearchNumber;
    }
    public boolean isDevelopperMode() { return developperMode; }


    static final Logger logger = LogManager.getLogger();


    /**
     * reading config.properties for developer mode
     *
     * @nbrCombinationSearchNumber: number of combination (integer in config.properties)
     */

    public void readConfigProperties() {


        final Properties prop = new Properties();
        InputStream input = null;


        try {

            input = new FileInputStream("C://Users//nicob//Documents//GitHub//Projet3//src//main//resources//config.properties");
            // load a properties file
            prop.load(input);

            // get the property value and print it out
            String developerMode;
            developerMode = prop.getProperty("DeveloperMode");
            logger.info("developerMode: " + developerMode);

            // String NbrCombinationSearchNumber -> Integer NbrCombinationSearchNumber

            nbrBoxesCombinationSearchNumber = Integer.parseInt(prop.getProperty("NbrBoxesCombinationSearchNumber"));
            logger.info("variable nbrBoxesCombinationSearchNumber = " + nbrBoxesCombinationSearchNumber);



        } catch (final IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}





