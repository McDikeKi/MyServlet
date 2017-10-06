package org.harvey.solve.business.businessimpl;


import org.harvey.solve.business.SolveJosephProblem;
import org.harvey.solve.dto.Request;
import org.harvey.solve.dto.Response;
import org.harvey.solve.service.JosephProblemFunction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SolveJosephProblemImpl implements SolveJosephProblem{
	public Response solve(Request request){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		JosephProblemFunction josephProblemFunction = (JosephProblemFunction) context.getBean("josephProblemFunction");
		String result = josephProblemFunction.getFinalElement(request.getCircle().getPersons() ,
				Integer.valueOf(request.getCircle().getStart()) , Integer.valueOf(request.getCircle().getInterval()));
		((ConfigurableApplicationContext)context).close();
		return new Response(result);
	}
}
