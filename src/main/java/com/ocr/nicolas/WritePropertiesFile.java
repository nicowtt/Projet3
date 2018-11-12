package com.ocr.nicolas;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class WritePropertiesFile {


    /**
     * Write developerMode on config.properties
     */
    public static void main(String[] args) {

        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("C://Users//nicob//Documents//GitHub//Projet3//src//main//resources//config.properties");
        } catch (FileNotFoundException e) {
            System.err.println("Fichier .properties non trouvé");
        }

        //Line to write in .properties
        prop.setProperty("DeveloperMode", "yes");
        prop.setProperty("NbrCombinationSearchNumber", "10");


        try {
            //save .properties to resources folder
            prop.store(output, null);

        } catch (IOException e) {
            System.err.println("Problème Entrée Sortie");
        }
    }

}