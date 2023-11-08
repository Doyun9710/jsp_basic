<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.exam.mail.MailSender"%>

<%
	//받을 이메일 주소
	String toEmail = "yuncoding1010@gmail.com";
	// 받을 사람 이름
	String toName = "테스터";
	// 보낼 제목
	String subject = "테스트 제목";
	// 보낼 내용 html
	StringBuilder sbHtml = new StringBuilder();
	sbHtml.append("<html>");
	sbHtml.append("<head>");
	sbHtml.append("<meta charset='utf-8' />");
	sbHtml.append("</head>");
	sbHtml.append("<body>");
	sbHtml.append("<font color='blue'>테스트 내용</font>");
	sbHtml.append("<img src='https://t1.daumcdn.net/daumtop_chanel/op/20200723055344399.png' />");
	sbHtml.append("</body>");
	sbHtml.append("</html>");
	
	MailSender sender = new MailSender( "yuncoding1010@gmail.com", "nuvx vbiw imqr daui" );
	sender.sendMail( toEmail, toName, subject, sbHtml.toString() );
%>