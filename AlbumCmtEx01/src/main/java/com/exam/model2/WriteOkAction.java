package com.exam.model2;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model1.BoardDAO;
import com.exam.model1.BoardTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteOkAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println( "WriteOkAction 호출" );

		try {
			String uploadPath = "C:/Java/jsp-workspace/AlbumCmtEx01/src/main/webapp/upload";
			int maxFileSize = 2 * 1024 * 1024;
			String encType = "utf-8";
			
			MultipartRequest multi = new MultipartRequest( request, uploadPath, maxFileSize, encType, new DefaultFileRenamePolicy() );
			BoardTO to = new BoardTO();
			
			to.setSubject( multi.getParameter( "subject" ) );
			to.setWriter( multi.getParameter( "writer" ) );
			to.setMail( "" );
			if( !multi.getParameter( "mail1" ).equals("") && !multi.getParameter( "mail2" ).equals("") ) {
				to.setMail( multi.getParameter( "mail1" ) + "@" + multi.getParameter( "mail2" ) );
			}
			to.setPassword( multi.getParameter( "password" ) );
			to.setContent( multi.getParameter( "content" ) );
			
			to.setWip( request.getRemoteAddr() );

			to.setFilename( multi.getFilesystemName( "upload" ) );
			to.setFilesize( 0 );
			if( multi.getFile( "upload" ) != null ) {
				to.setFilesize( multi.getFile( "upload" ).length() );
			}
			
			to.setLatitude( multi.getParameter( "latitude" ) );
			to.setLongitude( multi.getParameter( "longitude" ) );

			BoardDAO dao = new BoardDAO();
			int flag = dao.boardWriteOk( to );
			
			request.setAttribute( "flag", flag );
		} catch (IOException e) {
			System.out.println( "[에러] " + e.getMessage() );
		}

	}

}
