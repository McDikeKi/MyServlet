package org.harvey.solve.controller;

import java.math.BigDecimal;
import java.util.List;

import org.harvey.solve.business.SolveFibonacciProblem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/FibonacciProblem")
public class FibonacciProblemController {
	private SolveFibonacciProblem solveFibonacciProblem;

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
		int length = Integer.valueOf(lengthStr);
		List<BigDecimal> list = solveFibonacciProblem.Solve(length);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("FibonacciResult");
		mav.addObject("list",list);
		return mav;

	}
}
