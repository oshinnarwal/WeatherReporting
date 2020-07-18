package com.NDTV.Utils;

import org.apache.log4j.Logger;

public class Log {
	// Initialize Log4j logs
	 
	 private static Logger Log = Logger.getLogger(Log.class.getName());//
	 
	 // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
	 
	 public static void startTestCase(String sTestCaseName){
	 
	 Log.info("****************************************************************************************");
	 
	 Log.info("****************************************************************************************");
	 
	 Log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
	 
	 Log.info("****************************************************************************************");
	 
	 Log.info("****************************************************************************************");
	 
	 }
	 
	 //This is to print log for the ending of the test case
	 
	 public static void endTestCase(String sTestCaseName){
	 
	 Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
	 
	 Log.info("X");
	 
	 Log.info("X");
	 
	 Log.info("X");
	 
	 Log.info("X");
	 
	 }
	 
	 // Need to create these methods, so that they can be called  
	 
	 public static void info(Object message) {
	 
	 Log.info(message);
	 
	 }
	 
	 public static void warn(Object message) {
	 
	    Log.warn(message);
	 
	 }
	 
	 public static void error(Object message) {
	 
	    Log.error(message);
	 
	 }
	 
	 public static void fatal(Object message) {
	 
	    Log.fatal(message);
	 
	 }
	 
	 public static void debug(Object message) {
	 
	    Log.debug(message);
	 
	 }
}
