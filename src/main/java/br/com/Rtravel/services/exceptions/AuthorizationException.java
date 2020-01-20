package br.com.Rtravel.services.exceptions;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(String mgs) {
		super(mgs);		
	}
	
	public AuthorizationException(String mgs,Throwable cause) {
		super(mgs,cause);		
	}
	
}
