<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="upload1_ok.jsp" method="post" enctype="multipart/form-data" >
데이터1 <input type="text" name="data1" /><br /><br />
데이터2 <input type="text" name="data2" /><br /><br />
파일 <input type="file" name="upload" /><br /><br />
<input type="submit" value="전송" />
</form>

</body>
</html>