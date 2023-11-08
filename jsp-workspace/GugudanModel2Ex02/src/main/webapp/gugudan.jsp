<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
gugudan.jsp

<br /><br />

<form action='./controller' method='post'>
<input type="hidden" name="path" value="gugudan_ok" />
시작단 : <input type='text' name='startdan' />
- 
끝단 : <input type='text' name='enddan' />
&nbsp;&nbsp;<input type='submit' value='구구단 출력' />
</form>

</body>
</html>