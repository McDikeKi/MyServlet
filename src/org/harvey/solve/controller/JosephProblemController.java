package org.harvey.solve.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.harvey.solve.business.JosephProblemSolverBusiness;
import org.harvey.solve.business.JosephRequestCheckerBusiness;
import org.harvey.solve.dto.JosephProblemInputError;
import org.harvey.solve.dto.JosephProblemRequest;
import org.harvey.solve.dto.JosephProblemResponse;
import org.harvey.solve.exception.IllegalInputException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	private JosephProblemSolverBusiness josephProblemSolverBusiness;
	private JosephRequestCheckerBusiness josephRequestCheckerBusiness;

	public void setJosephRequestCheckerBusiness(JosephRequestCheckerBusiness josephRequestCheckerBusiness) {
		this.josephRequestCheckerBusiness = josephRequestCheckerBusiness;
	}

	public void setJosephProblemSolverBusiness(JosephProblemSolverBusiness josephProblemSolverBusiness) {
		this.josephProblemSolverBusiness = josephProblemSolverBusiness;
	}

	@RequestMapping("/ProblemInput")
	public ModelAndView josephProblemInput(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("JosephInput");
		return mav;
	}
	
	@RequestMapping(value="/ProblemSolve",method=RequestMethod.POST)
	@ResponseBody
    public Object solveJosephProblem(@Valid @RequestBody JosephProblemRequest josephRequest,BindingResult result){
		if(result.hasErrors()){
			List<ObjectError> errorList = result.getAllErrors();
			List<FieldError> fieldErrorList = result.getFieldErrors();
			List<JosephProblemInputError> inputErrors = new ArrayList<>();
			int index = 0;
			for(ObjectError objectError : errorList){
				String message = objectError.getDefaultMessage();
				String fieldName = fieldErrorList.get(index++).getField();
				log.info(message);
				log.info("Error field:"+fieldName);
				inputErrors.add(new JosephProblemInputError(message,fieldName));
			}
			return new JosephProblemResponse(null,inputErrors);
		}
		else{
			try {
				josephRequestCheckerBusiness.check(josephRequest);
			} catch (IllegalInputException e) {
				String errorMesage = e.getCause().getMessage();
				log.info(e.getMessage()+" "+errorMesage);
				int index = errorMesage.indexOf("-");
				List<JosephProblemInputError> errors = new ArrayList<>();
				errors.add(new JosephProblemInputError(errorMesage.substring(0, index-1)
											,errorMesage.substring(index+2,errorMesage.length())));
				return new JosephProblemResponse(null,errors);
			}
            JosephProblemResponse josephResponse = josephProblemSolverBusiness.solve(josephRequest);
			return josephResponse;
		}
    }	
}
