package org.harvey.solve.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.harvey.solve.business.SolveJosephProblem;
import org.harvey.solve.dto.Request;
import org.harvey.solve.dto.Response;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("JosephProblem")
public class JosephProblemController {
	private static Logger log = Logger.getLogger(JosephProblemController.class);
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
    public Object solveJosephProblem(@Valid @RequestBody Request josephRequest,BindingResult result){
		if(result.hasErrors()){
			List<ObjectError> errorList = result.getAllErrors();
			for(ObjectError error:errorList){
				log.info(error.getDefaultMessage());
			}
			return null;
		}
		else{
            Response josephResponse = solveJosephProblem.solve(josephRequest);
			return josephResponse;
		}
    }	
}
