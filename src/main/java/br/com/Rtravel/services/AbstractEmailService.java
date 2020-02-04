package br.com.Rtravel.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.com.Rtravel.domaim.User;


@Service
public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendNewPasswordEmail(User user, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(user, newPass);
		sendEmail(sm);
	}
	

	protected SimpleMailMessage prepareNewPasswordEmail(User user, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
	
}
