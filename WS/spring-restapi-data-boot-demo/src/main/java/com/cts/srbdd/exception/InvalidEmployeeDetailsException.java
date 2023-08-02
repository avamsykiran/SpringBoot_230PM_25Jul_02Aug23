package com.cts.srbdd.exception;

import org.springframework.validation.BindingResult;

@SuppressWarnings("serial")
public class InvalidEmployeeDetailsException extends Exception {

	private BindingResult bindingResult;
	
	public InvalidEmployeeDetailsException(BindingResult bindingResult) {
		this.bindingResult=bindingResult;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

}
