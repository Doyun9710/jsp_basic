package com.exam.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App {
	private String fromEmail;
	private String fromPassword;
	
	public App(String fromEmail, String fromPassword) {
		this.fromEmail = fromEmail;
		this.fromPassword = fromPassword;
	}

	public static void main(String[] args) {
		// 받을 이메일 주소
		String toEmail = "yuncoding1010@gmail.com";
		//String toEmail = "99@gmail.com";
		//String toEmail = "dnr1297@gmail.com";
		// 받을 사람 이름
		String toName = "테스터";
		// 보낼 제목
		String subject = "테스트 제목";
		// 보낼 내용
		// html -> 디자인
		//String content = "테스트 내용";
		StringBuilder sbHtml = new StringBuilder();
		sbHtml.append( "<html>" );
		sbHtml.append( "<head>" );
		sbHtml.append( "<meta charset='utf-8' />" );
		sbHtml.append( "</head>" );
		sbHtml.append( "<body>" );
		// https://t1.daumcdn.net/daumtop_chanel/op/20200723055344399.png
		sbHtml.append( "<font color='blue'>테스트 내용</font>" );
		sbHtml.append( "<img src='https://t1.daumcdn.net/daumtop_chanel/op/20200723055344399.png' />" );
		sbHtml.append( "</body>" );
		sbHtml.append( "</html>" );
		
		App app = new App( "yuncoding1010@gmail.com", "nuvx vbiw imqr daui" );
		app.sendMail(toEmail, toName, subject, sbHtml.toString());
	}
	
	public void sendMail(String toEmail, String toName, String subject, String content) {
		try {
			Properties props = new Properties();
			// 서버와의 연결 설정
			props.put( "mail.smtp.starttls.enable", "true" );
			props.put( "mail.smtp.host", "smtp.gmail.com" );
			props.put( "mail.smtp.port", "465" );
			props.put( "mail.smtp.auth", "true" );
			
			props.put( "mail.transport.protocol", "smtp" );
			props.put( "mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory" );
			
			MyAuthenticator authenticator = new MyAuthenticator(fromEmail, fromPassword);
			
			Session session = Session.getDefaultInstance(props, authenticator);
			
			// Message 설정
			MimeMessage msg = new MimeMessage(session);
			//msg.setHeader( "Content-type", "text/plain;charset=utf-8" );
			msg.setHeader( "Content-type", "text/html;charset=utf-8" );
			msg.addRecipient( Message.RecipientType.TO, new InternetAddress( toEmail, toName, "utf-8" ) );
			msg.setSubject( subject );
			//msg.setContent( content, "text/plain; charset=utf-8" );
			msg.setContent( content, "text/html; charset=utf-8" );
			
			msg.setSentDate( new java.util.Date() );
			
			Transport.send(msg);
			
			System.out.println( "메일 전송 완료" );
		} catch (UnsupportedEncodingException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} catch (MessagingException e) {
			System.out.println( "[에러] " + e.getMessage() );
		}
	}
}
