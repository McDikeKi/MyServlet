package org.harvey.solve.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.harvey.solve.business.FibonacciRequestCheckerBusiness;
import org.harvey.solve.business.FibonacciProblemSolverBusiness;
import org.harvey.solve.exception.IllegalInputException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/FibonacciProblem")
public class FibonacciProblemController {
	private static Logger log = Logger.getLogger(JosephProblemController.class);
	private FibonacciProblemSolverBusiness fibonacciProblemSolverBusiness;
	private FibonacciRequestCheckerBusiness fibonacciRequestCheckerBusiness;

	public void setFibonacciProblemSolverBusiness(FibonacciProblemSolverBusiness fibonacciProblemSolverBusiness) {
		this.fibonacciProblemSolverBusiness = fibonacciProblemSolverBusiness;
	}

	public void setFibonacciRequestCheckerBusiness(FibonacciRequestCheckerBusiness fibonacciRequestCheckerBusiness) {
		this.fibonacciRequestCheckerBusiness = fibonacciRequestCheckerBusiness;
	}

	@RequestMapping("/ProblemInput")
	public ModelAndView fibonacciProblemInput(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("FibonacciInput");
		return mav;
	}
	
	@RequestMapping("/ProblemSolve")
	public ModelAndView finbonacciProblemSolve(@RequestParam(value="length") String lengthStr){
		try {
			fibonacciRequestCheckerBusiness.check(lengthStr);
		} catch (IllegalInputException e) {
			log.info("Illegal input:"+e);
			String errorMessage = e.getMessage()+" - "+e.getCause().getMessage();
			ModelAndView mav = new ModelAndView();
			mav.setViewName("ErrorPage");
			mav.addObject("errorMessage",errorMessage);
			return mav;
		}
		
		int length = Integer.valueOf(lengthStr);
		List<BigDecimal> list = fibonacciProblemSolverBusiness.solve(length);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("FibonacciResult");
		mav.addObject("list",list);
		return mav;

	}
}
