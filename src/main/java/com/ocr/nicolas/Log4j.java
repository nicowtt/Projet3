package com.ocr.nicolas;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log4j {

    static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        logger.trace("msg de trace");

        System.out.println( "Hello man!" );

        logger.trace("msg de trace2");
    }
}






