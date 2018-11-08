package com.ocr.nicolas;

import org.apache.log4j.*;


public class Log4j {

    static Logger logger = Logger.getLogger(Log4j.class);

    public static void main(String[] args) {
        ConsoleAppender appender = (ConsoleAppender)  logger.getAppender("console");
        logger.addAppender(appender);
        logger.debug("test debug");
        logger.info("test info");
        logger.warn("test warning");
    }
}






