package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exam.model1.BoardListTO;
import com.exam.model1.BoardTO;

public interface BoardMapperInter {

	@Select( "select seq, subject, writer, filename, filesize, date_format(wdate, '%Y-%m-%d') wdate, hit, datediff(now(), wdate) wgap\r\n"
			+ "		from img_board1\r\n"
			+ "		order by seq desc" )
	public List<BoardTO> list();
	@Select( "select seq, subject, writer, filename, filesize, date_format(wdate, '%Y-%m-%d') wdate, hit, datediff(now(), wdate) wgap\r\n"
			+ "		from img_board1\r\n"
			+ "		where ${ searchKey } like #{ searchWord }\r\n"
			+ "		order by seq desc" )
	public List<BoardTO> listSearch(BoardListTO listTO);
	
	@Select( "select seq, subject, writer, mail, wip, wdate, hit, content, filename, filesize\r\n"
			+ "		from img_board1\r\n"
			+ "		where seq=#{ seq }" )
	public BoardTO view(BoardTO to);
	
	@Update( "update img_board1\r\n"
			+ "		set hit=hit+1\r\n"
			+ "		where seq=#{ seq }" )
	public void view_hit(BoardTO to);
	
	@Select( "select seq, subject \r\n"
			+ "		from img_board1 \r\n"
			+ "		where seq > #{ seq }\r\n"
			+ "		order by seq asc limit 1" )
	public BoardTO view_nextpage(BoardTO to);
	
	@Select( "select seq, subject \r\n"
			+ "		from img_board1 \r\n"
			+ "		where seq < #{ seq }\r\n"
			+ "		order by seq desc limit 1" )
	public BoardTO view_lastpage(BoardTO to);
	
	@Insert( "insert into img_board1\r\n"
			+ "		values ( 0, #{ subject }, #{ writer }, #{ mail }, #{ password }, #{ content }, #{ filename }, #{ filesize }, 0, #{ wip }, now() )\r\n" )
	public int write_ok(BoardTO to);
	
	@Select( "select seq, subject, writer, mail, content, filename \r\n"
			+ "		from img_board1 \r\n"
			+ "		where seq=#{ seq }" )
	public BoardTO modify(BoardTO to);
	
	@Select( "select filename \r\n"
			+ "		from img_board1 \r\n"
			+ "		where seq=#{ seq }" )
	public String modify_ok_select_filename(BoardTO to);
	
	@Update( "update img_board1 \r\n"
			+ "		set subject=#{ subject }, mail=#{ mail }, content=#{ content }, filename=#{ filename }, filesize=#{ filesize }\r\n"
			+ "		where seq=#{ seq } and password=#{ password }" )
	public int modify_ok(BoardTO to);
	
	@Select( "select seq, subject, writer \r\n"
			+ "		from img_board1 \r\n"
			+ "		where seq=#{ seq }" )
	public BoardTO delete(BoardTO to);
	
	@Delete( "delete from img_board1 \r\n"
			+ "		where seq=#{ seq } and password=#{ password }" )
	public int delete_ok(BoardTO to);
	
	@Select( "select filename \r\n"
			+ "		from img_board1 \r\n"
			+ "		where seq=#{ seq }" )
	public String delete_select_filename(BoardTO to);
}
