/*
 * File name: EmailServiceBean.java
 * Creation date: 23/11/2008 11:55:25
 * Copyright Mindpool
 */
package com.laborguru.service.email;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
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
	private static final Logger log = Logger.getLogger(EmailServiceBean.class);

	private JavaMailSender mailSender;
	private SimpleMailMessage mailMessage;
	private boolean enabled;
	
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
		if(isEnabled()) {
			SimpleMailMessage msg = new SimpleMailMessage(getMailMessage());
			msg.setTo(to);
			if(cc != null) {
				msg.setCc(cc);
			}
			msg.setSubject(subject);
			msg.setText(body);
			msg.setSentDate(new Date());
			
			if(log.isDebugEnabled()) {
				log.debug("About to send message [" + printMessage(msg) + "]");
			}
			
			getMailSender().send(msg);
			
			if(log.isDebugEnabled()) {
				log.debug("Message [" + printMessage(msg) + "] sent");
			}
		} else {
			log.warn("Email Service is disabled. Check configuration.");
		}
	}

	/**
	 * 
	 * @param msg
	 * @return
	 */
	private String printMessage(SimpleMailMessage msg) {
		if(msg != null) {
			try {
				return ReflectionToStringBuilder.toString(msg);
			} catch(Throwable ex) {
				log.error("Error in reflection to string builder", ex);
				return msg.toString();
			}
		} else {
			return null;
		}
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

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
