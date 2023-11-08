<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <c:redirect url="https://www.daum.net" /> --%>
<%-- 
<c:redirect url="https://search.daum.net/search">
	<c:param name="w" value="tot" />
	<c:param name="q" value="아시안컵" />
</c:redirect>
--%>
 <%-- 
<c:redirect url="https://search.naver.com/search.naver">
	<c:param name="query" value="아시안컵" />
</c:redirect>
--%>
 
<c:url var="url1" value="./list.do" />
${ url1 }<br />

<c:url var="url2" value="https://search.daum.net/search">
	<c:param name="w" value="tot" />
	<c:param name="q" value="아시안컵" />
</c:url>
${ url2 }<br />

<c:redirect url="${ url2 }" />