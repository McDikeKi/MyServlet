package org.harvey.solve.controller;

import java.util.ArrayList;
import java.util.List;

import org.harvey.solve.business.SolveJosephProblem;
import org.harvey.solve.dto.Request;
import org.harvey.solve.dto.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("JosephProblem")
public class JosephProblemController {
//	private static Logger log = Logger.getLogger(JosephProblemController.class);
	private SolveJosephProblem solveJosephProblem;

	public void setSolveJosephProblem(SolveJosephProblem solveJosephProblem) {
		this.solveJosephProblem = solveJosephProblem;
	}

	@RequestMapping("/ProblemInput")
	public ModelAndView josephProblemInput(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("JosephInputNew");
		return mav;
	}
	
	@RequestMapping(value="/ProblemSolve",method=RequestMethod.POST)
	@ResponseBody
    public Object solveJosephProblem(@RequestBody Request josephRequest ){ 
		Response josephResponse = solveJosephProblem.solve(josephRequest);
		return josephResponse;
    }	
	
	@RequestMapping("/ProblemSolve/hello")
	public ModelAndView helloPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hello");
		List<String> list = new ArrayList<>();
		list.add("Ready");
		list.add("to");
		list.add("go");
		list.add("!");
		mav.addObject("words","Hey man,how's the day");
		mav.addObject("list",list);
		return mav;
	}
}
