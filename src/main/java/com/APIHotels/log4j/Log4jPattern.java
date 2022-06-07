package com.APIHotels.log4j;



import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jPattern {

	static Logger logger = Logger.getLogger(Log4jPattern.class);

	public static void main(String[] args) {
		 PropertyConfigurator.configure("log4j.info");

		logger.info("This Is A Log Message ..!");
	}
}