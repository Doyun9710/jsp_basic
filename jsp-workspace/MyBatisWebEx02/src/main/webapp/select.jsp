<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.exam.model1.DeptDAO" %>
<%@ page import="com.exam.model1.DeptTO" %>

<%@ page import="java.util.List" %>

<%
	DeptDAO dao = new DeptDAO();
	List<DeptTO> lists = dao.selectlist();
	
	StringBuilder sbHtml = new StringBuilder();
	
	sbHtml.append( "<table width='600' border='1'>" );
	for( DeptTO to : lists ) {
		sbHtml.append( "<tr>" );
		sbHtml.append( "<td>" + to.getDeptno() + "</td>" );
		sbHtml.append( "<td>" + to.getDname() + "</td>" );
		sbHtml.append( "<td>" + to.getLoc() + "</td>" );
		sbHtml.append( "</tr>" );
	}
	sbHtml.append( "</table>" );
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%= sbHtml %>

</body>
</html>