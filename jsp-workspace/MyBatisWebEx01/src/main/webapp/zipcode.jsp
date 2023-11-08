<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.IOException" %>
<%@ page import="java.io.InputStream" %>

<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>

<%@ page import="model1.ZipcodeTO" %>
<%@ page import="java.util.List" %>

<%
	String resource = "myBatisConfig.xml";
	
	InputStream is = null;
	SqlSession sqlSession = null;
	
	try {
		is = Resources.getResourceAsStream( resource );
		SqlSessionFactory sqlSessionFactory
		= new SqlSessionFactoryBuilder().build( is );
		
		sqlSession = sqlSessionFactory.openSession();
	
		List<ZipcodeTO> lists = sqlSession.selectList( "selectparamlist3", "신사" );
	
		out.println( "<table border='1'>" );
		for( int i=0 ; i<lists.size() ; i++ ) {
			ZipcodeTO to = lists.get(i);
			
			out.println( "<tr>" );
			out.println( "<td>" + to.getZipcode() + to.getSido() + to.getGugun() + to.getDong() + to.getRi() + to.getBunji() + "</td>" );
			out.println( "</tr>" );
		}
		out.println( "</table>" );
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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