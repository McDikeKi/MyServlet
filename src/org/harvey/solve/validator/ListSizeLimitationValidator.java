package org.harvey.solve.validator;

import org.apache.log4j.Logger;
import org.harvey.solve.validator.constraint.ListSizeLimitation;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.List;

@Service
public class ListSizeLimitationValidator implements ConstraintValidator<ListSizeLimitation, Object> {
	String listFieldName;
	String limitedFieldName;
	Logger log = Logger.getLogger(ListSizeLimitationValidator.class);
	
	@Override
	public void initialize(ListSizeLimitation listSizeLimitation) {
		this.listFieldName = listSizeLimitation.listFieldName();
		this.limitedFieldName = listSizeLimitation.limitedFieldName();
	}
	
	@SuppressWarnings("unchecked")
	public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        List<Object> list = null;
        Integer limitedValue = null;
    	Class<?> clazz = value.getClass();
        Field listField,limitedField;
        try {
        	listField = clazz.getDeclaredField(listFieldName);
        	limitedField = clazz.getDeclaredField(limitedFieldName);
        	listField.setAccessible(true);
        	limitedField.setAccessible(true);
	        list = (List<Object>) listField.get(value);
	        limitedValue = (Integer) limitedField.get(value);
		} catch (NoSuchFieldException e) {
			log.error(e);
			return false;
		} catch (SecurityException e) {
			log.error(e);
			return false;
		} catch (IllegalArgumentException e) {
			log.error(e);
			return false;
		} catch (IllegalAccessException e) {
			log.error(e);
			return false;
		}
        if(limitedValue == null||list == null){
        	return true;
        }
        if(limitedValue<list.size()){
        	return true;
        }
        else{
        	constraintValidatorContext.disableDefaultConstraintViolation();
        	constraintValidatorContext.buildConstraintViolationWithTemplate(
					constraintValidatorContext.getDefaultConstraintMessageTemplate())
					.addPropertyNode(limitedFieldName)
					.addConstraintViolation();  
			return false;
		}
        
    }

}