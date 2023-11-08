<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Solution(package) -->
<jsp:useBean id="to" class="pack1.MemberTO" />
<jsp:setProperty name="to" property="id" value="tester" />
<jsp:setProperty name="to" property="password" value="1234" />

<!-- out.println + getXXX() -->
<jsp:getProperty name="to" property="id" /><br />
<jsp:getProperty name="to" property="password" /><br />