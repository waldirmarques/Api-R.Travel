package br.com.Rtravel.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.Rtravel.security.UserSS;

public class UserServiceService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
