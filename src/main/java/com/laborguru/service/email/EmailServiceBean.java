/*
 * File name: EmailServiceBean.java
 * Creation date: 23/11/2008 11:55:25
 * Copyright Mindpool
 */
package com.laborguru.service.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class EmailServiceBean implements EmailService {
	private JavaMailSender mailSender;
	private SimpleMailMessage mailMessage;
	
	/**
	 * 
	 */
	public EmailServiceBean() {
	}

	/**
	 * @param to
	 * @param cc
	 * @param subject
	 * @param body
	 * @see com.laborguru.service.email.EmailService#sendEmail(java.lang.String[], java.lang.String[], java.lang.String, java.lang.String)
	 */
	public void sendEmail(String[] to, String[] cc, String subject, String body) {
		SimpleMailMessage msg = getMailMessage();
		msg.setTo(to);
		if(cc != null) {
			msg.setCc(cc);
		}
		msg.setSubject(subject);
		msg.setText(body);
		
		getMailSender().send(msg);
	}

	/**
	 * @return the mailSender
	 */
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * @return the mailMessage
	 */
	public SimpleMailMessage getMailMessage() {
		return mailMessage;
	}

	/**
	 * @param mailMessage the mailMessage to set
	 */
	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

}
