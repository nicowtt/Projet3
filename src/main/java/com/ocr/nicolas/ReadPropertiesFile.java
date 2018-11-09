package com.ocr.nicolas;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ReadPropertiesFile {

    static final Logger logger = LogManager.getLogger();

    /**
     * reading config.properties for developer mode
     */
    public void ReadConfigProperties() {


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

            String digitSearchNumber;
            digitSearchNumber = prop.getProperty("DigitSearchNumber");

            // String digitSearchNumber -> Integer digitSearchNumberInt

            int digitSearchNumberInt = Integer.parseInt(prop.getProperty("DigitSearchNumber"));
            logger.info("variable digitSearchNumber = " + digitSearchNumberInt);



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




