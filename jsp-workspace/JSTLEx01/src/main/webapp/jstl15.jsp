<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- html 소스 읽어오기 -->
<%-- <c:import var="htmldata" url="https://www.daum.net" charEncoding="utf-8" /> --%>
<c:import var="htmldata" url="http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml" charEncoding="utf-8" >
	<c:param name="key" value="f5eef3421c602c6cb7ea224104795888" />
	<c:param name="targetDt" value="20120101" />
</c:import>

<textaraa rows="50" col="800" >
${ htmldata }
</textaraa>

</body>
</html>