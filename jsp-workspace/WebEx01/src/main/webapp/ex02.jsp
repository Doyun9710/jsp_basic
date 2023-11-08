<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Date startDay = new Date(2023, 9-1, 1);
	Date endDay = new Date(2023, 9, 1-1);
	int endDate = (int)endDay.getDate();
	String[] dayArr = { "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" };
	
	// out.println( "<h1>" + startDay.toString().substring(0, 16) + "</h1>" );
	out.println( "<h1>" + startDay.toString().substring(4, 8) + "</h1>" );
	
	out.println( "<table border='1' width='800'>");
	    for( int row=0 ; row<6 ; row++ ) {
	        out.println( "<tr>" );
	
	        for( int col=0 ; col<7 ; col++ ) {
	            if( row == 0 ) {
	                out.println( "<td>" + dayArr[col] + "</td>" );
	            }
	            else {                        
	                int dayNum = 7*(row-1)+col-(int)(startDay.getDay())+1;
	                out.println( "<td>" );
	                if( 0 <= dayNum && dayNum <= endDate ) {
	                    out.println( 7*(row-1)+col-(int)(startDay.getDay())+2 );
	                }
	                out.println( "</td>" );
	            }
	        }
	        out.println( "</tr>" );
	    }
	out.println( "</table>");
%>
</body>
</html>