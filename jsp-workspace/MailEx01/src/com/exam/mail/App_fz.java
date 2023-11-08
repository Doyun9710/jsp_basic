package com.exam.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App_fz {
	private String fromEmail;
	private String fromPassword;
	
	public App_fz(String fromEmail, String fromPassword) {
		this.fromEmail = fromEmail;
		this.fromPassword = fromPassword;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 받을 이메일 주소
		String toEmail = "mobilepower.pro1@gmail.com";
		// 받을 사람 이름
		String toName = "테스터";
		// 보낼 제목
		String subject = "테스트 제목";
		// 보낼 내용
		String content = "테스트 내용";
		
		App_fz app = new App_fz( "인증 이메일", "인증 암호" );
		app.sendMail( toEmail, toName, subject, content );
		
	}

	public void sendMail( String toEmail, String toName, String subject, String content) {
		
		try {
			Properties props = new Properties();
			props.put( "mail.smtp.starttls.enable", "true" );
			props.put( "mail.smtp.host", "smtp.gmail.com" );
			props.put( "mail.smtp.port", "465" );
			props.put( "mail.smtp.auth", "true" );
			
			props.put( "mail.transport.protocol", "smtp" );
			props.put( "mail.smtp.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory" );
			
			MyAuthenticator authenticator = new MyAuthenticator(fromEmail, fromPassword);
			
			Session session = Session.getDefaultInstance(props, authenticator);
			
			// 메세지 설정
			MimeMessage msg = new MimeMessage(session);
			
			msg.setHeader( "Content-type", "text/plain;charset=utf-8" );
			msg.addRecipient(
					Message.RecipientType.TO
					, new InternetAddress( toEmail, toName, "utf-8") );
			msg.setSubject( subject );
			msg.setContent( content, "text/plain; charset=utf-8" );
			
			msg.setSentDate( new java.util.Date() );
			
			Transport.send( msg );
			
			System.out.println( "메일 전송 완료" );
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
















