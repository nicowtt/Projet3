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
     * reading config.properties
     */
    public void ReadIfDeveloperModeIsOn() {


        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("C://Users//nicob//Documents//GitHub//Projet3//src//main//resources//config.properties");
        } catch (FileNotFoundException e) {
            System.out.println("Fichier .property non trouvé");
        }

        // load the properties file
        try {
            prop.load(input);
        } catch (IOException e) {
            System.out.println("Problème entrée sortie");
        }


        // check config.properties ,compare and force write if needed
        String developerMode;
        developerMode = prop.getProperty("DeveloperMode");
        if (developerMode.equals("yes"))
            logger.info("DeveloperMode deja ok dans config.properties");
        else{
            WritePropertiesFile write = new WritePropertiesFile();
            write.ForceDeveloperModeWriteOnPropertiesFile();
            logger.info("developerMode ecrit dans config.properties");
        }

        }
    }



