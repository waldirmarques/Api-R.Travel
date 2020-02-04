package br.com.Rtravel.resources.exceptions;

import java.io.Serializable;

public class FieldMassage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String fielName;
	private String message;
	
	public FieldMassage() {
		
	}

	public FieldMassage(String fielName, String message) {
		super();
		this.fielName = fielName;
		this.message = message;
	}

	public String getFielName() {
		return fielName;
	}

	public void setFielName(String fielName) {
		this.fielName = fielName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
