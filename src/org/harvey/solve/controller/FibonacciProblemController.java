package org.harvey.solve.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.harvey.solve.business.CheckFibonacciInput;
import org.harvey.solve.business.SolveFibonacciProblem;
import org.harvey.solve.exception.IllegalInputException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/FibonacciProblem")
public class FibonacciProblemController {
	private static Logger log = Logger.getLogger(JosephProblemController.class);
	private SolveFibonacciProblem solveFibonacciProblem;
	private CheckFibonacciInput checkFibonacciInput;

	public void setCheckFibonacciInput(CheckFibonacciInput checkFibonacciInput) {
		this.checkFibonacciInput = checkFibonacciInput;
	}

	public void setSolveFibonacciProblem(SolveFibonacciProblem solveFibonacciProblem) {
		this.solveFibonacciProblem = solveFibonacciProblem;
	}

	@RequestMapping("/ProblemInput")
	public ModelAndView fibonacciInput(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("FibonacciInput");
		return mav;
	}
	
	@RequestMapping("/ProblemSolve")
	public ModelAndView finbonacciResult(@RequestParam(value="length") String lengthStr){
		try {
			checkFibonacciInput.check(lengthStr);
		} catch (IllegalInputException e) {
			log.info("Illegal input:"+e);
			String errorMessage = e.getMessage()+" - "+e.getCause().getMessage();
			ModelAndView mav = new ModelAndView();
			mav.setViewName("ErrorPage");
			mav.addObject("errorMessage",errorMessage);
			return mav;
		}
		
		int length = Integer.valueOf(lengthStr);
		List<BigDecimal> list = solveFibonacciProblem.Solve(length);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("FibonacciResult");
		mav.addObject("list",list);
		return mav;

	}
}
