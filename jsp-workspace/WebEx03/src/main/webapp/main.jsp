<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- main.jsp -->
Hello1.jsp<br />
<!-- Hello2.jsp<br /> -->
<!-- 
	액션태그 : < jsp:
	태그 : jsp 용 태그
	< jsp:include
	인클루드 액션태그
 -->
 <% 
 	int i = 10;
 %>
<jsp:include page="sub.jsp">
	<jsp:param value="value" name="data" />
	<jsp:param value="<%= i %>" name="i" />
</jsp:include>
Hello3.jsp<br /><br />
i = <%= i %><br />
</body>
</html>