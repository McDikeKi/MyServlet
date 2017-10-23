package org.harvey.solve.validator;

import org.harvey.solve.validator.constraint.NoRepetitionList;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Service
public class NoRepetitionListValidator implements ConstraintValidator<NoRepetitionList, List<?>> {


    @Override
    public void initialize(NoRepetitionList constraintAnnotation) {
    }

    public boolean isValid(List<?> list, ConstraintValidatorContext constraintValidatorContext) {
    	if(list == null){
    		return true;
    	}
        if(list.size()==1){
        	return true;
        }
    	for (int i = 0;i < list.size()-1;i++) {
        	for(int j = i+1;j < list.size();j++){
	            if(list.get(i)!=null&&list.get(j)!=null&&list.get(i).equals(list.get(j))){
	            	return false;
	            }
        	}
        }
        return true;
    }

}