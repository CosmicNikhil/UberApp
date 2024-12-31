package com.nikhil.uber.services.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nikhil.uber.services.EmailSenderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {

	private final JavaMailSender javaMailSender;

	@Override
	public void sendEmail(String toEmail, String subject, String body) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

			simpleMailMessage.setTo(toEmail);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(body);

			javaMailSender.send(simpleMailMessage);
			log.info("Email sent successfully");
		} catch (Exception e) {
			log.info("Cannot send email, "+e.getMessage());
		}
	}

	@Override
	public void sendEmail(String[] toEmail, String subject, String body) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setBcc(toEmail);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(body);

			javaMailSender.send(simpleMailMessage);
			log.info("Email sent successfully");
		} catch (Exception e) {
			log.info("Cannot send email, "+e.getMessage());
		}
	}
}