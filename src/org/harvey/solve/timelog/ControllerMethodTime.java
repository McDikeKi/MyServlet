package org.harvey.solve.timelog;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class ControllerMethodTime {
	Logger log = Logger.getLogger(ControllerMethodTime.class);
	
	public void showEnterTime(){
		log.info("Controller method enter time:"
				+new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
	}
	
	public void showExitTime(){
		log.info("Controller method exit time:"
				+new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
	}
}
