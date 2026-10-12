package com.oraclejava.mailing_server;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MailMail {
	
	private JavaMailSender mailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(MailMail.class);

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String from, List<String> to, String subject, String msg) {
		logger.debug("sendMail Method...(multi mail)");
		MimeMessagePreparator messagePreparator = 
				new MimeMessagePreparator() {
					
					@Override
					public void prepare(MimeMessage mimeMessage) throws Exception {

						
						MimeMessageHelper messageHelper = 
								new MimeMessageHelper(mimeMessage, "UTF-8");
						messageHelper.setFrom(from);
						String[] toMailList = to.toArray(new String[to.size()]);
						messageHelper.setTo(toMailList);
						messageHelper.setSubject(subject);
						messageHelper.setText(msg, true);
						
					}
				};
				
				mailSender.send(messagePreparator);
	}
	
	public boolean sendMail(String from, String to, String subject, String msg) {
			logger.debug("sendMail Method...(one to one mail)");
			try {
				MimeMessagePreparator messagePreparator = 
					new MimeMessagePreparator() {
						
						@Override
						public void prepare(MimeMessage mimeMessage) throws Exception {
	
							
							MimeMessageHelper messageHelper = 
									new MimeMessageHelper(mimeMessage, "UTF-8");
							messageHelper.setFrom(from);
							
							messageHelper.setTo(to);
							messageHelper.setSubject(subject);
							


							
							messageHelper.setText(msg, true);
							
						}
					};
				
				mailSender.send(messagePreparator);
				
				return true;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
	}
}
