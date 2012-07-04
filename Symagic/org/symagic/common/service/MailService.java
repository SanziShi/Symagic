package org.symagic.common.service;

import java.util.Properties;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.symagic.common.db.bean.BeanOrder;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.utilty.presentation.bean.OrderBean;

public class MailService {

	private static String MAIL_USERNAME = "symagic_no_reply";

	private static String MAIL_PASSWORD = "symagicadmin";

	private static String MAIL_HOST = "163.com";

	private static String MAIL_SMTP = "smtp.163.com";

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

	public static void sendNewPassword(BeanUser user, String password) {
		String message = "您新密码为" + password;
		sendMail(message, user.getUsername(), "重置密码");
	}

	private static JavaMailSenderImpl javaMail;

	private static void sendMail(String str, String sendTo, String subject) {
		if (javaMail == null) {
			try {
				javaMail = new JavaMailSenderImpl();
				
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
				prop.setProperty("host", MAIL_SMTP);

				javaMail.setJavaMailProperties(prop);
				javaMail.setProtocol("smtp");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// 初始化邮件信息
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(MAIL_USERNAME + "@" + MAIL_HOST);
		message.setTo(sendTo);
		message.setSubject(subject);
		message.setText(str);

		javaMail.send(message);
	}
}
