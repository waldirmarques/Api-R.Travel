package br.com.Rtravel.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.Rtravel.security.JWTUtil;
import br.com.Rtravel.security.UserSS;
import br.com.Rtravel.services.UserServiceService;

@RestController
@RequestMapping(value = "/auth") 
public class AuthResource { //This class renews the token so you don't have to log in multiple times.	
	
	@Autowired
	private JWTUtil jwtUtil;
	
	//@Autowired
	//private AuthService service;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserServiceService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	//@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	//public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
	//	service.sendNewPassword(objDto.getEmail());
	//	return ResponseEntity.noContent().build();
	//}
}
