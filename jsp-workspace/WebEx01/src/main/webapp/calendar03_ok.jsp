<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date"%>
<%
	// 연도 / 월 입력
	int year = Integer.parseInt(request.getParameter("year"));
	int month = Integer.parseInt(request.getParameter("month"));

	Calendar sDay = Calendar.getInstance();
	Calendar eDay = Calendar.getInstance();
    
	sDay.set( year, month-1, 1 );
	eDay.set( year, month, 1-1 );
    
	int startDayOfWeek = sDay.get( Calendar.DAY_OF_WEEK );
	int endDayOfWeek = eDay.get( Calendar.DAY_OF_WEEK );
	int endDay = eDay.get( Calendar.DATE );
	
	Date today = new Date();
	int today1 = Integer.parseInt(today.toString().substring(8, 10));
    
	StringBuilder sbHtml = new StringBuilder();
	
	//sbHtml.append( today1 + "<br />" );
	sbHtml.append( "<table width='800' border='1'>" );
	sbHtml.append( "<colgroup>" );
	sbHtml.append( "<col span='6' style='background-color:white'>" );
	sbHtml.append( "<col style='background-color:darksalmon'>" );
	sbHtml.append( "</colgroup>" );
	sbHtml.append( "<tr>" );
	sbHtml.append( "<td colspan='7'>" + year + "년" + month + "월</td>" );
	sbHtml.append( "</tr>" );
	sbHtml.append( "<tr>" );
	sbHtml.append( "<td>SU</td><td>MO</td><td>TU</td><td>WE</td><td>TH</td><td>FR</td><td>SA</td>" );
	sbHtml.append( "</tr>" );
     
	sbHtml.append( "<tr>" );
	for( int i=1 ; i<startDayOfWeek ; i++ ) {
		sbHtml.append( "<td></td>" );
	}
     
	for( int i=1, n=startDayOfWeek ; i<=endDay ; i++, n++ ) {
		if( n % 7 == 1 ) sbHtml.append( "<tr>" );
        
		if( i == today1 ) {
			sbHtml.append( "<td><b>" + i + "</b></td>" );
		} else {
			sbHtml.append( "<td>" + i + "</td>" );
		}
          
		if( n % 7 == 0 ) sbHtml.append( "</tr>" );
	}
     
	for( int i=endDayOfWeek ; i<=6 ; i++ ) {
		sbHtml.append( "<td></td>" );
	}
	
	sbHtml.append( "</tr>" );
	sbHtml.append( "</table>" );
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%=sbHtml %>

</body>
</html>