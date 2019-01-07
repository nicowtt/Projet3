package com.ocr.nicolas;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Config {

    static final Logger logger = LogManager.getLogger();

    private int nbrDigit;
    private String developerMode;
    private int nbrOfTry;
    private int nbrMaxOnDigit;

    public int getNbrOfTry() { return nbrOfTry; }
    public String getDeveloperMode() { return developerMode; }
    public int getNbrDigit() {
        return nbrDigit;
    }
    public int getNbrMaxOnDigit() {return nbrMaxOnDigit;}

    public Config() {
        readConfigProperties();
    }

    /**
     * reading config.properties for developer mode
     */
    private void readConfigProperties() {

        final Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("src//main//resources//config.properties");
            // load a properties file
            prop.load(input);

            // get the property value and print it out
            developerMode = prop.getProperty("DeveloperMode");
            //logger.info("developerMode: " + developerMode);

            // String -> Integer

            nbrDigit = Integer.parseInt(prop.getProperty("NbrDigit"));
            //logger.info("variable NbrDigit = " + nbrDigit);

            nbrOfTry = Integer.parseInt(prop.getProperty("NbrOfTry"));
            //logger.info("variable nbrOfTry = "+ nbrOfTry);

            nbrMaxOnDigit = Integer.parseInt(prop.getProperty("NbrMaxOnDigit"));
            //logger.info("variable nbrMaxOnDigit = "+ nbrMaxOnDigit);


        } catch (final IOException ex) {
            logger.error(" erreur de lecture du fichier config.properties",ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    logger.error(" erreur de lecture du fichier config.properties",e);
                }
            }
        }

    }
}





