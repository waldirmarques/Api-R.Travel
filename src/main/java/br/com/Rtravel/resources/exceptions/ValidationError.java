package br.com.Rtravel.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMassage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}
	
	public List<FieldMassage> getErrors() {
		return errors;
	}
	public void addError(String fieldName, String messagem) {
		errors.add(new FieldMassage(fieldName,messagem));
	}
	

}
