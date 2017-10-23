package org.harvey.solve.timelog;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BusinessMethodTime {
	Logger log = Logger.getLogger(ServiceMethodTime.class);
	
	@Pointcut("execution(* org.harvey.solve.business.impl.*.*(..))")
	public void businessMethod(){
	}
	
	@Before("businessMethod()")
	public void showEnterTime(){
		log.info("Business method enter time:"
				+new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
	}
	
	@After("businessMethod()")
	public void showExitTime(){
		log.info("Business method exit time:"
				+new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
	}
}
