package br.com.Rtravel.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.com.Rtravel.domaim.User;

@Service
public interface EmailService {
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(User user, String newPass);
}