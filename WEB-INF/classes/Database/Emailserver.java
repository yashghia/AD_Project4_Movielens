package Database;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Emailserver {
	public static void emailRecommend(String name, String senderMail,  String friendMail, String messageTxt,String token,String link){
		final String username = "nbad3assignment@gmail.com";
		final String password = "1234qwer$";
		String[] to = { friendMail };
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			 InternetAddress me = new InternetAddress(senderMail);
		        try {
					me.setPersonal(name);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        message.setFrom(me);
			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject("New Recommend");
			message.setText("HI "+ friendMail.split("\\@")[0]  + ",\n" 
					+ "\nYou have been recommended for "+name+".\n\n"
					+"Message:  "+messageTxt
                                        + "\n\nPlease click on the link below to sign up "+".\n\n"
					+ link + "?action=recsignup&token="+token
					+ "\n\nRegards,\n" + name + "");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public static void emailContact(String name, String friendMail, String messageTxt){
		final String username = "nbad3assignment@gmail.com";
		final String password = "1234qwer$";
		String[] to = { friendMail };
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			 InternetAddress me = new InternetAddress("nbad3assignment@gmail.com");
		        try {
					me.setPersonal(name);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        message.setFrom(me);
			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject("New Contact");
			message.setText("Hi "+ friendMail.split("\\@")[0]  + ",\n" 
					+ "\nYou have been added as a contact for "+name+".\n\n"
					+"Message:  "+messageTxt
					+ "\n\nRegards,\n" + name + "");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
        public static void activateUser(String name, String email, String token,String link){
		final String username = "nbad3assignment@gmail.com";
		final String password = "1234qwer$";
		String[] to = { email };
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			 InternetAddress me = new InternetAddress("nbad3assignment@gmail.com");
		        try {
					me.setPersonal(name);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        message.setFrom(me);
			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject("Activation email");
			message.setText("Hi "+ name  + ",\n" 
					+ "\nPlease click on the link below to activate your account "+".\n\n"
					+ link + "?action=activate&token="+token
                                        + "\n\n Thank You");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

    public static void resetPass(String name, String email, String token,String link) {
        final String username = "nbad3assignment@gmail.com";
		final String password = "1234qwer$";
		String[] to = { email };
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			 InternetAddress me = new InternetAddress("nbad3assignment@gmail.com");
		        try {
					me.setPersonal(name);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        message.setFrom(me);
			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject("Reset Password");
			message.setText("Hi "+ name  + ",\n\n" 
					+ "\nPlease click on the link below to reset your password .\n\n"
					+ link + "?action=reset&token="+token
					+ "\n\nThank You");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
        
	
}
