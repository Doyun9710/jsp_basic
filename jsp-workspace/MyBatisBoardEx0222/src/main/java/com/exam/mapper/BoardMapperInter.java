package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exam.model1.BoardListTO;
import com.exam.model1.BoardTO;

public interface BoardMapperInter {

	@Select( "select seq, subject, writer, date_format(wdate, '%Y-%m-%d') wdate, hit, datediff(now(), wdate) wgap from board1 order by seq desc" )
	public List<BoardTO> boardList();
	@Select( "select seq, subject, writer, date_format(wdate, '%Y-%m-%d') wdate, hit, datediff(now(), wdate) wgap from board1 where ${ searchKey } like #{ searchWord } order by seq desc" )
	public List<BoardTO> boardListSearch(BoardListTO listTO);
	
	@Insert( "insert into board1 values ( 0, #{ subject }, #{ writer }, #{ mail }, #{ password }, #{ content }, 0, #{ wip }, now() )\r\n" )
	public int boardWriteOK(BoardTO to);
	
	@Select( "select seq, subject, writer, mail, wip, wdate, hit, content from board1 where seq=#{ seq }" )
	public BoardTO boardView(BoardTO to);
	
	@Update( "update board1 set hit=hit+1 where seq=#{ seq }" )
	public void boardViewHit(BoardTO to);
	
	@Select( "select seq, subject, writer, mail, content from board1 where seq=#{ seq }" )
	public BoardTO boardModify(BoardTO to);
	
	@Update( "update board1 set subject=#{ subject }, mail=#{ mail }, content=#{ content } where seq=#{ seq } and password=#{ password }" )
	public int boardModifyOk(BoardTO to);
	
	@Select( "select seq, subject, writer from board1 where seq=#{ seq }" )
	public BoardTO boardDelete(BoardTO to);
	
	@Delete( "delete from board1 where seq=#{ seq } and password=#{ password }" )
	public int boardDeleteOk(BoardTO to);
	
}
