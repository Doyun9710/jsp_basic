<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.IOException" %>
<%@ page import="java.io.InputStream" %>

<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>

<%@ page import="java.util.List" %>

<%@ page import="com.exam.model1.DeptDAO" %>
<%@ page import="com.exam.model1.DeptTO" %>

<%--
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
--%>

<%
	String resource = "myBatisConfig.xml";
	
	InputStream is = null;
	SqlSession sqlSession = null;
	
	StringBuilder sbHtml = new StringBuilder();
	
	try {
		is = Resources.getResourceAsStream( resource );
		SqlSessionFactory sqlSessionFactory
		= new SqlSessionFactoryBuilder().build( is );

		sqlSession = sqlSessionFactory.openSession( true );
		
		List<DeptTO> lists = sqlSession.selectList( "selectlist" );

		sbHtml.append( "<table width='600' border='1'>" );
		for( DeptTO to : lists ) {
			sbHtml.append( "<tr>" );
			sbHtml.append( "<td>" + to.getDeptno() + "</td>" );
			sbHtml.append( "<td>" + to.getDname() + "</td>" );
			sbHtml.append( "<td>" + to.getLoc() + "</td>" );
			sbHtml.append( "</tr>" );
		}
		sbHtml.append( "</table>" );
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

<%= sbHtml %>

</body>
</html>