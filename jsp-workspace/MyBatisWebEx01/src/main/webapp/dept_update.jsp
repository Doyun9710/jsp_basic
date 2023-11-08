<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.IOException" %>
<%@ page import="java.io.InputStream" %>

<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>

<%@ page import="model1.DeptTO" %>
<%@ page import="java.util.List" %>

<%
	String resource = "myBatisConfig.xml";
	
	InputStream is = null;
	SqlSession sqlSession = null;
	
	try {
		is = Resources.getResourceAsStream( resource );
		SqlSessionFactory sqlSessionFactory
		= new SqlSessionFactoryBuilder().build( is );
		
		// true 값 시 autocommit
		sqlSession = sqlSessionFactory.openSession( true );
		
		DeptTO to = new DeptTO();
		to.setDeptno( "10" );
		to.setDname( "생산부" );
		
		// 수정된 데이터 개수
		int result = sqlSession.update( "update1", to );
		out.println( "수정 성공 " + result );
	} catch (IOException e) {
		out.println( "[에러] " + e.getMessage() );
	} finally {
		if( sqlSession != null ) sqlSession.close();
		if( is != null ) try { is.close(); } catch(IOException e) {}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>