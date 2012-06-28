package org.symagic.common.service;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.utilty.presentation.bean.OrderBean;

public class MailService {

	private static final String MAIL_USERNAME = "";

	private static final String MAIL_PASSWORD = "";

	public static void sendString(String str) {

	}

	public static void sendOrder(BeanOrder order) {

	}

	public static void sendDeleteOrder(BeanOrder order) {

	}

	public static void sendFailOrder(BeanOrder order) {

	}

	public static void sendSuccessOrder(BeanOrder order) {

	}
	
	public static void sendPassOrder(BeanOrder order) {

	}

	public static void sendNewPassword(BeanUser user) {
		JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
		javaMail.setUsername(MAIL_USERNAME);
		javaMail.setPassword(MAIL_PASSWORD);

		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.debug", "true");
		prop.setProperty("mail.smtp.socketFactory.port", "465");
		prop.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.smtp.socketFactory.fallback", "false");
		prop.setProperty("mail.smtp.port", "465");
		prop.setProperty("mail.smtp.socketFactory.port", "465");

		javaMail.setJavaMailProperties(prop);
		javaMail.setProtocol("smtp");

		MimeMessage message = javaMail.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(user.getUsername());
			helper.setFrom(MAIL_USERNAME);
			helper.setSubject("");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
