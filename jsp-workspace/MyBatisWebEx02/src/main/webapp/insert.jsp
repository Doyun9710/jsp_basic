<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.IOException" %>
<%@ page import="java.io.InputStream" %>

<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>

<%@ page import="java.util.List" %>

<%@ page import="com.exam.model1.DeptTO" %>

<%
	String resource = "myBatisConfig.xml";
	
	InputStream is = null;
	SqlSession sqlSession = null;
	
	try {
		is = Resources.getResourceAsStream( resource );
		SqlSessionFactory sqlSessionFactory
		= new SqlSessionFactoryBuilder().build( is );
		System.out.println( "설정 성공" );
		
		// true 값 시 auto commit
		sqlSession = sqlSessionFactory.openSession( true );
		System.out.println( "연결 성공" );
		
		DeptTO to = new DeptTO();
		to.setDeptno( "90" );
		to.setDname( "개발부" );
		to.setLoc( "부산" );

		int result = sqlSession.insert( "insert1", to );
		if( result > 0 ) {
			System.out.println( "입력 성공" );
		} else {
			System.out.println( "입력 실패" );
		}
	} catch (IOException e) {
		System.out.println( "[에러] " + e.getMessage() );
	} finally {
		if( sqlSession != null ) sqlSession.close();
		if( is != null ) is.close();
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