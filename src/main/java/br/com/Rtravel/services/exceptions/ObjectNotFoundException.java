package br.com.Rtravel.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String mgs) {
		super(mgs);		
	}
	
	public ObjectNotFoundException(String mgs,Throwable cause) {
		super(mgs,cause);		
	}
	
}
