package com.exam.model2;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model1.BoardDAO;
import com.exam.model1.BoardTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ModifyOkAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println( "ModifyOkAction 호출" );

		String uploadPath = "C:/Java/jsp-workspace/AlbumCmtEx02/src/main/webapp/upload";
		int maxFileSize = 2 * 1024 * 1024;
		String encType = "utf-8";
		
		try {
			MultipartRequest multi = new MultipartRequest( request, uploadPath, maxFileSize, encType, new DefaultFileRenamePolicy() );
			
			String cpage = multi.getParameter( "cpage" );

			BoardTO to = new BoardTO();
			to.setSeq( multi.getParameter( "seq" ) );
			to.setPassword( multi.getParameter( "password" ) );

			to.setSubject( multi.getParameter( "subject" ) );
			to.setMail( "" );
			if(!multi.getParameter( "mail1" ).equals( "" ) && !multi.getParameter( "mail2" ).equals( "" )) {
				to.setMail( multi.getParameter( "mail1" ) + "@" + multi.getParameter( "mail2" ) );
			}

			to.setContent( multi.getParameter( "content" ) );

			//String oldfilename = multi.getParameter( "filename_org" );
			to.setFilename( multi.getFilesystemName( "upload" ) );
			to.setFilesize( 0 );
			if( multi.getFile( "upload" ) != null ) {
				to.setFilesize( multi.getFile( "upload" ).length() );
			}
			
			to.setLatitude( multi.getParameter( "latitude" ) );
			to.setLongitude( multi.getParameter( "longitude" ) );

			BoardDAO dao = new BoardDAO();
			int flag = dao.boardModifyOk( to );
			
			request.setAttribute( "flag", flag );
			request.setAttribute( "cpage", cpage );
		} catch (IOException e) {
			System.out.println( "[에러] " + e.getMessage() );
		}
	}

}
