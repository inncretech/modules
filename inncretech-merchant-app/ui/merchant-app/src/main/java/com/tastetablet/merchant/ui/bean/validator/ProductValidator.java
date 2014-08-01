package com.tastetablet.merchant.ui.bean.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tastetablet.merchant.ui.bean.ProductBean;

@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return ProductBean.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		validateStartDateAndEndDate();
	}

	private void validateStartDateAndEndDate() {
		// TODO Auto-generated method stub
		
	}

}
