<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${ header }<br />
<br /><hr /><br />
${ header['host'] }<br />
${ header['user-agent'] }<br />

${ header.host }<br />
<!-- ${ header.user-agent }<br /> -->
</body>
</html>