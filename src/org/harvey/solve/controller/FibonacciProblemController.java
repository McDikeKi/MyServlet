package org.harvey.solve.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.harvey.solve.service.FibonacciSequenceFunction;
import org.harvey.solve.service.impl.FibonacciSequenceFunctionImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/FibonacciProblem")
public class FibonacciProblemController {
	@RequestMapping("/ProblemInput")
	public ModelAndView fibonacciInput(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("FibonacciInput");
		return mav;
	}
	
	@RequestMapping("/ProblemSolve")
	public ModelAndView finbonacciResult(@RequestParam(value="length") String lengthStr){
		int length = Integer.valueOf(lengthStr);
		FibonacciSequenceFunction fibonacciSequenceFunction = new FibonacciSequenceFunctionImpl();
		List<BigDecimal> list = fibonacciSequenceFunction
				.getSequenceResult(length);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("FibonacciResult");
		mav.addObject("list",list);
		return mav;

	}
}
