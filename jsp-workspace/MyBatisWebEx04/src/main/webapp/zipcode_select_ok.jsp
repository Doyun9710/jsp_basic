<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.IOException" %>
<%@ page import="java.io.InputStream" %>

<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>

<%@ page import="java.util.List" %>

<%@ page import="com.exam.model1.ZipcodeTO" %>
<%@ page import="com.exam.mapper.SqlMapperInter" %>

<%
	request.setCharacterEncoding( "utf-8" );
	String strdong = request.getParameter( "strdong" );
	
	String resource = "myBatisConfig.xml";
	
	InputStream is = null;
	SqlSession sqlSession = null;
	
	StringBuilder sbHtml = new StringBuilder();
	
	try {
		is = Resources.getResourceAsStream( resource );
		SqlSessionFactory sqlSessionFactory
		= new SqlSessionFactoryBuilder().build( is );

		sqlSession = sqlSessionFactory.openSession( true );
		
		sqlSession.getConfiguration().addMapper( SqlMapperInter.class );
		SqlMapperInter mapper = (SqlMapperInter)sqlSession.getMapper( SqlMapperInter.class );
		
		//List<DeptTO> lists = mapper.selectList();
		//List<DeptTO> lists = mapper.selectListByDeptno( "20" );
		/*
		DeptTO paramTO = new DeptTO();
		paramTO.setDeptno( "30" );
		List<DeptTO> lists = mapper.selectListByDeptno( paramTO );
		*/
		List<ZipcodeTO> lists = mapper.selectListZipcode( strdong );
		
		sbHtml.append( "<table border='1'>" );
		for( ZipcodeTO to : lists ) {
	sbHtml.append( "<tr>" );
	
	sbHtml.append( "<td>" + to.getZipcode() + "</td>" );
	sbHtml.append( "<td>" + to.getSido() + "</td>" );
	sbHtml.append( "<td>" + to.getGugun() + "</td>" );
	sbHtml.append( "<td>" + to.getDong() + "</td>" );
	sbHtml.append( "<td>" + to.getRi() + "</td>" );
	sbHtml.append( "<td>" + to.getBunji() + "</td>" );
	
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

<%= sbHtml %>