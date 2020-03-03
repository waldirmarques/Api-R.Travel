package br.com.Rtravel.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data()
@ToString(exclude = { "" })
public class CredenciaisDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String Email;
	private String senha;
	
	public CredenciaisDTO() {
		
	}
	
}
