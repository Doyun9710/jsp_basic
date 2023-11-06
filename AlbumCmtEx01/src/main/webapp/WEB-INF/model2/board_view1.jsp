<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.exam.model1.BoardTO" %>
<%@ page import="com.exam.model1.BoardReplyTO" %>

<% 
	request.setCharacterEncoding( "utf-8" );

	String cpage = request.getParameter( "cpage" );

	BoardTO to = (BoardTO)request.getAttribute( "to" );
	
	String seq = to.getSeq();
	String subject = to.getSubject();
	String writer = to.getWriter();
	String mail = to.getMail();
	String wip = to.getWip();
	String wdate = to.getWdate();
	String hit = to.getHit();
	String content = to.getContent();

	String filename = to.getFilename();
	long filesize = to.getFilesize();
	
	String latitude = to.getLatitude();
	String longitude = to.getLongitude();
	
	String nextseq = to.getNextseq();
	String nextsub = to.getNextsub();
	String beseq = to.getBeseq();
	String besub = to.getBesub();
	
	String file = "";
	if( filesize != 0 ) {
		//file = "<a href='../../upload/" + filename + "'>(" + filesize + "Kbyte)";
		file = "<a href='./download.jsp?filename=" + filename + "'>" + filename + "(" + filesize + "Kbyte)";
	}
	
	
	
	StringBuilder sbHtml = new StringBuilder();
	ArrayList<BoardReplyTO> boardReplyLists = (ArrayList)request.getAttribute( "boardReplyLists" );
	int passcheck = boardReplyLists.size();
	int count = 1;
	for( BoardReplyTO rto : boardReplyLists ) {
		//if( count++ == passcheck ) continue;	// 최신댓글 맨위 추가 model1.BoardDAO.boardReplyList() order by seq desc
		if( count++ == 1 ) continue;			// 최신댓글 아래 추가 model1.BoardDAO.boardReplyList() order by seq asc
		
		String cwriter = rto.getCwriter();
		String ccontent = rto.getCcontent();
		String cdate = rto.getCdate();

		sbHtml.append( "<tr>" );
		sbHtml.append( "<td class='coment_re' width='20%'>" );
		sbHtml.append( "<strong>" + cwriter + "</strong> (" + cdate + ")" );
		sbHtml.append( "<div class='coment_re_txt'>" );
		sbHtml.append( ccontent );
		sbHtml.append( "</div>" );
		sbHtml.append( "</td>" );
		sbHtml.append( "</tr>" );
	}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/board_view.css">
<script type="text/javascript">
	window.onload = function() {
		document.getElementById( 'cbtn' ).onclick = function() {
			//alert( 'click' );
			if( document.cfrm.cwriter.value.trim() == '' ) {
				alert( '글쓴이를 입력하셔야 합니다' );
				return;
			}
			if( document.cfrm.cpassword.value.trim() == '' ) {
				alert( '비밀번호를 입력하셔야 합니다' );
				return;
			}
			if( document.cfrm.ccontent.value.trim() == '' ) {
				alert( '내용을 입력하셔야 합니다' );
				return;
			}
			document.cfrm.submit();
		}
	}
</script>
</head>

<body>
<!-- 상단 디자인 -->
<div class="contents1"> 
	<div class="con_title"> 
		<p style="margin: 0px; text-align: right">
			<img style="vertical-align: middle" alt="" src="./images/home_icon.gif" /> &gt; 커뮤니티 &gt; <strong>여행지리뷰</strong>
		</p>
	</div>

	<div class="contents_sub">	
	<!--게시판-->
		<div class="board_view">
			<table>
			<tr>
				<th width="10%">제목</th>
				<td width="60%"><%= subject %>(<%= wip %>)</td>
				<th width="10%">등록일</th>
				<td width="20%"><%= wdate %></td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td><%= writer %></td>
				<th>조회</th>
				<td><%= hit %></td>
			</tr>
			<tr>
				<th>위치정보</th>
				<td>
					위도 : (<%= latitude %>) / 경도 : (<%= longitude %>)
					<input type="button" value="지도보기" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='https://www.google.com/maps/@<%= latitude %>,<%= longitude %>,17z'" /> 
				</td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<td colspan="4" height="200" valign="top" style="padding:20px; line-height:160%">
					<div id="bbs_file_wrap">
						<div>
							<%
								if( filename != "없음" )
									out.println( "<img src='./upload/" + filename + "' width='900' onerror='' /><br />" );
								else
									out.println( "<img src='./images/noimage.jpg' width='300' onerror='' /><br />" );
							%>
						</div>
					</div>
					<%= content %>
				</td>
			</tr>			
			</table>
			
			<table>
<%= sbHtml %>
			</table>

			<form action="reply_ok.do" method="post" name="cfrm">
			<input type="hidden" name="cpage" value="<%= cpage %>" />
			<input type="hidden" name="pseq" value="<%= seq %>" />
			<table>
			<tr>
				<td width="94%" class="coment_re">
					글쓴이 <input type="text" name="cwriter" maxlength="5" class="coment_input" />&nbsp;&nbsp;
					비밀번호 <input type="password" name="cpassword" class="coment_input pR10" />&nbsp;&nbsp;
				</td>
				<td width="6%" class="bg01"></td>
			</tr>
			<tr>
				<td class="bg01">
					<textarea name="ccontent" cols="" rows="" class="coment_input_text"></textarea>
				</td>
				<td align="right" class="bg01">
					<input type="button" id="cbtn" value="댓글등록" class="btn_re btn_txt01" />
				</td>
			</tr>
			</table>
			</form>
		</div>
		<div class="btn_area">
			<div class="align_left">
				<input type="button" value="목록" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='list.do?cpage=<%= cpage %>'" />
			</div>
			<div class="align_right">
				<input type="button" value="수정" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='modify.do?cpage=<%= cpage %>&seq=<%= seq %>'" />
				<input type="button" value="삭제" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='delete.do?cpage=<%= cpage %>&seq=<%= seq %>'" />
				<input type="button" value="쓰기" class="btn_write btn_txt01" style="cursor: pointer;" onclick="location.href='write.do?cpage=<%= cpage %>'" />
			</div>
		</div>
		<!--//게시판-->
		
		<!-- 이전글 / 다음글 -->
		<div class="next_data_area">
			<span class="b">다음글 | </span><a href="view.do?cpage=<%= cpage %>&seq=<%= nextseq %>"><%= nextsub %></a>
		</div>
		<div class="prev_data_area">
			<span class="b">이전글 | </span><a href="view.do?cpage=<%= cpage %>&seq=<%= beseq %>"><%= besub %></a>
		</div>
		<!-- //이전글 / 다음글 -->
	</div>
<!-- 하단 디자인 -->
</div>

</body>
</html>
