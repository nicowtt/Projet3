package com.ocr.nicolas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFile {

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


        // check
        String developerMode;
        developerMode = prop.getProperty("DeveloperMode");
        if (developerMode.equals("yes"))
            System.out.println("DeveloperMode est deja ok ");
        else {
            WritePropertiesFile write = new WritePropertiesFile();
            write.ForceDeveloperModeWriteOnPropertiesFile();
            System.out.println("developerMode writed");
        }




        // get the property value and print it out

        //System.out.println(prop.getProperty("DeveloperMode"));
        //System.out.println(prop.getProperty("GameToPlay"));

        }
    }



