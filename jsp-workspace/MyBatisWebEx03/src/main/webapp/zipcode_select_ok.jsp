<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.exam.model1.ZipcodeDAO" %>
<%@ page import="com.exam.model1.ZipcodeTO" %>

<%@ page import="java.util.List" %>

<%
	request.setCharacterEncoding( "utf-8" );
	
	String strdong = request.getParameter( "strdong" );

	ZipcodeDAO dao = new ZipcodeDAO();
	List<ZipcodeTO> lists = dao.selectlist( strdong );
	
	StringBuilder sbHtml = new StringBuilder();
	
	sbHtml.append( "<table border='1'>" );
	for( ZipcodeTO to : lists ) {
		sbHtml.append( "<tr>" );
		
		sbHtml.append( "<td>" + to.getZipcode() + "</td>" );
		sbHtml.append( "<td>" + to.getSido() + "</td>" );
		sbHtml.append( "<td>" + to.getGugun() + "</td>" );
		sbHtml.append( "<td>" + to.getDong() + "</td>" );
		sbHtml.append( "<td>" + to.getRi() + "</td>" );
		sbHtml.append( "<td>" + to.getBunji() + "</td>" );
		
		sbHtml.append( "</tr>" );
	}
	sbHtml.append( "</table>" );
%>

<%= sbHtml %>