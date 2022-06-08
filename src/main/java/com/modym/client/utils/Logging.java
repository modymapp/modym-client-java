/************************************************************************ 
 * Copyright Modym, Ltd.
 * 
 */
package com.modym.client.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * @author sameer
 *
 */
public class Logging {

    private Logging() {
        // Singleton
    }

    private static final Map<String, Logger> loggerMap = new ConcurrentHashMap<>();

    public static void debug(String message) {
        log(Level.DEBUG, message, null, new Object[] {});
    }

    public static void debug(String message, Object... args) {
        log(Level.DEBUG, message, null, args);
    }

    public static void info(String message) {
        log(Level.INFO, message, null, new Object[] {});
    }

    public static void info(String message, Object... args) {
        log(Level.INFO, message, null, args);
    }

    public static void warn(String message) {
        log(Level.WARN, message, null, new Object[] {});
    }

    public static void warn(String message, Object... args) {
        log(Level.WARN, message, null, args);
    }

    public static void warn(String message, Throwable cause, Object... args) {
        log(Level.WARN, message, cause, args);
    }

    public static void error(String message) {
        log(Level.ERROR, message, null, new Object[] {});
    }

    public static void error(String message, Object... args) {
        log(Level.ERROR, message, null, args);
    }

    public static void error(String message, Throwable cause, Object... args) {
        log(Level.ERROR, message, cause, args);
    }

    public static void log(Level loggingLevel, String message, Throwable cause, Object... args) {
        Level level = loggingLevel == null ? Level.INFO : loggingLevel;
        Logger logger = getLogger();
        if (!level.isGreaterOrEqual(logger.getEffectiveLevel()))
            return;
        String formattedMessage = message;
        if (args != null && args.length > 0)
            formattedMessage = String.format(message, args);
        logger.log(Logging.class.getName(), level, formattedMessage, cause);
    }

    private static Logger getLogger() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String callingElement = stackTraceElements[4].toString().split("\\(")[0];
        String callingClass = callingElement.substring(0, callingElement.lastIndexOf('.'));
        if (!loggerMap.containsKey(callingClass)) {
            loggerMap.put(callingClass, Logger.getLogger(callingClass));
        }
        return loggerMap.get(callingClass);
    }
}
