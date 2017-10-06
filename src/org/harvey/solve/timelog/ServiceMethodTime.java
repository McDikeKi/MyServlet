package org.harvey.solve.timelog;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class ServiceMethodTime {
	Logger log = Logger.getLogger(ServiceMethodTime.class);
	
	public void showEnterTime(){
		log.info("Service method enter time:"
				+new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
	}
	
	public void showExitTime(){
		log.info("Service method exit time:"
				+new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
	}
}
