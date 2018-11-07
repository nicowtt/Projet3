package com.ocr.nicolas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFile {
    public static void main(final String[] args) {

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

        // get the property value and print it out
        System.out.println(prop.getProperty("DeveloperMode"));
        System.out.println(prop.getProperty("GameToPlay"));

        }
    }



