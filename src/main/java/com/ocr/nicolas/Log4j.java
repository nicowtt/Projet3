package com.ocr.nicolas;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log4j {

    static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        logger.trace("msg de trace"); // E/S methods

        logger.debug("msg de debogage"); // Display values of datas

        logger.info("msg d'information"); //loading of conf file, beg/end long process

        logger.warn("msg d'avertissement"); // error login, invalid data

        logger.error("msg d'erreur"); // all throwned exceptions

        logger.fatal("msg d'erreur fatale");  // error DB, exceptions which stop the program
    }
}






